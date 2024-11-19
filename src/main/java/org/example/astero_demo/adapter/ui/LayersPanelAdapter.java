package org.example.astero_demo.adapter.ui;

import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import org.apache.commons.lang3.StringUtils;
import org.example.astero_demo.adapter.model.entity.Shape;
import org.example.astero_demo.adapter.model.state.ModelState;
import org.example.astero_demo.adapter.ui.event.SelectElementById;
import org.example.astero_demo.adapter.ui.state.UIState;
import org.example.astero_demo.controller.ViewController;

import java.net.URL;
import java.util.Collection;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class LayersPanelAdapter extends LeafAdapter {
    private final ModelState modelState;

    public TreeView<String> layersTree;

    public LayersPanelAdapter(
            final ViewController controller,
            final UIState uiState,
            final ModelState modelState) {
        super(controller, uiState);
        this.modelState = modelState;
    }

    @Override
    public void update() {
        cleanUp();

        final var layers = modelState.getShapes().collect(Collectors.groupingBy(Shape::getPriority));
        if (layers.isEmpty()) {
            return;
        }

        final TreeItem<String> rootItem = new TreeItem<>(StringUtils.EMPTY);
        layersTree.setRoot(rootItem);
        layers.forEach((key, value) -> {
            final LayerItem layerItem = new LayerItem(key);
            rootItem.getChildren().add(layerItem);
            layerItem.setExpanded(true);
            layerItem.addShapes(value);
        });
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        layersTree.setOnMouseClicked(event -> {
            final TreeItem<String> selectedItem = layersTree.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                final String id = selectedItem instanceof final LayerItem item ? item.getFirstChildId() : selectedItem.getValue();
                parent.processEvent(new SelectElementById(Integer.parseInt(id)));
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
                            layersTree.getSelectionModel().select(item);
                        }
                    });
        }

        String getFirstChildId() {
            return getChildren().getFirst().getValue();
        };
    }
}
