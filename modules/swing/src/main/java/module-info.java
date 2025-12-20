open module swing_gui {
    requires com.google.guice;
    requires org.apache.commons.lang3;
    requires commons.math3;
    requires org.slf4j;
    requires jsr305;

    requires java.desktop;

    requires core;
    requires model;
    requires realization;
    requires util;
    requires static lombok;
    requires api;

    exports org.example.astero_demo.swing.port.ui;
    exports org.example.astero_demo.swing.port.ui.root;
}