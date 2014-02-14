package gui;

import model.*;
import entities.*;

import javax.swing.*;
import java.awt.event.*;
import java.sql.Date;

/**
 * EventPropertiesWindow.java
 * @author Andrew Thompson
 */
public class EventPropertiesWindow extends JFrame {

	/**
	 * Default serial version ID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The model of this application.
	 */
	private TimelineMaker model;
	/**
	 * The timeline container for the generated or edited event.
	 */
	private Timeline timeline;
	/**
	 * The event to be edited; null if new event addition.
	 */
	private TLEvent event;


	/**
	 * Window components.
	 */
	private JLabel titleLabel;
	private JTextField title;

	private JLabel typeLabel;
	private JComboBox<String> type;

	private JLabel dateLabel;
	private JTextField startDate;
	private JLabel toLabel;
	private JTextField endDate;

	private JLabel categoryLabel;
	private JTextField category;

	private JLabel commentLabel;
	private JScrollPane comments;
	private JTextArea commentsArea;

	private JButton okButton;
	private JButton cancelButton;

	
	/**
	 * Creates new event properties window.
	 */
	public EventPropertiesWindow(TimelineMaker model, Timeline timeline, TLEvent event) {
		this.model = model;
		this.timeline = timeline;
		this.event = event;

		initComponents();
		initLayout();

		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Event Properties");
	}

	/**
	 * Initialize window components.
	 */
	private void initComponents() {

		titleLabel = new JLabel();
		title = new JTextField();

		typeLabel = new JLabel();
		type = new JComboBox<String>();

		dateLabel = new JLabel();
		startDate = new JTextField(10);
		toLabel = new JLabel();
		endDate = new JTextField(10);

		categoryLabel = new JLabel();
		category = new JTextField();

		commentLabel = new JLabel();
		comments = new JScrollPane();
		commentsArea = new JTextArea();

		okButton = new JButton();
		cancelButton = new JButton();


		titleLabel.setText("Title");

		typeLabel.setText("Type");
		type.setModel(new DefaultComboBoxModel<String>(new String[] { "Duration", "Atomic" }));
		type.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (type.getSelectedItem().equals("Atomic")) {
					endDate.setVisible(false);
					toLabel.setVisible(false);
				} else {
					endDate.setVisible(true);
					toLabel.setVisible(true);
				}
			}
		});

		dateLabel.setText("Date");
		startDate.setText("yyyy-mm-dd");
		toLabel.setText("to");
		endDate.setText("yyyy-mm-dd");

		categoryLabel.setText("Category");

		commentLabel.setText("Comments");
		commentsArea.setColumns(20);
		commentsArea.setRows(5);
		comments.setViewportView(commentsArea);

		okButton.setText("Ok");
		if (event == null)
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					final String title = EventPropertiesWindow.this.title.getText();
					final String type = EventPropertiesWindow.this.type.getSelectedItem().toString();
					final String startDate = EventPropertiesWindow.this.startDate.getText();
					final String endDate = EventPropertiesWindow.this.endDate.getText();
					final String category = EventPropertiesWindow.this.category.getText();
					new Thread(new Runnable() {
						public void run() {
							if (type.equals("Atomic")) {
								timeline.addEvent(new Atomic(title, category, Date.valueOf(startDate)));
							}
							else {
								timeline.addEvent(new Duration(title, category, Date.valueOf(startDate), Date.valueOf(endDate)));
							}
							model.updateGraphics();
						}
					}).start();
					dispose();
				}
			});
		else {
			new Thread(new Runnable() {
				public void run() {
					final String eventName = event.getName();
					if (event instanceof Atomic) {
						final String date = ((Atomic)event).getDate().toString();
						SwingUtilities.invokeLater(new Runnable() {
							public void run() {
								title.setText(eventName);
								type.setSelectedItem("Atomic");
								startDate.setText(date);
							}
						});
					} else if (event instanceof Duration) {
						final String startDateString = ((Duration)event).getStartDate().toString();
						final String endDateString = ((Duration)event).getEndDate().toString();
						SwingUtilities.invokeLater(new Runnable() {
							public void run() {
								title.setText(eventName);
								type.setSelectedItem("Duration");
								startDate.setText(startDateString);
								endDate.setText(endDateString);
							}
						});
					}
				}
			}).start();

			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					final String title = EventPropertiesWindow.this.title.getText();
					final String type = EventPropertiesWindow.this.type.getSelectedItem().toString();
					final String startDate = EventPropertiesWindow.this.startDate.getText();
					final String endDate = EventPropertiesWindow.this.endDate.getText();
					final String category = EventPropertiesWindow.this.category.getText();
					new Thread(new Runnable() {
						public void run() {
							timeline.removeEvent(event);
							if (type.equals("Atomic")) {
								timeline.addEvent(new Atomic(title, category, Date.valueOf(startDate)));
							}
							else {
								timeline.addEvent(new Duration(title, category, Date.valueOf(startDate), Date.valueOf(endDate)));
							}
							model.updateGraphics();
						}
					}).start();
					dispose();
				}
			});
		}

		cancelButton.setText("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}

	/**
	 * Initialize the layout of the window.
	 * Note: Generated code.
	 */
	private void initLayout() {
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup()
										.addComponent(typeLabel)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGroup(layout.createSequentialGroup()
												.addComponent(dateLabel)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(startDate, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(toLabel)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(endDate, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
												.addComponent(comments)
												.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
														.addGap(0, 0, Short.MAX_VALUE)
														.addComponent(okButton)
														.addGap(18, 18, 18)
														.addComponent(cancelButton))
														.addGroup(layout.createSequentialGroup()
																.addComponent(categoryLabel)
																.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																.addComponent(category, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
																.addGroup(layout.createSequentialGroup()
																		.addComponent(commentLabel)
																		.addGap(0, 0, Short.MAX_VALUE))
																		.addGroup(layout.createSequentialGroup()
																				.addComponent(titleLabel)
																				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																				.addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
																				.addContainerGap())
				);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(titleLabel)
								.addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(typeLabel))
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(endDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(dateLabel)
												.addComponent(toLabel)
												.addComponent(startDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(categoryLabel)
														.addComponent(category, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
														.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(commentLabel)
														.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
														.addComponent(comments, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
														.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
																.addComponent(cancelButton)
																.addComponent(okButton))
																.addContainerGap())
				);
		pack();
	}
}