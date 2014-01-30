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
	 * @return
	 */
	public Timeline[] getTimelines();
}
