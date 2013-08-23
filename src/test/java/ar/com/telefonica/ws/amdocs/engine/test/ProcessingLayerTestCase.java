/** ******************************************************************************************
 * 
 * class ProcessingLayerTestCase
 * 
 * Unit Test for ProcessingLayer class.
 * 
 * Test coverage:
 * 		- getters / setters
 * 		- process handlers
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
import java.util.List;

import ar.com.telefonica.ws.amdocs.engine.priv.IProcessingObject;
import ar.com.telefonica.ws.amdocs.engine.priv.ProcessingLayer;



public class ProcessingLayerTestCase {
	
	// instance to test:
	ProcessingLayer tester = new ProcessingLayer();
	
	// input test values:
	int level = 1;
	List<IProcessingObject> processors = null;
	
	
	
	@Test
	public void testProcessingLayer() {
		this.tester = new ProcessingLayer(this.level, this.processors);
		
		assertNotNull(this.tester);
	}
	
	
	
	@Test
	public void testLevel() {
		this.tester.setLevel(this.level);
		
		assertEquals(this.tester.getLevel(), this.level);
	}

	@Test
	public void testProcessors() {
		this.tester.setProcessors(this.processors);
		
		assertEquals(this.tester.getProcessors(), this.processors);
	}
	
	
	
	@Test
	public void testProcessRecord() {
		fail("Not yet implemented");
	}

}
