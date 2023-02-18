package app.lyricsapp.model;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

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
            System.out.print("Veuillez inscrire le titre de la chanson : ");
            Scanner scanner = new Scanner(System.in);
            String title = scanner.nextLine();
            System.out.print("Veuillez inscrire le nom de l'artiste : ");
            String artist = scanner.nextLine();
            searchSong(title, artist);
        }
        else if (searchChoice == 2) {
            System.out.print("Veuillez inscrire les paroles de la chanson : ");
            Scanner scanner = new Scanner(System.in);
            String lyrics = scanner.nextLine();
        }
        else if (searchChoice == 3) {
            //
        }
        else {
            System.out.println("Entrée invalide");
        }
    }

    public static void searchSong(String title, String artist) {

        try {
            // encode song title and artist name for use in URL
            String encodedArtist = java.net.URLEncoder.encode(artist, "UTF-8");
            String encodedTitle = java.net.URLEncoder.encode(title, "UTF-8");

            // build URL for lyrics API
            String urlStr = "http://api.chartlyrics.com/apiv1.asmx/SearchLyric?artist=" + encodedArtist + "&song=" + encodedTitle;

            // create URL object and open connection
            URL url = new URL(urlStr);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            // set request method and headers
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json");

            // read response
            int status = con.getResponseCode();
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            con.disconnect();

            // print response
            System.out.println(content.toString());
        } catch (IOException e) {
            System.out.println("Erreur lors de la récupération des paroles de la chanson : " + e.getMessage());
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
        //
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
