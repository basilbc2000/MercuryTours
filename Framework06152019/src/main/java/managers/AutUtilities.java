package managers;

import autUtilities.ConfigFile;
import autUtilities.Logging;

public class AutUtilities {
	
	private static AutUtilities util = new AutUtilities();
	private static ConfigFile cfg;
	private static Logging log;

	private AutUtilities() {}
	
	public static AutUtilities call() {
		return util;
	}
	
	public ConfigFile config() {
		return (cfg == null) ? new ConfigFile() : cfg; 
	}
	
	public Logging setLogging() {
		return (log == null) ? new Logging() : log; 
	}
	
}
