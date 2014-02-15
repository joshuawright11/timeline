package gui;


import graphics.TimelineGraphics;
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
        graphics.setPanel(fxPanel);
	}
}