/** ******************************************************************************************
 * 
 * class ProcessingLoop
 * 
 * Encapsulates and manage a full processing cycle.
 * 
 * Main responsibilities:
 * 		- Input event receiver
 * 		- ObjectRecord wrapping and iterating through ProcessingLayers
 * 		- Output generation
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

import java.util.List;

import org.apache.log4j.Logger;

import ar.com.telefonica.ws.amdocs.commissioning.engine.ObjectRecord;
import ar.com.telefonica.ws.amdocs.commissioning.engine.ObjectRecord.ProcessingStatus;
import ar.com.telefonica.ws.amdocs.commissioning.engine.ObjectRecord.ResultStatus;



public class ProcessingLoop {

	static Logger logger = Logger.getLogger(ProcessingLoop.class);
	
	
	
	/*
	 * constructors:
	 */
	public ProcessingLoop() {
		
	};
	
	public ProcessingLoop(String name, SupportServices support, List<ProcessingLayer> layers) {
		this.setName(name);
		this.setLayers(layers);
		this.setSupportServices(support);
	};
	
	
	
	/*
	 * private variables:
	 */
	private String name = null;
	private SupportServices support = null;
	private List<ProcessingLayer> layers = null;
	
	
	
	/*
	 * setters/getters:
	 */
	public void setName(String name){
		this.name = name;
	};
	
	public String getName(){
		return this.name;
	};
	
	
	public void setSupportServices(SupportServices support) {
		this.support = support;
	};
	
	public SupportServices getSupportServices() {
		return this.support;
	};
	
	
	public void setLayers(List<ProcessingLayer> layers) {
		this.layers = layers;
	};
	
	public List<ProcessingLayer> getLayers() {
		return this.layers;
	};
	
	
	
	/*
	 * process an input record:
	 */
	public void processRecord(ObjectRecord oRec) throws Exception {
		
		for (ProcessingLayer layer : layers) {
			try {
				if (layer != null) {
					if (layer.processRecord(oRec, this.support)) {
						if (oRec.getProcessingStatus() == ProcessingStatus.COMPLETE) {
							return;
						}
						
					} else {
						return;
					}
				} 
				
			} catch (Exception e) {
				oRec.setProcessingStatus(ProcessingStatus.COMPLETE);
				oRec.setResultStatus(ResultStatus.ERROR);
				
				//String errMsg = "Could Not process Layer";
				logger.error(e.getMessage());
				throw e;
			}
		}
	};
}
