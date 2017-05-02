package com.remote.control.computer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.telephony.gsm.SmsManager;
import android.util.Log;

public class BluetoothCommandService {
	// Debugging
	private static final String TAG = "BluetoothCommandService";
	private static final boolean D = true;

	// Unique UUID for this application
	private static final UUID MY_UUID = UUID
			.fromString("04c6093b-0000-1000-8000-00805f9b34fb");

	// Member fields
	private final BluetoothAdapter mAdapter;
	private final Handler mHandler;
	private ConnectThread mConnectThread;
	private ConnectedThread mConnectedThread;
	private int mState;
	// private BluetoothDevice mSavedDevice;
	// private int mConnectionLostCount;

	// Constants that indicate the current connection state
	public static final int STATE_NONE = 0; // we're doing nothing
	public static final int STATE_LISTEN = 1; // listening for incoming
												// connections
	public static final int STATE_CONNECTING = 2; // initiating an outgoing
													// connection
	public static final int STATE_CONNECTED = 3; // connected to a remote
													// device

	// Constants that indicate command to computer
	public static final int EXIT_CMD = -1;
	
	//for powerPoint
	public static final int VOL_UP = 1;
	public static final int VOL_DOWN = 2;
	public static final int MOUSE_MOVE = 3;
	public static final int Top = 4;
	public static final int Bottom = 5;
	public static final int SlideShow = 9;
	
	//for power option
	public static final int SHUT_DOWN =	6;
	public static final int Restart = 7;
	public static final int LOG_OFF = 8;
	
	//windows media player
	public static final int WINDOWS_MEDIA_PLAYER_BUTTON_NEXT=101;
	public static final int WINDOWS_MEDIA_PLAYER_BUTTON_PREVIOUS=102;
	public static final int WINDOWS_MEDIA_PLAYER_BUTTON_FIRSTFORWARD=103;
	public static final int WINDOWS_MEDIA_PLAYER_BUTTON_FIRSTPREVIOUS=104;
	public static final int WINDOWS_MEDIA_PLAYER_BUTTON_PUSH_POP=105;
	public static final int WINDOWS_MEDIA_PLAYER_BUTTON_VOLOUM_UP=106;
	public static final int WINDOWS_MEDIA_PLAYER_BUTTON_VOLOUM_DOWN=107;
	
	
	// For KM player
	public static final int BUTTON_NEXT=111;
	public static final int BUTTON_PREVIOUS=112;
	public static final int BUTTON_FIRSTFORWARD=113;
	public static final int BUTTON_FIRSTPREVIOUS=114;
	public static final int BUTTON_PUSH_POP=115;
	public static final int BUTTON_VOLOUM_UP=116;
	public static final int BUTTON_VOLOUM_DOWN=117;
	
	
	// For Gom Player
	
	public static final int GOM_BUTTON_NEXT=211;
	public static final int GOM_BUTTON_PREVIOUS=212;
	public static final int GOM_BUTTON_FIRSTFORWARD=213;
	public static final int GOM_BUTTON_FIRSTPREVIOUS=214;
	public static final int GOM_BUTTON_PUSH_POP=215;
	public static final int GOM_BUTTON_VOLOUM_UP=216;
	public static final int GOM_BUTTON_VOLOUM_DOWN=217;
	
	//For VLC player
	
	public static final int VLC_BUTTON_NEXT=201;
	public static final int VLC_BUTTON_PREVIOUS=202;
	public static final int VLC_BUTTON_FIRSTFORWARD=203;
	public static final int VLC_BUTTON_FIRSTPREVIOUS=204;
	public static final int VLC_BUTTON_PUSH_POP=205;
	public static final int VLC_BUTTON_VOLOUM_UP=206;
	public static final int VLC_BUTTON_VOLOUM_DOWN=207;
	
	
	

	
	
	
	public BluetoothCommandService(Context context, Handler handler) {
		mAdapter = BluetoothAdapter.getDefaultAdapter();
		mState = STATE_NONE;
		// mConnectionLostCount = 0;
		mHandler = handler;
	}

	
	private synchronized void setState(int state) {
		if (D)
			Log.d(TAG, "setState() " + mState + " -> " + state);
		mState = state;

		// Give the new state to the Handler so the UI Activity can update
		mHandler.obtainMessage(RemoteBluetooth.MESSAGE_STATE_CHANGE, state, -1)
				.sendToTarget();
	}

	/**
	 * Return the current connection state.
	 */
	public synchronized int getState() {
		return mState;
	}

