package app.lyricsapp.model;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;

public class Parse {

    private static String artist;
    private static String song;
    private static String lyric;
    private static String[] lyricIds;
    private static String[] lyricChecksums;
    private static String[] songs;
    private static String[] artists;

    public static String getArtist() {
        return artist;
    }

    public static String getSong() {
        return song;
    }

    public static String getLyric() {
        return lyric;
    }

    public static String[] getSongs() {
        return songs;
    }

    public static String[] getArtists() {
        return artists;
    }

    public static void artistSongLyrics (String XML) {

        try {

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new InputSource(new StringReader(XML)));

            NodeList artistList = doc.getElementsByTagName("LyricArtist");
            NodeList songList = doc.getElementsByTagName("LyricSong");
            NodeList lyricList = doc.getElementsByTagName("Lyric");

                Node artistNode = artistList.item(0);
                artist = artistNode.getTextContent();

                Node songNode = songList.item(0);
                song = songNode.getTextContent();

                Node lyricNode = lyricList.item(0);
                lyric = lyricNode.getTextContent();

                lyric = lyric.replaceAll("([A-Z])", "\n$1");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void iDsChecksumsArtistsSongs(String XML) {

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new InputSource(new StringReader(XML)));

            NodeList lyricIdList = doc.getElementsByTagName("LyricId");
            NodeList lyricChecksumList = doc.getElementsByTagName("LyricChecksum");
            NodeList songList = doc.getElementsByTagName("Song");
            NodeList artistList = doc.getElementsByTagName("Artist");

            lyricIds = new String[lyricIdList.getLength()];
            lyricChecksums = new String[lyricChecksumList.getLength()];
            songs = new String[songList.getLength()];
            artists = new String[artistList.getLength()];


            for (int i = 0; i < lyricIdList.getLength(); i++) {
                Node lyricIdNode = lyricIdList.item(i);
                lyricIds[i] = lyricIdNode.getTextContent();
            }

            for (int i = 0; i < lyricChecksumList.getLength(); i++) {
                Node lyricChecksumNode = lyricChecksumList.item(i);
                lyricChecksums[i] = lyricChecksumNode.getTextContent();
            }

            for (int i = 0; i < songList.getLength(); i++) {
                Node lyricSongNode = songList.item(i);
                songs[i] = lyricSongNode.getTextContent();
            }

            for (int i = 0; i < artistList.getLength(); i++) {
                Node lyricArtistNode = artistList.item(i);
                artists[i] = lyricArtistNode.getTextContent();
            }

            //System.out.println("Liste de musiques : " + Arrays.toString(songs) + "\n");

            for (int i = 0 ; i < songs.length ; i++)
                System.out.println(i + 1 + ") " + songs[i]);

            Search.lyricsByIdAndChecksum(lyricIds[0], lyricChecksums[0]);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

