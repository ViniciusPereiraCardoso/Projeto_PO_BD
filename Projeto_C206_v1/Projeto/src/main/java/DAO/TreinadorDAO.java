package DAO;
import Codigo.Treinador;

import java.sql.SQLException;
import java.util.ArrayList;
public class TreinadorDAO extends ConnectionDAO{
    //DAO - Data Access Object
    boolean sucesso = false; //Para saber se funcionou

    //INSERT
    public boolean insertTreinador(Treinador treinador) {
        connectToDB();
        String sql = "INSERT INTO Treinador(nome) values(?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, treinador.getNome());
            pst.execute();
            sucesso = true;
        } catch (SQLException exc) {
            System.out.println("Erro TreinadorDAO 1: " + exc.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException exc) {
                System.out.println("Erro TreinadorDAO 2: " + exc.getMessage());
            }
        }
       return sucesso;
    }

    public boolean insertTreinadorNovo (String nome){
        connectToDB();
        String sql = "INSERT INTO Treinador (nome,equipeID) values(?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1,nome);
            pst.execute();
            sucesso = true;
        } catch (SQLException exc) {
            System.out.println("Erro TreinadorDAO 3: " + exc.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException exc) {
                System.out.println("Erro TreinadorDAO 4: " + exc.getMessage());
            }
        }
        return sucesso;
    }
    public boolean insertTreinadorEquipe (int ID){
        connectToDB();
        String sql = "INSERT INTO Treinador (EquipeID) values(?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1,ID);

            pst.execute();
            sucesso = true;
        } catch (SQLException exc) {
            System.out.println("Erro TreinadorDAO 5: " + exc.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException exc) {
                System.out.println("Erro TreinadorDAO 6: " + exc.getMessage());
            }
        }
        return sucesso;
    }


    //UPDATE
    public boolean updateTreinador(int id, String nomeNovo) {
        connectToDB();
        String sql = "UPDATE Treinador SET nome=?  where id = ?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            pst.setString(2, nomeNovo);
            pst.execute();
            sucesso = true;
        } catch (SQLException ex) {
            System.out.println("Erro TreinadorDAO 3 = " + ex.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException exc) {
                System.out.println("Erro TreinadorDAO 4: " + exc.getMessage());
            }
        }
        return sucesso;
    }


    //DELETE
    public boolean deleteTreinador(String nome) {
        connectToDB();
        String sql = "DELETE FROM Treinador where nome=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, nome);
            pst.execute();
            sucesso = true;
        } catch (SQLException ex) {
            System.out.println("Erro TreinadorDAO 5 = " + ex.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException exc) {
                System.out.println("Erro TreinadorDAO 6: " + exc.getMessage());
            }
        }
        return sucesso;
    }


    //SELECT
    public ArrayList<Treinador> selectTreinador() {
        ArrayList<Treinador> treinador = new ArrayList<>();
        connectToDB();
        String sql = "SELECT * FROM Treinador";
        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);
            System.out.println("Lista de Treinadores: ");
            while (rs.next()) {
                Treinador treinadorAux = new Treinador(rs.getInt("ID"),rs.getInt("EquipeID"), rs.getString("Nome"));
                System.out.println("ID = " + treinadorAux.getID());
                System.out.println("ID da Equipe = " + treinadorAux.getEquipeID());
                System.out.println("Nome = " + treinadorAux.getNome());
                System.out.println("--------------------------------");
                treinador.add(treinadorAux);
            }
            sucesso = true;
        } catch (SQLException e) {
            System.out.println("Erro TreinadorDAO 7: " + e.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                st.close();
            } catch (SQLException e) {
                System.out.println("Erro TreinadorDAO 8: " + e.getMessage());
            }
        }
        return treinador;
    }

    public Treinador selectTreinadorSelecionado(String nome) {
        connectToDB();
        // Faz a autenticação do login
        String sql = "SELECT * FROM Treinador WHERE nome=?";
        Treinador treinador = null; // Começa como null

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, nome);
            rs = pst.executeQuery();
            if(rs != null && rs.next()){ // Se houver um treinador correspondente ao nome
                treinador = new Treinador (rs.getInt("ID"),rs.getInt("EquipeID"),rs.getString("Nome"));
            }
            sucesso = true;
        } catch (SQLException e) {
            System.out.println("Erro TreinadorDAO 9: " + e.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Erro TreinadorDAO 10: " + e.getMessage());
            }
        }
        return treinador;
    }
    public int selectTreinadorID(String nome) {
        connectToDB();
        // Verifica o ID referente ao treinador informado
        String sql = "SELECT ID FROM Treinador WHERE nome=?";
        int id = 0;
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, nome);
            rs = pst.executeQuery();
            if(rs.next()){
                id = rs.getInt("ID");
            }
        } catch (SQLException e) {
            System.out.println("Erro TreinadorDAO 11: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Erro TreinadorDAO 12: " + e.getMessage());
            }
        }
        return id; // Retorna o id do treinador
    }

    public int selectEquipeID(String nome) {
        connectToDB();
        // Verifica o ID referente a equipe informada
        String sql = "SELECT ID FROM Equipe WHERE nome=?";
        int id = 0;
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, nome);
            rs = pst.executeQuery();
            if (rs.next()) {
                id = rs.getInt("ID");
            }
        } catch (SQLException e) {
            System.out.println("Erro TreinadorDAO 13: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Erro TreinadorDAO 14: " + e.getMessage());
            }
        }
        return id; // Retorna o id da equipe
    }


}


