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

public class RecordWriterMultiplexor implements IRecordWriter<ObjectRecord> {

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
	
	private List<String> typesOutput ;
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
	
	public List<String> getTypesOutput() {
		return typesOutput;
	}

	public void setTypesOutput(List<String> typesOutput) {
		this.typesOutput = typesOutput;
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
		generator.write((List<? extends ObjectRecord>) oRec);
	}
	
	
	// Valido si el ObjectRecord puede ser leido
	private boolean checkObjRecToWrite(ObjectRecord objRec) {

		if(objRec.getResultStatus().equals(ObjectRecord.ResultStatus.ERROR)){
			
			//TODO: Terminar el procesamiento del oRec y continuar con el siguiente.
			//		addLog(objRec);
			
			return false;
		}
		
		for (OutputRecord outRec : objRec.getOutputRecords()) {
			if(!typesOutput.contains(outRec)) {
				//TODO:	Genero LOg indicando el numero de operacion(atributo de ObjectRecord)
				//  	y que el mismo contenia un outputType desconocido
				//		Termino el procesamiento
				//		addLog(objRec,outRec);
				
				return false;
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
	public void write(List<? extends ObjectRecord> items) throws Exception {
			oRecList = (List<ObjectRecord>) items;
			
			// TODO Auto-generated method stub
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
	
	
	
	/**
	 * Generaciones de Log(clase aparte o dependencia de otro Logger????
	 */
	/*
	private void addLog(ObjectRecord objRec) {
		System.out.print("El ORec presenta un estado de Error y no puede ser procesado.");
		
	}
	private void addLog(ObjectRecord objRec, OutputRecord outRec) {
		System.out.print("el O-Rec tiene inconsistencia con el tipo de output que se quiere generar "+ objRec.toString() + "--" + outRec.getType());
	}	
	*/
	
}
