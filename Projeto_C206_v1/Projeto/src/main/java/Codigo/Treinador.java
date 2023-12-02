package Codigo;

public class Treinador {
    private int ID;
    private String nome;
    private int EquipeID;


    // Construtores

    public Treinador(int equipeID) {
        EquipeID = equipeID;
    }
    public Treinador(int ID, int equipeID, String nome) {
        this.ID = ID;
        EquipeID = equipeID;
        this.nome = nome;
    }
    public Treinador(String nome) {
        this.nome = nome;
    }

    // Getters & Setters
    public int getID() {
        return ID;
    }
    public int getEquipeID() {
        return EquipeID;
    }
    public String getNome() {
        return nome;
    }
}