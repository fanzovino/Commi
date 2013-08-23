/** ******************************************************************************************
 * 
 * class ProcessingLoopTestCase
 * 
 * Unit Test for ProcessingLoop class.
 * 
 * Test coverage:
 * 		- setters / getters
 * 		- process layers
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
import org.junit.Test;

//import ar.com.telefonica.ws.amdocs.engine.FileProcessor;
import java.util.List;
import ar.com.telefonica.ws.amdocs.engine.priv.ProcessingLayer;
import ar.com.telefonica.ws.amdocs.engine.priv.ProcessingLoop;
import ar.com.telefonica.ws.amdocs.engine.priv.SupportServices;

public class ProcessingLoopTestCase {
	
	// instance to test:
	ProcessingLoop tester = new ProcessingLoop();
	
	// input test values:
	String name = "ordenesLoop";
	SupportServices support = new SupportServices();
	List<ProcessingLayer> layers = null;
	
	
	
	@Test
	public void testProcessingLoop() {
		this.tester = new ProcessingLoop(this.name, this.support, this.layers);
	}
	
	
	
	@Test
	public void testName() {
		this.tester.setName(this.name);
		
		assertEquals(this.tester.getName(), this.name);
	}
	
	@Test
	public void testSupportServices() {
		this.tester.setSupportServices(this.support);
		
		assertEquals(this.tester.getSupportServices(), this.support);
	}
	
	@Test
	public void testLayers() {
		
		// TODO: setup layers (fixture)
		
		this.tester.setLayers(this.layers);
		
		assertEquals(this.tester.getSupportServices(), this.layers);
	}
	
	
	
	@Test
	public void testProcessRecord() {
		
		// TODO: all
		
		fail("Not yet implemented");
	}

}
