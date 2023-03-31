module com.example.responsiveui {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.responsiveui to javafx.fxml;
    exports com.example.responsiveui;
}