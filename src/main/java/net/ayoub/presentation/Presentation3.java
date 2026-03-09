package net.ayoub.presentation;

import net.ayoub.metier.IMetier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * VERSION 3 – Spring Framework avec configuration XML
 *
 * Spring prend en charge TOUT le travail de réflexion et d'injection.
 * On se contente de décrire les beans dans config.xml et Spring fait le reste.
 *
 * AVANTAGE : Zéro code de réflexion, configuration centralisée dans un seul fichier XML.
 * Pour changer l'implémentation DAO, on modifie uniquement config.xml,
 * sans toucher au code Java → AUCUNE RECOMPILATION.
 *
 * getBean(IMetier.class) retourne le bean déjà complètement initialisé et injecté.
 */
public class Presentation3 {

    public static void main(String[] args) {
        System.out.println("=== VERSION 3 : Spring Framework – Configuration XML ===\n");

        // Spring charge le fichier config.xml depuis le classpath (src/main/resources/)
        // Il instancie tous les beans et injecte les dépendances automatiquement
        ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");

        // Récupération du bean IMetier déjà prêt à l'emploi (injection déjà effectuée)
        IMetier metier = context.getBean(IMetier.class);

        System.out.println("Résultat : " + metier.calcul());

        // Fermeture du contexte Spring (libération des ressources)
        ((ClassPathXmlApplicationContext) context).close();
    }
}
