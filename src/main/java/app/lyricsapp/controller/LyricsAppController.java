package app.lyricsapp.controller;

import app.lyricsapp.Data;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import app.lyricsapp.model.FavoriteManager;
import app.lyricsapp.model.Parse;
import app.lyricsapp.model.Song;
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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static app.lyricsapp.model.FavoriteChoice.getFavorites;
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
    private TextField textField1Test;

    @FXML
    private TextField textField2Test;

    @FXML
    private TextArea lyricsByArtistAndTitle;

    private String[] songs;

    @FXML
    private Label showResults;

    private int songIndex;

    @FXML
    private ListView<String> songList;
    @FXML
    private ListView<String> testview;
    @FXML
    private MenuItem deleteMenuItem;
    @FXML
    private MenuItem showLyricsMenuItem;
    @FXML
    private ListView<String> favoriteList;

    @FXML
    private Button saveButton;

    private static FavoriteManager playList = new FavoriteManager();

    public static FavoriteManager getPlayList(){
        return playList;
    }

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
        ResourceBundle bundle = ResourceBundle.getBundle("en");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/lyricsapp/view/lyricsapp.fxml"));
        loader.setResources(bundle);
        Parent root = loader.load();
        //root = FXMLLoader.load(getClass().getResource("/app/lyricsapp/view/lyricsapp.fxml"));
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

        ResourceBundle bundle = ResourceBundle.getBundle("en");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/lyricsapp/view/favoris.fxml"));
        loader.setResources(bundle);
        Parent root = loader.load();
        //root = FXMLLoader.load(getClass().getResource("/app/lyricsapp/view/favoris.fxml"));
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
    protected void switchToResultforTitleAndArtist(ActionEvent event) throws IOException {

        String artistName = textField2.getText();
        String titleName = textField1.getText();
        data.setSongArtist(artistName);
        data.setSongTitle(titleName);
        root = FXMLLoader.load(getClass().getResource("/app/lyricsapp/view/resultLyrics.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow() ;
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void dataSetter(String artist, String title) throws IOException {

        data.setSongArtist(artist);
        data.setSongTitle(title);
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
        showResults.setText(String.valueOf(Parse.getSongs().length) + " resultats");
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
            }
        });
    }
    @FXML
    private void addToFavoritePlaylist(ActionEvent event) throws IOException{
        String artist = textField2.getText();
        String title = textField1.getText();
        songByArtistAndTitle(artist,title);
        lyricsByArtistAndTitle(getArtists()[0], getSongs()[0]);
        Song song = new Song(Parse.getArtist(),Parse.getSong(),Parse.getLyric());
        testview.setOnContextMenuRequested(rightClickEvent -> {

            ContextMenu contextMenu = new ContextMenu();

            contextMenu.getItems().add(deleteMenuItem);
            contextMenu.getItems().add(showLyricsMenuItem);

            deleteMenuItem.setOnAction(deleteEvent -> {
                try {
                    playList.removeFavorite(song);
                    String selectedItem = testview.getSelectionModel().getSelectedItem();
                    testview.getItems().remove(selectedItem);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            });

            showLyricsMenuItem.setOnAction(showEvent -> {
                try {
                    String selectedItem = testview.getSelectionModel().getSelectedItem();
                    String[] artistAndTitle = selectedItem.split(" - ");
                    dataSetter(artistAndTitle[1], artistAndTitle[0]);
                    root = FXMLLoader.load(getClass().getResource("/app/lyricsapp/view/resultLyrics.fxml"));
                    stage = (Stage)((Node)event.getSource()).getScene().getWindow() ;
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            });

            contextMenu.show(testview, rightClickEvent.getScreenX(), rightClickEvent.getScreenY());
        });

        if(!playList.songIsInFavoriteSongs(song)) {
            playList.addFavorite(song);
            //for(Song songSong:playList.getFavoriteSongs()) {}
            //if (!testview.getItems().contains(songSong.getSong())) {}
            testview.getItems().add(song.getSong() + " - " + song.getArtist());
        }
    }

    @FXML
    private void removeFromPlaylist(ActionEvent event) throws IOException{
        String artist = textField2.getText();
        String title = textField1.getText();

        Song song = new Song(artist, title);
        //if (playList.songIsInFavoriteSongs(ourSong)) {}
        playList.removeFavorite(song);
        if (!testview.getItems().contains(song.getSong())) {
            testview.getItems().remove(song.getSong() + " - " + song.getArtist());
        }
        testview.refresh();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


}
