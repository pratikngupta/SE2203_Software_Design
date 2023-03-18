module se2203b.assignments.ifinance {
    requires javafx.controls;
    requires javafx.fxml;


    opens se2203b.assignments.ifinance to javafx.fxml;
    exports se2203b.assignments.ifinance;
}