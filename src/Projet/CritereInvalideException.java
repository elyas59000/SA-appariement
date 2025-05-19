package src.Projet;

public class CritereInvalideException extends Exception {
    public CritereInvalideException(Critere critere, String valeur) {
        super("Critère invalide : " + critere + " avec valeur '" + valeur + "'");
    }
}