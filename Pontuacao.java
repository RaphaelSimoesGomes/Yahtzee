package Yahtzee;

public class Pontuacao {

    public int categoria;
    public int pontuacao;

    public Pontuacao(int categoria, int pontuacao) {
        this.categoria = categoria;
        this.pontuacao = pontuacao;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public int getPontuacao() {
        return pontuacao;
    }
}
