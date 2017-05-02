package com.remote.control.computer;

import java.awt.AWTException;
import java.awt.EventQueue;
import java.awt.Robot;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.microedition.io.StreamConnection;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.text.StyledEditorKit.BoldAction;

public class ProcessConnectionThread implements Runnable {

	private static StreamConnection mConnection;

	public static boolean isOutputStreamOpen = false;
	public static OutputStream outputStream = null;
	// Constant that indicate command from devices
	private static final int EXIT_CMD = -1;

	// power Point
	private static final int KEY_RIGHT = 1;
	private static final int KEY_LEFT = 2;
	private static final int KEY_HOME = 4;
	private static final int KEY_END = 5;
	private static final int KEY_SLIDE_SHOW = 9;

	// power Ooption
	private static final int key_ShutDown = 6;
	private static final int key_restart = 7;
	private static final int key_loggingOff = 8;

	// for windows media player

	private static final int windows_media_player_key_button_next = 101;
	private static final int windows_media_player_key_button_previous = 102;
	private static final int windows_media_player_key_button_fastForward = 103;
	private static final int windows_media_player_key_button_fastPrevious = 104;
	private static final int windows_media_player_key_button_PushPlay = 105;
	private static final int windows_media_player_key_button_voloumUp = 106;
	private static final int windows_media_player_key_button_voloumDown = 107;

	// for km player

	private static final int key_button_next = 111;
	private static final int key_button_previous = 112;
	private static final int key_button_fastForward = 113;
	private static final int key_button_fastPrevious = 114;
	private static final int key_button_PushPlay = 115;
	private static final int key_button_voloumUp = 116;
	private static final int key_button_voloumDown = 117;

	// For VLC Player

	private static final int vlc_button_next = 201;
	private static final int vlc_button_previous = 202;
	private static final int vlc_button_fastForward = 203;
	private static final int vlc_button_fastPrevious = 204;
	private static final int vlc_button_PushPlay = 205;
	private static final int vlc_button_voloumUp = 206;
	private static final int vlc_button_voloumDown = 207;

	// For Gom Player

	private static final int gom_button_next = 211;
	private static final int gom_button_previous = 212;
	private static final int gom_button_fastForward = 213;
	private static final int gom_button_fastPrevious = 214;
	private static final int gom_button_PushPlay = 215;
	private static final int gom_button_voloumUp = 216;
	private static final int gom_button_voloumDown = 217;

	public ProcessConnectionThread(StreamConnection connection) {
		mConnection = connection;
	}

