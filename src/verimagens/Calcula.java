/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verimagens;

/**
 *
 * @author damiao regis
 */
public class Calcula {

    private int[][] pixels;

    Calcula(int[][] matriz) {
        pixels = matriz;
    }

    public int largura() {
        if (pixels != null) {
            return pixels[0].length;
        } else {
            return 0;
        }
    }

    public int altura() {
        if (pixels != null) {
            return pixels.length;
        } else {
            return 0;
        }
    }

    public double arredondaParaCima(double n) {
        if (n % 1 > 0) {
            n -= n % 1;
        }
        n++;
        return n;
    }

    public void organizaVetor(int[] n) {

        int auxiliar = 0;
        for (int i = 0; i < n.length - 1; i++) {
            for (int j = 0; j < n.length - 2; j++) {
                if (n[j] > n[j + 1]) {
                    auxiliar = n[j + 1];
                    n[j + 1] = n[j];
                    n[j] = auxiliar;
                }
            }
        }
    }

    public int medianaDoArray(int[] n) {
        organizaVetor(n);
        int aux = 0;
        for (int i = 0; i < n.length; i++) {
            if (i != 256) {
                aux++;
            }
        }

        if (aux % 2 == 1) {
            return n[aux / 2];
        } else {

            double resultado = ((n[aux / 2 - 1] + n[aux / 2]) / 2);
            resultado = arredondaParaCima(resultado);
            return (int) resultado;
        }
    }

    public void filtroMedio(int tamanho) {
        if (tamanho % 2 == 0 || tamanho <= 0) {
            throw new ArithmeticException();
        }

        int[][] copia = new int[this.altura()][this.largura()];
        for (int i = 0; i <= this.altura() - 1; i++) { //i corresponde às linhas
            for (int j = 0; j <= this.largura() - 1; j++) {//j corresponde às colunas
                copia[i][j] = this.pixels[i][j];
            }
        }

        double novoValorPixxel = 0;
        int razao;

        for (int i = 0; i <= copia.length - 1; i++) {
            for (int j = 0; j <= copia[0].length - 1; j++) {
                razao = tamanho * tamanho;
                novoValorPixxel = 0;
                for (int k = i - tamanho / 2; k <= i + tamanho / 2; k++) {
                    for (int h = j - tamanho / 2; h <= j + tamanho / 2; h++) {
                        if (k < 0 || h < 0 || k > copia.length - 1 || h > copia[0].length - 1) {
                            razao--;
                            continue;
                        }
                        novoValorPixxel += copia[k][h];
                    }
                }
                novoValorPixxel = arredondaParaCima(novoValorPixxel / razao);
                if (novoValorPixxel >= 255) {
                    novoValorPixxel = 255;
                }
                
                pixels[i][j] = (int) novoValorPixxel;

            }
        }
    }

    public void filtroMediano(int tamanho) {
        if (tamanho % 2 == 0 || tamanho <= 0) {
            throw new ArithmeticException();
        }
        int[][] copia = new int[this.altura()][this.largura()];
        for (int i = 0; i <= this.altura() - 1; i++) {
            for (int j = 0; j <= this.largura() - 1; j++) {
                copia[i][j] = this.pixels[i][j];

            }
        }

        int[] n = new int[tamanho * tamanho];
        int mediana = 0;
        for (int i = 0; i <= copia.length - 1; i++) {
            for (int j = 0; j <= copia[0].length - 1; j++) {
                for (int k = 0; k < tamanho; k++) {
                    for (int h = 0; h < tamanho; h++) {
                        if (i - tamanho / 2 + k < 0 || j - tamanho / 2 + h < 0 || i - tamanho / 2 + k > copia.length - 1 || j - tamanho / 2 + h > copia[0].length - 1) {
                            n[k * tamanho + h] = 256;
                        } else {
                            n[k * tamanho + h] = copia[i - tamanho / 2 + k][j - tamanho / 2 + h];

                        }
                    }
                }

                mediana = medianaDoArray(n);

                if (mediana >= 255) {
                    mediana = 255;
                }

                pixels[i][j] = (int) mediana;

            }
        }
    }

