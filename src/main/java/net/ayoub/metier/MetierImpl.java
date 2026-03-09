package net.ayoub.metier;

import net.ayoub.dao.IDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Implémentation de la couche Métier.
 *
 * COUPLAGE FAIBLE : la dépendance IDao est déclarée via l'INTERFACE,
 * pas via une classe concrète. Le mot-clé `new` n'est jamais utilisé ici.
 * C'est le rôle du framework (ou du code de présentation) d'injecter
 * la bonne implémentation.
 *
 * @Service est une spécialisation de @Component pour la couche Métier.
 * @Autowired demande à Spring d'injecter automatiquement la dépendance IDao.
 * @Qualifier("dao") précise quelle implémentation injecter (DaoImpl ou DaoImplV2).
 */
@Service
public class MetierImpl implements IMetier {

    // Couplage faible : on dépend de l'interface, PAS de l'implémentation
    private IDao dao;

    /**
     * Injection par CONSTRUCTEUR (meilleure pratique recommandée).
     * Spring détecte automatiquement ce constructeur même sans @Autowired.
     */
    @Autowired
    public MetierImpl(@Qualifier("dao") IDao dao) {
        this.dao = dao;
    }

    /**
     * Constructeur sans argument requis pour :
     * - Version 1 (instanciation statique avec setter)
     * - Version 2 (réflexion dynamique avec newInstance())
     */
    public MetierImpl() {
    }

    /**
     * Injection par SETTER (pratique fonctionnelle, moins recommandée).
     * Permet d'injecter la dépendance après la création de l'objet.
     */
    public void setDao(IDao dao) {
        this.dao = dao;
    }

    /**
     * Calcul métier : utilise les données du DAO pour produire un résultat.
     * Ce calcul est indépendant de la source de données (BD ou capteur).
     */
    @Override
    public double calcul() {
        double data = dao.getData();
        // Exemple de traitement métier : calcul d'une moyenne pondérée fictive
        double resultat = data * 2.0 / 3.0 + 10;
        return resultat;
    }
}
