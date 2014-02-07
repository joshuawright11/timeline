/**
 * 
 */
package graphics;

import java.awt.Canvas;
import java.awt.Container;

import javax.swing.JFrame;

import com.badlogic.gdx.backends.lwjgl.LwjglAWTCanvas;

/**
 * @author Josh Wright
 * Created: Feb 6, 2014
 * Package: graphics
 *
 */
public class SwingLwjglTest extends JFrame {
	public LwjglAWTCanvas canvas1;
	public SwingLwjglTest(){
		
		Canvas thing = new LwjglAWTCanvas(new TimelineRender(),false).getCanvas();
	}
}
