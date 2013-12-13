package TP4.tp4_application_client;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class ListActivity extends Activity{
	
    List<ActivityToAdd> activities = new ArrayList<ActivityToAdd>();
    List<String> activityNames = new ArrayList<String>();
	
	  ListView liste = null;
	    
	  @Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity2);
        
	    liste = (ListView) findViewById(R.id.listView1);
	    activities.add(new ActivityToAdd(0, "Babe"));
	    activityNames.add(activities.get(0).getActivityName());
	   
	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, activityNames);
	    liste.setAdapter(adapter);
	    
	    liste.setOnItemClickListener(new AdapterView.OnItemClickListener() {
	    	  @Override
	    	  public void onItemClick(AdapterView<?> adapterView, 
	    	    View view, 
	    	    int position,
	    	    long id) {
	    		  setContentView(R.layout.activity_details);
	    		  
	    		  TextView name = (TextView) findViewById(R.id.textViewName);
	  		    	name.setText(activities.get(position).getActivityName());
	    	  }	
	    	});

	}
}
