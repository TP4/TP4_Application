package TP4.tp4_application_client;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class SecondaryActivity extends Activity{

	
	  ListView liste = null;
	    
	  @Override
 public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.main);
        
	    liste = (ListView) findViewById(R.id.);
	   List<String> exemple = new ArrayList<String>();
	    exemple.add("Item 1");
	    exemple.add("Item 2");
	    exemple.add("Item 3");
	        
	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, exemple);
	    liste.setAdapter(adapter);
	  }
	}
