package org.example.demo.qt.port.ui.element;

import lombok.SneakyThrows;
import org.example.demo.core.adapter.ui.ShapeSelector;
import org.example.demo.core.adapter.ui.state.UIState;
import org.example.demo.model.entity.Shape;
import org.example.demo.core.context.state.ModelState;
import org.example.demo.core.port.ui.elements.LayersTree;
import org.example.demo.qt.port.ui.QtMemoryView;

import org.example.demo.qt.port.ui.bridge.*;

import java.lang.foreign.Arena;
import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandle;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;
import static java.lang.foreign.ValueLayout.ADDRESS;
import static java.lang.foreign.ValueLayout.JAVA_INT;

/**
 * JavaFX's realization of {@link LayersTree}
 *
 * @author Pilip Yurchanka
 * @since v1.1
 */
public class QtLayersTree /*extends TreeView<String>*/ implements LayersTree, QtMemoryView {
    //<editor-fold desc="ABI method's names">
    private static final String NATIVE_LAYERS_PANEL_NAME = "ui_layers_controller_get";

    private static final String NATIVE_SELECT_SHAPE_CALLBACK_NAME = "set_select_shape_callback";

    private static final String NATIVE_UPDATE_PANEL_NAME = "ui_layers_update";
    private static final String NATIVE_CLEANUP_PANEL_NAME = "ui_layers_panel_cleanup";
    private static final String NATIVE_UNSELECT_ALL_NAME = "ui_layers_panel_unselect_all";
    //</editor-fold>

    private final ModelState modelState;
    private final UIState uiState;

    private final ShapeSelector shapeSelector;

    private MemorySegment selectShapeSegment;

    private MethodHandle qtRefHandle;
    private MethodHandle updateTreeHandle;
    private MethodHandle cleanupTreeHandle;
    private MethodHandle unselectAllHandle;

    @Override
    public void initialize() throws Throwable {
        this.selectShapeSegment = bindMethodToNative(
                "selectItem",
                void.class,
                new Class[]{ MemorySegment.class },
                FunctionDescriptor.ofVoid(ADDRESS),
                NATIVE_LAYERS_PANEL_NAME,
                NATIVE_SELECT_SHAPE_CALLBACK_NAME
        );

        this.qtRefHandle =
                findNative(
                        NATIVE_LAYERS_PANEL_NAME,
                        FunctionDescriptor.of(ADDRESS));

        this.updateTreeHandle =
                findNative(
                        NATIVE_UPDATE_PANEL_NAME,
                        FunctionDescriptor.ofVoid(ADDRESS, ADDRESS, ADDRESS));

        this.cleanupTreeHandle =
                findNative(
                        NATIVE_CLEANUP_PANEL_NAME,
                        FunctionDescriptor.ofVoid(ADDRESS));

        this.unselectAllHandle =
                findNative(
                        NATIVE_UNSELECT_ALL_NAME,
                        FunctionDescriptor.ofVoid(ADDRESS));


    }

    public QtLayersTree(final ModelState modelState, final UIState uiState, final ShapeSelector shapeSelector) {
        this.modelState = modelState;
        this.uiState = uiState;
        this.shapeSelector = shapeSelector;

/*        setOnMouseClicked(event -> {
            final TreeItem<String> selectedItem = getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                final String id = selectedItem instanceof final LayerItem item ? item.getFirstChildId() : selectedItem.getValue();
                shapeSelector.selectShape(id);
            }
        });

        getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);*/
    }

    @Override
    @SneakyThrows
    public void update() {
        System.out.println("layer update");
        cleanUp();

        final var layers = modelState.getShapes().collect(Collectors.groupingBy(Shape::getPriority));
        if (!layers.isEmpty()) {
            System.out.println("layer add values " + layers);
            try (final Arena arena = Arena.ofConfined()) {
                final MemorySegment mapSegment = buildSegmentFromMap(arena, layers);
                final MemorySegment utf8 = buildSelectedIdSegment(arena);

                updateTreeHandle.invoke(qtRefHandle.invoke(), mapSegment, utf8);
            }
        }
        System.out.println("update ends");
    }

    private MemorySegment buildSelectedIdSegment(final Arena arena) {
        return  uiState.hasSelectedId() ?
                arena.allocateUtf8String(valueOf(uiState.getSelectedShapeId()))
                : MemorySegment.NULL;
    }

    private MemorySegment buildSegmentFromMap(
            final Arena arena,
            final Map<String, List<Shape>> layers) {

        int i = 0;
        final MemorySegment layerArray = arena.allocateArray(LayerEntry.$LAYOUT(), layers.size());
        for (final var entry : layers.entrySet()) {

            final String key = entry.getKey();
            final List<Shape> shapes = entry.getValue();

            final MemorySegment shapesArray = arena.allocateArray(JAVA_INT, shapes.size());

            for (int j = 0; j < shapes.size(); j++) {
                shapesArray.setAtIndex(JAVA_INT, j, shapes.get(j).getId());
            }

            final MemorySegment layer =
                    layerArray.asSlice(
                            i * LayerEntry.$LAYOUT().byteSize(),
                            LayerEntry.$LAYOUT().byteSize()
                    );

            LayerEntry.layerKey$set(layer, Integer.parseInt(key));
            LayerEntry.shapeCount$set(layer, shapes.size());
            LayerEntry.shapeIds$set(layer, shapesArray);

            i++;
        }

        final MemorySegment snapshot = arena.allocate(LayersSnapshot.$LAYOUT());

        LayersSnapshot.layerCount$set(snapshot, layers.size());
        LayersSnapshot.layers$set(snapshot, layerArray);

        return snapshot;
    }

    @Override
    @SneakyThrows
    public void unSelectAll() {
        System.out.println("layer unselect all");
        unselectAllHandle.invoke(qtRefHandle.invoke());
    }

    @SneakyThrows
    private void cleanUp() {
        System.out.println("layer clean up");
        cleanupTreeHandle.invoke(qtRefHandle.invoke());
    }

    public void selectItem(final MemorySegment strId) {
        System.out.println("layer select item");
        final MemorySegment sizedSegment = strId.reinterpret(Long.MAX_VALUE);
        final String selectedId = sizedSegment.getUtf8String(0);
        System.out.println("layer select item " + selectedId);

        shapeSelector.selectShape(selectedId);
    }

/*    private class LayerItem extends TreeItem<String> {

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

                        if (uiState.hasSelectedId() && uiState.isIdSelected(parseInt(id))) {
                            getSelectionModel().select(item);
                        }
                    });
        }

        String getFirstChildId() {
            return getChildren().getFirst().getValue();
        };
    }*/
}
