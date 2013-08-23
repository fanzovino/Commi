/** ******************************************************************************************
 * 
 * class SupportServices
 * 
 * Singleton object that group common services for processing instances.
 * 
 * Main responsibilities:
 * 		- Provide a single entry point for each service
 * 		- Group services in types and expose a singleton for each group (logging, BTP, etc.)
 * 
 * 		
 * @Exceptions:
 * 		- setTranslationService		throw	IllegalStateException
 * 		- setOutputRecordManager	throw	IllegalStateException
 * 		- setExtensionServices		throw	IllegalStateException
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

import org.apache.log4j.Logger;

public class SupportServices implements ISupportServiceExtension{


	static Logger logger = Logger.getLogger(SupportServices.class);
	/*
	 * constructors:
	 */
	public SupportServices(){
		
	};
	
	
	
	/*
	 * private varibles:
	 */
	
	// basic services
	private TranslationService translation = null;
	private OutputRecordManagerService outRecMgr = null;
	
	// extended services
	private Map<String, ISupportServiceExtension> extensions = new HashMap<String, ISupportServiceExtension>();
	
	
	
	/*
	 * setters / getters
	 */
	public void setTranslationService(TranslationService bpt) throws IllegalStateException {
		if (this.translation != null) {
			String errMsg = "TranslationService already initialized. Setup not allowed.";
    		logger.error(errMsg);
			throw new UnsupportedOperationException(errMsg);
		}
		this.translation = bpt;
	};
	
	public TranslationService getTranslationService(){
		return this.translation;
	}
	
	
	public void setOutputRecordManagerService(OutputRecordManagerService outRecMgr) throws IllegalStateException {
		if (this.outRecMgr != null) {
			String errMsg = "OutputRecordManager already initialized. Setup not allowed.";
    		logger.error(errMsg);
			throw new UnsupportedOperationException(errMsg);
			
		}
		this.outRecMgr = outRecMgr;
	};
	
	public OutputRecordManagerService getOutputRecordManagerService(){
		return this.outRecMgr;
	};
	
	
	public void setExtensionServices(Map<String, ISupportServiceExtension> extended) throws IllegalStateException {
		if (this.outRecMgr != null) {
			String errMsg = "ExtensionServices Map already initialized. Setup not allowed.";
    		logger.error(errMsg);
			throw new UnsupportedOperationException(errMsg);
			
		}
		this.extensions = extended;
	};
	
	public Map<String, ISupportServiceExtension> getExtensionServices(){
		//return this.extension;
		
		Map<String, ISupportServiceExtension> ext = new HashMap<String, ISupportServiceExtension>();
		
		for(String key : this.extensions.keySet()) {
			ISupportServiceExtension value = this.extensions.get(key);
			
			// TODO: value = ((Cloneable) value).clone();
			
			ext.put(key, value);
		};
		
		return ext;
	};
	
	public ISupportServiceExtension getExtensionService(String name){
		return this.extensions.get(name);	// TODO: Clone object.
	};
	
	
	
	/*
	 * process:(non-Javadoc)
	 * @see ar.com.telefonica.ws.amdocs.engine.ISupportServiceExtension#initialize()
	 */
	public synchronized void initialize(){
		
		// TODO
		
	};
	
	public synchronized void deinitialize(){
		
		// TODO
		
	};
	
}
