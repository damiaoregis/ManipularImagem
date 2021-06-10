package verimagens;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

/**
 *
 * @author damiao regis
 */
public class Imagem {

   
    private BufferedImage img = null;
    private File f = null;

  

    public void salvaArq(String resultado) {

        try {
            f = new File("C:\\Users\\damia\\Downloads\\" + resultado + ".jpg");
            ImageIO.write(img, "JPG", f);
        } catch (IOException e) {
            System.out.println(e);
        }

    }

    public BufferedImage getImg() {
        return img;
    }

    public void setImg(BufferedImage img) {
        this.img = img;
    }

    public File getF() {
        return f;
    }

    public void setF(JFileChooser arquivoSeletor) {
        try {
            img = ImageIO.read(arquivoSeletor.getSelectedFile());
        } catch (IOException e) {
            System.out.println(e);
        }
    }

  
}
