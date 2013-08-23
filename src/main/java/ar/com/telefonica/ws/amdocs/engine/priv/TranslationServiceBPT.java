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

public class TranslationServiceBPT extends TranslationService {
	
	/*
	 * constructors:
	 */
	public TranslationServiceBPT(){
		
	};
	
	
	
	/*
	 * private varibles:
	 */
	
	// defaults:
	private String defaultSrc = "*default*";
	
	
	
	/*
	 * setters/getters	
	 */
	public void setDefaultOriginValue(String defaultOriginValue) throws IllegalStateException {
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
		
		// TODO:
		/*
		// si ya existe => no hacemos nada
		if (self.obj != null) {
			return;
		};
		
		// inicializacion
		self.obj = ...
		*/
	};
	
	public synchronized void deinitialize() {
		
		// TODO:
		/*
		// si no existe => no hacemos nada
		if (self.obj == null) {
			return;
		};
		
		// deinicializacion
		...
		self.obj = null;
		}
		*/
	};
	
}
