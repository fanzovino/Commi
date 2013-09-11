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
 * @package	ar.com.telefonica.ws.amdocs.commissioning.engine.test
 * 
 * @author	FDB <delbarriof@plussistemas.com.ar>
 * @version 1.0
 * @created 16-Ago-2013
 * 
 *********************************************************************************************/

package ar.com.telefonica.ws.amdocs.commissioning.engine.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import ar.com.telefonica.ws.amdocs.commissioning.engine.ObjectRecord;
import ar.com.telefonica.ws.amdocs.commissioning.engine.OutputRecord;
import ar.com.telefonica.ws.amdocs.commissioning.engine.RecordWriterGeneric;
import ar.com.telefonica.ws.amdocs.commissioning.engine.RecordWriterMultiplexor;



public class RecordWriterMultiplexorTestCase {
	
	// instance to test:
	RecordWriterMultiplexor tester = new RecordWriterMultiplexor();
	
	// test input values:
	RecordWriterGeneric generator;
	
	List<OutputRecord> typesOutput  = new ArrayList<OutputRecord>();
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
	 * Es el encargado validar el ObjRec.
	 * Si el ObjRec esta en ERROR genero el log de error con el numero de operacion.
	 * Si el OutPutType del ObjRecord no esta en el listado genero el log apropiado.
	 * Envio el ObjRecord a la clase RecordWriterGeneric.
	 */
	
	
	@Test
	public void testWriteOk() {
		ObjectRecord oRec = new ObjectRecord();
		OutputRecord outPutRec = new OutputRecord("outPutRec", new HashMap<String, Object>());
		oRec.addOutputRecord(outPutRec);
		oRec.setResultStatus(ObjectRecord.ResultStatus.DATA);
		this.tester = new RecordWriterMultiplexor();
		this.tester.setTypesOutput((List<OutputRecord>) outPutRec);
		this.tester.setORecList((List<ObjectRecord>) oRec);
		
		assertTrue(this.tester.checkObjRecToWrite(oRec));
		
		
	}
	
	/*
	 * writing:
	 * Es el encargado validar el ObjRec.
	 * Si el ObjRec esta en ERROR genero el log de error con el numero de operacion.
	 
	 */
	@Test
	public void testWriteFailObjectRecordERRORStatus() {
		ObjectRecord oRec = new ObjectRecord();
		OutputRecord outPutRec = new OutputRecord("outPutRec", new HashMap<String, Object>());
		oRec.addOutputRecord(outPutRec);
		oRec.setResultStatus(ObjectRecord.ResultStatus.ERROR);
		this.tester = new RecordWriterMultiplexor();
		this.tester.setTypesOutput((List<OutputRecord>) outPutRec);
		this.tester.setORecList((List<ObjectRecord>) oRec);
		
		//Devuelvo false para el objRecord en Error
		assertFalse(this.tester.checkObjRecToWrite(oRec));
		
		
	}
	
	/*
	 * writing:
	 * Es el encargado validar el ObjRec.
	 * Si el OutPutType del ObjRecord no esta en el listado genero el log apropiado.	 
	 */
	@Test
	public void testWriteFailOutputRecordNotContained() {
		ObjectRecord oRec = new ObjectRecord();
		OutputRecord outPutRec = new OutputRecord("outPutRec", new HashMap<String, Object>());
		
		oRec.addOutputRecord(new OutputRecord("outPutRecFAIL", new HashMap<String, Object>()));
		oRec.setResultStatus(ObjectRecord.ResultStatus.DATA);
		this.tester = new RecordWriterMultiplexor();
		this.tester.setTypesOutput((List<OutputRecord>) outPutRec);
		this.tester.setORecList((List<ObjectRecord>) oRec);
		
		//Devuelvo false para el outputRecord no contenido.
		assertFalse(this.tester.checkObjRecToWrite(oRec));
		
		
	}
	
	
	
	/*
	 * writing:
	 * Es el encargado validar el ObjRec.
	 * Si el OutPutType del ObjRecord no esta en el listado genero el log apropiado.	 
	 */
	@Test(expected = Exception.class)
	public void testWriteFailExpectedException() {
		
		this.tester = new RecordWriterMultiplexor();
		//Devuelvo false para el outputRecord no contenido.
		this.tester.checkObjRecToWrite(new ObjectRecord());
		
		
	}

}
