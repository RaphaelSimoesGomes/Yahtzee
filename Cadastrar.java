package Yahtzee;

import java.util.List;
import java.util.Scanner;

public class Cadastrar {

    public final List<Jogador> jogadores;

    public Cadastrar(List<Jogador> jogadores) {
        this.jogadores = jogadores;
    }

    public List<Jogador> getJogadores() {
        return jogadores;
    }

    public void cadastrarJogadores() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Quantos jogadores você deseja cadastrar? ");
        int numJogadores = 0;
        if (scanner.hasNextInt()) {
            numJogadores = scanner.nextInt();
            scanner.nextLine();
        } else {
            System.out.println("==============================================");
            System.out.println("Forneça um número válido");
            System.out.println("==============================================");
            scanner.nextLine();
        }

        for (int i = 0; i < numJogadores; i++) {
            System.out.println("Digite o nome do Jogador " + (i + 1) + ": ");
            String nome = scanner.nextLine();
            System.out.println("============================");
            System.out.println("Jogador cadastrado com sucesso!");
            System.out.println("============================");

            Jogador jogador = new Jogador(nome);
            jogadores.add(jogador);
        }
    }

    public void listarJogadores() {

        System.out.println("Lista de Jogadores:");

        for (Jogador jogador : jogadores) {
            System.out.println(jogador);
            System.out.println("Pontuação Total: " + jogador.getPontuacaoTotal());
            System.out.println();
        }
    }

    public void apagarJogador() {
        Scanner scanner = new Scanner(System.in);
        boolean jogadorEncontrado = false;

        listarJogadores();

        System.out.println("\nDigite o nome do jogador que você quer apagar: ");
        String nomeParaApagar = scanner.nextLine();

        for (int i = 0; i < jogadores.size(); i++) {
            Jogador jogador = jogadores.get(i);
            if (jogador.getNome().equals(nomeParaApagar)) {
                jogadores.remove(i);
                jogadorEncontrado = true;
                System.out.println("Jogador removido com sucesso!");
                System.out.println("");
                break;
            }
        }

        if (!jogadorEncontrado) {
            System.out.println("O nome digitado não foi encontrado.");
            System.out.println("");
        }
    }

    public void alterarJogador(int id) {
        for (Jogador jogador : jogadores) {
            if (jogador.getId() == id) {
                Scanner scanner = new Scanner(System.in);

                System.out.println("Digite o novo nome para o Jogador " + jogador.getId() + ": ");
                String nome = scanner.nextLine();
                jogador.setNome(nome);

                System.out.println("Jogador alterado com sucesso!");
                return;
            }
        }

        System.out.println("Jogador com ID " + id + " não encontrado.");
    }
}
