package Yahtzee;

import java.util.ArrayList;
import java.util.List;

public class Jogador {
    public static int contadorId = 1;
    public int[] cartela;
    public int id;
    public String nome;
    public int bonus;
    public final List<Pontuacao> pontuacoes;

    public int[] criarCartelaPadrao() {
        int[] cartela = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        return cartela;
    }

    public Jogador(String nome) {
        this.id = contadorId++;
        this.nome = nome;
        this.pontuacoes = new ArrayList<>();
        cartela = criarCartelaPadrao();
    }

    public Jogador(String nome, int[] cartela) {
        this.id = contadorId++;
        this.cartela = cartela;
        this.nome = nome;
        this.pontuacoes = new ArrayList<>();
    }
    public Jogador(int id,String nome, int[] cartela) {
        this.id = id;
        this.cartela = cartela;
        this.nome = nome;
        this.pontuacoes = new ArrayList<>();
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void adicionarPontuacao(int categoria, int pontuacao) {
        Pontuacao pontuacaoAtual = new Pontuacao(categoria, pontuacao);
        pontuacoes.add(pontuacaoAtual);
    }

    public int getPontuacaoTotal() {
        int pontuacaoTotal = 0;
        for (Pontuacao pontuacao : pontuacoes) {
            pontuacaoTotal += pontuacao.getPontuacao();
        }
        return pontuacaoTotal;
    }

    public int[] getCartela() {
        return cartela;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Nome: " + nome;
    }

    public int getPontuacao(int categoria) {
        for (Pontuacao pontuacao : pontuacoes) {
            if (pontuacao.getCategoria() == categoria) {
                return pontuacao.getPontuacao();
            }
        }
        return 0;
    }
    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public void adicionarBonus(int pontos) {
        bonus += pontos;
    }

    public boolean isCategoriaPreenchida(int categoria) {
        for (Pontuacao pontuacao : pontuacoes) {
            if (pontuacao.getCategoria() == categoria) {
                return true;
            }
        }
        return false;
    }

    public void atualizarPontuacao(int categoria, int pontuacao) {
        for (Pontuacao pontuacaoAtual : pontuacoes) {
            if (pontuacaoAtual.getCategoria() == categoria) {
                pontuacaoAtual.setPontuacao(pontuacao);
                return;
            }
        }
        adicionarPontuacao(categoria, pontuacao);
    }

    public void zerarPontuacoes() {
        pontuacoes.clear(); // Limpa a lista de pontuações
        bonus = 0; // Zera o bônus
    }

    public int getBonus() {
        return bonus;
    }
}
