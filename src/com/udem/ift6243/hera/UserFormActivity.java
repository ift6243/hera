package com.udem.ift6243.hera;

import java.util.ArrayList;


import com.udem.ift6243.model.Solution;
import com.udem.ift6243.dao.SolutionDao;
import com.udem.ift6243.dao.UserDao;
import com.udem.ift6243.model.User;
import com.udem.ift6243.utility.Constant;




import android.app.Activity;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.util.Log;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.Toast;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class UserFormActivity extends Activity implements OnClickListener {
	
	private Spinner spinner1, spinner2, spinner3, spinner4, spinner5, spinner6;
	private EditText editText1,editText2;
	private RadioButton radioButton;
	private Button btnSubmit;
	  
	// get the selected dropdown list value
	    public void addListenerOnButton() {
	    	
	    	editText1 = (EditText) findViewById(R.id.editText1);
	    	editText2 = (EditText) findViewById(R.id.editText2);
	    	spinner1 = (Spinner) findViewById(R.id.spinner1);
		  	spinner2 = (Spinner) findViewById(R.id.spinner2);
		  	spinner3 = (Spinner) findViewById(R.id.spinner3);
		  	spinner4 = (Spinner) findViewById(R.id.spinner4);
		  	spinner5 = (Spinner) findViewById(R.id.spinner5);
		  	spinner6 = (Spinner) findViewById(R.id.spinner6);
		  	radioButton = (RadioButton) findViewById(R.id.radio_W);
		  	btnSubmit = (Button) findViewById(R.id.btnSubmit);
		  	btnSubmit.setOnClickListener(this);
	   
	    }
	    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_user_form);
        addListenerOnButton();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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


    @Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
    	//la valeur du bouton radio à récuperer
		Toast.makeText(UserFormActivity.this,
		  		"OnClickListener : " + 
		                  "\nSpinner 1 : "+ String.valueOf(spinner1.getSelectedItem()) + 
		                  "\nSpinner 2 : "+ String.valueOf(spinner2.getSelectedItem())+ 
		                  "\nSpinner 3 : "+ String.valueOf(spinner3.getSelectedItem())+ 
		                  "\nSpinner 4 : "+ String.valueOf(spinner4.getSelectedItem())+ 
		                  "\nSpinner 5 : "+ String.valueOf(spinner5.getSelectedItem())+ 
		                  "\nSpinner 6 : "+ String.valueOf(spinner6.getSelectedItem()),
		  			Toast.LENGTH_SHORT).show();
		
		//Traduire les elements string spinner en entiers
		String firstName= String.valueOf(editText1.getText());
		String lastName = String.valueOf(editText2.getText());
		Integer gender=0;
		Integer age = 0;
		Integer maritalStatus = 0;
		Integer professionalStatus = 0;
		Boolean sport = false;
		Boolean meditation = false;
		Boolean woman = false;
		Integer expression = 0;
		
		//Récupérer la base de solution
		SolutionDao solutionDao = new SolutionDao(this);
		ArrayList<Solution> solutionList = solutionDao.getSolution();
		
		//gender
		woman = radioButton.isChecked();
		gender = woman ? Constant.GENDER_FEMALE : Constant.GENDER_MALE;
		
		//Cas des femmes ménopausées : a partir de 70 = sport doux
		if(woman == true && spinner1.getSelectedItemPosition()>4 && spinner1.getSelectedItemPosition()<8){
			Solution solutionSport = solutionList.get(0);
			solutionSport.increasePriority(0.1);
		}
		
		//Age
		if(spinner1.getSelectedItemPosition()==0) {
			age = Constant.AGE_0_9;
			Solution solutionSport = solutionList.get(0);
			solutionSport.increasePriority(0.1);
		}
		if(spinner1.getSelectedItemPosition()==1){
			age = Constant.AGE_10_19;
			Solution solutionSport = solutionList.get(0);
			solutionSport.increasePriority(0.1);
		}
		if(spinner1.getSelectedItemPosition()==2){
			age = Constant.AGE_20_29;
			Solution solutionSport = solutionList.get(0);
			solutionSport.increasePriority(0.1);
		}
		if(spinner1.getSelectedItemPosition()==3){
			age = Constant.AGE_30_39;
			Solution solutionSport = solutionList.get(0);
			solutionSport.increasePriority(0.1);
		}
		if(spinner1.getSelectedItemPosition()==4){
			age = Constant.AGE_40_49;
			Solution solutionSport = solutionList.get(0);
			solutionSport.increasePriority(0.1);
		}
		if(spinner1.getSelectedItemPosition()==5){
			age = Constant.AGE_50_59;
			Solution solutionSport = solutionList.get(0);
			solutionSport.increasePriority(0.1);
			Solution solutionMedicale = solutionList.get(2);
			solutionMedicale.increasePriority(0.1);
		}
		if(spinner1.getSelectedItemPosition()==6){
			age = Constant.AGE_60_69;
			Solution solutionSport = solutionList.get(0);
			solutionSport.decreasePriority(0.1);
			Solution solutionMedicale = solutionList.get(2);
			solutionMedicale.increasePriority(0.1);
		}
		if(spinner1.getSelectedItemPosition()==7){
			age = Constant.AGE_70_79;
			Solution solutionSport = solutionList.get(0);
			solutionSport.decreasePriority(0.2);
			Solution solutionMedicale = solutionList.get(2);
			solutionMedicale.increasePriority(0.2);
		}
		if(spinner1.getSelectedItemPosition()==8){
			age = Constant.AGE_80_89;
			Solution solutionSport = solutionList.get(0);
			solutionSport.decreasePriority(0.3);
			Solution solutionMedicale = solutionList.get(2);
			solutionMedicale.increasePriority(0.2);
		}
		if(spinner1.getSelectedItemPosition()==9){
			age = Constant.AGE_90_99;
			Solution solution = solutionList.get(0);
			solution.decreasePriority(0.3);
			Solution solutionMedicale = solutionList.get(2);
			solutionMedicale.increasePriority(0.3);
		}
		if(spinner1.getSelectedItemPosition()==10){
			age = Constant.AGE_100;
			Solution solutionSport = solutionList.get(0);
			solutionSport.decreasePriority(0.4);
			Solution solutionMedicale = solutionList.get(2);
			solutionMedicale.increasePriority(0.3);
			//Log.e("test position 10 : ", String.valueOf(spinner1.getSelectedItemPosition()));
		}
	
		// maritalStatus 
		if(spinner2.getSelectedItemPosition()==0){
			maritalStatus = Constant.SINGLE;
			Solution solutionSport = solutionList.get(0);
			solutionSport.increasePriority(0.2);
			
		}
		if(spinner2.getSelectedItemPosition()==1){
			maritalStatus = Constant.MARRIED;
			Solution solutionSport = solutionList.get(0);
			solutionSport.increasePriority(0.2);
		}
		if(spinner2.getSelectedItemPosition()==2){
			maritalStatus = Constant.DIVORCED;
			Solution solutionSport = solutionList.get(0);
			solutionSport.increasePriority(0.2);
		}
		
		//professionalStatus
		if(spinner3.getSelectedItemPosition()==0){
			professionalStatus = Constant.CAT_1;//agro
			Solution solutionSport = solutionList.get(0);
			solutionSport.increasePriority(0.1);
			Solution solutionActionRepos = solutionList.get(1);
			solutionActionRepos.increasePriority(0.2);
			
		}
		if(spinner3.getSelectedItemPosition()==1){
			professionalStatus = Constant.CAT_2;//artisans, chefs d'entreprise stressés
			Solution solutionSport = solutionList.get(0);
			solutionSport.increasePriority(0.1);
			Solution solutionActionRepos = solutionList.get(1);
			solutionActionRepos.increasePriority(0.2);
		}
		if(spinner3.getSelectedItemPosition()==2){
			professionalStatus = Constant.CAT_3;//cadres stressés
			Solution solutionSport = solutionList.get(0);
			solutionSport.increasePriority(0.1);
			Solution solutionActionRepos = solutionList.get(1);
			solutionActionRepos.increasePriority(0.2);
		}
		if(spinner3.getSelectedItemPosition()==3){
			professionalStatus = Constant.CAT_4;//professions intermédiaires stressés
			Solution solutionSport = solutionList.get(0);
			solutionSport.increasePriority(0.1);
			Solution solutionActionRepos = solutionList.get(1);
			solutionActionRepos.increasePriority(0.2);
		}
		if(spinner3.getSelectedItemPosition()==4){
			professionalStatus = Constant.CAT_5;//employés très stressés
			Solution solutionSport= solutionList.get(0);
			solutionSport.increasePriority(0.2);
			Solution solutionActionRepos = solutionList.get(1);
			solutionActionRepos.increasePriority(0.3);
		}
		if(spinner3.getSelectedItemPosition()==5){
			professionalStatus = Constant.CAT_6;//ouvriers fatigués très stressés
			Solution solutionSport= solutionList.get(0);
			solutionSport.decreasePriority(0.2);
			Solution solutionActionRepos = solutionList.get(1);
			solutionActionRepos.increasePriority(0.3);
		}
		if(spinner3.getSelectedItemPosition()==6){
			professionalStatus = Constant.CAT_7;//retraités fatigués
			Solution solutionSport= solutionList.get(0);
			solutionSport.decreasePriority(0.2);
			Solution solutionActionRepos = solutionList.get(1);
			solutionActionRepos.increasePriority(0.1);
		}
		if(spinner3.getSelectedItemPosition()==7){
			professionalStatus = Constant.CAT_8;//sans emplois
			Solution solutionSport= solutionList.get(0);
			solutionSport.increasePriority(0.2);
		}
		if(spinner3.getSelectedItemPosition()==8){
			professionalStatus = Constant.CAT_9;//étudiants
			Solution solutionSport= solutionList.get(0);
			solutionSport.increasePriority(0.2);
			Solution solutionActionRepos = solutionList.get(1);
			solutionActionRepos.increasePriority(0.1);
			
		}
		
		
		//sport
		if (spinner4.getSelectedItemPosition()== 0){
			sport = true;
			Solution solutionSport= solutionList.get(0);
			solutionSport.increasePriority(0.2);
		}else{
			Solution solutionSport= solutionList.get(0);
			solutionSport.decreasePriority(0.2);
			//Log.e("test sport", String.valueOf(sport));
		}
		//Log.e("test priority sport", String.valueOf(solutionList.get(0).getPriority()));
		//meditation	
		if (spinner5.getSelectedItemPosition()== 0){
			meditation = true;
			Solution solution = solutionList.get(4);//multimédia
			solution.increasePriority(0.2);
		}else{//if false
			Solution solution = solutionList.get(4);
			solution.decreasePriority(0.2);
		}
			
		//expression
		if(spinner6.getSelectedItemPosition()==0){
			expression = Constant.READING;
			Solution solution = solutionList.get(3);//créativité
			solution.increasePriority(0.2);
		}
		if(spinner6.getSelectedItemPosition()==1){
			expression = Constant.WRITING;
			Solution solution = solutionList.get(3);
			solution.increasePriority(0.2);
		}
		if(spinner6.getSelectedItemPosition()==2){
			expression = Constant.DRAWING;
			Solution solution = solutionList.get(3);
			solution.increasePriority(0.2);
		}
		if(spinner6.getSelectedItemPosition()==3){
			expression = Constant.SINGING;
			Solution solution = solutionList.get(3);
			solution.increasePriority(0.2);
		}
		if(spinner6.getSelectedItemPosition()==4){
			expression = Constant.DANCING;
			Solution solution3 = solutionList.get(3);//créativité
			solution3.increasePriority(0.2);
			Log.e("List.get(3)", solution3.getName());
			Solution sol0 = solutionList.get(0); //sport
			sol0.increasePriority(0.1);
		}
		
		
		User userOne = new User(null, firstName, lastName, gender, age, maritalStatus, professionalStatus, sport, meditation, expression);
		UserDao user = new UserDao(this);
		user.createUser(userOne);
		
		Intent intent = new Intent(UserFormActivity.this, WaitingActivity.class);
		startActivity(intent);
		
	}
    
    
 

}
