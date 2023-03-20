package app.lyricsapp.controller;

import app.lyricsapp.Language;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ChoiceSearchController implements Initializable {

    private Stage stage;

    private Scene scene;

    private Parent root;


    @FXML
    private Button titleAndArtistButton;

    @FXML
    private Button lyricsButton;

    @FXML
    protected void switchToLyrics(ActionEvent event) throws IOException {

        ResourceBundle bundle = ResourceBundle.getBundle(Language.getLanguageSelection());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/lyricsapp/view/lyrics.fxml"));
        loader.setResources(bundle);
        Parent root = loader.load();


        //root = FXMLLoader.load(getClass().getResource("/app/lyricsapp/view/lyrics.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    protected void switchToTitreAndArtist(ActionEvent event) throws IOException {

        ResourceBundle bundle = ResourceBundle.getBundle(Language.getLanguageSelection());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/lyricsapp/view/titreAndArtist.fxml"));
        loader.setResources(bundle);
        Parent root = loader.load();


        //root = FXMLLoader.load(getClass().getResource("/app/lyricsapp/view/titreAndArtist.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    protected void switchToMain(ActionEvent event) throws IOException {
        ResourceBundle bundle = ResourceBundle.getBundle(Language.getLanguageSelection());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/lyricsapp/view/lyricsapp.fxml"));
        loader.setResources(bundle);
        Parent root = loader.load();
        //root = FXMLLoader.load(getClass().getResource("/app/lyricsapp/view/lyricsapp.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
