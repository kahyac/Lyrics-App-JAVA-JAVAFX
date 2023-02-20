package app.lyricsapp.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class FavoriteManager {
    private List<Song> favoriteSongs;
    private File favoriteFile = new File("favoriteFile.xml");

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

    public Song getSong(int i) throws IOException {
        return favoriteSongs.get(i);
    }

    public List<Song> getFavoriteSongs() throws IOException {
        return favoriteSongs;
    }

    public int favoriteSongSize() throws IOException {
        return favoriteSongs.size();
    }

    public boolean songIsInFavoriteSongs(Song song) throws IOException {
        return favoriteSongs.contains(song);
    }

    public static void main(String [] args) throws IOException {
        File file = new File("query1.xml");
        List<Song> songs = ReadXMLFile.readFile(file);

        FavoriteManager favoriteManager = new FavoriteManager();
        favoriteManager.addFavorite(songs.get(0));
    }
}
