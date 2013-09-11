/** ******************************************************************************************
 * 
 * class TranslationServiceTestCase
 * 
 * Unit Test for TranslationService class.
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
 * @created 14-Ago-2013
 * 
 *********************************************************************************************/

package ar.com.telefonica.ws.amdocs.engine.test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import ar.com.telefonica.ws.amdocs.engine.priv.ITranslationServiceExtension;
import ar.com.telefonica.ws.amdocs.engine.priv.TranslationService;

public class TranslationServiceTestCase {
	
	private TranslationService tester = null; 

	@Test
	public void testTranslationService() {
		fail("Not yet implemented");
	}

	
	/*Testeo del OriginSys cuando se setea y el seteo es correcto a lo esperado.
	 */
	@Test
	public void testSetOriginSystemOk() {
		String originSys = new String("OriginSysTest");
		
		this.tester.setOriginSystem(originSys);
		assertEquals(this.tester.getOriginSystem(), originSys);
		
	}
	
	
	/* Espero la exception si el OriginSys ya fue seteado anteriormente.(not Null) 
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testSetOriginSystemException() {
		String originSys = new String ("OriginSysTest");
		this.tester.setOriginSystem(originSys);
		originSys = new String("OriginSysTestBIS");

		 this.tester.setOriginSystem(originSys);
		
	}
	

	
	@Test
	public void testSetTargetSystemOK() {
		String targetSys = new String("TargetSysTest");
		this.tester.setTargetSystem(targetSys);
		assertEquals(this.tester.getTargetSystem(), targetSys);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testSetTargetSystemException() {
		String targetSys = new String("TargetSysTest");
		this.tester.setTargetSystem(targetSys);
		targetSys = new String("TargetSysTestBIS");

		this.tester.setOriginSystem(targetSys);
		
	}
	
	//Seteo el map de extensiones.
	@Test
	public void testSetExtensionsOK() {
		//Map<String, ITranslationServiceExtension>
		Map<String, ITranslationServiceExtension> extensions = new HashMap<String, ITranslationServiceExtension>();
		ITranslationServiceExtension trans = null;
		
		extensions.put("E1", trans);
		extensions.put("E2", trans);
		extensions.put("E3", trans);
		this.tester.setExtensions(extensions);
		
		assertTrue(extensions == this.tester.getExtensions());
		
		
	}
	//Seteo el map de extensiones.Si contiene elementos devuelvo exception
	@Test(expected = UnsupportedOperationException.class)
	public void testSetExtensionsException() {
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
