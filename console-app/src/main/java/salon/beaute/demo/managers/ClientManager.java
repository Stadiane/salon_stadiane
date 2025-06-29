package salon.beaute.demo.managers;

import salon.beaute.demo.models.Client;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ClientManager {
    private List<Client> clients;
    private String fichier;

    public ClientManager(String fichier) {
        this.fichier = fichier;
        this.clients = new ArrayList<>();
        chargerClients();
    }

    private void chargerClients() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fichier))) {
            String ligne;
            while ((ligne = reader.readLine()) != null) {
                clients.add(Client.fromCSV(ligne));
            }
        } catch (IOException e) {
            System.out.println("⚠️ Impossible de charger les clients : " + e.getMessage());
        }
    }

    public void ajouterClient(Client client) {
        clients.add(client);
        sauvegarder();
    }

    private void sauvegarder() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fichier))) {
            for (Client c : clients) {
                writer.write(c.toCSV());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("⚠️ Erreur lors de la sauvegarde : " + e.getMessage());
        }
    }

    public List<Client> getClients() {
        return clients;
    }

    public void afficherClients() {
        for (Client c : clients) {
            c.afficher();
        }
    }
}
