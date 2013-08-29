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
 * @package	ar.com.telefonica.ws.amdocs.commissioning.engine
 * 
 * @author	FDB <delbarriof@plussistemas.com.ar>
 * @version 1.0
 * @created 31-Jul-2013
 * 
 *********************************************************************************************/

package ar.com.telefonica.ws.amdocs.commissioning.engine.priv;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import ar.com.telefonica.ws.amdocs.commissioning.engine.ObjectRecord;
import ar.com.telefonica.ws.amdocs.commissioning.engine.ObjectRecord.ProcessingStatus;
import ar.com.telefonica.ws.amdocs.commissioning.engine.ObjectRecord.ResultStatus;



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
		
		// remove all null entries
		this.processors.removeAll(Collections.singleton(null));
		
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
			int iHand = 0;
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
						
						logger.fatal("Layer " + this.level + ": Processor setted on " + iProc + " position, for oRec " + oRec.getRecordId() + ". Check method canceled with fatal error.");
						
						return false;
					  }
					  
					  default:
						break;
					}
							
				} catch (Exception e) {
					oRec.setProcessingStatus(ProcessingStatus.COMPLETE);
					oRec.setResultStatus(ResultStatus.ERROR);
					
					String errMsg ="Layer level " + this.level + ": Processor setted on " + iProc + " position, for oRec " + oRec.getRecordId() + ". Check method canceled with unexpected exception.";
					logger.error(errMsg);
					throw new Exception(errMsg);
					
					//return false;
				}
			}
			
			// Iterate the willHanlde processors and execute:
			for (IProcessingObject handler : willHandle) {
				iHand++;
				
				try {
					if (!handler.handle(oRec, supportServices)) {
						
						oRec.setProcessingStatus(ProcessingStatus.COMPLETE);
						oRec.setResultStatus(ResultStatus.ERROR);
						
						String errMsg ="Layer level " + this.level + ": Processor handled on " + iHand + " position, for oRec " + oRec.getRecordId() + ". Handle method canceled with error.";
						logger.error(errMsg);
						
						return false;
					} 
						
				} catch (Exception e) {
					oRec.setProcessingStatus(ProcessingStatus.COMPLETE);
					oRec.setResultStatus(ResultStatus.ERROR);
					
					String errMsg ="Layer level " + this.level + ": Processor handled on " + iHand + " position, for oRec " + oRec.getRecordId() + ". Handle method canceled with unexpected exception.";
					logger.error(errMsg);
					throw new Exception(errMsg);
					//throw e;
				}
			}
		}
		
		return true;
	};
}
