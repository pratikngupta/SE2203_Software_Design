module assignment2.pgupta85.ifinance {
    requires javafx.controls;
    requires javafx.fxml;


    opens assignment2.pgupta85.ifinance to javafx.fxml;
    exports assignment2.pgupta85.ifinance;
}