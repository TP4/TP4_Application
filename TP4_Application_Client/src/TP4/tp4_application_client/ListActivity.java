package TP4.tp4_application_client;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class ListActivity extends Activity{
	
    List<ActivityToAdd> activities = new ArrayList<ActivityToAdd>();
    public List<ActivityToAdd> getActivities() {
		return activities;
	}

	public void setActivities(List<ActivityToAdd> activities) {
		this.activities = activities;
	}

	List<String> activityNames = new ArrayList<String>();
	
	  ListView liste = null;
	    
	  @Override
	  public void onCreate(Bundle savedInstanceState) {
		 
	    super.onCreate(savedInstanceState);
//	    try {
	    	Client client = new Client();
	    	client.execute();
			//this.parseXML();
//		} catch (UnknownHostException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SAXException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ParserConfigurationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
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
	 private String read() throws IOException
		{
			// Read the file
			System.out.print("Read");
			FileInputStream inStream = new FileInputStream("LOISIR_LIBRE.XML");
			InputStreamReader inputReader = new InputStreamReader(inStream);
			BufferedReader reader = new BufferedReader(inputReader);
			String message = "";
			String lineRead = null;
		
		
			while ((lineRead = reader.readLine()) != null)
			{
				message = message + lineRead;
			}
		
			reader.close();
			System.out.print(message);
			return message;
		}
	 
	 private void parseXML() throws SAXException, IOException, ParserConfigurationException
		{
			// Parse XML
		 	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document xmlDoc = builder.parse(new InputSource(new StringReader(this.read())));
			
			xmlDoc.getDocumentElement().normalize();
			NodeList activityNodeList = xmlDoc.getElementsByTagName("Activite");
						
						for (int activityPosition = 0; activityPosition < activityNodeList.getLength(); activityPosition++)
						{
							Node activityNode = activityNodeList.item(activityPosition);
							if (activityNode.getNodeType() == Node.ELEMENT_NODE)
							{
								Element activityElement = (Element) activityNode;
								ActivityToAdd activity = new ActivityToAdd(0,activityElement.getElementsByTagName("NOM_COUR").item(0).getTextContent());
								this.activities.add(activity);
							}
						}
			}
		}
