package dataProviders;

import managers.FileReaderManager;

public class XMLDataReader {

	private final String loc = FileReaderManager.getInstance().getConfigReader().getDataPath() + "User.xml";
	

}
