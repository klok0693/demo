open module api {
    requires java.desktop;

    requires static lombok;

    exports org.example.astero_demo.api.graphics;
    exports org.example.astero_demo.api.graphics.color;

    exports org.example.astero_demo.api.keyboard;
}