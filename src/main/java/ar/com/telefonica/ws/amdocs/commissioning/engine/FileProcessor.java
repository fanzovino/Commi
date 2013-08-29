/** ******************************************************************************************
 * 
 * class FileProcessor
 * 
 * Entry point to the input files process.
 * 
 * Main responsibilities:
 * 		- Hide processing complexity
 * 
 * @Exceptions:
 * 		- setFileType	Throw	IllegalStateException
 * 		- process		Log		Exception
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

import org.apache.log4j.Logger;

import ar.com.telefonica.ws.amdocs.commissioning.engine.ObjectRecord.ResultStatus;
import ar.com.telefonica.ws.amdocs.commissioning.engine.priv.ProcessingLoop;



public class FileProcessor {

	static Logger logger = Logger.getLogger(FileProcessor.class);
	
	
	
	/*
	 * constructors:
	 */
	public FileProcessor(){
		
	};
	
	public FileProcessor(String fileType){
		this.fileType = fileType;
	};
	
	
	
	/*
	 * private variables:
	 */
	
	// file type
	private String fileType = null;
	
	// spring context and other variables
	private ProcessingLoop loop = null;
	
	
	
	/*
	 * setters/getters
	 */
	
	// File Type:
	public void setFileType(String fileType) throws IllegalStateException {
		this.setFileType(fileType, true);
	}
	
	public void setFileType(String fileType, boolean init) throws IllegalStateException {
		
		if(this.fileType == null){
			this.fileType = fileType;

			// TODO: initialize, if we need to:
			if (init) {
			/*
				String fileName = this.getContextFileName();
				InputStream istream = this.getContextInputStream(fileName);
				this.loadContext(istream);
				
				>>>  if (logger.isInfoEnabled()) { logger.warn("Logging Exception por processRecord"); }
			*/
			}
			
		} else {
    		String errMsg = "FileType already initilized. Setting not allowed.";
    		logger.error(errMsg);
    		throw new IllegalStateException(errMsg);	
		}
	};
	
	public String getFileType(){
		return this.fileType;
	};
	
	
	public void setProcessingLoop(ProcessingLoop loop){
		this.loop = loop;
	};
	
	public ProcessingLoop getProcessingLoop(){
		return this.loop;
	};
	
	
	
	/*
	 *  process input records:
	 */
	public ObjectRecord process(IInputRecord iRec) throws Exception {
		
		ObjectRecord oRec = new ObjectRecord();
		
		oRec.setRawInputRecord(iRec);
		
		// call the processing loop to handle the record:
		try {
			this.loop.processRecord(oRec);
			
		} catch (Exception e) {
			
			if (oRec.getResultStatus() == ResultStatus.ERROR) {
				
				logger.error(e.getMessage()); 
				 
				// TODO: Ver con Jota manejo de Exceptions en SpringBatch
				 
				throw e;
			}
		}
		
		return oRec;
	};
}
