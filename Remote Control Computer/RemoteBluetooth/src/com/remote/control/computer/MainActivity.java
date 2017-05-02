package com.remote.control.computer;

import com.remote.control.computer.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore.Audio.Media;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class MainActivity extends Activity {

	String[] web = { "Connection",

	"PowerPoint", "Media Player", "Power Option", "Keyboard" };

	int[] imageId = { R.drawable.connection, R.drawable.power_point,
			R.drawable.media_player, R.drawable.power_option, R.drawable.keyboard };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		CustomGrid adapter = new CustomGrid(MainActivity.this, web, imageId);

		GridView gridview = (GridView) findViewById(R.id.gridview);

		gridview.setAdapter(adapter);

		gridview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				// Toast.makeText(MainActivity.this, "" + position,
				// Toast.LENGTH_SHORT).show();
				switch (position) {
				case 0:
					Intent in0 = new Intent(MainActivity.this,
							RemoteBluetooth.class);
					startActivity(in0);
					break;
				case 1:
					Intent in1 = new Intent(MainActivity.this, PowerPoint.class);
					startActivity(in1);
					break;
				case 2:
					Intent in2 = new Intent(MainActivity.this,
							MediaPlayer.class);
					startActivity(in2);
					break;
				case 3:
					Intent in3 = new Intent(MainActivity.this,
							PowerOption.class);
					startActivity(in3);
					break;
					
				case 4:
					Intent in4 = new Intent(MainActivity.this,
							Keyboard.class);
					startActivity(in4);
					break;	

				default:
					break;
				}

			}
		});
	}

}
