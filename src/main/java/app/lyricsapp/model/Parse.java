package app.lyricsapp.model;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;

public class Parse {

    private static String[] artists;
    private static String[] songs;
    private static String[] lyrics;

    public static String[] getArtists() {
        return artists;
    }

    public static String[] getSongs() {
        return songs;
    }

    public static String[] getLyrics() {
        return lyrics;
    }

    public static void artistSongLyrics(String XML) {

        try {

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new InputSource(new StringReader(XML)));

            NodeList artistList = doc.getElementsByTagName("LyricArtist");
            NodeList songList = doc.getElementsByTagName("LyricSong");
            NodeList lyricList = doc.getElementsByTagName("Lyric");

            artists = new String[artistList.getLength()];
            songs = new String[songList.getLength()];
            lyrics = new String[lyricList.getLength()];

            for (int i = 0; i < artistList.getLength(); i++) {
                Node artistNode = artistList.item(i);
                artists[i] = artistNode.getTextContent();
            }

            for (int i = 0; i < songList.getLength(); i++) {
                Node songNode = songList.item(i);
                songs[i] = songNode.getTextContent();
            }

            for (int i = 0; i < lyricList.getLength(); i++) {
                Node lyricNode = lyricList.item(i);
                lyrics[i] = lyricNode.getTextContent();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void iDAndChecksum(String XML) {

        try {

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new InputSource(new StringReader(XML)));

            NodeList lyricIdList = doc.getElementsByTagName("LyricId");
            NodeList lyricChecksumList = doc.getElementsByTagName("LyricChecksum");

            String[] lyricIds = new String[lyricIdList.getLength()];
            String[] lyricChecksums = new String[lyricChecksumList.getLength()];

            for (int i = 0; i < lyricIdList.getLength(); i++) {
                Node lyricIdNode = lyricIdList.item(i);
                lyricIds[i] = lyricIdNode.getTextContent();
            }

            for (int i = 0; i < lyricChecksumList.getLength(); i++) {
                Node lyricChecksumNode = lyricChecksumList.item(i);
                lyricChecksums[i] = lyricChecksumNode.getTextContent();
            }

            Search.lyricsByIdAndChecksum(lyricIds[0], lyricChecksums[0]);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
