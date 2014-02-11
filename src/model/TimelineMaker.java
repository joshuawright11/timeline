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
	private ArrayList<Timeline> timelines;
	private Timeline selectedTimeline;
	private DBHelper database;
	private MainWindow gui;
	private TimelineGraphics graphics;

	/**
	 * Constructor.
	 * Create a new TimelineMaker application model with database, graphics, and gui compnents.
	 */
	public TimelineMaker() {
		database = new DBHelper("timeline.db");
		graphics = new TimelineGraphics();
		timelines = new ArrayList<Timeline>();
		
		
		// Adding test timelines. TODO Remove.
		for (Timeline t : database.getTimelines())
			timelines.add(t);
		
		selectedTimeline = timelines.get(0);
		
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
	 * @param name The name of the timeline to be found
	 * @return The timeline with the correct name; null otherwise.
	 */
	private Timeline getTimeline(String name) { 
		for (Timeline t : timelines)
			if (t.getName().equals(name))
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
		// TODO Add rendering code here!
		graphics.renderTimeline(selectedTimeline);
	}
	
	/**
	 * Add a timeline to this model.
	 * @param t the timeline to be added
	 */
	public void addTimeline(Timeline t) {
		timelines.add(t);
		// TODO Add database saving code here.
	}
	
	/**
	 * Remove a timeline from this model.
	 * @param t the timeline to be removed
	 */
	public void removeTimeline(Timeline t) {
		timelines.remove(t);
		// TODO Add database saving code here.
	}
	
}