	/*
	  Start the chat service. begin a session in listening (server) mode. Called by the Activity onResume()
	 */
	public synchronized void start() {
		if (D)
			Log.d(TAG, "start");

		// Cancel any thread attempting to make a connection
		if (mConnectThread != null) {
			mConnectThread.cancel();
			mConnectThread = null;
		}

		// Cancel any thread currently running a connection
		if (mConnectedThread != null) {
			mConnectedThread.cancel();
			mConnectedThread = null;
		}

		setState(STATE_LISTEN);
	}

	/*
	  Start the ConnectThread to initiate a connection to a remote device.
	             
	 */
	public synchronized void connect(BluetoothDevice device) {
		if (D)
			Log.d(TAG, "connect to: " + device);

		// Cancel any thread attempting to make a connection
		if (mState == STATE_CONNECTING) {
			if (mConnectThread != null) {
				mConnectThread.cancel();
				mConnectThread = null;
			}
		}

		// Cancel any thread currently running a connection
		if (mConnectedThread != null) {
			mConnectedThread.cancel();
			mConnectedThread = null;
		}

		// Start the thread to connect with the given device
		mConnectThread = new ConnectThread(device);
		mConnectThread.start();
		setState(STATE_CONNECTING);
	}

	
	public synchronized void connected(BluetoothSocket socket,
			BluetoothDevice device) {
		if (D)
			Log.d(TAG, "connected");

		// Cancel the thread that completed the connection
		if (mConnectThread != null) {
			mConnectThread.cancel();
			mConnectThread = null;
		}

		// Cancel any thread currently running a connection
		if (mConnectedThread != null) {
			mConnectedThread.cancel();
			mConnectedThread = null;
		}

		// Start the thread to manage the connection and perform transmissions
		mConnectedThread = new ConnectedThread(socket);
		mConnectedThread.start();

		// Send the name of the connected device back to the UI Activity
		Message msg = mHandler
				.obtainMessage(RemoteBluetooth.MESSAGE_DEVICE_NAME);
		Bundle bundle = new Bundle();
		bundle.putString(RemoteBluetooth.DEVICE_NAME, device.getName());
		msg.setData(bundle);
		mHandler.sendMessage(msg);

		// save connected device
		// mSavedDevice = device;
		// reset connection lost count
		// mConnectionLostCount = 0;

		setState(STATE_CONNECTED);
	}

	/*
	  Stop all threads
	 */
	public synchronized void stop() {
		if (D)
			Log.d(TAG, "stop");
		if (mConnectThread != null) {
			mConnectThread.cancel();
			mConnectThread = null;
		}
		if (mConnectedThread != null) {
			mConnectedThread.cancel();
			mConnectedThread = null;
		}

		setState(STATE_NONE);
	}

	
	public void write(byte[] out) {
		// Create temporary object
		ConnectedThread r;
		// Synchronize a copy of the ConnectedThread
		synchronized (this) {
			if (mState != STATE_CONNECTED)
				return;
			r = mConnectedThread;
		}
		// Perform the write unsynchronized
		r.write(out);
	}
	
	
	public void write(int out) {
		// Create temporary object
		ConnectedThread r;
		// Synchronize a copy of the ConnectedThread
		synchronized (this) {
			if (mState != STATE_CONNECTED)
				return;
			r = mConnectedThread;
		}
		// Perform the write unsynchronized
		r.write(out);
	}

	/**
	 * Indicate that the connection attempt failed and notify the UI Activity.
	 */
	private void connectionFailed() {
		setState(STATE_LISTEN);

		// Send a failure message back to the Activity
		Message msg = mHandler.obtainMessage(RemoteBluetooth.MESSAGE_TOAST);
		Bundle bundle = new Bundle();
		bundle.putString(RemoteBluetooth.TOAST, "Unable to connect device");
		msg.setData(bundle);
		mHandler.sendMessage(msg);
	}

	/**
	 * Indicate that the connection was lost and notify the UI Activity.
	 */
	private void connectionLost() {
		
		setState(STATE_LISTEN);
		// Send a failure message back to the Activity
		Message msg = mHandler.obtainMessage(RemoteBluetooth.MESSAGE_TOAST);
		Bundle bundle = new Bundle();
		bundle.putString(RemoteBluetooth.TOAST, "Device connection was lost");
		msg.setData(bundle);
		mHandler.sendMessage(msg);
		// }
	}

	/*
	  This thread runs while attempting to make an outgoing connection with a
	  device
	 */
	private class ConnectThread extends Thread {
		private final BluetoothSocket mmSocket;
		private final BluetoothDevice mmDevice;

