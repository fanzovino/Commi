/** ******************************************************************************************
 * 
 * class ProcessingLayerTestCase
 * 
 * Unit Test for ProcessingLayer class.
 * 
 * Test coverage:
 * 		- getters / setters
 * 		- process with diferent paths for check / handle
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

import java.util.List;
import java.util.ArrayList;

import ar.com.telefonica.ws.amdocs.commissioning.engine.ObjectRecord;
import ar.com.telefonica.ws.amdocs.commissioning.engine.priv.IProcessingObject;
import ar.com.telefonica.ws.amdocs.commissioning.engine.priv.ProcessingLayer;
import ar.com.telefonica.ws.amdocs.commissioning.engine.priv.SupportServices;



public class ProcessingLayerTestCase {
	
	// instance to test:
	ProcessingLayer tester = new ProcessingLayer();
	
	// input test values:
	int level = 1;
	List<IProcessingObject> processors = null;
	
	ObjectRecord oRec = null;

	
	
	/** ******************************************************************************************
	 * 
	 * tester constructor:
	 *
	 *********************************************************************************************/
	
	@Before
	public void testProcessingLayer() {
		this.tester = new ProcessingLayer(this.level, this.processors);
		
		//assertNotNull(this.tester);
	}
	
	
	
	/** ******************************************************************************************
	 * 
	 * test setters/getters:
	 *
	 *********************************************************************************************/
	
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
	
	
	// TODO: Test Case con lista de processors que tenga nulls.
	
	
	/** ******************************************************************************************
	 * 
	 * test process records:
	 * 
	 * - Setea mock processors con respuestas predeterminadas a la condición de testing requerida.
	 *   Todos los casos se ejecutan con un caso de procesamiento ignorado + uno ad hoc para el test
	 *
	 *********************************************************************************************/
	
	private void initializeProcessors() { 
		this.processors = new ArrayList<IProcessingObject>();
		this.processors.add(1, new IProcessingObject() {
			public void setName(String name) { };
			public void setDescription(String description) { };
			public boolean handle(ObjectRecord oRec, SupportServices support) { return true; }
			public String getName() { return null; }
			public String getDescription() { return null; }
			public CheckResult check(ObjectRecord oRec, SupportServices support) { return CheckResult.IGNORE; }
		} );
		
		this.oRec = new ObjectRecord();
	}
	
	
	@Test
	public void testProcessRecordHandleOK() {
		
		this.initializeProcessors();
		
		this.processors.add(2, new IProcessingObject() {
			public void setName(String name) { };
			public void setDescription(String description) { };
			public boolean handle(ObjectRecord oRec, SupportServices support) { return true; }
			public String getName() { return null; }
			public String getDescription() { return null; }
			public CheckResult check(ObjectRecord oRec, SupportServices support) { return CheckResult.HANDLE; }
		} );
		
		try {
			assertTrue(this.tester.processRecord(oRec, null));
		
		} catch (Exception e) {
			fail("Unexpected exception");
		}
	}
	
	
	@Test
	public void testProcessRecordHandleFalse() {
		
		this.initializeProcessors();
		
		this.processors.add(2, new IProcessingObject() {
			public void setName(String name) { };
			public void setDescription(String description) { };
			public boolean handle(ObjectRecord oRec, SupportServices support) { return false; }
			public String getName() { return null; }
			public String getDescription() { return null; }
			public CheckResult check(ObjectRecord oRec, SupportServices support) { return CheckResult.HANDLE; }
		} );
		
		try {
			assertFalse(this.tester.processRecord(oRec, null));
		
		} catch (Exception e) {
			fail("Unexpected exception");
		}
	}
	
	
	@Test
	public void testProcessRecordHandleException() {
		
		this.initializeProcessors();
		
		this.processors.add(2, new IProcessingObject() {
			public void setName(String name) { };
			public void setDescription(String description) { };
			public boolean handle(ObjectRecord oRec, SupportServices support) { return true; }
			public String getName() { return null; }
			public String getDescription() { return null; }
			public CheckResult check(ObjectRecord oRec, SupportServices support) { return CheckResult.HANDLE; }
		} );
		
		try {
			this.tester.processRecord(oRec, null);
		
			fail("No se produjo la excepcion esperada");
			
		} catch (Exception e) {
			
		}
	}
	
	
	@Test
	public void testProcessRecordCheckCANCEL() {
		
		this.initializeProcessors();
		
		this.processors.add(2, new IProcessingObject() {
			public void setName(String name) { };
			public void setDescription(String description) { };
			public boolean handle(ObjectRecord oRec, SupportServices support) { return true; }
			public String getName() { return null; }
			public String getDescription() { return null; }
			public CheckResult check(ObjectRecord oRec, SupportServices support) { return CheckResult.CANCEL; }
		} );
		
		try {
			assertFalse(this.tester.processRecord(oRec, null));
		
		} catch (Exception e) {
			fail("Unexpected exception");
		}
	}
	
	
	@Test
	public void testProcessRecordCheckFATAL() {
		
		this.initializeProcessors();
		
		this.processors.add(2, new IProcessingObject() {
			public void setName(String name) { };
			public void setDescription(String description) { };
			public boolean handle(ObjectRecord oRec, SupportServices support) { return true; }
			public String getName() { return null; }
			public String getDescription() { return null; }
			public CheckResult check(ObjectRecord oRec, SupportServices support) { return CheckResult.FATAL; }
		} );
		
		try {
			assertFalse(this.tester.processRecord(oRec, null));
		
		} catch (Exception e) {
			fail("Unexpected exception");
		}
	}
	
	
	@Test
	public void testProcessRecordCheckException() {
		
		this.initializeProcessors();
		
		this.processors.add(2, new IProcessingObject() {
			public void setName(String name) { };
			public void setDescription(String description) { };
			public boolean handle(ObjectRecord oRec, SupportServices support) { return true; }
			public String getName() { return null; }
			public String getDescription() { return null; }
			public CheckResult check(ObjectRecord oRec, SupportServices support) { return CheckResult.HANDLE; }
		} );
		
		try {
			this.tester.processRecord(oRec, null);
		
			fail("No se produjo la excepcion esperada");
			
		} catch (Exception e) {
			
		}
	}

}
