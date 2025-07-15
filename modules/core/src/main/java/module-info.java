open module core/*org.example.astero_demo*/ {
/*    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;*/

    requires com.google.guice;
    requires lombok;
    requires org.apache.commons.lang3;
    requires commons.math3;
    requires org.slf4j;
    requires jsr305;

    requires java.desktop;

    requires util;

    exports org.example.astero_demo.core.port.ui;
    exports org.example.astero_demo.core.adapter.ui.state;
    exports org.example.astero_demo.core.adapter.ui.toolbar;
    exports org.example.astero_demo.core.adapter.ui;
    exports org.example.astero_demo.core.adapter.ui.canvas;
    exports org.example.astero_demo.core.adapter.ui.layerspanel;
    exports org.example.astero_demo.core.adapter.ui.property;
    exports org.example.astero_demo.core.adapter.keyboard;
    exports org.example.astero_demo.core.model.state;
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
    exports org.example.astero_demo.core.adapter.model;
    exports org.example.astero_demo.core.model.entity;
    exports org.example.astero_demo.core.model.metadata.dto;
    exports org.example.astero_demo.core.util;
    exports org.example.astero_demo.core.port.ui.canvas.tool.draggable;
    exports org.example.astero_demo.core.port.ui.canvas.tool.draggable.selection;
    /*exports org.example.astero_demo.port.ui.view;*/
}