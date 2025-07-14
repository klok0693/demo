open module javafx_gui/*org.example.astero_demo*/ {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;

    requires com.google.guice;
    requires lombok;
    requires org.apache.commons.lang3;
    requires commons.math3;
    requires org.slf4j;
    requires jsr305;

    requires java.desktop;

    requires core;
    requires realization;
    requires util;

    exports org.example.astero_demo.fx.port.ui;
    /*exports org.example.astero_demo;*/
}