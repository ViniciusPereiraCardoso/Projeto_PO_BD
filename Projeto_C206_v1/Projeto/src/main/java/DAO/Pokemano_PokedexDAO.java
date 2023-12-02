package DAO;
import Codigo.Pokemano_Pokedex;

import java.sql.SQLException;
import java.util.ArrayList;
public class Pokemano_PokedexDAO extends ConnectionDAO{
    //DAO - Data Access Object
    boolean sucesso = false; //Para saber se funcionou

    //INSERT
    public boolean insertPokedex_Pokemano (Pokemano_Pokedex pokedexPokemano) {
        connectToDB();

        String sql = "INSERT INTO Pokedex_Pokemano (pokedexID,pokemanoID) values(?,?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, pokedexPokemano.getPokedexID());
            pst.setInt(2, pokedexPokemano.getPokemanoID());
            pst.execute();
            sucesso = true;
        } catch (SQLException exc) {
            System.out.println("Erro PokedexPokemano 1: " + exc.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException exc) {
                System.out.println("Erro PokedexPokemano 2: " + exc.getMessage());
            }
        }
        return sucesso;
    }

    public boolean insertPokemanoPokebola (int ID) {
        connectToDB();

        String sql = "INSERT INTO Pokedex_Pokemano (pokemanoPokedex) values(?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, ID);
            pst.execute();
            sucesso = true;
        } catch (SQLException exc) {
            System.out.println("Erro PokedexPokemano 3: " + exc.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException exc) {
                System.out.println("Erro PokedexPokemano 4: " + exc.getMessage());
            }
        }
        return sucesso;
    }


    // DELETE
    public boolean deletePokemano(int pokemanoID) {
        connectToDB();
        String sql = "DELETE FROM Pokedex_Pokemano where pokemanoID = ?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, pokemanoID);
            pst.execute();
            sucesso = true;
        } catch (SQLException ex) {
            System.out.println("Erro PokedexPokemano 5: " + ex.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException exc) {
                System.out.println("Erro PokedexPokemano 6: " + exc.getMessage());
            }
        }
        return sucesso;
    }


    //SELECT
    public ArrayList<Pokemano_Pokedex> selectPokedex_Pokemano() {
        ArrayList<Pokemano_Pokedex> PokedexPokemano = new ArrayList<>();
        connectToDB();
        String sql = "SELECT * FROM Pokedex_Pokemano";
        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);
            System.out.println("Lista de pokemanos na pokedex: ");
            while (rs.next()) {
                Pokemano_Pokedex pokemanosPokedexAUX = new Pokemano_Pokedex(rs.getInt("pokedexID"),rs.getInt("pokemanoID"));
                System.out.println("pokedexID = " + pokemanosPokedexAUX.getPokedexID());
                System.out.println("pokemanoID = " + pokemanosPokedexAUX.getPokemanoID());
                System.out.println("--------------------------------");
                PokedexPokemano.add(pokemanosPokedexAUX);
            }
            sucesso = true;
        } catch (SQLException e) {
            System.out.println("Erro PokedexPokemano 7: " + e.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                st.close();
            } catch (SQLException e) {
                System.out.println("Erro PokedexPokemano 8: " + e.getMessage());
            }
        }
        return PokedexPokemano;
    }

    public int selectIDPokemanosPokebolas(int PokedexID) {
        connectToDB();
        // Verifica o ID referente a equipe informada
        String sql = "SELECT PokemanoID FROM Pokedex_Pokemano WHERE PokedexID=?";
        int id = 0;
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, PokedexID);
            rs = pst.executeQuery();
            if (rs.next()) {
                id = rs.getInt("PokemanoID");
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
        return id; // Retorna o id dos pokemanos
    }
}