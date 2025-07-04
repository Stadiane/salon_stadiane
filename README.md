#  Salon de Beauté - Application Java Console

Bienvenue dans mon projet de gestion d’un **salon de beauté**, développé en **Java** avec une interface **console (CLI)**.  
Ce programme permet aux clients de gérer leurs rendez-vous, et à l’administrateur de gérer les services proposés.


##  Structure du projet

console-app/
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── org/example/Main.java              ← Point d'entrée principal
│   │       ├── salon/beaute/demo/models/          ← Modèles : Client, Service, RendezVous
│   │       └── salon/beaute/demo/managers/        ← Managers : logique + persistance
│
├── data/
│   ├── clients.csv
│   ├── services.csv
│   └── rendezvous.csv


##  Fonctionnalités développées

### 1.  Gestion des services
- Lister tous les services
- Ajouter un service (nom, description, prix, durée, image)
- Supprimer un service
- Rechercher un service par nom

 Fichier : `services.csv`  
 Classe : `Service.java`  
 Manager : `ServiceManager.java`  

---

### 2.  Espace client
- Inscription (nom, email, téléphone, mot de passe)
- Connexion (avec contrôle du mot de passe)
- Message de bienvenue personnalisé après connexion

 Fichier : `clients.csv`  
 Classe : `Client.java`  
 Manager : `ClientManager.java`  

---

### 3.  Réservation de rendez-vous
- Réserver un service à une date précise
- Afficher les rendez-vous du client connecté
- Modifier la **date** d’un rendez-vous existant
- **Annuler** un rendez-vous

 Les identifiants des rendez-vous sont générés au format : `RDV001`, `RDV002`…  
 Persistance dans : `rendezvous.csv`  
 Classe : `RendezVous.java`  
 Manager : `RendezVousManager.java`  



### 4. Gestion des erreurs & validation
- Contrôle du format des dates (`LocalDate`)
- Vérification de la correspondance des mots de passe
- Gestion des erreurs de saisie (`try/catch`)
- Messages clairs en cas de mauvaise saisie


##  Exemple d'exécution

=== MENU PRINCIPAL ===
1. Lister les services
2. Ajouter un service
3. Supprimer un service
4. Rechercher un service
5. Espace client
0. Quitter

=== ESPACE CLIENT ===
1. S'inscrire
2. Se connecter

=== MENU CLIENT CONNECTÉ ===
1. Réserver un rendez-vous
2. Voir mes rendez-vous
3. Annuler un rendez-vous
4. Modifier la date d'un rendez-vous
0. Retour


##  Technologies utilisées

- **Java 21**
- **Scanner** : lecture console
- **LocalDate** : manipulation des dates
- **Fichiers `.csv`** : persistance des données


##  Lancer le projet

1. Cloner le dépôt :
git clone https://github.com/<votre-repo>/Salon-de-beaute.git
2. Ouvrir dans **IntelliJ IDEA** 
3. Lancer le fichier `Main.java`


## Améliorations possibles

- Ajout d’une interface graphique
- Passage à une base de données (MySQL)
- Gestion des autres rôles (Admin / Employés)
- Gestion ds statuts des RDV une fois la date passé
- Système d’avis / notes pour les services
- Gestion du planning et des disponibilités


 Réalisé par : Stadiane METINO 
 Projet Java - ESGI
