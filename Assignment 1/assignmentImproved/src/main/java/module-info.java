module pgupta85.assignmentimproved {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.medusa;
    requires MaterialFX;

    opens pgupta85.assignmentimproved to javafx.fxml;
    exports pgupta85.assignmentimproved;
}