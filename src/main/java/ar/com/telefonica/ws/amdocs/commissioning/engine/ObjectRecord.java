/** ******************************************************************************************
 * 
 * class ObjectRecord
 * 
 * Abstraction of a record being processed, with general context information.
 * 
 * Main responsibilities:
 * 		- Input / Output records encapsulation
 * 		- Tags and processing state container
 * 
 * 		
 * @Exceptions:
 * 		- unSeal			throw			IllegalStateException
 * 		- setTags			throw			IllegalStateException
 * 		- processRecord		Log / rethrow	Exception
 * 		- setRawInputRecord throw      		IllegalStateException
 * 		- setInput			throw      		IllegalStateException 
 * 		- getInputBool		throw      		UnsupportedOperationException
 * 		- getInputLong		throw      		UnsupportedOperationException
 * 		- getInputDouble	throw      		UnsupportedOperationException
 * 		- getInputBigDecimal throw      	UnsupportedOperationException
 * 		- getInputDate 		throw      		IllegalStateException
 * 		- getInputArray 	throw      		IllegalStateException
 * 
 * 
 * 
 *********************************************************************************************
 * 
 * @package	ar.com.telefonica.ws.amdocs.commissioning.engine
 * 
 * @author	FGA <FAnzovino@plussistemas.com.ar>
 * @version 1.0
 * @created 30-Jul-2013
 * @modif..	FDB <delbarriof@plussistemas.com.ar>
 * @updated 13-Ago-2013
 * 
 *********************************************************************************************/

package ar.com.telefonica.ws.amdocs.commissioning.engine;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;



public class ObjectRecord {
	
	static Logger logger = Logger.getLogger(ObjectRecord.class);
	
	
	
	/*
	 * enums:
	 */
	public static enum ProcessingStatus { PROCESSING, COMPLETE };
	public static enum ResultStatus { DATA, ERROR };
	
	
	
	/*
	 * constructors:
	 */
	public ObjectRecord() {
	};
	
	public ObjectRecord(String RecId, Object rawInputRecord) {
		this.recordId = RecId;
		this.rawInputRecord = rawInputRecord;
	};
	
	
	
	/*
	 * private variables:
	 */
	private String recordId = null;
	private ProcessingStatus processingStatus = ProcessingStatus.PROCESSING; 
	private ResultStatus resultStatus = ResultStatus.DATA;	
	private boolean sealed = false;	
	// tags
	private  Map<String, Object> tags = new HashMap<String, Object>();
	// the raw object received from the record reader
	private Object rawInputRecord = null;
	private Map<String, Object> input = new HashMap<String, Object>();
	// output records
	private List<OutputRecord> output =	new ArrayList<OutputRecord>(16);
	
	
	
	/*
	 * public interface:
	 */
	public void seal() { 
		this.sealed = true;	
	}
	
	public void unSeal() throws IllegalStateException {
		if (sealed){
			
			String msg = "Anything modified sealed atributte.";
			logger.warn(msg);
			throw new IllegalStateException(msg);
			
		}else{
			this.sealed = false;
		}
	}
	
