/** ******************************************************************************************
 * 
 * class AmdocsInputRecord
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

import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

import ar.com.telefonica.ws.amdocs.commissioning.engine.IInputRecord;
import ar.com.telefonica.ws.amdocs.commissioning.engine.priv.ProcessingLayer;



public class AmdocsInputRecord implements IInputRecord {
	
	static Logger logger = Logger.getLogger(AmdocsInputRecord.class);
	
	/*
	 * constructors:
	 */
	public AmdocsInputRecord() {
		
	};
	
	public AmdocsInputRecord(String[] fields) {
		
	};
	
	public AmdocsInputRecord(String[] fields, String[] names) {
		
	};
	
	public AmdocsInputRecord(String[] fields, String[] names, String recordId, String rawRecord) {
		
	};
	
	
	
	/*
	 * private variables:
	 */
	private String recordId = null;
	private String rawRecord = null;
	
	private int fieldCount = 0;
	private String[] fields = null;
	
	private int fieldNamesCount = 0;
	private String[] fieldNames = null;
	
	
	
	/*
	 * setters/getters:
	 */
	public void setRecordId(String recordId) {
		this.recordId = recordId;
	};
	
	public String getRecordId() {
		return this.recordId;
	};
	
	
	
	public void setRawRecord(String rawRecord) {
		this.rawRecord = rawRecord;
	};
	
	public String getRawRecord() {
		return this.rawRecord;
	};
	
	
	
	public void setFields(String[] fields) {
		this.fields = fields;
		this.fieldCount = fields.length;
	};
	
	public String[] getFields() {
		return this.fields;
	};
	
	
	
	public void setFieldNames(String[] fieldNames) {
		this.fieldNames = fieldNames;
	};
	
	public String[] getFieldNames() {
		return this.fieldNames;
	};
	
	
	
	/*
	 * field accessors
	 */
	public int getFieldCount() {
		return this.fieldCount;
	};
	

	public String getFieldName(int i) {	
		if(i >= 0 && i < this.fieldNames.length-1){
			return (this.fieldNames[i]).trim();
		}else if(i >= this.fieldNames.length){
			return null;
		}else{
			String msgErr = "Out the range of a group of numbers";
			logger.error(msgErr);
			throw new ArrayIndexOutOfBoundsException(msgErr);
		}
	};

	
	
	public String getField(int i) {
		return this.getField(i, null);
	};
	
	public String getField(int i, String def) {
		if(i >= 0 && i < this.fields.length-1){
			return this.fields[i];
		}else if(i >= this.fields.length){
			return def;
		}else{
			String msgErr = "Out the range of a group of numbers";
			logger.error(msgErr);
			throw new ArrayIndexOutOfBoundsException(msgErr);
		}
	
		
	};
	
	
	
	public Boolean getFieldBool(int i) throws UnsupportedOperationException {
		return this.getFieldBool(i,null);
	};
	
	public Boolean getFieldBool(int i, Boolean def) throws UnsupportedOperationException {
		String value = this.getField(i,null);
		if(value != null) {
			try {
				if (value.equalsIgnoreCase("true") || value.equalsIgnoreCase("t") ||
					value.equalsIgnoreCase("y") || value.equalsIgnoreCase("s")) {
					return new Boolean(true);
				};
				return new Boolean(false);
				
			} catch(Exception e) {
				String errMsg = "Converting field [" + value + "]: not boolean type.";
				logger.error(errMsg);
				throw new UnsupportedOperationException(errMsg);
			}
		} else {
			return def;
		}
	};
	
	
	
	public Long getFieldLong(int i) {
		return this.getFieldLong(i, null);
	};
	
	public Long getFieldLong(int i, Long def) {
		String value = this.getField(i);
		value = value.trim();
		
		def
		
		return long
	};
	
	
	
	public Double getFieldDouble(int i) {
		return getFieldDouble(i, null);
		
	};
	
	public Double getFieldDouble(int i, Double def) {
		return def;
	};
	
	
	
	public BigDecimal getFieldBigDecimal(int i) {
		
	};
	
	public BigDecimal getFieldBigDecimal(int i, BigDecimal def) {
		
	};
	
	
	
	public Date getFieldDate(int i) {
		
	};
	
	public Date getFieldDate(int i, Date def) {
		
	};
	
	
	
	public String getFieldString(int i) {
		return getFieldString(i,null);
	};
	
	public String getFieldString(int i, String def) {
		return this.getField(i, def).toString();		
	};
}
