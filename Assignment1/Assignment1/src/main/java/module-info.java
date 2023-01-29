module assignment1.pgupta85.assignment1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens assignment1.pgupta85.assignment1 to javafx.fxml;
    exports assignment1.pgupta85.assignment1;
}