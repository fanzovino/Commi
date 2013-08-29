/** ******************************************************************************************
 * 
 * class TranslationExtensionEPCAmdocs
 * 
 * Additional translation mechanism.
 * 
 * Main responsabilities:
 * 		- Decide whether a Component / attribute must be informed to the Commissioning system.
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

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ar.com.telefonica.ws.amdocs.commissioning.engine.priv.ITranslationServiceExtension;
import ar.com.telefonica.ws.amdocs.commissioning.engine.priv.OutputRecordManagerService;
//import ar.com.telefonica.ws.amdocs.commissioning.files.generic.EPCRule.OPERATOR;



public class TranslationExtensionEPCAmdocs implements ITranslationServiceExtension {

	static Logger logger = Logger.getLogger(OutputRecordManagerService.class);
	
	
	private /*static*/ enum OPERATOR { NONE, EQUAL, NOT_EQUAL, LESS, LESS_EQUAL, GREATER, GREATER_EQUAL, IN_LIST };
	
	
	protected class EPCRule {
		
		public EPCRule() { };
		
		public String orderType;
		public Long componentId;
		public String attributeName;
		public OPERATOR operator;
		public String operatorValue;
		
		//TODO: Avisar a JLC por actualización de doc.
		public String resultValue;
		public String groupingCode;

	}
	
	
	public class EPCTranslation {
		
		public EPCTranslation() { };
		
		public Boolean commissionable;
		public String groupingCode;
		public String value;
	}	
	
	
	
	/*
	 * constructors:
	 */
	public TranslationExtensionEPCAmdocs() {
	};
		
	
	
	/*
	 * private variables:
	 */
	private List<EPCRule> rules = null;
	
	
	
	/*
	 * private methods:
	 */
	private void load() {
		
		/* TODO: - Load registers from DB.
		 * 		 - Create an EPCRule Object for each.
		 * 		 - Sort asc by EvaluationOrder field.
		 * 		 - Check conversions.
		 */
		
	};
	
	
	
	/*
	 * initialization / deinitialization:
	 */
	public synchronized void initialize() {
		
		if (this.rules != null) {
			this.rules = new ArrayList<EPCRule>();
		}
		
		this.load();
	};
	
	public synchronized void deinitialize() {
		this.rules = null;
	};
	
	
	
	/*
	 * transalate:
	 */
	public EPCTranslation translate(String orderType, Long componentId, String attributeName, String attributeValue) {
		
		// TODO
		
		return null;
	};
	
}
