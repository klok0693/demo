open module api {
    requires java.desktop;

    requires static lombok;

    exports org.example.demo.api.graphics;
    exports org.example.demo.api.graphics.color;

    exports org.example.demo.api.keyboard;
}