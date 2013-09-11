/** ******************************************************************************************
 * 
 * class TranslationServiceMockTestCase
 * 
 * Unit Test for TranslationServiceMock class + parent class TranslationService.
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
 * @created 14-Ago-2013
 * 
 *********************************************************************************************/

package ar.com.telefonica.ws.amdocs.commissioning.engine.test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import ar.com.telefonica.ws.amdocs.commissioning.engine.priv.ITranslationServiceExtension;
import ar.com.telefonica.ws.amdocs.commissioning.engine.priv.TranslationServiceMock;

public class TranslationServiceMockTestCase {
	
	TranslationServiceMock tester = null; 
	
	
	
	/** ******************************************************************************************
	 * 
	 * tester constructor:
	 *
	 *********************************************************************************************/
	
	@Before
	public void testTranslationService() {
		this.tester = new TranslationServiceMock();
	}
	
	
	
	/** ******************************************************************************************
	 * 
	 * test setters/getters:
	 *
	 *********************************************************************************************/
	
	// Origin System:
	
	@Test
	public void testSetOriginSystemOk() {
		String originSys = new String("OriginSysTest");
		
		this.tester.setOriginSystem(originSys);
		assertEquals(this.tester.getOriginSystem(), originSys);
	}
	
	
	@Test(expected = UnsupportedOperationException.class)
	public void testOriginSystemException() {
		String originSys = new String ("OriginSysTest");
		this.tester.setOriginSystem(originSys);
		originSys = new String("OriginSysTestBIS");
		
		this.tester.setOriginSystem(originSys);
		
	}
	
	
	// Target System:
	
	@Test
	public void testTargetSystemOK() {
		String targetSys = new String("TargetSysTest");
		this.tester.setTargetSystem(targetSys);
		assertEquals(this.tester.getTargetSystem(), targetSys);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testTargetSystemException() {
		String targetSys = new String("TargetSysTest");
		this.tester.setTargetSystem(targetSys);
		targetSys = new String("TargetSysTestBIS");
		
		this.tester.setOriginSystem(targetSys);
		
	}
	
	
	// Default Origin:
	
	@Test
	public void testDefaultOriginValueOk() {
		String dftOriginValue = "**Default**";
		this.tester.setDefaultOriginValue(dftOriginValue);
		
		assertEquals(this.tester.getDefaultOriginValue(), dftOriginValue);
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void testDefaultOriginValueException() {
		String dftOriginValue = "**Default**";
		this.tester.setDefaultOriginValue(dftOriginValue);

		dftOriginValue = "**IsNotDefault**";
		this.tester.setDefaultOriginValue(dftOriginValue);
	}
	
	
	//Seteo el map de extensiones. Si contiene elementos devuelvo exception
	
	@Test
	public void testExtensionsOK() {
		//Map<String, ITranslationServiceExtension>
		Map<String, ITranslationServiceExtension> extensions = new HashMap<String, ITranslationServiceExtension>();
		ITranslationServiceExtension trans = null;
		
		extensions.put("E1", trans);
		extensions.put("E2", trans);
		extensions.put("E3", trans);
		this.tester.setExtensions(extensions);
		
		assertTrue(extensions == this.tester.getExtensions());
		
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void testExtensionsException() {
		//Map<String, ITranslationServiceExtension>
		Map<String, ITranslationServiceExtension> extensions = new HashMap<String, ITranslationServiceExtension>();
		ITranslationServiceExtension trans = null;
		extensions.put("E1", trans);
		extensions.put("E2", trans);
		extensions.put("E3", trans);
		this.tester.setExtensions(extensions);
		
		Map<String, ITranslationServiceExtension> ext2 = new HashMap<String, ITranslationServiceExtension>();
		ext2.put("EE1", trans);
		ext2.put("EE2", trans);
		ext2.put("EE3", trans);
		
		//con este set espero la excepcion
		this.tester.setExtensions(ext2);
	}

	
	
	//TODO: Test de translators 
	
	@Test
	public void testTranslateToLong() {
		fail("Not yet implemented");
	}

	@Test
	public void testTranslateToString() {
		fail("Not yet implemented");
	}
	
	
	
	/** ******************************************************************************************
	 * 
	 * test initialization / deinitialization:
	 *
	 *********************************************************************************************/
	
	//TODO: Test (De)Initialize
	
	@Test
	public void testInitialize() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeinitialize() {
		fail("Not yet implemented");
	}

}
