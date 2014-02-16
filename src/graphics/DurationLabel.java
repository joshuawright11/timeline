/**
 * 
 */
package graphics;

import java.util.ArrayList;

import model.TimelineMaker;
import entities.Duration;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

/**
 * @author josh
 *
 */
public class DurationLabel extends TLEventLabel {
	private Duration event;
	private int xPos;
	private int yPos;
	private TimelineMaker model;
	private DurationLabel label;
	private ArrayList<TLEventLabel> eventLabels;
	private int width;
	
	DurationLabel(Duration event, int xPos, int yPos, int width, TimelineMaker model, ArrayList<TLEventLabel> eventLabels){
		super(event.getName());
		this.event = event;
		this.xPos = xPos;
		this.yPos = yPos;
		this.eventLabels = eventLabels;
		this.label = this;
		this.width = width;
		init();
	}
	
	private void init() {
		initDesign();
		initHandlers();
	}

	private void initDesign(){
		label.setPrefWidth(width);
		label.setAlignment(Pos.CENTER);
		label.setLayoutX(xPos);
		label.setLayoutY(yPos);
		label.setStyle("-fx-border-color: blue");
	}
	
	private void initHandlers(){
		label.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				for(TLEventLabel label : eventLabels){
					label.setSelected(false);
				}
				setSelected(true);
				new Thread(new Runnable() {
					public void run() {
						model.selectEvent(event);
					}
				}).start();
			}
		});
	}

	@Override
	public void updateDesign() {
		if (isSelected()) {
			label.setStyle("-fx-border-color: black");
		}else{	
			label.setStyle("-fx-border-color: blue");
		}
	}
}
