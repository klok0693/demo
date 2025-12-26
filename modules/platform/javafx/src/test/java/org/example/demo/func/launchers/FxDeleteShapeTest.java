package org.example.demo.func.launchers;

import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("org/example/demo/functional/scenario/DeleteShape.feature")
public class FxDeleteShapeTest {
}
