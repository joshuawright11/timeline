package gui;


import graphics.TimelineGraphics;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;

import javax.swing.*;

public class DisplayPane extends JScrollPane {
	
	/**
	 * Default serial version ID.
	 * I made this super messy, clean this up
	 */
	private static final long serialVersionUID = 1L;
	//Component timelineRenderer;
	
	private JFXPanel fxPanel;
	
	public DisplayPane(TimelineGraphics graphics) {       
		fxPanel = new JFXPanel();
		setViewportView(fxPanel);
		setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER); //should auto show if necessary
        graphics.setPanel(fxPanel);
		Platform.runLater(graphics);
	}
}