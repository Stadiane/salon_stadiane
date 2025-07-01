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
}
