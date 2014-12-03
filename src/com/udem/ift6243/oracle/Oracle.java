package com.udem.ift6243.oracle;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.util.SparseArray;

import com.udem.ift6243.dao.HeraContextDao;
import com.udem.ift6243.dao.SolutionDao;
import com.udem.ift6243.dao.SolutionProposedDao;
import com.udem.ift6243.factory.HeraContextFactory;
import com.udem.ift6243.hera.NotificationReceiverActivity;
import com.udem.ift6243.hera.PaulActivity;
import com.udem.ift6243.hera.R;
import com.udem.ift6243.model.HeraContext;
import com.udem.ift6243.model.Solution;
import com.udem.ift6243.model.SolutionProposed;
import com.udem.ift6243.sensor.ReadEdaTask;
import com.udem.ift6243.utility.Constant;

public final class Oracle
{
    private static volatile Oracle instance = null;
    private Context context;
    private Activity activity;
    private int messageNb = 0;
    private boolean isRunning = false;
    private ArrayList<Integer> proposedSolutionList;
    private ReadEdaTask edaTask;
    
    /**
     * Constructeur de l'objet.
     */
    private Oracle() {
        super();
        proposedSolutionList = new ArrayList<Integer>();
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
    public void start(final ReadEdaTask edaTask)
    {
    	this.edaTask = edaTask;
    }
    
    public void notifyUser(Integer stressLevel)
    {
    	if(!this.isRunning)
    	{
	    	this.isRunning = true;
	    	
	    	Solution solution = Oracle.instance.findSolution(stressLevel);
	    	
	    	if(solution != null)
	    	{
	    		NotificationCompat.Builder  mBuilder = 
				  new NotificationCompat.Builder(Oracle.instance.context);
				  
//				PendingIntent pIntent = PendingIntent.getActivity(Oracle.instance.context, 0, 
//						Oracle.instance.activity.getIntent(), PendingIntent.FLAG_CANCEL_CURRENT);
				
				  mBuilder.setSmallIcon(R.drawable.ic_launcher);
				  mBuilder.setContentTitle("Hera");
				  mBuilder.setContentText("Vous etes stresse");
				  mBuilder.setTicker("Stress detecte");
				  
//				  Uri uri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
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
						  stackBuilder.getPendingIntent( 0, PendingIntent.FLAG_UPDATE_CURRENT );
				
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
    	
    	if(state.equals(Constant.STATE_ACCEPTED))
    	{
    		saveSolution(currentSolution, Constant.BONUS_ACCEPTED, state);
    	}
    	else if(state.equals(Constant.STATE_REFUSED))
    	{
    		saveSolution(currentSolution, Constant.BONUS_REFUSED, state);
    		
    		newSolution = Oracle.instance.findSolution(state);
    	}
    	else if(state.equals(Constant.STATE_TERMINATED))
    	{
    		Integer currentStressLevel = this.edaTask.getStressLevel();
    		if(currentStressLevel.equals(Constant.STRESS_LEVEL_NEGATIVE_OR_CONSTANT)) // succeed
    		{
        		saveSolution(currentSolution, Constant.BONUS_SUCCEED, state);
    			reset();
    		}
    		else // failed
    		{
        		saveSolution(currentSolution, Constant.BONUS_FAILED, state);
        		
        		newSolution = Oracle.instance.findSolution(state);
    		}
    	}
    	else
    	{
    		throw new IllegalArgumentException("Wrong state");
    	}
    	
    	return newSolution;
    }
    
    /**
     * Reset Oracle
     */
    public void reset()
    {
    	this.proposedSolutionList.clear();
    	this.isRunning = false;
    }
    
    private Solution findSolution(Integer stressLevel)
    {
    	Solution solutionSelected = null;
    	
    	// Recuperation de toutes les solutions
    	SolutionDao solutionDao = new SolutionDao(Oracle.instance.context);
    	ArrayList<Solution> solutionList = solutionDao.getSolution();
    	
    	// Creation du contexte courant
    	HeraContext currentHeraContext = null;
		try {
			currentHeraContext = HeraContextFactory.fromContext(this.context, stressLevel);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		HeraContext similarHeraContext = null;
		if(currentHeraContext != null)
		{
			HeraContextDao heraContextDao = new HeraContextDao(this.context);
			similarHeraContext = heraContextDao.getSimilarHeraContext(currentHeraContext);
		}
		
		if(similarHeraContext != null)
		{
	    	// Recuperation des solutions proposees dans ce meme contexte
			SolutionProposedDao solutionProposedDao = new SolutionProposedDao(this.context);
	    	ArrayList<SolutionProposed> solutionProposedList = solutionProposedDao
	    			.getSolutionProposedFromHeraContext(similarHeraContext.getId());
	    	
	    	SparseArray<SolutionProposed> solutionProposedMapBySolutionId = new SparseArray<SolutionProposed>();
	    	for(SolutionProposed solutionProposed : solutionProposedList)
	    	{
	    		solutionProposedMapBySolutionId.put(solutionProposed.getSolutionId().intValue(), solutionProposed);
	    	}
	    	
	    	// Mise a jour des priorites
	    	SolutionProposed solutionProposed = null;
	    	double bonus = 0;
	    	for(Solution solution : solutionList)
			{
	    		if(solutionProposedMapBySolutionId.indexOfKey(solution.getId().intValue()) >= 0)
	    		{
	    			solutionProposed = solutionProposedMapBySolutionId.get(solution.getId().intValue());
	    			bonus = solutionProposed.getBonus().doubleValue();
	    			if(bonus > 0)
	    			{
	    				solution.increasePriority(bonus);
	    			}
	    			else if(bonus < 0)
	    			{
	    				solution.decreasePriority(Math.abs(bonus));
	    			}
	    		}
			}
		}
		
    	// Choix de la solution
    	do
    	{
			// Meilleure solution
    		int maxPriority = 0;
    		for(Solution solution : solutionList)
    		{
//    			Log.e("Solution" + String.valueOf(solution.getId()), String.valueOf(solution.getPriority()));
    			int currentPriority = solution.getPriority().intValue();
    			if(currentPriority >= maxPriority)
    			{
    				solutionSelected = solution;
    				
    				maxPriority = currentPriority;
    			}
    		}
    		
    	} while(proposedSolutionList.contains(solutionSelected.getId()) 
    			&& solutionList.size() > proposedSolutionList.size());
    	
    	// Ajout a la liste des solutions deja proposees
    	proposedSolutionList.add(solutionSelected.getId());
    	
    	return solutionSelected;
    }
    
    private void saveSolution(Solution solution, Double bonus, Integer stressLevel)
    {
    	// Create HeraContext
    	HeraContext heraContext = null;
		try
		{
			heraContext = HeraContextFactory.fromContext(this.context, stressLevel);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
    	
    	// Save heraContext in db
    	HeraContextDao heraContextDao = new HeraContextDao(this.context);
    	heraContextDao.createHeraContext(heraContext);
    	
    	// Create SolutionProposed
    	Calendar currentDate = Calendar.getInstance();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.FRANCE);
		String date = sdf.format(currentDate.getTime());
		
    	SolutionProposed solutionProposed = new SolutionProposed(null, 
    			heraContext.getId(), solution.getId(), bonus, date);
    	
    	// Save solution proposed
    	SolutionProposedDao solutionProposedDao = new SolutionProposedDao(this.context);
    	solutionProposedDao.createSolutionProposed(solutionProposed);
    }
}
