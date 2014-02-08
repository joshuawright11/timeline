/**
 * 
 */
package model;

import database.DBHelper;

import entities.Timeline;

/**
 * @author Josh Wright
 * Created: Jan 29, 2014
 * Package: backend
 *
 */

public class OptionsDispatch implements OptionsDispatchAPI {
	public static final String DB = "timeline.db";
	DBHelper helper;
	OptionsDispatch(){
		helper = new DBHelper(DB);
	}
	/* (non-Javadoc)
	 * @see backend.OptionsDispatchAPI#addTimeline()
	 */
	@Override
	public boolean addTimeline(Timeline timeline) {return helper.writeTimeline(timeline);}

	/* (non-Javadoc)
	 * @see backend.OptionsDispatchAPI#removeTimeline()
	 */
	@Override
	public boolean removeTimeline(Timeline timeline) {return helper.removeTimeline(timeline);}

	/* (non-Javadoc)
	 * @see backend.OptionsDispatchAPI#selectTimeline()
	 */
	@Override
	public void selectTimeline(Timeline timeline) {
		//TODO test to see if timeline is in database
		//if it is, 'select' it and display it on the graphics window.
	}
	@Override
	public Timeline[] loadTimelines() {	return helper.getTimelines();}
	
}
