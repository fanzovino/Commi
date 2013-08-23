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
 * @package	ar.com.telefonica.ws.amdocs.engine.test
 * 
 * @author	FDB <delbarriof@plussistemas.com.ar>
 * @version 1.0
 * @created 14-Ago-2013
 * 
 *********************************************************************************************/

package ar.com.telefonica.ws.amdocs.engine.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;



@RunWith(Suite.class)
@SuiteClasses({ FileProcessorTestCase.class, 
				ObjectRecordTestCase.class,
				OutputRecordTestCase.class,
				ProcessingLayerTestCase.class,
				ProcessingLoopTestCase.class,
				RecordWriterMultiplexorTestCase.class
			  })
public class EngineTestSuite {

}
