/** ******************************************************************************************
 * 
 * interface ProcessingObject
 * 
 * Contract for objects implementing record processing responsibilities.
 * Classes implementing this interface should make changes to the ObjectRecord received.
 * 
 * Main responsabilities:
 * 		- check implementation for a processing action.
 * 		- handle a processing action.
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

import ar.com.telefonica.ws.amdocs.engine.ObjectRecord;

public interface IProcessingObject {
	
	// constants:
	public enum CheckResult { IGNORE, HANDLE, CANCEL, FATAL };
	
	
	
	// getters / setters:
	public String getName();
	public void setName(String name);
	
	public String getDescription(); // can return null
	public void setDescription(String description);	
	
	
	
	// processing:
	public CheckResult check(ObjectRecord oRec, SupportServices support);
	
	public boolean handle(ObjectRecord oRec, SupportServices support);
	
}