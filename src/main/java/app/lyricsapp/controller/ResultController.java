package app.lyricsapp.controller;

import app.lyricsapp.Data;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

import static app.lyricsapp.model.Parse.getLyric;
import static app.lyricsapp.model.Search.lyricsByArtistAndTitle;

public class ResultController implements Initializable {

    Data data = Data.getNewData();

    @FXML
    private Label titleLabel;

    @FXML
    private Label artistLabel;

    @FXML
    private TextArea lyricsByArtistAndTitle;

    public void initialize(URL location, ResourceBundle resourceBundle) {
        titleLabel.setText(data.getSongTitle());
        artistLabel.setText(data.getSongArtist());
        lyricsByArtistAndTitle(data.getSongArtist(), data.getSongTitle());
        lyricsByArtistAndTitle.setText(getLyric());
    }
}
