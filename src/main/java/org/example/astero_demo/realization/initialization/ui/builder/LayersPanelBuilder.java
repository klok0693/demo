package org.example.astero_demo.realization.initialization.ui.builder;

import com.google.inject.Inject;
import javafx.beans.DefaultProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.util.Builder;
import org.example.astero_demo.port.ui.LayersPanel;

import java.net.URL;
import java.util.HashMap;
import java.util.List;

import static java.lang.Double.parseDouble;

@DefaultProperty("children")
public class LayersPanelBuilder extends HashMap<Object, Object> implements Builder<LayersPanel>  {
    private final LayersPanel panel;

    @Inject
    public LayersPanelBuilder(final LayersPanel panel) {
        this.panel = panel;
        put("children", panel.getChildren());
    }

    @Override
    public LayersPanel build() {
        panel.setPrefWidth(parseDouble(get("prefWidth").toString()));
        panel.setPrefHeight(parseDouble(get("prefHeight").toString()));
        panel.getStylesheets().add(get("stylesheets").toString());
        return panel;
    }

    public ObservableList<Node> getChildren() {
        return panel.getChildren();
    }
}
