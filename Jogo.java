package Yahtzee;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Jogo {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        List<Jogador> jogadores = new ArrayList<>();
        Cadastrar cadastrar = new Cadastrar(jogadores);
        List<int[]> cartela = new ArrayList<>();

        System.out.println("Bem-vindo ao Yahtzee!");

        int rodada = 1;
        File saveFile = new File(SAVE_FILE_PATH + 1 + ".txt");
        if (saveFile.exists()) {
            System.out.print("Jogo salvo encontrado. Deseja continuar o jogo anterior? (1 - Sim, 2 - Não): ");
            int escolha = scanner.nextInt();
            if (escolha == 1) {
                List<Object[]> players = carregarJogadores();
                for (Object[] objects : players) {
                    Jogador jogador = new Jogador((int) objects[0], (String) objects[1], (int[]) objects[2]);
                    jogadores.add(jogador);
                }
                rodada = carregarRodada();
                while (true) {
                    System.out.println("==============================================");
                    System.out.println("Menu:");
                    System.out.println("==============================================");
                    System.out.println("1. Alterar jogadores");
                    System.out.println("2. Apagar jogadores");
                    System.out.println("3. Mostrar lista de jogadores");
                    System.out.println("4. Jogar");
                    System.out.println("5. Mostrar Resultado");
                    System.out.println("6. Salvar Jogo");
                    System.out.println("7. Sair");
                    System.out.println("==============================================");
                    System.out.print("Escolha uma das opções: ");

                    int opcao = scanner.nextInt();

                    switch (opcao) {
                        case 1:
                            if (jogadores.isEmpty()) {
                                System.out.println("Por favor, cadastre jogadores antes de alterá-los.");
                            } else {
                                cadastrar.listarJogadores();
                                System.out.print("Digite o ID do jogador que você deseja alterar: ");
                                int idParaAlterar = scanner.nextInt();
                                scanner.nextLine();
                                cadastrar.alterarJogador(idParaAlterar);
                                cadastrar.listarJogadores();
                            }
                            break;
                        case 2:
                            if (jogadores.isEmpty()) {
                                System.out.println("Por favor, cadastre jogadores antes de apagá-los.");
                            } else {
                                cadastrar.apagarJogador();
                            }
                            break;
                        case 3:
                            if (jogadores.isEmpty()) {
                                System.out.println("Por favor, cadastre jogadores antes de ver a lista de jogadores.");
                            } else {
                                cadastrar.listarJogadores();
                            }
                            break;
                        case 4:
                            if (jogadores.isEmpty()) {
                                System.out.println("Por favor, cadastre jogadores antes de jogar.");
                            } else {
                                jogar(jogadores, cartela);
                            }
                            break;
                        case 5:
                            if (jogadores.isEmpty()) {
                                System.out.println("Por favor, cadastre jogadores antes de mostrar o resultado.");
                            } else {
                                mostrarResultado(jogadores);
                            }
                            break;
                        case 6:
                            salvarJogo(jogadores, rodada);
                            System.out.println("Jogo salvo com sucesso!");
                            break;
                        case 7:
                            System.out.println("Saindo do programa.");
                            return;
                        default:
                            System.out.println("Opção inválida. Tente novamente.");
                            System.out.println("");
                            break;
                    }
                }
            }
        }

        while (true) {
            System.out.println("==============================================");
            System.out.println("Menu:");
            System.out.println("==============================================");
            System.out.println("1. Cadastrar Jogadores");
            System.out.println("2. Alterar jogadores");
            System.out.println("3. Apagar jogadores");
            System.out.println("4. Mostrar lista de jogadores");
            System.out.println("5. Jogar");
            System.out.println("6. Mostrar Resultado");
            System.out.println("7. Sair");
            System.out.println("==============================================");

            System.out.print("Escolha uma das opções: ");

            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    cadastrar.cadastrarJogadores();
                    cadastrar.listarJogadores();
                    break;
                case 2:
                    if (jogadores.isEmpty()) {
                        System.out.println("Por favor, cadastre jogadores antes de alterá-los.");
                        break;
                    } else {
                        cadastrar.listarJogadores();
                        System.out.print("Digite o ID do jogador que você deseja alterar: ");
                        int idParaAlterar = scanner.nextInt();
                        scanner.nextLine();
                        cadastrar.alterarJogador(idParaAlterar);
                        cadastrar.listarJogadores();
                        break;
                    }
                case 3:
                    if (jogadores.isEmpty()) {
                        System.out.println("Por favor, cadastre jogadores antes de apagá-los.");
                    } else {
                        cadastrar.apagarJogador();
                    }
                    break;
                case 4:
                    cadastrar.listarJogadores();
                    break;
                case 5:
                    if (jogadores.isEmpty()) {
                        System.out.println("Por favor, cadastre jogadores antes de jogar.");
                    } else {
                        jogar(jogadores, cartela);
                    }
                    break;
                case 6:
                    if (jogadores.isEmpty()) {
                        System.out.println("Por favor, cadastre jogadores antes de mostrar o resultado.");
                    } else {
                        Cartela(jogadores);
                    }
                    break;
                case 7:
                    System.out.println("Saindo do programa.");
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
    }
    public static void mostrarResultado(List<Jogador> jogadores) {
        int numJogadores = jogadores.size();
        int numCategorias = categorias.length;

        System.out.printf("%-15s", "");
        for (String categoria : categorias) {
            System.out.printf("%-15s", categoria.substring(0, Math.min(categoria.length(), 15)));
        }
        System.out.println();

        for (Jogador jogador : jogadores) {
            System.out.printf("%-15s", jogador.getNome().substring(0, Math.min(jogador.getNome().length(), 15)));
            for (int i = 0; i < numCategorias; i++) {
                int pontuacao = jogador.getPontuacao(i + 1);
                System.out.printf("%-15d", pontuacao);
            }
            System.out.println();
        }
    }


    public static final String[] categorias = {
            "Uns", "Dois", "Três", "Quatros", "Cincos", "Seis",
            "Trinca", "Quadra", "Fullhouse", "Seq. curta", "Seq. longa", "Yahtzee", "Sorte"
    };

    public static void jogar(List<Jogador> jogadores, List<int[]> cartelas) {
        Scanner scanner = new Scanner(System.in);
    
        int rodadas = 0;
        int maxRodadas = 13;
    
        while (rodadas < maxRodadas) {
            rodadas++;
    
            System.out.println("==============================================");
            System.out.println("Rodada " + rodadas);
            System.out.println("==============================================");
    
            for (Jogador jogador : jogadores) {
                System.out.println();
                System.out.println("Vez do jogador: " + jogador.getNome());
                System.out.println();
    
                int[] dados = new int[5];
    
                for (int i = 0; i < dados.length; i++) {
                    dados[i] = rolarDado();
                }
    
                System.out.println("Dados lançados:");
                for (int valor = 0; valor < 5; valor++) {
                    System.out.println("Posição " + (valor + 1) + " = " + dados[valor]);
                }
                System.out.println();

                for (int tentativa = 0; tentativa <= 1; tentativa++) {
                    System.out.println("Tentativa " + (tentativa + 1) + ":");
                    int p1 = 0, p2 = 0, p3 = 0, p4 = 0, p5 = 0;
        
                    for (int i = 0; i < 5; i++) {
                        System.out.println("Você quer alterar o valor de algum dos dados?\n"
                        + "Sim = 1.\n"
                        + "Não = Qualquer outro número.");
                        int alt = scanner.nextInt();
                        if (alt != 1){
                            System.out.println("Ok\n");
                            break;
                        }
                        System.out.print("Qual posição você deseja alterar? (1-5): ");
                        int dadoSelecionado = scanner.nextInt();
        
                        if (dadoSelecionado == 1 && p1 == 0) {
                            p1 = 1;
                            dados[dadoSelecionado - 1] = rolarDado();
                        } else if (dadoSelecionado == 2 && p2 == 0) {
                            p2 = 1;
                            dados[dadoSelecionado - 1] = rolarDado();
                        }else if (dadoSelecionado == 3 && p3 == 0) {
                            p3 = 1;
                            dados[dadoSelecionado - 1] = rolarDado();
                        }else if (dadoSelecionado == 4 && p4 == 0) {
                            p4 = 1;
                            dados[dadoSelecionado - 1] = rolarDado();
                        }else if (dadoSelecionado == 5 && p5 == 0) {
                            p5 = 1;
                            dados[dadoSelecionado - 1] = rolarDado();
                        }else if (dadoSelecionado == 1 && p1 == 1) {
                            System.out.println("Já selecionado");
                            i--;
                        }else if (dadoSelecionado == 2 && p2 == 1) {
                            System.out.println("Já selecionado");
                            i--;
                        }else if (dadoSelecionado == 3 && p3 == 1) {
                            System.out.println("Já selecionado");
                            i--;
                        }else if (dadoSelecionado == 4 && p4 == 1) {
                            System.out.println("Já selecionado");
                            i--;
                        }else if (dadoSelecionado == 5 && p5 == 1) {
                            System.out.println("Já selecionado");
                            i--;
                        }	else {
                            System.out.println("Índice inválido. Tente novamente.");
                            i--;
                        }
                    }
                }
    
                System.out.println("Calculando pontos...");
    
                int[] dadosCopy = Arrays.copyOf(dados, dados.length);
    
                for (int i = 0; i < categorias.length; i++) {
                    int pontuacao = PontuacaoYahtzee.calcularPontuacao(dadosCopy, categorias[i]);
                    System.out.println((i + 1) + ". " + categorias[i] + ": " + pontuacao + " pontos");
                }
    
                int bonus = Bonus(dadosCopy);
                jogador.adicionarBonus(bonus);
                System.out.println("Bônus acumulado: " + jogador.getBonus());
    
                int somaPrimeirasCategorias = 0;
                for (int i = 0; i < 6; i++) {
                    somaPrimeirasCategorias += jogador.getPontuacao(i + 1);
                }
    
                int bonusAtingido = (somaPrimeirasCategorias >= 63) ? jogador.getBonus() : 0;
                jogador.adicionarPontuacao(-1, bonusAtingido);
    
                int categoriaEscolhida = -1;
                do {
                    System.out.print("Escolha a categoria (1-13): ");
                    categoriaEscolhida = scanner.nextInt();
    
                    if (jogador.isCategoriaPreenchida(categoriaEscolhida)) {
                        System.out.println("Categoria já preenchida. Escolha outra.");
                        categoriaEscolhida = -1;
                    }
                } while (categoriaEscolhida < 1 || categoriaEscolhida > 13);
    
                int pontuacaoCategoria = PontuacaoYahtzee.calcularPontuacao(dadosCopy, categorias[categoriaEscolhida - 1]);
                jogador.atualizarPontuacao(categoriaEscolhida, pontuacaoCategoria);
            }
    
            Cartela(jogadores);

            System.out.println("==============================================");
            System.out.println("Opções");
            System.out.println("1. Continuar jogo");
            System.out.println("2. Salvar jogo");
            System.out.println("3. Voltar pro menu");
            System.out.println("==============================================");
            System.out.print("Escolha uma opção: ");
    
            int opcaoRodada = scanner.nextInt();
    
            switch (opcaoRodada) {
                case 1:
                    break;
                case 2:
                    salvarJogo(jogadores, opcaoRodada);
                    System.out.println("Jogo salvo com sucesso!");
                    return;
                case 3:
                    Voltar(jogadores);
                    System.out.println("==============================================");
                    System.out.println("Saindo da Partida...");
                    return;
                default:
                    System.out.println("Opção inválida. Continuando jogo...");
                    break;
            }
        }
    
        Vencedor(jogadores);
    }


    public static void Voltar(List<Jogador> jogadores) {
        for (Jogador jogador : jogadores) {
            jogador.zerarPontuacoes();
        }

        cartela.clear();
    }

    public static int Bonus(int[] dados) {
        int SeisCategorias = 0;

        for (int i = 1; i <= 6; i++) {
            SeisCategorias += PontuacaoYahtzee.calcularSomaDado(dados, i);
        }

        int bonus = (SeisCategorias >= 63) ? 35 : 0;

        return bonus;
    }

    public static void Cartela(List<Jogador> jogadores) {
        int numJogadores = jogadores.size();
        int numCategorias = categorias.length;

        System.out.printf("%-15s", "");
        for (String categoria : categorias) {
            System.out.printf("%-15s", categoria.substring(0, Math.min(categoria.length(), 15)));
        }
        System.out.println();

        for (Jogador jogador : jogadores) {
            System.out.printf("%-15s", jogador.getNome().substring(0, Math.min(jogador.getNome().length(), 15)));
            int somaPrimeirasCategorias = 0;

            for (int i = 0; i < 6; i++) {
                somaPrimeirasCategorias += jogador.getPontuacao(i + 1);
            }

            if (somaPrimeirasCategorias >= 63) {
                jogador.adicionarPontuacao(-1, 35);
            } else {
                jogador.adicionarPontuacao(-1, 0);
            }

            for (int i = 0; i < numCategorias; i++) {
                int pontuacao = jogador.getPontuacao(i + 1);
                System.out.printf("%-15d", pontuacao);
            }

            System.out.printf("%-15d", jogador.getBonus());
            System.out.println();
        }

        for (Jogador jogador : jogadores) {
            int pontosRestantes = 63 - jogador.getPontuacao(-1);
            System.out.println("\nJogador: " + jogador.getNome());
            System.out.println("Pontuação Total: " + jogador.getPontuacaoTotal());
            System.out.println("==============================================");
        }
    }

    public static int rolarDado() {
        Random random = new Random();
        return random.nextInt(6) + 1;
    }

    public static void Vencedor(List<Jogador> jogadores) {
        Jogador vencedor = jogadores.get(0);

        for (Jogador jogador : jogadores) {
            if (jogador.getPontuacaoTotal() > vencedor.getPontuacaoTotal()) {
                vencedor = jogador;
            }
        }

        System.out.println("\n==============================================");
        System.out.println("Vencedor da partida: " + vencedor.getNome());
        System.out.println("Pontuação Total: " + vencedor.getPontuacaoTotal());
        System.out.println("Parabéns!");
        System.out.println("==============================================");
    }

    public static final String SAVE_FILE_PATH = "yahtzee_save";

    public static void salvarJogo(List<Jogador> jogadores, int rodada) {
        for (Jogador jogador : jogadores) {
            try (BufferedWriter writer = new BufferedWriter(
                    new FileWriter(SAVE_FILE_PATH + jogador.getId() + ".txt"))) {
                writer.write(jogador.getId() + "\n" + jogador.getNome() + "\n");
                for (int linhaCartela : jogador.cartela) {
                    writer.write(linhaCartela + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        salvarRodada(rodada);
        System.out.println("Jogo salvo com sucesso!");
    }


    public static void salvarRodada(int rodada) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Rodada.txt"))) {
            writer.write(rodada + "");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static List<Object[]> carregarJogadores() {
        List<Object[]> players = new ArrayList<>();
        Object player = new Object();
        int targetId = 1;
        while (true) {
            File saveFile = new File(SAVE_FILE_PATH + targetId + ".txt");
            if (saveFile.exists()) {
                String nick = "";
                int id = 0;
                int[] cartela = new int[14];
                try (BufferedReader reader = new BufferedReader(new FileReader(SAVE_FILE_PATH + targetId + ".txt"))) {
                    id = Integer.parseInt(reader.readLine());
                    nick = reader.readLine();

                    String linha;
                    int index = 0;
                    while ((linha = reader.readLine()) != null) {
                        cartela[index] = (Integer.parseInt(linha));
                        index++;
                    }

                    System.out.println("Jogo carregado com sucesso!");
                } catch (IOException | NumberFormatException e) {
                    e.printStackTrace();
                }
                players.add(new Object[] { id, nick, cartela });
                targetId++;
            } else {
                break;
            }
        }
        return players;
    }


    public static int carregarRodada() {
        int rodada = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader("Rodada.txt"))) {
            rodada = Integer.parseInt(reader.readLine());
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return rodada;
    }



}
