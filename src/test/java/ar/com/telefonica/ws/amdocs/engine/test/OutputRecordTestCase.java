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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import ar.com.telefonica.ws.amdocs.engine.ObjectRecord;
import ar.com.telefonica.ws.amdocs.engine.OutputRecord;



public class OutputRecordTestCase {
	
	// instance to test:
	OutputRecord tester = null;
	// test input values:
		final static Long LONG_TEST = new Long(12345);
		final static Double DOUBLE_TEST = new Double(12345.67);
		final static BigDecimal BDECIMAL_TEST = new BigDecimal(12345.67);
		final static Date DATE_TEST = new Date(30082013);
	
	// input test values:
	private Map<String, Object> attributes = null;
	private String type = null;
	
	
	
	/*
	 * tester constructor:
	 */
	
	@Before
	private void setAttributeTest() {
		
		// TODO: construct fixture
		this.type = "OutputType";
		this.attributes = new HashMap<String, Object>();
		this.attributes.put("T1",new ObjectRecord());
		this.attributes.put("T2",new ObjectRecord());
		this.attributes.put("T3",new ObjectRecord());
		this.attributes.put("T4",new ObjectRecord());
		
//		this.tester = new OutputRecord(this.type, this.attributes);
		
	}
	
	@Before
	private void setupInputsForTests(){
		this.tester = new OutputRecord();
		Map<String, Object> mapAttr = new HashMap<>();
		
		mapAttr.put("boolOK", Boolean.TRUE);
		mapAttr.put("boolErr", new String("FailedTest"));
		mapAttr.put("LongOK", LONG_TEST);
		mapAttr.put("LongErr", new String("FailedTest"));
		mapAttr.put("DoubleOK", DOUBLE_TEST);
		mapAttr.put("DoubleErr", new String("FailedTest"));
		mapAttr.put("BigDecimalOK", BDECIMAL_TEST);
		mapAttr.put("BigDecimalErr", new String("FailedTest"));
		mapAttr.put("DateOK", DATE_TEST);
		mapAttr.put("DateErr", new String("FailedTest"));
		
		mapAttr.put("ListErr", new String("FailedTestArray"));
		
		this.tester.setAttributes(mapAttr);
		
	}
	
	
	/*
	 * public methods:
	 */
	
	
	/*
	 * Test Setup() to generate the exception.
	 */
	@Test(expected= IllegalStateException.class)
	public void testSetupError() {
		
		setAttributeTest();
		//Instancio un outputRecord para que selle el Objeto
		this.tester = new OutputRecord(this.type, this.attributes);
		//Seteo nuevamenta para recibir la exception
		this.tester.setup("NewType", this.attributes);
	}

	//Test Seal() is True
	@Test
	public void testSealOk() {
		setAttributeTest();
		//Instancio un outputRecord para que selle el Objeto
		this.tester = new OutputRecord(this.type, this.attributes);
		
		assertTrue(this.tester.isSealed());
		
		this.tester.unSeal();
	}
	
	//Test Seal() is new change
	@Test(expected= IllegalStateException.class)
	public void testSealError() {
		setAttributeTest();
		//Instancio un outputRecord para que selle el Objeto
		this.tester = new OutputRecord(this.type, this.attributes);
		this.tester.unSeal();
	}
	
	
	/*
	 * setters/getters:
	 */
	
	/*Teste Setter de mapeo de Attributes del outputRecord
	 */
	
	@Test
	public void testAttributesOK() {
//		fail("Not yet implemented");
		setAttributeTest();
		this.tester = new OutputRecord();
		this.tester.setAttributes(this.attributes);
		assertEquals(this.tester.getAttributes(),this.attributes);
		
		
	}
	
	/*Teste Setter de mapeo de Attributes del outputRecord
	 */
	
	@Test(expected= IllegalStateException.class)
	public void testAttributesError() {
//		fail("Not yet implemented");
		setAttributeTest();
		this.tester = new OutputRecord();
		this.tester.setAttributes(this.attributes);
		
		Map<String, Object> t = new HashMap<>();
		t.put("TT1", new Object());
		t.put("TT2", new Object());
		t.put("TT3", new Object());
		
		this.tester.setAttributes(t);
		
	}
	
	
	/*Tester de Type variable; AssertTrue
	 */
	@Test
	public void testTypeOK() {
		setAttributeTest();
		this.tester = new OutputRecord();
		this.tester.setType(this.type);
		assertEquals(this.tester.getType(), this.type);
	}
	/*Tester de Type variable; Expected exception if the type variable is not null
	 */
	@Test (expected = IllegalStateException.class)
	public void testTypeError() {
		setAttributeTest();
		this.tester = new OutputRecord();
		this.tester.setType(this.type);
		String newType = "OtherType";
		this.tester.setType(newType);
	}
	
	/*Test para setear /agregar un key-value al mapa de atributos
	 */
	@Test
	public void testSetAttrOk(){
		//Seteo el ambiente de las variables
		this.tester = new OutputRecord();
		//Seteo los attr en la clases tester
		this.tester.setAttributes(this.attributes);
		//setteo un attr contenido en el mapa de la clase tester
		this.tester.set("T1",new String("TestOK"));
		//Verifico si el attr modificado fue efectuado correctamente
		assertEquals((String) this.tester.get("T1"), new String("TestOK"));
		
		
	}
	
