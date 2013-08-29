/** ******************************************************************************************
 * 
 * class TranslationService
 * 
 * Singleton managed by the SupportServices Class
 * 
 * Main responsibilities:
 * 		- Translate data from origin to target system
 * 
 * 		
 * @Exceptions:
 * 		- setOriginSystem	throw	IllegalStateException
 * 		- setTargetSystem	throw	IllegalStateException
 * 		- setExtensions		throw	IllegalStateException
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

import java.util.*;

import org.apache.log4j.Logger;



public abstract class TranslationService {
	
	static Logger logger = Logger.getLogger(TranslationService.class);
	
	
	
	/*
	 * constructors:
	 */
	public TranslationService() {
		
	};
	
	
	
	/*
	 * private varibles:
	 */
	
	// defaults
	private String originSystem = null;
	private String targetSystem = null;
	
	
	// extended translations
	private Map<String, ITranslationServiceExtension> extensions = new HashMap<String, ITranslationServiceExtension>();
	
	
	
	/*
	 * setters / getters:
	 */
	
	// origin System:
	public void setOriginSystem(String origin) throws IllegalStateException {
		if (this.originSystem != null) {
			String errMsg = "OriginSystem already defined. Setup not allowed.";
    		logger.warn(errMsg);
			throw new UnsupportedOperationException(errMsg);
		}
		this.originSystem = origin;
	};
	
	public String getOriginSystem() {
		return this.originSystem;
	};
	
	
	// target System
	public void setTargetSystem(String target) throws IllegalStateException {
		if (this.targetSystem != null) {
			String errMsg = "TargetSystem already defined. Setup not allowed.";
    		logger.warn(errMsg);
			throw new UnsupportedOperationException(errMsg);
		}
		this.targetSystem = target;
	};
	
	public String getTargetSystem() {
		return this.targetSystem;
	};
	
	
	// extensions: set / return a clone of the received object.
	public void setExtensions(Map<String, ITranslationServiceExtension> extensions) throws UnsupportedOperationException {
		
		if (this.extensions != null) {
			String errMsg = "Extensions map already initialized. Setup not allowed.";
    		logger.warn(errMsg);
			throw new UnsupportedOperationException(errMsg);
		}
		
		this.extensions = extensions;
		
		for(String key : extensions.keySet()) {
			ITranslationServiceExtension value = extensions.get(key);
			
			this.extensions.put(key, value);
		};
	};
	
	public Map<String, ITranslationServiceExtension> getExtensions() {
		//return this.extensions;
		
		Map<String, ITranslationServiceExtension> ext = new HashMap<String, ITranslationServiceExtension>();
		
		for(String key : this.extensions.keySet()) {
			ITranslationServiceExtension value = this.extensions.get(key);
			
			ext.put(key, value);
		};
		
		return ext;
	};
	
	public Object getExtension(String name){
		return this.extensions.get(name);
	};
	
	
	
	/*
	 * public methods:
	 */
	
	// 2 params translations (use default origin and target to call the full parameter methods)
	public Long translateToLong (String table, String value) {
		return this.translateToLong(table, value, this.originSystem);
	};
	
	public Long translateToLong (String table, Long value) {
		return this.translateToLong(table, value, this.originSystem);
	};
	
	public String translateToString(String table, String value) {
		return this.translateToString(table, value, this.originSystem);
	};
	
	public String translateToString(String table, Long value) {
		return this.translateToString(table, value, this.originSystem);
	};
	
	
	// 3 params translations (use default origin to call the full parameter methods).
	public Long translateToLong (String table, String value, String origin) {
		return this.translateToLong(table, value, origin, this.targetSystem);
	};
	
	public Long translateToLong (String table, Long value, String origin) {
		return this.translateToLong(table, value, origin, this.targetSystem);
	};
	
	public String translateToString(String table, String value, String origin) {
		return this.translateToString(table, value, origin, this.targetSystem);
	};
	
	public String translateToString(String table, Long value, String origin) {
		return this.translateToString(table, value, origin, this.targetSystem);
	};
	
	
	// 4 params abstract methods that implement the translation.
	public abstract Long translateToLong (String table, String value, String origin, String target);
	public abstract Long translateToLong (String table, Long value, String origin, String target);
	public abstract String translateToString(String table, String value, String origin, String target);
	public abstract String translateToString(String table, Long value, String origin, String target);
	
	
	
	/*
	 * initialization / deinitialization:
	 */
	public synchronized void initialize() throws Exception {
		// extended translations:
		for(String key : this.extensions.keySet()) {
			try {
				this.extensions.get(key).initialize();
			} catch (Exception e) {
				//String errMsg = "Could not initialize support services.";
	    		
				logger.error(e.getMessage());
				// we care if we cannot initialize
				throw e; //new UnsupportedOperationException(errMsg);
			}
		};
	};
	
	
	public synchronized void deinitialize() {

		// extended translations:
		for(String key : this.extensions.keySet()) {
			try {
				this.extensions.get(key).deinitialize();
			} catch (Exception e) {
				//String errMsg = "Could not deinitialize support services.";
	    		
				logger.error(e.getMessage());
				// we don't care if we cannot deinitialize
			}
		};
	};
	
}
