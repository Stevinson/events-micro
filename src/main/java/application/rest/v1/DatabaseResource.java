package application.rest.v1;

import application.model.Event;
import application.store.DatabaseType;
import application.store.EventsStore;
import application.store.EventsStoreFactory;
import com.google.gson.Gson;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Path("api")
public class DatabaseResource {

//	EventsStore store = EventsStoreFactory.build(DatabaseType.Cloudant);

	/**
	 * Get all the {@link Event}s.
	 */
	@Path("/events")
	@GET
	@Produces("application/json")
	public String events() // TODO: Change returned type to a Response
	{
		EventsStore store = EventsStoreFactory.getInstance();

		if (store == null)
		{
			return "[]";
		}
		List<String> eventNames = new ArrayList<String>();

		final Collection<Event> events = store.getAll();

		events.stream()
				.filter(event -> event.getName() != null) // TODO: Event fields need to be made required so this not needed
				.map(event -> eventNames.add(event.getName()))
				.collect(Collectors.toList());

		return new Gson().toJson(eventNames);
	}
}
