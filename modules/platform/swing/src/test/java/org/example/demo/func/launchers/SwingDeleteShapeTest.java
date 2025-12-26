package org.example.demo.func.launchers;

import com.github.caciocavallosilano.cacio.ctc.junit.CacioTest;
import org.example.demo.func.SwingUITestBase;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

@Suite
@CacioTest
@IncludeEngines("cucumber")
@SelectClasspathResource("org/example/demo/functional/scenario/DeleteShape.feature")
public class SwingDeleteShapeTest extends SwingUITestBase {
}
