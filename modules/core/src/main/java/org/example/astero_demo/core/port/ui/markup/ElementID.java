package org.example.astero_demo.core.port.ui.markup;

public enum ElementID {
    CANVAS("canvas"),
    CANVAS_ROOT("canvasRoot"),
    INSERT_RECT_BTN("insertRectBtn"),
    INSERT_CYCLE_BTN("insertCycleBtn"),
    UNDO_BTN("undoBtn"),
    DELETE_BTN("deleteBtn"),

    X_FIELD("xField"),
    Y_FIELD("yField"),
    WIDTH_FIELD("widthField"),
    HEIGHT_FIELD("heightField"),
    LAYER_FIELD("layerField"),
    COLOR_FIELD("colorField"),

    LAYERS_TREE("layersTree");

    private final String id;

    ElementID(final String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return id;
    }
}
