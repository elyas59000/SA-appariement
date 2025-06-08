package Projet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Pretraitement {

    public static void nettoyerAdolescents(Set<Adolescent> ados, int limiteMaxIncoherences) {
        List<Adolescent> aSupprimer = new ArrayList<>();

        for (Adolescent ado : ados) {
            int incoherences = 0;

            // Vérifie l'incohérence allergie/animal
            if (ado.getCritere(Critere.GUEST_ANIMAL_ALLERGY).equals("yes") && 
                ado.getCritere(Critere.HOST_HAS_ANIMAL).equals("yes")) {
                incoherences++;
            }

            // Vérifie tous les critères invalides
            for (Map.Entry<Critere, String> entry : ado.getDico().entrySet()) {
                try {
                    ado.validerCritere(entry.getKey(), entry.getValue());
                } catch (CritereInvalideException e) {
                    incoherences++;
                }
            }

            if (incoherences > limiteMaxIncoherences) {
                aSupprimer.add(ado);
            }
        }

        for (Adolescent ado : aSupprimer) {
            ados.remove(ado);
            System.out.println("Adolescent supprimé (incohérences) : " + ado.getPrenom() + " " + ado.getNom());
        }
    }
}