	public boolean isSealed() { 
		return sealed; 
	}
	
	
	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) throws IllegalStateException {
		if(this.recordId!= null){
			String msg = "Setting recordId not allowed.";
			logger.warn(msg);
			throw new IllegalStateException(msg);
		}
		this.recordId = recordId;
	}

	// tags:
	public Map<String, Object> getTags() {
		return tags;
	}
	
	public void setTags(Map<String, Object> tags) throws IllegalStateException {
		if(!this.tags.isEmpty()){
			
			String msg = "Setting tags not allowed.";
			logger.warn(msg);
			throw new IllegalStateException(msg);
		}
		
		this.tags = new HashMap<String, Object>();
		
		for (String key : tags.keySet()) {
			Object value = tags.get(key);
			/*
			if(value!= null){
				// TODO: value = value.clone()
			}
			*/
			this.tags.put(key, value);
		}
	}
	
	
	// raw input:
	public void setRawInputRecord(Object rawInputRecord) throws IllegalStateException {
		
		if(this.rawInputRecord != null){
			
			String msg = "rawInputRecord already setted. Setup not allowed.***";
			logger.warn(msg);
			throw new IllegalStateException(msg);
			
		}		
		this.rawInputRecord = rawInputRecord;		
	}
	
	public Object getRawInputRecord() {
		return rawInputRecord;
	}
	
	
	// status:
	public void setProcessingStatus(ProcessingStatus processingStatus) {
		this.processingStatus = processingStatus;
	}
	
	public ProcessingStatus getProcessingStatus() {
		return processingStatus;
	}
	
	public ResultStatus getResultStatus() {
		return resultStatus;
	}
	
	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}
	
	
	// output:
	public List<OutputRecord> getOutputRecords() {
		return this.output;
	}
	
	public boolean addOutputRecord(OutputRecord outputRecord) {
		return this.output.add(outputRecord);
	}
	
	
	// input acessors:
	public void setInput(String key, Object value) throws IllegalStateException {
		if(this.sealed){

			String msg = "ObjectRecord already sealed. Operation not allowed.";
			logger.warn(msg);
			throw new IllegalStateException(msg);
			
		}else{
			this.input.put(key, value);
		}
	}
	
	
	public Object getInput(String key){
		return this.input.get(key);
	}
	
	public Object getInput(String key, Object def){
		Object value = this.input.get(key);
		
		return value != null ? value : def ;
	}
	
	
	public Boolean getInputBool(String key){
		return getInputBool(key, null);
	}
	
	public Boolean getInputBool(String key, Boolean def) throws UnsupportedOperationException {
		Object value = this.getInput(key, def);		
		
		if(value != null ) {
			try{
			    return new Boolean((Boolean)value);
			}catch(Exception e){

				String errMsg = "Not boolean type.";
				logger.error(errMsg);
				throw new UnsupportedOperationException(errMsg);
				
			}			
		}else{
			return def;
		}
	}
	
	
	public Long getInputLong(String key)  throws UnsupportedOperationException {
		return this.getInputLong(key, null);
	}
	
	public Long getInputLong(String key, Long def)  throws UnsupportedOperationException{
		Object value = this.getInput(key, def);
		
		if(value == null){
			return(null);
		}	
		if(value instanceof Long){
			return new Long((Long) value);
		}
		

		String errMsg = "Not Long type.";
		logger.error(errMsg);
		throw new UnsupportedOperationException(errMsg);
	}
	
	
	public Double getInputDouble(String key)  throws UnsupportedOperationException{      
		return this.getInputDouble(key, null);
	}
	
	
	public Double getInputDouble(String key, Double def)  throws UnsupportedOperationException { 
		Object value = this.getInput(key, def);
		
		if(value == null){
			return(null);
		}	
		if(value instanceof Double){
			return (Double)value ;
		}
		
		String errMsg = "Not Double Type.";
		logger.error(errMsg);
		throw new UnsupportedOperationException(errMsg);
		
	}
	
	
	public BigDecimal getInputBigDecimal(String key)  throws UnsupportedOperationException{      
		return this.getInputBigDecimal(key, null);
	}
	
	public BigDecimal getInputBigDecimal(String key,BigDecimal def)  throws UnsupportedOperationException {     
		Object value = this.getInput(key, def);
		
		if (value == null) {
			return(null);
		}
		if (value instanceof BigDecimal) {
			return( (BigDecimal) value);
		}
		if (value instanceof Double) {
			return(new BigDecimal( ((Double) value).doubleValue()) );
		}
		if (value instanceof Long) {
			return(new BigDecimal( ((Long) value).longValue()) );
		}
		
		String errMsg = "Type error converting key [" + key + "]: not numeric value.";
		logger.warn(errMsg);
		throw new UnsupportedOperationException(errMsg);
		
	}
	
	
	public Date getInputDate(String key)  throws UnsupportedOperationException{      
		return this.getInputDate(key, null);
	}
	
	public Date getInputDate(String key, Date def)  throws UnsupportedOperationException { 
		Object value = this.getInput(key, def);
		
		if (value != null && ! (value instanceof Date) ) {			
			String errMsg = "Type error converting key [" + key + "]: not date value.";
			logger.warn(errMsg);
			throw new UnsupportedOperationException(errMsg);
		}

		return( (Date) value);
	}
	
	
	public String getInputString(String key){      
		return this.getInputString(key,null);
	}
	
	public String getInputString(String key, String def){ 
		Object value = this.getInput(key,def);
		
		return value.toString();
	}
	
	
	public List<?> getInputArray(String key, List<?> def)  throws UnsupportedOperationException {
		
		Object value = this.getInput(key, def);
		
		if (value != null && ! (value instanceof List<?>) ) {			
			String errMsg = "Type error converting key [" + key + "]: not Array value.";
			logger.warn(errMsg);
			throw new UnsupportedOperationException(errMsg);
		}

		return( (List<?>) value);
	}
	
}
