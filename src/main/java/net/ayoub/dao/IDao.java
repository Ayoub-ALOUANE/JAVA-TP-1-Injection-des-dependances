package net.ayoub.dao;

/**
 * Interface de la couche DAO.
 * Définit le contrat pour l'accès aux données.
 */
public interface IDao {
    /**
     * Récupère une donnée (ex : température) depuis une source de données.
     * @return une valeur de type double
     */
    double getData();
}
