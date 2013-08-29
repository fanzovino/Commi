package ar.com.telefonica.ws.amdocs.commissioning.engine;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import ar.com.telefonica.ws.amdocs.commissioning.engine.priv.IProcessingObject;
import ar.com.telefonica.ws.amdocs.commissioning.engine.priv.SupportServices;
import ar.com.telefonica.ws.amdocs.commissioning.files.generic.AmdocsInputRecord;


public class L1PO_InputRecord_Amdocs implements IProcessingObject {
	

	/*
	 * constructors:
	 */
	public L1PO_InputRecord_Amdocs() {
	}


	/*
	 * private variables:
	 */
	// tipos de dato de los campos
	private Map<String, String> fieldTypes = new HashMap<String, String>();
	
	/*
	 * setters/getters:
	 */
	public void setFieldTypes(Map<String, String> fieldTypes){
		this.fieldTypes = fieldTypes;
	};
	
	public Map<String, String> getFieldTypes(){
		
		Map<String, String> tagsReturn = new HashMap<String, String>();
		
		for(String key : this.fieldTypes.keySet()) {
			String value = new String (this.fieldTypes.get(key));
			
			if (value != null) {
				// TODO: value = value.clone();
			};
			tagsReturn.put(key, value);
		};
		
		return tagsReturn;
	};
	 
	
	public String getDescription() {
		return("Convierte un InputRecord de Amdocs " +
		"a un ObjectRecord que pueda procesar " +
		"el motor");
	};
	
	public void setDescription(String description) {
		return;
	}

	public String getName() { return(this.getClass().getName()); };


	public void setName(String name) {
		return;
	}
	
	
	// processing
	public CheckResult check(ObjectRecord oRec,
		SupportServices support) {
		return(CheckResult.HANDLE);
	};
	
	public boolean handle(ObjectRecord oRec,SupportServices support){
		if(!oRec.isSealed()){
			
			AmdocsInputRecord amDocsInputRec = (AmdocsInputRecord) oRec.getRawInputRecord();
			
			//TODO: ¿¿que diferencia hay entre Field y FieldNames???
			amDocsInputRec.getFieldCount();
			for (int i = 0; i <=  amDocsInputRec.getFieldCount(); i++) {
				String fieldName = amDocsInputRec.getFieldName(i);
				//TODO: ¿¿quien setea el FileTypes ??? 
				String fieldType = "String"; //inicializo como Default
				
				if(this.fieldTypes.containsKey(fieldName)){
					fieldType = this.getFieldTypes().get(fieldName);
				}
				
				//Object obj = getValue (amDocsInputRec,fieldType,i);
				
				//TODO:ver el if definido aca abajo es correcto a validar o no. Ver con J--
				//Si es valido genero un metodo privado de la clase.... Integer; {Date,Time,DateTime}....
//				if(fieldType.equalsIgnoreCase("BOOLEAN")){
//					fieldType = "Bool";
//				}else{
//					//remplazo la primera letra a UPPER para poder pedirlo al instanciar el metodo
//					fieldType =  fieldType.substring(0, 1).toUpperCase() + fieldType.substring(1, fieldType.length()).toLowerCase();
//				}
				
				try {
					Class c = Class.forName(AmdocsInputRecord.class.getCanonicalName());
					Method mthd = c.getMethod("getField"+fieldType,Integer.TYPE);
					//seteo el Input 
					//¿¿¿FieldName o FieldType como key????
					oRec.setInput(fieldName, mthd.invoke(amDocsInputRec,i));
				} catch (Exception e) {
					//???Log
					e.printStackTrace();
				} 
				
			}
			
			return true;
		}
		
		//Retorno False si el oRec esta sellado.
		return false;
		
	}
	
}
