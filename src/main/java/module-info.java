open module org.example.astero_demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;

    requires com.google.guice;
    requires lombok;
    requires org.apache.commons.lang3;
    requires org.slf4j;
    requires jsr305;

    requires java.desktop;

    exports org.example.astero_demo;
}