package com.remote.control.computer;

import android.R.string;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class PowerPoint extends Activity {

	public RelativeLayout mainLayout;
	private Button slide_show;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.power_point);

	   slide_show = (Button)findViewById(R.id.btn_slide_show); 
	   slide_show.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			//RemoteBluetooth.mCommandService.write(BluetoothCommandService.SlideShow);
			
		String f5 = String.valueOf(BluetoothCommandService.SlideShow);
		
			byte[] f5byte = f5.getBytes();
			RemoteBluetooth.mCommandService.write(f5byte);
			
			
		}
	});
		mainLayout = (RelativeLayout)findViewById(R.id.powerpoint_layout);
		
		mainLayout.setOnTouchListener(new OnSwipeTouchListener(PowerPoint.this) {
		    public void onSwipeTop() {
		       
		    	String swipeTop = String.valueOf(BluetoothCommandService.Top);
		    			byte[] swipeTopByte = swipeTop.getBytes(); 
		    			RemoteBluetooth.mCommandService.write(swipeTopByte);
		        //RemoteBluetooth.mCommandService.write(BluetoothCommandService.Top);
		        
		    }
		    public void onSwipeRight() {
		        
		        String swipeRight = String.valueOf(BluetoothCommandService.VOL_UP);
    			byte[] swipeTopByte = swipeRight.getBytes(); 
    			RemoteBluetooth.mCommandService.write(swipeTopByte);
//		        RemoteBluetooth.mCommandService.write(BluetoothCommandService.VOL_UP);
				
		    }
		    public void onSwipeLeft() {
		        
		        String swipeLeft = String.valueOf(BluetoothCommandService.VOL_DOWN);
    			byte[] swipeTopByte = swipeLeft.getBytes(); 
    			RemoteBluetooth.mCommandService.write(swipeTopByte);
//		        RemoteBluetooth.mCommandService.write(BluetoothCommandService.VOL_DOWN);
				
		    }
		    public void onSwipeBottom() {
		    	
		    	String swipeBottom = String.valueOf(BluetoothCommandService.Bottom);
    			byte[] swipeTopByte = swipeBottom.getBytes(); 
    			RemoteBluetooth.mCommandService.write(swipeTopByte);
//		    	RemoteBluetooth.mCommandService.write(BluetoothCommandService.Bottom);
		    }

		});
	
	}

	

	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
			
			String swipeRight = String.valueOf(BluetoothCommandService.VOL_UP);
			byte[] swipeTopByte = swipeRight.getBytes(); 
			RemoteBluetooth.mCommandService.write(swipeTopByte);
//			RemoteBluetooth.mCommandService.write(BluetoothCommandService.VOL_UP);
			return true;
		}
		else if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN){
			
			 String swipeLeft = String.valueOf(BluetoothCommandService.VOL_DOWN);
 			byte[] swipeTopByte = swipeLeft.getBytes(); 
 			RemoteBluetooth.mCommandService.write(swipeTopByte);
//			RemoteBluetooth.mCommandService.write(BluetoothCommandService.VOL_DOWN);
			return true;
		}

		
		return super.onKeyDown(keyCode, event);
	}
	
	
	
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

}
