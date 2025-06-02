package Projet;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Appariement {

    public static HashMap<Adolescent, Adolescent> apparier(HashSet<Adolescent> invites, HashSet<Adolescent> hotes) {
        HashMap<Adolescent, Adolescent> resultat = new HashMap<>();

        for (Adolescent invite : invites) {
            // On ne traite que les invités
            if (!invite.estHote()) {

                Adolescent meilleurHote = null;
                int meilleurScore = Integer.MAX_VALUE;

                for (Adolescent hote : hotes) {
                    // On ne considère que les hôtes
                    if (hote.estHote() && !invite.equals(hote) && invite.estCompatible(hote)) {
                        int score = invite.ScoreAffectation(hote);
                        if (score < meilleurScore) {
                            meilleurScore = score;
                            meilleurHote = hote;
                        }
                    }
                } 
                resultat.put(invite, meilleurHote);
            }
        }

        return resultat;
    }
}
