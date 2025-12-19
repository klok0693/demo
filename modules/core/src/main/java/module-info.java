open module core {
    requires org.apache.commons.lang3;
    requires commons.math3;
    requires org.slf4j;
    requires jsr305;

    requires java.desktop;
    requires static lombok;

    requires util;
    requires model;
    requires api;

    exports org.example.astero_demo.core.port.ui;
    exports org.example.astero_demo.core.adapter.ui.state;
    exports org.example.astero_demo.core.adapter.ui.toolbar;
    exports org.example.astero_demo.core.adapter.ui;
    exports org.example.astero_demo.core.adapter.ui.canvas;
    exports org.example.astero_demo.core.adapter.ui.layerspanel;
    exports org.example.astero_demo.core.adapter.ui.property;
    exports org.example.astero_demo.core.adapter.keyboard;
    exports org.example.astero_demo.core.context.state;
    exports org.example.astero_demo.core.logic;
    exports org.example.astero_demo.core.port.ui.elements;
    exports org.example.astero_demo.core.port.ui.canvas;
    exports org.example.astero_demo.core.port.ui.canvas.background;
    exports org.example.astero_demo.core.port.ui.canvas.shape;
    exports org.example.astero_demo.core.port.ui.canvas.tool;
    exports org.example.astero_demo.core.port.ui.canvas.tool.draggable.drag;
    exports org.example.astero_demo.core.port.ui.canvas.tool.draggable.insert;
    exports org.example.astero_demo.core.controller.ui;
    exports org.example.astero_demo.core.logic.command;
    exports org.example.astero_demo.core.controller.model;
    exports org.example.astero_demo.core.adapter.state;
    exports org.example.astero_demo.core.util;
    exports org.example.astero_demo.core.port.ui.canvas.tool.draggable;
    exports org.example.astero_demo.core.port.ui.canvas.tool.draggable.selection;
    exports org.example.astero_demo.core.port.ui.model;
    exports org.example.astero_demo.core.port.os;
    exports org.example.astero_demo.core.adapter.clipboard;
    exports org.example.astero_demo.core.controller.keyboard;
    exports org.example.astero_demo.core.context.ops;
    exports org.example.astero_demo.core.context.ops.workspace;
    exports org.example.astero_demo.core.context.ops.execution;
}