package com.remote.control.computer;

import com.remote.control.computer.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;

public class MediaPlayer extends Activity {

	String[] web = { "Media Player",

	"KM Player", "VLC Player", "Gom Player" };

	int[] imageId = { R.drawable.windows_media_player, R.drawable.km_player,
			R.drawable.vlc_player, R.drawable.gom_player };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.media_player);

		MediaCustomGrid adapter = new MediaCustomGrid(MediaPlayer.this, web,
				imageId);

		GridView mediaGridview = (GridView) findViewById(R.id.mediaGridView);

		mediaGridview.setAdapter(adapter);

		mediaGridview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {

				switch (position) {
				case 0:
					Intent intent0 = new Intent(MediaPlayer.this,
							WindowsMediaPlayer.class);
					startActivity(intent0);
					break;
				case 1:
					Intent intent1 = new Intent(MediaPlayer.this,
							KmPlayer.class);
					startActivity(intent1);
					break;
				case 2:
					Intent intent2 = new Intent(MediaPlayer.this,
							VlcPlayer.class);
					startActivity(intent2);
					break;
				case 3:
					Intent intent3 = new Intent(MediaPlayer.this,
							GomPlayer.class);
					startActivity(intent3);
					break;

				default:
					break;
				}
			}
		});
	}

}
