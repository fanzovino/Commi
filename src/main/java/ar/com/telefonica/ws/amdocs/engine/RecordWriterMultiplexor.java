/** ******************************************************************************************
 * 
 * class RecordWriterMultiplexor
 * 
 * Writer selector for an oRec.
 * 
 * Main responsibilities:
 * 		- Select OutputRecord handlers for each recognized type.
 * 
 * 
 * 
 * @Exceptions:
 * 		- write					throw			Exception
 * 		- generateOutPutRecFile	throw			Exception
 * 
 *  
 *  
 * 		 
 *********************************************************************************************
 * 
 * @package	ar.com.telefonica.ws.amdocs.engine
 * 
 * @author	FGA <fanzovino@plussistemas.com.ar>
 * @version 1.0
 * @created 31-Jul-2013
 * 
 *********************************************************************************************/

package ar.com.telefonica.ws.amdocs.engine;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
// import org.apache.log4j.BasicConfigurator;

public class RecordWriterMultiplexor extends RecordWriterGeneric {

   static Logger logger = Logger.getLogger(RecordWriterMultiplexor.class);
   
   
   
	/*
	 * constructors:
	 */
	public RecordWriterMultiplexor() {
	
	}
	
	
	
	/*
	 * private variables:
	 */
	private RecordWriterGeneric generator;
	private List<RecordWriterGeneric> writerGenericList ;
	
	private List<OutputRecord> typesOutput ;
	private List<ObjectRecord> oRecList = new ArrayList<ObjectRecord>();

	



	/* 
	 * setters/getters
	 */
	
	public List<RecordWriterGeneric> getWriterGenericList() {
		return writerGenericList;
	}

	public void setWriterGenericList(List<RecordWriterGeneric> writerGenericList) {
		this.writerGenericList = writerGenericList;
	}
	
	public List<OutputRecord> getTypesOutput() {
		return typesOutput;
	}

	public void setTypesOutput(List<OutputRecord> typesOutput) {
		this.typesOutput.addAll(typesOutput);
//		this.typesOutput = typesOutput;
	}
	 
	public List<ObjectRecord> getORecList() {
		return oRecList;
	}
	public void setORecList(List<ObjectRecord> orecList) {
		oRecList = orecList;
	}
	
	
	
	/* 
	 * private methods:
	 */

	@SuppressWarnings("unchecked")
	private void generateOutPutRecFile(ObjectRecord oRec) throws Exception {
		generator = new RecordWriterGeneric();
		generator.setObjRecord(oRec);
		generator.write((List<ObjectRecord>) oRec);
	}
	
	
	// Valido si el ObjectRecord puede ser leido
	//TODO poner la firma del metodo en privado???
	public boolean checkObjRecToWrite(ObjectRecord objRec) {

		if(objRec.getResultStatus().equals(ObjectRecord.ResultStatus.ERROR)){			
			logger.error("La operacion [ " + objRec.getRecordId() + " ] esta en estado de error. No se puede continuar el procesamiento.");
			return false;
		}
		
		for (OutputRecord outRec : objRec.getOutputRecords()) {
			if(!typesOutput.contains(outRec)) {
				logger.error("La operacion [ " + objRec.getRecordId() + " ] contiene un OutputType desconocido.");				
				return false;
			}else{
				//TODO: Generará log indicando el registro (número de operación) y la cantidad de output record types de cada tipo generado.
			}
			
		}
		
		return true;
	}
	
	
	
	/* 
	 * public methods:(non-Javadoc)
	 * @see ar.com.telefonica.ws.amdocs.engine.IRecordWriter#write(java.util.List)
	 */
	
	// writing to target file
	
	@SuppressWarnings("unchecked")
	public void write(List<ObjectRecord> items) throws Exception {
			oRecList = (List<ObjectRecord>) items;
			
			for (ObjectRecord objRec : items) {
				try{
					if(checkObjRecToWrite(objRec)){
						generateOutPutRecFile(objRec);
					}
				
				}catch(Exception e) {
					logger.error(e.getMessage());
					throw e;
				}
			}
			
		}
	
}
