/**
 * 
 */
package model;

import gui.*;
import entities.*;

import javax.swing.*;
import java.util.*;
import java.util.logging.*;

/**
 * @author Josh Wright and Andrew Thompson
 *
 */
public class TimelineMaker implements Runnable {
	private ArrayList<Timeline> timelines;
	private Timeline currentTimeline;
	
	private TLOptionsDispatch timelineOptions;
	private TimelineGraphics timelineGraphics;
	private OptionsDispatch options;
	
	private EditWindow gui;
	
	

	public TimelineMaker() {
		initGUI();
	}
	
	private void initGUI() {
		// TODO Determine if this code should be on EDT.
		try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EditWindow.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(EditWindow.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(EditWindow.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(EditWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				gui = new EditWindow();
				gui.setVisible(true);
			}
		});
	}

	public void run() {
		
	}

	public void createEvent(){
		//timelineOptions.addEvent(event);
	}
}
