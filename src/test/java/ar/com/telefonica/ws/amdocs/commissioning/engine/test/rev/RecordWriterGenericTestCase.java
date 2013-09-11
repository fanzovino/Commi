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
 * @package	ar.com.telefonica.ws.amdocs.commissioning.engine.test
 * 
 * @author	FDB <delbarriof@plussistemas.com.ar>
 * @version 1.0
 * @created 19-Ago-2013
 * 
 *********************************************************************************************/

package ar.com.telefonica.ws.amdocs.commissioning.engine.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;

import ar.com.telefonica.ws.amdocs.commissioning.engine.ObjectRecord;
import ar.com.telefonica.ws.amdocs.commissioning.engine.OutputRecord;
import ar.com.telefonica.ws.amdocs.commissioning.engine.RecordWriterGeneric;



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
	 * Es el encargado de generar el archivo correspondiente.
	 * Si el Type de outputRecord enviado esta contenido dentro de los type
	 * definidos en el contexto Spring puedo generar el archivo objectRecord.processingStatus=COMPLETE.
	 *  Sino devuelve excepcion
	 */
	
	@Test
	public void testWriteOk() {
		ObjectRecord oRec = new ObjectRecord();
		
		oRec.setProcessingStatus(ObjectRecord.ProcessingStatus.PROCESSING);
		
		OutputRecord outPutRec = new OutputRecord("outPutRec", new HashMap<String, Object>());
		oRec.addOutputRecord(outPutRec);
		
		this.tester = new RecordWriterGeneric();
		this.tester.setOutPutRecList((List<OutputRecord>) outPutRec);
		this.tester.setTypes("outPutRec");
		
		try {
			this.tester.write((List<? extends ObjectRecord>) oRec);
			assertEquals(this.tester.getObjRecord().getProcessingStatus(), ObjectRecord.ProcessingStatus.COMPLETE);
		} catch (Exception e) {
			fail("No se esperaba una excepcion");
		}
	}
}
