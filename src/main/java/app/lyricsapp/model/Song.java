package app.lyricsapp.model;

public class Song {
    private String lyricsCheckSum;
    private String lyricsId;
    private String artist;
    private String song;
    public Song(String lyricsCheckSum, String lyricsId,String Artist,String Song){
        this.lyricsCheckSum=lyricsCheckSum;
        this.lyricsId=lyricsId;
        this.artist =Artist;
        this.song =Song;
    }

    @Override
    public String toString() {
        return "Song {" + "lyricsCheckSum='" + lyricsCheckSum + '\'' + ", lyricsId='" + lyricsId + '\'' + ", Artist='" + artist + '\'' + ", Song='" + song + '\'' + '}';
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
