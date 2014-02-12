/**
 * 
 */
package graphics;

import java.util.ArrayList;

import model.TimelineMaker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.chart.Axis;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
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

	private TimelineMaker model;
	private Timeline timeline;
	private Group group;
	private ArrayList<Duration> durations;
	private ArrayList<Atomic> atomics;
	
	//these are probably something that could be set in the actual timeline itself, but we can do that if there is time
	private long minTime;
	private long maxTime;
	
	/**
	 * @param timeline
	 * @param root
	 */
	public TimelineRender(TimelineMaker model, Timeline timeline, Group group) {
		this.model = model;
		this.timeline = timeline;
		this.group = group;
		atomics = new ArrayList<Atomic>();
		durations = new ArrayList<Duration>();
		init();
		initRange();
	}


	@Override
	public void run() {
		renderTimeline();
	}

	private void initRange() {
	}
	
	private void init(){
		int size = timeline.getEvents().length;
		
		if(size < 1) return; // Initializes the variables, super kludgy but we can make it better later if there is time
		if (timeline.getEvents()[0] instanceof Duration){
			minTime = ((Duration)timeline.getEvents()[0]).getStartDate().getTime();
			maxTime = ((Duration)timeline.getEvents()[0]).getEndDate().getTime();
		}else{
			minTime = ((Atomic)timeline.getEvents()[0]).getDate().getTime();
			maxTime = ((Atomic)timeline.getEvents()[0]).getDate().getTime();
		}
		
		for(TLEvent event : timeline.getEvents()){
			if(event instanceof Duration){
				durations.add((Duration)event);
				long start = ((Duration) event).getStartDate().getTime();
				long end = ((Duration) event).getEndDate().getTime();
				if(start < minTime){ minTime = start; }
				if(end > maxTime){ maxTime = end; }
			}else if(event instanceof Atomic){
				atomics.add((Atomic)event);
				long date = ((Atomic) event).getDate().getTime();
				if(date < minTime){ minTime = date; }
				if(date > maxTime){ maxTime = date; }
			}
		}
	}

	private void renderTimeline() {
		group.getChildren().clear();
		renderTime();
		renderAtomics();
		renderDurations();
	}
	
	private void renderTime() {
		// TODO if time, change timeline size depending on the total duration / number of events
		//min size is 1 year
		//months from 1-2 years
		//just years at more than 2
		long length = maxTime - minTime;
		int days = (int) (length / (1000*60*60*24)); //this is valid (I tested it once so it must work all the time, right?)
		System.out.println("Your timeline is this many days long: " + days);
		double years = days / 365.0;
		System.out.println("And this many years long: " + years);
		
		ListView<String> time = new ListView<String>();
		time.setOrientation(Orientation.HORIZONTAL);
		if(years > 2.0){ //No Months
			ArrayList<String> yearList = new ArrayList<String>();
			int firstYear =1970 + (int)(minTime / 1000 / 60 / 60 / 24 / 365); //gets the first year in the timeline
			for(int i = 0 ; i < ((int)years+1) ; i++ ){
				yearList.add(Integer.toString(firstYear + i));
			}
			time.setItems(FXCollections.observableArrayList(yearList));
		}else if(years <= 1.0){ //24 Months
			time.setItems(FXCollections.observableArrayList(
					"January", "February", "March", "April", "May", "June",
	                "July", "August", "September", "October", "November", "December",
	                "January", "February", "March", "April", "May", "June",
	                "July", "August", "September", "October", "November", "December"
			));
		}else{//12 Months
			time.setItems(FXCollections.observableArrayList(
					"January", "February", "March", "April", "May", "June",
	                "July", "August", "September", "October", "November", "December"
			));
		}
		time.setLayoutY(80);
		time.setPrefSize(2000, 100);
		group.getChildren().add(time);
	}


	private void renderAtomics() {
		int counter = 0;
		for(Atomic e : atomics){
			final Label label = new Label(e.getName());
			label.setLayoutX(counter);
			label.setLayoutY(10);
			final Atomic event = e;
			label.setOnMouseClicked(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent e) {
					System.out.println("You clicked "+ event.getName());
					label.setStyle("-fx-border-color: black;");
					model.setSelectedEvent(event);
				}
			});
			group.getChildren().add(label);
			counter += 30;
		}
	}

	/**
	 * 
	 */
	private void renderDurations() {
		int counter = 0;
		for(Duration e : durations){
			final Label label = new Label(e.getName());
			label.setLayoutX(counter);
			label.setLayoutY(50);
			final Duration event = e;
			label.setOnMouseClicked(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent e) {
					System.out.println("You clicked "+ event.getName());
					label.setStyle("-fx-border-color: black;");
					model.setSelectedEvent(event);
				}
			});
			group.getChildren().add(label);
			counter += 30;
		}
	}

}
