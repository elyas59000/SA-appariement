package Projet;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class TestAppariement {
    public static void main(String[] args){
        try{
            HashSet<Adolescent> listeInvite = File.importListAdo("src/Projet/data.csv",false);
            HashSet<Adolescent> listeHote = File.importListAdo("src/Projet/data.csv",true);

            for(Adolescent a : listeInvite){
                System.out.println(a.toString());
            }
            File.export("export", listeInvite, listeHote);
            HashMap<Adolescent,Adolescent> appariement =  Appariement.apparier(listeInvite, listeHote);
            System.out.println("\n===== APPARIEMENTS =====\n");
            for(Map.Entry<Adolescent, Adolescent> entry : appariement.entrySet()){
                Adolescent invite = entry.getKey();
                Adolescent hote = entry.getValue();
                if (hote == null) {
                    System.out.println(" Aucun hôte trouvé pour : " + invite.getPrenom() + " " + invite.getNom());
                }else{
                    System.out.println(invite.getPrenom() + " " + invite.getNom() + "  :  " + hote.getPrenom() + " " + hote.getNom());    
                }
            }
        }catch(FileNotFoundException e){
            System.err.println("erreur : " + e.getMessage());
        }catch(IOException e){
            System.err.println("erreur :" + e.getMessage());
        }
        System.out.println();
       
    }
}