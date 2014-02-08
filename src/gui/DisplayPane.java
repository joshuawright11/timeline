package gui;

import graphics.TimelineRender;

import java.awt.Graphics;

import javax.swing.*;

import com.badlogic.gdx.backends.lwjgl.LwjglAWTCanvas;

import java.awt.*;

public class DisplayPane extends ScrollPane {

	/**
	 * Default serial version ID.
	 */
	private static final long serialVersionUID = 1L;
	Canvas timelineRenderer;
	
	public DisplayPane() {
		timelineRenderer = new LwjglAWTCanvas(new TimelineRender(),false).getCanvas();
		timelineRenderer.setSize(1000, 480);
		add((timelineRenderer));
		//setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        //setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
	}
	public final void paint(){
	}
	
}
