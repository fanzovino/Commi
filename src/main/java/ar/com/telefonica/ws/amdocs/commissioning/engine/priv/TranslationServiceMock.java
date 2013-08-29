/** ******************************************************************************************
 * 
 * class TranslationServiceMock
 * 
 * Generic translation service for mock testing. 
 * Inherits abstract class TranslationService.
 * 
 * 		
 * @Exceptions:
 * 		- setOriginSystem	throw	IllegalStateException
 * 		- 
 * 
 *********************************************************************************************
 * 
 * @package	ar.com.telefonica.ws.amdocs.commissioning.engine
 * 
 * @author	FDB <delbarriof@plussistemas.com.ar>
 * @version 1.0
 * @created 23-Ago-2013
 * 
 *********************************************************************************************/

package ar.com.telefonica.ws.amdocs.commissioning.engine.priv;

import org.apache.log4j.Logger;

public class TranslationServiceMock extends TranslationService {
	
	static Logger logger = Logger.getLogger(TranslationServiceMock.class);
	
	static TranslationServiceMock singleton = null;
	
	
	
	/*
	 * constructors:
	 */
	public TranslationServiceMock() {
		
		// TODO: Revisar
		/*
		synchronized (singleton) {
			if (singleton == null) { singleton = this; }
		}
		*/
	};
	
	
	
	/*
	 * private varibles:
	 */
	
	// defaults:
	private String defaultSrc = null;
	
	
	
	/*
	 * setters/getters	
	 */
	
	public void setDefaultOriginValue(String defaultOriginValue) throws IllegalStateException {
		if(!this.defaultSrc.isEmpty()){
			String errMsg = "DefaultOriginValue already initialized. Setup not allowed.";
    		logger.warn(errMsg);
			throw new IllegalStateException(errMsg);
		}
		
		this.defaultSrc = defaultOriginValue;
	};
	
	public String getDefaultOriginValue() {
		return this.defaultSrc;
	};
	
	
	
	/*
	 * public methods: 
	 * 
	 * override the abstract methods to actually translate.
	 * 
	 */
	
	// IMPORTANT: note that target goes **BEFORE** origin
	
	public Long translateToLong (String table, String value, String target, String origin) {
		return new Long(value);
	};
	
	public Long translateToLong (String table, Long value, String target, String origin) {
		return value;
	};
	
	public String translateToString(String table, String value, String target, String origin) {
		return value;
	};
	
	public String translateToString(String table, Long value, String target, String origin) {
		return value.toString();
	};
	
	
	
	/*
	 * initialization / deinitialization:
	 */
	public synchronized void initialize() throws Exception{
		
		try {
			super.initialize();
			
		} catch (Exception e) {
			logger.warn(e.getMessage());
			throw e;
		}
		
	};
	
	
	public synchronized void deinitialize() {
		
		TranslationServiceMock.singleton = null;
		
	};
	
}
