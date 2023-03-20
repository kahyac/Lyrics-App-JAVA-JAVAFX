package app.lyricsapp.controller;

import app.lyricsapp.Language;
import app.lyricsapp.model.Song;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class FavoriteArtistController implements Initializable {

    @FXML
    private ListView<String> listArtist;

    private Stage stage;

    private Scene scene;

    private Parent root;


    @FXML
    protected void switchToFavoris(ActionEvent event) throws IOException {
        ResourceBundle bundle = ResourceBundle.getBundle(Language.getLanguageSelection());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/lyricsapp/view/favorıs.fxml"));
        loader.setResources(bundle);
        Parent root = loader.load();

        //root = FXMLLoader.load(getClass().getResource("/app/lyricsapp/view/favoris.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listArtist.getItems().addAll(getFavoriteArtistsBySongCount(LyricsAppController.getPlayList().getFavoriteSongs()));
    }
}
