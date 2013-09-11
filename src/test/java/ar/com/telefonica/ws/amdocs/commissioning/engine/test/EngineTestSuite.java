package ar.com.telefonica.ws.amdocs.commissioning.engine.test;
/** ******************************************************************************************
 * 
 * class MainTestSuite
 * 
 * Test suit for engine Test Cases.
 * 
 * Test suit coverage:
 * 		- FileProcessorTestCase
 * 		- ProcessingLoopTestCase
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


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;



@RunWith(Suite.class)
@SuiteClasses({ FileProcessorTestCase.class, 
				ObjectRecordTestCase.class,
				OutputRecordTestCase.class,
				OutputRecordManagerServiceTestCase.class,
				ProcessingLayerTestCase.class,
				ProcessingLoopTestCase.class,
				//RecordWriterGenericTestCase.class,
				RecordWriterMultiplexorTestCase.class,
				SupportServicesTestCase.class,
				//TranslationServiceMockTestCase.class
			  })
public class EngineTestSuite {

}
