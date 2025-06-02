package Projet;

import java.util.HashMap;
import java.util.HashSet;

public class Appariement {

    
    public static HashMap<Adolescent, Adolescent> apparier(
            HashSet<Adolescent> invites,
            HashSet<Adolescent> hotes,
            HashMap<Adolescent, Adolescent> historique) {

        HashSet<Adolescent> hotesRestants = new HashSet<>(hotes);
        HashMap<Adolescent, Adolescent> resultat = new HashMap<>();

        for (Adolescent invite : invites) {
            Adolescent meilleurHote = null;
            int meilleurScore = Integer.MAX_VALUE;

            for (Adolescent hote : hotesRestants) {
                if (invite.equals(hote)) {
                    // Même personne, on ne traite pas
                } else if (!invite.estCompatible(hote)) {
                    // Non compatibles selon les critères classiques
                } else {
                    // Gestion de l'historique
                    Adolescent ancienHote = null;
                    if (historique != null) {
                        ancienHote = historique.get(invite);
                    }

                    String histInvite = invite.getCritere(Critere.HISTORY);
                    String histHote = hote.getCritere(Critere.HISTORY);

                    boolean refusAppariement = false;
                    boolean exigenceReappairage = false;

                    if (ancienHote != null && ancienHote.equals(hote)) {
                        if (histInvite.equals("other") || histHote.equals("other")) {
                            refusAppariement = true;
                        } else if (histInvite.equals("same") && histHote.equals("same")) {
                            exigenceReappairage = true;
                        }
                    }

                    if (refusAppariement) {
                        // Ils ne doivent pas être appariés
                    } else if (exigenceReappairage) {
                        // Obligation de réappairer
                        meilleurHote = hote;
                        break; // On arrête la recherche, car c’est obligatoire
                    } else {
                        int score = invite.ScoreAffectation(hote);
                        if (score < meilleurScore) {
                            meilleurScore = score;
                            meilleurHote = hote;
                        }
                    }
                }
            }

            if (meilleurHote != null) {
                resultat.put(invite, meilleurHote);
                hotesRestants.remove(meilleurHote);
            } else {
                resultat.put(invite, null);
            }
        }

        return resultat;
    }

    public static HashMap<Adolescent, Adolescent> apparier(HashSet<Adolescent> invites,HashSet<Adolescent> hotes){
        return apparier(invites, hotes,null);
    }



 
}
