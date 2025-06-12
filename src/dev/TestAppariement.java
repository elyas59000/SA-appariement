package dev;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class TestAppariement {
    public static void main(String[] args){
        try{
            HashSet<Adolescent> listeInvite = FileAdo.importListAdo("res/invites.csv");
            HashSet<Adolescent> listeHote = FileAdo.importListAdo("res/hotes.csv");

            for(Adolescent a : listeInvite){
                System.out.println(a.toString());
            }
            HashMap<Adolescent,Adolescent> appariement =  Appariement.apparier(listeInvite, listeHote);
            FileAdo.export("export", appariement);
            System.out.println("\n===== APPARIEMENTS =====\n");
            for(Map.Entry<Adolescent, Adolescent> entry : appariement.entrySet()){
                Adolescent invite = entry.getKey();
                Adolescent hote = entry.getValue();
                if (hote == null) {
                    System.out.println(" Aucun hôte trouvé pour : " + invite.getNom() + " " + invite.getPrenom());
                }else{
                    System.out.println(invite.getNom() + " " + invite.getPrenom() + "  :  " + hote.getNom() + " " + hote.getPrenom()+ " | Avec un score de :" + invite.ScoreAffectation(hote));    
                }
            }

            Historique.sauvegarderHistorique("historique",appariement);
        }catch(FileNotFoundException e){
            System.err.println("erreur : " + e.getMessage());
        }catch(IOException e){
            System.err.println("erreur :" + e.getMessage());
        }
        System.out.println();
       
    }
}