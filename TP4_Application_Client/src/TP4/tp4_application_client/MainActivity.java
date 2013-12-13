package TP4.tp4_application_client;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	 private OnTouchListener touchListenerBouton = new View.OnTouchListener() {
		    @Override
		    public boolean onTouch(View v, MotionEvent event) {
		      /* Réagir au toucher pour le bouton 3*/
		        Intent userCreationIntent = new Intent(v.getContext(), ListActivity.class);
                startActivityForResult(userCreationIntent, 0);
				return true;
		    }
	 };
		  
	@Override
	public void onCreate(Bundle savedInstanceState) {

	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);
	  
	   Button b = (Button) findViewById(R.id.premier);
	  // Puis on lui indique que cette classe sera son listener pour l'évènement Touch
	  b.setOnTouchListener(touchListenerBouton);
	}

}