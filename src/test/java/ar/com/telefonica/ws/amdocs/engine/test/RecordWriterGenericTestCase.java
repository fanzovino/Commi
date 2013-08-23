/** ******************************************************************************************
 * 
 * class RecordWriterGenericTestCase
 * 
 * Unit Test for RecordWriterGeneric class.
 * 
 * Test coverage:
 * 		- setters / getters
 * 		- record writing
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
import java.util.List;

import org.junit.Test;

import ar.com.telefonica.ws.amdocs.engine.ObjectRecord;
import ar.com.telefonica.ws.amdocs.engine.OutputRecord;
import ar.com.telefonica.ws.amdocs.engine.RecordWriterGeneric;



public class RecordWriterGenericTestCase {
	
	// instance to test:
	RecordWriterGeneric tester = new RecordWriterGeneric();
	
	// test input values:
	private String types = "sarasa";
	private ObjectRecord oRec = new ObjectRecord();
	// private List<OutputRecord> outPutRecList = new ArrayList<OutputRecord>();
	
	
	
	/*
	 * tester constructor:
	 */
	
	@Test
	public void testRecordWriterGeneric() {
		this.tester = new RecordWriterGeneric(this.oRec);
	}
	
	
	
	/*
	 * setters/getters:
	 */
	
	@Test
	public void testTypes() {
		this.tester.setTypes(this.types);
		
		assertEquals(this.tester.getTypes(), this.types);
	}
	
	
	@Test
	public void testObjRecord() {
		this.tester.setObjRecord(this.oRec);
		
		assertEquals(this.tester.getObjRecord(), this.oRec);
	}
	
	
	
	/*
	 * writing:
	 */
	
	@Test
	public void testWrite() {
		
		// TODO:
		
		fail("Not yet implemented");
	}

}
