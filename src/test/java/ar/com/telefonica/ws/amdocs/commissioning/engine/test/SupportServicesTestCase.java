/** ******************************************************************************************
 * 
 * class SupportServicesTestCase
 * 
 * Unit Test for SupportServices class.
 * 
 * Test coverage:
 * 		- setters / getters
 * 		- cascade initialization / deinitialization
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

package ar.com.telefonica.ws.amdocs.commissioning.engine.test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import ar.com.telefonica.ws.amdocs.commissioning.engine.priv.ISupportServiceExtension;
import ar.com.telefonica.ws.amdocs.commissioning.engine.priv.OutputRecordManagerService;
import ar.com.telefonica.ws.amdocs.commissioning.engine.priv.SupportServices;
import ar.com.telefonica.ws.amdocs.commissioning.engine.priv.TranslationService;
import ar.com.telefonica.ws.amdocs.commissioning.engine.priv.TranslationServiceMock;



public class SupportServicesTestCase {

	// instance to test:
	SupportServices tester = new SupportServices();
	
	// input test values:
	TranslationService translation = null;
	OutputRecordManagerService outRecMgr = null;
	Map<String, ISupportServiceExtension> extensions = null; //new HashMap<String, ISupportServiceExtension>();
	
	
	
	/** ******************************************************************************************
	 * 
	 * tester constructor:
	 *
	 *********************************************************************************************/
	
	@Before
	public void testSupportServices() {
		this.tester = new SupportServices();
	}
	
	
	
	/** ******************************************************************************************
	 * 
	 * test setters/getters:
	 *
	 *********************************************************************************************/
	
	@Test
	public void testTranslationServiceOK() {
		this.translation = new TranslationServiceMock();
		
		this.tester.setTranslationService(this.translation);
		
		assertEquals(this.tester.getTranslationService(), this.translation);
	}
	
	@Test(expected= IllegalStateException.class)
	public void testTranslationServiceException() {
		this.translation = new TranslationServiceMock();
		
		// set twice to force an exception:
		this.tester.setTranslationService(this.translation);
		this.tester.setTranslationService(this.translation);
	}
	
	
	@Test
	public void testOutputRecordManagerServiceOK() {
		this.outRecMgr = new OutputRecordManagerService();
		
		this.tester.setOutputRecordManagerService(this.outRecMgr);
		
		assertEquals(this.tester.getOutputRecordManagerService(), this.outRecMgr);
	}
	
	@Test(expected= IllegalStateException.class)
	public void testOutputRecordManagerServiceException() {
		this.outRecMgr = new OutputRecordManagerService();
		
		// set twice to force an exception:
		this.tester.setOutputRecordManagerService(this.outRecMgr);
		this.tester.setOutputRecordManagerService(this.outRecMgr);
	}
	
	
	@Test
	public void testExtensionServicesOK() {
		this.extensions = new HashMap<String, ISupportServiceExtension>();
		
		this.tester.setExtensionServices(this.extensions);
		
		assertEquals(this.tester.getExtensionServices(), this.extensions);
	}
	
	@Test(expected= IllegalStateException.class)
	public void testExtensionServicesException() {
		this.extensions = new HashMap<String, ISupportServiceExtension>();
		
		// set twice to force an exception:
		this.tester.setExtensionServices(this.extensions);
		this.tester.setExtensionServices(this.extensions);
	}

	
	
	/** ******************************************************************************************
	 * 
	 * test initialization / deinitialization:
	 *
	 *********************************************************************************************/
	
	@Test
	public void testInitialize() {
		try {
			this.tester.initialize();
			
		} catch (Exception e) {
			fail("Unexpected exception on initialization.");
		}
	}
	
	// TODO: Test case con asserts para verificar corrección en init / deinit individuales.
	
	
	@Test
	public void testDeinitialize() {
		try {
			this.tester.deinitialize();
			
		} catch (Exception e) {
			fail("Unexpected exception on deinitialization.");
		}
	}
}
