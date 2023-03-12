package app.lyricsapp.controller;

import app.lyricsapp.model.Parse;
import app.lyricsapp.model.Search;
import app.lyricsapp.model.Song;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;

import static app.lyricsapp.model.Search.*;

public class LyricsAppController implements Initializable {

    private Stage stage;

    private Scene scene;

    private Parent root;

    @FXML
    private Label welcomeText;

    @FXML
    private Button exitButton;

    @FXML
    private Button searchButton;

    @FXML
    private static Label lyricstext;

    @FXML
    private AnchorPane scenePane;

    @FXML
    private TextField textField1;

    @FXML
    private TextField textFieldLyrics;

    @FXML
    private TextField textField2;

    @FXML
    private TextArea lyricsByArtistAndTitle;

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

    @FXML
    protected void switchToResult(ActionEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("/app/lyricsapp/view/result.fxml"));
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

   // @FXML
    //private void setLyricstext() throws IOException {
     //   lyricstext.setText(getTextAndSearch());
   // }

    @FXML
    private void getTextAndSearch() throws IOException{
        String artist = textField2.getText();
        String title = textField1.getText();
        songByArtistAndTitle(artist,title);
        if (Parse.getArtists().length == 0){

        }
        lyricsByArtistAndTitle(artist,title);
        lyricsByArtistAndTitle.setText(Parse.getLyric());
        //lyricsByArtistAndTitle.setText(lyricsByArtistAndTitle(artist, title));
        //Song song = new Song(artist,title,lyricsByArtistAndTitle(artist, title));
       // lyricsByArtistAndTitle.setText(song.getLyrics());

/*
            try {
                root = FXMLLoader.load(getClass().getResource("/app/lyricsapp/view/lyrics.fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();*/

    }

    @FXML
    private void getByLyrics() throws IOException{
        String lyrics = textFieldLyrics.getText();
        songByLyrics(lyrics);
        lyricsByArtistAndTitle(Parse.getArtists()[0],Parse.getSongs()[0]);
        if (Parse.getArtists().length == 0){

        }
        lyricsByArtistAndTitle.setText(Parse.getLyric());

    }
}
