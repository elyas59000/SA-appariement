package Projet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;


public class File {
    
    public static HashSet<Adolescent> importListAdo(String name, boolean estHote) throws IOException{
        try (BufferedReader br = new BufferedReader(new FileReader(name))) {
            String entete = br.readLine();
            HashSet<Adolescent> ados = new HashSet<>();
            String ligne;
            while((ligne = br.readLine())  != null){
                String partie[] = ligne.split(";",-1);
                String nom = partie[0];
                String prenom = partie[1];
                String country = partie[2];
                LocalDate date_naissance = LocalDate.parse(partie[3].trim());
                String genre = partie[9];
                Adolescent ado = new Adolescent(nom, prenom, genre, date_naissance, country, estHote);
                String guest_animal_allergy = partie[4];
                String host_has_animal = partie[5];
                String guest_food_constraint = partie[6];
                String host_food = partie[7];
                String hobbies = partie[8];
                String pair_gender = partie[10];
                String history = partie[11];
                ado.addCritere(Critere.GUEST_ANIMAL_ALLERGY, guest_animal_allergy);
                ado.addCritere(Critere.HOST_HAS_ANIMAL, host_has_animal);
                ado.addCritere(Critere.GUEST_FOOD, guest_food_constraint);
                ado.addCritere(Critere.HOST_FOOD, host_food);
                ado.addCritere(Critere.HOBBIES, hobbies);
                ado.addCritere(Critere.PAIR_GENDER, pair_gender);
                ado.addCritere(Critere.HISTORY, history);
                ados.add(ado);
            }

            return ados;
        }

    }

    public static void export(String name, HashSet<Adolescent> listeInvité, HashSet<Adolescent> listeHote)  {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(name + ".csv"))){
            bw.write("InvitePrenon;InviteNom;HotePrenom;HoteNom;Score");
            bw.newLine();
            for(Adolescent a: listeInvité){
                for(Adolescent b : listeHote){
                    if(!a.equals(b) && !a.estHote() && b.estHote() && a.estCompatible(b)){
                        bw.write(a.getPrenom() + ";" + a.getNom() + ";" + b.getPrenom() + ";" + b.getNom()  + ";" + a.ScoreAffectation(b));
                        bw.newLine();
                    }
                }
            }
             
        }catch(IOException e){
            System.out.println("erreur : " + e.getMessage());
        }
    }
}
