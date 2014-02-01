/**
 * 
 */
package model;

import entities.Timeline;

/**
 * @author Josh Wright
 * Created: Jan 29, 2014
 * Package: backend
 *
 */
public interface OptionsDispatchAPI {
	public boolean addTimeline(Timeline timeline);
	public boolean removeTimeline(Timeline timeline);
	public void selectTimeline(Timeline timeline);
	public Timeline[] loadTimelines();
}
