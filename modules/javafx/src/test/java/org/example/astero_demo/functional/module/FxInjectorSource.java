package org.example.astero_demo.functional.module;


import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;
import io.cucumber.guice.CucumberModules;
import io.cucumber.guice.InjectorSource;
import org.example.astero_demo.functional.module.FxTestModule;

public class FxInjectorSource implements InjectorSource {
    //private static final Injector ROOT = Guice.createInjector(new FxTestModule());

    @Override
    public Injector getInjector() {
        //return ROOT.createChildInjector(CucumberModules.createScenarioModule());
        return Guice.createInjector(Stage.PRODUCTION, CucumberModules.createScenarioModule(), new FxTestModule());
    }
}
