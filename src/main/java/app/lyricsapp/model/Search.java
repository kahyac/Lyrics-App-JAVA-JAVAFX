package app.lyricsapp.model;

public class Search {

    public static void songByArtistAndTitle(String artist, String title) {

        artist =  artist.replaceAll(" ", "%20");
        title =  title.replaceAll(" ", "%20");
        String url = "http://api.chartlyrics.com/apiv1.asmx/SearchLyric?artist=" + artist + "&song=" + title;
        String response = API.call(url);
        Parse.iDAndChecksum(String.valueOf(response));

    }

    public static void songByLyrics(String lyrics) {

        lyrics =  lyrics.replaceAll("\\s+", "%20");
        String url = "http://api.chartlyrics.com/apiv1.asmx/SearchLyricText?lyricText=" + lyrics;
        API.call(url);
        String response = API.call(url);
        Parse.iDAndChecksum(String.valueOf(response));
    }

    public static void lyricsByIdAndChecksum(String id, String checksum) {

        String url = "http://api.chartlyrics.com/apiv1.asmx/GetLyric?lyricId=" + id + "&lyricCheckSum=" + checksum;
        API.call(url);
        String response = API.call(url);
        Parse.artistSongLyrics(String.valueOf(response));
    }

}
