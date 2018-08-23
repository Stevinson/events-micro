package application.store;

import application.model.Event;
import com.cloudant.client.api.Database;
import java.util.Collection;

/**
 * A database that holds the events.
 */
public interface EventsStore
{
	/**
	 * Get the store object
	 */
	public Database getInstance();

	/**
	 * Return all the {@Event}s from the store.
	 */
	public Collection<Event> getAll();

	/**
	 * Return an {@link Event} from the store given its id.
	 */
	public Event get(String id);

	/**
	 * Persist an {@link Event} into the store.
	 */
	public Event persist(Event event);

	/**
	 * Update an {@link Event} in the store given its id.
	 */
	public Event update(String id, Event event);

	/**
	 * Delete an {@link Event} from the store given its id.
	 */
	public void delete(String id);
}
