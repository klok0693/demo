open module util {
    requires org.apache.commons.lang3;
    requires commons.math3;
    requires org.slf4j;
    requires jsr305;

    requires java.desktop;

    requires static lombok;

    exports org.example.astero_demo.util.logging;
    exports org.example.astero_demo.util;
}