package org.example.astero_demo.util.logging;

import lombok.experimental.UtilityClass;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

/**
 * Set of {@link Marker} to filter the logger output.<p>
 * More like a demonstration in current version, will<p>
 * be expanded later
 */
@UtilityClass
public class MarkerStorage {
    /**
     * {@link Marker} to track GUI element's behavior
     */
    public static final Marker UI_MARKER = MarkerFactory.getMarker("ui");
    /**
     * {@link Marker} to track user's actions
     */
    public static final Marker USER_INPUT_MARKER = MarkerFactory.getMarker("user_input");
    /**
     * {@link Marker} to track changes in GUI state
     */
    public static final Marker UI_STATE_MARKER = MarkerFactory.getMarker("ui_state");
    /**
     * {@link Marker} to track performed commands
     */
    public static final Marker COMMAND_MARKER = MarkerFactory.getMarker("command");
    /**
     * {@link Marker} to track initialization details
     */
    public static final Marker INITIALIZATION_MARKER = MarkerFactory.getMarker("initialization");
    /**
     * {@link Marker} to track inner events
     */
    public static final Marker EVENT_MARKER = MarkerFactory.getMarker("event");
}
