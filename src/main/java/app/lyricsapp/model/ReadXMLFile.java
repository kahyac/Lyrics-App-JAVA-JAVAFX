package app.lyricsapp.model;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;

public class ReadXMLFile {
    public static void main(String argv[]) {
        try {
            String basePath = ReadXMLFile.class.getProtectionDomain().getCodeSource().getLocation().getPath();
            File inputFile = new File(basePath + "/app/lyricsapp/view/query1.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

