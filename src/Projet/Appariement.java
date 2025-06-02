package Projet;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Appariement {

    public static HashMap<Adolescent, Adolescent> apparier(
            HashSet<Adolescent> invites,
            HashSet<Adolescent> hotes) {

        // Copie de la liste des hôtes pour pouvoir retirer un hôte lorsqu'il est assigné
        HashSet<Adolescent> hotesRestants = new HashSet<>(hotes);
        HashMap<Adolescent, Adolescent> resultat = new HashMap<>();

        for (Adolescent invite : invites) {
            // On ne traite que ceux marqués invités
            if (!invite.estHote()) {
                Adolescent meilleurHote = null;
                int meilleurScore = Integer.MAX_VALUE;

                // Parcours des hôtes encore disponibles
                for (Adolescent hote : hotesRestants) {
                    if (invite.estCompatible(hote)) {
                        int score = invite.ScoreAffectation(hote);
                        if (score < meilleurScore) {
                            meilleurScore = score;
                            meilleurHote = hote;
                        }
                    }
                }

                // S’il n’existe aucun hôte compatible, on met null
                if (meilleurHote == null) {
                    resultat.put(invite, null);
                } else {
                    resultat.put(invite, meilleurHote);
                    // Retire l’hôte pour qu’il ne soit pas réutilisé
                    hotesRestants.remove(meilleurHote);
                }
            }
        }
        return resultat;
    }

 
}
