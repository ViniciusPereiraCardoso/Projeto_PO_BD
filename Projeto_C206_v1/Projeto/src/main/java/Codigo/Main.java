package Codigo;

import DAO.*;

import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Main {

    // Variaveis de classe
    private static Scanner scanner = new Scanner(System.in);
    private static Scanner entrada1 = new Scanner(System.in);
    private static Pokedex pokedex;
    private static Treinador treinadorSelecionado;
    private static Treinador nomeNovoTreinador;
    private static Treinador idEquipeSelecionada;
    private static Equipe equipe;
    private static Pokemano pokemano;



    // Instanciando as classes
    private static PokemanoDAO pokemanoDAO = new PokemanoDAO();
    private static TreinadorDAO treinadorDAO = new TreinadorDAO();
    private static PokedexDAO pokedexDAO = new PokedexDAO();
    private static Pokemano_PokedexDAO _pokemanoPokedexDAO = new Pokemano_PokedexDAO();
    private static EquipeDAO equipeDAO = new EquipeDAO();



    public static void main(String[] args) {
        boolean running = true;


        Scanner entrada1 = new Scanner(System.in);
        Scanner entrada2 = new Scanner(System.in);

        // Menu inicial
        System.out.println("\n---------- Bem vindo, Treinador! ----------");

        System.out.println("-> Primeiro, vamos começar te cadastrando!");
        cadastroTreinador();

        System.out.println("\n-> Digite '1' caso queira ingressar em alguma das equipes listadas acima");
        System.out.println("-> Digite '2' caso queira criar sua própria equipe");

        while (running) {



            int userInputInicial = entrada1.nextInt();

            while(userInputInicial != 1 && userInputInicial != 2 ){ // critica de dados
                System.out.println("-> Opção Inválida. Digite 1 para fazer selecionar uma equipe existente ou 2 para criar sua própria equipe: ");
                userInputInicial = entrada1.nextInt();
            }

            if(userInputInicial == 1) {
                equipeSelecionada();
            }
            else if(userInputInicial == 2) {
                cadastroEquipe();
            }


            // Menu do meio
            System.out.println("Muito bem! Vc passou da etapa inicial! Agora vc pode escolher uma das opções abaixo!\n");
            System.out.println("------------MENU-------------");
            System.out.println(" 1. Adicionar Novo Treinador");
            System.out.println(" 2. Adicionar Novo Pokemano");
            System.out.println(" 3. Atualizar Nome de Equipe");
            System.out.println(" 4. Atualizar Nome de Pokemano");
            System.out.println(" 5. Remover Equipe");
            System.out.println(" 6. Remover Pokemano");
            System.out.println(" 7. Mostrar Pokemanos");
            System.out.println(" 8. Mostrar Equipes");
            System.out.println(" 9. Sair");
            System.out.println("------------------------------\n");

            System.out.println("-> Entre com o nmr da opcao desejada!");

            int userInputMeio = entrada2.nextInt();

            while(userInputMeio != 1 && userInputMeio != 2 && userInputMeio != 3 && userInputMeio != 4 && userInputMeio != 5 && userInputMeio != 6 && userInputMeio != 7 && userInputMeio != 8 && userInputMeio != 9 ){ // critica de dados
                System.out.println("Entre com o nmr da opcao desejada!");
                userInputMeio = entrada2.nextInt();
            }

            switch (userInputMeio) {
                case 1:
                    cadastroTreinador2();
                    break;
                case 2:
                    cadastroPokemano();
                    break;
                case 3:
                    atualizarNomeEquipe();
                    break;
                case 4:
                    atualizarPokemano();
                    break;
                case 5:
                    removerEquipe();
                    break;
                case 6:
                    removerPokemano();
                    break;
                case 7:
                    mostrarEquipes();
                    break;
                case 8:
                    mostrarPokemanos();
                    break;
                case 9:
                    mostrarPokemanos();
                    break;

                case 10:
                    running = sair();
                    break;

                default:
                    System.out.println("Opcao invalida.\n");
            }

        }
        scanner.close();
    }




    // INSERT
    private static void cadastroTreinador() {
        System.out.print("-> Qual o seu nome?\n");
        String nomeNovoTreinador = scanner.nextLine();
        Treinador treinador = new Treinador(nomeNovoTreinador);
        treinadorDAO.insertTreinadorNovo(nomeNovoTreinador);
        System.out.println("-> Segue abaixo, lista de equipes disponíveis para selecao: ");
        System.out.println("--------------------------");
        mostrarEquipes();
    }

    private static void cadastroTreinador2() {
        System.out.print("-> Qual o nome do novo treinador?");
        String nomeNovoTreinador = scanner.nextLine();
        Treinador treinador = new Treinador(nomeNovoTreinador);
        treinadorDAO.insertTreinadorNovo(nomeNovoTreinador);
        System.out.println("-> Segue abaixo, lista de equipes disponíveis para selecao: ");
        System.out.println("--------------------------");
        mostrarEquipes();
    }

    private static void cadastroPokemano() {
        System.out.println("--------------------------");
        System.out.print("-> Qual o nome do pokemano?");
        String nomeNovoPokemano = scanner.nextLine();
        System.out.println("--------------------------");
        System.out.print("-> Qual o tipo do pokemano?");
        String tipoNovoPokemano = scanner.nextLine();
        System.out.println("--------------------------");
        System.out.print("-> Qual o nivel do pokemano?");
        int nivelNovoPokemano = scanner.nextInt();
        System.out.println("--------------------------");
        Pokemano pokemano = new Pokemano(nomeNovoPokemano,tipoNovoPokemano,nivelNovoPokemano);
        pokemanoDAO.insertPokemanoNovo(nomeNovoPokemano,tipoNovoPokemano,nivelNovoPokemano);
    }

    private static void cadastroEquipe(){
        System.out.print("-> Qual o nome da sua nova equipe?");
        String nomeNovaEquipe = scanner.nextLine();
        equipeDAO.InserirEquipeNova(nomeNovaEquipe);
        System.out.println("-> Sua equipe foi adicionada com sucesso! Veja!");
        equipeDAO.selectEquipeSelecionada(nomeNovaEquipe);
        System.out.println("-> Agora é hora de adicionar um pokemano inicial na sua pokedex!");
        Equipe nomeEquipe = new Equipe(nomeNovaEquipe);
        adicionarPokemanoInicial();
    }

    private static void adicionarPokemanoInicial(){
        System.out.println("Agora é hora de escolher um pokemano para a sua pokedex!");
        System.out.println("-> Segue abaixo, lista de pokemanos disponíveis para selecao: \n");
        pokemanoDAO.selectPokemano();
        System.out.println("-> Informe o ID do pokemano que vc quer adicionar na sua pokedex: ");
        int idPokemanoSelecionado = scanner.nextInt();
        _pokemanoPokedexDAO.insertPokemanoPokebola(idPokemanoSelecionado);
        System.out.println("-> Seu pokemano foi adicionado a sua pokedex!");
        System.out.println("--------------------------\n");
    }

    private static void adicionarEquipe(){

        System.out.println("Muito bem! Qual o nome da sua nova equipe?");
        String novaEquipe = scanner.nextLine().toLowerCase();

        System.out.println("Parabéns! Vc criou a Equipe: " +novaEquipe);
        Equipe equipe = new Equipe(novaEquipe);
        equipeDAO.InserirEquipe(equipe);
    }

    private static void equipeSelecionada (){
        System.out.println("-> Informe o ID da equipe que vc quer ingressar: ");
        int idEquipeSelecionada = scanner.nextInt();
        treinadorDAO.insertTreinadorEquipe(idEquipeSelecionada);
        adicionarPokemanoInicial();
    }

    // SELECT
    private static void mostrarEquipes (){
        equipeDAO.selecEquipe();
    }

    private static void mostrarPokemanos (){
        pokemanoDAO.selectPokemano();
    }

    private static void mostrarTreinador (){
        treinadorDAO.selectTreinador();
    }

    // UPDATE
    private static void atualizarNomeEquipe () {
        int idEquipe;
        
        while (true){
            System.out.print("-> Digite o ID da equie que quer atualizar o nome: ");
            if (scanner.hasNextInt() && (idEquipe = scanner.nextInt()) >= 0 ) {
                scanner.nextInt();
                break;
            }
            scanner.nextInt();
        }

        System.out.print("-> Novo nome da equipe: ");
        String nomeNovo = scanner.nextLine();

        equipeDAO.updateEquipe(idEquipe,nomeNovo);
    }
    private static void atualizarPokemano () {

        System.out.print("-> Digite o ID do pokemano que quer atualizar as informações: ");
        int idPokemano = scanner.nextInt();
        System.out.print("-> Novo nome do pokemano: ");
        String nomeNovo = scanner.nextLine();
        System.out.print("-> Novo tipo do pokemano: ");
        String tipoNovo = scanner.nextLine();
        System.out.print("-> Novo nivel do pokemano: ");
        int nivelNovo = scanner.nextInt();

        pokemanoDAO.updateNomePokemano(idPokemano,nomeNovo,tipoNovo,nivelNovo);
    }


    // DELETE
    private static void removerPokemano() {
        pokemanoDAO.selectPokemano();
        System.out.print("-> ID do pokemano a ser removido: ");
        int idPokemanoRemover = scanner.nextInt();

        pokemanoDAO.deletePokemano(idPokemanoRemover);
    }
    private static void removerTreinador() {

        System.out.println("Digite o nome do treinador a ser removido: ");
        String nomeARemover = scanner.nextLine();

        treinadorDAO.deleteTreinador(nomeARemover);
    }
    private static void removerEquipe() {

        System.out.println("Digite o nome da equipe a ser removida: ");
        String nomeARemover = scanner.nextLine();

        equipeDAO.deleteEquipe(nomeARemover);
    }


    // SAIR
    private static boolean sair() {
        while (true) {
            System.out.print("-> Ja vai? (sim/nao): ");
            String resposta = scanner.nextLine().toLowerCase();

            if (resposta.equals("sim") || resposta.equals("s")) {
                return false;
            } else if (resposta.equals("nao") || resposta.equals("n")) {
                System.out.println("Cancelado!\n");
                return true;
            }
        }
    }

}