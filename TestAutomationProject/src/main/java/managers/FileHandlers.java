/*Singleton Design Pattern limits object creation to one*/

package managers;

import handlers.ConfigFile;
import handlers.JSONFile;
import handlers.XMLFile;

public class FileHandlers {
	
	private static FileHandlers fh = new FileHandlers();
	private static ConfigFile cfh;
	private static JSONFile jfh;
	private static XMLFile xfh;
	
	//prevent user from creating instances
	private FileHandlers() {
	}
	
	//maintain static reference to own instance
	public static FileHandlers handle() {
		return fh;
	}

	//use getInstance() method to access public methods
	public ConfigFile configFile() {
		return (cfh == null) ? new ConfigFile() : cfh; 
	}
	
	public JSONFile jsonFile() {
		return (jfh == null) ? new JSONFile() : jfh; 
	}
	
	public XMLFile xmlFile() {
		return (xfh == null) ? new XMLFile() : xfh; 
	}
}
