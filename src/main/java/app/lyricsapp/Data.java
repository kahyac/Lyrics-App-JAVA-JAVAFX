package app.lyricsapp;

public class Data {

    private static final Data newData = new Data();

    public static Data getNewData() {
        return newData;
    }

    private static String songTitle = "";

    private static String songArtist = "";

    public static String getSongArtist() {
        return songArtist;
    }

    public static String getSongTitle() {
        return songTitle;
    }

    public static void setSongArtist(String songArtist) {
        Data.songArtist = songArtist;
    }

    public static void setSongTitle(String songTitle) {
        Data.songTitle = songTitle;
    }

}
