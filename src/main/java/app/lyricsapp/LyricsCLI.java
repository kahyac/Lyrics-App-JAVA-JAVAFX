package app.lyricsapp;

import app.lyricsapp.model.FavoriteManager;
import app.lyricsapp.model.Parse;
import app.lyricsapp.model.Search;
import app.lyricsapp.model.Song;

import java.io.IOException;
import java.util.Scanner;

public class LyricsCLI {
    private static final FavoriteManager favorites = new FavoriteManager();

    public static void main(String[] args) throws IOException {
        runCLI();
    }

    public static void display() {
        System.out.println("\033[0;36m\n\nMenu principal :\033[0m");
        System.out.println("\033[0;32m\n1- Rechercher une chanson\033[0m");
        System.out.println("\033[0;31m2- Accéder aux favoris\033[0m");
        System.out.println("\033[0;35m3- Quitter\033[0m");
        System.out.print("\033[0;34m\nSaisir votre choix : \033[0m");
    }

    public static void lobbyChoice(int choice) throws IOException {
        int searchChoice = -1;
        int favoriteChoice = -1;
        int element = 1;

        if (choice == 1) {
            System.out.println("\033[0;36m\n\nRechercher une chanson :\n\033[0m");
            System.out.println("\033[0;32m1- Par titre et artiste\033[0m");
            System.out.println("\033[0;31m2- Par paroles\033[0m");
            System.out.println("\033[0;35m3- Retour au menu principal\033[0m");
            System.out.print("\033[0;34m\nSaisir votre choix : \033[0m");
            Scanner scanner = new Scanner(System.in);

            try {
                searchChoice = scanner.nextInt();
                if(searchChoice < 1 || searchChoice > 3) {
                    throw new Exception("\033[0;30m\nLes seules valeurs possibles sont 1, 2 et 3\033[0m");
                }
            } catch (Exception e) {
                searchChoice = -1;
                System.out.println("\033[0;30m\nEntrée invalide - Retour au menu principal\033[0m");
                display();
            }
            searchChoice(searchChoice);
        }

        else if (choice == 2) {
            System.out.println("\033[0;36m\n\nAccès aux favoris :\n\033[0m");
            if(favorites.favoriteSongSize() == 0) {
                System.out.println("\033[0;33mIl n'y a aucune chanson parmi les favoris\033[0m");
            } else {
                System.out.println("Voici les chansons favorites :\n\033[0;33m");
                for (Song favorite : favorites.getFavoriteSongs()) {
                    System.out.println(element + ") " + favorite);
                    element++;
                }
                System.out.println("\033[0m");
            }
            System.out.println("\033[0;32m\n1- Afficher les paroles d’une chanson parmi les favoris\033[0m");
            System.out.println("\033[0;31m2- Supprimer une chanson des favoris\033[0m");
            System.out.println("\033[0;35m3- Retour au menu principal\033[0m");
            System.out.print("\033[0;34m\nSaisir votre choix : \033[0m");

            Scanner scanner = new Scanner(System.in);

            try {
                favoriteChoice = scanner.nextInt();
                if(favoriteChoice < 1 || favoriteChoice > 3) {
                    throw new Exception("\033[0;30m\nLes seules valeurs possibles sont 1, 2 et 3\033[0m");
                }
            } catch (Exception e) {
                favoriteChoice = -1;
                System.out.println("\033[0;30m\nEntrée invalide - Retour au menu principal\033[0m");
                display();
            }
            favoriteChoice(favoriteChoice);
        }
    }

    public static void searchChoice(int searchChoice) throws IOException {
        int index;

        if (searchChoice == 1) {
            System.out.print("\033[0;34m\nVeuillez inscrire le titre de la chanson : \033[0m");
            Scanner scanner = new Scanner(System.in);
            String title = scanner.nextLine();
            System.out.print("\033[0;34mVeuillez inscrire le nom de l'artiste : \033[0m");
            String artist = scanner.nextLine();

            System.out.println("\nVoici la liste des chansons disponibles :\n\033[0;33m");
            Search.songByArtistAndTitle(artist, title);

            System.out.print("\033[0;34m\nChoisir la musique : \033[0m");
            Scanner scanner2 = new Scanner(System.in);

            try {
                index = scanner2.nextInt();

                if(index < 1 || index > Parse.getSongs().length) {
                    throw new Exception("\033[0;30m\nIl faut entrer une valeur parmi les chansons proposées ci-dessus"
                            + "\033[0m");
                } else {
                    Search.lyricsByArtistAndTitle(Parse.getArtists()[index - 1], Parse.getSongs()[index - 1]);
                    System.out.println("\033[0;33m\nVous avez sélectionné : Artiste => " +
                            Parse.getArtists()[index - 1]);
                    System.out.println(" ".repeat(24) + "Chanson => " + Parse.getSongs()[index - 1]);

                    afterSearch();
                }
            } catch (Exception e) {
                System.out.println("\033[0;30m\nEntrée invalide - Retour au menu principal\033[0m");
                display();
            }
        }

        else if (searchChoice == 2) {
            System.out.print("\033[0;34m\nVeuillez inscrire les paroles de la chanson : \033[0m");
            Scanner scanner = new Scanner(System.in);
            String lyrics = scanner.nextLine();

            System.out.println("\nVoici la liste des chansons disponibles :\n\033[0;33m");
            Search.songByLyrics(lyrics);

            System.out.print("\033[0;34m\nChoisir la musique : \033[0m");
            Scanner scanner2 = new Scanner(System.in);

            try {
                index = scanner2.nextInt();

                if(index < 1 || index > Parse.getSongs().length) {
                    throw new Exception("\033[0;30m\nIl faut entrer une valeur parmi les chansons proposées ci-dessus"
                            + "\033[0m");
                } else {
                    Search.lyricsByArtistAndTitle(Parse.getArtists()[index - 1], Parse.getSongs()[index - 1]);
                    System.out.println("\033[0;33m\nVous avez sélectionné : Artiste => " +
                            Parse.getArtists()[index - 1]);
                    System.out.println(" ".repeat(24) + "Chanson => " + Parse.getSongs()[index - 1]);

                    afterSearch();
                }
            } catch (Exception e) {
                System.out.println("\033[0;30m\nEntrée invalide - Retour au menu principal\033[0m");
                display();
            }
        }

        else if(searchChoice == 3) {
            display();
        }
    }

