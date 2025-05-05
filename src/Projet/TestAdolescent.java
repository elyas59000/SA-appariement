package src.Projet;

import java.time.LocalDate;

public class TestAdolescent {

    public static void main(String[] args) {
        //emile(guest)
        Adolescent emile = new Adolescent("Camard", "Emile", "M", LocalDate.of(2006, 1, 1), "France", false);
        //enzo(host)
        Adolescent enzo = new Adolescent("Dewame", "Enzo", "M", LocalDate.of(2006, 4, 1), "France", true);
        //elyas(guest)
        Adolescent elyas = new Adolescent("Rabhiu", "Elyas", "M", LocalDate.of(2005, 4, 1), "France", false);


        emile.ajouterValeur(Critere.GUEST_ANIMAL_ALLERGY, true);
        emile.ajouterValeur(Critere.GUEST_FOOD, "vegetarian");
        System.out.println("Émile est alergique ? " + emile.getCritere(Critere.GUEST_ANIMAL_ALLERGY));


        enzo.ajouterValeur(Critere.HOST_HAS_ANIMAL, true);
        enzo.ajouterValeur(Critere.HOST_FOOD, "vegetarian");
        System.out.println("Enzo a un animal ? " + enzo.getCritere(Critere.HOST_HAS_ANIMAL));
        

        elyas.ajouterValeur(Critere.GUEST_ANIMAL_ALLERGY, false);
        elyas.ajouterValeur(Critere.GUEST_FOOD, "");
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

    }
}
