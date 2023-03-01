package assignment2.pgupta85.ifinance;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.Objects;

public class AboutUs implements Runnable {

    public void createAboutUs(){

        //create a event thread
        Thread eventThread = new Thread(this);
        eventThread.start();
        Stage aboutUsStage = new Stage();

        aboutUsStage.setTitle("About Us");
        aboutUsStage.getIcons().add(new javafx.scene.image.Image(Objects.requireNonNull(getClass().getResourceAsStream("WesternLogo.png"))));

        AnchorPane AboutUsvbox = new AnchorPane();
        Label companyLabel = new Label("iFinance");
        Label copyRightLabel = new Label("Copyright @2023 SE2203b");
        Label name = new Label("Pratik Narendra Gupta");
        Button close = new Button("Close");

        //set the size of the vbox
        AboutUsvbox.setPrefSize(500, 500);

        //align the elements in the vbox in center of the vbox
        alignElements(companyLabel, 228, 203);
        alignElements(copyRightLabel, 171, 233);
        alignElements(name, 188, 263);

        alignElements(close, 223, 310);

        //set name color to red
        name.setStyle("-fx-text-fill: red");

        //set the action of the button
        close.setOnAction(event -> aboutUsStage.close());

        //set the scene of the stage
        aboutUsStage.setScene(new Scene(AboutUsvbox));
        //Add all the elements to the vbox
        AboutUsvbox.getChildren().addAll(companyLabel, copyRightLabel, name, close);

        aboutUsStage.show();
    }

    private void alignElements(Label label, double x, double y) {
        label.setLayoutX(x);
        label.setLayoutY(y);
    }

    private void alignElements(Button button, double x, double y) {
        button.setLayoutX(x);
        button.setLayoutY(y);
    }

    @Override
    public void run() {

    }

    public static void main(String[] args) {
        AboutUs aboutUs = new AboutUs();
        aboutUs.createAboutUs();

    }
}
