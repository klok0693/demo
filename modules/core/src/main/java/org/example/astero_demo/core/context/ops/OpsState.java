package org.example.astero_demo.core.context.ops;

import org.example.astero_demo.core.adapter.clipboard.Clipboard;
import org.example.astero_demo.model.entity.Shape;
import org.example.astero_demo.model.metadata.dto.ShapeParams;

/**
 * Current state of the operation model
 *
 * @author Pilip Yurchanka
 * @since v1.2
 */
public interface OpsState extends Clipboard<Shape, ShapeParams> {
}
