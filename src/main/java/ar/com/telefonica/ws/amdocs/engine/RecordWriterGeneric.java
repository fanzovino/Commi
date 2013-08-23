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
 * @package	ar.com.telefonica.ws.amdocs.engine
 * 
 * @author	FGA <FAnzovino@plussistemas.com.ar>
 * @version 1.0
 * @created 31-Jul-2013
 * 
 *********************************************************************************************/

package ar.com.telefonica.ws.amdocs.engine;

import java.util.List;
import org.apache.log4j.Logger;


public class RecordWriterGeneric implements IRecordWriter<ObjectRecord> {
	
	static Logger logger = Logger.getLogger(RecordWriterGeneric.class);
	
	
	
	/*
	 * constructors:
	 */
	public RecordWriterGeneric() {
		
	}
	
	public RecordWriterGeneric(ObjectRecord objRecord) {
		super();
		oRec = objRecord;
	}
	
	
	
	/*
	 * private variables:
	 */
	private String types;
	private ObjectRecord oRec;
	//private OutputRecord outputGenerator ;
	private List<OutputRecord> outPutRecList;
	
	
	
	/*
	 * setters/getters:
	 */
	public String getTypes() {
		return types;
	}
	
	public void setTypes(String types) {
		this.types = types;
	}

	
	public ObjectRecord getObjRecord() {
		return oRec;
	}

	public void setObjRecord(ObjectRecord objRecord) {
		this.oRec = objRecord;
	}
	
	
	
	/*
	 * Private methods:
	 */
	
	// Generador del archivo final output resuelve el enigma segun la definicion del output Readear
	private void GenerateFile(ObjectRecord obRec) throws Exception {
		//6.5. The Delegate Pattern and Registering with the Step
		//-->>>>>>>>>>
		
		try {
			
			// TODO: Generador de Archivos
		
		} catch (Exception e) {
			
			String errMsg = "Problem to generate the file OutPutRecord.";
    		logger.error(errMsg);
    		throw e;
			
    		
		}
		
		obRec.setProcessingStatus(ObjectRecord.ProcessingStatus.COMPLETE);
	}
	
	
	
	/*
	 * Public methods:
	 */
	
	// Genrador del archivo final output resuelve el enigma segun la definicion del output Reader
	public void write(List<? extends ObjectRecord> obRec) throws Exception {
		oRec = obRec.get(0);
	    
	    for (OutputRecord output : outPutRecList) {
	    	
	    	if(this.getTypes().contains(output.getType())){
	    		
	    		//funcion de conversion de archivo output!
	    		GenerateFile(oRec);
	    		
	    	}else{
	    		String errMsg = "Undefined OutputRecord generator.";
	    		logger.error(errMsg);
	    		throw new Exception(errMsg);
	    	}
		}
	}
		
	
	
}

