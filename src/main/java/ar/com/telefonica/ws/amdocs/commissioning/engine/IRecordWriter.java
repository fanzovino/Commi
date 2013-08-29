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
 * @package	ar.com.telefonica.ws.amdocs.commissioning.engine
 * 
 * @author	FDB <delbarriof@plussistemas.com.ar>
 * @version 1.0
 * @created 31-Jul-2013
 * 
 *********************************************************************************************/

package ar.com.telefonica.ws.amdocs.commissioning.engine;

import java.util.List;

public interface IRecordWriter {
	
	// processing:
	public void write(List<ObjectRecord> items) throws Exception;

	public void write(ObjectRecord item) throws Exception;
	
	public void write(OutputRecord rec) throws Exception;

}
