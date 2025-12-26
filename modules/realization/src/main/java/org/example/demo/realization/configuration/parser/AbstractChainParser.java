package org.example.demo.realization.configuration.parser;

import lombok.AccessLevel;
import lombok.Getter;
import org.example.demo.realization.context.ops.runtime.MutableConfiguration;

import javax.annotation.Nullable;

/**
 * Chain of responsibility pattern for configurations parsers
 *<p>
 * @since 1.2
 * @author Pilip Yurchanka
 */
public abstract class AbstractChainParser implements ConfigurationParser {
    @Getter(AccessLevel.PROTECTED)
    private final MutableConfiguration configuration;
    @Nullable
    private final ConfigurationParser next;

    protected AbstractChainParser(
            final MutableConfiguration configuration,
            final ConfigurationParser next) {
        this.configuration = configuration;
        this.next = next;
    }

    @Override
    public void parse(final String... args) {
        parseValues(args);
        if (next != null) {
            next.parse(args);
        }
    }

    protected abstract void parseValues(String... args);
}
