package org.example.demo.swing.port.ui.element;

import org.example.demo.core.adapter.ui.ShapeSelector;
import org.example.demo.core.adapter.ui.state.UIState;
import org.example.demo.model.entity.Shape;
import org.example.demo.core.context.state.ModelState;
import org.example.demo.core.port.ui.elements.LayersTree;

import javax.annotation.Nullable;
import javax.swing.*;
import javax.swing.tree.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.stream.Collectors;

import static org.example.demo.core.port.ui.markup.ElementID.LAYERS_TREE;

/**
 * Swing realization of {@link LayersTree}
 *
 * @author Pilip Yurchanka
 * @since v1.2
 */
public class SwingLayersTree extends JTree implements LayersTree {

    private final ModelState modelState;
    private final UIState uiState;
    private final ShapeSelector shapeSelector;

    private DefaultTreeModel treeModel;
    private LayerRootNode root;

    public SwingLayersTree(
            final ModelState modelState,
            final UIState uiState,
            final ShapeSelector shapeSelector) {

        this.modelState = modelState;
        this.uiState = uiState;
        this.shapeSelector = shapeSelector;

        setName(LAYERS_TREE.toString());

        //setBackground(Color.red);
        //setOpaque(true);

        setMinimumSize(new Dimension(200, 300));
        setAlignmentX(JComponent.LEFT_ALIGNMENT);

        // Root
        root = new LayerRootNode();
        treeModel = new DefaultTreeModel(root);
        setModel(treeModel);

        // Mouse handling
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                final TreePath path = getPathForLocation(e.getX(), e.getY());
                if (path == null) {
                    return;
                }

                final Object node = path.getLastPathComponent();
                if (node instanceof final LayerNode layerNode) {
                    final String id = layerNode.getFirstChildId();
                    if (id != null) {
                        shapeSelector.selectShape(id);
                    }
                } else if (node instanceof final ShapeNode shapeNode) {
                    shapeSelector.selectShape(shapeNode.getId());
                }
            }
        });

        // Selection
        getSelectionModel().setSelectionMode(TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION);
        addTreeSelectionListener(e -> repaint());

        setCellRenderer(new DefaultTreeCellRenderer() {
            @Override
            public Component getTreeCellRendererComponent(
                    final JTree tree,
                    final Object value,
                    final boolean selected,
                    final boolean expanded,
                    final boolean leaf,
                    final int row,
                    final boolean hasFocus) {

                final JLabel c = (JLabel) super.getTreeCellRendererComponent(
                        tree, value, selected, expanded, leaf, row, hasFocus
                );

                final boolean isLeafSelected;
                if (value instanceof final ShapeNode node) {
                    isLeafSelected = uiState.isIdSelected(Integer.parseInt(node.getId()));
                }
                else {
                    isLeafSelected = false;
                }

                if (selected || isLeafSelected) {
                    c.setBackground(new Color(0xCCE5FF));
                    c.setForeground(Color.BLACK);
                }
                else {
                    c.setBackground(tree.getBackground());
                    c.setForeground(tree.getForeground());
                }

                c.setOpaque(true);
                return c;
            }
        });

        setRootVisible(false);
        setShowsRootHandles(false);
    }

    @Override
    public void update() {
        cleanUp();

        final var layers = modelState.getShapes().collect(Collectors.groupingBy(Shape::getPriority));

        if (layers.isEmpty()) {
            treeModel.reload();
            return;
        }

        layers.forEach((priority, shapes) -> {
            final LayerNode layerNode = new LayerNode(priority);
            root.add(layerNode);

            shapes.forEach(shape -> {
                final ShapeNode shapeNode = new ShapeNode(shape.getId());
                layerNode.add(shapeNode);

                if (uiState.hasSelectedId() && uiState.isIdSelected(shape.getId())) {
                    selectNode(shapeNode);
                }
            });
        });

        treeModel.reload();
        expandAll();
    }

    @Override
    public void unSelectAll() {
        clearSelection();
    }

    private void cleanUp() {
        root.removeAllChildren();
        treeModel.reload();
    }

    private void expandAll() {
        for (int i = 0; i < getRowCount(); i++) {
            expandRow(i);
        }
    }

    private void selectNode(final DefaultMutableTreeNode node) {
        final TreePath path = new TreePath(node.getPath());
        addSelectionPath(path);
    }

    /* ---------------- Tree Nodes ---------------- */

    private static class LayerRootNode extends DefaultMutableTreeNode {
        LayerRootNode() {
            super("ROOT");
        }
    }

    private static class LayerNode extends DefaultMutableTreeNode {
        LayerNode(final String layerKey) {
            super(layerKey);
        }

        @Nullable
        String getFirstChildId() {
            if (getChildCount() == 0) {
                return null;
            }
            final Object child = getChildAt(0);
            return child instanceof final ShapeNode sn ? sn.getId() : null;
        }
    }

    private static class ShapeNode extends DefaultMutableTreeNode {
        private final String id;

        ShapeNode(final int id) {
            this.id = String.valueOf(id);
            setUserObject(this.id);
        }

        String getId() {
            return id;
        }
    }
}

