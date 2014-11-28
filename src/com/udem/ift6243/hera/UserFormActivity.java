package com.udem.ift6243.hera;

import com.udem.ift6243.model.User;
import com.udem.ift6243.oracle.Oracle;
import com.udem.ift6243.utility.Constant;


import com.udem.ift6243.dao.UserDao;
import android.app.Activity;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
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
		
		//gender
		woman = radioButton.isChecked();
		gender = woman ? Constant.GENDER_FEMALE : Constant.GENDER_MALE;
		
		//Age
		if(spinner1.getSelectedItemPosition()==0)
			age = Constant.AGE_0_9;
		if(spinner1.getSelectedItemPosition()==1)
			age = Constant.AGE_10_19;
		if(spinner1.getSelectedItemPosition()==2)
			age = Constant.AGE_20_29;
		if(spinner1.getSelectedItemPosition()==3)
			age = Constant.AGE_30_39;
		if(spinner1.getSelectedItemPosition()==4)
			age = Constant.AGE_40_49;
		if(spinner1.getSelectedItemPosition()==5)
			age = Constant.AGE_50_59;
		if(spinner1.getSelectedItemPosition()==6)
			age = Constant.AGE_60_69;
		if(spinner1.getSelectedItemPosition()==7)
			age = Constant.AGE_70_79;
		if(spinner1.getSelectedItemPosition()==8)
			age = Constant.AGE_80_89;
		if(spinner1.getSelectedItemPosition()==9)
			age = Constant.AGE_90_99;
		if(spinner1.getSelectedItemPosition()==10)
			age = Constant.AGE_100;
	
		// maritalStatus 
		if(spinner2.getSelectedItemPosition()==0)
			maritalStatus = Constant.SINGLE;
		if(spinner2.getSelectedItemPosition()==1)
			maritalStatus = Constant.MARRIED;
		if(spinner2.getSelectedItemPosition()==2)
			maritalStatus = Constant.DIVORCED;
		
		//professionalStatus
		if(spinner3.getSelectedItemPosition()==0)
			professionalStatus = Constant.CAT_1;
		if(spinner3.getSelectedItemPosition()==1)
			professionalStatus = Constant.CAT_2;
		if(spinner3.getSelectedItemPosition()==2)
			professionalStatus = Constant.CAT_3;
		if(spinner3.getSelectedItemPosition()==3)
			professionalStatus = Constant.CAT_4;
		if(spinner3.getSelectedItemPosition()==4)
			professionalStatus = Constant.CAT_4;
		if(spinner3.getSelectedItemPosition()==5)
			professionalStatus = Constant.CAT_5;
		if(spinner3.getSelectedItemPosition()==6)
			professionalStatus = Constant.CAT_6;
		if(spinner3.getSelectedItemPosition()==7)
			professionalStatus = Constant.CAT_7;
		if(spinner3.getSelectedItemPosition()==8)
			professionalStatus = Constant.CAT_8;
		if(spinner3.getSelectedItemPosition()==9)
			professionalStatus = Constant.CAT_9;
		
		//sport
		if (spinner4.getSelectedItemPosition()==0)
			sport = true;
		
		//meditation	
		if (spinner5.getSelectedItemPosition()==0)
			meditation = true;
			
		//expression
		if(spinner6.getSelectedItemPosition()==0)
			expression = Constant.READING;
		if(spinner6.getSelectedItemPosition()==1)
			expression = Constant.WRITING;
		if(spinner6.getSelectedItemPosition()==2)
			expression = Constant.DRAWING;
		if(spinner6.getSelectedItemPosition()==3)
			expression = Constant.SINGING;
		if(spinner6.getSelectedItemPosition()==4)
			expression = Constant.DANCING;
		
		User userOne = new User(null, firstName, lastName, gender, age, maritalStatus, professionalStatus, sport, meditation, expression);
		UserDao user = new UserDao(this);
		user.createUser(userOne);
		
		Intent intent = new Intent(UserFormActivity.this, WaitingActivity.class);
		startActivity(intent);
		
	}
    
    
 

}
