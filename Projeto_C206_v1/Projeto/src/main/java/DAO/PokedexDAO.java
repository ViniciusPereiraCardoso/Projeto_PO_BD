package DAO;
import java.sql.SQLException;
import Codigo.Pokedex;
import Codigo.Treinador;

public class PokedexDAO extends ConnectionDAO{
    //DAO - Data Access Object
    boolean sucesso = false; //Para saber se funcionou


    //INSERT
    public boolean insertPokedexTreinador (Treinador treinador) {
        connectToDB();
        String sql = "INSERT INTO Pokedex (int treinadorID) values(?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, treinador.getID());
            pst.execute();
            sucesso = true;
        } catch (SQLException exc) {
            System.out.println("Erro PokedexDAO 1: " + exc.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException exc) {
                System.out.println("Erro PokedexDAO 2: " + exc.getMessage());
            }
        }
        return sucesso;
    }

    public int selectPokedexID(int ID) {
        connectToDB();
        // Pega o ID da pokebola que pertence ao usuário do ID fornecido
        String sql = "SELECT ID FROM Pokedex, Pokemano WHERE Pokedex.Pokemano_TreinadorID=?";
        int idPokebola = 0;
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1,ID);
            rs = pst.executeQuery();
            if(rs.next()){
                idPokebola = rs.getInt("ID Pokebola");
            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
        return idPokebola;
    }
}