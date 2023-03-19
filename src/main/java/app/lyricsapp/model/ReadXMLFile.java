package app.lyricsapp.model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class ReadXMLFile {

    public static List<Song> readFile(File file) {

        List<Song> songs = new ArrayList<>();

        try {

            String XML = "<Favorite>\n" +
                    "  <SongFavorite>\n" +
                    "    <LyricsCheckSum>null</LyricsCheckSum>\n" +
                    "    <LyricsId>null</LyricsId>\n" +
                    "    <Artist>Michael Jackson</Artist>\n" +
                    "    <Song>Bad</Song>\n" +
                    "  </SongFavorite>\n" +
                    "</Favorite>";

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);

            //Document doc = dBuilder.parse(new ByteArrayInputStream(XML.getBytes()));

            // read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();

            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("SearchLyricResult");
            System.out.println("----------------------------");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                System.out.println("\nCurrent Element :" + nNode.getNodeName());
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    String trackId = eElement.getElementsByTagName("TrackId").item(0).getTextContent();
                    String lyricsCheckSum = eElement.getElementsByTagName("LyricChecksum").item(0).getTextContent();
                    String lyricsId = eElement.getElementsByTagName("LyricId").item(0).getTextContent();
                    String SongUrl = eElement.getElementsByTagName("SongUrl").item(0).getTextContent();
                    String ArtistUrl = eElement.getElementsByTagName("ArtistUrl").item(0).getTextContent();
                    String Artist = eElement.getElementsByTagName("Artist").item(0).getTextContent();
                    String Song = eElement.getElementsByTagName("Song").item(0).getTextContent();
                    String SongRank = eElement.getElementsByTagName("SongRank").item(0).getTextContent();
                    Song song = new Song(lyricsCheckSum,lyricsId,Artist,Song);
                    songs.add(song);

                    /*
                    System.out.println("TrackId : " + eElement.getElementsByTagName("TrackId").item(0).getTextContent());
                    System.out.println("LyricChecksum : " + eElement.getElementsByTagName("LyricChecksum").item(0).getTextContent());
                    System.out.println("LyricId : " + eElement.getElementsByTagName("LyricId").item(0).getTextContent());
                    System.out.println("SongUrl : " + eElement.getElementsByTagName("SongUrl").item(0).getTextContent());
                    System.out.println("ArtistUrl : " + eElement.getElementsByTagName("ArtistUrl").item(0).getTextContent());
                    System.out.println("Artist : " + eElement.getElementsByTagName("Artist").item(0).getTextContent());
                    System.out.println("Song : " + eElement.getElementsByTagName("Song").item(0).getTextContent());
                    System.out.println("SongRank : " + eElement.getElementsByTagName("SongRank").item(0).getTextContent());
                    */

                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return songs;
    }

    public static void main(String [] args) {
        File file = new File("query1.xml");
        List<Song> songs = readFile(file);
        for (Song song : songs)
            System.out.println(song.toString());
    }
}
