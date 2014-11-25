package com.udem.ift6243.hera;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.media.RingtoneManager;
import android.net.Uri;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;

@SuppressLint("NewApi")
public class NotificationActivity extends Activity {
	   private NotificationManager mNotificationManager;
	   private int notificationID = 4;
	   private int numMessages = 0;
	   
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notification);
		
	     Button startBtn = (Button) findViewById(R.id.start);
	      startBtn.setOnClickListener(new View.OnClickListener() {
	         public void onClick(View view) {
	            displayNotification();
	         }
	      });

	//      Button cancelBtn = (Button) findViewById(R.id.cancel);
	//     cancelBtn.setOnClickListener(new View.OnClickListener() {
	//         public void onClick(View view) {
	//           // cancelNotification();
	//         }
	//      });
	   
//	      Button updateBtn = (Button) findViewById(R.id.update);
//	      updateBtn.setOnClickListener(new View.OnClickListener() {
//	         public void onClick(View view) {
//	           // updateNotification();
//	         }
//	      });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.notification, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	@SuppressLint("NewApi")
	protected void displayNotification() {
	      Log.i("Start", "notification");

	      /* Invoking the default notification service */
	      NotificationCompat.Builder  mBuilder = 
	      new NotificationCompat.Builder(this);	
	      //Intent intent = new Intent(MainActivity.this, NotificationView.class); 
	      // pass your values and retrieve them in the other Activity using keyName
	      //startActivity(intent);
	      
	      PendingIntent pIntent = PendingIntent.getActivity(NotificationActivity.this, 0, getIntent(), PendingIntent.FLAG_CANCEL_CURRENT);

	      mBuilder.setSmallIcon(R.drawable.ic_launcher);
	      mBuilder.setContentTitle("Héra");
	      mBuilder.setContentText("Vous etes stressé");
	      
	      mBuilder.setTicker("Stress détecté"); 
	      //Buttons below the notifications
	      /*mBuilder.addAction(R.drawable.ic_launcher, "Voir", pIntent);
	      mBuilder.addAction(0, "Plus tard", pIntent);*/
	      
	      Uri uri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
	      mBuilder.setSound(Uri.parse("android.resource://"+getPackageName()+"/"+ R.raw.cristal));      
	      
	      /*Sound linked to the notification default sound for the android device
	      Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
	      mBuilder.setSound(alarmSound); */
	      
	      /* Increase notification number every time a new notification arrives */
	      mBuilder.setNumber(++numMessages);
	      
	      /* Creates an explicit intent for an Activity in your app */
	      Intent resultIntent = new Intent(this, NotificationReceiverActivity.class);
	      
	      //String s = "solution";
	      //resultIntent.putExtra("solution", s);
	      
	      Bundle dataBundle = new Bundle();
	      dataBundle.putInt("notificationID",(int)notificationID);
	      
		  resultIntent.putExtras(dataBundle);
		  
		  
	      TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
	      stackBuilder.addParentStack(NotificationReceiverActivity.class);

	      /* Adds the Intent that starts the Activity to the top of the stack */
	      stackBuilder.addNextIntent(resultIntent);
	      PendingIntent resultPendingIntent =
	         stackBuilder.getPendingIntent(
	            0,
	            PendingIntent.FLAG_UPDATE_CURRENT
	         );

	      mBuilder.setContentIntent(resultPendingIntent);

	      mNotificationManager =
	      (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

	      /* notificationID allows you to update the notification later on. */
	      mNotificationManager.notify(notificationID, mBuilder.build());
	   }
	/* cancel and update notifications functions */
	   
	//   protected void cancelNotification() {
//	      Log.i("Cancel", "notification");//
//	      mNotificationManager.cancel(notificationID);
	//   }

	//   protected void updateNotification() {
//	      Log.i("Update", "notification"); 
	//
//	      /* Invoking the default notification service */
//	      NotificationCompat.Builder  mBuilder = 
//	      new NotificationCompat.Builder(this);	
	      
//	      mBuilder.setContentTitle("Updated Message");
//	      mBuilder.setContentText("You've got updated message.");
//	      mBuilder.setTicker("Updated Message Alert!");
//	      mBuilder.setSmallIcon(R.drawable.ic_launcher);
	      

	     /* Increase notification number every time a new notification arrives */
	 //     mBuilder.setNumber(++numMessages);
	      
	      /* Creates an explicit intent for an Activity in your app */
//	      Intent resultIntent = new Intent(this, NotificationView.class);

//	      TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
//	      stackBuilder.addParentStack(NotificationView.class);

	      /* Adds the Intent that starts the Activity to the top of the stack */
//	      stackBuilder.addNextIntent(resultIntent);
//	      PendingIntent resultPendingIntent =
//	         stackBuilder.getPendingIntent(
//	            0,
//	            PendingIntent.FLAG_UPDATE_CURRENT
//	       );

//	      mBuilder.setContentIntent(resultPendingIntent);

//	      mNotificationManager =
//	      (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

	      /* Update the existing notification using same notification ID */
//	      mNotificationManager.notify(notificationID, mBuilder.build());
	//   } 
}
