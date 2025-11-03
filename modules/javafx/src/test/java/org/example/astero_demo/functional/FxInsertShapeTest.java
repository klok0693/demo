package org.example.astero_demo.functional/*functional*/;

import io.cucumber.junit.platform.engine.Constants;
import io.cucumber.junit.platform.engine.Cucumber;
import org.example.astero_demo.core.model.entity.Shape;
import org.junit.platform.suite.api.ConfigurationParameter;
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
@IncludeEngines("cucumber")
@SelectClasspathResource("org/example/astero_demo/functional/scenario")
/*@ConfigurationParameter(key = Constants.FEATURES_PROPERTY_NAME,value = "src/test/resources/org/example/astero_demo/functional/scenario/InsertShape.feature")
@ConfigurationParameter(key = Constants.GLUE_PROPERTY_NAME, value = "org/example/astero_demo/functional")
@ConfigurationParameter(key = Constants.PLUGIN_PROPERTY_NAME, value = "pretty, summary, html:target/cucumber-reports.html")*/
//@Cucumber
public class FxInsertShapeTest {
}