    public void filtroGaussiano(double sigma, int tamanho) {
        if (tamanho % 2 == 0 || tamanho <= 0 || sigma < 0) {
            throw new ArithmeticException();
        }
        int[][] copia = new int[this.altura()][this.largura()];
        for (int i = 0; i <= this.altura() - 1; i++) {
            for (int j = 0; j <= this.largura() - 1; j++) {
                copia[i][j] = this.pixels[i][j];
            }
        }
        double somatorioGaussiano = 0;
        int n = 0;
        for (int i = 0; i <= copia.length - 1; i++) {
            for (int j = 0; j <= copia[0].length - 1; j++) {
                somatorioGaussiano = 0;
                for (int k = i - tamanho / 2; k <= i + tamanho / 2; k++) {
                    for (int h = j - tamanho / 2; h <= j + tamanho / 2; h++) {
                        if (k < 0 || h < 0 || k > copia.length - 1 || h > copia[0].length - 1) {
                            continue;
                        }
                        somatorioGaussiano += (Math.exp(-((i - k) * (i - k) + (j - h) * (j - h)) / 2 * sigma * sigma) / (2 * Math.PI * sigma * sigma));
                    }
                }
                n = 0;
                for (int k = i - tamanho / 2; k <= i + tamanho / 2; k++) {
                    for (int h = j - tamanho / 2; h <= j + tamanho / 2; h++) {
                        if (k < 0 || h < 0 || k > copia.length - 1 || h > copia[0].length - 1) {
                            continue;
                        }
                        n += (Math.exp(-((i - k) * (i - k) + (j - h) * (j - h)) / 2 * sigma * sigma) / (2 * Math.PI * sigma * sigma)) * copia[k][h] / somatorioGaussiano;

                    }
                }

                if (n >= 255) {
                    n = 255;
                }

                pixels[i][j] = (int) arredondaParaCima(n);

            }
        }

    }

    public int Localizar_Moda(int[] vetor) {

        int moda = 0, aux = 0, valor = 0;
        for (int x = 0; x < vetor.length; x++) {
            if (aux > moda) {
                moda = aux;
                valor = vetor[x - 1];
            }
            aux = 0;
            for (int y = x; y < vetor.length; y++) {
                if (vetor[x] == vetor[y]) {
                    aux++;
                }
            }
        }
        if (moda == 1) {
            return vetor[0];
        } else {
            return valor;
        }
    }

    public void filtroModa(int tamanho) {
        if (tamanho % 2 == 0 || tamanho <= 0) {
            throw new ArithmeticException();
        }

        int[][] copia = new int[this.altura()][this.largura()];
        for (int i = 0; i <= this.altura() - 1; i++) {
            for (int j = 0; j <= this.largura() - 1; j++) {
                copia[i][j] = this.pixels[i][j];
            }
        }
        int[] n = new int[tamanho * tamanho];
        int moda = 0;
        for (int i = 0; i <= copia.length - 1; i++) {
            for (int j = 0; j <= copia[0].length - 1; j++) {
                for (int k = 0; k < tamanho; k++) {
                    for (int h = 0; h < tamanho; h++) {
                        if (i - tamanho / 2 + k < 0 || j - tamanho / 2 + h < 0 || i - tamanho / 2 + k > copia.length - 1 || j - tamanho / 2 + h > copia[0].length - 1) {
                            n[k * tamanho + h] = 256;
                        } else {
                            n[k * tamanho + h] = copia[i - tamanho / 2 + k][j - tamanho / 2 + h];

                        }
                    }
                }
                moda = Localizar_Moda(n);
                pixels[i][j] = (int) moda;
            }
        }
    }

    public int[][] getPixels() {
        return pixels;
    }

    public void setPixels(int[][] pixels) {
        this.pixels = pixels;
    }

    public void detectorBorda() {

    }

}
