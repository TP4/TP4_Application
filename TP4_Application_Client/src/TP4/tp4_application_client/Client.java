package TP4.tp4_application_client;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.widget.Toast;

public class Client extends AsyncTask<Void, Void, Void>
{
	private ListActivity liste = null;
	private Socket socket = null;
	
	public Client(ListActivity listActivity) {
		this.liste = listActivity;
	}


	public Socket getSocket() {
		return socket;
	}

	private Document xmlDoc = null;
    public Document getXmlDoc() {
		return xmlDoc;
	}
    
    private String messageServer = "";
    public String getMessage() {
		return messageServer;
	}


    @Override
    protected Void doInBackground(Void... params) {
       //Do your network operation here
    	try {
			socket = new Socket("162.209.100.18", 50035);
			this.messageServer = this.reception();		
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }
    protected void onPostExecute() throws IOException{
		socket.close();
		System.out.print("Connection closed");
		this.liste.saveToFile(this.messageServer);
    }
	
//	public Client(ListActivity liste) throws UnknownHostException, IOException 
//	{
//		this.liste = liste;
//		
//		try
//		{
//			socket = new Socket("173.178.177.45", 50001);
//			socket.close();
//			String message = this.read();
//			Document xmlDoc = this.createDocument(message);
//			
//			String serverMessage = this.reception();
//			this.saveToFile(serverMessage);
//			this.parseXML(xmlDoc);
//			
			
//		}
//		catch (UnknownHostException e)
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		catch (IOException e)
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		catch (ParserConfigurationException e)
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		catch (SAXException e)
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}


	private String reception() throws IOException
	{
		// Reçoit
		System.out.print("Reçoit :");
		BufferedReader fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		String serverMessage = fromServer.readLine();
		System.out.print("Reçu : ");
		System.out.println(serverMessage);	
		return serverMessage;
		
	}
	
}