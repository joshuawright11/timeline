package gui;

import model.*;
import entities.*;

import javax.swing.*;
import java.awt.*;
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

	/**
	 * Window components.
	 */
	private JLabel dataSectionLabel;

	private JLabel titleLabel;
	private JTextField title;

	private JSeparator separator1;

	private JLabel appearanceSectionLabel;

	private JLabel colorLabel;
	private JButton colorChooserButton;

	private JLabel fontLabel;
	private JTextField font;

	private JLabel axisLabelLabel;
	private JComboBox<String> axisLabel;

	private JSeparator separator2;

	private JButton okButton;
	private JButton cancelButton;


	public TimelinePropertiesWindow(final TimelineMaker model) {
		initComponents();

		// Define action for adding a timeline.
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final String titleString = title.getText();
				final int axisLabelIndex = axisLabel.getSelectedIndex();
				new Thread(new Runnable() {
					public void run() {
						model.addTimeline(new Timeline(titleString, axisLabelIndex));
					}
				}).start();
				dispose();
			}
		});

		initLayout();
	}



	/**
	 * Creates new timeline properties window.
	 */
	public TimelinePropertiesWindow(final TimelineMaker model, final Timeline timeline) {
		initComponents();
		
		// Load information from timeline into the dialog.
		new Thread(new Runnable() {
			public void run() {
				final String timelineTitle = timeline.getName();
				final int timelineAxisLabelIndex = timeline.getAxisLabelIndex();
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						title.setText(timelineTitle);
						axisLabel.setSelectedItem(axisLabel.getItemAt(timelineAxisLabelIndex));
					}
				});
			}
		}).start();

		// Define action for editing a timeline.
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final String titleString = title.getText();
				final int axisLabelIndex = axisLabel.getSelectedIndex();
				new Thread(new Runnable() {
					public void run() {
						TLEvent[] events = timeline.getEvents();
						model.editTimeline(new Timeline(titleString, events, axisLabelIndex));
					}
				}).start();
				dispose();
			}
		});
		
		initLayout();
	}

	/**
	 * Initialize window components.
	 */
	private void initComponents() {
		dataSectionLabel = new JLabel();

		titleLabel = new JLabel();
		title = new JTextField();

		separator1 = new JSeparator();

		appearanceSectionLabel = new JLabel();

		axisLabelLabel = new JLabel();
		axisLabel = new JComboBox<String>();

		colorLabel = new JLabel();
		colorChooserButton = new JButton();

		fontLabel = new JLabel();
		font = new JTextField();

		separator2 = new JSeparator();

		okButton = new JButton();
		cancelButton = new JButton();


		setTitle("Timeline Properties");

		dataSectionLabel.setFont(new Font("Tahoma", 1, 12));
		dataSectionLabel.setText("Data:");

		titleLabel.setText("Title");

		appearanceSectionLabel.setFont(new Font("Tahoma", 1, 12));
		appearanceSectionLabel.setText("Appearance:");

		colorLabel.setText("Color");

		colorChooserButton.setText("Choose color...");
		colorChooserButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ColorChooser().setVisible(true);
			}
		});

		axisLabelLabel.setText("Axis Label");
		axisLabel.setModel(new DefaultComboBoxModel<String>(new String[] { "Days", "Weeks", "Months", "Years", "Decades", "Centuries", "Millennia" }));
		axisLabel.setSelectedItem("Years");

		fontLabel.setText("Font");

		font.setText("Tahoma");

		okButton.setText("Ok");

		cancelButton.setText("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
	
	/**
	 * Initialize the layout of this window.
	 */
	private void initLayout() {
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(separator1)
								.addComponent(separator2)
								.addGroup(layout.createSequentialGroup()
										.addComponent(colorLabel)
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(colorChooserButton))
										.addGroup(layout.createSequentialGroup()
												.addComponent(axisLabelLabel)
												.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(axisLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addGroup(layout.createSequentialGroup()
														.addComponent(fontLabel)
														.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 208, Short.MAX_VALUE)
														.addComponent(font, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
														.addGroup(layout.createSequentialGroup()
																.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
																		.addComponent(dataSectionLabel)
																		.addComponent(appearanceSectionLabel))
																		.addGap(0, 0, Short.MAX_VALUE))
																		.addGroup(layout.createSequentialGroup()
																				.addComponent(titleLabel)
																				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
																				.addComponent(title, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
																				.addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
																						.addGap(0, 0, Short.MAX_VALUE)
																						.addComponent(okButton)
																						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
																						.addComponent(cancelButton)))
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
								.addComponent(separator1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(appearanceSectionLabel)
								.addGap(1, 1, 1)
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(colorLabel)
										.addComponent(colorChooserButton))
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
												.addComponent(axisLabelLabel)
												.addComponent(axisLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
												.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
														.addComponent(fontLabel)
														.addComponent(font, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
														.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
														.addComponent(separator2, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
														.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
																.addComponent(okButton)
																.addComponent(cancelButton))
																.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);

		pack();
	}
}
