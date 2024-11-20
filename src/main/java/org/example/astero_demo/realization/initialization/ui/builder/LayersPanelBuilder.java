package org.example.astero_demo.realization.initialization.ui.builder;

import com.google.inject.Inject;
import javafx.beans.DefaultProperty;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.util.Builder;
import org.example.astero_demo.port.ui.LayersPanelView;

import java.util.HashMap;

import static java.lang.Double.parseDouble;

@DefaultProperty("children")
public class LayersPanelBuilder extends HashMap<Object, Object> implements Builder<LayersPanelView>  {
    private final LayersPanelView panel;

    @Inject
    public LayersPanelBuilder(final LayersPanelView panel) {
        this.panel = panel;
        //put("children", panel.getChildren());
    }

    @Override
    public LayersPanelView build() {
/*        panel.setPrefWidth(parseDouble(get("prefWidth").toString()));
        panel.setPrefHeight(parseDouble(get("prefHeight").toString()));
        panel.getStylesheets().add(get("stylesheets").toString());*/
        return panel;
    }

 /*   public ObservableList<Node> getChildren() {
        return panel.getChildren();
    }*/
}
