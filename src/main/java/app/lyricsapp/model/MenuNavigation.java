package app.lyricsapp.model;

import java.util.Scanner;

public class MenuNavigation {

    public static void display() {
        System.out.println("1- Rechercher une chanson");
        System.out.println("2- Accéder aux favoris");
        System.out.println("3- Quitter");
    }

    public static void lobbyChoice(int choice) {
        int searchChoice = -1;
        if (choice == 1) {
            System.out.println("Rechercher une chanson :\n");
            System.out.println("1- Par titre et artiste");
            System.out.println("2- Par paroles");
            System.out.println("3- Retour");
            System.out.print("\nSaisir votre choix : ");
            Scanner scanner = new Scanner(System.in);

            try {
                choice = scanner.nextInt();
            } catch (Exception e) {
                choice = -1;
                System.out.println("Entrée invalide");
            }

            searchChoice(choice);
        }
        else if (choice == 2) {
            System.out.println("Accès aux favoris :\n");
            System.out.println("1- Afficher les paroles d’une chanson");
            System.out.println("2- Supprimer une chanson");
            System.out.println("3- Retour");
            System.out.print("\nSaisir votre choix : ");

            Scanner scanner = new Scanner(System.in);

            try {
                choice = scanner.nextInt();
            } catch (Exception e) {
                choice = -1;
                System.out.println("Entrée invalide");
            }
            favoriteChoice(choice);
        }

        else {
            System.out.println("Entrée invalide");
        }
    }

    public static void searchChoice(int choice) {
        if (choice == 1) {
            System.out.println("1");
        }
        else if (choice == 2) {
            System.out.println("2");
        }
        else if (choice == 3) {
            System.out.println(3);
        }
        else {
            System.out.println("Entrée invalide");
        }
    }

    public static void favoriteChoice(int choice) {
        if (choice == 1) {
            System.out.println("1");
        }
        else if (choice == 2) {
            System.out.println("2");
        }
        else if (choice == 3) {
            System.out.println("3");
        }
        else {
            System.out.println("Entrée invalide");
        }
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
