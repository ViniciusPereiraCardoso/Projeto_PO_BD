-- -----------------------------------------------------
-- Schema projeto
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS projeto DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE projeto ;

-- -----------------------------------------------------
-- Table projeto.Equipe
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS projeto.Equipe (
  ID INT NOT NULL AUTO_INCREMENT,
  Nome VARCHAR(45) NOT NULL,
  PRIMARY KEY (ID));


-- -----------------------------------------------------
-- Table projeto.Treinador
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS projeto.Treinador (
  ID INT NOT NULL AUTO_INCREMENT,
  Nome VARCHAR(45) NOT NULL,
  EquipeID INT NOT NULL,
  PRIMARY KEY (ID),
  CONSTRAINT fk_Treinador_Equipe
    FOREIGN KEY (EquipeID)
    REFERENCES projeto.Equipe (ID)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table projeto.Pokedex
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS projeto.Pokedex (
  ID INT NOT NULL AUTO_INCREMENT,
  TreinadorID INT NOT NULL,
  PRIMARY KEY (ID),
  CONSTRAINT fk_Pokedex_Treinador1
    FOREIGN KEY (TreinadorID)
    REFERENCES projeto.Treinador (ID)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table projeto.Pokemano
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS projeto.Pokemano (
  ID INT NOT NULL AUTO_INCREMENT,
  Nome VARCHAR(45) NOT NULL,
  Tipo VARCHAR(45) NOT NULL,
  Nivel VARCHAR(45) NOT NULL,
  PRIMARY KEY (ID));


-- -----------------------------------------------------
-- Table projeto.Pokemano_Pokedex
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS projeto.Pokemano_Pokedex (
  PokemanoID INT NOT NULL,
  PokedexID INT NOT NULL,
  PRIMARY KEY (PokemanoID, PokedexID),
  CONSTRAINT fk_Pokemano_has_Pokedex_Pokemano1
    FOREIGN KEY (PokemanoID)
    REFERENCES projeto.Pokemano (ID)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_Pokemano_has_Pokedex_Pokedex1
    FOREIGN KEY (PokedexID)
    REFERENCES projeto.Pokedex (ID)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    
    INSERT INTO Equipe (nome) VALUES ('Equipe A');
    INSERT INTO Equipe (nome) VALUES ('Equipe B');
    INSERT INTO Equipe (nome) VALUES ('Equipe C');
    
	INSERT INTO Treinador (Nome,EquipeID) VALUES ('Treinador 1','1');
	INSERT INTO Treinador (Nome,EquipeID) VALUES ('Treinador 2','1');
	INSERT INTO Treinador (Nome,EquipeID) VALUES ('Treinador 3','2');
	INSERT INTO Treinador (Nome,EquipeID) VALUES ('Treinador 4','2');
	INSERT INTO Treinador (Nome,EquipeID) VALUES ('Treinador 5','3');
	INSERT INTO Treinador (Nome,EquipeID) VALUES ('Treinador 6','3');
    
	INSERT INTO Pokedex (TreinadorID) VALUES ('1');
	INSERT INTO Pokedex (TreinadorID) VALUES ('2');
	INSERT INTO Pokedex (TreinadorID) VALUES ('3');
	INSERT INTO Pokedex (TreinadorID) VALUES ('4');
	INSERT INTO Pokedex (TreinadorID) VALUES ('5');
	INSERT INTO Pokedex (TreinadorID) VALUES ('6');
    
    INSERT INTO Pokemano (Nome, Tipo, Nivel) VALUES ('Pokemano A', 'Fogo', '10');
    INSERT INTO Pokemano (Nome, Tipo, Nivel) VALUES ('Pokemano B', 'Agua', '10');
    INSERT INTO Pokemano (Nome, Tipo, Nivel) VALUES ('Pokemano C', 'Eletrico', '10');

	INSERT INTO Pokemano_Pokedex (PokemanoID, PokedexID) VALUES ('1','1');
	INSERT INTO Pokemano_Pokedex (PokemanoID, PokedexID) VALUES ('2','1');
	INSERT INTO Pokemano_Pokedex (PokemanoID, PokedexID) VALUES ('3','1');
	INSERT INTO Pokemano_Pokedex (PokemanoID, PokedexID) VALUES ('1','2');
	INSERT INTO Pokemano_Pokedex (PokemanoID, PokedexID) VALUES ('2','2');
	INSERT INTO Pokemano_Pokedex (PokemanoID, PokedexID) VALUES ('3','2');
    INSERT INTO Pokemano_Pokedex (PokemanoID, PokedexID) VALUES ('1','3');
	INSERT INTO Pokemano_Pokedex (PokemanoID, PokedexID) VALUES ('2','3');
	INSERT INTO Pokemano_Pokedex (PokemanoID, PokedexID) VALUES ('3','3');
	INSERT INTO Pokemano_Pokedex (PokemanoID, PokedexID) VALUES ('1','4');
	INSERT INTO Pokemano_Pokedex (PokemanoID, PokedexID) VALUES ('2','4');
	INSERT INTO Pokemano_Pokedex (PokemanoID, PokedexID) VALUES ('3','4');
    INSERT INTO Pokemano_Pokedex (PokemanoID, PokedexID) VALUES ('1','5');
	INSERT INTO Pokemano_Pokedex (PokemanoID, PokedexID) VALUES ('2','5');
	INSERT INTO Pokemano_Pokedex (PokemanoID, PokedexID) VALUES ('3','5');
	INSERT INTO Pokemano_Pokedex (PokemanoID, PokedexID) VALUES ('1','6');
	INSERT INTO Pokemano_Pokedex (PokemanoID, PokedexID) VALUES ('2','6');
	INSERT INTO Pokemano_Pokedex (PokemanoID, PokedexID) VALUES ('3','6');
	