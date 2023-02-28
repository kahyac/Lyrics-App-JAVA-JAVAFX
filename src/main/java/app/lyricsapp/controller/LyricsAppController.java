package app.lyricsapp.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

import java.net.URL;
import java.util.ResourceBundle;

public class LyricsAppController implements Initializable {

    private Stage stage;

    private Scene scene;

    private Parent root;

    @FXML
    private Label welcomeText;

    @FXML
    private Button exitButton;

    @FXML
    private AnchorPane scenePane;

    @FXML
    protected void switchToRechercher(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("/app/lyricsapp/view/rechercher.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void switchToMain(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("/app/lyricsapp/view/lyricsapp.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    protected void quitingAction(ActionEvent event) throws IOException{
        stage = (Stage) this.scenePane.getScene().getWindow();
        stage.close();
    }

    @FXML
    protected void switchToFavoris(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("/app/lyricsapp/view/favoris.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML private Button helloWorldButton;
    @FXML private Button goodByeWorldButton;
    @FXML private Label label;

    //    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {}

    @FXML
    private void displayHelloWorld() {
        label.setText("Hello World");
        helloWorldButton.setVisible(false);
        if (!goodByeWorldButton.isVisible())
            goodByeWorldButton.setVisible(true);
    }

    @FXML
    private void goodByeWorld() {
        label.setText("");
        goodByeWorldButton.setVisible(false);
        if (!helloWorldButton.isVisible())
            helloWorldButton.setVisible(true);
    }
}
