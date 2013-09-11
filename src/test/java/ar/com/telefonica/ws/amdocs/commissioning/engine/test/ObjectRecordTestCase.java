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
 * @package	ar.com.telefonica.ws.amdocs.commissioning.engine.test
 * 
 * @author	FGA <fanzovino@plussistemas.com.ar>
 * @version 1.0
 * @created 21-Ago-2013
 * 
 *********************************************************************************************/

package ar.com.telefonica.ws.amdocs.commissioning.engine.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import ar.com.telefonica.ws.amdocs.commissioning.engine.ObjectRecord;
import ar.com.telefonica.ws.amdocs.commissioning.engine.OutputRecord;
import ar.com.telefonica.ws.amdocs.commissioning.engine.ObjectRecord.*;



public class ObjectRecordTestCase {
	
	// instance to test:
	ObjectRecord tester = null;
	// test input values:
	final static Long LONG_TEST = new Long(12345);
	final static Double DOUBLE_TEST = new Double(12345.67);
	final static BigDecimal BDECIMAL_TEST = new BigDecimal(12345.67);
	final static Date DATE_TEST = new Date(30082013);
	
	
	
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
	
	
	
	
	
	/** ******************************************************************************************
	 * 
	 * tester constructor:
	 *
	 *********************************************************************************************/
	
	
	@Before
	public void testObjectRecord() {
		this.tester = new ObjectRecord();
	}
	
	/*
	 * public interface:
	 */
	
	/*Seteo el RecordId para que evalue el seteo correcto del mismo 
	 */
	@Test
	public void testRecordIdOk() {
		String s = new String("--");
		this.tester.setRecordId(s);
		
		assertEquals(this.tester.getRecordId(),s);
		
	}
	

	/*Seteo el RecordId sobre uno existente para que devuelva la excepccion.
	 */
	@Test(expected= IllegalStateException.class)
	public void testRecordIdError() {
		String s = new String("--");
		this.tester.setRecordId(s);
		
		String s2 = new String("---");
		this.tester.setRecordId(s2);
		
		
	}
	

	
	
	/*Efectuo la implementacion del sellador para validar su funcionamiento Ok!
	 */
	@Test
	public void testSealOk() {
		this.tester.seal();
		assertTrue(this.tester.isSealed());
		
	}
	

	/*Una vez sellado el Objecto este no puede ser desellado por nadie.Espero una Excepcion.
	 */
	@Test(expected= IllegalStateException.class)
	public void testSealError() {
		this.tester.seal();
		this.tester.unSeal();
		
	}
	
	/*Testeo el seteo del Map Tag cuando este Ok!
	 */
	
	@Test
	public void testTagsOk() {
		
		this.tester = new ObjectRecord();
		Map<String,Object> tagTst = new HashMap<String,Object>();
		tagTst.put("1",new Object());
		tagTst.put("2",new Object());
		tagTst.put("3",new Object());
		
		this.tester.setTags(tagTst);
		
		assertEquals(this.tester.getTags(), tagTst);

	}
	
	/*Testeo el seteo del Map Tag cuando este devuelva una excepcion!
	 */
	
	@Test(expected= IllegalStateException.class)
	public void testTagsError() {
		
		this.tester = new ObjectRecord();
		//Armo un primer mapa de objetos para setearlo
		Map<String,Object> tagTst = new HashMap<String,Object>();
		tagTst.put("1",new Object());
		tagTst.put("2",new Object());
		tagTst.put("3",new Object());
		//seteo el primer mapa
		this.tester.setTags(tagTst);
		//Armo un segundo mapa de objetos para setearlo y probar la falla
		Map<String,Object> newTagTst = new HashMap<String,Object>();
		newTagTst.put("1A",new Object());
		newTagTst.put("2A",new Object());
		newTagTst.put("3A",new Object());

		//seteo el segundo mapa y espero la excepcion
		this.tester.setTags(newTagTst);
		
	}
	
	/*Test del RawInput por primera vez
	 */
	@Test
	public void testRawInputRecordOk() {
		
		this.tester = new ObjectRecord();
		Object rawInput = new Object();
		
		this.tester.setRawInputRecord(rawInput);
		
		assertEquals(this.tester.getRawInputRecord(), rawInput);
		
		
	}
	

	/*Test del RawInput generando excepcion en el caso que se quiera insertar otro rawInput Distinto.
	 */
	
