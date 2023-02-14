package app.lyricsapp;

import app.lyricsapp.model.ReadXMLFile;
import app.lyricsapp.model.Song;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class FavoriteManager {
    private List<Song> favoriteSongs;
    File favoriteFile = new File("favoriteFile.xml");
    public FavoriteManager() {
        favoriteSongs = ReadXMLFile.readFile(favoriteFile);
    }

    public void writeFavoriteFile() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(favoriteFile));
        writer.write("<Favorite>\n");
        for (Song song : favoriteSongs) {
            writer.write(song.toXML());
        }
        writer.write("</Favorite>\n");
        writer.close();
    }
    public void addFavorite(Song song) throws IOException {
        favoriteSongs.add(song);
        writeFavoriteFile();
    }
    public void removeFavorite(Song song) throws IOException {
        favoriteSongs.remove(song);
        writeFavoriteFile();
    }

    public List<Song> getFavoriteSongs() {
        return favoriteSongs;
    }

    public static void main(String [] args) throws IOException {
        File file = new File("query1.xml");
        List<Song> songs = ReadXMLFile.readFile(file);

        FavoriteManager favoriteManager = new FavoriteManager();
        favoriteManager.addFavorite(songs.get(0));
    }
}
