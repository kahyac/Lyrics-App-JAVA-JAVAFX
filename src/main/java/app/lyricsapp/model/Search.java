package app.lyricsapp.model;

import java.io.UnsupportedEncodingException;

public class Search {

    public static void songByArtistAndTitle(String artist, String title) {

        artist =  artist.replaceAll(" ", "%20");
        title =  title.replaceAll(" ", "%20");
        String url = "http://api.chartlyrics.com/apiv1.asmx/SearchLyric?artist=" + artist + "&song=" + title;
        String response = API.call(url);
        Parse.iDsChecksumsArtistsSongs(String.valueOf(response));
    }

    public static void songByLyrics(String lyrics) throws UnsupportedEncodingException {

        lyrics =  lyrics.replaceAll("\\s+", "+");
//        lyrics =  URLEncoder.encode(lyrics , StandardCharsets.UTF_8);
        String url = "http://api.chartlyrics.com/apiv1.asmx/SearchLyricText?lyricText=" + lyrics;
        String response = API.call(url);
        Parse.iDsChecksumsArtistsSongs(String.valueOf(response));
    }

    public static void lyricsByIdAndChecksum(String id, String checksum) {

        String url = "http://api.chartlyrics.com/apiv1.asmx/GetLyric?lyricId=" + id + "&lyricCheckSum=" + checksum;
        String response = API.call(url);
        Parse.artistSongLyrics(String.valueOf(response));
    }

    public static void lyricsByArtistAndTitle(String artist, String song) {

        artist =  artist.replaceAll(" ", "%20");
        song =  song.replaceAll(" ", "%20");

        String url = "http://api.chartlyrics.com/apiv1.asmx/SearchLyricDirect?artist=" + artist + "&song=" + song;
        String response = API.call(url);
        Parse.artistSongLyrics(String.valueOf(response));
    }

}
