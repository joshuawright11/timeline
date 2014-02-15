package graphics;

import model.TimelineMaker;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import entities.Timeline;

public class TimelineGraphics{
	
	private JFXPanel fxPanel;
	private Group root;
	
	private TimelineMaker model;
	
	public TimelineGraphics(TimelineMaker model){
		this.model = model;
		root = new Group();
	}
	
	public void renderTimeline(Timeline timeline) {
		root = new Group();
		Platform.runLater(new TimelineRender(fxPanel, model, timeline, root));
	}

	public void clearScreen() {
		Platform.runLater(new Runnable() {
			public void run() {
				root.getChildren().clear();
			}
		});
	}

	/**
	 * @param fxPanel
	 */
	public void setPanel(JFXPanel fxPanel) {
		this.fxPanel = fxPanel;
	}
}
