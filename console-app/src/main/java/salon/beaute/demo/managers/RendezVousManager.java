package salon.beaute.demo.managers;

import salon.beaute.demo.models.Client;
import salon.beaute.demo.models.RendezVous;
import salon.beaute.demo.models.Service;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RendezVousManager {
    private static final String FILE_PATH = "src/data/rendezvous.csv";
    private List<RendezVous> rdvs = new ArrayList<>();
    private ClientManager clientManager;
    private ServiceManager serviceManager;

    public RendezVousManager(ClientManager clientManager, ServiceManager serviceManager) {
        this.clientManager = clientManager;
        this.serviceManager = serviceManager;
        charger();
    }

    public void ajouter(RendezVous rdv) {
        rdvs.add(rdv);
        sauvegarder();
    }

    public List<RendezVous> getAll() {
        return rdvs;
    }

    public void afficherTous() {
        for (RendezVous r : rdvs) r.afficher();
    }

    private void charger() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                rdvs.add(RendezVous.fromCSV(ligne, clientManager, serviceManager));
            }
        } catch (IOException e) {
            System.out.println("Erreur chargement RDV : " + e.getMessage());
        }
    }

    private void sauvegarder() {
        File f = new File(FILE_PATH);
        f.getParentFile().mkdirs();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (RendezVous r : rdvs) {
                writer.write(r.toCSV());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erreur sauvegarde RDV : " + e.getMessage());
        }
    }

    public void reserverRendezVous(Client client, Scanner scanner) {
        System.out.println("=== Réservation ===");
        serviceManager.afficherTous();
        System.out.print("Nom du service : ");
        String nom = scanner.nextLine();
        Service service = serviceManager.chercherParNom(nom);

        if (service == null) {
            System.out.println(" Service introuvable.");
            return;
        }

        System.out.print("Date (YYYY-MM-DD) : ");
        String dateStr = scanner.nextLine();

        try {
            LocalDate date = LocalDate.parse(dateStr);
            RendezVous rdv = new RendezVous(client, service, date, "Prévu");
            rdvs.add(rdv);
            sauvegarder();
            System.out.println(" Rendez-vous enregistré !");
        } catch (DateTimeParseException e) {
            System.out.println(" Date invalide.");
        }
    }
    public void afficherRendezVousPourClient(Client client) {
        System.out.println("=== Vos Rendez-vous ===");
        for (RendezVous rdv : rdvs) {
            if (rdv.getClient().getEmail().equals(client.getEmail())) {
                rdv.afficher();
            }
        }
    }
    public void modifierDateRendezVous(Client client, Scanner scanner) {
        afficherRendezVousPourClient(client);
        System.out.print("ID du rendez-vous à modifier (ex: RDV001) : ");
        String id = scanner.nextLine();

        for (RendezVous rdv : rdvs) {
            if (rdv.getId().toString().equalsIgnoreCase(id) &&
                    rdv.getClient().getEmail().equals(client.getEmail())) {
                System.out.print("Nouvelle date (YYYY-MM-DD) : ");
                String newDateStr = scanner.nextLine();
                try {
                    LocalDate newDate = LocalDate.parse(newDateStr);
                    rdv.setDate(newDate);
                    sauvegarder();
                    System.out.println(" Date mise à jour !");
                } catch (Exception e) {
                    System.out.println(" Date invalide.");
                }
                return;
            }
        }
        System.out.println(" RDV non trouvé.");
    }

    public void annulerRendezVous(Client client, Scanner scanner) {
        afficherRendezVousPourClient(client);
        System.out.print("ID du rendez-vous à annuler (ex: RDV001) : ");
        String id = scanner.nextLine();

        RendezVous toRemove = null;
        for (RendezVous rdv : rdvs) {
            if (rdv.getId().toString().equalsIgnoreCase(id) &&
                    rdv.getClient().getEmail().equals(client.getEmail())) {
                toRemove = rdv;
                break;
            }
        }

        if (toRemove != null) {
            rdvs.remove(toRemove);
            sauvegarder();
            System.out.println(" Rendez-vous annulé !");
        } else {
            System.out.println(" RDV non trouvé.");
        }
    }
}
