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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import static app.lyricsapp.model.Parse.getLyric;
import static app.lyricsapp.model.Search.lyricsByArtistAndTitle;

public class ResultController implements Initializable {

    private Stage stage;

    private Scene scene;

    private Parent root;

    @FXML
    private AnchorPane scenePane;

    Data data = Data.getNewData();

    @FXML
    private Label titleLabel;

    @FXML
    private Label artistLabel;

    @FXML
    private TextArea lyricsByArtistAndTitle;

    @FXML
    private CheckBox addToFavorites;

    /* public void change(ActionEvent event) throws IOException {
        Song thisSong = new Song(data.getSongArtist(), data.getSongTitle(), Parse.getLyric());
        if (addToFavorites.isSelected()) {
            // heart.setImage(empty);
        }
        else {
            // heart.setImage(full);
            ;
        }
    }


     */

    public static String capitalizeString(String string) {
        char[] chars = string.toLowerCase().toCharArray();
        boolean found = false;
        for (int i = 0; i < chars.length; i++) {
            if (!found && Character.isLetter(chars[i])) {
                chars[i] = Character.toUpperCase(chars[i]);
                found = true;
            } else if (Character.isWhitespace(chars[i]) || chars[i]=='.' || chars[i]=='\'') { // You can add other chars here
                found = false;
            }
        }
        return String.valueOf(chars);
    }

    public void initialize(URL location, ResourceBundle resourceBundle) {
        titleLabel.setText(capitalizeString(data.getSongTitle()));
        artistLabel.setText(capitalizeString(data.getSongArtist()));
        lyricsByArtistAndTitle(data.getSongArtist(), data.getSongTitle());
        lyricsByArtistAndTitle.setText(getLyric());
    }

    @FXML
    protected void switchToChoiceSearch(ActionEvent event) throws IOException {
        ResourceBundle bundle = ResourceBundle.getBundle(Language.getLanguageSelection());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/lyricsapp/view/choiceSearch.fxml"));
        loader.setResources(bundle);
        Parent root = loader.load();

        //root = FXMLLoader.load(getClass().getResource("/app/lyricsapp/view/choiceSearch.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
