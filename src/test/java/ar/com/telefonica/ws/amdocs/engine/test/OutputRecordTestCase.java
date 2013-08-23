/** ******************************************************************************************
 * 
 * class OutputRecordTestCase
 * 
 * Unit Test for OutputRecord class.
 * 
 * Test coverage:
 * 		- object sealing and setup
 * 		- setting / getting output data
 * 		- other setters / getters
 * 
 *********************************************************************************************
 * 
 * @package	ar.com.telefonica.ws.amdocs.engine.test
 * 
 * @author	FDB <delbarriof@plussistemas.com.ar>
 * @version 1.0
 * @created 19-Ago-2013
 * 
 *********************************************************************************************/

package ar.com.telefonica.ws.amdocs.engine.test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import ar.com.telefonica.ws.amdocs.engine.ObjectRecord;
import ar.com.telefonica.ws.amdocs.engine.OutputRecord;



public class OutputRecordTestCase {
	
	// instance to test:
	OutputRecord tester = new OutputRecord();
	
	// input test values:
	private Map<String, Object> attributes = null;
	
	private String type = null;
	
	
	
	/*
	 * tester constructor:
	 */
	
	@Test
	public void testOutputRecord() {
		
		// TODO: construct fixture
		this.type = "OutputType";
		this.attributes = new HashMap<String, Object>();
		this.attributes.put("T1",new ObjectRecord());
		this.attributes.put("T2",new ObjectRecord());
		this.attributes.put("T3",new ObjectRecord());
		this.attributes.put("T4",new ObjectRecord());
		
		this.tester = new OutputRecord(this.type, this.attributes);
	}
	
	
	
	/*
	 * public methods:
	 */
	
	@Test(expected= IllegalStateException.class)
	public void testSetup() {
		// this.tester.unSeal();
		
		// TODO: asserts on sealing.
		
		this.tester.seal();
		
		this.tester.setup(this.type, this.attributes);
	}

	@Test
	public void testSeal() {
		this.tester.seal();
		
		assertTrue(this.tester.isSealed());
		
		this.tester.unSeal();
	}
	
	
	
	/*
	 * setters/getters:
	 */
	
	@Test
	public void testAttributes() {
//		fail("Not yet implemented");
		assertTrue(this.tester.getAttributes()!= null);
		
		
	}
	
	@Test
	public void testType() {
		fail("Not yet implemented");
	}
	
	
	@Test
	public void testOutputData() {
		fail("Not yet implemented");
		
		/*
		 * TODO:
		 * 
		 * 1. set + get (Object)
		 * 2. set + getBool
		 * 3. set + getLong
		 * 4. set + getDouble
		 * 5. set + getBigDecimal
		 * 6. set + getDate
		 * 7. set + getString
		 * 8. set + getArray
		 * 
		 */
	}

}
