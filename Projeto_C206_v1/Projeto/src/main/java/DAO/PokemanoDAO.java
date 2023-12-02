package DAO;
import Codigo.Pokemano;

import java.sql.SQLException;
import java.util.ArrayList;
public class PokemanoDAO extends ConnectionDAO{
    //DAO - Data Access Object
    boolean sucesso = false; //Para saber se funcionou

    //INSERT
    public boolean insertPokemano(Pokemano pokemano) {
        connectToDB();
        String sql = "INSERT INTO Pokemano (nome,tipo,nivel) values(?,?,?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, pokemano.getNome());
            pst.setString(2, pokemano.getTipo());
            pst.setInt(3, pokemano.getNivel());
            pst.execute();
            sucesso = true;
        } catch (SQLException exc) {
            System.out.println("Erro PokemanoDAO 1: " + exc.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException exc) {
                System.out.println("Erro PokemanoDAO 2: " + exc.getMessage());
            }
        }
        return sucesso;
    }

    public boolean insertPokemanoNovo (String nome, String tipo, int nivel){
        connectToDB();
        String sql = "INSERT INTO Pokemano (nome,tipo,nivel) values(?,?,?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1,nome);
            pst.setString(2,tipo);
            pst.setInt(3,nivel);
            pst.execute();
            sucesso = true;
        } catch (SQLException exc) {
            System.out.println("Erro PokemanoDAO 3: " + exc.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException exc) {
                System.out.println("Erro PokemanoDAO 4: " + exc.getMessage());
            }
        }
        return sucesso;
    }

    //UPDATE
    public boolean updateNomePokemano(int idPokemano, String nomeNovo, String tipoNovo, int nivelNovo) {
        connectToDB();
        String sql = "UPDATE Pokemano SET nome=?, tipo=?, nivel=? where id = ?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, idPokemano);
            pst.setString(2, nomeNovo);
            pst.setString(3, tipoNovo);
            pst.setInt(4, nivelNovo);
            pst.execute();
            sucesso = true;
        } catch (SQLException ex) {
            System.out.println("Erro PokemanoDAO 5 = " + ex.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException exc) {
                System.out.println("Erro PokemanoDAO 6: " + exc.getMessage());
            }
        }
        return sucesso;
    }

    //DELETE
    public boolean deletePokemano(int pokemanoID) {
        connectToDB();
        String sql = "DELETE FROM Pokemano where pokemanoID=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, pokemanoID);
            pst.execute();
            sucesso = true;
        } catch (SQLException ex) {
            System.out.println("Erro PokemanoDAO 7: " + ex.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException exc) {
                System.out.println("Erro PokemanoDAO 8: " + exc.getMessage());
            }
        }
        return sucesso;
    }

    //SELECT
    public ArrayList<Pokemano> selectPokemano() {
        ArrayList<Pokemano> pokemanos = new ArrayList<>();
        connectToDB();
        String sql = "SELECT * FROM Pokemano";
        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);
            System.out.println("Lista de Pokemanos: \n");

            while (rs.next()) {
                Pokemano pokemanoAux = new Pokemano(rs.getInt("ID"),rs.getString("Nome"),rs.getString("Tipo"),rs.getInt("Nivel"));
                System.out.println("ID Pokemano: " + pokemanoAux.getID());
                System.out.println("Nome: " + pokemanoAux.getNome());
                System.out.println("Tipo: " + pokemanoAux.getTipo());
                System.out.println("Nivel: " + pokemanoAux.getNivel());
                System.out.println("--------------------------------");
                pokemanos.add(pokemanoAux);
            }
            sucesso = true;
        } catch (SQLException e) {
            System.out.println("Erro PokemanoDAO 8: " + e.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                st.close();
            } catch (SQLException e) {
                System.out.println("Erro PokemanoDAO 9: " + e.getMessage());
            }
        }
        return pokemanos;
    }
}