/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verimagens;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

/**
 *
 * @author damiao regis
 */
public class ManipImagem {

    int t = 0;

    private Imagem imagem = new Imagem();
    private Imagem image = new Imagem();

    public ManipImagem(Imagem imagem) {
        this.imagem = imagem;
    }

    public BufferedImage bandaR(BufferedImage imgEntrada) {
        BufferedImage imgSaida = new BufferedImage(imgEntrada.getWidth(), imgEntrada.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < imgEntrada.getHeight(); x++) {
            for (int y = 0; y < imgEntrada.getWidth(); y++) {
                int rgb = imgEntrada.getRGB(x, y);
                Color c = new Color(rgb);
                int red = c.getRed();
                Color newC = new Color(red, 0, 0);
                imgSaida.setRGB(x, y, newC.getRGB());
            }
        }
        return imgSaida;

    }

    public BufferedImage bandaG(BufferedImage imgEntrada) {
        BufferedImage imgSaida = new BufferedImage(imgEntrada.getWidth(), imgEntrada.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < imgEntrada.getHeight(); x++) {
            for (int y = 0; y < imgEntrada.getWidth(); y++) {
                int rgb = imgEntrada.getRGB(x ,y);
                Color c = new Color(rgb);
                int green = c.getGreen();
                Color newC = new Color(0, green, 0);
                imgSaida.setRGB(x,y, newC.getRGB());
            }
        }
        return imgSaida;
    }

    public BufferedImage bandaB(BufferedImage imgEntrada) {
        BufferedImage imgSaida = new BufferedImage(imgEntrada.getWidth(), imgEntrada.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < imgEntrada.getHeight(); x++) {
            for (int y = 0; y < imgEntrada.getWidth(); y++) {
                int rgb = imgEntrada.getRGB(x,y);
                Color c = new Color(rgb);
                int blue = c.getBlue();
                Color newC = new Color(0, 0, blue);
                imgSaida.setRGB(x,y, newC.getRGB());
            }
        }
        return imgSaida;
    }

    public float[][][] ConvertRGBYIQ(BufferedImage imgEntrada) {

        int altura = imgEntrada.getHeight();
        int largura = imgEntrada.getWidth();
        float[][][] matriz = new float[largura][altura][3];
        for (int x = 0; x < altura; x++) {
            for (int y = 0; y < largura; y++) {
                int rgb = imgEntrada.getRGB(x,y);
                Color c = new Color(rgb);
                int r = (int) c.getRed();
                int g = (int) c.getGreen();
                int b = (int) c.getBlue();

                float Y = (float) ((0.299 * r) + (0.587 * g) + (0.114 * b));
                float I = (float) ((0.596 * r) - (0.274 * g) - (0.322 * b));
                float Q = (float) ((0.211 * r) - (0.523 * g) + (0.312 * b));

                matriz[x][y][0] = Y;
                matriz[x][y][1] = I;
                matriz[x][y][2] = Q;
            }
        }
        return matriz;
    }

    public BufferedImage ConvertYuvRgb(float[][][] matriz) {

        BufferedImage imgSaida = new BufferedImage(matriz.length, matriz.length, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {

                float red = (float) ((1.000 * matriz[i][j][0]) + (0.956 * matriz[i][j][1]) + (0.621 * matriz[i][j][2]));
                float green = (float) ((1.000 * matriz[i][j][0]) - (0.272 * matriz[i][j][1]) - (0.647 * matriz[i][j][2]));
                float blue = (float) ((1.000 * matriz[i][j][0]) - (1.106 * matriz[i][j][1]) + (1.703 * matriz[i][j][2]));

                if (red < 0) {
                    red = 0;
                }
                if (red > 255) {
                    red = 255;
                }
                if (green < 0) {
                    green = 0;
                }
                if (green > 255) {
                    green = 255;
                }
                if (blue < 0) {
                    blue = 0;
                }
                if (blue > 255) {
                    blue = 255;
                }

                Color newC = new Color((int) red, (int) green, (int) blue);
                imgSaida.setRGB(i, j, newC.getRGB());
            }
        }
        return imgSaida;
    }

