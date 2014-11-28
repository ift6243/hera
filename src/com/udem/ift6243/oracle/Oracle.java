package com.udem.ift6243.oracle;

import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

import com.udem.ift6243.dao.SolutionDao;
import com.udem.ift6243.hera.NotificationReceiverActivity;
import com.udem.ift6243.hera.PaulActivity;
import com.udem.ift6243.hera.R;
import com.udem.ift6243.model.Solution;

public final class Oracle
{
    private static volatile Oracle instance = null;
    private Context context;
    private Activity activity;
    private int messageNb = 0;
    private volatile boolean isRunning = false;
    
    /**
     * Constructeur de l'objet.
     */
    private Oracle() {
        super();
    }

    /**
     * Méthode permettant de renvoyer une instance de la classe Singleton
     * @return Retourne l'instance de l'oracle.
     */
    public final static Oracle getInstance() {
        if (Oracle.instance == null) {
           synchronized(Oracle.class) {
             if (Oracle.instance == null) {
            	 Oracle.instance = new Oracle();
             }
           }
        }
        return Oracle.instance;
    }
    
    /**
     * Initialize context
     * @param context
     */
    public void setContext(Context context)
    {
    	Oracle.instance.context = context;
    }
    
    /**
     * Initialize activity
     * @param activity
     */
    public void setActivity(Activity activity)
    {
    	Oracle.instance.activity = activity;
    }
    
    /**
     * Demarrage de l'Oracle
     */
    public void start()
    {
    	if(!this.isRunning)
    	{
	    	this.isRunning = true;
	    	
	    	Solution solution = Oracle.instance.findSolution();
	    	
	    	if(solution != null)
	    	{
	    		NotificationCompat.Builder  mBuilder = 
				  new NotificationCompat.Builder(Oracle.instance.context);
				  
				PendingIntent pIntent = PendingIntent.getActivity(Oracle.instance.context, 0, 
						Oracle.instance.activity.getIntent(), PendingIntent.FLAG_CANCEL_CURRENT);
				
				  mBuilder.setSmallIcon(R.drawable.ic_launcher);
				  mBuilder.setContentTitle("Hera");
				  mBuilder.setContentText("Vous etes stresse");
				  mBuilder.setTicker("Stress detecte");
				  
				  Uri uri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
				  mBuilder.setSound(Uri.parse("android.resource://" + PaulActivity.getApplicationPackageName() + "/"+ R.raw.cristal));
				  
				  /* Increase notification number every time a new notification arrives */
				  mBuilder.setNumber(++this.messageNb);
				  
				  /* Creates an explicit intent for an Activity in your app */
				  Intent resultIntent = new Intent(Oracle.instance.context, NotificationReceiverActivity.class);
				  
				  Bundle dataBundle = new Bundle();
				  dataBundle.putInt("notificationID", solution.getId().intValue());
				  resultIntent.putExtras(dataBundle);
				  
				  
				  TaskStackBuilder stackBuilder = TaskStackBuilder.create(Oracle.instance.context);
				  stackBuilder.addParentStack(NotificationReceiverActivity.class);
				
				  /* Adds the Intent that starts the Activity to the top of the stack */
				  stackBuilder.addNextIntent(resultIntent);
				  PendingIntent resultPendingIntent =
				     stackBuilder.getPendingIntent(
				        0,
				        PendingIntent.FLAG_UPDATE_CURRENT
				     );
				
				  mBuilder.setContentIntent(resultPendingIntent);
				
				  NotificationManager mNotificationManager =
						  (NotificationManager) Oracle.instance.context.getSystemService(Context.NOTIFICATION_SERVICE);
				
				  /* notificationID allows you to update the notification later on. */
				  mNotificationManager.notify(solution.getId().intValue(), mBuilder.build());
	    	}
    	}
    }
    
    public Solution feedback(Solution currentSolution, Integer state)
    {
    	Solution newSolution = null;
    	
    	return newSolution;
    }
    
    /**
     * Stop Oracle
     */
    public void stop()
    {
    	this.isRunning = false;
    }
    
    private Solution findSolution()
    {
    	Solution solution = null;
    	
    	SolutionDao solutionDao = new SolutionDao(Oracle.instance.context);
    	ArrayList<Solution> solutionList = solutionDao.getSolution();
    	
    	Random r = new Random();
    	int selectedIndex = r.nextInt(solutionList.size());
    	solution = solutionList.get(selectedIndex);
    	
    	return solution;
    }
}
