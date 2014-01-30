/**
 * 
 */
package backend;

import java.util.ArrayList;

/**
 * @author Josh Wright
 * Created: Jan 29, 2014
 * Package: backend
 *
 */
public class Timeline implements TimelineAPI{
	ArrayList<TLEvent> events;
	private String name;
	private boolean dirty;
	
	Timeline(String name){
		this.name = name;
		events = new ArrayList<TLEvent>();
		setDirty(true);
	}
	/* (non-Javadoc)
	 * @see backend.TimelineAPI#addEvent(backend.TLEvent)
	 */
	@Override
	public void addEvent(TLEvent event) {
		events.add(event);
	}

	/* (non-Javadoc)
	 * @see backend.TimelineAPI#removeEvent(backend.TLEvent)
	 */
	@Override
	public boolean removeEvent(TLEvent event) {
		if(events.contains(event)){
			events.remove(event);
			return true;
		}else{
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see backend.TimelineAPI#changeEvent(backend.TLEvent, backend.TLEvent)
	 */
	@Override
	public boolean changeEvent(TLEvent oldEvent, TLEvent newEvent) {
		if(events.contains(oldEvent)){
			events.remove(oldEvent);
			events.add(newEvent);
			return true;
		}else{
			return false;
		}
	}
	/* (non-Javadoc)
	 * @see backend.TimelineAPI#getEvents()
	 */
	@Override
	public TLEvent[] getEvents() {
		return (TLEvent[])events.toArray();
	}
	/* (non-Javadoc)
	 * @see backend.TimelineAPI#isDirty()
	 */
	public boolean isDirty() {
		return dirty;
	}
	/* (non-Javadoc)
	 * @see backend.TimelineAPI#setDirty(boolean)
	 */
	public void setDirty(boolean dirty) {
		this.dirty = dirty;
	}
	/* (non-Javadoc)
	 * @see backend.TimelineAPI#getName()
	 */
	public String getName() {
		return name;
	}
}