    //public int escalaCinza(int valor) { }
    public BufferedImage pretoBranco(BufferedImage imgEntrada) {
        BufferedImage imgSaida = new BufferedImage(imgEntrada.getWidth(), imgEntrada.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < imgEntrada.getHeight(); x++) {
            for (int y = 0; y < imgEntrada.getWidth(); y++) {
                int rgb = imgEntrada.getRGB(x,y);
                Color c = new Color(rgb);
                int red = c.getRed();
                Color newC = new Color(red, red, red);
                imgSaida.setRGB(x,y, newC.getRGB());
            }
        }
        return imgSaida;
    }

    public BufferedImage pretoBranco2(BufferedImage imgEntrada) {
        BufferedImage imgSaida = new BufferedImage(imgEntrada.getWidth(), imgEntrada.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < imgEntrada.getHeight(); x++) {
            for (int y = 0; y < imgEntrada.getWidth(); y++) {
                int rgb = imgEntrada.getRGB(x,y);
                Color c = new Color(rgb);
                int green = c.getGreen();
                Color newC = new Color(green, green, green);
                imgSaida.setRGB(x,y, newC.getRGB());
            }
        }
        return imgSaida;
    }

    public BufferedImage pretoBranco3(BufferedImage imgEntrada) {
        BufferedImage imgSaida = new BufferedImage(imgEntrada.getWidth(), imgEntrada.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < imgEntrada.getHeight(); x++) {
            for (int y = 0; y < imgEntrada.getWidth(); y++) {
            
                int rgb = imgEntrada.getRGB(x,y);
                Color c = new Color(rgb);
                int blue = c.getBlue();
                Color newC = new Color(blue, blue, blue);
                imgSaida.setRGB(x,y, newC.getRGB());
            }
        }
        return imgSaida;
    }

    public BufferedImage pretoBranco4(BufferedImage imgEntrada) {
        BufferedImage imgSaida = new BufferedImage(imgEntrada.getWidth(), imgEntrada.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < imgEntrada.getHeight(); x++) {
            for (int y = 0; y < imgEntrada.getWidth(); y++) {
            
                int rgb = imgEntrada.getRGB(x,y);
                Color c = new Color(rgb);
                int red = c.getRed();
                int green = c.getGreen();
                int blue = c.getBlue();
                int media = (red + green + blue) / 3;
                Color newC = new Color(media, media, media);
                imgSaida.setRGB(x,y, newC.getRGB());
            }
        }
        return imgSaida;
    }

    public BufferedImage negativoRGB(BufferedImage imgEntrada) {
        BufferedImage imgSaida = new BufferedImage(imgEntrada.getWidth(), imgEntrada.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < imgEntrada.getHeight(); x++) {
            for (int y = 0; y < imgEntrada.getWidth(); y++) {
                int RGB = (imgEntrada.getRGB(x,y));
                Color c = new Color(RGB);
                int red = (255 - (int) c.getRed());
                int green = (255 - (int) c.getGreen());
                int blue = (255 - (int) c.getBlue());
                Color newC = new Color(red, green, blue);
                imgSaida.setRGB(x,y, newC.getRGB());

            }
        }
        return imgSaida;
    }

    public BufferedImage negativoY(BufferedImage imgEntrada) {
        BufferedImage imgSaida;
        float[][][] matriz = new float[imgEntrada.getHeight()][imgEntrada.getWidth()][3];
        matriz = ConvertRGBYIQ(imgEntrada);
        for (int i = 0; i < imgEntrada.getWidth(); i++) {
            for (int j = 0; j < imgEntrada.getHeight(); j++) {
                matriz[i][j][0] = 255 - matriz[i][j][0];
            }
        }
        imgSaida = ConvertYuvRgb(matriz);
        return imgSaida;
    }

    public BufferedImage sobreporImagem(BufferedImage fundo, BufferedImage imagem) {

        BufferedImage imgfinal = new BufferedImage(fundo.getHeight(), fundo.getWidth(), BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < fundo.getWidth(); x++) {
            for (int y = 0; y < fundo.getHeight(); y++) {
            
                int pixel = (fundo.getRGB(x,y));//pego os pixels da primera imagem
                Color c = new Color(pixel);
                int sobrepor = (imagem.getRGB(x,y));
                Color c2 = new Color(sobrepor);
                int red = (c.getRed() + c2.getRed()) / 2;
                int green = (c.getGreen() + c2.getGreen()) / 2;
                int blue = (c.getBlue() + c2.getBlue()) / 2;
                Color newC = new Color(red, green, blue);
                imgfinal.setRGB(x,y, newC.getRGB());

            }
        }
        return imgfinal;
    }

