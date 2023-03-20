package app.lyricsapp.controller;

import app.lyricsapp.Data;
import app.lyricsapp.model.Song;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import static app.lyricsapp.model.Search.songByArtistAndTitle;

public class FavoritesController implements Initializable {

    private Stage stage;

    private Scene scene;

    private Parent root;

    @FXML
    private AnchorPane scenePane;

    @FXML
    private ListView<String> favoriteList;

    @FXML
    private CheckBox addToFavorites;
    @FXML
    private TextField favoriteSongName;
    @FXML
    private TextField textField1;

    @FXML
    private TextField textFieldLyrics;

    @FXML
    private TextField textField2;

    @FXML
    private MenuItem deleteMenuItem;
    @FXML
    private MenuItem showLyricsMenuItem;

    Data data = Data.getNewData();

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
    protected void switchToFavoriteArtist(ActionEvent event) throws IOException {


        root = FXMLLoader.load(getClass().getResource("/app/lyricsapp/view/favoriteArtist.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        favoriteList.setOnContextMenuRequested(rightClickEvent -> {

            ContextMenu contextMenu = new ContextMenu();

            contextMenu.getItems().add(deleteMenuItem);
            contextMenu.getItems().add(showLyricsMenuItem);

            deleteMenuItem.setOnAction(deleteEvent -> {
                try {
                    String selectedItem = favoriteList.getSelectionModel().getSelectedItem();
                    String[] songAndArtist = selectedItem.split(" - ");
                    Song song = new Song(songAndArtist[1],songAndArtist[0]);
                    LyricsAppController.getPlayList().removeFavorite(song);
                    favoriteList.getItems().remove(selectedItem);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            });

            showLyricsMenuItem.setOnAction(showEvent -> {
                try {
                    String selectedItem = favoriteList.getSelectionModel().getSelectedItem();
                    String[] artistAndTitle = selectedItem.split(" - ");
                    data.setSongArtist(artistAndTitle[1]);
                    data.setSongTitle(artistAndTitle[0]);
                    root = FXMLLoader.load(getClass().getResource("/app/lyricsapp/view/resultLyrics.fxml"));
                    stage = (Stage)showLyricsMenuItem.getParentPopup().getOwnerWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            });

            contextMenu.show(favoriteList, rightClickEvent.getScreenX(), rightClickEvent.getScreenY());
        });
        favoriteList.getItems().addAll(LyricsAppController.getPlayList().getFavoriteSongsForView());

    }

    public List<String> getFavoriteArtistsBySongCount(List<Song> songList) {
        // Créer une carte des artistes et du nombre de chansons
        Map<String, Integer> artistCountMap = new HashMap<>();
        for (Song song : songList) {
            String artist = song.getArtist();
            int count = artistCountMap.getOrDefault(artist, 0);
            artistCountMap.put(artist, count + 1);
        }

        // Trier les artistes par nombre de chansons
        List<Map.Entry<String, Integer>> artistCountList = new ArrayList<>(artistCountMap.entrySet());
        Collections.sort(artistCountList, (a, b) -> b.getValue().compareTo(a.getValue()));

        // Créer une liste de noms d'artistes triés par nombre de chansons
        List<String> favoriteArtists = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : artistCountList) {
            favoriteArtists.add(entry.getKey());
        }

        // Retourner la liste des artistes triés par ordre décroissant du nombre de chansons
        return favoriteArtists;
    }


}
