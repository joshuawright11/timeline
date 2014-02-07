/**
 * 
 */
package model;

import entities.TLEvent;
import entities.Timeline;

/**
 * @author Josh Wright
 * Created: Jan 29, 2014
 * Package: backend
 *
 */
public class TLOptionsDispatch implements TLOptionsDispatchAPI {
	private Timeline timeline;
	
	TLOptionsDispatch(Timeline timeline){
		this.timeline = timeline; 
	}
	/* (non-Javadoc)
	 * @see backend.TLOptionsDispatchAPI#addEvent(backend.TLEvent)
	 */
	@Override
	public void addEvent(TLEvent event) {timeline.addEvent(event);}

	/* (non-Javadoc)
	 * @see backend.TLOptionsDispatchAPI#removeEvent(backend.TLEvent)
	 */
	@Override
	public void removeEvent(TLEvent event) {timeline.removeEvent(event);}

	/* (non-Javadoc)
	 * @see backend.TLOptionsDispatchAPI#editEvent(backend.TLEvent)
	 */
	@Override
	public void editEvent(TLEvent oldEvent, TLEvent newEvent) {timeline.changeEvent(oldEvent, newEvent);}

	/* (non-Javadoc)
	 * @see backend.TLOptionsDispatchAPI#settings()
	 */
	@Override
	public void settings() {
		//TODO open settings
	}

	/* (non-Javadoc)
	 * @see backend.TLOptionsDispatchAPI#saveTimeline()
	 */
	@Override
	public boolean saveTimeline() {
		return false;
	}

}
