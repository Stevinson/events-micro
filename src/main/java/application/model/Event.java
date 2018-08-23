package application.model;

/**
 * A model of an event.
 */
public class Event {
	/**
	 * Each event has a unique id.
	 */
	private String id;

	/**
	 * The name of the event.
	 */
	private String name;

	public void setName(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return this.name;
	}
}
