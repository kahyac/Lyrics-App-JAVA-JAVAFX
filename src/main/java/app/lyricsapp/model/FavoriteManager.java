package app.lyricsapp.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class FavoriteManager {
    private final List<Song> favoriteSongs;
    private final File favoriteFile = new File("favoriteFile.xml");

    public FavoriteManager() {
        favoriteSongs = ReadXMLFile.readFile(favoriteFile);
        System.out.println(favoriteSongs);
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
        for(Song favorite : getFavoriteSongs()) {
            if(favorite.toString().equals(song.toString())) {
                favoriteSongs.remove(favorite);
                break;
            }
        }
        writeFavoriteFile();
    }

    public Song getSong(int i) {
        return favoriteSongs.get(i);
    }

    public List<Song> getFavoriteSongs() {
        return favoriteSongs;
    }

    public List<String> getFavoriteSongsForView() {

        List<String> listSong = new ArrayList<>();
        for (Song favoriteSong : favoriteSongs) {
            listSong.add(favoriteSong.getSong()+" - " +favoriteSong.getArtist());

        }
        return listSong;
    }

    public int favoriteSongSize() {
        return favoriteSongs.size();
    }

    public boolean songIsInFavoriteSongs(Song song) {
        for(Song favorite : getFavoriteSongs()) {
            if (favorite.toString().equals(song.toString())) {
                return true;
            }
        }
        return false;
    }

    public static void main(String [] args) throws IOException {
        //File file = new File("query1.xml");
        File file = new File("favoriteFile.xml");
        List<Song> songs = ReadXMLFile.readFile(file);

        FavoriteManager favoriteManager = new FavoriteManager();
        favoriteManager.addFavorite(songs.get(0));
    }
}
