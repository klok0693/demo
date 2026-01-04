open module qt_gui {
    requires com.google.guice;
    requires org.apache.commons.lang3;
    requires commons.math3;
    requires org.slf4j;
    requires jsr305;
    requires it.unimi.dsi.fastutil;

    requires java.desktop;

    requires core;
    requires model;
    requires realization;
    requires util;
    requires api;

    requires static lombok;
}