package gui;

import graphics.OneTriangle;

import java.awt.Dimension;

import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLJPanel;
import javax.swing.*;

public class DisplayPane extends JScrollPane {

	/**
	 * Default serial version ID.
	 */
	private static final long serialVersionUID = 1L;
	Component timelineRenderer;
	
	public DisplayPane() {
		timelineRenderer = new JPanel().add(new LwjglAWTCanvas(new TimelineRender(),false).getCanvas());
		timelineRenderer.setSize(800, 480);
		timelineRenderer.addComponentListener(new ComponentListener(){

			@Override
			public void componentResized(ComponentEvent e) {}

			@Override
			public void componentMoved(ComponentEvent e) {System.out.println("Moved.");}
			@Override
			public void componentShown(ComponentEvent e) {}

			@Override
			public void componentHidden(ComponentEvent e) {}
			
		});
		add((timelineRenderer));
		setSize(100,100);
		//timelineRenderer.setSize(1,1);
		setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        
	}
	public final void paint(){
		
	}
	
}
