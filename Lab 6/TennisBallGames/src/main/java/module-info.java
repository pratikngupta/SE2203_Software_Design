module se2203b.lab6.tennisballgames {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens se2203b.lab6.tennisballgames to javafx.fxml;
    exports se2203b.lab6.tennisballgames;
}