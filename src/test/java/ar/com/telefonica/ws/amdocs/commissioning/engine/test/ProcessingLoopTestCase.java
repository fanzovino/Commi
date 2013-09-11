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
 * @package	ar.com.telefonica.ws.amdocs.commissioning.engine.test
 * 
 * @author	FDB <delbarriof@plussistemas.com.ar>
 * @version 1.0
 * @created 14-Ago-2013
 * 
 *********************************************************************************************/

package ar.com.telefonica.ws.amdocs.commissioning.engine.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import ar.com.telefonica.ws.amdocs.commissioning.engine.ObjectRecord;
import ar.com.telefonica.ws.amdocs.commissioning.engine.ObjectRecord.ResultStatus;
import ar.com.telefonica.ws.amdocs.commissioning.engine.priv.ProcessingLayer;
import ar.com.telefonica.ws.amdocs.commissioning.engine.priv.ProcessingLoop;
import ar.com.telefonica.ws.amdocs.commissioning.engine.priv.SupportServices;



public class ProcessingLoopTestCase {
	
	// instance to test:
	ProcessingLoop tester = null;
	
	// input test values:
	String name = null;
	SupportServices support = null;
	List<ProcessingLayer> layers = null;
	
	// output test variables:
	ObjectRecord oRec = null;
	
	
	
	/** ******************************************************************************************
	 * 
	 * tester constructor:
	 *
	 *********************************************************************************************/
	
	@Before
	public void testProcessingLoop() {
		this.name = "ordenesLoop";
		this.support = new SupportServices();
		this.layers = null;
		
		this.tester = new ProcessingLoop(this.name, this.support, this.layers);
	}
	
	
	
	/** ******************************************************************************************
	 * 
	 * test setters/getters:
	 *
	 *********************************************************************************************/
	
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
		this.layers = new ArrayList<ProcessingLayer>();

		this.layers.add(1, new ProcessingLayer(1, null));
		this.layers.add(2, new ProcessingLayer());
		
		this.tester.setLayers(this.layers);
		
		assertEquals(this.tester.getLayers(), this.layers);
	}
	
	
	
	/** ******************************************************************************************
	 * 
	 * test process records:
	 *
	 *********************************************************************************************/
	
	@Test
	public void testProcessRecordOK() {
		
		this.oRec = new ObjectRecord();
		
		// TODO: oRec fixture.
		
		// Test execution:
		try {
			this.tester.processRecord(this.oRec);
			
			// OK path
			
		} catch (Exception e) {
			
			// fail path:
			assertEquals(this.oRec.getResultStatus(), ResultStatus.ERROR);
		}
		
		// fail("Not yet implemented");
	}
	
	
	@Test
	public void testProcessRecordException() {
		
		// TODO: all
		
		fail("Not yet implemented");
	}

}