	/*Test para setear un key-value al mapa de atributos.Seteo un attr despues de que el objecto quede sellado.Espero una excepcion a cambio.
	 */
	@Test (expected = IllegalStateException.class)
	public void testSetAttrError1(){
		setAttributeTest();
		this.tester = new OutputRecord(this.type,this.attributes);
		//Expect the excepcion.
		this.tester.set("T1", new String("TestError1"));
		
	}
	/*Test para setear un key-value al mapa de atributos.Seteo un attr no contenido en el map.Espero una excepcion a cambio.
	 */
	@Test (expected = UnsupportedOperationException.class)
	public void testSetAttrError2(){
		setAttributeTest();
		this.tester = new OutputRecord();
		this.tester.setAttributes(this.attributes);
		//Expect the excepcion.
		this.tester.set("T8", new String("TestError1"));
		
	}
	
	
	
	

	//Testeo la conversion booleana del getAttr
	@Test
	public void testAttributeBoolOk() {
		//Setup enviroment for testCase
		setupInputsForTests();
		assertEquals(this.tester.getBool("boolOK"), Boolean.TRUE);
		
	}
	
	//Testeo la conversion booleana del get cuando genera la excepcion (no puede convertir el valor)
	@Test (expected = UnsupportedOperationException.class)
	public void testAttributeBoolError() {
		//Setup enviroment for testCase
		setupInputsForTests();
		this.tester.getBool("boolErr");
		
	}
	
	
	//Testeo la conversion Long del getAttr
	@Test
	public void testAttributeLongOk() {
		//Setup enviroment for testCase
		setupInputsForTests();
		assertEquals(this.tester.getLong("LongOK"), LONG_TEST);
		
	}
	
	//Testeo la conversion Long del getAttr cuando genera la excepcion (no puede convertir el valor)
	@Test (expected = UnsupportedOperationException.class)
	public void testAttributeLongError() {
		//Setup enviroment for testCase
		setupInputsForTests();
		this.tester.getLong("LongErr");
		
	}
	
	//Testeo la conversion Double del Attribute
		@Test
	public void testAttributeDoubleOk() {
		//Setup enviroment for testCase
		setupInputsForTests();
		assertEquals(this.tester.getDouble("DoubleOK"), DOUBLE_TEST);
		
	}
		
	//Testeo la conversion Double del getAttribute cuando genera la excepcion (no puede convertir el valor)
	@Test (expected = UnsupportedOperationException.class)
	public void testAttributeDoubleError() {
		//Setup enviroment for testCase
		setupInputsForTests();
		this.tester.getDouble("DoubleErr");
		
	}
	
	//Testeo la conversion BigDecimal del getAttribute
	@Test
	public void testAttributeBigDecimalOk() {
		//Setup enviroment for testCase
		setupInputsForTests();
		assertEquals(this.tester.getBigDecimal("BigDecimalOK"), BDECIMAL_TEST);
		
	}
	
	//Testeo la conversion BigDecimal del getAttribute cuando genera la excepcion (no puede convertir el valor)
	@Test (expected = UnsupportedOperationException.class)
	public void testAttributeBigDecimalError() {
		//Setup enviroment for testCase
		setupInputsForTests();
		this.tester.getBigDecimal("BigDecimalErr");
		
	}
	
	//Testeo la conversion Date del getAttribute
	@Test
	public void testAttributeDateOk() {
		//Setup enviroment for testCase
		setupInputsForTests();
		assertEquals(this.tester.getDate("DateOK"), DATE_TEST);
		
	}
		
	//Testeo la conversion Date del getAttribute cuando genera la excepcion (no puede convertir el valor)
	@Test (expected = UnsupportedOperationException.class)
	public void testAttributeDateError() {
		//Setup enviroment for testCase
		setupInputsForTests();
		this.tester.getDate("DateErr");
			
	}
	
	
	//Testeo la conversion Date del getAttribute
	@Test
	public void testAttributeArrayOk() {
		//Setup enviroment for testCase
		List<String> listObj = new ArrayList<String>();
		listObj.add(new String("OutPutRec-Commi"));
		listObj.add(LONG_TEST.toString());
		listObj.add(DOUBLE_TEST.toString());
		listObj.add(BDECIMAL_TEST.toString());
		listObj.add(DATE_TEST.toString());
		
		this.tester = new OutputRecord();
		Map<String, Object> map = new HashMap<>();
		map.put("ListOk", listObj);
		
		this.tester.setAttributes(map);
	
		
		assertEquals(this.tester.getArray("ListOk",null), listObj);
		
	}
			
	//Testeo la conversion List del getInput cuando genera la excepcion (no puede convertir el valor)
	@Test (expected = UnsupportedOperationException.class)
	public void testInputArrayError() {
		setupInputsForTests();
		
		this.tester.getArray("ListErr", null);
		
		
	}
	
	
	

}
