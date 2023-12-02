package Codigo;
public class Pokemano {

    protected String nome;
    protected String tipo;
    private int nivel;
    private int ID;


    // Construtores
    public Pokemano(int ID, String nome, String tipo, int nivel) {
        this.ID = ID;
        this.nome = nome;
        this.tipo = tipo;
        this.nivel = nivel;
    }
    public Pokemano(String nome, String tipo, int nivel) {
        this.nome = nome;
        this.tipo = tipo;
        this.nivel = nivel;
    }



    // Getters & Setters
    public String getNome() {
        return nome;
    }
    public String getTipo() {
        return tipo;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public int getNivel() {
        return nivel;
    }
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
    public int getID() {
        return ID;
    }

}