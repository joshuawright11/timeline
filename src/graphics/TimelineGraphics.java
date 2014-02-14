package graphics;

import model.TimelineMaker;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import entities.Timeline;

public class TimelineGraphics implements Runnable, TimelineGraphicsAPI {
	
	private JFXPanel fxPanel;
	private Group root;
	private Scene scene;
	
	private TimelineMaker model;
	
	public TimelineGraphics(TimelineMaker model){
		this.model = model;
		root = new Group();
	}
	
	@Override
	public void run() {
		System.out.println("Well this was pointless...");
	}

	@Override
	public void renderTimeline(Timeline timeline) {
		root = new Group();
		Platform.runLater(new TimelineRender(fxPanel, model, timeline, root));
	}

	@Override
	public void clearScreen() {
//		fxPanel.setScene(emptyScene);
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
