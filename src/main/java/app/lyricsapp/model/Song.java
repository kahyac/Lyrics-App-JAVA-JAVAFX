package app.lyricsapp.model;

public class Song {
    private String lyricsCheckSum;
    private String lyricsId;
    private String Artist;
    private String Song;
    public Song(String lyricsCheckSum, String lyricsId,String Artist,String Song){
        this.lyricsCheckSum=lyricsCheckSum;
        this.lyricsId=lyricsId;
        this.Artist=Artist;
        this.Song=Song;
    }

    @Override
    public String toString() {
        return "Song{" +
                "lyricsCheckSum='" + lyricsCheckSum + '\'' +
                ", lyricsId='" + lyricsId + '\'' +
                ", Artist='" + Artist + '\'' +
                ", Song='" + Song + '\'' +
                '}';
    }
}
