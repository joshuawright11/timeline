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
	public boolean addEvent(TLEvent event);
	/**
	 * @param event
	 * @return
	 */
	public boolean removeEvent(TLEvent event);
	/**
	 * @param event
	 * @return
	 */
	public boolean editEvent(TLEvent event);
	/**
	 * 
	 */
	public void settings(); //?
	/**
	 * @return
	 */
	public boolean saveTimeline();
}
