/**
 * 
 */
package database;

import entities.Timeline;

/**
 * @author Josh Wright
 * Created: Jan 29, 2014
 * Package: backend
 *
 * Note: try and have _id as parameters, it will make editing and adding stuff alot easier (add _id to Timeline and TLEvent).
 * Also, add/remove/updateEvent(TLEvent event, Timeline timeline) would be good
 *
 */
public interface DBHelperAPI {
	/**
	 * @param timeline
	 * @return
	 */
	public boolean writeTimeline(Timeline timeline);
	/**
	 * @param timeline
	 * @return
	 */
	public boolean removeTimeline(Timeline timeline);
	/**
	 * @param timeline
	 * @return
	 */
	public Timeline changeTimeline(Timeline oldTimeline, Timeline newTimeline);
	/**
	 * @return
	 */
	public Timeline[] getTimelines();
}
