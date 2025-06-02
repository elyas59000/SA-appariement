package Projet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;


public class File {
    
    public static HashSet<Adolescent> importListAdo(String name) throws IOException{
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
                Adolescent ado = new Adolescent(nom, prenom, genre, date_naissance, country);
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

    public static void export(String chemin, HashMap<Adolescent, Adolescent> appariements) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(chemin))) {
            bw.write("InvitePrenom;InviteNom;HotePrenom;HoteNom;Score");
            bw.newLine();

            for (Map.Entry<Adolescent, Adolescent> entry : appariements.entrySet()) {
                Adolescent invite = entry.getKey();
                Adolescent hote = entry.getValue();

                if (hote != null) {
                    int score = invite.ScoreAffectation(hote);
                    bw.write(invite.getPrenom() + ";" + invite.getNom() + ";" +
                             hote.getPrenom() + ";" + hote.getNom() + ";" + score);
                } else {
                    // Aucun hôte trouvé
                    bw.write(invite.getPrenom() + ";" + invite.getNom() + ";null;null;inf");
                }
                bw.newLine();
            }

        } catch (IOException e) {
            System.err.println("Erreur lors de l'export : " + e.getMessage());
        }
    }
}
