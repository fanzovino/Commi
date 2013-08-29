/** ******************************************************************************************
 * 
 * class OutputRecord
 * 
 * Abstraction of an output event (comisionable).
 * Has two output states: bound / unbound
 * 
 * Main responsabilities:
 * 		- Dictionary with pairs { attribute, value }
 * 		- Output stream definition.
 * 		- Provide object sealing functionality
 * 
 * 		
 * @Exceptions:
 * 		- addOutputRecord	throw	IllegalStateException
 * 		- setup 			throw IllegalStateException
 *		- setAttributes 	throw IllegalStateException
 * 		- getAttributes		throw UnsupportedOperationException
 * 		- setType			throw UnsupportedOperationException
 * 		- set				throw IllegalStateException,UnsupportedOperationException
 * 		- getBool			throw UnsupportedOperationException
 * 		- getLong			throw UnsupportedOperationException
 * 		- getDouble			throw UnsupportedOperationException
 * 		- getBigDecimal		throw UnsupportedOperationException
 * 		- getDate			throw UnsupportedOperationException
 * 		- getArray			throw UnsupportedOperationException
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
//import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;



public class OutputRecord {

	static Logger logger = Logger.getLogger(OutputRecord.class);
	
	
	
	/*
	 * constructors:
	 */
	public OutputRecord() {	}
	
	public OutputRecord(String type, Map<String, Object> attrs){
		this.setup(type, attrs);
	}
	
	
	
	/*
	 * private variables:
	 */
	private Map<String, Object> attributes = null;
	private boolean sealed = false;	
	
	private String type = null;
	
	
	
	/*
	 * public methods:
	 */
	public void setup(String type, Map<String, Object> attrs) throws IllegalStateException{
		if (sealed){
    		String errMsg = "OutputRecord sealed. Setup not allowed.";
    		logger.warn(errMsg);
    		throw new IllegalStateException(errMsg);
			
		}

		setType(type);
		setAttributes(attrs);
		this.sealed= true;
	}
	
	public void seal() { 
		this.sealed = true;
	}
	
	public void unSeal() { 
		this.sealed = false;
	}
	
	public boolean isSealed() { 
		return sealed; 
	}
	
	
	
	/*
	 * setters/getters:
	 */
	
	// attributes: set a clone of the received object.
	public void setAttributes(Map<String, Object> attrs) throws IllegalStateException {
		if (this.attributes != null) {
			String errMsg = "Attributes already setted. Resetting not allowed.";
    		logger.warn(errMsg);
    		throw new IllegalStateException(errMsg);
		}
		
		this.attributes = new HashMap<String, Object>();
		
		for(String key : attrs.keySet()) {
			Object value = attrs.get(key);
			
			if (value != null) {
				// TODO: value = value.clone();
			};
			this.attributes.put(key, value);
		};
	}
	
	public Map<String, Object> getAttributes() throws UnsupportedOperationException {
		
		Map<String, Object> attrs = new HashMap<String, Object>();
		
		for(String key : this.attributes.keySet()) {
			Object value = this.attributes.get(key);
			
			if (value != null) {
				// TODO: value = value.clone();
			};
			attrs.put(key, value);
		};
		
		return attrs; 
	} 
	
	
	// type
	public void setType(String type) throws UnsupportedOperationException {
		if (this.type != null) {

			String errMsg = "OutputRecord sealed. Setup not allowed.";
    		logger.warn(errMsg);
    		throw new UnsupportedOperationException(errMsg);
		
		}
		this.type = type;
	}
	
	public String getType() {
		return this.type; 
	}
	
	
	// attribute accessors
	public void set(String key, Object value) throws IllegalStateException,UnsupportedOperationException {
		if(this.isSealed()){

			String errMsg = "The OutputRecord is sealed.";
    		logger.warn(errMsg);
    		throw new IllegalStateException(errMsg);
		};
		
		if (this.attributes == null) {
			this.attributes = new HashMap<String, Object>();
		}

		//Si el key no esta contenido en el mapa no puedo hacer el set del attr.
		if ( ! attributes.containsKey(key)){
			
			String errMsg = "Attribute [" + key + "] already exists in mapping. Insert not allowed.";
    		logger.error(errMsg);
    		throw new UnsupportedOperationException(errMsg);
			
		}
		
		this.attributes.put(key, value);
	}
	
	public Object get(String key) {
		return this.get(key, null);
	}
	
	public Object get(String key, Object def) {
		Object value = this.attributes.get(key);
		
		return  value != null ? value : def;
	}
	
	
	public Boolean getBool(String key) throws UnsupportedOperationException {
		return this.getBool(key, null);
	}
	
	public Boolean getBool(String key, Boolean def) throws UnsupportedOperationException {
		if(this.attributes.containsKey(key)) {
			try{
			 return Boolean.valueOf((String) this.attributes.get(key));
			}catch(Exception e){
				String errMsg = "Converting key [" + key + "]: not boolean type.";
				logger.error(errMsg);
				throw new UnsupportedOperationException(errMsg);
			}			
		}else{
			return def;
		}
	}
	
	
	public Long getLong(String key) throws UnsupportedOperationException {
		return this.getLong(key, null);
	}
	
	public Long getLong(String key, Long def) throws UnsupportedOperationException {
		
		Object value = this.get(key, def);
		
		if(value == null){
			return(null);
		}	
		if(value instanceof Long){
			return new Long((Long) value);
		}else{
			String errMsg = "Converting key [" + key + "]: not Long type.";
			logger.error(errMsg);
			throw new UnsupportedOperationException(errMsg);
		}
	}
	
	
	public Double getDouble(String key) throws UnsupportedOperationException {
		return this.getDouble(key, null);
	}
	
	public Double getDouble(String key, Double def) throws UnsupportedOperationException {
		if(this.attributes.containsKey(key)) {
			try{
			 return Double.valueOf((String) this.attributes.get(key));
			}catch(Exception e){

				String errMsg = "Type error converting key [" + key + "]: not Double value.";
				logger.error(errMsg);
				throw new UnsupportedOperationException(errMsg);
			
			}
			
		}else{
			return def;
		}
	}
	
	
	public BigDecimal getBigDecimal(String key) throws UnsupportedOperationException {      
		return this.getBigDecimal(key,null);
	}
	
	public BigDecimal getBigDecimal(String key, BigDecimal def) throws UnsupportedOperationException {     

		Object value = this.get(key, def);
		
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
		logger.error(errMsg);
		throw new UnsupportedOperationException(errMsg);
	}
	
	
	public Date getDate(String key) throws UnsupportedOperationException {      
		return this.getDate(key, null);
	}
	
	public Date getDate(String key, Date def) throws UnsupportedOperationException {
		
		Object value = this.get(key, def);
		
		if (value != null && ! (value instanceof Date) ) {
			
			String errMsg = "Type error converting key [" + key + "]: not Date value.";
			logger.error(errMsg);
			throw new UnsupportedOperationException(errMsg);
		}

		return( (Date) value);
	}
	
	public String getString(String key){
		return this.getString(key, null);
	}
	
	public String getString(String key, String def){
		if(this.attributes.containsKey(key)) {
			return this.attributes.get(key).toString();
		}else{
			return def.toString();
		}
	}
	

	public List<?> getArray(String key, List<?> def) throws UnsupportedOperationException {
		
		Object value = this.get(key, def);
		
		if (value != null && ! (value instanceof List<?>) ) {
			
			String errMsg = "Type error converting key [" + key + "]: not Array value.";
			logger.error(errMsg);
			throw new UnsupportedOperationException(errMsg);
		}

		return( (List<?>) value);
	}

}
