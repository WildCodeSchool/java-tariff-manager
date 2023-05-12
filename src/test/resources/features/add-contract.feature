# language: fr
Fonctionnalité: Ajouter un contrat
    Scénario: Ajout d'un contrat pour un client
        Etant donné le client suivant:
            | Paul  | Poulpe |
            Et les tariffs suivants:
                | Téléphone  | 55.0 |
                | Pendule  | 23.0 |
        Quand je crée un contrat pour le client "Paul Poulpe" sur le produit "Pendule"
        Alors le client "Paul Poulpe" a 1 contrat
            Et le contrat 1 du client "Paul Poulpe" a pour produit "Pendule"