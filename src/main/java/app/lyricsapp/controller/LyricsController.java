package app.lyricsapp.controller;

import app.lyricsapp.Data;
import app.lyricsapp.Language;
import app.lyricsapp.model.FavoriteManager;
import app.lyricsapp.model.Parse;
import app.lyricsapp.model.Song;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static app.lyricsapp.model.Parse.getArtists;
import static app.lyricsapp.model.Parse.getSongs;
import static app.lyricsapp.model.Search.*;

public class LyricsController implements Initializable {

    private Stage stage;

    private Scene scene;

    private Parent root;

    @FXML
    private TextField textFieldLyrics;

    @FXML
    private ListView<String> songList;

    private int songIndex;

    @FXML
    private Label showResults;

    @FXML
    private MenuItem deleteMenuItem;

    @FXML
    private MenuItem showLyricsMenuItem;

    @FXML
    private MenuItem addMenuItem;

    private static FavoriteManager playList = LyricsAppController.getPlayList();

    Data data = Data.getNewData();

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
    protected void switchToResult(ActionEvent event) throws IOException {
        ResourceBundle bundle = ResourceBundle.getBundle(Language.getLanguageSelection());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/lyricsapp/view/resultLyrics.fxml"));
        loader.setResources(bundle);
        Parent root = loader.load();

        //root = FXMLLoader.load(getClass().getResource("/app/lyricsapp/view/resultLyrics.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow() ;
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void switchToChoiceSearch(ActionEvent event) throws IOException {
        ResourceBundle bundle = ResourceBundle.getBundle(Language.getLanguageSelection());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/lyricsapp/view/choiceSearch.fxml"));
        loader.setResources(bundle);
        Parent root = loader.load();

        //root = FXMLLoader.load(getClass().getResource("/app/lyricsapp/view/choiceSearch.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        songList.setOnContextMenuRequested(rightClickEvent -> {

            ContextMenu contextMenu = new ContextMenu();

            contextMenu.getItems().add(deleteMenuItem);
            contextMenu.getItems().add(addMenuItem);

            deleteMenuItem.setOnAction(deleteEvent -> {
                try {
                    String selectedItem = songList.getSelectionModel().getSelectedItem();
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
                    Song song = new Song(data.getSongArtist(), data.getSongTitle());
                    LyricsAppController.getPlayList().removeFavorite(song);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            });

            addMenuItem.setOnAction(addEvent -> {
                try {
                    String selectedItem = songList.getSelectionModel().getSelectedItem();
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
                    Song song = new Song(data.getSongArtist(), data.getSongTitle());
                    if(!playList.songIsInFavoriteSongs(song)){
                        LyricsAppController.getPlayList().addFavorite(song);
                    }
                    //songList.getItems().remove(selectedItem);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            });

            contextMenu.show(songList, rightClickEvent.getScreenX(), rightClickEvent.getScreenY());
        });

    }
}
