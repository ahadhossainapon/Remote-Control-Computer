package com.remote.control.computer;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

public class MessageNotification extends JFrame {

	public MessageNotification(String message) {
		super("Test translucent window");
		// String message =
		// got a new notification message. 
		String header = "New Notification";
		final JFrame frame = new JFrame();
		frame.setSize(300, 125);
		frame.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.weightx = 1.0f;
		constraints.weighty = 1.0f;
		constraints.insets = new Insets(5, 5, 5, 5);
		constraints.fill = GridBagConstraints.BOTH;
		JLabel headingLabel = new JLabel(header);
		// headingLabel .setIcon(headingIcon); // --- use image icon you want to
		// be as heading image.
		headingLabel.setOpaque(false);
		frame.add(headingLabel, constraints);
		constraints.gridx++;
		constraints.weightx = 0f;
		constraints.weighty = 0f;
		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.NORTH;
		JButton cloesButton = new JButton(new AbstractAction("x") {
			@Override
			public void actionPerformed(final ActionEvent e) {
				frame.dispose();
			}
		});
		cloesButton.setMargin(new Insets(1, 4, 1, 4));
		cloesButton.setFocusable(false);
		frame.add(cloesButton, constraints);
		constraints.gridx = 0;
		constraints.gridy++;
		constraints.weightx = 1.0f;
		constraints.weighty = 1.0f;
		constraints.insets = new Insets(5, 5, 5, 5);
		constraints.fill = GridBagConstraints.BOTH;
		JLabel messageLabel = new JLabel("<HtMl>" + message);

		frame.add(messageLabel, constraints);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();// size
																		// of
																		// the
																		// screen
		Insets toolHeight = Toolkit.getDefaultToolkit().getScreenInsets(
				frame.getGraphicsConfiguration());// height of the task bar
		frame.setLocation(scrSize.width - frame.getWidth(), scrSize.height
				- toolHeight.bottom - frame.getHeight());
		frame.setVisible(true);
		frame.setAlwaysOnTop(true);
	}

}
