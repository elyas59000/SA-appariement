package Projet;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

public class Historique{

    public static void sauvegarderHistorique(String name,Map<Adolescent,Adolescent> historique){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(name))) {
            oos.writeObject(historique);
        } catch(Exception e) {
            System.out.println("erreur : " + e.getMessage());
        }

    }

    public static HashMap<Adolescent,Adolescent> chargerHistorique(String name){
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(name))){
             return (HashMap<Adolescent, Adolescent>) ois.readObject();
        } catch(Exception e){
            System.out.println("erreur : " + e.getMessage());
            return null;
        }
    }

    
}
