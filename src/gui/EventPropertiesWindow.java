package gui;

import model.*;
import entities.*;

import javax.swing.*;
import java.awt.event.*;

/**
 * EventPropertiesWindow.java
 * @author Andrew Thompson
 */
public class EventPropertiesWindow extends JFrame {

	/**
	 * Default serial version ID.
	 */
	private static final long serialVersionUID = 1L;

	private TimelineMaker model;
	
	/**
	 * Window components.
	 */
	private JButton cancelButton;
	private JLabel commentLabel;
	private JScrollPane comments;
	private JTextField endDate;
	private JTextArea commentsArea;
	private JButton okButton;
	private JLabel periodLabel;
	private JTextField startDate;
	private JTextField title;
	private JLabel titleLabel;
	private JLabel toLabel;
	private JComboBox<String> type;
	private JLabel typeLabel;

	/**
	 * Creates new event properties window.
	 */
	public EventPropertiesWindow(TimelineMaker model) {
		initComponents();
		initLayout();
	}

	/**
	 * Initialize window components.
	 */
	private void initComponents() {

		titleLabel = new JLabel();
		title = new JTextField();
		typeLabel = new JLabel();
		type = new JComboBox<String>();
		periodLabel = new JLabel();
		startDate = new JTextField();
		toLabel = new JLabel();
		endDate = new JTextField();
		commentLabel = new JLabel();
		comments = new JScrollPane();
		commentsArea = new JTextArea();
		okButton = new JButton();
		cancelButton = new JButton();

		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Event Properties");

		titleLabel.setText("Title");

		title.setText("");

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

		periodLabel.setText("Time Period");

		startDate.setText("");

		toLabel.setText("to");

		endDate.setText("");

		commentLabel.setText("Comments");

		commentsArea.setColumns(20);
		commentsArea.setRows(5);
		comments.setViewportView(commentsArea);

		okButton.setText("Ok");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Determine whether to create a new event or modify an existing one. Load its data from the window.
				dispose();
			}
		});

		cancelButton.setText("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
	
	private void initLayout() {
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup()
										.addComponent(typeLabel)
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(type, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
												.addComponent(titleLabel)
												.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(title, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
												.addGroup(layout.createSequentialGroup()
														.addComponent(periodLabel)
														.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
														.addComponent(startDate, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
														.addComponent(toLabel)
														.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
														.addComponent(endDate, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
														.addGroup(layout.createSequentialGroup()
																.addComponent(commentLabel)
																.addGap(0, 0, Short.MAX_VALUE))
																.addComponent(comments)
																.addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
																		.addGap(0, 0, Short.MAX_VALUE)
																		.addComponent(okButton)
																		.addGap(18, 18, 18)
																		.addComponent(cancelButton)))
																		.addContainerGap())
				);

		layout.setVerticalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(titleLabel)
								.addComponent(title, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(type, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(typeLabel))
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
												.addComponent(endDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(periodLabel)
												.addComponent(toLabel)
												.addComponent(startDate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
												.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(commentLabel)
												.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(comments, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
														.addComponent(cancelButton)
														.addComponent(okButton))
														.addContainerGap())
				);

		pack();
	}
}