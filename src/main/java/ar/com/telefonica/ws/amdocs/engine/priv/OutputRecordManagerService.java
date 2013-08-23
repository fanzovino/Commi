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
 * @package	ar.com.telefonica.ws.amdocs.engine
 * 
 * @author	FDB <delbarriof@plussistemas.com.ar>
 * @version 1.0
 * @created 31-Jul-2013
 * 
 *********************************************************************************************/

package ar.com.telefonica.ws.amdocs.engine.priv;

import java.util.*;

import ar.com.telefonica.ws.amdocs.engine.OutputRecord;

public final class OutputRecordManagerService {

	/*
	 * constructors:
	 */
	public OutputRecordManagerService(){
		
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
	 * process:
	 */
	public synchronized void initialize(){
		
		// TODO
		
	};
	
	public synchronized void deinitialize(){
		
		// TODO
		
	};
	
}
