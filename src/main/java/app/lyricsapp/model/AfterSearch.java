package app.lyricsapp.model;

import java.io.IOException;
import java.util.Scanner;

import static app.lyricsapp.model.FavoriteChoice.getFavorites;

public class AfterSearch {

    public static void afterSearch() throws IOException {
        int afterSearchChoice = -1;
        Song song = new Song(Parse.getArtist(), Parse.getSong(), Parse.getLyric());

        System.out.println("\033[0;34m\nQue souhaitez-vous faire maintenant ?");
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
            System.out.printf("\033[0;33m\nParoles :\n\033[0m\n\033[0;36m" + Parse.getLyric() + "\n\033[0m");
            afterSearch();
        }

        else if (afterSearchChoice == 2) {
            System.out.println("\033[0;33m\n" + song + "\033[0m");
            if (getFavorites().songIsInFavoriteSongs(song)) {
                getFavorites().removeFavorite(song);
                System.out.println("\033[0;33m\nLa chanson a été supprimé des favoris\033[0m");
            }
            else {
                getFavorites().addFavorite(song);
                System.out.println("\033[0;33m\nLa chanson a été ajouté aux favoris\033[0m");
            }
            afterSearch();
        }
    }

}

