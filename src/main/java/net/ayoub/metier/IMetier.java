package net.ayoub.metier;

/**
 * Interface de la couche Métier.
 * Définit le contrat pour les traitements métier.
 */
public interface IMetier {
    /**
     * Effectue un calcul métier basé sur les données récupérées depuis le DAO.
     * @return le résultat du calcul
     */
    double calcul();
}
