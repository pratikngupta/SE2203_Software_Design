package assignment2.pgupta85.ifinance;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.Objects;

import static javafx.application.Platform.exit;


public class iFinanceController {

    @FXML
    private BorderPane MainStage;
    @FXML
    private Stage aboutUsStage = new Stage();

    public void initialize() {
        //set WesternBackground.png as background
        MainStage.setStyle(
                "-fx-background-image: url(" +
                        Objects.requireNonNull(getClass().getResource("WesternBackground.png")) +
                        "); " +
                        "-fx-background-size: 100% 100%;"
        );
    }

    @FXML
    void AboutUsClicked( ) {
        createAboutUs();
    }

    @FXML
    void CloseOptionClicked() {
        exit();
    }

    //create a method to align the elements in the anchor pane which will be used in the AboutUsClicked method
    public void alignElements(Label label, int x, int y) {
        label.setLayoutX(x);
        label.setLayoutY(y);
    }
    //create a generic method to align the elements in the anchor pane which will be used in the AboutUsClicked method
    public void alignElements(Button button, int x, int y) {
        button.setLayoutX(x);
        button.setLayoutY(y);
    }

    public void createAboutUs(){
        //Launch the stage
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
}