	@Test(expected = IllegalStateException.class)
	public void testRawInputRecordError() {
		
		this.tester = new ObjectRecord();
		Object rawInput = new Object();
		//Seteo el nuevo objecto
		this.tester.setRawInputRecord(rawInput);
		
		Object rawInput2 = new Object();
		
		this.tester.setRawInputRecord(rawInput2);		
		
	}
	
	
	private void setupInputsForTests(){
		this.tester = new ObjectRecord();
		this.tester.setInput("boolOK", Boolean.TRUE);
		this.tester.setInput("boolErr", new String("FailedTest"));
		this.tester.setInput("LongOK", LONG_TEST);
		this.tester.setInput("LongErr", new String("FailedTest"));
		this.tester.setInput("DoubleOK", DOUBLE_TEST);
		this.tester.setInput("DoubleErr", new String("FailedTest"));
		this.tester.setInput("BigDecimalOK", BDECIMAL_TEST);
		this.tester.setInput("BigDecimalErr", new String("FailedTest"));
		this.tester.setInput("DateOK", DATE_TEST);
		this.tester.setInput("DateErr", new String("FailedTest"));
		
		this.tester.setInput("ListErr", new String("FailedTestArray"));
		
	}
	
	
	//Testeo la conversion booleana del getInput
	@Test
	public void testInputBoolOk() {
		//Setup enviroment for testCase
		setupInputsForTests();
		assertEquals(this.tester.getInputBool("boolOK"), Boolean.TRUE);
		
	}
	
	//Testeo la conversion booleana del getInput cuando genera la excepcion (no puede convertir el valor)
	@Test (expected = UnsupportedOperationException.class)
	public void testInputBoolError() {
		//Setup enviroment for testCase
		setupInputsForTests();
		this.tester.getInputBool("boolErr");
		
	}
	
	
	//Testeo la conversion Long del getInput
	@Test
	public void testInputLongOk() {
		//Setup enviroment for testCase
		setupInputsForTests();
		assertEquals(this.tester.getInputLong("LongOK"), LONG_TEST);
		
	}
	
	//Testeo la conversion Long del getInput cuando genera la excepcion (no puede convertir el valor)
	@Test (expected = UnsupportedOperationException.class)
	public void testInputLongError() {
		//Setup enviroment for testCase
		setupInputsForTests();
		this.tester.getInputLong("LongErr");
		
	}
	
	//Testeo la conversion Double del getInput
		@Test
	public void testInputDoubleOk() {
		//Setup enviroment for testCase
		setupInputsForTests();
		assertEquals(this.tester.getInputDouble("DoubleOK"), DOUBLE_TEST);
		
	}
		
	//Testeo la conversion Double del getInput cuando genera la excepcion (no puede convertir el valor)
	@Test (expected = UnsupportedOperationException.class)
	public void testInputDoubleError() {
		//Setup enviroment for testCase
		setupInputsForTests();
		this.tester.getInputDouble("DoubleErr");
		
	}
	
	//Testeo la conversion BigDecimal del getInput
	@Test
	public void testInputBigDecimalOk() {
		//Setup enviroment for testCase
		setupInputsForTests();
		assertEquals(this.tester.getInputBigDecimal("BigDecimalOK"), BDECIMAL_TEST);
		
	}
	
	//Testeo la conversion BigDecimal del getInput cuando genera la excepcion (no puede convertir el valor)
	@Test (expected = UnsupportedOperationException.class)
	public void testInputBigDecimalError() {
		//Setup enviroment for testCase
		setupInputsForTests();
		this.tester.getInputBigDecimal("BigDecimalErr");
		
	}
	
	//Testeo la conversion Date del getInput
	@Test
	public void testInputDateOk() {
		//Setup enviroment for testCase
		setupInputsForTests();
		assertEquals(this.tester.getInputDate("DateOK"), DATE_TEST);
		
	}
		
	//Testeo la conversion Date del getInput cuando genera la excepcion (no puede convertir el valor)
	@Test (expected = UnsupportedOperationException.class)
	public void testInputDateError() {
		//Setup enviroment for testCase
		setupInputsForTests();
		this.tester.getInputDate("DateErr");
			
	}
	
	
	//Testeo la conversion Date del getInput
	@Test
	public void testInputArrayOk() {
		//Setup enviroment for testCase
		List<String> listObj = new ArrayList<String>();
		listObj.add(new String("Commisiones"));
		listObj.add(LONG_TEST.toString());
		listObj.add(DOUBLE_TEST.toString());
		listObj.add(BDECIMAL_TEST.toString());
		listObj.add(DATE_TEST.toString());
		
		this.tester = new ObjectRecord();
		this.tester.setInput("ListOk", listObj);
	
		
		assertEquals(this.tester.getInputArray("ListOk",null), listObj);
		
	}
			
	//Testeo la conversion List del getInput cuando genera la excepcion (no puede convertir el valor)
	@Test (expected = UnsupportedOperationException.class)
	public void testInputArrayError() {
		setupInputsForTests();
		
		this.tester.getInputArray("ListErr", null);
		
		
	}
	
}
