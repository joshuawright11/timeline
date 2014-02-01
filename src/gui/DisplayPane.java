package gui;

import java.awt.Graphics;

import javax.swing.*;
import java.awt.*;

public class DisplayPane extends JScrollPane {

	/**
	 * Default serial version ID.
	 */
	private static final long serialVersionUID = 1L;
	
	public DisplayPane() {
		setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        setBackground(Color.white);
	}
	
	 public final void paint(Graphics g) {
	    	super.paint(g);
	    	g.fillRect(50, 50, 50, 50);
	 }
}
