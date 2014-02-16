package graphics;

import java.util.ArrayList;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import model.TimelineMaker;
import entities.Atomic;

public class AtomicLabel extends TLEventLabel {
	private Atomic event;
	private int xPos;
	private int yPos;
	private TimelineMaker model;
	private AtomicLabel label;
	private ArrayList<TLEventLabel> eventLabels;
	
	AtomicLabel(Atomic event, int xPos, int yPos, TimelineMaker model, ArrayList<TLEventLabel> eventLabels){
		super(event.getName());
		this.event = event;
		this.xPos = xPos;
		this.yPos = yPos;
		this.eventLabels = eventLabels;
		this.label = this;
		init();
	}
	
	private void init() {
		initDesign();
		initHandlers();
	}

	private void initDesign(){
		label.setLayoutX(xPos);
		label.setLayoutY(yPos);
		label.setStyle("-fx-border-color: green");
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
			label.setStyle("-fx-border-color: green");
		}
	}

}
