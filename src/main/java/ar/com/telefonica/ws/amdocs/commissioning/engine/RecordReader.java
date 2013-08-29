/** ******************************************************************************************
 * 
 * class RecordReader
 * 
 * Base class for input record processing.
 * 
 * Main responsabilities:
 * 		- Iterate input files and generate an InputRecord object for each record in the file.
 * 		
 * @Exceptions:
 * 		- open		throw		Exception
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
 * @created 31-Jul-2013
 * 
 *********************************************************************************************/

package ar.com.telefonica.ws.amdocs.commissioning.engine;

public class RecordReader implements IRecordReader, IRecordStream {
	
	/*
	 * constructor:
	 */
	public RecordReader() {
		
	};
	
	
	
	/*
	 * IRecordStream:
	 * Empty implementation for common SpringBatch methods.
	 */
	public void open(Object context) throws Exception {	}
	
	public void close() throws Exception { }
	
	public void update(Object context) throws Exception { };
	
	
	
	/*
	 * IRecordReader:
	 * Empty implementation for te read method (to be inherited in specific readers)
	 */
	public IInputRecord read() throws Exception /*, UnexpectedInputException, ParseException*/ {
		
		// IInputRecord inputRecord = null;
		
		return null;
	}
}