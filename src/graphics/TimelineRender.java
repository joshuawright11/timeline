/**
 * 
 */
package graphics;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import model.TimelineMaker;
import javafx.embed.swing.JFXPanel;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import entities.Atomic;
import entities.Duration;
import entities.TLEvent;
import entities.Timeline;
import entities.Timeline.AxisLabel;

/**
 * @author Josh Wright
 * Created: Feb 10, 2014
 * Package: graphics
 *
 */
public class TimelineRender implements Runnable {

	private JFXPanel fxPanel;
	private TimelineMaker model;
	private Timeline timeline;
	private Group group;

	private ArrayList<Duration> durations;
	private ArrayList<Atomic> atomics;

	private AxisLabel axisLabel;
	private final String[] months = {"Jan", "Feb", "March", "April",
			"May","June","July","Aug",
			"Sept","Oct","Nov","Dec"};
	private int unitWidth;
	private long minTime;
	private long maxTime;
	private int pushDown;

	/**
	 * @param fxPanel 
	 * @param timeline
	 * @param root
	 */
	public TimelineRender(JFXPanel fxPanel, TimelineMaker model, Timeline timeline, Group group) {
		this.model = model;
		this.timeline = timeline;
		if (timeline.getAxisLabel() == AxisLabel.DAYS || timeline.getAxisLabel() == AxisLabel.MONTHS || timeline.getAxisLabel() == AxisLabel.YEARS)
			this.axisLabel = timeline.getAxisLabel();
		else
			this.axisLabel = AxisLabel.YEARS;
		this.group = group;
		this.fxPanel = fxPanel;
		atomics = new ArrayList<Atomic>();
		durations = new ArrayList<Duration>();
	}

	@Override
	public void run() {
		if (!initRange()){ //set a blank screen if there is nothing to render
			Scene toShow = new Scene(new Group(), 0, 0, Color.WHITE);
			fxPanel.setScene(toShow);
			return;
		}
		init();
		renderTimeline();
	}

	private void init(){
		unitWidth = 150;
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

	private boolean initRange() {
		if(timeline.getEvents() == null) { // Initializes the variables, super kludgy but we can make it better later if there is time
			return false; 
		}
		if (timeline.getEvents()[0] instanceof Duration){
			minTime = ((Duration)timeline.getEvents()[0]).getStartDate().getTime();
			maxTime = ((Duration)timeline.getEvents()[0]).getEndDate().getTime();
		}else{
			minTime = ((Atomic)timeline.getEvents()[0]).getDate().getTime();
			maxTime = ((Atomic)timeline.getEvents()[0]).getDate().getTime();
		}
		return true;
	}

	private void renderTimeline() {
		group.getChildren().clear();
		group = new Group();
		renderAtomics(); //render in order of height
		renderTime();
		renderDurations();
	}

	private void renderTime() {
		int diffUnit = getUnitLength();
		int xPos2 = 0;
		for(int i = 0; i < diffUnit ; i++){
			Label label = unitLabel(i,xPos2);
			group.getChildren().add(label);
			xPos2+=unitWidth;
		}
		Scene toShow = new Scene(group, xPos2+5, pushDown, Color.WHITE);
		fxPanel.setScene(toShow);
	}

	private Label unitLabel(int i, int xPos2) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(getFirstDate());
		cal.add(getUnit(), i); //adds this many days to the unit
		Label label;

		switch(axisLabel){
		case DAYS:
			label = new Label(new Date(cal.getTime().getTime()).toString());
			break;
		case MONTHS:
			label = new Label(months[cal.get(Calendar.MONTH)]+" "+ cal.get(Calendar.YEAR));
			break;
		case YEARS:
			label = new Label(cal.get(Calendar.YEAR)+"");
			break;
		default:
			label = new Label("");
			break;
		}
		label.setLayoutX(xPos2);
		label.setLayoutY(pushDown);
		label.setPrefWidth(unitWidth);
		label.setPrefHeight(40);
		label.setAlignment(Pos.CENTER);
		label.setStyle("-fx-border-color: black;");
		return label;
	}

