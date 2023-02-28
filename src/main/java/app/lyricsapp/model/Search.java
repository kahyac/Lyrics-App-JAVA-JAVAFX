package app.lyricsapp.model;

public class Search {

    public static void songByArtistAndTitle(String artist, String title) {

        artist = artist.replaceAll(" ", "%20");
        title = title.replaceAll(" ", "%20");
        String url = "http://api.chartlyrics.com/apiv1.asmx/SearchLyric?artist=" + artist + "&song=" + title;
        String response = API.call(url);
        Parse.idsChecksumsArtistsSongs(String.valueOf(response), false);
        System.out.println(response);
    }

    public static void songByLyrics(String lyrics) {

        lyrics = lyrics.replaceAll("\\s+", "+");
        String url = "http://api.chartlyrics.com/apiv1.asmx/SearchLyricText?lyricText=" + lyrics;
        String response = API.call(url);
        Parse.idsChecksumsArtistsSongs(String.valueOf(response), true);
    }

    public static void lyricsByArtistAndTitle(String artist, String song) {

        artist = artist.replaceAll(" ", "%20");
        song = song.replaceAll(" ", "%20");

        String url = "http://api.chartlyrics.com/apiv1.asmx/SearchLyricDirect?artist=" + artist + "&song=" + song;
        String response = API.call(url);
        Parse.artistSongLyrics(String.valueOf(response));
    }

}