    public BufferedImage controleBrilhoAditivo(BufferedImage imgEntrada, float brilho) {

        BufferedImage imgSaida = new BufferedImage(imgEntrada.getWidth(), imgEntrada.getHeight(), BufferedImage.TYPE_INT_RGB);
        float[][][] matriz = new float[imgEntrada.getHeight()][imgEntrada.getWidth()][3];
        matriz = ConvertRGBYIQ(imgEntrada);
        for (int i = 0; i < imgEntrada.getWidth(); i++) {
            for (int j = 0; j < imgEntrada.getHeight(); j++) {
                matriz[i][j][0] += brilho;
            }
        }
        imgSaida = ConvertYuvRgb(matriz);
        return imgSaida;
    }

    public BufferedImage controleBrilhoMultiplicativo(BufferedImage imgEntrada, float brilho) {

        BufferedImage imgSaida;
        float[][][] matriz;
        matriz = ConvertRGBYIQ(imgEntrada);
        for (int i = 0; i < imgEntrada.getWidth(); i++) {
            for (int j = 0; j < imgEntrada.getHeight(); j++) {
                matriz[i][j][0] *= brilho;
            }
        }
        imgSaida = ConvertYuvRgb(matriz);
        return imgSaida;
    }

    public int[][] entradaDosFiltrosDeRuidos(BufferedImage imgEntrada) {
        int altura = imgEntrada.getHeight();
        int largura = imgEntrada.getWidth();
        int[][] matrizRGB = new int[largura][altura];
        int imagem[][][] = new int[largura][altura][3];
        for (int i = 0; i < largura; i++) {
            for (int j = 0; j < altura; j++) {
                matrizRGB[i][j] = imgEntrada.getRGB(i, j);

            }
        }
        return matrizRGB;
    }

     public BufferedImage retornoDosFiltros(BufferedImage image, int window, int opType) {
    	/*
    	 * 1: media
    	 * 2: mediana
    	 * 3: moda
    	 * 4: gaussiano
    	 * 
    	 * */
    	if(opType == 1) {
    	BufferedImage newImg = new BufferedImage(image.getWidth(),image.getHeight(),1);
            for(int i = 0; i < image.getWidth(); i++){
                for(int j = 0; j < image.getHeight(); j++ ){
                    newImg.setRGB(i, j, getMedia(image, window, i, j));
                }
            }
            return newImg;
    	}
    	else if(opType == 2) {
   	BufferedImage newImg = new BufferedImage(image.getWidth(),image.getHeight(),1);
            for(int i = 0; i < image.getWidth(); i++){
                for(int j = 0; j < image.getHeight(); j++ ){
                    newImg.setRGB(i, j, getMediana(image, window, i, j));
                }
            }
            return newImg;
    	}
    	else if(opType == 3) {
        BufferedImage newImg = new BufferedImage(image.getWidth(),image.getHeight(),1);
            for(int i = 0; i < image.getWidth(); i++){
                for(int j = 0; j < image.getHeight(); j++ ){
                    newImg.setRGB(i, j, Moda(image, window, i, j));
                }
            }
            return newImg;
    	}
    	else if(opType == 4) {
    	BufferedImage newImg = new BufferedImage(image.getWidth(),image.getHeight(),1);
            for(int i = 0; i < image.getWidth(); i++){
                for(int j = 0; j < image.getHeight(); j++ ){
                    newImg.setRGB(i, j,this.getGausino(image, i, j));
                }
            }
            return newImg;
    	}else if(opType == 5) {
    	BufferedImage newImg = new BufferedImage(image.getWidth(),image.getHeight(),1);
            for(int i = 0; i < image.getWidth(); i++){
                for(int j = 0; j < image.getHeight(); j++ ){
                    newImg.setRGB(i, j, getRgbResultEdgeDetec(image, i, j, 1));
                }
            }
            return newImg;
    	}else if(opType == 6) {
    	BufferedImage newImg = new BufferedImage(image.getWidth(),image.getHeight(),1);
            for(int i = 0; i < image.getWidth(); i++){
                for(int j = 0; j < image.getHeight(); j++ ){
                    newImg.setRGB(i, j, getRgbResultEdgeDetec(image, i, j, 2));
                }
            }
            return newImg;
    	}else if(opType == 7) {
    	BufferedImage newImg = new BufferedImage(image.getWidth(),image.getHeight(),1);
            for(int i = 0; i < image.getWidth(); i++){
                for(int j = 0; j < image.getHeight(); j++ ){
                    newImg.setRGB(i, j, getRgbResultEdgeDetec(image, i, j, 3));
                }
            }
            return newImg;
    	}else {
    		return null;
    	}
    	
    }
    public int getMedia(BufferedImage imgEntrada, int tamanho, int i, int j) {
        ArrayList<Integer> valuesR = new ArrayList<Integer>();
        ArrayList<Integer> valuesG = new ArrayList<Integer>();
        ArrayList<Integer> valuesB = new ArrayList<Integer>();

        int sumR = 0;
        int sumG = 0;
        int sumB = 0;

        int alt = imgEntrada.getHeight();
        int larg = imgEntrada.getWidth();

        for (int x = i - (tamanho / 2), tx = 0; tx < tamanho; tx++, x++) {
            for (int y = j - (tamanho / 2), ty = 0; ty < tamanho; ty++, y++) {
                if (x >= 0 && y >= 0 && x < larg && y < alt) {

                    int rgb = imgEntrada.getRGB(x, y);
                    Color c = new Color(rgb);

                    valuesR.add(c.getRed());
                    valuesG.add(c.getGreen());
                    valuesB.add(c.getBlue());

                    sumR = (sumR + c.getRed());
                    sumG = (sumG + c.getGreen());
                    sumB = (sumB + c.getBlue());
                } else {
                    valuesR.add(0);
                    valuesG.add(0);
                    valuesB.add(0);

                }
            }
        }
        return new Color(sumR / valuesR.size(), sumG / valuesG.size(), sumB / valuesB.size()).getRGB();
    }

