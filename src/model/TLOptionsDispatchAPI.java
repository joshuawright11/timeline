/**
 * 
 */
package model;

import entities.TLEvent;

/**
 * @author Josh Wright
 * Created: Jan 29, 2014
 * Package: backend
 *
 */
public interface TLOptionsDispatchAPI {
	public void addEvent(TLEvent event);
	public void removeEvent(TLEvent event);
	public void editEvent(TLEvent oldEvent, TLEvent newEvent);
	public void settings(); //do later
	public boolean saveTimeline(); //do later
}
