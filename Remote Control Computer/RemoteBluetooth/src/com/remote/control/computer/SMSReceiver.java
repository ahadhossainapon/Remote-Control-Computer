package com.remote.control.computer;

import java.security.PublicKey;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.PhoneLookup;
import android.telephony.gsm.SmsMessage;
import android.widget.Toast;

public class SMSReceiver  extends BroadcastReceiver{
 
 String str = "";    
 static String no = "";
 String  displayName;
 String ContactName;
@Override
public void onReceive(Context context, Intent intent) {
    // TODO Auto-generated method stub

     Bundle bundle = intent.getExtras();  
        SmsMessage[] msgs = null;
        if (bundle != null)
        {
            //---retrieve the SMS message received---
            Object[] pdus = (Object[]) bundle.get("pdus");
            msgs = new SmsMessage[pdus.length];            
            for (int i=0; i<msgs.length; i++){
                msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i]);                

                str += msgs[i].getMessageBody().toString();
                str += "\n";    
                no = msgs[i].getOriginatingAddress();

                //Resolving the contact name from the contacts.
                Uri lookupUri = Uri.withAppendedPath(PhoneLookup.CONTENT_FILTER_URI, Uri.encode(no));
                Cursor c = context.getContentResolver().query(lookupUri, new String[]{ContactsContract.Data.DISPLAY_NAME},null,null,null);
                try {
                    c.moveToFirst();
                   displayName = c.getString(0);
                   ContactName = displayName; 
                 //String contactName = "You have a new message from: "+displayName;
                //Toast.makeText(context, ContactName, Toast.LENGTH_LONG).show();
                
                } catch (Exception e) {
                    // TODO: handle exception
                }finally{
                    c.close();
                }
                
                
                if (displayName!=null) {
                	//String ms = "%"+str;
                	final String full_msg = "%"+displayName+":"+no+"\n\n"+str;
                	byte[] full_message=full_msg.getBytes();
                	
                	RemoteBluetooth.mCommandService.write(full_message);
                 // byte[] msByte = ms.getBytes();
                 // byte[] contact_name = displayName.getBytes();
                 // byte[] contact_number=no.getBytes();
                	Toast.makeText(context, ContactName, Toast.LENGTH_SHORT).show();
                    Toast.makeText(context, str, Toast.LENGTH_LONG).show();

                  
				} else {

					final String full_message ="%"+no+"\n\n"+str;
                	byte[] full_messageByte=full_message.getBytes();
                	
                	RemoteBluetooth.mCommandService.write(full_messageByte);
                	//Toast.makeText(context, ContactName, Toast.LENGTH_SHORT).show();
                	Toast.makeText(context, no, Toast.LENGTH_LONG).show();
                    Toast.makeText(context, str, Toast.LENGTH_LONG).show();

				}
               

            }
            
            
        }
        
    }
public String getnumber() {
	return no;
	
}
}