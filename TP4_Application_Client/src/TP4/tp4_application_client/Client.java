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

public class Client
{
	private Socket socket;
	private ListActivity liste;
	
	public Client(ListActivity liste)
	{
		this.liste = liste;
		try
		{
			socket = new Socket("localhost", 50025);
			
			String message = this.read();
			Document xmlDoc = this.createDocument(message);
			
			String serverMessage = this.reception();
			this.saveToFile(serverMessage);
			this.parseXML(xmlDoc);
			
			
		}
		catch (UnknownHostException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (ParserConfigurationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (SAXException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Document createDocument(String message) throws SAXException, IOException, ParserConfigurationException
	{
		// Create a document
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document xmlDoc = builder.parse(new InputSource(new StringReader(message)));
		xmlDoc.getDocumentElement().normalize();
		return xmlDoc;
	}
	
	private String read() throws IOException
	{
		// Read the file
		System.out.print("Read");
		FileInputStream inStream = new FileInputStream("XMLActivity.xml");
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
	
	private void saveToFile(String serverMessage) throws IOException
	{
		// Save to a file
		System.out.print("Sauvegarde");
		FileOutputStream outStream = new FileOutputStream("XMLActivity.xml");
		OutputStreamWriter writerStream = new OutputStreamWriter(outStream);
		writerStream.write(serverMessage);
		writerStream.flush();
		writerStream.close();
	}
	
	private void parseXML(Document xmlDoc)
	{
		// Parse XML
		NodeList activityNodeList = xmlDoc.getElementsByTagName("Activite");
					
					for (int activityPosition = 0; activityPosition < activityNodeList.getLength(); activityPosition++)
					{
						Node activityNode = activityNodeList.item(activityPosition);
						if (activityNode.getNodeType() == Node.ELEMENT_NODE)
						{
							Element activityElement = (Element) activityNode;
							ActivityToAdd activity = new ActivityToAdd(0,activityElement.getElementsByTagName("NOM_COUR").item(0).getTextContent());
							liste.getActivities().add(activity);
						}
					}
		}
	}
