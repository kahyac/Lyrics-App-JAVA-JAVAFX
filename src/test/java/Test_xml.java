

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

    public class Test_xml {
        public static void main(String[] args) {
            try {
                File inputFile = new File("query2.xml");
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(inputFile);
                doc.getDocumentElement().normalize();
                System.out.println("Racine du document : " + doc.getDocumentElement().getNodeName());
                NodeList nList = doc.getElementsByTagName("GetLyricResult");
                System.out.println("----------------------------");
                for (int temp = 0; temp < nList.getLength(); temp++) {
                    Node nNode = nList.item(temp);
                    System.out.println("\nÉlément courant : " + nNode.getNodeName());
                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElement = (Element) nNode;
                        System.out.println("TrackId : " + eElement.getElementsByTagName("TrackId").item(0).getTextContent());
                        System.out.println("LyricChecksum : " + eElement.getElementsByTagName("LyricChecksum").item(0).getTextContent());
                        System.out.println("LyricId : " + eElement.getElementsByTagName("LyricId").item(0).getTextContent());
                        System.out.println("LyricSong : " + eElement.getElementsByTagName("LyricSong").item(0).getTextContent());
                        System.out.println("LyricArtist : " + eElement.getElementsByTagName("LyricArtist").item(0).getTextContent());
                        System.out.println("LyricUrl : " + eElement.getElementsByTagName("LyricUrl").item(0).getTextContent());
                        System.out.println("LyricRank : " + eElement.getElementsByTagName("LyricRank").item(0).getTextContent());
                        System.out.println("LyricCorrectUrl : " + eElement.getElementsByTagName("LyricCorrectUrl").item(0).getTextContent());
                        System.out.println("Lyric : " + eElement.getElementsByTagName("Lyric").item(0).getTextContent());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

