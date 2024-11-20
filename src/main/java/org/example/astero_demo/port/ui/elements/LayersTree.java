package org.example.astero_demo.port.ui.elements;

import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import org.apache.commons.lang3.StringUtils;
import org.example.astero_demo.adapter.model.entity.Shape;
import org.example.astero_demo.adapter.model.state.ModelState;
import org.example.astero_demo.adapter.ui.ShapeSelector;
import org.example.astero_demo.adapter.ui.state.UIState;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

public class LayersTree extends TreeView<String> {
    private final ModelState modelState;
    private final UIState uiState;

    public LayersTree(final ModelState modelState, final UIState uiState, final ShapeSelector shapeSelector) {
        this.modelState = modelState;
        this.uiState = uiState;

        setOnMouseClicked(event -> {
            final TreeItem<String> selectedItem = getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                final String id = selectedItem instanceof final LayerItem item ? item.getFirstChildId() : selectedItem.getValue();
                shapeSelector.selectShape(id);
            }
        });
    }

    public void update() {
        cleanUp();

        final var layers = modelState.getShapes().collect(Collectors.groupingBy(Shape::getPriority));
        if (layers.isEmpty()) {
            return;
        }

        final TreeItem<String> rootItem = new TreeItem<>(StringUtils.EMPTY);
        setRoot(rootItem);
        layers.forEach((key, value) -> {
            final LayerItem layerItem = new LayerItem(key);
            rootItem.getChildren().add(layerItem);
            layerItem.setExpanded(true);
            layerItem.addShapes(value);
        });
    }

    private void cleanUp() {
        final TreeItem<String> root = getRoot();
        if (root != null) {
            root.getChildren().clear();
            setRoot(null);
        }
    }

    private class LayerItem extends TreeItem<String> {

        LayerItem(final String key) {
            super(key);
        }

        void addShapes(final Collection<Shape> shapes) {
            shapes.stream()
                    .map(Shape::getId)
                    .map(String::valueOf)
                    .forEach(id -> {
                        final var item = new TreeItem<>(id);
                        item.setExpanded(true);
                        getChildren().add(item);

                        if (uiState.hasSelectedId() && Objects.equals(uiState.getSelectedShapeId(), Integer.valueOf(id))) {
                            getSelectionModel().select(item);
                        }
                    });
        }

        String getFirstChildId() {
            return getChildren().getFirst().getValue();
        };
    }
}
