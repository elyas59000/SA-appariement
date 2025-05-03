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
    private Map<Critere,String> criteres;


    public Adolescent(String nom, String prenom, String genre, LocalDate date_naissance, String pays_origine) {
        this.nom = nom;
        this.prenom = prenom;
        this.genre = genre;
        this.date_naissance = date_naissance;
        this.pays_origine = pays_origine;
        this.criteres = new HashMap<>();
    }

    



    

   
}