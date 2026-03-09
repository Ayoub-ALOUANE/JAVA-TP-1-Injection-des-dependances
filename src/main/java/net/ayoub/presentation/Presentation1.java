package net.ayoub.presentation;

import net.ayoub.dao.DaoImpl;
import net.ayoub.dao.IDao;
import net.ayoub.metier.IMetier;
import net.ayoub.metier.MetierImpl;

/**
 * VERSION 1 – Instanciation STATIQUE (Couplage fort dans la couche Présentation)
 *
 * Le développeur utilise manuellement le mot-clé `new` pour créer les objets
 * et les lier entre eux.
 *
 * AVANTAGE : Simple à comprendre.
 * INCONVÉNIENT : Si le client demande une version "Capteur" du DAO,
 * il faut modifier ce fichier (changer DaoImpl par DaoImplV2)
 * et RECOMPILER l'application. La couche Présentation est OUVERTE à la modification.
 */
public class Presentation1 {

    public static void main(String[] args) {
        System.out.println("=== VERSION 1 : Instanciation Statique ===\n");

        // --- Injection par SETTER ---
        System.out.println("--- Injection par Setter ---");
        IDao dao = new DaoImpl();           // Instanciation de la dépendance
        MetierImpl metier = new MetierImpl(); // Constructeur sans argument
        metier.setDao(dao);                  // Injection de la dépendance
        System.out.println("Résultat : " + metier.calcul());

        System.out.println();

        // --- Injection par CONSTRUCTEUR ---
        System.out.println("--- Injection par Constructeur ---");
        IDao dao2 = new DaoImpl();
        IMetier metier2 = new MetierImpl(dao2); // Dépendance injectée via le constructeur
        System.out.println("Résultat : " + metier2.calcul());

        // NB : Retirer l'injection → NullPointerException !
        // MetierImpl metierSansInjection = new MetierImpl();
        // metierSansInjection.calcul(); // → NullPointerException car dao == null
    }
}
