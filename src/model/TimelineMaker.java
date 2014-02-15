package model;

import graphics.*;
import gui.*;
import database.*;
import entities.*;

import javax.swing.*;
import java.util.*;
import java.util.logging.*;

/**
 * TimelineMaker.java
 * The model of the timeline editor and viewer. Contains all necessary components to model the application.
 * @author Josh Wright and Andrew Thompson
 *
 */
public class TimelineMaker {
	/**
	 * A list of all the timelines in this application.
	 */
	private ArrayList<Timeline> timelines;
	/**
	 * The timeline selected in this application.
	 */
	private Timeline selectedTimeline;
	/**
	 * The event selected in this application.
	 */
	private TLEvent selectedEvent;
	/**
	 * The database for storing timelines of this application.
	 */
	private DBHelper database;
	/**
	 * The main GUI window for this application.
	 */
	private MainWindow gui;
	/**
	 * The graphics object for displaying timelines in this application.
	 */
	private TimelineGraphics graphics;

	/**
	 * Constructor.
	 * Create a new TimelineMaker application model with database, graphics, and GUI components.
	 */
	public TimelineMaker() {
		database = new DBHelper("timeline.db");
		graphics = new TimelineGraphics(this);
		timelines = new ArrayList<Timeline>();

		try {
			for (Timeline t : database.getTimelines())
				timelines.add(t);
			selectedTimeline = timelines.get(0);
			selectedEvent = selectedTimeline.getEvents()[0];
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Your database is empty.");
		} catch (Exception e){
			System.out.println("Error loading from Database.");
		}

		initGUI();
	}

	/**
	 * Initialize the GUI components of this application.
	 */
	private void initGUI() {
		try {
			for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
		} catch (UnsupportedLookAndFeelException ex) {
			Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
		}

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				gui = new MainWindow(TimelineMaker.this, graphics);
				gui.setVisible(true);
				new Thread(new Runnable() {
					public void run() {
						gui.updateTimelines(getTimelineTitles(), null);
					}
				}).start();
			}
		});

	}

	/**
	 * Retrieve a list of the names of all the timelines.
	 * @return timelines
	 */
	public ArrayList<String> getTimelineTitles() {
		ArrayList<String> toReturn = new ArrayList<String>();
		for (Timeline t: timelines)
			toReturn.add(t.getName());
		return toReturn;
	}

	/**
	 * Retrieve the timeline with the parameterized name.
	 * @param title The name of the timeline to be found
	 * @return The timeline with the correct name; null otherwise.
	 */
	private Timeline getTimeline(String title) { 
		for (Timeline t : timelines)
			if (t.getName().equals(title))
				return t;
		return null;
	}

	/**
	 * Retrieve the currently selected timeline.
	 * @return selectedTimeline
	 */
	public Timeline getSelectedTimeline() {
		return selectedTimeline;
	}

	/**
	 * Set the selected timeline.
	 * Find the timeline of the parameterized title and set selectedTimeline to it.
	 * @param title of the timeline
	 */
	public void setSelectedTimeline(String title) {
		selectedTimeline = getTimeline(title);
		if (selectedTimeline != null)
			updateGraphics();
	}

	/**
	 * Add a timeline to this model.
	 * @param t the timeline to be added
	 */
	public void addTimeline(Timeline t) {
		selectedTimeline = t;
		timelines.add(selectedTimeline);

		database.writeTimeline(selectedTimeline);
		gui.updateTimelines(getTimelineTitles(), selectedTimeline.getName());
		updateGraphics();
	}

	/**
	 * Remove a timeline from this model.
	 * @param t the timeline to be removed
	 */
	public void deleteTimeline() {
		if (selectedTimeline != null) {
			timelines.remove(selectedTimeline);
			database.removeTimeline(selectedTimeline);
			selectedTimeline = null;
			gui.updateTimelines(getTimelineTitles(), null);
			graphics.clearScreen();
		}
	}

	/**
	 * Edit the selected timeline.
	 * Remove the selected timeline and replace it with the parameterized one.
	 * @param t
	 */
	public void editTimeline(Timeline t) {
		timelines.remove(selectedTimeline);
		database.removeTimeline(selectedTimeline);

		boolean newName = !selectedTimeline.getName().equals(t.getName());
		selectedTimeline = t;
		timelines.add(selectedTimeline);
		database.writeTimeline(selectedTimeline);
		if (newName)
			gui.updateTimelines(getTimelineTitles(), selectedTimeline.getName());
		updateGraphics();
	}

	/**
	 * Retrieve the currently selected event.
	 * @return selectedEvent
	 */
	public TLEvent getSelectedEvent() { 
		return selectedEvent; 
	}

	/**
	 * Set the selected event.
	 * @param e The event to be selected
	 */
	public void setSelectedEvent(TLEvent e) {
		if (e != null) {
			selectedEvent = e;
			//System.out.println("Model confirms event selection:\n" +
			//		"\tYou selected event: " + selectedEvent.getName() + " in the timeline: " + selectedTimeline.getName());
		}
	}

	public void addEvent(TLEvent e) {
		selectedTimeline.addEvent(e);
		selectedEvent = e;

		updateGraphics();

		database.removeTimeline(selectedTimeline);
		database.writeTimeline(selectedTimeline);
	}

	public void deleteEvent() {
		if (selectedTimeline.contains(selectedEvent))
			selectedTimeline.removeEvent(selectedEvent);
		selectedEvent = null;

		updateGraphics();

		database.removeTimeline(selectedTimeline);
		database.writeTimeline(selectedTimeline);
	}

	public void editEvent(TLEvent e) {
		selectedTimeline.removeEvent(selectedEvent);
		selectedEvent = e;
		selectedTimeline.addEvent(selectedEvent);

		updateGraphics();

		database.removeTimeline(selectedTimeline);
		database.writeTimeline(selectedTimeline);
	}

	/**
	 * Update the graphics for the display screen.
	 */
	public void updateGraphics() { 
		if(selectedTimeline.isDirty()){
			//TODO sync to database. Easier to do it here, although this would mean the timeline would have to be swapped out every time
		}
		graphics.clearScreen();
		graphics.renderTimeline(selectedTimeline);
	}

}
