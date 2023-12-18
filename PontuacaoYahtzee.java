package Yahtzee;

import java.util.Arrays;

public class PontuacaoYahtzee {
    public static int calcularPontuacao(int[] dados, String categoria) {
        switch (categoria.toLowerCase()) {
            case "uns":
                return calcularSomaDado(dados, 1);
            case "dois":
                return calcularSomaDado(dados, 2);
            case "três":
                return calcularSomaDado(dados, 3);
            case "quatros":
                return calcularSomaDado(dados, 4);
            case "cincos":
                return calcularSomaDado(dados, 5);
            case "seis":
                return calcularSomaDado(dados, 6);
            case "trinca":
                return calcularTrinca(dados);
            case "quadra":
                return calcularQuadra(dados);
            case "fullhouse":
                return calcularFullHouse(dados);
            case "seqcurta":
                return calcularSequenciaBaixa(dados);
            case "seqlonga":
                return calcularSequenciaAlta(dados);
            case "sorte":
                return calcularChance(dados);
            case "yahtzee":
                return calcularYatzy(dados);
            default:
                return 0;
        }
    }

    public static int calcularSomaDado(int[] dados, int valorDado) {
        int soma = 0;
        for (int dado : dados) {
            if (dado == valorDado) {
                soma += valorDado;
            }
        }
        return soma;
    }

    public static int calcularTrinca(int[] dados) {
        int[] contagem = new int[7];
        int soma = 0;
        for (int dado : dados) {
            contagem[dado]++;
        }

        for (int i = 6; i >= 1; i--) {
            if (contagem[i] >= 3) {
                soma = dados[0] + dados[1] + dados[2] + dados[3] + dados[4];
                break;
            }
        }
        return soma;
    }

    public static int calcularQuadra(int[] dados) {
        int[] contagem = new int[7];
        int soma = 0;
        for (int dado : dados) {
            contagem[dado]++;
        }

        for (int i = 6; i >= 1; i--) {
            if (contagem[i] >= 4) {
                soma = dados[0] + dados[1] + dados[2] + dados[3] + dados[4];
            }
        }
        return soma;
    }

    public static int calcularFullHouse(int[] dados) {
        int[] contagem = new int[7];
        boolean trinca = false;
        boolean par = false;

        for (int dado : dados) {
            contagem[dado]++;
        }

        for (int i = 6; i >= 1; i--) {
            if (contagem[i] == 3) {
                trinca = true;
            } else if (contagem[i] == 2) {
                par = true;
            }
        }

        if (trinca && par) {
            return 25;
        } else {
            return 0;
        }
    }

    public static int calcularSequencia(int[] dados, int chave) {
        Arrays.sort(dados);
        int sequencia = 0;

        for (int i = 0; i < dados.length - 1; i++) {
            if (dados[i] + 1 == dados[i + 1]) {
                sequencia++;
            }
        }

        if (sequencia == dados.length - 1 && chave == 2) {
            return 40;
        } else if (sequencia >= dados.length - 2 && chave == 1) {
            return 30;
        } else {
            return 0;
        }
    }

    public static int calcularSequenciaBaixa(int[] dados) {
        int resultado = calcularSequencia(dados, 1);
        return resultado;
    }

    public static int calcularSequenciaAlta(int[] dados) {
        int resultado = calcularSequencia(dados, 2);
        return resultado;
    }

    public static int calcularYatzy(int[] dados) {
        for (int i = 1; i < dados.length; i++) {
            if (dados[i] != dados[0]) {
                return 0;
            }
        }
        return 50;
    }

    public static int calcularChance(int[] dados) {
        int soma = 0;
        for (int dado : dados) {
            soma += dado;
        }
        return soma;
    }

    public static int Bonus(int[] dados) {
        int SeisCategorias = 0;

        for (int i = 1; i <= 6; i++) {
            SeisCategorias += calcularSomaDado(dados, i);
        }

        int bonus = (SeisCategorias >= 63) ? 35 : 0;

        return bonus;
    }

    public static int Total(int[] dados, String[] categorias) {
        int pontuacaoTotal = 0;

        for (String categoria : categorias) {
            pontuacaoTotal += calcularPontuacao(dados, categoria);
        }

        pontuacaoTotal += Bonus(dados);

        return pontuacaoTotal;
    }
}