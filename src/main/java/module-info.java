module org.example.astero_demo {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.astero_demo to javafx.fxml;
    exports org.example.astero_demo;
}