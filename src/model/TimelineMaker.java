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
	private DBHelper database;
	private EditWindow gui;

	public TimelineMaker() {
		database = new DBHelper("timeline.db");
		
		timelines = new ArrayList<Timeline>();
		
		// Adding test timelines. TODO Remove.
		for (Timeline t : database.getTimelines())
			timelines.add(t);
		timelines.add(new Timeline("Test2"));
		
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
		// TODO Determine run behavior.
	}

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
		// TODO Add database saving code here.
	}
	
	public void removeTimeline(Timeline t) {
		timelines.remove(t);
		// TODO Add database saving code here.
	}
	
}
