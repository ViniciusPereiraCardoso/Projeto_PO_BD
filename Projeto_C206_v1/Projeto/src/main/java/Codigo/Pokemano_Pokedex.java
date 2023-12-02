package Codigo;

public class Pokemano_Pokedex {
    private int PokemanoID;
    private int PokedexID;


    // Construtor
    public Pokemano_Pokedex(int pokedexID, int pokemanoID) {
        PokedexID = pokedexID;
        PokemanoID = pokemanoID;
    }
    public Pokemano_Pokedex(int pokemanoID) {
        PokemanoID = pokemanoID;
    }

    public int getPokemanoID() {
        return PokemanoID;
    }
    public int getPokedexID() {
        return PokedexID;
    }
}