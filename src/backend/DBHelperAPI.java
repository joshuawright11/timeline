/**
 * 
 */
package backend;

/**
 * @author Josh Wright
 * Created: Jan 29, 2014
 * Package: backend
 *
 */
public interface DBHelperAPI {
	/**
	 * @param timeline
	 * @return
	 */
	public Timeline writeTimeline(Timeline timeline);
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
