package app.lyricsapp;

public class DataFavoriteDelete {

    private static final DataFavoriteDelete newDataFavoriteDelete = new DataFavoriteDelete();

    public static DataFavoriteDelete getNewDataFavoriteDelete() {
        return newDataFavoriteDelete;
    }

    private static String songTitleFavoriteDelete = "";

    private static String songArtistFavoriteDelete = "";

    public static String getSongArtist() {
        return songArtistFavoriteDelete;
    }

    public static String getSongTitle() {
        return songTitleFavoriteDelete;
    }

    public static void setSongArtist(String songArtist) {
        DataFavoriteDelete.songArtistFavoriteDelete = songArtist;
    }

    public static void setSongTitle(String songTitle) {
        DataFavoriteDelete.songTitleFavoriteDelete = songTitle;
    }

}
