open module javafx_gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;

    requires com.google.guice;
    requires org.apache.commons.lang3;
    requires commons.math3;
    requires org.slf4j;
    requires jsr305;

    requires java.desktop;

    requires core;
    requires realization;
    requires util;
    requires static lombok;

    exports org.example.astero_demo.fx.port.ui;
}