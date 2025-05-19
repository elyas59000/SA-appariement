package src.Projet;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
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
    private Map<Critere,Object> criteres;


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
    

    public boolean estCompatible(Adolescent autre) { //retourne true si les deux adolescents sont compatibles
        return this.CompaAnimal(autre) && this.CompaAlimentaire(autre);
    }

    
    public Map<Critere, Object> getDico() { 
        return criteres;   
    }

    public Object getValeur(Critere critere) {
        Object valeur = criteres.get(critere);
        if (valeur == null) return null;

        if (critere.getType() == 'T' && valeur instanceof String) {
            return (String) valeur;
        } else if (critere.getType() == 'B' && valeur instanceof Boolean) {
            return (Boolean) valeur;
        } else if (critere == Critere.HOBBIES && valeur instanceof List) {
            return valeur; // Retourne directement la liste des hobbies
        }
        return null;
    }

 
    
    public boolean CompaAnimal(Adolescent autre) {
        // Si le guest (this) est allergique aux animaux et l’hôte (autre) a des animaux → pas compatible
        Boolean allergie = (Boolean) this.getValeur(Critere.GUEST_ANIMAL_ALLERGY);
        Boolean animauxHote = (Boolean) autre.getValeur(Critere.HOST_HAS_ANIMAL);
    
        if (allergie != null && animauxHote != null) {
            if (allergie && animauxHote) {
                return false;
            }
        }
        return true;
    }
    

    public boolean CompaAlimentaire(Adolescent autre) {
        // Compatibles si les régimes sont les mêmes
        String nourritureinvité = (String) this.getValeur(Critere.GUEST_FOOD);
        String nourriturehôte = (String) autre.getValeur(Critere.HOST_FOOD);
        if(nourritureinvité.equals("")){
            return true;
        }else{
            return nourritureinvité != null && nourritureinvité.equals(nourriturehôte);

        }

    }


    //permet d'ajouter une valeur a une critére 
   public void ajouterValeur(Critere critere, Object valeur) {
        char type = critere.getType();

        if (critere == Critere.HOBBIES && valeur instanceof String) {
            if (!criteres.containsKey(Critere.HOBBIES)) {
                criteres.put(Critere.HOBBIES, new ArrayList<String>());
            }
            List<String> hobbies = (List<String>) criteres.get(Critere.HOBBIES);
            hobbies.add((String) valeur);
        } else if (critere.getType() == 'T' && valeur instanceof String) {
            criteres.put(critere, valeur);
        } else if (critere.getType() == 'B' && valeur instanceof Boolean) {
            criteres.put(critere, valeur);
        }
   }



    public int ScoreAffectation(Adolescent autre){
        int score = 10;
        //test pour préférence de genre/genre
        Object pairGender = getValeur(Critere.PAIR_GENDER);
        Object pairGenderParam = autre.getValeur(Critere.PAIR_GENDER);
        if (pairGender != null && pairGender.equals(autre.genre) && pairGenderParam != null && pairGenderParam.equals(this.genre)) {
            score = score - 2;
        }
        //comparaison des date de naissance
        long monthBetween = ChronoUnit.MONTHS.between(this.date_naissance, autre.date_naissance);
        if(monthBetween<= 18){
            score = score - 2;
        }
        //comparaison des hobbies
         List<String> hobbies = (List<String>) this.getValeur(Critere.HOBBIES);
         List<String> hobbiesAutre = (List<String>) autre.getValeur(Critere.HOBBIES);

        if (hobbies != null && hobbiesAutre != null) {
            List<String> commun = new ArrayList<>(hobbies);
            commun.retainAll(hobbiesAutre);
            score -= commun.size();
        }    
        return score;    
    }

    


}



