/** ******************************************************************************************
 * 
 * interface RecordWriter
 * 
 * Contract for output generation.
 * 
 * Main responsibilities:
 * 		- Generate output files for the different OutputRecords in the current O-Rec
 * 		- Compatibility with Spring's ItemReader interface
 * 
 * 		
 * @Exceptions:
 * 		- write		throw		Exception
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

import java.util.List;

public interface IRecordWriter<T> {
	
	// processing:
	void write(List<? extends T> items) throws Exception;

}
