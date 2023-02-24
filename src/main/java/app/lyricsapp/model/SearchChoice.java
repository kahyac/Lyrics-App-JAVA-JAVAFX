package app.lyricsapp.model;

import java.io.IOException;
import java.util.Scanner;

import static app.lyricsapp.LyricsCLI.runCLI;
import static app.lyricsapp.model.AfterSearch.afterSearch;

public class SearchChoice {

    public static void searchChoice(int searchChoice) throws IOException {
        try {
            int index;

            if (searchChoice == 1) {
                System.out.print("\033[0;34m\nVeuillez inscrire le titre de la chanson : \033[0m");
                Scanner scanner = new Scanner(System.in);
                String title = scanner.nextLine();
                System.out.print("\033[0;34mVeuillez inscrire le nom de l'artiste : \033[0m");
                String artist = scanner.nextLine();

                try {
                    Search.songByArtistAndTitle(artist, title);
                } catch (Exception e) {
                    System.out.println("\033[0;30mOups... Il semblerait que votre recherche soit introuvable dans " +
                            "l'API ChartLyrics - Retour au menu principal\033[0m");
                    runCLI();
                }


                if (Parse.getArtists().length == 0) {
                    System.out.println("\033[0;30m\nOups... Il semblerait que votre recherche soit introuvable dans " +
                            "l'API ChartLyrics - Retour au menu principal\033[0m");
                    runCLI();
                }

                Search.lyricsByArtistAndTitle(Parse.getArtists()[0], Parse.getSongs()[0]);

                System.out.println("\033[0;33m\nVous avez recherché : Artiste => " + Parse.getArtists()[0]);
                System.out.println(" ".repeat(22) + "Chanson => " + Parse.getSongs()[0]);

                afterSearch();
            }

            else if (searchChoice == 2) {
                System.out.print("\033[0;34m\nVeuillez inscrire les paroles de la chanson : \033[0m");
                Scanner scanner = new Scanner(System.in);
                String lyrics = scanner.nextLine();

                System.out.println("\nVoici la liste des chansons disponibles :\n\033[0;33m");
                Search.songByLyrics(lyrics);

                if (Parse.getArtists().length == 0) {
                    System.out.println("\033[0;30mOups... Il semblerait que votre recherche soit introuvable dans " +
                            "l'API ChartLyrics - Retour au menu principal\033[0m");
                    runCLI();
                }

                System.out.print("\033[0;34m\nChoisir la musique : \033[0m");
                Scanner scanner2 = new Scanner(System.in);

                try {
                    index = scanner2.nextInt();

                    if (index < 1 || index > Parse.getSongs().length) {
                        throw new Exception("\033[0;30m\nIl faut entrer une valeur parmi les chansons proposées " +
                                "ci-dessus\033[0m");
                    } else {
                        Search.lyricsByArtistAndTitle(Parse.getArtists()[index - 1], Parse.getSongs()[index - 1]);
                        System.out.println("\033[0;33m\nVous avez sélectionné : Artiste => " +
                                Parse.getArtists()[index - 1]);
                        System.out.println(" ".repeat(24) + "Chanson => " + Parse.getSongs()[index - 1]);

                        afterSearch();
                    }
                } catch (Exception e) {
                    System.out.println("\033[0;30m\nEntrée invalide - Retour au menu principal\033[0m");
                    runCLI();
                }
            }
        } catch (Exception e) {
            System.out.println("Erreur : " + e);
        } finally {
            runCLI();
        }
    }

}

