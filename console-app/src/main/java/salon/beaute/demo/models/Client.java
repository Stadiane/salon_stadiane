package salon.beaute.demo.models;

public class Client {
    private String nom;
    private String telephone;
    private String email;

    public Client(String nom, String telephone, String email) {
        this.nom = nom;
        this.telephone = telephone;
        this.email = email;
    }

    public void afficher() {
        System.out.println("Client: " + nom + ", Téléphone: " + telephone + ", Email: " + email);
    }

    // --- Getters et Setters ---
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // --- Pour sauvegarder dans CSV ---
    public String toCSV() {
        return nom + ";" + telephone + ";" + email;
    }

    public static Client fromCSV(String line) {
        String[] parts = line.split(";");
        return new Client(parts[0], parts[1], parts[2]);
    }
}
