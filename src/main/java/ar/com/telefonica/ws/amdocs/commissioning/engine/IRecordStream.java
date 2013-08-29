/** ******************************************************************************************
 * 
 * interface InputRecord
 * 
 * Contract for common SpringBatch methods.
 * Implementable for RecordReader and RecordWriter classes.
 * 
 * Main contract:
 * 		- open, close, update methods
 * 	
 * @Exceptions:
 * 		- close		throw		Exception
 * 		- update	throw		Exception
 * 		- read		throw		Exception
 * 
 *********************************************************************************************
 * 
 * @package	ar.com.telefonica.ws.amdocs.commissioning.engine
 * 
 * @author	FDB <delbarriof@plussistemas.com.ar>
 * @version 1.0
 * @created 25-Ago-2013
 * 
 *********************************************************************************************/

package ar.com.telefonica.ws.amdocs.commissioning.engine;

public interface IRecordStream {
	
	public void open(Object context) throws Exception;
	
	public void close() throws Exception;
	
	public void update(Object context) throws Exception;
	
}