    public int getMediana(BufferedImage imgEntrada, int tamanho, int i, int j) {
        ArrayList<Integer> valuesR = new ArrayList<Integer>();
        ArrayList<Integer> valuesG = new ArrayList<Integer>();
        ArrayList<Integer> valuesB = new ArrayList<Integer>();
        int alt = imgEntrada.getHeight();
        int larg = imgEntrada.getWidth();

        for (int x = i - (tamanho / 2), tx = 0; tx < tamanho; tx++, x++) {
            for (int y = j - (tamanho / 2), ty = 0; ty < tamanho; ty++, y++) {

                if (x >= 0 && y >= 0 && x < larg && y < alt) {

                    int rgb = imgEntrada.getRGB(x, y);
                    Color c = new Color(rgb);

                    valuesR.add(c.getRed());
                    valuesG.add(c.getGreen());
                    valuesB.add(c.getBlue());

                } else {
                    valuesR.add(0);
                    valuesG.add(0);
                    valuesB.add(0);
                }
            }
        }
        Collections.sort(valuesR);
        Collections.sort(valuesG);
        Collections.sort(valuesB);
        return new Color(valuesR.get(valuesR.size() / 2), valuesG.get(valuesG.size() / 2), valuesB.get(valuesB.size() / 2)).getRGB();
    }

    public int Moda(BufferedImage imgEntrada, int tamanho, int i, int j) {
        ArrayList<Integer> valuesR = new ArrayList<Integer>();
        ArrayList<Integer> valuesG = new ArrayList<Integer>();
        ArrayList<Integer> valuesB = new ArrayList<Integer>();
        int alt = imgEntrada.getHeight();
        int larg = imgEntrada.getWidth();

        for (int x = i - (tamanho / 2), tx = 0; tx < tamanho; tx++, x++) {
            for (int y = j - (tamanho / 2), ty = 0; ty < tamanho; ty++, y++) {

                if (x >= 0 && y >= 0 && x < larg && y < alt) {

                    int rgb = imgEntrada.getRGB(x, y);
                    Color c = new Color(rgb);

                    valuesR.add(c.getRed());
                    valuesG.add(c.getGreen());
                    valuesB.add(c.getBlue());

                } else {
                    valuesR.add(0);
                    valuesG.add(0);
                    valuesB.add(0);
                }
            }
        }
        return new Color(getModa(valuesR), getModa(valuesG), getModa(valuesB)).getRGB();

    }

