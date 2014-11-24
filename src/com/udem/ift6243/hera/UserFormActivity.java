package com.udem.ift6243.hera;


import com.udem.ift6243.model.User;


import com.udem.ift6243.dao.UserDao;
import android.app.Activity;
import android.widget.Button;
import android.widget.EditText;
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
		Integer expression = 0;
		
		//Age
		age = spinner1.getSelectedItemPosition();
		
		// maritalStatus 
		maritalStatus = spinner2.getSelectedItemPosition()+1;
		
		//professionalStatus		
		professionalStatus = spinner3.getSelectedItemPosition() +1;
		
		
		//sport
		if (spinner4.getSelectedItemPosition() == 0)
			sport = true;
		
		//meditation	
		if (spinner5.getSelectedItemPosition()== 0)
			meditation = true;
			
		//expression
		expression = spinner6.getSelectedItemPosition();
		
		User One = new User(null, firstName, lastName, gender, age, maritalStatus, professionalStatus, sport, meditation, expression);
		UserDao user = new UserDao(this);
		user.createUser(One);
		//Intent inten = new Intent(getApplicationContext(),showUser.class);
  	    //startActivity(inten);
		//Log.e("user", String.valueOf(One.getAge()));
	}
    
    
 

}
