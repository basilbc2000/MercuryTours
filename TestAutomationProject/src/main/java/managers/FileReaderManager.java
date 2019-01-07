/*Singleton Design Pattern limits object creation to one*/

package managers;

import dataProviders.ConfigFileReader;
import dataProviders.JsonDataReader;
import dataProviders.XMLDataReader;

public class FileReaderManager {
	
	private static FileReaderManager frm = new FileReaderManager();
	private static ConfigFileReader cfg;
	private static JsonDataReader jdr;
	private static XMLDataReader xdr;
	
	//prevent user from creating instances
	private FileReaderManager() {
	}
	
	//maintain static reference to own instance
	public static FileReaderManager getInstance() {
		return frm;
	}

	//use getInstance() method to access public methods
	public ConfigFileReader getConfigReader() {
		return (cfg == null) ? new ConfigFileReader() : cfg; 
	}
	
	public JsonDataReader getJsonReader() {
		return (jdr == null) ? new JsonDataReader() : jdr; 
	}
	
	public XMLDataReader getXMLReader() {
		return (xdr == null) ? new XMLDataReader() : xdr; 
	}
}
