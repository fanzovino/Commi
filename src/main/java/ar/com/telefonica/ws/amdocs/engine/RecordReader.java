/** ******************************************************************************************
 * 
 * class RecordReader
 * 
 * Base class for input record processing.
 * 
 * Main responsabilities:
 * 		- Iterate input files and generate an InputRecord object for each record in the file.
 * 
 * 		
 * @Exceptions:
 * 		- read		Log / throw		Exception
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

public class RecordReader {

	/*
	 * constructor:
	 */
	public RecordReader(){
		
	};
	
	
	
	/*
	 * setters/getters
	 */
	
	
	
	/*
	 * private functions:
	 */
	
	// TODO: Manejo del archivo de input
	
	
	
	/*
	 * process:
	 */
	public IInputRecord read() throws Exception //,
							  //UnexpectedInputException,
							  //ParseException
							  {
		IInputRecord inputRecord = null;
		
		// TODO: Base implementation for te read method (to be inherited in specific readers)
		
		return inputRecord;
	};
	
}