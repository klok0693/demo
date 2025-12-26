package org.example.astero_demo.realization.configuration.parser;

/**
 * Interface representing a configuration parser
 * for setting up an application/system.
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
@FunctionalInterface
public interface ConfigurationParser {

    void parse(String... args);
}
