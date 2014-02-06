/**
 * 
 */
package model;

import gui.*;
import database.*;
import entities.*;

import javax.swing.*;

import java.sql.Date;
import java.util.*;
import java.util.logging.*;

/**
 * @author Josh Wright and Andrew Thompson
 *
 */
public class TimelineMaker implements Runnable {
	private ArrayList<Timeline> timelines;
	private Timeline currentTimeline;
	
	private TLOptionsDispatch timelineOptions;
	private TimelineGraphics timelineGraphics;
	private OptionsDispatch options;
	
	private EditWindow gui;
	
	private DBHelper database;
	
	

	public TimelineMaker() {
		database = new DBHelper("timeline.db");
		
		timelines = new ArrayList<Timeline>();
		currentTimeline = new Timeline("Test1");
		timelines.add(currentTimeline);
		
		// TODO Load timelines from database. Store in ArrayList<Timeline> timelines.
//		for (Timeline t : database.getTimelines())
//			timelines.add(t);
		
		initGUI();
	}
	
	private void initGUI() {
		// TODO Determine if this code should be on EDT.
		try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EditWindow.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(EditWindow.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(EditWindow.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(EditWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				gui = new EditWindow(TimelineMaker.this);
				gui.setVisible(true);
			}
		});
	}

	public void run() {
		
	}

	/**
	 * Retrieve the currently in-focus timeline.
	 * @return currentTimeline
	 */
	public Timeline currentTimeline() { return currentTimeline; }
	
	/**
	 * Retrieve a list of the names of all the timelines.
	 * @return
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
	public Timeline getTimeline(String name) { 
		for (Timeline t : timelines)
			if (t.getName().equals(name))
				return t;
		return null;
	}
	
	public void addTimeline(Timeline t) {
		timelines.add(t);
		// TODO Add database pushing code here.
	}
	
	public void removeTimeline(Timeline t) {
		timelines.remove(t);
		// TODO Add database pushing code here.
	}
	
}
