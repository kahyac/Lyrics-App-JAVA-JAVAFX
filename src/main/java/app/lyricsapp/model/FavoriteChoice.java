package app.lyricsapp.model;

import java.io.IOException;
import java.util.Scanner;

import static app.lyricsapp.LyricsCLI.runCLI;

public class FavoriteChoice {
    private static final FavoriteManager favorites = new FavoriteManager();

    public static FavoriteManager getFavorites() {
        return favorites;
    }

    public static void favoriteChoice(int favoriteChoice) throws IOException {
        int showSong;
        int deleteSong;

        if(favoriteChoice == 3) {
            runCLI();
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
                                    favorites.getSong(showSong - 1) + "\nParoles :");
                            System.out.println("\033[0;36m" + favorites.getSong(showSong - 1).getLyrics());
                        }
                        runCLI();
                    } catch (Exception e) {
                        System.out.println("\033[0;30m\nEntrée invalide - Retour au menu principal\033[0m");
                        runCLI();
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
                        runCLI();
                    } catch (Exception e) {
                        System.out.println("\033[0;30m\nEntrée invalide - Retour au menu principal\033[0m");
                        runCLI();
                    }
                }
            }
            else {
                System.out.println("\033[0;33m\nDésolé, mais il est impossible d'effectuer cette action, car il " +
                        "n'y a aucune chanson enregistrée dans les favoris\033[0m");
                runCLI();
            }
        }
    }

}

