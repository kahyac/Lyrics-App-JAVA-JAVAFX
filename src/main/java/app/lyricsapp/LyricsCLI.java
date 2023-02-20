package app.lyricsapp;

import app.lyricsapp.model.FavoriteManager;
import app.lyricsapp.model.Parse;
import app.lyricsapp.model.Search;
import app.lyricsapp.model.Song;

import java.io.IOException;
import java.util.Scanner;

public class LyricsCLI {

    private static FavoriteManager favorites = new FavoriteManager();

    public static void display() {
        System.out.println("1- Rechercher une chanson");
        System.out.println("2- Accéder aux favoris");
        System.out.println("3- Quitter");
    }

    public static void lobbyChoice(int choice) throws IOException {
        int searchChoice = -1;
        int favoriteChoice = -1;
        int element = 1;

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
                System.out.println("\nEntrée invalide");
            }
            searchChoice(searchChoice);
        }

        else if (choice == 2) {
            if(favorites.favoriteSongSize() == 0) {
                System.out.println("\nVous n'avez pour l'instant pas encore ajouté de chansons parmi les favoris\n");
            } else {
                System.out.println("\nVoici les chansons favorites :\n");
                for (Song favorite : favorites.getFavoriteSongs()) {
                    System.out.println(element + " " + favorite + "\n");
                    element++;
                }
            }
            System.out.println("Accès aux favoris :\n");
            System.out.println("1- Afficher les paroles d’une chanson parmi les favoris");
            System.out.println("2- Supprimer une chanson des favoris");
            System.out.println("3- Retour");
            System.out.print("\nSaisir votre choix : ");

            Scanner scanner = new Scanner(System.in);

            try {
                favoriteChoice = scanner.nextInt();
            } catch (Exception e) {
                favoriteChoice = -1;
                System.out.println("\nEntrée invalide");
            }
            favoriteChoice(favoriteChoice);
        }

        /*else {
            System.out.println("\nEntrée invalide");
        }*/
    }

    public static void searchChoice(int searchChoice) throws IOException {

        if (searchChoice == 1) {

            System.out.print("\nVeuillez inscrire le titre de la chanson : ");
            Scanner scanner = new Scanner(System.in);
            String title = scanner.nextLine();
            System.out.print("Veuillez inscrire le nom de l'artiste : ");
            String artist = scanner.nextLine();

            Search.songByArtistAndTitle(artist, title);
            System.out.println("\nArtiste : " + Parse.getArtists()[0]);
            System.out.println("Chanson : " + Parse.getSongs()[0]);

            afterSearch();
        }

        else if (searchChoice == 2) {

            System.out.print("\nVeuillez inscrire les paroles de la chanson : ");
            Scanner scanner = new Scanner(System.in);
            String lyrics = scanner.nextLine();

            Search.songByLyrics(lyrics);
            System.out.println("\nArtiste : " + Parse.getArtists()[0]);
            System.out.println("Chanson : " + Parse.getSongs()[0]);

            afterSearch();
        }

        else {
            System.out.println("\nEntrée invalide");
        }
    }

    public static void afterSearch() throws IOException {
        Song song = new Song(Parse.getArtists()[0], Parse.getSongs()[0], Parse.getLyrics()[0]);
        int afterSearchChoice = -1;

        System.out.println("\n1- Afficher les paroles de la chanson");
        System.out.println("2- Ajouter/retirer la chanson aux favoris");
        System.out.print("\nSaisir votre choix : ");

        Scanner scanner = new Scanner(System.in);
        try {
            afterSearchChoice = scanner.nextInt();
        } catch (Exception e) {
            afterSearchChoice = -1;
            System.out.println("\nEntrée invalide");
        }

        if (afterSearchChoice == 1) {
            System.out.println("\nLyrics : " + Parse.getLyrics()[0]);
        }
        else if (afterSearchChoice == 2) {
            if (favorites.songIsInFavoriteSongs(song)) {
                favorites.removeFavorite(song);
                System.out.println("\nLa chanson a été supprimé des favoris");
            }
            else {
                favorites.addFavorite(song);
                System.out.println("\nLa chanson a été ajouté aux favoris");
            }
        }
    }

    public static void favoriteChoice(int favoriteChoice) throws IOException {
        int showSong = -1;
        int deleteSong = -1;
        if(favorites.favoriteSongSize() != 0) {
            if (favoriteChoice == 1) {
                System.out.print("Sélectionner une chanson afin d'afficher ses paroles : ");
                Scanner scanner = new Scanner(System.in);
                try {
                    showSong = scanner.nextInt();
                    System.out.println("\nVous avez sélectionné " + favorites.getSong(showSong));
                    System.out.println(favorites.getSong(showSong).getLyrics());
                } catch (Exception e) {
                    showSong = -1;
                    System.out.println("\nEntrée invalide");
                }
            }
            else if (favoriteChoice == 2) {
                System.out.print("Sélectionner une chanson à supprimer des favoris : ");
                Scanner scanner = new Scanner(System.in);
                try {
                    deleteSong = scanner.nextInt();
                    System.out.println("\nVous avez décidé de supprimer des favoris " + favorites.getSong(deleteSong));
                    favorites.removeFavorite(favorites.getSong(deleteSong));
                } catch (Exception e) {
                    deleteSong = -1;
                    System.out.println("\nEntrée invalide");
                }
            }
            else if (favoriteChoice == 3) {
                //
            }
            else {
                System.out.println("\nEntrée invalide");
            }
        }
        else {
            System.out.println("Désolé, mais il est impossible d'effectuer cette action, car il n'y a aucune " +
                    "chanson enregistrée dans les favoris");
            //retour en arrière
        }
    }

    public static void runCLI() throws IOException {

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
                System.out.println("\nEntrée invalide");
            }

            lobbyChoice(choice);
        }

        System.out.println("Fin du programme");
    }

    public static void main(String[] args) throws IOException {
        runCLI();
    }

}
