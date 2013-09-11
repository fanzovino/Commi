/** ******************************************************************************************
 * 
 * class TranslationServiceBPTTestCase
 * 
 * Unit Test for TranslationServiceBPT class.
 * 
 * Test coverage:
 * 		- 
 * 
 *********************************************************************************************
 * 
 * @package	ar.com.telefonica.ws.amdocs.engine.test
 * 
 * @author	FDB <delbarriof@plussistemas.com.ar>
 * @version 1.0
 * @created 16-Ago-2013
 * 
 *********************************************************************************************/

package ar.com.telefonica.ws.amdocs.engine.test;

import static org.junit.Assert.*;

import org.junit.Test;

import ar.com.telefonica.ws.amdocs.engine.priv.TranslationServiceBPT;

public class TranslationServiceBPTTestCase {

	private TranslationServiceBPT tester ;
	

	//Testeo el setteo y el get del defaultOriginValue
	@Test
	public void testSetDefaultOriginValueOk() {
		String dftOriginValue = "**Default**";
		this.tester.setDefaultOriginValue(dftOriginValue);
		
		assertEquals(this.tester.getDefaultOriginValue(), dftOriginValue);
		
	}
	
	@Test (expected = IllegalStateException.class)
	public void testSetDefaultOriginValueException() {

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

	@Test
	public void testTranslationServiceBPT() {
		fail("Not yet implemented");
	}

	
	@Test
	public void testGetDefaultOriginValue() {
		fail("Not yet implemented");
	}

}
