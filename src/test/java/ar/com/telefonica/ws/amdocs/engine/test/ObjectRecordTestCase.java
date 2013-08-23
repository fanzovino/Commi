/** ******************************************************************************************
 * 
 * class ObjectRecordTestCase
 * 
 * Unit Test for ObjectRecord class.
 * 
 * Test coverage:
 * 		- object sealing
 * 		- setting / getting input data
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import ar.com.telefonica.ws.amdocs.engine.OutputRecord;
import ar.com.telefonica.ws.amdocs.engine.ObjectRecord;
import ar.com.telefonica.ws.amdocs.engine.ObjectRecord.*;



public class ObjectRecordTestCase {
	
	// instance to test:
	ObjectRecord tester = new ObjectRecord();
	
	// input test values:
	ProcessingStatus processingStatus = ProcessingStatus.PROCESSING; 
	ResultStatus resultStatus = ResultStatus.DATA; 
	
	// tags
	Map<String, Object> tags = new HashMap<String, Object>();
	
	// the raw object received from the record reader
	Object rawInputRecord = null;
	Map<String, Object> input = new HashMap<String, Object>();
	boolean sealed = false;
	
	// output records
	List<OutputRecord> output =	new ArrayList<OutputRecord>(16);
	
	
	
	/*
	 * tester constructor:
	 */
	
	@Test
	public void testObjectRecord() {
		this.output = new ArrayList<OutputRecord>(16);
	}
	
	
	
	/*
	 * public interface:
	 */
	
	@Test(expected= IllegalStateException.class)
	public void testSeal() {
		this.tester.seal();
		
		assertTrue(this.tester.isSealed());
		
		this.tester.unSeal();
	}
	
	
	@Test
	public void testTags() {
		
		// TODO.
		
		fail("Not yet implemented");
	}
	
	
	@Test
	public void testRawInputRecord() {
		
		// TODO.
		
		fail("Not yet implemented");
	}
	
	
	@Test
	public void testProcessingStatus() {
		
		// TODO.
		
		fail("Not yet implemented");
	}
	
	
	@Test
	public void testResultStatus() {
		
		// TODO.
		
		fail("Not yet implemented");
	}
	
	
	@Test
	public void testOutputRecords() {
		
		// TODO.
		
		fail("Not yet implemented");
	}
	
	
	@Test
	public void testInputData() {
		fail("Not yet implemented");
		
		/*
		 * TODO:
		 * 
		 * 1. setInput + getInput (Object)
		 * 2. setInput + getInputBool
		 * 3. setInput + getInputLong
		 * 4. setInput + getInputDouble
		 * 5. setInput + getInputBigDecimal
		 * 6. setInput + getInputDate
		 * 7. setInput + getInputString
		 * 8. setInput + getInputArray
		 * 
		 */
	}

}
