package src.Projet;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public abstract class Adolescent {
    private String nom;
    private String prenom;
    private String genre;
    private LocalDate date_naissance;
    private String pays_origine;
    private boolean est_hote; // true si l'adolescent est hôte, false sinon
    protected Map<Critere,String> criteres;


    public Adolescent(String nom, String prenom, String genre, LocalDate date_naissance, String pays_origine, boolean est_hote) {
        this.nom = nom;
        this.prenom = prenom;
        this.genre = genre;
        this.date_naissance = date_naissance;
        this.pays_origine = pays_origine;
        this.criteres = new HashMap<>();
        this.est_hote = est_hote;
    }

    
    public String gestOuHote(){
        if (est_hote == true) {
            return "l'adolescent est hote";
        } else {
            return "l' est un Guest";
        }
    }
    

    public boolean estCompatible(Adolescent autre) {
        if (this.CompaAnimal(autre) == false) {
            return false;
        }
        if (this.CompaAlimentaire(autre) == false) {
            return false;
        } 
        return true;
    }




    
    public String getCritere(Critere critere) {
        return criteres.getOrDefault(critere, "NON");
    }

    public boolean CompaAnimal(Adolescent autre) {
        if(autre.getCritere(Critere.GUEST_ANIMAL_ALLERGY) == "OUI" && autre.getCritere(Critere.HOST_HAS_ANIMAL) == "OUI") {
            return false;
        }
        return true;
    }
    public boolean CompaAlimentaire(Adolescent autre) {
        if(autre.getCritere(Critere.GUEST_FOOD) == "OUI" && autre.getCritere(Critere.HOST_FOOD) == "OUI") {
            return false;
        }
        return true;
    }    




    

   
}