package src.Projet;

import java.time.LocalDate;
import java.util.HashMap;
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
        if (est_hote == true) {
            return "l'Adolescent est hote";
        } else {
            return "l'Adolescent est un Guest";
        }
    }
    

    public boolean estCompatible(Adolescent autre) { //retourne true si les deux adolescents sont compatibles
        if (this.CompaAnimal(autre) == false) {
            return false;
        }
        if (this.CompaAlimentaire(autre) == false) {
            return false;
        } 
        return true;
    }

    
 public Map<Critere, Object> getCriteres() { 
        return criteres;
    }

public Object getCritere(Critere critere) {
        if (criteres.containsKey(critere)) {
            return criteres.get(critere);
        } else {
            return null;
        }
    }

    public String getCritereTexte(Critere critere) {
        if (critere.getType() == 'T') {
            return (String) criteres.get(critere);
        }
        return null;
    }
    
    public Boolean getCritereBooleen(Critere critere) {
        if (critere.getType() == 'B') {
            return (Boolean) criteres.get(critere);
        }
        return null;
    }


    public boolean CompaAnimal(Adolescent autre) {
        // Si le guest (this) est allergique aux animaux et l’hôte (autre) a des animaux → pas compatible
        return !(this.getCritereBooleen(Critere.GUEST_ANIMAL_ALLERGY) &&
                 autre.getCritereBooleen(Critere.HOST_HAS_ANIMAL));
    }

    public boolean CompaAlimentaire(Adolescent autre) {
        // Compatibles si les régimes sont les mêmes
        String nourritureinvité = this.getCritereTexte(Critere.GUEST_FOOD);
        String nourriturehôte = autre.getCritereTexte(Critere.HOST_FOOD);
        if(nourritureinvité.equals("")){
            return true;
        }else{
            return nourritureinvité != null && nourritureinvité.equals(nourriturehôte);

        }

    }



    public void ajouterValeur(Critere critere, String valeur) {
        if (critere.getType() == 'T') {
            criteres.put(critere, valeur);
        }
    }
    
    public void ajouterValeur(Critere critere, boolean valeur) {
        if (critere.getType() == 'B') {
            criteres.put(critere, valeur);
        }
    }
}