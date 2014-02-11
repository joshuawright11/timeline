package gui;

import model.*;

import javax.swing.*;
import entities.*;

import java.awt.event.*;

/**
 * TimelinePropertiesWindow.java
 * @author Andrew Thompson
 */
public class TimelinePropertiesWindow extends JFrame {

	/**
	 * Default serial version ID.
	 */
	private static final long serialVersionUID = 1L;

	private TimelineMaker model;
	private MainWindow window;
	private Timeline timeline;

	/**
	 * Window components.
	 */
	private JLabel dataSectionLabel;

	private JLabel titleLabel;
	private JTextField title;

	private JLabel dateLabel;
	private JTextField startDate;
	private JLabel toLabel;
	private JTextField endDate;

	private JSeparator separator1;

	private JLabel appearanceSectionLabel;

	private JLabel colorLabel;
	private JButton colorChooserButton;

	private JLabel fontLabel;
	private JTextField font;

	private JLabel numLabelsLabel;
	private JSpinner numLabels;

	private JSeparator separator2;

	private JButton okButton;
	private JButton cancelButton;


	/**
	 * Creates new timeline properties window.
	 */
	public TimelinePropertiesWindow(TimelineMaker model, MainWindow window, Timeline timeline) {
		this.model = model;
		this.window = window;
		this.timeline = timeline;
		initComponents();
	}

	/**
	 * Initialize window components.
	 */
	private void initComponents() {

		dataSectionLabel = new JLabel();

		titleLabel = new JLabel();
		title = new JTextField();

		dateLabel = new JLabel();
		endDate = new JTextField();
		toLabel = new JLabel();
		startDate = new JTextField();

		separator1 = new JSeparator();

		appearanceSectionLabel = new JLabel();

		colorLabel = new JLabel();
		colorChooserButton = new JButton();

		numLabelsLabel = new JLabel();
		numLabels = new JSpinner();

		fontLabel = new JLabel();
		font = new JTextField();

		separator2 = new JSeparator();

		okButton = new JButton();
		cancelButton = new JButton();


		setTitle("Timeline Properties");

		dataSectionLabel.setFont(new java.awt.Font("Tahoma", 1, 12));
		dataSectionLabel.setText("Data:");

		titleLabel.setText("Title");

		dateLabel.setText("Time Period");

		endDate.setText("yyyy-mm-dd");

		toLabel.setText("to");

		startDate.setText("yyyy-mm-dd");

		appearanceSectionLabel.setFont(new java.awt.Font("Tahoma", 1, 12));
		appearanceSectionLabel.setText("Appearance:");

		colorLabel.setText("Color");

		colorChooserButton.setText("Choose color...");
		colorChooserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ColorChooser().setVisible(true);
			}
		});

		numLabelsLabel.setText("Number of Labels");

		fontLabel.setText("Font");

		font.setText("Tahoma");

		okButton.setText("Ok");
		if (timeline == null)
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					final String titleString = title.getText();
					// TODO Parse other timeline properties here.
					new Thread(new Runnable() {
						public void run() {
							model.addTimeline(new Timeline(titleString));
							SwingUtilities.invokeLater(new Runnable() {
								public void run() {
									window.loadTimelines();
								}
							});
						}
					}).start();
					dispose();
				}
			});
		else {
			title.setText(timeline.getName());        	
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					final String titleString = title.getText();
					// TODO Parse other timeline properties here.
					new Thread(new Runnable() {
						public void run() {
							model.removeTimeline(timeline);
							model.addTimeline(new Timeline(titleString));
							SwingUtilities.invokeLater(new Runnable() {
								public void run() {
									window.loadTimelines();
								}
							});
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

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(separator1)
								.addGroup(layout.createSequentialGroup()
										.addComponent(titleLabel)
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(title, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
										.addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
												.addComponent(dateLabel)
												.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 167, Short.MAX_VALUE)
												.addComponent(startDate, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(toLabel)
												.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(endDate, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE))
												.addGroup(layout.createSequentialGroup()
														.addComponent(colorLabel)
														.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(colorChooserButton))
														.addGroup(layout.createSequentialGroup()
																.addComponent(numLabelsLabel)
																.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																.addComponent(numLabels, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
																.addComponent(separator2)
																.addGroup(layout.createSequentialGroup()
																		.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
																				.addComponent(dataSectionLabel)
																				.addComponent(appearanceSectionLabel))
																				.addGap(0, 0, Short.MAX_VALUE))
																				.addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
																						.addGap(0, 0, Short.MAX_VALUE)
																						.addComponent(okButton)
																						.addGap(18, 18, 18)
																						.addComponent(cancelButton))
																						.addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
																								.addComponent(fontLabel)
																								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																								.addComponent(font, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
																								.addContainerGap())
				);
		layout.setVerticalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap()
						.addComponent(dataSectionLabel)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(titleLabel)
								.addComponent(title, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(endDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(toLabel)
										.addComponent(startDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(dateLabel))
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(separator1, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(appearanceSectionLabel)
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
												.addComponent(colorLabel)
												.addComponent(colorChooserButton))
												.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
												.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
														.addComponent(numLabelsLabel)
														.addComponent(numLabels, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
														.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
														.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
																.addComponent(fontLabel)
																.addComponent(font, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
																.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(separator2, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
																		.addComponent(cancelButton)
																		.addComponent(okButton))
																		.addContainerGap())
				);

		pack();
	}
}
