package org.example.astero_demo.adapter.ui;

import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import org.apache.commons.lang3.StringUtils;
import org.example.astero_demo.adapter.model.Shape;
import org.example.astero_demo.adapter.model.StateHolder;
import org.example.astero_demo.adapter.ui.event.SelectElementEvent;
import org.example.astero_demo.adapter.ui.state.UIState;
import org.example.astero_demo.controller.ViewController;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class LayersAdapter extends LeafAdapter {
    private final StateHolder holder;

    public TreeView<String> layersTree;

    public LayersAdapter(
            final ViewController controller,
            final UIState uiState,
            final StateHolder holder) {

        super(controller, uiState);
        this.holder = holder;
    }

    @Override
    public void update() {
        cleanUp();

        final var layers = holder.getShapes().collect(Collectors.groupingBy(Shape::getPriority));
        if (layers.isEmpty()) {
            return;
        }

        final TreeItem<String> rootItem = new TreeItem<>(StringUtils.EMPTY);
        layersTree.setRoot(rootItem);
        layers.forEach((key, value) -> {
            final TreeItem<String> layerItem = new TreeItem<>(key);
            layerItem.setExpanded(true);
            rootItem.getChildren().add(layerItem);

            value.stream()
                    .map(Shape::getId)
                    .map(String::valueOf)
                    .forEach(id -> {
                        final var item = new TreeItem<>(id);
                        item.setExpanded(true);
                        layerItem.getChildren().add(item);

                        if (uiState.hasSelectedId() && Objects.equals(uiState.getSelectedShapeId(), Integer.valueOf(id))) {
                            layersTree.getSelectionModel().select(item);
                        }
                    });
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        layersTree.setOnMouseClicked(event -> {
            final TreeItem<String> selectedItem = layersTree.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                final Shape shape = holder.getShape(parseInt(selectedItem.getValue()));
                parent.processEvent(new SelectElementEvent(parseDouble(shape.getX()), parseDouble(shape.getY())));
            }
        });
    }

    private void cleanUp() {
        final TreeItem<String> root = layersTree.getRoot();
        if (root != null) {
            root.getChildren().clear();
            layersTree.setRoot(null);
        }
    }
}
