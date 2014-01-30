/**
 * 
 */
package backend;

/**
 * @author Josh Wright
 * Created: Jan 29, 2014
 * Package: backend
 *
 */
public interface TimelineAPI {
	/**
	 * @param event
	 */
	public void addEvent(TLEvent event);
	/**
	 * @param event
	 * @return
	 */
	public boolean removeEvent(TLEvent event);
	/**
	 * @param oldEventName
	 * @param newEvent
	 * @return
	 */
	public boolean changeEvent(TLEvent oldEventName, TLEvent newEvent);
	/**
	 * @return
	 */
	public TLEvent[] getEvents();
	/**
	 * @return
	 */
	public boolean isDirty();
	/**
	 * @param dirty
	 */
	public void setDirty(boolean dirty);
	/**
	 * @return
	 */
	public String getName();
}
