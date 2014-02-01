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
public interface TLOptionsDispatchAPI {
	/**
	 * @param event
	 * @return
	 */
	public void addEvent(TLEvent event);
	/**
	 * @param event
	 * @return
	 */
	public void removeEvent(TLEvent event);
	/**
	 * @param event
	 * @return
	 */
	public void editEvent(TLEvent oldEvent, TLEvent newEvent);
	/**
	 * 
	 */
	public void settings(); //?
	/**
	 * @return
	 */
	public boolean saveTimeline();
}
