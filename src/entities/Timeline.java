/**
 * 
 */
package entities;

import java.util.ArrayList;
import java.util.Arrays;

import entities.Timeline.AxisLabel;

/**
 * @author Josh Wright
 * Created: Jan 29, 2014
 * Package: backend
 *
 */
public class Timeline implements TimelineAPI{
	private ArrayList<TLEvent> events;
	private String name;
	public static enum AxisLabel {
		DAYS, WEEKS, MONTHS, YEARS, DECADES, CENTURIES, MILLENNIA;
	}
	private static final AxisLabel[] AXIS_LABELS = { AxisLabel.DAYS, AxisLabel.WEEKS, AxisLabel.MONTHS, AxisLabel.YEARS, AxisLabel.DECADES, AxisLabel.CENTURIES, AxisLabel.MILLENNIA};
	private AxisLabel axisLabel;
	private boolean dirty;

	public Timeline(String name){
		this.name = name;
		events = new ArrayList<TLEvent>();
		axisLabel = AxisLabel.YEARS;
		setDirty(true);
	}
	public Timeline(String name, TLEvent[] events){
		this.name = name;
		this.events = new ArrayList<TLEvent>(Arrays.asList(events));
		axisLabel = AxisLabel.YEARS;
		setDirty(true);
	}
	public Timeline(String name, int axisLabel) {
		this.name = name;
		this.axisLabel = AXIS_LABELS[axisLabel];
		this.events = new ArrayList<TLEvent>();
		dirty = true;
	}
	public Timeline(String name, TLEvent[] events, int axisLabel) {
		this.name = name;
		if(events != null)
			this.events = new ArrayList<TLEvent>(Arrays.asList(events));
		else
			this.events = new ArrayList<TLEvent>();
		this.axisLabel = AXIS_LABELS[axisLabel];
		dirty = true;
	}

	public boolean contains(TLEvent event) {
		for (TLEvent e : events)
			if (e.equals(event))
				return true;
		return false;
	}

	/* (non-Javadoc)
	 * @see backend.TimelineAPI#addEvent(backend.TLEvent)
	 */
	@Override
	public void addEvent(TLEvent event) {
		setDirty(true);
		events.add(event);
	}

	/* (non-Javadoc)
	 * @see backend.TimelineAPI#removeEvent(backend.TLEvent)
	 */
	@Override
	public boolean removeEvent(TLEvent event) {
		if(events.contains(event)){
			events.remove(event);
			setDirty(true);
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
			setDirty(true);
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
		if(events.isEmpty()) return null;
		return (TLEvent[])events.toArray(new TLEvent[events.size()]);
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

	public int getAxisLabelIndex() { 
		for (int i = 0; i < AXIS_LABELS.length; i++)
			if (AXIS_LABELS[i] == axisLabel)
				return i;
		return -1;
	}
	public AxisLabel getAxisLabel() {
		return axisLabel;
	}
}
