package net.ayoub.dao;

import org.springframework.stereotype.Repository;

/**
 * Implémentation n°1 de IDao – Version Base de Données.
 * Simule la récupération d'une température depuis une base de données.
 *
 * @Repository est une spécialisation de @Component pour la couche DAO.
 * Le qualifiant "dao" permet à Spring de l'identifier par ce nom.
 */
@Repository("dao")
public class DaoImpl implements IDao {

    @Override
    public double getData() {
        System.out.println("[DaoImpl] Récupération de données depuis la Base de Données...");
        // Simulation d'une valeur retournée par la BD
        return 12.5;
    }
}
