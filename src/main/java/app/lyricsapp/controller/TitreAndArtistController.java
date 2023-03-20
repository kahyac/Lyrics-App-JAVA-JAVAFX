package app.lyricsapp.controller;

import app.lyricsapp.Data;
import app.lyricsapp.Language;
import app.lyricsapp.model.FavoriteManager;
import app.lyricsapp.model.Parse;
import app.lyricsapp.model.Song;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static app.lyricsapp.model.Parse.getArtists;
import static app.lyricsapp.model.Parse.getSongs;
import static app.lyricsapp.model.Search.lyricsByArtistAndTitle;
import static app.lyricsapp.model.Search.songByArtistAndTitle;

public class TitreAndArtistController implements Initializable {

    @FXML
    private TextField textField1;

    @FXML
    private TextField textField2;

    private Stage stage;

    private Scene scene;

    private Parent root;

    @FXML
    private MenuItem deleteMenuItem;

    @FXML
    private MenuItem showLyricsMenuItem;

    private static FavoriteManager playList = LyricsAppController.getPlayList();

    Data data = Data.getNewData();

    @FXML
    private void addToFavoritePlaylist(ActionEvent event) throws IOException {
        String artist = textField2.getText();
        String title = textField1.getText();
        songByArtistAndTitle(artist,title);
        lyricsByArtistAndTitle(getArtists()[0], getSongs()[0]);
        Song song = new Song(Parse.getArtist(),Parse.getSong(),Parse.getLyric());
        if(!playList.songIsInFavoriteSongs(song)) {
            playList.addFavorite(song);
            //for(Song songSong:playList.getFavoriteSongs()) {}
            //if (!testview.getItems().contains(songSong.getSong())) {}
        }
    }

    @FXML
    protected void switchToResultforTitleAndArtist(ActionEvent event) throws IOException {
        ResourceBundle bundle = ResourceBundle.getBundle(Language.getLanguageSelection());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/lyricsapp/view/resultLyrıcs.fxml"));
        loader.setResources(bundle);
        Parent root = loader.load();

        String artistName = textField2.getText();
        String titleName = textField1.getText();
        data.setSongArtist(artistName);
        data.setSongTitle(titleName);
        //root = FXMLLoader.load(getClass().getResource("/app/lyricsapp/view/resultLyrics.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow() ;
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void switchToChoiceSearch(ActionEvent event) throws IOException {
        ResourceBundle bundle = ResourceBundle.getBundle(Language.getLanguageSelection());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/lyricsapp/view/choıceSearch.fxml"));
        loader.setResources(bundle);
        Parent root = loader.load();

        //root = FXMLLoader.load(getClass().getResource("/app/lyricsapp/view/choiceSearch.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
