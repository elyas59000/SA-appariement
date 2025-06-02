package Projet;

import java.time.LocalDate;

public class TestAdolescent {

    public static void main(String[] args) {
        //emile(guest)
        Adolescent emile = new Adolescent("Camard", "Emile", "M", LocalDate.of(2006, 1, 1), "France", false);
        //enzo(host)
        Adolescent enzo = new Adolescent("Dewame", "Enzo", "M", LocalDate.of(2006, 4, 1), "France", true);
        //elyas(guest)
        Adolescent elyas = new Adolescent("Rabhiu", "Elyas", "M", LocalDate.of(2005, 4, 1), "France", false);


        emile.addCritere(Critere.GUEST_ANIMAL_ALLERGY, "yes");
        emile.addCritere(Critere.GUEST_FOOD, "vegetarian");
        System.out.println("Émile est alergique ? " + emile.getCritere(Critere.GUEST_ANIMAL_ALLERGY));


        enzo.addCritere(Critere.HOST_HAS_ANIMAL, "yes");
        enzo.addCritere(Critere.HOST_FOOD, "vegetarian");
        System.out.println("Enzo a un animal ? " + enzo.getCritere(Critere.HOST_HAS_ANIMAL));
        

        elyas.addCritere(Critere.GUEST_ANIMAL_ALLERGY, "no");
        elyas.addCritere(Critere.GUEST_FOOD, "");
        System.out.println("Élyas est alergique ? " + elyas.getCritere(Critere.GUEST_ANIMAL_ALLERGY));
        System.out.println("quelle regime accepte enzo: " + enzo.getCritere(Critere.HOST_FOOD));
        System.out.println();


        System.out.println("compatibilité animal entre emile et enzo  : " + emile.CompaAnimal(enzo));
        System.out.println("compatibilité nourriture entre emile et enzo: " + emile.CompaAlimentaire(enzo));

        boolean compatibles = emile.estCompatible(enzo);
        System.out.println("Émile est compatible avec Enzo ? " + compatibles);
        System.out.println("Critères d'Enzo : " + enzo.getDico());
        System.out.println("Critères d'Émile : " + emile.getDico());
        System.out.println("Critères d'Elyas : " + elyas.getDico());
        System.out.println();



        System.out.println("comptabilité animal entre elyas et enzo : " + elyas.CompaAnimal(enzo));
        System.out.println("comptabilité alimentaire entre elyas et enzo: "+ elyas.CompaAlimentaire(enzo));
        System.out.println("elyas et compatible avec enzo ? "+ elyas.estCompatible(enzo) );
        System.out.println();


        elyas.addCritere(Critere.PAIR_GENDER, "M");  // Elyas veut être apparié avec un garçon
        enzo.addCritere(Critere.PAIR_GENDER, "M");   // Enzo aussi
        emile.addCritere(Critere.PAIR_GENDER, "F");  // Emile veut une fille

        // Ajout des hobbies
        emile.addCritere(Critere.HOBBIES, "football");
        emile.addCritere(Critere.HOBBIES, "jeux vidéo");
        
        enzo.addCritere(Critere.HOBBIES, "football");
        enzo.addCritere(Critere.HOBBIES, "cuisine");

        elyas.addCritere(Critere.HOBBIES, "lecture");
        elyas.addCritere(Critere.HOBBIES, "cuisine");

        // Affichage des hobbies
        System.out.println("Hobbies d'Émile : " + emile.getCritere(Critere.HOBBIES));
        System.out.println("Hobbies d'Enzo : " + enzo.getCritere(Critere.HOBBIES));
        System.out.println("Hobbies d'Elyas : " + elyas.getCritere(Critere.HOBBIES));
        System.out.println();

        // Test des scores d'affectation
        System.out.println("Score d'affectation entre Émile et Enzo : " + emile.ScoreAffectation(enzo));
        System.out.println("Score d'affectation entre Élyas et Enzo : " + elyas.ScoreAffectation(enzo));

        // Test de la compatibilité spécifique au pays
        System.out.println("Compatibilité spécifique au pays entre Émile et Enzo : " + emile.compatibiliteSpecifiquePays(enzo));
        System.out.println("Compatibilité spécifique au pays entre Élyas et Enzo : " + elyas.compatibiliteSpecifiquePays(enzo));
        System.out.println("Compatibilité spécifique au pays entre Émile et Élyas : " + emile.compatibiliteSpecifiquePays(elyas));

    }
}
