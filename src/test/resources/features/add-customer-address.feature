# language: fr
Fonctionnalité: Ajouter une adresse
    Scénario: Ajout d'une adresse pour un client
        Etant donné le client suivant:
            | Jean-Michel  | ParfaitPourUnTest |
        Quand j'ajoute une adresse 55 "Rue du Faubourg Saint-Honoré", "75008" "Paris" pour le client "Jean-Michel ParfaitPourUnTest"
        Alors le client "Jean-Michel ParfaitPourUnTest" a 1 adresse
            Et la ville de l'adresse 1 du client "Jean-Michel ParfaitPourUnTest" est 55 "Rue du Faubourg Saint-Honoré", "75008" "Paris" 