open module realization {
    requires com.google.guice;
    requires lombok;
    requires org.apache.commons.lang3;
    requires commons.math3;
    requires org.slf4j;
    requires jsr305;

    requires java.desktop;

    requires core;
    requires util;

    exports org.example.astero_demo.realization.initialization.di.module;
    exports org.example.astero_demo.realization.configuration;
    exports org.example.astero_demo.realization.level.async.logic;
    exports org.example.astero_demo.realization.level.async.ui;
}