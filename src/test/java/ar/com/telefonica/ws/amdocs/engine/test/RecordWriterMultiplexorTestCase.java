/** ******************************************************************************************
 * 
 * class RecordWriterMultiplexorTestCase
 * 
 * Unit Test for RecordWriterMultiplexor class.
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
 * @created 16-Ago-2013
 * 
 *********************************************************************************************/

package ar.com.telefonica.ws.amdocs.engine.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import ar.com.telefonica.ws.amdocs.engine.ObjectRecord;
import ar.com.telefonica.ws.amdocs.engine.RecordWriterGeneric;
import ar.com.telefonica.ws.amdocs.engine.RecordWriterMultiplexor;



public class RecordWriterMultiplexorTestCase {
	
	// instance to test:
	RecordWriterMultiplexor tester = new RecordWriterMultiplexor();
	
	// test input values:
	RecordWriterGeneric generator;
	
	List<String> typesOutput  = new ArrayList<String>();
	List<ObjectRecord> oRecList = new ArrayList<ObjectRecord>();
	
	
	
	/*
	 * tester constructor:
	 */
	
	@Test
	public void testRecordWriterMultiplexor() {
		this.tester = new RecordWriterMultiplexor();
	}
	
	
	
	/*
	 * setters/getters:
	 */
	
	@Test
	public void testTypesOutput() {
		this.tester.setTypesOutput(this.typesOutput);
		
		assertEquals(this.tester.getTypesOutput(), this.typesOutput);
	}
	
	@Test
	public void testOrecList() {
		this.tester.setORecList(this.oRecList);
		
		assertEquals(this.tester.getORecList(), this.oRecList);
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
