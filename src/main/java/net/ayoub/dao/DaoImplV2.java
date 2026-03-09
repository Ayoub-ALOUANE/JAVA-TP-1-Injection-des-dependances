package net.ayoub.dao;

import org.springframework.stereotype.Repository;

/**
 * Implémentation n°2 de IDao – Version Capteur (Sensor).
 * Simule la récupération d'une température depuis un capteur physique.
 *
 * Démontre l'extensibilité : on crée une nouvelle classe sans modifier
 * le code existant (principe Open/Closed).
 *
 * @Repository("daoV2") distingue ce bean de DaoImpl pour Spring.
 * Pour l'utiliser : changer config.txt ou utiliser @Qualifier("daoV2").
 */
@Repository("daoV2")
public class DaoImplV2 implements IDao {

    @Override
    public double getData() {
        System.out.println("[DaoImplV2] Récupération de données depuis le Capteur (Sensor)...");
        // Simulation d'une valeur retournée par un capteur physique
        return 25.0;
    }
}
