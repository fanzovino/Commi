/** ******************************************************************************************
 * 
 * interface RecordReader
 * 
 * Contract for heterogeneous record types processing.
 * Implementing classes must provide an InputRecord stream 
 * and a checkpoint for process completing.
 * 
 * 		
 * @Exceptions:
 * 		- read		throw		Exception
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

package ar.com.telefonica.ws.amdocs.engine;

public interface IRecordReader {
	
	// processing:
	public IInputRecord read() throws Exception; //, UnexpectedInputException, ParseException;
	
}