		public ConnectThread(BluetoothDevice device) {
			mmDevice = device;
			BluetoothSocket tmp = null;

			// Get a BluetoothSocket for a connection with the
			// given BluetoothDevice
			try {
				tmp = device.createRfcommSocketToServiceRecord(MY_UUID);
			} catch (IOException e) {
				Log.e(TAG, "create() failed", e);
			}
			mmSocket = tmp;
		}

		public void run() {
			Log.i(TAG, "BEGIN mConnectThread");
			setName("ConnectThread");

			// discovery cancelled because it will slow down a connection
			mAdapter.cancelDiscovery();

			// Make a connection to the BluetoothSocket
			try {
				// This is a blocking call and will only return on a
				// successful connection or an exception
				mmSocket.connect();
			} catch (IOException e) {
				connectionFailed();
				// Close the socket
				try {
					mmSocket.close();
				} catch (IOException e2) {
					Log.e(TAG,
							"unable to close() socket during connection failure",
							e2);
				}
				// Start the service over to restart listening mode
				BluetoothCommandService.this.start();
				return;
			}

			// Reset the ConnectThread because we're done
			synchronized (BluetoothCommandService.this) {
				mConnectThread = null;
			}

			// Start the connected thread
			connected(mmSocket, mmDevice);
		}

		public void cancel() {
			try {
				mmSocket.close();
			} catch (IOException e) {
				Log.e(TAG, "close() of connect socket failed", e);
			}
		}
	}

	/**
	 * This thread runs during a connection with a remote device. It handles all
	 * incoming and outgoing transmissions.
	 */
	private class ConnectedThread extends Thread {
		private final BluetoothSocket mmSocket;
		private final InputStream mmInStream;
		private final OutputStream mmOutStream;

		public ConnectedThread(BluetoothSocket socket) {
			Log.d(TAG, "create ConnectedThread");
			mmSocket = socket;
			InputStream tmpIn = null;
			OutputStream tmpOut = null;

			// Get the BluetoothSocket input and output streams
			try {
				tmpIn = socket.getInputStream();
				tmpOut = socket.getOutputStream();
			} catch (IOException e) {
				Log.e(TAG, "temp sockets not created", e);
			}

			mmInStream = tmpIn;
			mmOutStream = tmpOut;
		}

		public void run() {
			Log.i(TAG, "BEGIN mConnectedThread");
			byte[] buffer = new byte[1024];

			// Keep listening to the InputStream while connected
			while (true) {
				try {
					// Read from the InputStream
					//int bytes = mmInStream.read(buffer);

					
					mmInStream.read(buffer);
					int bytes;
					
					
//					System.out.println("IN L:--" + inputStream.available());
					String temp = "";
					for (byte by : buffer) {

						if (by == 0)
							break;
						else
							temp = temp + (char) by;

					}

					
					
					if(temp.startsWith("%"))
					{

						Log.e("Message Receive---",temp);
						
						String msg = temp.substring(1);

						SMSReceiver sms=new SMSReceiver();
						String mobileNumber=sms.getnumber();
						Log.e("Mobile nmner---",mobileNumber);
						SmsManager smsManager = SmsManager.getDefault();
						smsManager.sendTextMessage(mobileNumber, null, msg, null, null);

					}
					else
					
					{
						bytes = Integer.parseInt(temp);
						mHandler.obtainMessage(RemoteBluetooth.MESSAGE_READ, bytes,
							-1, buffer).sendToTarget();
					}
				} catch (IOException e) {
					Log.e(TAG, "disconnected", e);
					connectionLost();
					break;
				}
			}
		}

		/**
		 * Write to the connected OutStream.
		 * 
		 * @param buffer
		 *            The bytes to write
		 */
		public void write(byte[] buffer) {
			try {
				mmOutStream.write(buffer);

				// Share the sent message back to the UI Activity
				// mHandler.obtainMessage(BluetoothChat.MESSAGE_WRITE, -1, -1,
				// buffer)
				// .sendToTarget();
			} catch (IOException e) {
				Log.e(TAG, "Exception during write", e);
			}
		}
		
		

		public void write(int out) {
			try {
				mmOutStream.write(out);

				// Share the sent message back to the UI Activity
				// mHandler.obtainMessage(BluetoothChat.MESSAGE_WRITE, -1, -1,
				// buffer)
				// .sendToTarget();
			} catch (IOException e) {
				Log.e(TAG, "Exception during write", e);
			}
		}

		public void cancel() {
			try {
				mmOutStream.write(EXIT_CMD);
				mmSocket.close();
			} catch (IOException e) {
				Log.e(TAG, "close() of connect socket failed", e);
			}
		}
		
	}
}