package com.remote.control.computer;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract.PhoneLookup;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class PhoneStatReceiver extends BroadcastReceiver {

	private static final String TAG = "PhoneStatReceiver";

	private static boolean incomingFlag = false;

	private static String incoming_number = null;
	public static String CallerName = null;

	@Override
	public void onReceive(Context context, Intent intent) {

		incomingFlag = true;

		incoming_number = intent.getStringExtra("incoming_number");
		String newPhoneState = intent
				.getStringExtra(TelephonyManager.EXTRA_STATE);

		TelephonyManager tm =

		(TelephonyManager) context.getSystemService(Service.TELEPHONY_SERVICE);

		switch (tm.getCallState()) {

		case TelephonyManager.CALL_STATE_RINGING:

			ContentResolver cr = context.getContentResolver();
			Uri uri = Uri.withAppendedPath(PhoneLookup.CONTENT_FILTER_URI,
					Uri.encode(incoming_number));
			Cursor cursor = cr
					.query(uri, new String[] { PhoneLookup.DISPLAY_NAME },
							null, null, null);

			if (cursor.moveToFirst()) {
				CallerName = cursor.getString(cursor
						.getColumnIndex(PhoneLookup.DISPLAY_NAME));
			}

			if (cursor != null && !cursor.isClosed()) {
				cursor.close();
			}

			if (CallerName != null
					&& newPhoneState
							.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
				// String ms = "%"+str;
				final String Name = "*" + CallerName + " (" + incoming_number
						+ ") is calling you...";
				byte[] NameByte = Name.getBytes();

				RemoteBluetooth.mCommandService.write(NameByte);
				String callname = CallerName + " is call you";
				Toast.makeText(context, callname, Toast.LENGTH_LONG).show();

			} else if (CallerName == null
					&& newPhoneState
							.equals(TelephonyManager.EXTRA_STATE_RINGING)) {

				final String Number = "*" + incoming_number
						+ " is calling you...";
				byte[] NumberByte = Number.getBytes();

				RemoteBluetooth.mCommandService.write(NumberByte);
				// Toast.makeText(context, ContactName,
				// Toast.LENGTH_SHORT).show();

				String callname = incoming_number + " is call you";
				Toast.makeText(context, callname, Toast.LENGTH_LONG).show();

			}

			CallerName = null;
			break;



		}

	}
}