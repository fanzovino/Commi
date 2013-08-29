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

package ar.com.telefonica.ws.amdocs.commissioning.engine;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

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
	// lista de writers
	private Map<String, RecordWriterGeneric> writers = new HashMap<String, RecordWriterGeneric>();
	
	
	
	/* 
	 * setters/getters
	 */
	public Map<String, RecordWriterGeneric> getWriters() {
		return this.writers;
	}

	public void setWriters(Map<String, RecordWriterGeneric> writers) {
		this.writers = writers;
	}
	
	
	
	/* 
	 * private methods:
	 */
	
	
	
	/* 
	 * public methods:(non-Javadoc)
	 * @see ar.com.telefonica.ws.amdocs.engine.IRecordWriter#write(java.util.List)
	 */
	//Recorre la lista de writers e invoca el metodo apropiado de cada writer
	@Override
	public void open(Object context) throws Exception {
		for (RecordWriterGeneric RWriterGeneric : this.getWriters().values()) {
			RWriterGeneric.open(context);
		}
		return;
	}

	@Override
	public void close() throws Exception {
		for (RecordWriterGeneric RWriterGeneric : this.getWriters().values()) {
			RWriterGeneric.close();
		}
		return;
	}

	@Override
	public void update(Object context) throws Exception {
		return;
	}


	// public void write(List<ObjectRecord> obRec) throws Exception()
	
	
	@Override
	public void write(ObjectRecord oRec) throws Exception{
		Map<String,RecordWriterGeneric> writersExec = new HashMap<String,RecordWriterGeneric>();
		String msgLog = null;
        
		if(oRec.getResultStatus().equals(ObjectRecord.ResultStatus.ERROR)){			
			
			oRec.setProcessingStatus(ObjectRecord.ProcessingStatus.COMPLETE);
			logger.warn("The operation [ " + oRec.getRecordId() + " ] canceled with Status = " + oRec.getResultStatus() + ".");
			return;
		}
		
		for (OutputRecord outRec : oRec.getOutputRecords()) {
			   if(!writers.containsKey(outRec.getType())){
				   //If no contains the writer create log
				   logger.error("The operation [ " + oRec.getRecordId() + " ] contains an unknown OutputRecord type.");
				   oRec.setProcessingStatus(ObjectRecord.ProcessingStatus.COMPLETE);
				   oRec.setResultStatus(ObjectRecord.ResultStatus.ERROR);
				   return;
			   }else{
				   // Mapeo el OutputRecord con el WriterGeneric Correspondiente.
				   writersExec.put(outRec.getType(), writers.get(outRec.getType()));
				 
				   //TODO: ---whit JLC------------------>>>>>>>>>>>>>>>>>
				   //lo hacemos de esta forma o voy generando el log en esta instancia y disparando los writer?
				   //Si hacemos esta ultima opcion tendriamos que cerrar todos los generic en la condicion de arriba
			   }
					
		}//Fin del ForEach();
		
		//Genero el log indicando el registro y la cantidad de output Rec types a generar.
		if (logger.isInfoEnabled()){
			msgLog = "The operation [ " + oRec.getRecordId() + " ] executed ("+ writersExec.size() + ") process of generator OutputFile. \n WritersExecutor: \n";
		}
		
		//Genero el log y ejecuto los writers.
		List<ObjectRecord> listORec = new ArrayList<ObjectRecord>();
		listORec.add(oRec);
		
		for (String writerType : writersExec.keySet()) {
			if (logger.isInfoEnabled()){
				msgLog = msgLog + writerType +" \n ";
			}
			try {
				writersExec.get(writerType).write(listORec);
			} catch (Exception e) {
				logger.error("The operation write on output record type " + writerType + " for record [ " + oRec.getRecordId() + " ] generated an exception: " + e.getMessage());
			}
		}
		
		//Loggeo como info el ultimo status de los executor.
		if (logger.isInfoEnabled()) {
			logger.info(msgLog);
		};
		
		oRec.setProcessingStatus(ObjectRecord.ProcessingStatus.COMPLETE);
		return;
	}
	
	public void write(OutputRecord rec) throws Exception{
		//NO Lo usa esta clase
	}
	
}
