module com.example.learingexternalpackage {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires Medusa;

    opens com.example.learingexternalpackage to javafx.fxml;
    exports com.example.learingexternalpackage;
}