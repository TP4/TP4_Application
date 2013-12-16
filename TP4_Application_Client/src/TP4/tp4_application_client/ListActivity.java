package TP4.tp4_application_client;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
import org.xmlpull.v1.XmlPullParser;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.simpleframework.xml.*;


public class ListActivity extends Activity{
	
	Client client = null;
	File fichierXML = null;
	Boolean allowed = true;
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
	    	this.client = new Client(this);
	    	client.execute();
	    	
	    	
			
					
			

			
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
			FileInputStream inStream = new FileInputStream(new File("LOISIR_LIBRE.XML"));
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
	 
	 private void parseXML() throws SAXException, IOException, ParserConfigurationException, InterruptedException
		{
			// Parse XML
		 	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			
			Document xmlDoc = builder.parse(this.fichierXML);
			
			xmlDoc.getDocumentElement().normalize();
			
			ActivityToAdd activity = new ActivityToAdd(0,"Accessed");
			this.activities.add(activity);
			
			NodeList activityNodeList = xmlDoc.getElementsByTagName("LOISIR_LIBRE");
						
						for (int activityPosition = 0; activityPosition < activityNodeList.getLength(); activityPosition++)
						{
							Node activityNode = activityNodeList.item(activityPosition);
							if (activityNode.getNodeType() == Node.ELEMENT_NODE)
							{
								Element activityElement = (Element) activityNode;
								 activity = new ActivityToAdd(0,activityElement.getElementsByTagName("NOM_COUR").item(0).getTextContent());
								this.activities.add(activity);
							}
						}
						
			}
		
public void saveToFile(String serverMessage) throws IOException
{
	
	// Save to a file
	System.out.print("Sauvegarde");
	
	
	String filename = "XMLActivity.xml";
	String string = serverMessage;


	try {
	File file = new File(getFilesDir(), filename);
	this.fichierXML = file;
      FileOutputStream actXml = new FileOutputStream(file);
      actXml.write(string.getBytes());
      this.allowed = true;
	  actXml.close();
		  
	  this.parseXML();
	  
	} catch (Exception e) {
	  e.printStackTrace();
	}

}
}

//private Document createDocument(String message) throws SAXException, IOException, ParserConfigurationException
//{
//	// Create a document
//	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//	DocumentBuilder builder = factory.newDocumentBuilder();
//	Document xmlDoc = builder.parse(new InputSource(new StringReader(message)));
//	xmlDoc.getDocumentElement().normalize();
//	return xmlDoc;
//}


	
//	public void parseXML() throws ParserConfigurationException, SAXException, IOException {
////		InputStream raw = getApplicationContext().getAssets().open("XMLActivity.xml");            
////        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
////        DocumentBuilder dBuilder= dbFactory.newDocumentBuilder();
////        Document doc = dBuilder.parse(raw);
////
////        doc.getDocumentElement().normalize();
//
//		FileInputStream inputStream = null;
//		
//
//
//		try {
//			inputStream = this.openFileInput("XMLActivity.xml");
//			inputStream.read();
//			
//			for (int activityPosition = 0; activityPosition < activityNodeList.getLength(); activityPosition++)
//				{
//					Node activityNode = activityNodeList.item(activityPosition);
//					if (activityNode.getNodeType() == Node.ELEMENT_NODE)
//					{
//						Element activityElement = (Element) activityNode;
//						ActivityToAdd activity = new ActivityToAdd(0,activityElement.getElementsByTagName("NOM_COUR").item(0).getTextContent());
//						this.activities.add(activity);
//					}
//				}
//			inputStream.close();
//		} catch (Exception e) {
//		  e.printStackTrace();
//		}
//		
//	 }
//
//}
//	// Parse XML
//	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//	DocumentBuilder builder = factory.newDocumentBuilder();
//	Document xmlDoc =  builder.parse(new File("XMLActivity.xml"));
//
//	Serializer serializer = new Persister();
//	File source = new File("XMLActivity.xml");
//	Activity activity = serializer.read(Activity.class, source);
//				
//				for (int activityPosition = 0; activityPosition < activityNodeList.getLength(); activityPosition++)
//				{
//					Node activityNode = activityNodeList.item(activityPosition);
//					if (activityNode.getNodeType() == Node.ELEMENT_NODE)
//					{
//						Element activityElement = (Element) activityNode;
//						ActivityToAdd activity = new ActivityToAdd(0,activityElement.getElementsByTagName("NOM_COUR").item(0).getTextContent());
//						this.activities.add(activity);
//					}
//				}
//}


