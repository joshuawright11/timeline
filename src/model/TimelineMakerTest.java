package model;

import static org.junit.Assert.*;

import org.junit.Test;

import database.DBHelper;
import entities.*;

/**
 * TimelineMakerTest.java
 * 
 * This class tests the functionality of the TimelineMaker class.
 * 
 * @author Andrew Thompson
 * Wheaton College, CS 335, Spring 2014
 * Project Phase 1
 * Feb 15, 2014
 */
public class TimelineMakerTest {
	
	static TimelineMaker app;
	
	static {
		System.out.println("Hi");
		DBHelper db = new DBHelper("databases/timelinemakertest.db");
		while (db.getTimelines().length != 0)
			db.removeTimeline(db.getTimelines()[0]);
		app = new TimelineMaker(db);
	}
	
	@Test
	public void testTimelineMakerInit() {
		assertNotNull(app);
	}
	
	@Test
	public void testTimelineAdditionDeletion() {
		Timeline test = new Timeline("Test");
		assertFalse(app.getTimelineTitles().contains("Test"));
		app.addTimeline(test);
		assertTrue(app.getTimelineTitles().contains("Test"));
		app.deleteTimeline();
		assertFalse(app.getTimelineTitles().contains("Test"));
	}
}
