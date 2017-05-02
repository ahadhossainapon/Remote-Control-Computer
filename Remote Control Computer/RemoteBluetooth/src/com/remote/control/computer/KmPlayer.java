package com.remote.control.computer;

import com.remote.control.computer.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class KmPlayer extends Activity {

	private Button buttonNext,buttonPrevious,buttonFirstForward,buttonFirstprevious,buttonVoloumUp,buttonVoloumDown,buttonPushStart;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.km_player);
		buttonNext=(Button)findViewById(R.id.btnNext);
		buttonPrevious=(Button)findViewById(R.id.btnPrevious);
		buttonFirstForward=(Button)findViewById(R.id.btnFirstForward);
		buttonFirstprevious=(Button)findViewById(R.id.btnFirstBackWard);
		buttonVoloumUp=(Button)findViewById(R.id.btnVoloumUp);
		buttonVoloumDown=(Button)findViewById(R.id.btnVoloumDown);
		buttonPushStart=(Button)findViewById(R.id.btnPushPop);
		
		buttonNext.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {

				String next=String.valueOf(BluetoothCommandService.BUTTON_NEXT);
				byte[] nextToByte = next.getBytes();
				RemoteBluetooth.mCommandService.write(nextToByte);
				
				
//				RemoteBluetooth.mCommandService.write(BluetoothCommandService.BUTTON_NEXT);
				
			}
		});
		
		buttonPrevious.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				String previous=String.valueOf(BluetoothCommandService.BUTTON_PREVIOUS);
				byte[] previousToByte=previous.getBytes();
				RemoteBluetooth.mCommandService.write(previousToByte);
				
//				RemoteBluetooth.mCommandService.write(BluetoothCommandService.BUTTON_PREVIOUS);
			}
		});
		
buttonFirstForward.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				String firstForward=String.valueOf(BluetoothCommandService.BUTTON_FIRSTFORWARD);
				byte[] firstForwardToByte=firstForward.getBytes();
				RemoteBluetooth.mCommandService.write(firstForwardToByte);
				
//				RemoteBluetooth.mCommandService.write(BluetoothCommandService.BUTTON_FIRSTFORWARD);
			}
		});

buttonFirstprevious.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		
		String firstPrevious=String.valueOf(BluetoothCommandService.BUTTON_FIRSTPREVIOUS);
		byte[] firstPreviousToByte=firstPrevious.getBytes();
		RemoteBluetooth.mCommandService.write(firstPreviousToByte);
		
//		RemoteBluetooth.mCommandService.write(BluetoothCommandService.BUTTON_FIRSTPREVIOUS);
	}
});

buttonVoloumUp.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		
		String voloum=String.valueOf(BluetoothCommandService.BUTTON_VOLOUM_UP);
		byte[] voloumToByte=voloum.getBytes();
		RemoteBluetooth.mCommandService.write(voloumToByte);
		
		
//		RemoteBluetooth.mCommandService.write(BluetoothCommandService.BUTTON_VOLOUM_UP);
	}
});
buttonVoloumDown.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		
		String voloumdown =String.valueOf(BluetoothCommandService.BUTTON_VOLOUM_DOWN);
		byte[] voloumdownToByte=voloumdown.getBytes();
		RemoteBluetooth.mCommandService.write(voloumdownToByte);
		
//		RemoteBluetooth.mCommandService.write(BluetoothCommandService.BUTTON_VOLOUM_DOWN);
	}
});
buttonPushStart.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		
		String pushStart =String.valueOf(BluetoothCommandService.BUTTON_PUSH_POP);
		byte[] pushStartToByte=pushStart.getBytes();
		RemoteBluetooth.mCommandService.write(pushStartToByte);
		
//		RemoteBluetooth.mCommandService.write(BluetoothCommandService.BUTTON_PUSH_POP);
	}
});
	}

	
}
