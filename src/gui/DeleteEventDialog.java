package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * DeleteEventDialog.java
 * @author Andrew Thompson
 */
public class DeleteEventDialog extends JDialog {

	/**
	 * Default serial version ID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Dialog components.
	 */
	private JButton cancelButton;
	private JLabel jLabel1;
	private JButton okButton;

	/**
	 * Creates new delete dialog.
	 */
	public DeleteEventDialog(Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	/**
	 * Initialize dialog components.
	 */
	private void initComponents() {

		jLabel1 = new JLabel();
		okButton = new JButton();
		cancelButton = new JButton();

		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		jLabel1.setFont(new Font("Tahoma", 0, 12));
		jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel1.setText("Are you sure you want to delete this event?");

		okButton.setText("Yes");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Determine and delete selected event on separate thread.
				dispose();
			}
		});

		cancelButton.setText("No, Cancel");
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
								.addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
						.addComponent(jLabel1)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(cancelButton)
								.addComponent(okButton))
								.addContainerGap())
				);

		pack();
	}
}
