package gui;

import model.*;
import entities.*;
import graphics.TimelineRender;

import javax.swing.*;

import com.badlogic.gdx.backends.lwjgl.LwjglAWTCanvas;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 * EditWindow.java
 * @author Andrew Thompson
 */
public class EditWindow extends JFrame {

	/**
	 * Default serial version ID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * TimelineMaker model for this window.
	 */
	private TimelineMaker model;

	/**
	 * Window components.
	 */
	private JButton addEventButton;
	private JButton addTimelineButton;
	private JButton deleteEventButton;
	private JMenuItem deleteMenuItem;
	private JButton deleteTimelineButton;
	private JButton editEventButton;
	private JMenu editMenu;
	private JPopupMenu.Separator editMenuSeparator1;
	private JButton editTimelineButton;
	private JMenuItem editViewMenuItem;
	private JLabel eventsEditLabel;
	private JMenuItem exitMenuItem;
	private JMenu fileMenu;
	private JPopupMenu.Separator fileMenuSeparator1;
	private JPopupMenu.Separator fileMenuSeparator2;
	private JMenu insertMenu;
	private DisplayPane displayPane;
	private JSplitPane mainSplitPane;
	private JMenuBar menuBar;
	private JMenuItem multiViewMenuItem;
	private JMenuItem newEventMenuItem;
	private JMenuItem newTimelineMenuItem;
	private JMenuItem redoMenuItem;
	private JMenuItem saveTimelineMenuItem;
	private JList<String> timelines;
	private JLabel timelinesEditLabel;
	private JScrollPane timelinesPane;
	private JPanel toolbar;
	private JLabel toolbarLabel;
	private JSeparator toolbarSeparator1;
	private JSeparator toolbarSeparator2;
	private JMenuItem undoMenuItem;
	private JMenu viewMenu;

	/**
	 * Creates new edit window.
	 */
	public EditWindow(TimelineMaker model) {
		this.model = model;
		initComponents();
		initActionListeners();
		loadTimelines();
	}

	/**
	 * Initialize all window components.
	 */
	private void initComponents() {
		// Instantiate all components.
		mainSplitPane = new JSplitPane();
		toolbar = new JPanel();
		toolbarLabel = new JLabel();
		toolbarSeparator1 = new JSeparator();
		eventsEditLabel = new JLabel();
		addEventButton = new JButton();
		deleteEventButton = new JButton();
		editEventButton = new JButton();
		toolbarSeparator2 = new JSeparator();
		timelinesEditLabel = new JLabel();
		timelinesPane = new JScrollPane();
		timelines = new JList<String>();
		addTimelineButton = new JButton();
		deleteTimelineButton = new JButton();
		editTimelineButton = new JButton();
		displayPane = new DisplayPane();
		menuBar = new JMenuBar();
		fileMenu = new JMenu();
		newTimelineMenuItem = new JMenuItem();
		fileMenuSeparator1 = new JPopupMenu.Separator();
		saveTimelineMenuItem = new JMenuItem();
		fileMenuSeparator2 = new JPopupMenu.Separator();
		exitMenuItem = new JMenuItem();
		editMenu = new JMenu();
		undoMenuItem = new JMenuItem();
		redoMenuItem = new JMenuItem();
		editMenuSeparator1 = new JPopupMenu.Separator();
		deleteMenuItem = new JMenuItem();
		viewMenu = new JMenu();
		editViewMenuItem = new JMenuItem();
		multiViewMenuItem = new JMenuItem();
		insertMenu = new JMenu();
		newEventMenuItem = new JMenuItem();

		// Set default close operation and title of this window.
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Timelord - Create, edit, and view timelines!");
		
		// Set location of the main divider.
		mainSplitPane.setDividerLocation(140);

		
		// Set up the toolbar:
		toolbarLabel.setFont(new Font("Tahoma", 0, 12));
		toolbarLabel.setText("Toolbar");

		eventsEditLabel.setText("Events");
		addEventButton.setText("Add Event");
		deleteEventButton.setText("Delete Event");
		editEventButton.setText("Edit Event");

		timelinesEditLabel.setText("Timelines");
		timelinesPane.setViewportView(timelines);
		timelines.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		addTimelineButton.setText("Add Timeline");
		deleteTimelineButton.setText("Delete Timeline");
		editTimelineButton.setText("Edit Timeline");
		
		GroupLayout toolbarLayout = new GroupLayout(toolbar);
		toolbar.setLayout(toolbarLayout);
		toolbarLayout.setHorizontalGroup(
				toolbarLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(toolbarLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(toolbarSeparator1)
				.addComponent(eventsEditLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(addEventButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(deleteEventButton, GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
				.addComponent(editEventButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(toolbarSeparator2)
				.addComponent(timelinesEditLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addGroup(toolbarLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(timelinesPane, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
						.addContainerGap())
						.addComponent(editTimelineButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(addTimelineButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(deleteTimelineButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				);
		toolbarLayout.setVerticalGroup(
				toolbarLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(toolbarLayout.createSequentialGroup()
						.addComponent(toolbarLabel)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(toolbarSeparator1, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(eventsEditLabel)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(addEventButton)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(deleteEventButton)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(editEventButton)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(toolbarSeparator2, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(timelinesEditLabel)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(timelinesPane, GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(addTimelineButton)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(deleteTimelineButton)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(editTimelineButton))
				);

		mainSplitPane.setLeftComponent(toolbar);
		mainSplitPane.setRightComponent(displayPane);

		// Set up the menu bar:
		fileMenu.setText("File");
		newTimelineMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		newTimelineMenuItem.setText("New");
		fileMenu.add(newTimelineMenuItem);
		fileMenu.add(fileMenuSeparator1);
		saveTimelineMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		saveTimelineMenuItem.setText("Save");
		fileMenu.add(saveTimelineMenuItem);
		fileMenu.add(fileMenuSeparator2);
		exitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_MASK));
		exitMenuItem.setText("Exit");
		fileMenu.add(exitMenuItem);
		menuBar.add(fileMenu);

		editMenu.setText("Edit");
		undoMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_MASK));
		undoMenuItem.setText("Undo");
		editMenu.add(undoMenuItem);
		redoMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_MASK));
		redoMenuItem.setText("Redo");
		editMenu.add(redoMenuItem);
		editMenu.add(editMenuSeparator1);
		deleteMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0));
		deleteMenuItem.setText("Delete");
		editMenu.add(deleteMenuItem);
		menuBar.add(editMenu);

		viewMenu.setText("View");
		editViewMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K, InputEvent.CTRL_MASK));
		editViewMenuItem.setText("Edit View");
		viewMenu.add(editViewMenuItem);
		multiViewMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_MASK));
		multiViewMenuItem.setText("Multi View");
		viewMenu.add(multiViewMenuItem);
		menuBar.add(viewMenu);

		insertMenu.setText("Insert");
		newEventMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK));
		newEventMenuItem.setText("New Event");
		newEventMenuItem.addActionListener(null); // TODO
		insertMenu.add(newEventMenuItem);
		menuBar.add(insertMenu);

