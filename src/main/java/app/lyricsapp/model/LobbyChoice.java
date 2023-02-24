package app.lyricsapp.model;

import java.io.IOException;
import java.util.Scanner;

import static app.lyricsapp.LyricsCLI.runCLI;
import static app.lyricsapp.model.FavoriteChoice.favoriteChoice;
import static app.lyricsapp.model.FavoriteChoice.getFavorites;
import static app.lyricsapp.model.SearchChoice.searchChoice;

public class LobbyChoice {

    public static void lobbyChoice(int choice) throws IOException {
        int searchChoice;
        int favoriteChoice;
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
                runCLI();
            }
            searchChoice(searchChoice);
        }

        else if (choice == 2) {
            System.out.println("\033[0;36m\n\nAccès aux favoris :\n\033[0m");
            if(getFavorites().favoriteSongSize() == 0) {
                System.out.println("\033[0;33mIl n'y a aucune chanson parmi les favoris\033[0m");
            } else {
                System.out.println("Voici les chansons favorites :\n\033[0;33m");
                for (Song favorite : getFavorites().getFavoriteSongs()) {
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
                runCLI();
            }
            favoriteChoice(favoriteChoice);
        }
    }

}

