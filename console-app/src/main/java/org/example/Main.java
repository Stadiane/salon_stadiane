package org.example;

import salon.beaute.demo.managers.ServiceManager;
import salon.beaute.demo.models.Service;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ServiceManager manager = new ServiceManager();
        Scanner scanner = new Scanner(System.in);
        int choix;

        do {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1. Lister les services");
            System.out.println("2. Ajouter un service");
            System.out.println("3. Supprimer un service");
            System.out.println("4. Rechercher un service");
            System.out.println("0. Quitter");
            System.out.print("Votre choix : ");
            choix = scanner.nextInt();
            scanner.nextLine(); // consomme le retour

            switch (choix) {
                case 1:
                    manager.afficherTous();
                    break;
                case 2:
                    System.out.print("Nom : ");
                    String nom = scanner.nextLine();
                    System.out.print("Description : ");
                    String desc = scanner.nextLine();
                    System.out.print("Prix (€) : ");
                    double prix = scanner.nextDouble();
                    System.out.print("Durée (min) : ");
                    int duree = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("URL de l'image : ");
                    String img = scanner.nextLine();

                    Service service = new Service(nom, desc, prix, duree, img);
                    manager.ajouter(service);
                    System.out.println(" Service ajouté !");
                    break;
                case 3:
                    System.out.print("Nom du service à supprimer : ");
                    String nomSuppr = scanner.nextLine();
                    boolean supprime = manager.supprimerParNom(nomSuppr);
                    if (supprime) {
                        System.out.println(" Supprimé !");
                    } else {
                        System.out.println(" Introuvable.");
                    }
                    break;
                case 4:
                    System.out.print("Nom du service à rechercher : ");
                    String recherche = scanner.nextLine();
                    Service trouve = manager.chercherParNom(recherche);
                    if (trouve != null) {
                        trouve.afficher();
                    } else {
                        System.out.println(" Aucun service trouvé.");
                    }
                    break;
                case 0:
                    System.out.println(" Au revoir !");
                    break;
                default:
                    System.out.println(" Choix invalide.");
            }
        } while (choix != 0);

        scanner.close();
    }
}
