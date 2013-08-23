/** ******************************************************************************************
 * 
 * class ProcessingLayer
 * 
 * Pass ObjectRecords through the ProcessingObjects of an specific layer of the processing cycle.
 * 
 * Main responsabilities:
 * 		- Persist a list of ProcessingObjects
 * 		- Iterate ObjectRecords through this objects, invoking the Check / Handle methods pair 
 * 
 * 		
 * @Exceptions:
 * 		- processRecord		Log / rethrow	Exception
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

package ar.com.telefonica.ws.amdocs.engine.priv;

import java.util.List;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import ar.com.telefonica.ws.amdocs.engine.ObjectRecord;
import ar.com.telefonica.ws.amdocs.engine.ObjectRecord.ProcessingStatus;
import ar.com.telefonica.ws.amdocs.engine.ObjectRecord.ResultStatus;
//import ar.com.telefonica.ws.amdocs.engine.RecordWriterGeneric;
//import ar.com.telefonica.ws.amdocs.engine.IProcessingObject.CheckResult;


public class ProcessingLayer {
	
	static Logger logger = Logger.getLogger(ProcessingLayer.class);
	
	
	
	/*
	 * constructors:
	 */
	public ProcessingLayer() {
		
	};
	
	public ProcessingLayer(int level, List<IProcessingObject> processors) {
		this.setLevel(level);
		this.setProcessors(processors);
	};
	
	
	
	/*
	 * private variables:
	 */
	private int level = -1;
	private List<IProcessingObject> processors = null;
	
	
	
	/*
	 * setters/getters:
	 */
	public void setLevel(int level) {
		this.level = level;
	};
	
	public int getLevel() {
		return this.level;
	};
	
	public void setProcessors(List<IProcessingObject> processors) {
		this.processors = processors;
		
		// TODO: null entries control.
		
	};
	
	public List<IProcessingObject> getProcessors() {
		return this.processors;
	};
	
	
	
	/*
	 * process an input record:
	 */
	public boolean processRecord(ObjectRecord oRec, SupportServices supportServices) throws Exception {
		
		if (oRec.getProcessingStatus() != ProcessingStatus.COMPLETE){
			
			int iProc = 0;
			List<IProcessingObject> willHandle = new ArrayList<IProcessingObject>();
			
			// Iterate all registered processors and complete willHandle list:
			for (IProcessingObject processor : this.processors) {
				iProc++;
				
				try {
					switch (processor.check(oRec, supportServices)) {
					  case IGNORE:{
						//continue;
						break;
					  }
					  
					  case HANDLE:{
						willHandle.add(processor);
						
						break;
					  }
					  
					  case CANCEL:{
						oRec.setProcessingStatus(ProcessingStatus.COMPLETE);
						
						 logger.warn("Processor level " + iProc + " canceled.");
						
						return false;
					  }
						
					  case FATAL:{
						oRec.setProcessingStatus(ProcessingStatus.COMPLETE);
						oRec.setResultStatus(ResultStatus.ERROR);
						
						logger.fatal("Processor level " + iProc + " canceled with fatal error.");
						
						return false;
					  }
					  
					  default:
						break;
					}
							
				} catch (Exception e) {
					// e.printStackTrace();
					
					oRec.setProcessingStatus(ProcessingStatus.COMPLETE);
					oRec.setResultStatus(ResultStatus.ERROR);
					
					String errMsg ="Processor level " + iProc + " canceled with fatal error.";
					logger.error(errMsg +"]--[ "+ e);
					// TODO: Log, layer "level" para el oRec (ver identificador), fallo en método "check" de "processor"
					throw e;
					
					//return false;
				}
			}
			
			// Iterate the willHanlde processors and execute:
			for (IProcessingObject handler : willHandle) {
				try {
					if (!handler.handle(oRec, supportServices)) {
						
						oRec.setProcessingStatus(ProcessingStatus.COMPLETE);
						oRec.setResultStatus(ResultStatus.ERROR);
						
						//TODO: Log, layer "level" para el oRec (ver identificador), método "handle" indicó terminar (false)
						
						return false;
					} 
						
				} catch (Exception e) {
					//e.printStackTrace();
					
					oRec.setProcessingStatus(ProcessingStatus.COMPLETE);
					oRec.setResultStatus(ResultStatus.ERROR);
					
					logger.error(e);
					// TODO: Log, layer "level" para el oRec (ver identificador), fallo en método "handle" de "processor"
					
					throw e;
					
					//return false;
				}
			}
		}
		
		return true;
	};
}
