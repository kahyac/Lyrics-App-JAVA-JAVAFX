package app.lyricsapp.controller;

import app.lyricsapp.Data;
import app.lyricsapp.model.Parse;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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

import static app.lyricsapp.model.Parse.*;
import static app.lyricsapp.model.Search.*;

public class LyricsAppController implements Initializable {

    private Stage stage;

    private Scene scene;

    private Parent root;

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

    private String[] songs;

    private int songIndex;

    @FXML
    private ListView<String> songList;

    Data data = Data.getNewData();

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

        root = FXMLLoader.load(getClass().getResource("/app/lyricsapp/view/resultLyrics.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow() ;
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



    @FXML
    private void getTextAndSearch() throws IOException{
        String artist = textField2.getText();
        String title = textField1.getText();
        songByArtistAndTitle(artist,title);
        if (getArtists().length == 0){

        }
        lyricsByArtistAndTitle(artist,title);
        lyricsByArtistAndTitle.setText(getLyric());
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
        lyricsByArtistAndTitle(getArtists()[0], getSongs()[0]);
        if (getArtists().length == 0){

        }
        lyricsByArtistAndTitle.setText(getLyric());

    }

    @FXML
    private void getListByLyrics() throws IOException {
        String lyrics = textFieldLyrics.getText();
        songByLyrics(lyrics);
        songList.getItems().addAll(Parse.getSongs());
        songList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
                songIndex= songList.getSelectionModel().getSelectedIndex();
                String titleName = getSongs()[songIndex];
                String artistName = getArtists()[songIndex];
                data.setSongArtist(artistName);
                data.setSongTitle(titleName);
                lyricsByArtistAndTitle(artistName, titleName);
                lyricsByArtistAndTitle.setText(getLyric());
            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
