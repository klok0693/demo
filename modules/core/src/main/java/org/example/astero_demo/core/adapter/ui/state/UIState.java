package org.example.astero_demo.core.adapter.ui.state;

import org.example.astero_demo.core.adapter.ui.state.mode.UIMode;
import org.example.astero_demo.core.adapter.ui.state.model.Selection;
import org.example.astero_demo.core.model.entity.ShapeType;

/**
 * In addition to the model's data, UI block has it own state,<p>
 * holding references on current selected state, copied values etc.<p>
 *
 * This state only reflects data from the model state, so it must be updated<p>
 * every time the model data values are changed
 *
 * @author Pilip Yurchanka
 * @since v1.0
 */
public interface UIState extends Selection {

    boolean isInInsertMode();

    UIMode getMode();

    boolean isActiveMode(UIMode mode);

    ShapeType getInsertShapeType();

    boolean hasCopy();

    String getCopyWidth();

    String getCopyHeight();

    String getCopyPriority();

    String getCopyColor();

    String getCopyType();
}
