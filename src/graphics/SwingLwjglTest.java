/**
 * 
 */
package graphics;

import java.awt.Canvas;
import java.awt.Container;

import javax.swing.JFrame;

import com.badlogic.gdx.backends.lwjgl.LwjglAWTCanvas;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglGraphics;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;

/**
 * @author Josh Wright
 * Created: Feb 6, 2014
 * Package: graphics
 *
 */
public class SwingLwjglTest extends JFrame {
	public LwjglAWTCanvas canvas1;
	public SwingLwjglTest(){
		
		LwjglGraphics thing = new LwjglApplication(new TimelineRender()).getGraphics();
		//ScrollPane scroll = new ScrollPane();
	}
}
