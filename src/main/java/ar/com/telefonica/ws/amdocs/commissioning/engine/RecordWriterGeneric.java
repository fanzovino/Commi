/** ******************************************************************************************
 * 
 * class RecordWriterGeneric
 * 
 * Writer implementation for generic output.
 * 
 * Main responsibilities:
 * 		- Process oRec and lookup for specific OutputRecords
 * 		- Write to output file
 * 
 * 
 * @Exceptions:
 * 		- write			throw			Exception
 * 		- GenerateFile	throw			Exception
 * 		
 *********************************************************************************************
 * 
 * @package	ar.com.telefonica.ws.amdocs.commissioning.engine
 * 
 * @author	FGA <FAnzovino@plussistemas.com.ar>
 * @version 1.0
 * @created 31-Jul-2013
 * 
 *********************************************************************************************/

package ar.com.telefonica.ws.amdocs.commissioning.engine;

import java.util.List;
import org.apache.log4j.Logger;



public class RecordWriterGeneric implements IRecordWriter, IRecordStream {
	
	static Logger logger = Logger.getLogger(RecordWriterGeneric.class);
	
	
	
	/*
	 * constructors:
	 */
	
	public RecordWriterGeneric() {
		
	}
	
	
	
	/*
	 * Public methods:
	 */
	
	public void open(Object context) throws Exception { }

	public void close() throws Exception { }

	public void update(Object context) throws Exception { }
	
	
	
	public void write(List<ObjectRecord> items) throws Exception {
		for (ObjectRecord objectRecord : items) {
			this.write(objectRecord);
		}
	}
	
	
	public void write(ObjectRecord item) throws Exception{
		for (OutputRecord outRec : item.getOutputRecords()) {
			this.write(outRec);
		}
	}
	
	
	public void write(OutputRecord rec) throws Exception{
		return; 
	}
	
}

