/** ******************************************************************************************
 * 
 * class FileProcessorTestCase
 * 
 * Unit Test for FileProcessor class.
 * 
 * Test coverage:
 * 		- FileType set/get
 * 		- process loop
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

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import ar.com.telefonica.ws.amdocs.engine.FileProcessor;
import ar.com.telefonica.ws.amdocs.engine.IInputRecord;
import ar.com.telefonica.ws.amdocs.engine.priv.IProcessingObject;
import ar.com.telefonica.ws.amdocs.engine.priv.ProcessingLayer;
import ar.com.telefonica.ws.amdocs.engine.priv.ProcessingLoop;
import ar.com.telefonica.ws.amdocs.engine.priv.SupportServices;
import ar.com.telefonica.ws.amdocs.engine.ObjectRecord;
import ar.com.telefonica.ws.amdocs.engine.ObjectRecord.*;



public class FileProcessorTestCase {
	
	// instance to test:
	FileProcessor tester = null;
	
	// test input values:
	final static String FILE_TYPE = "ordenes";
	
	IInputRecord inputRecord = null; 
	ProcessingLoop loop = new ProcessingLoop();
	List<ProcessingLayer> layers = null;
	List<IProcessingObject> processors = null;
	
	// output test variables:
	ObjectRecord oRec = null;
	
	
	
	/** ******************************************************************************************
	 * 
	 * tester constructor:
	 *
	 *********************************************************************************************/
	
	@Before
	public void testFileProcessor() {
		this.tester = new FileProcessor();
	}
	
	
	
	/** ******************************************************************************************
	 * 
	 * test setters/getters:
	 *
	 *********************************************************************************************/
	
	@Test
	public void testFileTypeOK() {
		this.tester.setFileType(FILE_TYPE, false);
		
		assertEquals(this.tester.getFileType(), FILE_TYPE);
	}
	
	
	@Test(expected= IllegalStateException.class)
	public void testFileTypeException() {
		// set the file type twice to force an exception:
		tester.setFileType(FILE_TYPE, false);
		tester.setFileType(FILE_TYPE, false);
	}
	
	
	
	/** ******************************************************************************************
	 * 
	 * test process:
	 *
	 *********************************************************************************************/
	
	/*
	 * Test #1. Ejecucion OK:
	 */
	@Test
	public void testProcessOK() {
	
		// Test fixture:
		this.inputRecord = new IInputRecord(){};
		
		try {
			this.oRec = this.tester.process(this.inputRecord);
			
		} catch (Exception e) {
			fail("Fallo al ejecutar el process vacio.");
		}
		
		// oRec not null assert:
		assertNotNull(oRec);
	}
	
	
	
	/*
	 * Test #2. Layers with fail:
	 * - layer 1: 2 processing objects
	 * - layer 2: 1 processing object que siempre genera excepcion (sin procesadores)
	 */
	@Test
	public void testProcessException() {
		
		// Test fixture:
		this.oRec = null;
		this.loop = new ProcessingLoop();
		
		//TODO: Revisar.
		this.processors.add(1, new IProcessingObject() {
			public String getName(){return null;}
			public void setName(String name){}
			public String getDescription(){return null;}
			public void setDescription(String description) {}
			public CheckResult check(ObjectRecord oRec, SupportServices support) {return CheckResult.HANDLE;}
			public boolean handle(ObjectRecord oRec, SupportServices support){return true;}
		}); 
		this.processors.add(2, new IProcessingObject() {
			public String getName(){return null;}
			public void setName(String name){}
			public String getDescription(){return null;}
			public void setDescription(String description) {}
			public CheckResult check(ObjectRecord oRec, SupportServices support) {return CheckResult.HANDLE;}
			public boolean handle(ObjectRecord oRec, SupportServices support){return true;}
		}); 
		
		this.layers = new ArrayList<ProcessingLayer>();
		
		this.layers.add(1, new ProcessingLayer(1, this.processors));
		this.layers.add(2, new ProcessingLayer());
		
		this.loop.setLayers(this.layers);
		
		// Test execution:
		try {
			this.oRec = this.tester.process(this.inputRecord);
			
			// fail path:
			fail("no se produjo la excepcion esperada");
		} catch (Exception e) {
			
			// OK path:
			assertEquals(this.oRec.getResultStatus(), ResultStatus.ERROR);
		}
	}
	
}
