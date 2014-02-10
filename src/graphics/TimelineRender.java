/**
 * 
 */
package graphics;

import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.control.Label;
import entities.Atomic;
import entities.Duration;
import entities.TLEvent;
import entities.Timeline;

/**
 * @author Josh Wright
 * Created: Feb 10, 2014
 * Package: graphics
 *
 */
public class TimelineRender implements Runnable {

	private Timeline timeline;
	private Group group;
	private ArrayList<Duration> durations;
	private ArrayList<Atomic> atomics;
	private long minYear;
	private long maxYear;
	
	/**
	 * @param timeline
	 * @param root
	 */
	public TimelineRender(Timeline timeline, Group group) {
		this.timeline = timeline;
		this.group = group;
		atomics = new ArrayList<Atomic>();
		durations = new ArrayList<Duration>();
		initArrays();
		initRange();
	}


	@Override
	public void run() {
		renderTimeline();
		renderAtomics();
		renderDurations();
	}

	private void initRange() {
	}
	
	private void initArrays(){
		for(TLEvent event : timeline.getEvents()){
			if(event instanceof Duration){
				durations.add((Duration)event);
				long start = ((Duration) event).getStartDate().getTime();
				long end = ((Duration) event).getEndDate().getTime();
			}else if(event instanceof Atomic){
				atomics.add((Atomic)event);
				long date = ((Atomic) event).getDate().getTime();
			}
		}
	}

	private void renderTimeline() {
		
	}
	
	private void renderAtomics() {
		int counter = 0;
		for(Atomic event : atomics){
			Label label = new Label(event.getName());
			label.setLayoutX(counter);
			label.setLayoutY(10);
			label.setOnMouseClicked(new TLEventClickEventHandler(label, event));
			group.getChildren().add(label);
			counter += 30;
		}
	}

	/**
	 * 
	 */
	private void renderDurations() {
		int counter = 0;
		for(Duration event : durations){
			Label label = new Label(event.getName());
			label.setLayoutX(counter);
			label.setLayoutY(50);
			label.setOnMouseClicked(new TLEventClickEventHandler(label, event));
			group.getChildren().add(label);
			counter += 30;
		}
	}

}
