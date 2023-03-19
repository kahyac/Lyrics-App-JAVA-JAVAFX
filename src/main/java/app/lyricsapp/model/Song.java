package app.lyricsapp.model;

public class Song {
    private String lyricsCheckSum;
    private String lyricsId;
    private final String artist;
    private final String song;
    private String lyrics;

    public Song(String lyricsCheckSum, String lyricsId, String artist, String song){
        this.lyricsCheckSum= lyricsCheckSum;
        this.lyricsId= lyricsId;
        this.artist = artist;
        this.song = song;
    }

    public Song(String artist, String song, String lyrics) {
        this.artist = artist;
        this.song = song;
        this.lyrics = lyrics;
    }

    public Song(String artist, String song) {
        this.artist = artist;
        this.song = song;
    }

    public String getLyrics() {
        return lyrics;
    }
    public String getSong() {
        return song;
    }

    public String getArtist() {
        return artist;
    }
    public String toString() {
        return "{Artiste => \"" + artist + "\", Chanson => \"" + song + "\"}";
        /*return "Song : {" + "lyricsCheckSum='" + lyricsCheckSum + '\'' + ", lyricsId='" + lyricsId + '\'' +
        ", Artist='" + artist + '\'' + ", Song='" + song + '\'' + '}';*/
    }

    public String toXML() {
        StringBuilder builder = new StringBuilder();

        builder.append("<SongFavorite>\n");
        builder.append("\t<LyricsCheckSum>" + lyricsCheckSum + "</LyricsCheckSum>\n");
        builder.append("\t<LyricsId>" + lyricsId + "</LyricsId>\n");
        builder.append("\t<Artist>" + artist + "</Artist>\n");
        builder.append("\t<Song>" + song + "</Song>\n");
        builder.append("</SongFavorite>\n");

        return builder.toString();
    }
}
