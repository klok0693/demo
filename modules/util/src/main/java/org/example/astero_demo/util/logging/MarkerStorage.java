package org.example.astero_demo.util.logging;

import lombok.experimental.UtilityClass;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

/**
 * Set of {@link Marker} to manage the logger output
 */
@UtilityClass
public class MarkerStorage {
    public static final Marker UI_MARKER = MarkerFactory.getMarker("ui");
    public static final Marker USER_INPUT_MARKER = MarkerFactory.getMarker("user_input");
    public static final Marker UI_STATE_MARKER = MarkerFactory.getMarker("ui_state");
    public static final Marker COMMAND_MARKER = MarkerFactory.getMarker("command");
    public static final Marker INITIALIZATION_MARKER = MarkerFactory.getMarker("initialization");
    public static final Marker EVENT_MARKER = MarkerFactory.getMarker("event");
}