    private int getModa(ArrayList<Integer> m) {
        int higherOccur = 0;
        int previousCont = 0;
        for (int i = 0; i < m.size(); i++) {
            int cont = 0;
            for (int j = 0; j < m.size(); j++) {

                if (Objects.equals(m.get(i), m.get(j))) {
                    cont++;
                }
            }

            if (cont > previousCont) {
                higherOccur = i;
            }
            previousCont = cont;
            cont = 0;
        }
        return m.get(higherOccur);
    }

    public int getGausino(BufferedImage imgEntrada, int i, int j) {
        double valoresGaus[][] = new double[3][3];
        valoresGaus[0][0] = 0.0625;
        valoresGaus[0][1] = 0.125;
        valoresGaus[0][2] = 0.0625;

        valoresGaus[1][0] = 0.125;
        valoresGaus[1][1] = 0.25;
        valoresGaus[1][2] = 0.125;

        valoresGaus[2][0] = 0.0625;
        valoresGaus[2][1] = 0.125;
        valoresGaus[2][2] = 0.0625;

        double vR = 0;
        double vG = 0;
        double vB = 0;

        int alt = imgEntrada.getHeight();
        int larg = imgEntrada.getWidth();

        for (int x = i - (3 / 2), tx = 0; tx < 3; tx++, x++) {
            for (int y = j - (3 / 2), ty = 0; ty < 3; ty++, y++) {

                if (x >= 0 && y >= 0 && x < larg && y < alt) {
                    int rgb = imgEntrada.getRGB(x, y);
                    Color c = new Color(rgb);
                    vR = vR + (valoresGaus[tx][ty] * c.getRed());
                    vG = vG + (valoresGaus[tx][ty] * c.getGreen());
                    vB = vB + (valoresGaus[tx][ty] * c.getBlue());
                }
            }
        }

        return new Color((int) vR, (int) vG, (int) vB).getRGB();
    }

    private int getRgbResultEdgeDetec(BufferedImage image, int i, int j, int opFilter) {

        double filter[][] = new double[3][3];
        if (opFilter == 1) {
            filter[0][0] = 1;
            filter[0][1] = 1;
            filter[0][2] = 1;
            filter[1][0] = 0;
            filter[1][1] = 0;
            filter[1][2] = 0;
            filter[2][0] = -1;
            filter[2][1] = -1;
            filter[2][2] = -1;
        } else if (opFilter == 2) {
            filter[0][0] = 1;
            filter[0][1] = 0;
            filter[0][2] = -1;
            filter[1][0] = 1;
            filter[1][1] = 0;
            filter[1][2] = -1;
            filter[2][0] = 1;
            filter[2][1] = 0;
            filter[2][2] = -1;
        } else {
            filter[0][0] = -1;
            filter[0][1] = -1;
            filter[0][2] = -1;
            filter[1][0] = -1;
            filter[1][1] = 8;
            filter[1][2] = -1;
            filter[2][0] = -1;
            filter[2][1] = -1;
            filter[2][2] = -1;
        }

        double vR = 1;
        double vG = 1;
        double vB = 1;

        for (int x = i - (3 / 2), windowX = 0; windowX < 3; windowX++, x++) {

            for (int y = j - (3 / 2), windowY = 0; windowY < 3; windowY++, y++) {

                if (x >= 0 && y >= 0 && x < image.getWidth() && y < image.getHeight()) {

                    int rgb = image.getRGB(x, y);
                    Color c = new Color(rgb);
                    vR = vR + (filter[windowX][windowY] * c.getRed());
                    vG = vG + (filter[windowX][windowY] * c.getGreen());
                    vB = vB + (filter[windowX][windowY] * c.getBlue());
                }
            }
        }

        vR = (vR > 255 ? 255 : vR);
        vG = (vG > 255 ? 255 : vG);
        vB = (vB > 255 ? 255 : vB);

        vR = (vR < 0 ? 0 : vR);
        vG = (vR < 0 ? 0 : vR);
        vB = (vR < 0 ? 0 : vR);
        return new Color((int) vR, (int) vG, (int) vB).getRGB();
    }

    public Imagem getImagem() {
        return imagem;
    }

    public void setImagem(Imagem imagem) {
        this.imagem = imagem;
    }
}
