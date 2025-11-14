open module model {
    requires org.apache.commons.lang3;
    requires jsr305;
    requires commons.math3;

    requires static lombok;

    exports org.example.astero_demo.model.entity;
    exports org.example.astero_demo.model.metadata;
    exports org.example.astero_demo.model.metadata.dto;
}