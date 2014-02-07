/**
 * 
 */
package graphics;

import java.awt.BorderLayout;
import java.awt.Canvas;

import javax.swing.JFrame;

import com.badlogic.gdx.backends.lwjgl.LwjglAWTCanvas;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Driver {
	static LwjglAWTCanvas canvas1;
	public static void main(String[] args) {

		JFrame frame = new JFrame("FrameDemo");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Canvas thing = new LwjglAWTCanvas(new TimelineRender(),false).getCanvas();
		
		
		frame.getContentPane().add(thing, BorderLayout.CENTER);

		frame.pack();
		
		frame.setSize(810, 480);;

		frame.setVisible(true);
	}
}