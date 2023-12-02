package Codigo;
public class Pokedex {
    private int ID;
    private int TreinadorID;



    // Constructores
    public Pokedex(int ID) {
        this.ID = ID;
    }
    public Pokedex(int ID, int treinadorID) {
        this.ID = ID;
        TreinadorID = treinadorID;
    }


    // Getters & Setters
    public int getID() {
        return ID;
    }
    public int getTreinadorID() {
        return TreinadorID;
    }
}