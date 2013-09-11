/** ******************************************************************************************
 * 
 * class TranslationServiceBPT
 * 
 * Inherits abstract class TranslationService.
 * 
 * Main responsibilities:
 * 		- Thread safe initialization
 * 		- Use BPT library (Pragma) for method implementation.
 * 
 * 		
 * @Exceptions:
 * 		- setOriginSystem	throw	IllegalStateException
 * 		- 
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

import org.apache.log4j.Logger;

// import tes.comp.integration.mapping.DVSimpleMappingManager;

public class TranslationServiceBPT extends TranslationService {
	
	static Logger logger = Logger.getLogger(SupportServices.class);
	
	static TranslationServiceBPT singleton = null;
	
	//private MappingManager manager = null;
	
	
	
	/*
	 * constructors:
	 */
	public TranslationServiceBPT(){
		this.initialize();
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
	 * public methods: (non-Javadoc)
	 * @see ar.com.telefonica.ws.amdocs.engine.TranslationService#translateToLong(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	
	// override the abstract methods to actually translate.
	//
	// IMPORTANT: note that target goes **BEFORE** origin
	//
	public Long translateToLong (String table, String value, String target, String origin) {

		// TODO: transalate origin to target using BPTs libraries.
		
		// this.manager.getMapping(....)

		
		return new Long(value);
	};
	
	public Long translateToLong (String table, Long value, String target, String origin) {
		
		// TODO: transalate origin to target using BPTs libraries.
		
		return new Long(value);
	};
	
	public String translateToString(String table, String value, String target, String origin) {
		
		// TODO: transalate origin to target using BPTs libraries.
		
		return new String(value);
	};
	
	public String translateToString(String table, Long value, String target, String origin) {
		
		// TODO: transalate origin to target using BPTs libraries.
		
		return new String(value.toString());
	};
	
	
	
	/*
	 * process:(non-Javadoc)
	 * @see ar.com.telefonica.ws.amdocs.engine.TranslationService#initialize()
	 */
	
	// Initialize singleton, thread safe:
	public synchronized void initialize() {

		try {
			super.initialize();
			
			synchronized (singleton) {
				if (singleton != null) {
					// TODO: Exception type
					// throw new Exception("SupportServices: invalid constructor invocation.");
				}
				singleton = this;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// inicializacion
		// this.manager = DVSimpleMappingManager.getInstance();
		// this.manager.loadMappings();

	};
	
	public synchronized void deinitialize() {

		// TODO:

		/*
		// si no existe => no hacemos nada
		if (self.manager == null) {
			return;
		};
		
		// deinicializacion

		this.manager = null;
		*/
	};
	
}
