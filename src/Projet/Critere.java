package src.Projet;

public enum Critere {
    GUEST_ANIMAL_ALLERGY('B'),
    HOST_HAS_ANIMAL('B'),
    GUEST_FOOD('T'),
    HOST_FOOD('T'),
    HOBBIES('T'),
    GENDER('T'),
    PAIR_GENDER('T'),
    HISTORY('T');

    private final char type;

    Critere(char type){
        this.type = type;
    }

    public char getType(){
        return this.type;
    }
    
    public String getCritere(){
        return this.name();
    }

    
}
