package org.example.demo.func.launchers;

import com.github.caciocavallosilano.cacio.ctc.junit.CacioTest;
import org.example.demo.func.SwingUITestBase;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

/**
 * Test creation of the {@link Shape} via canvas drag&drop
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
@Suite
@CacioTest
@IncludeEngines("cucumber")
@SelectClasspathResource("org/example/demo/functional/scenario/InsertShape.feature")
public class SwingInsertShapeTest extends SwingUITestBase {
}