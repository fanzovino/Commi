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

public abstract class TranslationService {
	

	static Logger logger = Logger.getLogger(TranslationService.class);
	/*
	 * constructors:
	 */
	public TranslationService(){
		
	};
	
	
	
	/*
	 * private varibles:
	 */
	
	// defaults
	private String originSystem = null;
	private String targetSystem = null;
	
	
	// extended translations
	private Map<String, Object> extensions = new HashMap<String, Object>();
	
	
	
	/*
	 * setters / getters:
	 */
	
	// originSystem:
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
	
	
	// targetSystem
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
	public void setExtensions(Map<String, Object> extensions) throws IllegalStateException {
		if (this.extensions != null) {
			String errMsg = "Extensions map already initialized. Setup not allowed.";
    		logger.warn(errMsg);
			throw new UnsupportedOperationException(errMsg);
		}
		
		//this.extensions = extensions;
		
		this.extensions = new HashMap<String, Object>();
		
		for(String key : extensions.keySet()) {
			Object value = extensions.get(key);
			
			/* TODO: Clone value object.
			if (value != null) {
				value = ((Cloneable) value).clone();
			};
			*/
			
			this.extensions.put(key, value);
		};
	};
	
	public Map<String, Object> getExtensions() {
		//return this.extensions;
		
		Map<String, Object> ext = new HashMap<String, Object>();
		
		for(String key : this.extensions.keySet()) {
			Object value = this.extensions.get(key);
			
			// TODO: value = ((Cloneable) value).clone();
			
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
	abstract Long translateToLong (String table, String value, String origin, String target);
	abstract Long translateToLong (String table, Long value, String origin, String target);
	abstract String translateToString(String table, String value, String origin, String target);
	abstract String translateToString(String table, Long value, String origin, String target);
	
	
	
	/*
	 * abstract process declaration:
	 */
	abstract /*synchronized*/ void initialize();
	abstract /*synchronized*/ void deinitialize();
	
}
