/** ******************************************************************************************
 * 
 * class AmdocsRecordWriter
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
 * @author	FGA <FAnzovino@plussistemas.com.ar>
 * @version 1.0
 * @created 26-Ago-2013
 * 
 *********************************************************************************************/

package ar.com.telefonica.ws.amdocs.commissioning.files.generic;

import java.io.FileOutputStream;
import java.io.OutputStream;

import org.apache.log4j.Logger;

import ar.com.telefonica.ws.amdocs.commissioning.engine.OutputRecord;
import ar.com.telefonica.ws.amdocs.commissioning.engine.RecordWriterGeneric;


public class AmdocsRecordWriter extends RecordWriterGeneric {
	
	static Logger logger = Logger.getLogger(AmdocsRecordWriter.class);
	
	
	
	/*
	 * constructors:
	 */
	public AmdocsRecordWriter() {
		super();
	};
		
	public AmdocsRecordWriter(String fileName, String fieldsOrder, String recordType, String separator) {

		this.separator = separator;
		this.fileName = fileName;//FileName a inicializar
		this.recordType = recordType;//recordType del OutPutRecord
		setFieldsOrder(fieldsOrder);//este set lo hago para poder utilizar el split.
	};
	
	
	
	/*
	 * private variables:
	 */
	private String fileName = null;
	private String separator = "|";
	private String newline = "\0x0D\0x0A";
	private String recordType = null;
	private String fieldsOrder = null;
	private String fieldsSeq[] = null;
	private int fieldSeqLength = 0;
	private int recordCount = 0;
	private OutputStream oStream = null;
		
	
	
	/*
	 * setters / getters:
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	};
	
	public String getFileName() {
		return this.fileName;
	};
	
	
	
	public void setSeparator(String separator) {
		if(separator != null){
			this.separator = separator;
		}
	};
	
	public String getSeparator() {
		return this.separator;
	};
	
	
	
	public void setFieldsOrder(String fieldsOrder) {
		this.fieldsOrder = fieldsOrder;
		this.fieldsSeq = fieldsOrder.split(this.getSeparator());
		this.fieldSeqLength = fieldsOrder.split(this.getSeparator()).length;
	};
	
	public String getFieldsOrder() {
		return this.fieldsOrder;
	};
	
	
	
	public void setRecordType(String recordType) {
		this.recordType = recordType;
	};
	
	public String getRecordType() {
		return this.recordType;
	};
	
	
	
	/*
	 * private methods:
	 */
	private AmdocsInputRecord createInputRecord(String record) {
		
		//TODO method
		
		return null;
	};
	
	
	
	/*
	 * public methods:
	 */
	
	// IRecordStream
	
	@Override
	public void open(Object context) throws Exception {
		//TODO: check this point : truncarlo si ya existe?
		//TODO: decidir si el stream es buffered o no.
		this.oStream = new FileOutputStream(this.getFileName());
		//OutputStreamWriter oWriter = new OutputStreamWriter(oStream);	
	};
	
	@Override
	public void close() throws Exception {
		//TODO: check this point
		String closeFile = null;
		closeFile = String.valueOf(this.recordCount) + this.newline;
		this.oStream.write(Byte.parseByte(closeFile));
		this.oStream.close();
	};
	
	@Override
	public void update(Object context) throws Exception {
		return;
	};
	
	
	
	// IRecordWriter
	
	@Override
	public void write(OutputRecord outputRecord) throws Exception {
		
		//Si no coinciden los getType() entonces return;
		if(!outputRecord.getType().equals(this.getRecordType())) {
			return;
		} 
		
		//BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(this.oStream));
		StringBuffer writer = new StringBuffer();
		
		for (String field : this.fieldsSeq) {
			String value = null;
			
			try{
				value = this.formatField(outputRecord.get(field));
			}catch(Exception e){
				String errMsg = "The field "+ field + " in the OutputRecord[ "+outputRecord.getType()+" ] not exists.";
				logger.error(errMsg);
				throw new MissingOutputRecordFieldAmdocsException(errMsg);
				//continue;//TODO: sigo con el siguiente field...
			}
			
			if(value.equals(null)) {
				value = "";
			}
			
			writer.append(field+this.separator+ value.toString()+this.newline); // Formato :  field|value \n\r(\0x0D\0x0A)
		}
	}

	private String formatField(Object value) {
		
		if (value == null) {
			return "";
		}

		if (value instanceof Boolean) {
			Boolean val = (Boolean) value;
			
			return val ? "S" : "N";
		}
		
		// TODO: Long, Double, BigDecimal, Date
		
		return "";
	}
}
