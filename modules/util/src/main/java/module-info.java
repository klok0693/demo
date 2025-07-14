open module util {
    requires lombok;
 /*   requires org.apache.commons.lang3;*/
    requires commons.math3;
    requires org.slf4j;
    requires jsr305;

    requires java.desktop;

    exports org.example.astero_demo.util.logging;
}