package com.remote.control.computer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

 class Notification extends JFrame {

	private JPanel contentPane;
	public static String longMessage = "";

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 * @param msg 
	 */
	public Notification(String msg) {
		super("New Message");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		final JFrame frame =  new JFrame();
		final JTextArea textArea = new JTextArea();
		contentPane.add(textArea, BorderLayout.CENTER);
		textArea.setText(msg);
	    textArea.setLineWrap(true);
	    textArea.setWrapStyleWord(true);
	    textArea.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

	    contentPane.add(new JScrollPane(textArea));
		
		JButton btnNewButton = new JButton("Reply");
		btnNewButton.setForeground(SystemColor.activeCaptionText);
		btnNewButton.setBackground(SystemColor.activeCaption);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		contentPane.add(btnNewButton, BorderLayout.SOUTH);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String sms = textArea.getText();
				ProcessConnectionThread.sendMessage(sms);
				
				JOptionPane.showMessageDialog(frame, "Message has been sent");
				
				
			}
		});
//		String sms = textArea.getText();
//		
		
		
	}

}