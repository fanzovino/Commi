/** ******************************************************************************************
 * 
 * class TranslationServiceBPTAmdocsTestCase
 * 
 * Unit Test for TranslationServiceBPTAmdocs class.
 * 
 * Test coverage:
 * 		- 
 * 
 *********************************************************************************************
 * 
 * @package	ar.com.telefonica.ws.amdocs.commissioning.engine.test
 * 
 * @author	FDB <delbarriof@plussistemas.com.ar>
 * @version 1.0
 * @created 16-Ago-2013
 * 
 *********************************************************************************************/

package ar.com.telefonica.ws.amdocs.commissioning.files.generic.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ar.com.telefonica.ws.amdocs.commissioning.files.generic.TranslationServiceBPTAmdocs;

public class TranslationServiceBPTAmdocsTestCase {

	// instance to test:
	private TranslationServiceBPTAmdocs tester ;
	
	
	
	/** ******************************************************************************************
	 * 
	 * tester constructor:
	 *
	 *********************************************************************************************/
	
	@Before
	public void testTranslationServiceBPT() {
		this.tester = new TranslationServiceBPTAmdocs();
	}

	
	
	/** ******************************************************************************************
	 * 
	 * test setters/getters:
	 *
	 *********************************************************************************************/
	
	//Testeo el setteo y el get del defaultOriginValue
	
	@Test
	public void testDefaultOriginValueOk() {
		String dftOriginValue = "**Default**";
		this.tester.setDefaultOriginValue(dftOriginValue);
		
		assertEquals(this.tester.getDefaultOriginValue(), dftOriginValue);
	}
	
	@Test (expected = IllegalStateException.class)
	public void testDefaultOriginValueException() {
		String dftOriginValue = "**Default**";
		this.tester.setDefaultOriginValue(dftOriginValue);

		dftOriginValue = "**IsNotDefault**";
		this.tester.setDefaultOriginValue(dftOriginValue);
	}
	
	
	
	//TODO: Test de translator / (De)Initialize
	
	@Test
	public void testTranslateToLong() {
		fail("Not yet implemented");
	}

	@Test
	public void testTranslateToString() {
		fail("Not yet implemented");
	}
	
	
	
	@Test
	public void testInitialize() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeinitialize() {
		fail("Not yet implemented");
	}
	
}
