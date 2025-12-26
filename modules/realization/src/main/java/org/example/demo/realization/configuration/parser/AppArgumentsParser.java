package org.example.demo.realization.configuration.parser;

import org.apache.commons.lang3.StringUtils;
import org.example.demo.realization.context.ops.runtime.MutableConfiguration;

import java.util.Arrays;

public class AppArgumentsParser extends AbstractChainParser {
    private static final String NO_CUSTOM_UI = "-no-custom-ui";

    public AppArgumentsParser(
            final MutableConfiguration configuration,
            final ConfigurationParser next) {
        super(configuration, next);
    }

    @Override
    protected void parseValues(final String... args) {
        getConfiguration().setCustomSkinsAllowed(
                Arrays.stream(args).noneMatch(arg -> StringUtils.equals(arg, NO_CUSTOM_UI))
        );
    }
}
