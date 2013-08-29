/** ******************************************************************************************
 * 
 * class OutputRecordManagerService
 * 
 * Singleton to be accessed through the SupportServices.getOutputRecordManagerService method.
 * Has two output states: bound / unbound
 * 
 * Main responsabilities:
 * 		- Create OutputRecord objects for different types.
 * 		- Expose a map with types of OutputRecords as keys and pairs {attrib, value} as value.
 * 		- Have a config param with the String Context configuration
 * 		- Implement an initialize method that process that file to get the required config.
 * 
 *********************************************************************************************
 * 
 * @package	ar.com.telefonica.ws.amdocs.commissioning.engine
 * 
 * @author	FDB <delbarriof@plussistemas.com.ar>
 * @version 1.0
 * @created 31-Jul-2013
 * 
 *********************************************************************************************/

package ar.com.telefonica.ws.amdocs.commissioning.engine.priv;

import java.io.InputStream;
import java.util.*;

import org.apache.log4j.Logger;

import ar.com.telefonica.ws.amdocs.commissioning.engine.OutputRecord;
import org.stringtree.json.JSONReader;

public final class OutputRecordManagerService {

	static Logger logger = Logger.getLogger(OutputRecordManagerService.class);
	
	static OutputRecordManagerService singleton = null;
	
	
	
	/*
	 * constructors:
	 */
	public OutputRecordManagerService(){
		
		// TODO: Revisar
		/*
		synchronized (singleton) {
			if (singleton != null) {
				// TODO: Exception type
				// throw new Exception("SupportServices: invalid constructor invocation.");
			}
			singleton = this;
		}
		*/
	};
	
	
	
	/*
	 * private variables:
	 */
	
	// extended translations
	private Map<String, Map<String, Object> > types = new HashMap<String, Map<String, Object> >();
	
	// configuration file
	private String configFile = null;
	
	
	
	/*
	 * setters / getters:
	 */
	public void setConfigFile(String configFile){
		this.configFile = configFile;
	}
	
	public String getConfigFile(){
		return this.configFile;
	};
	
	public OutputRecord get(String type){
		return (OutputRecord) this.types.get(type);
	};
	
	
	
	/*
	 * initialization / deinitialization:
	 */
	public synchronized void initialize() throws Exception {
		
		try {
			this.initialize(this.readConfig());
			
		} catch (Exception e) {
			logger.warn(e.getMessage());
			throw e;
		}
		
	};
	
	public synchronized void initialize(String config) throws Exception {
		
		try {
			// be sure we have no types
			this.types = null;			
			
			// read and parse JSON config
			this.parseConfig(config);
			
		} catch (Exception e) {
			logger.warn(e.getMessage());
			throw e;
		}
		
	};
	
	public synchronized void deinitialize(){
		
		this.types = null;
		
	};
	
	
	
	private String readConfig() {

		InputStream stream = OutputRecordManagerService.class.getClassLoader().getResourceAsStream(this.configFile);
		
		// lee toda la mierda en un string y lo retorna
		
		
		return config;
	};
	
	private void parseConfig(String config) {

		// parse json object
		JSONReader reader = new JSONReader();
		Object result = reader.read(config);

		// check it's a Map
		if ( ! (result instanceof Map<?, ?>)) {
			// log & throw
		}

		// save the list of types
		this.types = (Map<String, Map<String, Object>>) result;
	}
	
}