	@Override
	public void run() {
		try {

			// prepare to receive data
			InputStream inputStream = mConnection.openInputStream();

			System.out.println("waiting for input");

			while (true) {
				// int command = inputStream.read();

				byte[] b = new byte[1024];
				inputStream.read(b);

				// System.out.println("IN L:--" + inputStream.available());
				String temp = "";
				for (byte by : b) {

					if (by == 0)
						break;
					else
						temp = temp + (char) by;

				}

				if (temp.startsWith("%")) {

					processMsg(temp);

				} else if (temp.startsWith("*")) {
					processCall(temp);
				}

				else if (temp.startsWith("$")) {

					processKeyboard(temp);

				}

				else {
					if (temp != null && !"".equals(temp)) {
						try {
							Integer command = Integer.parseInt(temp);
							// Integer command = Integer.parseInt(temp);
							// Integer command = Integer.valueOf(temp);

							if (command == EXIT_CMD) {
								System.out.println("finish process");
								break;
							}

							processCommand(command);
						} catch (NumberFormatException e) {
							// TODO: handle exception
							System.out.println("This is not a number");
							System.out.println(e.getMessage());
						}

					}
					// System.out.println(temp);
					/*
					 * if (command == EXIT_CMD) {
					 * System.out.println("finish process"); break; }
					 * 
					 * processCommand(command);
					 */}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void processKeyboard(String key_letter) {
		// TODO Auto-generated method stub

		char keyboard_letter_charecter = 0;
		
		Robot robot = null;
		try {
			robot = new Robot();
		} catch (AWTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		if (key_letter.length() > 2) {
			robot.keyPress(KeyEvent.VK_BACK_SPACE);

		}

		else {
			String keyboardLetter = key_letter.substring(1);
			keyboard_letter_charecter = keyboardLetter.charAt(0);
			

			try {
				
				switch (keyboard_letter_charecter) {

				case 'a':
					robot.keyPress(KeyEvent.VK_A);
					break;
				case 'b':
					robot.keyPress(KeyEvent.VK_B);
					break;
				case 'c':
					robot.keyPress(KeyEvent.VK_C);
					break;
				case 'd':
					robot.keyPress(KeyEvent.VK_D);
					break;
				case 'e':
					robot.keyPress(KeyEvent.VK_E);
					break;
				case 'f':
					robot.keyPress(KeyEvent.VK_F);
					break;
				case 'g':
					robot.keyPress(KeyEvent.VK_G);
					break;
				case 'h':
					robot.keyPress(KeyEvent.VK_H);
					break;
				case 'i':
					robot.keyPress(KeyEvent.VK_I);
					break;
				case 'j':
					robot.keyPress(KeyEvent.VK_J);
					break;
				case 'k':
					robot.keyPress(KeyEvent.VK_K);
					break;
				case 'l':
					robot.keyPress(KeyEvent.VK_L);
					break;
				case 'm':
					robot.keyPress(KeyEvent.VK_M);
					break;
				case 'n':
					robot.keyPress(KeyEvent.VK_N);
					break;
				case 'o':
					robot.keyPress(KeyEvent.VK_O);
					break;
				case 'p':
					robot.keyPress(KeyEvent.VK_P);
					break;
				case 'q':
					robot.keyPress(KeyEvent.VK_Q);
					break;
				case 'r':
					robot.keyPress(KeyEvent.VK_R);
					break;
				case 's':
					robot.keyPress(KeyEvent.VK_S);
					break;
				case 't':
					robot.keyPress(KeyEvent.VK_T);
					break;
				case 'u':
					robot.keyPress(KeyEvent.VK_U);
					break;
				case 'v':
					robot.keyPress(KeyEvent.VK_V);
					break;
				case 'w':
					robot.keyPress(KeyEvent.VK_W);
					break;
				case 'x':
					robot.keyPress(KeyEvent.VK_X);
					break;
				case 'y':
					robot.keyPress(KeyEvent.VK_Y);
					break;
				case 'z':
					robot.keyPress(KeyEvent.VK_Z);
					break;
				case 'A':
					robot.keyPress(KeyEvent.VK_SHIFT);
					robot.keyPress(KeyEvent.VK_A);
					robot.keyRelease(KeyEvent.VK_SHIFT);
					break;

				case 'B':
					robot.keyPress(KeyEvent.VK_SHIFT);
					robot.keyPress(KeyEvent.VK_B);
					robot.keyRelease(KeyEvent.VK_SHIFT);
					break;
				case 'C':
					robot.keyPress(KeyEvent.VK_SHIFT);
					robot.keyPress(KeyEvent.VK_C);
					robot.keyRelease(KeyEvent.VK_SHIFT);
					break;
				case 'D':
					robot.keyPress(KeyEvent.VK_SHIFT);
					robot.keyPress(KeyEvent.VK_D);
					robot.keyRelease(KeyEvent.VK_SHIFT);
					break;
				case 'E':
					robot.keyPress(KeyEvent.VK_SHIFT);
					robot.keyPress(KeyEvent.VK_E);
					robot.keyRelease(KeyEvent.VK_SHIFT);
					break;
				case 'F':
					robot.keyPress(KeyEvent.VK_SHIFT);
					robot.keyPress(KeyEvent.VK_F);
					robot.keyRelease(KeyEvent.VK_SHIFT);
					break;
				case 'G':
					robot.keyPress(KeyEvent.VK_SHIFT);
					robot.keyPress(KeyEvent.VK_G);
					robot.keyRelease(KeyEvent.VK_SHIFT);
					break;
				case 'H':
					robot.keyPress(KeyEvent.VK_SHIFT);
					robot.keyPress(KeyEvent.VK_H);
					robot.keyRelease(KeyEvent.VK_SHIFT);
					break;
				case 'I':
					robot.keyPress(KeyEvent.VK_SHIFT);
					robot.keyPress(KeyEvent.VK_I);
					robot.keyRelease(KeyEvent.VK_SHIFT);
					break;
				case 'J':
					robot.keyPress(KeyEvent.VK_SHIFT);
					robot.keyPress(KeyEvent.VK_J);
					robot.keyRelease(KeyEvent.VK_SHIFT);
					break;
				case 'K':
					robot.keyPress(KeyEvent.VK_SHIFT);
					robot.keyPress(KeyEvent.VK_K);
					robot.keyRelease(KeyEvent.VK_SHIFT);
					break;
				case 'L':
					robot.keyPress(KeyEvent.VK_SHIFT);
					robot.keyPress(KeyEvent.VK_L);
					robot.keyRelease(KeyEvent.VK_SHIFT);
					break;
				case 'M':
					robot.keyPress(KeyEvent.VK_SHIFT);
					robot.keyPress(KeyEvent.VK_M);
					robot.keyRelease(KeyEvent.VK_SHIFT);
					break;
				case 'N':
					robot.keyPress(KeyEvent.VK_SHIFT);
					robot.keyPress(KeyEvent.VK_N);
					robot.keyRelease(KeyEvent.VK_SHIFT);
					break;
				case 'O':
					robot.keyPress(KeyEvent.VK_SHIFT);
					robot.keyPress(KeyEvent.VK_O);
					robot.keyRelease(KeyEvent.VK_SHIFT);
					break;
				case 'P':
					robot.keyPress(KeyEvent.VK_SHIFT);
					robot.keyPress(KeyEvent.VK_P);
					robot.keyRelease(KeyEvent.VK_SHIFT);
					break;
				case 'Q':
					robot.keyPress(KeyEvent.VK_SHIFT);
					robot.keyPress(KeyEvent.VK_Q);
					robot.keyRelease(KeyEvent.VK_SHIFT);
					break;
				case 'R':
					robot.keyPress(KeyEvent.VK_SHIFT);
					robot.keyPress(KeyEvent.VK_R);
					robot.keyRelease(KeyEvent.VK_SHIFT);
					break;
				case 'S':
					robot.keyPress(KeyEvent.VK_SHIFT);
					robot.keyPress(KeyEvent.VK_S);
					robot.keyRelease(KeyEvent.VK_SHIFT);
					break;
				case 'T':
					robot.keyPress(KeyEvent.VK_SHIFT);
					robot.keyPress(KeyEvent.VK_T);
					robot.keyRelease(KeyEvent.VK_SHIFT);
					break;
				case 'U':
					robot.keyPress(KeyEvent.VK_SHIFT);
					robot.keyPress(KeyEvent.VK_U);
					robot.keyRelease(KeyEvent.VK_SHIFT);
					break;
				case 'V':
					robot.keyPress(KeyEvent.VK_SHIFT);
					robot.keyPress(KeyEvent.VK_V);
					robot.keyRelease(KeyEvent.VK_SHIFT);
					break;
				case 'W':
					robot.keyPress(KeyEvent.VK_SHIFT);
					robot.keyPress(KeyEvent.VK_W);
					robot.keyRelease(KeyEvent.VK_SHIFT);
					break;
				case 'X':
					robot.keyPress(KeyEvent.VK_SHIFT);
					robot.keyPress(KeyEvent.VK_X);
					robot.keyRelease(KeyEvent.VK_SHIFT);
					break;
				case 'Y':
					robot.keyPress(KeyEvent.VK_SHIFT);
					robot.keyPress(KeyEvent.VK_Y);
					robot.keyRelease(KeyEvent.VK_SHIFT);
					break;
				case 'Z':
					robot.keyPress(KeyEvent.VK_SHIFT);
					robot.keyPress(KeyEvent.VK_Z);
					robot.keyRelease(KeyEvent.VK_SHIFT);
					break;
				case '`':
					robot.keyPress(KeyEvent.VK_BACK_QUOTE);
					break;
				case '0':
					robot.keyPress(KeyEvent.VK_0);
					break;
				case '1':
					robot.keyPress(KeyEvent.VK_1);
					break;
				case '2':
					robot.keyPress(KeyEvent.VK_2);
					break;
				case '3':
					robot.keyPress(KeyEvent.VK_3);
					break;
				case '4':
					robot.keyPress(KeyEvent.VK_4);
					break;
				case '5':
					robot.keyPress(KeyEvent.VK_5);
					break;
				case '6':
					robot.keyPress(KeyEvent.VK_6);
					break;
				case '7':
					robot.keyPress(KeyEvent.VK_7);
					break;
				case '8':
					robot.keyPress(KeyEvent.VK_8);
					break;
				case '9':
					robot.keyPress(KeyEvent.VK_9);
					break;
				case '-':
					robot.keyPress(KeyEvent.VK_MINUS);
					break;
				case '=':
					robot.keyPress(KeyEvent.VK_EQUALS);
					break;
				case '~':
					robot.keyPress(KeyEvent.VK_SHIFT);
					robot.keyPress(KeyEvent.VK_BACK_QUOTE);
					robot.keyRelease(KeyEvent.VK_SHIFT);
					break;
				case '!':
					robot.keyPress(KeyEvent.VK_SHIFT);
					robot.keyPress(KeyEvent.VK_1);
					robot.keyRelease(KeyEvent.VK_SHIFT);
					break;
				case '@':
					robot.keyPress(KeyEvent.VK_SHIFT);
					robot.keyPress(KeyEvent.VK_2);
					robot.keyRelease(KeyEvent.VK_SHIFT);
					break;
				case '#':
					robot.keyPress(KeyEvent.VK_SHIFT);
					robot.keyPress(KeyEvent.VK_3);
					robot.keyRelease(KeyEvent.VK_SHIFT);
					break;
				case '$':
					robot.keyPress(KeyEvent.VK_SHIFT);
					robot.keyPress(KeyEvent.VK_4);
					robot.keyRelease(KeyEvent.VK_SHIFT);
					break;
				case '%':
					robot.keyPress(KeyEvent.VK_SHIFT);
					robot.keyPress(KeyEvent.VK_5);
					robot.keyRelease(KeyEvent.VK_SHIFT);
					break;
				case '^':
					robot.keyPress(KeyEvent.VK_SHIFT);
					robot.keyPress(KeyEvent.VK_6);
					robot.keyRelease(KeyEvent.VK_SHIFT);
					break;
				case '&':
					robot.keyPress(KeyEvent.VK_SHIFT);
					robot.keyPress(KeyEvent.VK_7);
					robot.keyRelease(KeyEvent.VK_SHIFT);
					break;
				case '*':
					robot.keyPress(KeyEvent.VK_SHIFT);
					robot.keyPress(KeyEvent.VK_8);
					robot.keyRelease(KeyEvent.VK_SHIFT);
					break;
				case '(':
					robot.keyPress(KeyEvent.VK_SHIFT);
					robot.keyPress(KeyEvent.VK_9);
					robot.keyRelease(KeyEvent.VK_SHIFT);
					break;
				case ')':
					robot.keyPress(KeyEvent.VK_SHIFT);
					robot.keyPress(KeyEvent.VK_0);
					robot.keyRelease(KeyEvent.VK_SHIFT);
					break;
				case '_':
					robot.keyPress(KeyEvent.VK_SHIFT);
					robot.keyPress(KeyEvent.VK_MINUS);
					robot.keyRelease(KeyEvent.VK_SHIFT);
					break;
				case '+':
					robot.keyPress(KeyEvent.VK_SHIFT);
					robot.keyPress(KeyEvent.VK_EQUALS);
					robot.keyRelease(KeyEvent.VK_SHIFT);
					break;
				case '\t':
					robot.keyPress(KeyEvent.VK_TAB);
					break;
				case '\n':
					robot.keyPress(KeyEvent.VK_ENTER);
					break;
				case '[':
					robot.keyPress(KeyEvent.VK_OPEN_BRACKET);
					break;
				case ']':
					robot.keyPress(KeyEvent.VK_CLOSE_BRACKET);
					break;
				case '\\':
					robot.keyPress(KeyEvent.VK_BACK_SLASH);
					break;
				case '{':
					robot.keyPress(KeyEvent.VK_SHIFT);
					robot.keyPress(KeyEvent.VK_OPEN_BRACKET);
					robot.keyRelease(KeyEvent.VK_SHIFT);
					break;
				case '}':
					robot.keyPress(KeyEvent.VK_SHIFT);
					robot.keyPress(KeyEvent.VK_CLOSE_BRACKET);
					robot.keyRelease(KeyEvent.VK_SHIFT);
					break;
				case '|':
					robot.keyPress(KeyEvent.VK_SHIFT);
					robot.keyPress(KeyEvent.VK_BACK_SLASH);
					robot.keyRelease(KeyEvent.VK_SHIFT);
					break;
				case ';':
					robot.keyPress(KeyEvent.VK_SEMICOLON);
					break;
				case ':':
					robot.keyPress(KeyEvent.VK_SHIFT);
					robot.keyPress(KeyEvent.VK_SEMICOLON);
					robot.keyRelease(KeyEvent.VK_SHIFT);
					break;
				case '\'':
					robot.keyPress(KeyEvent.VK_QUOTE);
					break;
				case '"':
					robot.keyPress(KeyEvent.VK_SHIFT);
					robot.keyPress(KeyEvent.VK_QUOTE);
					robot.keyRelease(KeyEvent.VK_SHIFT);
					break;
				case ',':
					robot.keyPress(KeyEvent.VK_COMMA);
					break;
				case '<':
					robot.keyPress(KeyEvent.VK_SHIFT);
					robot.keyPress(KeyEvent.VK_COMMA);
					robot.keyRelease(KeyEvent.VK_SHIFT);
					break;
				case '.':
					robot.keyPress(KeyEvent.VK_PERIOD);
					break;
				case '>':
					robot.keyPress(KeyEvent.VK_SHIFT);
					robot.keyPress(KeyEvent.VK_PERIOD);
					robot.keyRelease(KeyEvent.VK_SHIFT);
					break;
				case '/':
					robot.keyPress(KeyEvent.VK_SLASH);
					break;
				case '?':
					robot.keyPress(KeyEvent.VK_SHIFT);
					robot.keyPress(KeyEvent.VK_SLASH);
					robot.keyRelease(KeyEvent.VK_SHIFT);
					break;
				case ' ':
					robot.keyPress(KeyEvent.VK_SPACE);
					break;

				default:
					break;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
			

		}
		
		
	}

	// receive the call name or number
	private void processCall(final String call) {
		// TODO Auto-generated method stub
		

		try {
			for (UIManager.LookAndFeelInfo info : UIManager
					.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// JFrame.setDefaultLookAndFeelDecorated(true);
		final String call_name = call.substring(1);
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				MessageNotification w = new MessageNotification(call_name);
				// w.setVisible(true);
			}
		});

	}

	// Process the command from client

	private void processMsg(final String msg) {
	
		final String sms = msg.substring(1);

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Notification frame = new Notification(sms);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	public static void sendMessage(String sendMsg) {

		try {

			if (!isOutputStreamOpen) {

				outputStream = mConnection.openOutputStream();
				String f5 = "%" + sendMsg;

				byte[] f5byte = f5.getBytes();
				outputStream.write(f5byte);
				isOutputStreamOpen = true;
			} else {
				String f5 = "%" + sendMsg;

				byte[] f5byte = f5.getBytes();
				outputStream.write(f5byte);

			}

			// mConnection.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void processCommand(int command) {
		try {
			Robot robot = new Robot();
			switch (command) {

			// power point
			case KEY_RIGHT:
				robot.keyPress(KeyEvent.VK_RIGHT);
				System.out.println("Right");
				// release the key after it is pressed. Otherwise the event just
				// keeps getting trigged
				robot.keyRelease(KeyEvent.VK_RIGHT);
				break;
			case KEY_LEFT:
				robot.keyPress(KeyEvent.VK_LEFT);
				System.out.println("Left");
				// release the key after it is pressed. Otherwise the event just
				// keeps getting trigged
				robot.keyRelease(KeyEvent.VK_LEFT);
				break;
			case KEY_HOME:
				robot.keyPress(KeyEvent.VK_HOME);
				System.out.println("jump to First....");
				robot.keyRelease(KeyEvent.VK_HOME);
				break;
			case KEY_END:
				robot.keyPress(KeyEvent.VK_END);
				System.out.println("Jump to last");
				robot.keyRelease(KeyEvent.VK_END);
				break;
			case KEY_SLIDE_SHOW:
				robot.keyPress(KeyEvent.VK_F5);
				System.out.println("Slide Show Mode");
				robot.keyRelease(KeyEvent.VK_F5);
				break;

			// power option
			case key_ShutDown:
				shutDown();
				System.out.println("Shutting Down");
				break;
			case key_restart:
				reStart();
				System.out.println("Restarting...");
				break;
			case key_loggingOff:
				loggingOff();
				System.out.println("Logging off....");
				System.exit(0);
				break;

			// for windows media player

			case windows_media_player_key_button_next:
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_F);
				System.out.println("Going to next....");
				robot.keyRelease(KeyEvent.VK_F);
				robot.keyRelease(KeyEvent.VK_CONTROL);
				break;
			case windows_media_player_key_button_previous:
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_B);
				System.out.println("Going to previous....");
				robot.keyRelease(KeyEvent.VK_B);
				robot.keyRelease(KeyEvent.VK_CONTROL);
				break;
			case windows_media_player_key_button_fastForward:
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_F);
				System.out.println("Fast forwarding....");
				robot.keyRelease(KeyEvent.VK_F);
				robot.keyRelease(KeyEvent.VK_SHIFT);
				robot.keyRelease(KeyEvent.VK_CONTROL);
				break;
			case windows_media_player_key_button_fastPrevious:
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_B);
				System.out.println("Fast Backwarding....");
				robot.keyRelease(KeyEvent.VK_B);
				robot.keyRelease(KeyEvent.VK_SHIFT);
				robot.keyRelease(KeyEvent.VK_CONTROL);
				break;
			case windows_media_player_key_button_PushPlay:
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_P);
				System.out.println("Pause/Play....");
				robot.keyRelease(KeyEvent.VK_P);
				robot.keyRelease(KeyEvent.VK_CONTROL);
				break;
			case windows_media_player_key_button_voloumUp:
				robot.keyPress(KeyEvent.VK_F9);
				System.out.println("Volume Up....");
				robot.keyRelease(KeyEvent.VK_F9);
				break;

			case windows_media_player_key_button_voloumDown:
				robot.keyPress(KeyEvent.VK_F8);
				System.out.println("Volume Down....");
				robot.keyRelease(KeyEvent.VK_F8);
				break;

			// for KM player
			case key_button_next:
				robot.keyPress(KeyEvent.VK_PAGE_DOWN);
				System.out.println("Going to next....");
				robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
				break;
			case key_button_previous:
				robot.keyPress(KeyEvent.VK_PAGE_UP);
				System.out.println("Going to previous....");
				robot.keyRelease(KeyEvent.VK_PAGE_UP);
				break;
			case key_button_fastForward:
				robot.keyPress(KeyEvent.VK_RIGHT);
				System.out.println("Fast forwarding....");
				robot.keyRelease(KeyEvent.VK_RIGHT);
				break;
			case key_button_fastPrevious:
				robot.keyPress(KeyEvent.VK_LEFT);
				System.out.println("Fast Backwarding....");
				robot.keyRelease(KeyEvent.VK_LEFT);
				break;
			case key_button_PushPlay:
				robot.keyPress(KeyEvent.VK_SPACE);
				System.out.println("Pause/Play....");
				robot.keyRelease(KeyEvent.VK_SPACE);
				break;
			case key_button_voloumUp:
				robot.keyPress(KeyEvent.VK_UP);
				System.out.println("Volume Up....");
				robot.keyRelease(KeyEvent.VK_UP);
				break;

			case key_button_voloumDown:
				robot.keyPress(KeyEvent.VK_DOWN);
				System.out.println("Volume Down....");
				robot.keyRelease(KeyEvent.VK_DOWN);
				break;

			// For VLC Player

			case vlc_button_next:
				robot.keyPress(KeyEvent.VK_N);
				System.out.println("Going to next....");
				robot.keyRelease(KeyEvent.VK_N);
				break;
			case vlc_button_previous:
				robot.keyPress(KeyEvent.VK_P);
				System.out.println("Going to previous....");
				robot.keyRelease(KeyEvent.VK_P);
				break;
			case vlc_button_fastForward:
				robot.keyPress(KeyEvent.VK_ALT);
				robot.keyPress(KeyEvent.VK_RIGHT);
				System.out.println("Fast forwarding....");
				robot.keyRelease(KeyEvent.VK_RIGHT);
				robot.keyRelease(KeyEvent.VK_ALT);
				break;
			case vlc_button_fastPrevious:
				robot.keyPress(KeyEvent.VK_ALT);
				robot.keyPress(KeyEvent.VK_LEFT);
				System.out.println("Fast Backwarding....");
				robot.keyRelease(KeyEvent.VK_LEFT);
				robot.keyRelease(KeyEvent.VK_ALT);
				break;
			case vlc_button_PushPlay:
				robot.keyPress(KeyEvent.VK_SPACE);
				System.out.println("Pause/Play....");
				robot.keyRelease(KeyEvent.VK_SPACE);
				break;
			case vlc_button_voloumUp:
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_UP);
				System.out.println("Volume Up....");
				robot.keyRelease(KeyEvent.VK_UP);
				robot.keyRelease(KeyEvent.VK_CONTROL);
				break;

			case vlc_button_voloumDown:
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_DOWN);
				System.out.println("Volume Down....");
				robot.keyRelease(KeyEvent.VK_DOWN);
				robot.keyRelease(KeyEvent.VK_CONTROL);
				break;

			// For gom Player

			case gom_button_next:
				robot.keyPress(KeyEvent.VK_PAGE_DOWN);
				System.out.println("Going to next....");
				robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
				break;
			case gom_button_previous:
				robot.keyPress(KeyEvent.VK_PAGE_UP);
				System.out.println("Going to previous....");
				robot.keyRelease(KeyEvent.VK_PAGE_UP);
				break;
			case gom_button_fastForward:
				robot.keyPress(KeyEvent.VK_RIGHT);
				System.out.println("Fast forwarding....");
				robot.keyRelease(KeyEvent.VK_RIGHT);
				break;
			case gom_button_fastPrevious:
				robot.keyPress(KeyEvent.VK_LEFT);
				System.out.println("Fast Backwarding....");
				robot.keyRelease(KeyEvent.VK_LEFT);
				break;
			case gom_button_PushPlay:
				robot.keyPress(KeyEvent.VK_SPACE);
				System.out.println("Pause/Play....");
				robot.keyRelease(KeyEvent.VK_SPACE);
				break;
			case gom_button_voloumUp:
				robot.keyPress(KeyEvent.VK_UP);
				System.out.println("Volume Up....");
				robot.keyRelease(KeyEvent.VK_UP);
				break;

			case gom_button_voloumDown:
				robot.keyPress(KeyEvent.VK_DOWN);
				System.out.println("Volume Down....");
				robot.keyRelease(KeyEvent.VK_DOWN);
				break;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void loggingOff() throws IOException {

		String shutdownCmd = "shutdown -l -f";
		Process child = Runtime.getRuntime().exec(shutdownCmd);
	}

	public void reStart() throws Exception {
		Runtime r = Runtime.getRuntime();
		r.exec("shutdown -r -f -t 0");
	}

	public void shutDown() throws IOException {

		Runtime runtime = Runtime.getRuntime();
		runtime.exec("shutdown -s -f -t 0");
		System.exit(0);

	}

	public byte[] readBytes(InputStream inputStream) throws IOException {
		// this dynamically extends to take the bytes you read
		ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();

		// this is storage overwritten on each iteration with bytes
		int bufferSize = 1024;
		byte[] buffer = new byte[bufferSize];

		// we need to know how may bytes were read to write them to the
		// byteBuffer
		int len = 0;
		while ((len = inputStream.read(buffer)) != -1) {
			byteBuffer.write(buffer, 0, len);
		}

		// and then we can return your byte array.
		return byteBuffer.toByteArray();
	}

}