    public static void afterSearch() throws IOException {
        int afterSearchChoice = -1;
        Song song = new Song(Parse.getArtist(), Parse.getSong(), Parse.getLyric());

        System.out.println("\033[0;32m\n1- Afficher les paroles de la chanson\033[0m");
        System.out.println("\033[0;31m2- Ajouter/retirer la chanson aux favoris\033[0m");
        System.out.println("\033[0;35m3- Retour au menu principal\033[0m");
        System.out.print("\033[0;34m\nSaisir votre choix : \033[0m");
        Scanner scanner = new Scanner(System.in);

        try {
            afterSearchChoice = scanner.nextInt();
            if(afterSearchChoice < 1 || afterSearchChoice > 3) {
                throw new Exception("\033[0;30m\nLes seules valeurs possibles sont 1, 2 et 3\033[0m");
            }
        } catch (Exception e) {
            System.out.println("\033[0;30m\nEntrée invalide\033[0m");
            afterSearch();
        }

        if (afterSearchChoice == 1) {
            System.out.println("\033[0;33m\nParoles :\033[0m\n\033[0;36m" + Parse.getLyric() + "\n\033[0m");
            afterSearch();
        }

        else if (afterSearchChoice == 2) {
            System.out.println("\033[0;33m\n" + song);
            if (favorites.songIsInFavoriteSongs(song)) {
                favorites.removeFavorite(song);
                System.out.println("\033[0;33m\nLa chanson a été supprimé des favoris\033[0m");
            }
            else {
                favorites.addFavorite(song);
                System.out.println("\033[0;33m\nLa chanson a été ajouté aux favoris\033[0m");
            }
            afterSearch();
        }

        else if (afterSearchChoice == 3) {
            display();
        }
    }

    public static void favoriteChoice(int favoriteChoice) {
        int showSong;
        int deleteSong;

        if(favoriteChoice == 3) {
            display();
        }
        else if (favoriteChoice == 1 || favoriteChoice == 2) {
            if(favorites.favoriteSongSize() != 0) {
                if (favoriteChoice == 1) {
                    System.out.print("\033[0;34mSélectionner une chanson afin d'afficher ses paroles : \033[0m");
                    Scanner scanner = new Scanner(System.in);
                    try {
                        showSong = scanner.nextInt();
                        if(showSong < 1) {
                            throw new Exception("\033[0;30m\nVeuillez sélectionner une chanson parmi celles " +
                                    "disponibles\033[0m");
                        } else {
                            System.out.println("\033[0;33m\nVous avez sélectionné : " +
                                    favorites.getSong(showSong - 1));
                            System.out.println("\033[0;36m" + favorites.getSong(showSong - 1).getLyrics());
                        }
                        display();
                    } catch (Exception e) {
                        System.out.println("\033[0;30m\nEntrée invalide - Retour au menu principal\033[0m");
                        display();
                    }
                }
                else {
                    System.out.print("\033[0;34mSélectionner une chanson à supprimer des favoris : \033[0m");
                    Scanner scanner = new Scanner(System.in);
                    try {
                        deleteSong = scanner.nextInt();
                        if(deleteSong < 1) {
                            throw new Exception("\033[0;30m\nVeuillez sélectionner une chanson parmi celles " +
                                    "disponibles\033[0m");
                        } else {
                            System.out.println("\033[0;33m\nVous avez décidé de supprimer des favoris : " +
                                    favorites.getSong(deleteSong - 1));
                            favorites.removeFavorite(favorites.getSong(deleteSong - 1));
                        }
                        display();
                    } catch (Exception e) {
                        System.out.println("\033[0;30m\nEntrée invalide - Retour au menu principal\033[0m");
                        display();
                    }
                }
            }
            else {
                System.out.println("\033[0;33m\nDésolé, mais il est impossible d'effectuer cette action, car il " +
                        "n'y a aucune chanson enregistrée dans les favoris\033[0m");
                display();
            }
        }
    }

    public static void runCLI() throws IOException {
        int choice = -1;

        System.out.println("\033[0;34m\nBienvenue sur LyricsCLI !\033[0m");

        while(choice != 3) {
            if(choice != 1 && choice != 2) {
                display();
            }
            Scanner scanner = new Scanner(System.in);

            try {
                choice = scanner.nextInt();
                if(choice < 1 || choice > 3) {
                    throw new Exception("\033[0;30m\nLes seules valeurs possibles sont 1, 2 et 3\033[0m");
                }
            } catch (Exception e) {
                choice = -1;
                System.out.println("\033[0;30m\nEntrée invalide - Retour au menu principal\033[0m");
            }

            lobbyChoice(choice);
        }

        System.out.println("\033[0;36m\nFin du programme\033[0m");
    }

}
