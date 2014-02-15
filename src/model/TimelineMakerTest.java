package model;

import static org.junit.Assert.*;

import org.junit.Test;

import entities.*;

public class TimelineMakerTest {

	@Test
	public void testTimelineSelectionInit() {
		TimelineMaker app = new TimelineMaker();
		assertNull("There should be no timeline selected: ", app.getSelectedTimeline());
	}
	
	@Test
	public void testTimelineAddition() {
		TimelineMaker app = new TimelineMaker();
		Timeline tester = new Timeline("Tester");
		
		assertNull("Selected timeline is null: ", app.getSelectedTimeline());
		assertFalse("Timeline does not contain tester: ", app.getTimelineTitles().contains(tester.getName()));
		
		app.addTimeline(tester);
		
		assertTrue("Timeline now contains tester: ", app.getTimelineTitles().contains(tester.getName()));
		assertTrue("Selected timeline is tester: ", app.getSelectedTimeline().equals(tester));
		
		cleanup();
	}
	
	@Test
	public void testTimelineDeletion() {
		TimelineMaker app = new TimelineMaker();
		Timeline tester = new Timeline("tester");
		
		assertTrue("Selected timeline is tester: ", app.getSelectedTimeline().equals(tester));
		assertTrue("Timeline contains tester: ", app.getTimelineTitles().contains(tester.getName()));
		
		app.deleteTimeline();
		
		assertFalse("Timeline no longer contains tester: ", app.getTimelineTitles().contains(tester.getName()));
		assertFalse("Selected timeline is not tester: ", app.getSelectedTimeline().equals(tester));
	}
	
	private void cleanup() {
		TimelineMaker app = new TimelineMaker();
		while (!app.getTimelineTitles().isEmpty())
			app.deleteTimeline();
	}
	
}
