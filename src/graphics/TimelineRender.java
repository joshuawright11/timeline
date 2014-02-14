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
	private int firstUnit;
	private long firstDateMillis;
	private int pushDown;
	
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
		renderAtomics(); //render in order of height
		renderTime();
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
		
		Date firstDate = getFirstDate(minTime+(1000*60*60*6)); //the first unit on the timeline
		
		firstDateMillis = firstDate.getTime();
		
		//TODO month, day, etc.
		firstUnit = getYear(firstDate); // will round the year down, specific for years
		
		//just need to find the millis of the first year, ie the mintime of the whole timeline (lowest event rounded down by a unit)
		
		
		for(long i = (minTime + 1000*60*60*6); i <  (maxTime + unit); i += unit){ 
			//time zone and leap year. Also extra unit at start
			int unit = getYear(new Date(i));
			Label label = timeLabel(xPos, unit);
			group.getChildren().add(label);
			xPos+=unitWidth;
		}
		//TODO create scene here for right size
	}

	/**
	 * @param l
	 * @return
	 */
	private int getYear(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.YEAR);
	}


	/**
	 * @param xPos
	 * @param unit
	 * @return
	 */
	private Label timeLabel(int xPos, int unit) {
		final Label label = new Label(unit+"");
		label.setLayoutX(xPos);
		label.setLayoutY(pushDown);
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
	private Date getFirstDate(long i) { // *first date joke*
		//currently only works for years
		Date date = new Date(i);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Date toReturn = null;
		switch(unitName){
		case "month":
			//TODO do this
			break;
		case "years":
			int year = cal.get(Calendar.YEAR);
			cal.set(year, 0, 0);
			toReturn = new Date(cal.getTime().getTime());
			break;
		}
		return toReturn; //this is the first date on the timeline, rounded down to the unit
	}

	private void renderAtomics() {
		pushDown = 20; //where to put the event (Y)
		for(Atomic e : atomics){
			final Label label = new Label(e.getName());
			int xPos = getXPos(e.getDate());
			label.setLayoutX(xPos);
			label.setLayoutY(pushDown);
			final Atomic event = e;
			label.setOnMouseClicked(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent e) {
					System.out.println("You clicked "+ event.getName());
					label.setStyle("-fx-border-color: black");
					model.setSelectedEvent(event);
				}
			});
			group.getChildren().add(label);
			pushDown += 20;
		}
		
	}

	/**
	 * 
	 */
	private void renderDurations() {
		int counter = 0;
		for(Duration e : durations){
			final Label label = new Label(e.getName());
			int xStart = getXPos(e.getStartDate());
			int xEnd = getXPos(e.getEndDate());
			int labelWidth = xEnd - xStart;
			label.setStyle("-fx-border-color: blue;");
			label.setLayoutX(xStart);
			label.setPrefWidth(labelWidth);
			label.setAlignment(Pos.CENTER);
			label.setLayoutY(pushDown + 45 + counter);
			final Duration event = e;
			label.setOnMouseClicked(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent e) {
					System.out.println("You clicked "+ event.getName());
					label.setStyle("-fx-border-color: black;");
					model.setSelectedEvent(event);
				}
			});
			group.getChildren().add(label);
			counter += 20;
		}
	}


	/**
	 * @param startDate
	 * @return
	 */
	private int getXPos(Date date) {
		long millis = date.getTime();
		
		long distance = millis - firstDateMillis; //distance across the timeline the event is
		
		double inUnits = (((double)distance )/ unit);
		
		System.out.println("Event " + date.toString() + " is " +inUnits+ " units after the start. It has an x offset of " +(int)(inUnits*unitWidth)+ " pixels.");
		
		int xPos = (int)(inUnits * (double)unitWidth);
		
		
		return xPos;
	}

}
