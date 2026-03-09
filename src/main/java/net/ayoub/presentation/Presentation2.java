package net.ayoub.presentation;

import net.ayoub.dao.IDao;
import net.ayoub.metier.IMetier;
import net.ayoub.metier.MetierImpl;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Scanner;

/**
 * VERSION 2 – Instanciation DYNAMIQUE par Réflexion Java
 *
 * On n'utilise PLUS le mot-clé `new` dans ce fichier.
 * Les noms des classes à instancier sont lus depuis config.txt.
 * Java Reflection permet de charger et d'instancier les classes dynamiquement.
 *
 * AVANTAGE : L'application est 100% FERMÉE à la modification.
 * Pour passer à la version "Capteur", on change simplement config.txt :
 *   net.ayoub.dao.DaoImplV2     ← une seule ligne modifiée, AUCUNE RECOMPILATION.
 *
 * INCONVÉNIENT : Ce code de réflexion est lourd et répétitif.
 * C'est exactement pour éviter d'écrire cela que Spring Framework a été créé.
 */
public class Presentation2 {

    public static void main(String[] args) {
        System.out.println("=== VERSION 2 : Instanciation Dynamique (Réflexion) ===\n");

        try {
            // Lecture de config.txt ligne par ligne
            Scanner scanner = new Scanner(new File("config.txt"));

            // --- Ligne 1 : Classe DAO ---
            String daoClassName = scanner.nextLine().trim();
            System.out.println("Chargement de la classe DAO : " + daoClassName);

            // Class.forName() charge la classe en mémoire (lève ClassNotFoundException si introuvable)
            Class<?> cDao = Class.forName(daoClassName);

            // newInstance() crée une instance (requiert un constructeur sans argument public)
            IDao dao = (IDao) cDao.getDeclaredConstructor().newInstance();

            // --- Ligne 2 : Classe Métier ---
            String metierClassName = scanner.nextLine().trim();
            System.out.println("Chargement de la classe Métier : " + metierClassName);

            Class<?> cMetier = Class.forName(metierClassName);

            // Injection par CONSTRUCTEUR : on cherche le constructeur qui accepte un IDao
            IMetier metier;
            try {
                metier = (IMetier) cMetier.getConstructor(IDao.class).newInstance(dao);
                System.out.println("Injection réussie via le constructeur.");
            } catch (NoSuchMethodException e) {
                // Fallback : injection par SETTER si pas de constructeur avec IDao
                System.out.println("Pas de constructeur IDao. Injection via setter...");
                metier = (IMetier) cMetier.getDeclaredConstructor().newInstance();

                // Invocation dynamique du setter setDao(IDao dao)
                Method setterMethod = cMetier.getMethod("setDao", IDao.class);
                setterMethod.invoke(metier, dao);
                System.out.println("Injection réussie via le setter.");
            }

            scanner.close();

            System.out.println();
            System.out.println("Résultat : " + metier.calcul());

        } catch (ClassNotFoundException e) {
            System.err.println("Erreur : Classe introuvable → " + e.getMessage());
        } catch (InstantiationException e) {
            System.err.println("Erreur : Impossible d'instancier (classe abstraite ?) → " + e.getMessage());
        } catch (IllegalAccessException e) {
            System.err.println("Erreur : Accès interdit (constructeur privé ?) → " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erreur inattendue : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
