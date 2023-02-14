package app.lyricsapp;

import java.io.File;
import java.util.ArrayList;

public class FavoriteManager {
    File favoriteSong;


    public FavoriteManager(File favoriteSong) {
        this.favoriteSong = new File("favoriteSong.xml");
    }
}
