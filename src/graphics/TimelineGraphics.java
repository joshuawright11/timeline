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
	private Group empty;
	private Scene emptyScene;
	
	private TimelineMaker model;
	
	public TimelineGraphics(TimelineMaker model){
		this.model = model;
	}
	
	@Override
	public void run() {
		empty = new Group();
		emptyScene = new Scene(empty, 2000, 500, Color.WHITE);	
		root = new Group();
        Scene scene = new Scene(root, 2000, 500, Color.WHITE);
        fxPanel.setScene(scene);
        
	}

	@Override
	public void renderTimeline(Timeline timeline) {
		Platform.runLater(new TimelineRender(model, timeline, root));
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
