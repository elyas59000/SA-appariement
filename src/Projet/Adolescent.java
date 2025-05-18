package src.Projet;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Adolescent {
    private String nom;
    private String prenom;
    private String genre;
    private LocalDate date_naissance;
    private String pays_origine;
    private boolean est_hote; // true si ado est hôte, false sinon
    private Map<Critere,String> criteres;


    public Adolescent(String nom, String prenom, String genre, LocalDate date_naissance, String pays_origine, boolean est_hote) {
        this.nom = nom;
        this.prenom = prenom;
        this.genre = genre;
        this.date_naissance = date_naissance;
        this.pays_origine = pays_origine;
        this.criteres = new HashMap<>();
        this.est_hote = est_hote;
    }

    
    public String gestOuHote(){ // permet de savoir si l'adolescent est hôte ou guest
        if (this.est_hote) {
            return "l'Adolescent est hote";
        } else {
            return "l'Adolescent est un Guest";
        }
    }
       
    public Map<Critere, String> getDico() { 
        return criteres;   
    }

    public String getCritere(Critere critere) {
        return criteres.getOrDefault(critere, "");
    }

       //permet d'ajouter une valeur a une critére 
    public void addCritere(Critere critere, String valeur) {
        try {
            validerCritere(critere, valeur);
            criteres.put(critere, valeur.trim().toLowerCase());
        } catch (CritereInvalideException e) {
            System.out.println("Critère rejeté : " + e.getMessage());
        }
    }

   



    public boolean estCompatible(Adolescent autre) { //retourne true si les deux adolescents sont compatibles
        return this.CompaAnimal(autre) && this.CompaAlimentaire(autre) && compatibiliteSpecifiquePays(autre);
    }


        
    public boolean CompaAnimal(Adolescent autre) {
        boolean inviteAllergique = this.getCritere(Critere.GUEST_ANIMAL_ALLERGY).equals("yes");
        boolean hoteAUnAnimal = autre.getCritere(Critere.HOST_HAS_ANIMAL).equals("yes");
        return !(inviteAllergique && hoteAUnAnimal);
    }
    

    public boolean CompaAlimentaire(Adolescent autre) {
        String foodInvite = this.getCritere(Critere.GUEST_FOOD);
        String foodHote = autre.getCritere(Critere.HOST_FOOD);

        if (foodInvite.isEmpty()) return true;

        // Le host doit contenir toutes les contraintes du guest
        List<String> contraintesInvite = Arrays.asList(foodInvite.split(","));
        List<String> contraintesHote = Arrays.asList(foodHote.split(","));

        for (String contrainte : contraintesInvite) {
            if (!contraintesHote.contains(contrainte)) return false;
        }

        return true;
    }

    private boolean compatibiliteSpecifiquePays(Adolescent autre) {
        if (this.pays_origine.equals("france") || autre.pays_origine.equals("france")) {
            List<String> hobbies1 = Arrays.asList(this.getCritere(Critere.HOBBIES).split(","));
            List<String> hobbies2 = Arrays.asList(autre.getCritere(Critere.HOBBIES).split(","));

            for (String h : hobbies1) {
                if (hobbies2.contains(h)) {
                    return true;
                }
            }
            return false; // Aucun hobby en commun → incompatible
        }
        return true; // Autres pays → on ne change rien
    }


    public int ScoreAffectation(Adolescent autre) {
        int score = 10;

        // Préférences de genre
        String pref1 = this.getCritere(Critere.PAIR_GENDER);
        String pref2 = autre.getCritere(Critere.PAIR_GENDER);

        if ((pref1.isEmpty() || pref1.equals(autre.genre)) && (pref2.isEmpty() || pref2.equals(this.genre))) {
            score -= 2;
        }

        // Âge
        long mois = Math.abs(ChronoUnit.MONTHS.between(this.date_naissance, autre.date_naissance));
        if (mois <= 18) score -= 2;

        // Hobbies
        List<String> hobbies1 = Arrays.asList(getCritere(Critere.HOBBIES).split(","));
        List<String> hobbies2 = Arrays.asList(autre.getCritere(Critere.HOBBIES).split(","));
        int commun = 0;
        for (String hob : hobbies1) {
            if (hobbies2.contains(hob)) commun++;
        }
        score -= commun;

        return score;
    }

    public void validerCritere(Critere critere, String valeur) throws CritereInvalideException {
        char type = critere.getType();

        if (type == 'B') {
            if (!valeur.equals("yes") && !valeur.equals("no")) {
                throw new CritereInvalideException(critere, valeur);
            }
        }
        else if (type == 'T') {
            // permet de verifier si la valeur est vide ou contient que des espaces
            if (valeur.trim().isEmpty()) {
                throw new CritereInvalideException(critere, valeur);
            }
        }
    }

    

}



