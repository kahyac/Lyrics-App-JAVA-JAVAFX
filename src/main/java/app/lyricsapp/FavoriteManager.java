package app.lyricsapp;

import app.lyricsapp.model.ReadXMLFile;
import app.lyricsapp.model.Song;

import java.io.File;
import java.util.*;

public class FavoriteManager {
    private List<Song> favoriteSong;
    File favoriteFile = new File("favoriteFile.xml");
    public FavoriteManager() {
        favoriteSong = ReadXMLFile.readFile(favoriteFile);
    }

    public void writeFavoriteFile() {
        //vider le fichier
        for (Song song : favoriteSong) {
            // ecrire song dans le fichier
        }
    }
    public void addFavorite(Song song) {
        favoriteSong.add(song);
        writeFavoriteFile();
    }
    public void removeFavorite(Song song) {
        favoriteSong.remove(song);
        writeFavoriteFile();
    }

    public File getFavoriteFile() {
        return favoriteFile;
    }
}