		setJMenuBar(menuBar);

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(mainSplitPane, GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
				);
		layout.setVerticalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(mainSplitPane)
				);

		pack();
	}
	
	/**
	 * Initialize action listeners for all interactive buttons and shortcuts.private void initActionListeners() {
	 */
	private void initActionListeners() {
		// Set up event toolbar listeners.
		addEventButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (EditWindow.this.getSelectedTimeline() != null)
					new EventPropertiesWindow(EditWindow.this.model, EditWindow.this.getSelectedTimeline(), null).setVisible(true);
			}
		});
		editEventButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (EditWindow.this.getSelectedTimeline() != null)
					new EventPropertiesWindow(EditWindow.this.model, EditWindow.this.getSelectedTimeline(), null).setVisible(true);
			}
		});
		deleteEventButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Implement deletion.
			}
		});
		
		// Set up timeline toolbar listeners.
		addTimelineButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TimelinePropertiesWindow(model, EditWindow.this, null).setVisible(true);
			}
		});
		editTimelineButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO threading
				new TimelinePropertiesWindow(model, EditWindow.this, model.getTimeline(timelines.getSelectedValue())).setVisible(true);
			}
		});
		deleteTimelineButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.removeTimeline(model.getTimeline(timelines.getSelectedValue()));
				EditWindow.this.loadTimelines();					
			}
		});

		// Set up menu item listeners.
		newTimelineMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TimelinePropertiesWindow(model, EditWindow.this, null).setVisible(true);
			}
		});
		saveTimelineMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Add action for the save button.
			}
		});
		exitMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		undoMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Add action for the undo button.
			}
		});
		redoMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Add action for the redo button.
			}
		});
	}

	/**
	 * Load timelines from TimelineMaker model into GUI window.
	 * Get a list of timeline titles from model. Then populate a default list model for the JList of timelines with those titles.
	 */
	public void loadTimelines() {
		final DefaultListModel<String> listModel = new DefaultListModel<String>();
		new Thread(new Runnable() {
			public void run() {
				ArrayList<String> temp = model.getTimelineTitles();
				for (String t : temp)
					listModel.addElement(t);
			}
		}).start();
		timelines.setModel(listModel);
	}
	
	/**
	 * Retrieve the timeline object that is currently selected.
	 * @return
	 */
	public Timeline getSelectedTimeline() {
		return model.getTimeline(timelines.getSelectedValue());
	}
}
