/** ******************************************************************************************
 * 
 * interface SupportServiceExtension
 * 
 * Provide a contract for support service classes implementations.
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

public interface ISupportServiceExtension {
	
	public /*synchronized*/ void initialize();
	
	public /*synchronized*/ void deinitialize();

}