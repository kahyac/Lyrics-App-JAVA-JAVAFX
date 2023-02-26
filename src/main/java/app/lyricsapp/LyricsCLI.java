package app.lyricsapp;

import java.io.IOException;
import java.util.Scanner;

import static app.lyricsapp.model.LobbyChoice.lobbyChoice;

public class LyricsCLI {
    private static boolean firstRun = true;

    public static void runCLI() throws IOException {
        int choice = -1;

        if(firstRun) {
            System.out.println("\033[0;34m\nBienvenue sur LyricsCLI !\033[0m");
            firstRun = false;
        }

        while(choice != 3) {
            if(choice != 1 && choice != 2) {
                System.out.println("\033[0;36m\n\nMenu principal :\033[0m");
                System.out.println("\033[0;32m\n1- Rechercher une chanson\033[0m");
                System.out.println("\033[0;31m2- Accéder aux favoris\033[0m");
                System.out.println("\033[0;35m3- Quitter\033[0m");
                System.out.print("\033[0;34m\nSaisir votre choix : \033[0m");
            }
            Scanner scanner = new Scanner(System.in);

            try {
                choice = scanner.nextInt();
                if(choice < 1 || choice > 3) {
                    throw new Exception("\033[0;30m\nLes seules valeurs possibles sont 1, 2 et 3" +
                            "\033[0m");
                }
            } catch (Exception e) {
                choice = -1;
                System.out.println("\033[0;30m\nEntrée invalide - Retour au menu principal\033[0m");
            }

            lobbyChoice(choice);
        }

        System.out.println("\033[0;36m\n\nFin du programme\n\033[0m");
        System.exit(0);
    }

    public static void main(String[] args) throws IOException {
        runCLI();
    }

}

