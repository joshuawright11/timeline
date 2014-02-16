package graphics;

import javafx.scene.control.Label;

public abstract class TLEventLabel extends Label {
	
	private boolean selected;
	
	TLEventLabel(String text){
		super(text);
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
		updateDesign();
	}

	public abstract void updateDesign();
}
