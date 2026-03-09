package net.ayoub.presentation;

import net.ayoub.metier.IMetier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * VERSION 4 – Spring Framework avec Annotations
 *
 * On supprime complètement le fichier XML. À la place :
 * - @Repository sur les classes DAO
 * - @Service sur la classe Métier
 * - @Autowired + @Qualifier pour l'injection automatique
 *
 * Spring scanne le package "net.ayoub" à la recherche de composants annotés.
 *
 * GESTION DE L'AMBIGUÏTÉ : Si DaoImpl ET DaoImplV2 sont tous les deux @Repository,
 * Spring ne sait pas lequel injecter → UnsatisfiedDependencyException.
 * Solution : utiliser @Qualifier("dao") ou @Qualifier("daoV2") dans MetierImpl.
 */
public class Presentation4 {

    /**
     * Classe de configuration Spring par annotations.
     * @Configuration indique que c'est une classe de config Spring.
     * @ComponentScan("net.ayoub") demande à Spring de scanner tout le package
     * net.ayoub pour trouver les classes annotées @Component/@Repository/@Service.
     */
    @Configuration
    @ComponentScan("net.ayoub")
    static class SpringConfig {
        // Aucun bean déclaré manuellement : Spring découvre tout automatiquement
    }

    public static void main(String[] args) {
        System.out.println("=== VERSION 4 : Spring Framework – Annotations ===\n");

        // Démarrage du contexte Spring basé sur la classe de configuration annotée
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

        // Spring a déjà trouvé MetierImpl (@Service), DaoImpl (@Repository),
        // et injecté DaoImpl dans MetierImpl grâce à @Autowired + @Qualifier("dao")
        IMetier metier = context.getBean(IMetier.class);

        System.out.println("Résultat : " + metier.calcul());

        ((AnnotationConfigApplicationContext) context).close();
    }
}
