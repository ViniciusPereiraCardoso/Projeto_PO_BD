package DAO;
import Codigo.Equipe;

import java.sql.SQLException;
import java.util.ArrayList;
public class EquipeDAO extends ConnectionDAO{
    //DAO - Data Access Object7
    boolean sucesso = false; //Para saber se funcionou

    //INSERT
    public boolean InserirEquipe (Equipe equipe){
        connectToDB();
        String sql = "INSERT INTO Equipe (nome) values(?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1,equipe.getNome());
            pst.execute();
            sucesso = true;
        } catch (SQLException exc) {
            System.out.println("ErroEquipeDAO 1: " + exc.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException exc) {
                System.out.println("ErroEquipeDAO 2: " + exc.getMessage());
            }
        }
        return sucesso;
    }

    public boolean InserirEquipeNova (String nome){
        connectToDB();
        String sql = "INSERT INTO Equipe (nome) values(?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1,nome);
            pst.execute();
            sucesso = true;
        } catch (SQLException exc) {
            System.out.println("ErroEquipeDAO 1: " + exc.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException exc) {
                System.out.println("ErroEquipeDAO 2: " + exc.getMessage());
            }
        }
        return sucesso;
    }


    //UPDATE
    public boolean updateEquipe(int id, String nomeNovo) {
        connectToDB();
        String sql = "UPDATE Equipe SET nome=?  where id=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1,id);
            pst.setString(2,nomeNovo);
            pst.execute();
            sucesso = true;
        } catch (SQLException ex) {
            System.out.println("ErroEquipeDAO 3 = " + ex.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException exc) {
                System.out.println("ErroEquipeDAO 4: " + exc.getMessage());
            }
        }
        return sucesso;
    }

    //DELETE
    public boolean deleteEquipe (String nome) {
        connectToDB();
        String sql = "DELETE FROM Equipe where nome=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, nome);
            pst.execute();
            sucesso = true;
        } catch (SQLException ex) {
            System.out.println("ErroEquipeDAO 5 = " + ex.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException exc) {
                System.out.println("ErroEquipeDAO 6: " + exc.getMessage());
            }
        }
        return sucesso;
    }

    //SELECT
        public ArrayList<Equipe> selecEquipe() {
        ArrayList<Equipe> equipes = new ArrayList<>();
        connectToDB();
        String sql = "SELECT * FROM Equipe";
        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);
            System.out.println("-> Lista de Equipes: ");
            System.out.println("--------------------------------");
            while (rs.next())
            {
                Equipe equipeAux = new Equipe(rs.getInt("ID"), rs.getString("Nome"));
                System.out.println("ID Equipe: " + equipeAux.getID() + " | Nome Equipe: " + equipeAux.getNome());
                System.out.println("--------------------------------");
                equipes.add(equipeAux);
            }
            sucesso = true;
        } catch (SQLException e) {
            System.out.println("ErroEquipeDAO 7: " + e.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                st.close();
            } catch (SQLException e) {
                System.out.println("ErroEquipeDAO 8: " + e.getMessage());
            }
        }
        return equipes;
    }

    public Equipe selectEquipeSelecionada(String nomeEquipeSelecionada) {
        connectToDB();
        // Faz a autenticação do login
        String sql = "SELECT * FROM Equipe WHERE nome=?";
        Equipe equipe = null; // Começa como null

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, nomeEquipeSelecionada);
            rs = pst.executeQuery();
            if (rs != null && rs.next()) { // Se houver uma equipe correspondente ao nome informado
                equipe = new Equipe(rs.getString("Nome"));
            }
            sucesso = true;
        } catch (SQLException e) {
            System.out.println("ErroEquipeDAO 9: " + e.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("ErroEquipeDAO 10: " + e.getMessage());
            }
        }
        return equipe;
    }


}


