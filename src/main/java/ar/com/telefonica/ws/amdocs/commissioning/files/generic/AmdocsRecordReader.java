/** ******************************************************************************************
 * 
 * class AmdocsRecordReader
 * 
 * .
 * 
 * Main responsabilities:
 * 		-  
 * 		
 * 		
 * @Exceptions:
 * 		- .		throw	Exception
 * 
 *********************************************************************************************
 * 
 * @package	ar.com.telefonica.ws.amdocs.commissioning.files.generic
 * 
 * @author	FDB <delbarriof@plussistemas.com.ar>
 * @version 1.0
 * @created 26-Ago-2013
 * 
 *********************************************************************************************/

package ar.com.telefonica.ws.amdocs.commissioning.files.generic;

import java.io.InputStream;

import org.apache.log4j.Logger;

import ar.com.telefonica.ws.amdocs.commissioning.engine.RecordReader;



public class AmdocsRecordReader extends RecordReader {

	static Logger logger = Logger.getLogger(AmdocsRecordReader.class);
	
	
	
	/*
	 * constructors:
	 */
	public AmdocsRecordReader();
	public AmdocsRecordReader(String fileName, String expectedFields);
	
	
	
	/*
	 * private variables:
	 */
	private String fileName;
	private String expectedFields;
	private String fieldNames[];
	private InputStream iStream;	// TODO: Ver tipo.
	
		
		
	/*
	 * setters / getters:
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	};
	
	public String getFileName() {
		return this.fileName;
	};
	
	
	
	public void setExpectedFields(String expectedFields) {
		this.expectedFields = expectedFields;
	};
	
	public String getExpectedFields() {
		return this.expectedFields;
	};
	
	
	
	/*
	 * Private methods:
	 */
	private	void checkFileFinalizer() throws Exception {
		
		// TODO:
		
		return;
	};
	
	private AmdocsInputRecord createInputRecord(String record) {
		
		// TODO:
		
	};
	
	
	
	/*
	 * Public methods:
	 */
	
	// IRecordStream
	
	@Override
	public void open(Object context) throws Exception {
		
		// TODO:
		
	};
	
	@Override
	public void close() throws Exception {
		
		// TODO:
		
	};
	
	@Override
	void update(Object context) throws Exception {
		
		// TODO:
		
	};
	
	
	
	// RecordReader
	
	@Override
	public InputRecord read() throws Exception, UnexpectedInputException, ParseException {
		
		// TODO:
		
	};
	
}
