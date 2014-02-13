/**
 * 
 */
package graphics;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

import model.TimelineMaker;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
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
	private String unitName = "years";
	private long unit;
	private final String[] months = {"January", "February", "March", "April",
			"May","June","July","August",
			"September","October","November","December"};
	private int unitWidth;
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
	}


	@Override
	public void run() {
		renderTimeline();
	}

	private void initRange() {
		int size = timeline.getEvents().length;
		if(size < 1) return; // Initializes the variables, super kludgy but we can make it better later if there is time
		if (timeline.getEvents()[0] instanceof Duration){
			minTime = ((Duration)timeline.getEvents()[0]).getStartDate().getTime();
			maxTime = ((Duration)timeline.getEvents()[0]).getEndDate().getTime();
		}else{
			minTime = ((Atomic)timeline.getEvents()[0]).getDate().getTime();
			maxTime = ((Atomic)timeline.getEvents()[0]).getDate().getTime();
		}
	}
	
	private void init(){
		initRange();
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
	
	private void info(){ //prints info, debugging
		long length = maxTime - minTime;
		int days = (int) (length / (1000*60*60*24)); //this is valid (I tested it once so it must work all the time, right?)
		System.out.println("Your timeline is this many days long: " + days);
		double years = days / 365.0;
		System.out.println("And this many years long: " + years);
	}
	
	private void renderTime() {
		switch(unitName){
		case "months":
			unit = ((long) 1000)*60*60*24*30;
			//TODO ugh
			break;
		case "years":
			unit = ((long) 1000)*60*60*24*365;
			break;
		}
		
		unitWidth = 100; //TODO figure this out
		
		int xPos = 0;
		for(long i = (minTime + 1000*60*60*6); i <  (maxTime + unit); i += unit){ 
			//extra unit on the ends... also leap year issues :(
			//time zone and leap year. Also extra unit at start
			int unit = getUnit(i);
			Label label = timeLabel(xPos, unit);
			group.getChildren().add(label);
			xPos+=unitWidth;
		}
		//TODO create scene here for right size
	}

	/**
	 * @param xPos
	 * @param unit
	 * @return
	 */
	private Label timeLabel(int xPos, int unit) {
		final Label label = new Label(unit+"");
		label.setLayoutX(xPos);
		label.setLayoutY(250);
		label.setPrefWidth(unitWidth);
		label.setPrefHeight(40);
		label.setAlignment(Pos.CENTER);
		label.setStyle("-fx-border-color: black;");
		return label;
	}

	/**
	 * @param i
	 * @return
	 */
	private int getUnit(long i) {
		//currently only works for years
		Date date = new Date(i);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);
		return year;
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
					label.setStyle("-fx-border-color: black");
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
