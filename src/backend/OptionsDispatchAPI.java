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
public interface OptionsDispatchAPI {
	/**
	 * @return
	 */
	public boolean addTimeline(Timeline timeline);
	/**
	 * @return
	 */
	public boolean removeTimeline(Timeline timeline);
	/**
	 * 
	 */
	public void selectTimeline(Timeline timeline);
}
