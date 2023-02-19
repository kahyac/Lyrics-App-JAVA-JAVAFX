import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class MenuNavigation {

    public static void display() {
        System.out.println("1- Rechercher une chanson");
        System.out.println("2- Accéder aux favoris");
        System.out.println("3- Quitter");
    }

    public static void lobbyChoice(int choice) {
        int searchChoice = -1;
        int favoriteChoice = -1;

        if (choice == 1) {
            System.out.println("\nRechercher une chanson :\n");
            System.out.println("1- Par titre et artiste");
            System.out.println("2- Par paroles");
            System.out.println("3- Retour");
            System.out.print("\nSaisir votre choix : ");
            Scanner scanner = new Scanner(System.in);

            try {
                searchChoice = scanner.nextInt();
            } catch (Exception e) {
                searchChoice = -1;
            }
            searchChoice(searchChoice);
        }

        else if (choice == 2) {
            System.out.println("\nAccès aux favoris :\n");
            System.out.println("1- Afficher les paroles d’une chanson parmi les favoris");
            System.out.println("2- Supprimer une chanson des favoris");
            System.out.println("3- Retour");
            System.out.print("\nSaisir votre choix : ");

            Scanner scanner = new Scanner(System.in);

            try {
                favoriteChoice = scanner.nextInt();
            } catch (Exception e) {
                favoriteChoice = -1;
            }
            favoriteChoice(favoriteChoice);
        }

        else {
            System.out.println("Entrée invalide");
        }
    }

    public static void searchChoice(int searchChoice) {

        if (searchChoice == 1) {

            System.out.print("\nVeuillez inscrire le titre de la chanson : ");
            Scanner scanner = new Scanner(System.in);
            String title = scanner.nextLine();
            System.out.print("Veuillez inscrire le nom de l'artiste : ");
            String artist = scanner.nextLine();
            searchSongByArtistAndTitle(artist, title);
        }

        else if (searchChoice == 2) {

            System.out.print("\nVeuillez inscrire les paroles de la chanson : ");
            Scanner scanner = new Scanner(System.in);
            String lyrics = scanner.nextLine();
            searchSongByLyrics(lyrics);
        }
        else if (searchChoice == 3) {

        }

        else {
            System.out.println("Entrée invalide");
        }
    }

    public static void searchSongByArtistAndTitle(String artist, String title) {

        artist =  artist.replaceAll(" ", "%20");
        title =  title.replaceAll(" ", "%20");

        try {

            URL url = new URL("http://api.chartlyrics.com/apiv1.asmx/SearchLyric?artist=" + artist + "&song=" + title);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;

            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            in.close();
            System.out.println(response);
            readXML(String.valueOf(response));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void searchSongByLyrics(String lyrics) {

        lyrics =  lyrics.replaceAll(" ", "%20");

        try {

            URL url = new URL("http://api.chartlyrics.com/apiv1.asmx/SearchLyricText?lyricText=" + lyrics);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;

            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            in.close();
            System.out.println(response);
            readXML(String.valueOf(response));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readXML(String XML) {

        try {

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(new InputSource(new StringReader(XML)));

            // Extraire les éléments avec les noms "Artist", "Song", "LyricId" et "LyricChecksum" en utilisant getElementsByTagName()
            NodeList artistList = doc.getElementsByTagName("Artist");
            NodeList songList = doc.getElementsByTagName("Song");
            NodeList lyricIdList = doc.getElementsByTagName("LyricId");
            NodeList lyricChecksumList = doc.getElementsByTagName("LyricChecksum");

            // Créer des tableaux de String pour stocker le contenu des éléments
            String[] artists = new String[artistList.getLength()];
            String[] songs = new String[songList.getLength()];
            String[] lyricIds = new String[lyricIdList.getLength()];
            String[] lyricChecksums = new String[lyricChecksumList.getLength()];

            // Parcourir les NodeList et extraire le contenu de chaque élément
            for (int i = 0; i < artistList.getLength(); i++) {
                Node artistNode = artistList.item(i);
                artists[i] = artistNode.getTextContent();
            }

            for (int i = 0; i < songList.getLength(); i++) {
                Node songNode = songList.item(i);
                songs[i] = songNode.getTextContent();
            }

            for (int i = 0; i < lyricIdList.getLength(); i++) {
                Node lyricIdNode = lyricIdList.item(i);
                lyricIds[i] = lyricIdNode.getTextContent();
            }

            for (int i = 0; i < lyricChecksumList.getLength(); i++) {
                Node lyricChecksumNode = lyricChecksumList.item(i);
                lyricChecksums[i] = lyricChecksumNode.getTextContent();
            }

            // Afficher le contenu des tableaux
            for (String artist : artists) {
                System.out.println("Artist: " + artist);
            }

            for (String song : songs) {
                System.out.println("Song: " + song);
            }

            for (String lyricId : lyricIds) {
                System.out.println("LyricId: " + lyricId);
            }

            for (String lyricChecksum : lyricChecksums) {
                System.out.println("LyricChecksum: " + lyricChecksum);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void favoriteChoice(int favoriteChoice) {

        if (favoriteChoice == 1) {
            System.out.println("Sélectionner une chanson à afficher : ");

        }
        else if (favoriteChoice == 2) {
            System.out.println("Sélectionner une chanson à supprimer : ");
        }
        else if (favoriteChoice == 3) {
            //
        }
        else {
            System.out.println("Entrée invalide");
        }
    }

    public static void titleArtistChoice(int choice) {

    }

    public static void runCLI() {

        System.out.println("\nBienvenue sur LyricsApp\n");

        int choice = -1;

        while(choice != 3) {
            if(choice != 1 && choice != 2) {
                display();
            }
            System.out.print("\nSaisir votre choix : ");
            Scanner scanner = new Scanner(System.in);

            try {
                choice = scanner.nextInt();
            } catch (Exception e) {
                choice = -1;
                System.out.println("Entrée invalide");
            }

            lobbyChoice(choice);
        }

        System.out.println("Fin du programme");
    }

    public static void main(String[] args) {
        runCLI();
    }

}
