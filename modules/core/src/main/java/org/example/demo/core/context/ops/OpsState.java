package org.example.demo.core.context.ops;

import org.example.demo.core.adapter.clipboard.Clipboard;
import org.example.demo.model.entity.Shape;
import org.example.demo.model.metadata.dto.ShapeParams;

/**
 * Current state of the operation model
 *
 * @author Pilip Yurchanka
 * @since v1.2
 */
public interface OpsState extends Clipboard<Shape, ShapeParams> {
}
