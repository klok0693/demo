package org.example.astero_demo.realization.initialization.di.module;

import com.google.inject.*;
import org.example.astero_demo.realization.configuration.AppConfigurationManager;
import org.example.astero_demo.realization.configuration.parser.AppArgumentsParser;
import org.example.astero_demo.realization.configuration.preparer.BeforeLaunchPreparer;
import org.example.astero_demo.realization.configuration.parser.ConfigurationParser;
import org.example.astero_demo.realization.configuration.parser.SystemPropertiesConfigurationParser;
import org.example.astero_demo.realization.configuration.preparer.Preparer;
import org.example.astero_demo.realization.context.ops.runtime.MutableConfiguration;

public class ConfigurationModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Preparer.class).to(BeforeLaunchPreparer.class).in(Scopes.SINGLETON);
    }

    @Inject
    @Provides
    @Singleton
    public AppConfigurationManager provideConfigurationParser(
            final BeforeLaunchPreparer preparer,
            final ConfigurationParser parser) {
        return new AppConfigurationManager(preparer, parser);
    }

    @Inject
    @Provides
    @Singleton
    public ConfigurationParser provideSystemPropertyConfigurationParser(
            final MutableConfiguration configuration,
            final AppArgumentsParser argumentsParser) {
        return new SystemPropertiesConfigurationParser(configuration, argumentsParser);
    }

    @Inject
    @Provides
    @Singleton
    public AppArgumentsParser provideAppArgumentsParser(final MutableConfiguration configuration) {
        return new AppArgumentsParser(configuration, null);
    }
}
