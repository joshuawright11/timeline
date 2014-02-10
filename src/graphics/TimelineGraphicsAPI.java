/**
 * 
 */
package graphics;

import javafx.embed.swing.JFXPanel;
import entities.Timeline;

/**
 * @author Josh Wright
 * Created: Feb 9, 2014
 * Package: graphics
 *
 */
public interface TimelineGraphicsAPI {
	public void renderTimeline(Timeline timeline);
	public void clearScreen();
	public void setPanel(JFXPanel fxPanel);
}
