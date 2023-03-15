package app.lyricsapp.controller;

import app.lyricsapp.model.FavoriteManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FavoritesController implements Initializable {

    private Stage stage;

    private Scene scene;

    private Parent root;

    @FXML
    private AnchorPane scenePane;

    @FXML
    private ListView<String> favoriteList;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
