package application.store;

import application.model.Event;
import com.cloudant.client.api.ClientBuilder;
import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.List;

/**
 * A Cloudant NoSQL database.
 */
public class CloudantEventsStore implements EventsStore
{
	private static final String DATABASENAME = "microevents-cat-db";

	private Database db = null;

	public CloudantEventsStore()
	{
		CloudantClient cloudant = createClient();
		if (cloudant != null)
		{
			db = cloudant.database(DATABASENAME, true);
		}
	}

	public Database getInstance()
    {
        return db;
    }
	
	private static CloudantClient createClient()
	{
		final String url;

		if (System.getenv("VCAP_SERVICES") != null)
		{
            // In Bluemix the VCAP_SERVICES env var will have the credentials for all services - parse it looking for cloudant.
            JsonObject cloudantCredentials = VCAPHelper.getCloudCredentials("cloudant");
			if (cloudantCredentials ==  null)
			{
				System.out.println("No cloudant database service bound to this application");
				return null;
			}

			url = cloudantCredentials.get("url").getAsString();
		}
		else 
		{
			System.out.println("Running locally, looking for credentials in cloudant.properties");
			url = VCAPHelper.getLocalProperties("cloudant.properties").getProperty("cloudant_url");
			if (url == null || url.length() == 0)
			{
				System.out.println("To use a database, set the cloudant url in src/main/resources/cloudant.properties");
				return null;
			}
		}

		try
		{
			System.out.println("Connecting to Cloudant");
			CloudantClient client = ClientBuilder.url(new URL(url))
					.build();
			return client;
		}
		catch (Exception ex)
        {
            System.out.println("Unable to connect to database"); // TODO: Fail properly
            return null;
        }
	}

	@Override
    public Collection<Event> getAll()
    {
        List<Event> docs;
        try {
            docs = db.getAllDocsRequestBuilder().includeDocs(true).build().getResponse().getDocsAs(Event.class);
        } catch (IOException e) {
            return null;
        }
        return docs;
    }

    @Override
	public Event get(String id)
	{
		return db.find(Event.class, id);
	}

    @Override
	public Event persist(Event event)
	{
		String id = db.save(event).getId();
		return db.find(Event.class, id);
	}

    @Override
	public Event update(String id, Event updatedEvent)
	{
		Event event = db.find(Event.class, id);
		event.setName(updatedEvent.getName());
		db.update(event);
		return db.find(Event.class, id);
	}

	public void delete(String id)
	{
		Event event = db.find(Event.class, id);
		db.remove(event);
	}
}
