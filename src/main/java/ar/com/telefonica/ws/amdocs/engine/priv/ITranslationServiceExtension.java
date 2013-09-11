/** ******************************************************************************************
 * 
 * interface TranslationServiceExtension
 * 
 * Provide a contract for translation service classes implementations.
 * 
 *********************************************************************************************
 * 
 * @package	ar.com.telefonica.ws.amdocs.engine
 * 
 * @author	FDB <delbarriof@plussistemas.com.ar>
 * @version 1.0
 * @created 22-Ago-2013
 * 
 *********************************************************************************************/

package ar.com.telefonica.ws.amdocs.engine.priv;

public interface ITranslationServiceExtension {
	
	public /*synchronized*/ void initialize() throws Exception;
	
	public /*synchronized*/ void deinitialize() throws Exception;

}