/** ******************************************************************************************
 * 
 * class OutputRecordManagerServiceTestCase
 * 
 * Unit Test for OutputRecordManagerService class.
 * 
 * Test coverage:
 * 		- setters / getters
 * 		- concrete initialization / deinitialization
 * 
 *********************************************************************************************
 * 
 * @package	ar.com.telefonica.ws.amdocs.commissioning.engine.test
 * 
 * @author	FDB <delbarriof@plussistemas.com.ar>
 * @version 1.0
 * @created 19-Ago-2013
 * 
 *********************************************************************************************/

package ar.com.telefonica.ws.amdocs.commissioning.engine.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ar.com.telefonica.ws.amdocs.commissioning.engine.priv.OutputRecordManagerService;



public class OutputRecordManagerServiceTestCase {

	// instance to test:
	OutputRecordManagerService tester = new OutputRecordManagerService();
	
	String configFile = null;
	
	
	
	/** ******************************************************************************************
	 * 
	 * tester constructor:
	 *
	 *********************************************************************************************/
	
	@Before
	public void testOutputRecordManagerService() {
		this.tester = new OutputRecordManagerService();
	}
	
	
	
	/** ******************************************************************************************
	 * 
	 * test setters/getters:
	 *
	 *********************************************************************************************/
	
	@Test
	public void testConfigFile() {
		this.tester.setConfigFile(this.configFile);
		
		assertEquals(this.tester.getConfigFile(), this.configFile);
	}
	
	
	@Test
	public void testGet() {
		fail("Not yet implemented");
	}
	
	
	
	/** ******************************************************************************************
	 * 
	 * test initialization / deinitialization:
	 *
	 *********************************************************************************************/
	
	@Test
	public void testInitialize() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testDeinitialize() {
		fail("Not yet implemented");
	}

}
