package application.store;

// TODO: refactor the two database factories into each other

/**
 * A factory for building {@link EventsStore}s.
 */
public class EventsStoreFactory 
{
	// TODO: This code makes it a singleton but is causing a InvocationTargetException - put back in
//	private static EventsStore instance;
//
//	public static EventsStore build(DatabaseType dbType)
//	{
//		if (instance == null)
//		{
//			switch (dbType)
//			{
////				case MySql:
////					instance = new MySqlEventsStore();
////					break;
//				case Cloudant:
//					instance = new CloudantEventsStore();
//					break;
//				default:
//					// TODO: throw exception
//					break;
//			}
//		}
//		return instance;
//	}

	private static EventsStore instance;

	public EventsStoreFactory()
	{
	}

	public static EventsStore getInstance() {
		if (instance == null)
		{
			CloudantEventsStore cvif = new CloudantEventsStore();
			if(cvif.getInstance() != null){
				instance = cvif;
			}
		}
		return instance;
	}
}
