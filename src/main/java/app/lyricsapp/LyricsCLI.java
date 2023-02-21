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
        System.out.println("\n\nMenu principal :");
        System.out.println("\n1- Rechercher une chanson");
        System.out.println("2- Accéder aux favoris");
        System.out.println("3- Quitter");
        System.out.print("\nSaisir votre choix : ");
    }

    public static void lobbyChoice(int choice) throws IOException {
        int searchChoice;
        int favoriteChoice;
        int element = 1;

        if (choice == 1) {
            System.out.println("\n\nRechercher une chanson :\n");
            System.out.println("1- Par titre et artiste");
            System.out.println("2- Par paroles");
            System.out.println("3- Retour au menu principal");
            System.out.print("\nSaisir votre choix : ");
            Scanner scanner = new Scanner(System.in);

            try {
                searchChoice = scanner.nextInt();
                if(searchChoice < 1 || searchChoice > 3) {
                    throw new Exception("\nLes seules valeurs possibles sont 1, 2 et 3");
                }
            } catch (Exception e) {
                searchChoice = -1;
                System.out.println("\nEntrée invalide - Retour au menu principal");
                display();
            }
            searchChoice(searchChoice);
        }

        else if (choice == 2) {
            System.out.println("\n\nAccès aux favoris :\n");
            if(favorites.favoriteSongSize() == 0) {
                System.out.println("Vous n'avez pour l'instant pas encore ajouté de chansons parmi les favoris\n");
            } else {
                System.out.println("Voici les chansons favorites :\n");
                for (Song favorite : favorites.getFavoriteSongs()) {
                    System.out.println(element + "- " + favorite);
                    element++;
                }
            }
            System.out.println("\n1- Afficher les paroles d’une chanson parmi les favoris");
            System.out.println("2- Supprimer une chanson des favoris");
            System.out.println("3- Retour au menu principal");
            System.out.print("\nSaisir votre choix : ");

            Scanner scanner = new Scanner(System.in);

            try {
                favoriteChoice = scanner.nextInt();
                if(favoriteChoice < 1 || favoriteChoice > 3) {
                    throw new Exception("\nLes seules valeurs possibles sont 1, 2 et 3");
                }
            } catch (Exception e) {
                favoriteChoice = -1;
                System.out.println("\nEntrée invalide - Retour au menu principal");
                display();
            }
            favoriteChoice(favoriteChoice);
        }
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
        else if(searchChoice == 3) {
            display();
        }
    }

    public static void afterSearch() throws IOException {
        Song song = new Song(Parse.getArtists()[0], Parse.getSongs()[0], Parse.getLyrics()[0]);
        int afterSearchChoice = -1;

        System.out.println("\n1- Afficher les paroles de la chanson");
        System.out.println("2- Ajouter/retirer la chanson aux favoris");
        System.out.println("3- Retour au menu principal");
        System.out.print("\nSaisir votre choix : ");

        Scanner scanner = new Scanner(System.in);
        try {
            afterSearchChoice = scanner.nextInt();
            if(afterSearchChoice < 1 || afterSearchChoice > 3) {
                throw new Exception("\nLes seules valeurs possibles sont 1, 2 et 3");
            }

        } catch (Exception e) {
            System.out.println("\nEntrée invalide - Retour au menu principal");
            display();
        }

        if (afterSearchChoice == 1) {
            System.out.println("\nLyrics : " + Parse.getLyrics()[0]);
            display();
        }
        else if (afterSearchChoice == 2) {
            if (favorites.songIsInFavoriteSongs(song)) {
                favorites.removeFavorite(song);
                System.out.println("\nLa chanson a été supprimé des favoris");
                display();
            }
            else {
                favorites.addFavorite(song);
                System.out.println("\nLa chanson a été ajouté aux favoris");
                display();
            }
        }
        else if (afterSearchChoice == 3) {
            display();
        }
    }

    public static void favoriteChoice(int favoriteChoice) throws IOException {
        int showSong;
        int deleteSong;

        if(favoriteChoice == 3) {
            display();
        }
        else if (favoriteChoice == 1 || favoriteChoice == 2) {
            if(favorites.favoriteSongSize() != 0) {
                if (favoriteChoice == 1) {
                    System.out.print("Sélectionner une chanson afin d'afficher ses paroles : ");
                    Scanner scanner = new Scanner(System.in);
                    try {
                        showSong = scanner.nextInt();
                        if(showSong < 1) {
                            throw new Exception("\nVeuillez sélectionner une chanson parmi celles disponibles");
                        } else {
                            System.out.println("\nVous avez sélectionné " + favorites.getSong(showSong - 1));
                            System.out.println("\n" + favorites.getSong(showSong - 1).getLyrics());
                        }
                        display();
                    } catch (Exception e) {
                        System.out.println("\nEntrée invalide - Retour au menu principal");
                        display();
                    }
                }
                else {
                    System.out.print("Sélectionner une chanson à supprimer des favoris : ");
                    Scanner scanner = new Scanner(System.in);
                    try {
                        deleteSong = scanner.nextInt();
                        if(deleteSong < 1) {
                            throw new Exception("\nVeuillez sélectionner une chanson parmi celles disponibles");
                        } else {
                            System.out.println("\nVous avez décidé de supprimer des favoris " +
                                    favorites.getSong(deleteSong - 1));
                            favorites.removeFavorite(favorites.getSong(deleteSong - 1));
                        }
                        display();
                    } catch (Exception e) {
                        System.out.println("\nEntrée invalide - Retour au menu principal");
                        display();
                    }
                }
            }
            else {
                System.out.println("\nDésolé, mais il est impossible d'effectuer cette action, car il n'y a aucune " +
                        "chanson enregistrée dans les favoris");
                display();
            }
        }
    }

    public static void runCLI() throws IOException {

        System.out.println("\nBienvenue sur LyricsCLI");

        int choice = -1;

        while(choice != 3) {
            if(choice != 1 && choice != 2) {
                display();
            }
            Scanner scanner = new Scanner(System.in);

            try {
                choice = scanner.nextInt();
                if(choice < 1 || choice > 3) {
                    throw new Exception("\nLes seules valeurs possibles sont 1, 2 et 3");
                }
            } catch (Exception e) {
                choice = -1;
                System.out.println("\nEntrée invalide - Retour au menu principal");
            }

            lobbyChoice(choice);
        }

        System.out.println("\nFin du programme");
    }

    public static void main(String[] args) throws IOException {
        runCLI();
    }

}

