package gui;


import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

import javax.swing.*;

public class DisplayPane extends JScrollPane {

	/**
	 * Default serial version ID.
	 * I made this super messy, clean this up
	 */
	private static final long serialVersionUID = 1L;
	//Component timelineRenderer;
	
	public DisplayPane() {        
		final JFXPanel fxPanel = new JFXPanel();
		setViewportView(fxPanel);
		setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER); //should auto show if necessary
        Platform.runLater(new Runnable() {
            public void run() {
            	Group root = new Group();
                Scene scene = new Scene(root, 5000, 500, Color.BLUE);
                fxPanel.setScene(scene);
            }
        });
	}
	public final void paint(){
		
	}
	
}
