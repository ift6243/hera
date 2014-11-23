package com.udem.ift6243.oracle;

import java.util.ArrayList;
import java.util.Random;

import android.content.Context;

import com.udem.ift6243.dao.SolutionDao;
import com.udem.ift6243.model.Solution;

public final class Oracle
{
    private static volatile Oracle instance = null;
    private static Context context;
    
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
    public static void setContext(Context context)
    {
    	Oracle.context = context;
    }
    
    /**
     * Demarrage de l'Oracle
     */
    public static void start()
    {
    	Solution solution = Oracle.findSolution();
    	
    	if(solution != null)
    	{
    		//// LANCEMENT DE LA NOTIFICATION
    	}
    }
    
    private static Solution findSolution()
    {
    	Solution solution = null;
    	
    	SolutionDao solutionDao = new SolutionDao(Oracle.context);
    	ArrayList<Solution> solutionList = solutionDao.getSolution();
    	
    	
    	Random r = new Random();
    	int selectedIndex = r.nextInt(solutionList.size());
    	solution = solutionList.get(selectedIndex);
    	
    	return solution;
    }
}
