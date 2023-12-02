package Codigo;

import DAO.EquipeDAO;

public class Equipe {
    private String nome;
    private int ID;

    // Construtores
    public Equipe(int ID,String nome) {
        this.ID = ID;
        this.nome = nome;
    }
    public Equipe(String nome) {
        this.nome = nome;
    }



    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    public int getID() {
        return ID;
    }
}