	private int getUnitLength() {
		Calendar startCalendar = new GregorianCalendar();
		startCalendar.setTime(getFirstDate());
		Calendar endCalendar = new GregorianCalendar();
		endCalendar.setTime(new Date(maxTime));

		int diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
		int diffMonth = diffYear * 12 + endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
		int diffDay = diffYear * 365 +endCalendar.get(Calendar.DAY_OF_YEAR) - startCalendar.get(Calendar.DAY_OF_YEAR);

		switch(axisLabel){ //+1 to account for first
		case DAYS:
			return diffDay+1;
		case MONTHS:
			return diffMonth+1;
		case YEARS:
			return diffYear+1;
		default:
			return 0;
		}
	}

	/**
	 * @return
	 */
	private Date getFirstDate() {
		Date date = new Date(minTime);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Date toReturn = null;

		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_YEAR);

		switch(axisLabel){
		case DAYS:
			cal.set(year, month, day);
			toReturn = new Date(cal.getTime().getTime());
			break;
		case MONTHS:
			cal.set(year, month, 1);
			toReturn = new Date(cal.getTime().getTime());
			break;
		case YEARS:
			cal.set(year, 0, 1);
			toReturn = new Date(cal.getTime().getTime());
			break;
		default:
			break;
		}
		return toReturn; //this is the first date on the timeline, rounded down to the unit
	}

	private void renderAtomics() {
		pushDown = 60; //where to put the event ( y - axis )
		for(Atomic e : atomics){
			final Label label = new Label(e.getName());
			int xPosition = getXPos(e.getDate());
			label.setLayoutX(xPosition);
			label.setLayoutY(pushDown);
			final Atomic event = e;
			label.setStyle("-fx-border-color: blue");
			label.setOnMouseClicked(new EventHandler<MouseEvent>() {
				public void handle(MouseEvent e) {
					label.setStyle("-fx-border-color: black");
					new Thread(new Runnable() {
						public void run() {
							model.selectEvent(event);
						}
					}).start();
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
					label.setStyle("-fx-border-color: black");
					new Thread(new Runnable() {
						public void run() {
							model.selectEvent(event);
						}
					}).start();
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
		double units = getUnitsSinceStart(date);
		int xPosition = (int)(units*unitWidth); 
		//System.out.println("Event " + date.toString() + " is " +units+ " units after the start. It has an x offset of " +(int)(units*unitWidth)+ " pixels.");
		return xPosition;
	}

	private double getUnitsSinceStart(Date date){
		Calendar startCalendar = new GregorianCalendar();
		startCalendar.setTime(getFirstDate());
		Calendar endCalendar = new GregorianCalendar();
		endCalendar.setTime(date);

		double diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
		double diffMonth = diffYear * 12 + endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
		double diffDay = diffYear * 365 +endCalendar.get(Calendar.DAY_OF_YEAR) - startCalendar.get(Calendar.DAY_OF_YEAR);

		double years = diffYear + (endCalendar.get(Calendar.DAY_OF_YEAR) - startCalendar.get(Calendar.DAY_OF_YEAR))/365.0;
		double months = diffMonth + (endCalendar.get(Calendar.DAY_OF_MONTH) - startCalendar.get(Calendar.DAY_OF_MONTH))/30.5;
		double days = diffDay + 0.5; //so that dates lineup nicely

		switch(axisLabel){
		case DAYS:
			return days;
		case MONTHS:
			return months;
		case YEARS:
			return years;
		default:
			return 0;
		}
	}

	private int getUnit(){
		switch(axisLabel){
		case DAYS:
			return Calendar.DATE;
		case MONTHS:
			return Calendar.MONTH;
		case YEARS:
			return Calendar.YEAR;
		default:
			return 0;
		}
	}

}
