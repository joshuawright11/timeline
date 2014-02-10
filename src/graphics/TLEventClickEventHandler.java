/**
 * 
 */
package graphics;

import entities.TLEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

/**
 * @author Josh Wright
 * Created: Feb 9, 2014
 * Package: graphics
 *
 */
public class TLEventClickEventHandler implements EventHandler<MouseEvent> {

	private Label label;
	private TLEvent event;
	
	TLEventClickEventHandler(Label label, TLEvent event){
		this.label = label;
		this.event = event;
	}
	
	@Override
	public void handle(MouseEvent arg0) {
		System.out.println("You clicked "+event.getName());
		label.setStyle("-fx-border-color: black;");
	}

}
