package com.remote.control.computer;

import com.remote.control.computer.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class PowerOption extends Activity {

	Button shutDownButton,reStartButton,logOffButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.power_option);
		shutDownButton=(Button)findViewById(R.id.btnShutdown);
		reStartButton=(Button)findViewById(R.id.btnRestart);
		logOffButton=(Button)findViewById(R.id.btnLogOff);
		
		shutDownButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				 String shutdown = String.valueOf(BluetoothCommandService.SHUT_DOWN);
	    			byte[] shutdownTopByte = shutdown.getBytes(); 
	    			RemoteBluetooth.mCommandService.write(shutdownTopByte);
//				RemoteBluetooth.mCommandService.write(BluetoothCommandService.SHUT_DOWN);
				Toast.makeText(getApplicationContext(), "Shutting Down", Toast.LENGTH_LONG).show();
				
			}
		});
		
		reStartButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				String reStart = String.valueOf(BluetoothCommandService.Restart);
    			byte[] reStartTopByte = reStart.getBytes(); 
    			RemoteBluetooth.mCommandService.write(reStartTopByte);
				
//				RemoteBluetooth.mCommandService.write(BluetoothCommandService.Restart);
				Toast.makeText(getApplicationContext(), "Reastarting....", Toast.LENGTH_LONG).show();
				
			}
		});
		
		logOffButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String logOff=String.valueOf(BluetoothCommandService.LOG_OFF);
				byte[] logOffTOByte=logOff.getBytes();
				RemoteBluetooth.mCommandService.write(logOffTOByte);
				
//				RemoteBluetooth.mCommandService.write(BluetoothCommandService.LOG_OFF);
				Toast.makeText(getApplicationContext(), "Loging off...", Toast.LENGTH_LONG).show();
				
			}
		});
	}

	

}
