package com.udem.ift6243.hera;


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
		Toast.makeText(UserFormActivity.this,
		  		"OnClickListener : " + 
		                  "\nSpinner 1 : "+ String.valueOf(spinner1.getSelectedItem()) + 
		                  "\nSpinner 2 : "+ String.valueOf(spinner2.getSelectedItem())+ 
		                  "\nSpinner 3 : "+ String.valueOf(spinner3.getSelectedItem())+ 
		                  "\nSpinner 4 : "+ String.valueOf(spinner4.getSelectedItem())+ 
		                  "\nSpinner 5 : "+ String.valueOf(spinner5.getSelectedItem())+ 
		                  "\nSpinner 6 : "+ String.valueOf(spinner6.getSelectedItem()),
		  			Toast.LENGTH_SHORT).show();
		
		
		//User One = new User(NULL,)
		
		//Traduire les elements string spinner en entiers
	
		
	}
    
 

}
