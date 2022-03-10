/*
 * Clase para las imagenes, sonidos y otros recursos
 * Metodos estaticos
 */
package graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Juan David Rivera
 */
public class Loader {
    // BufferedImage Es la forma en la que llava guarda imagenes en memoria
    public static BufferedImage ImageLoader(String path){ 
        try {
            return ImageIO.read(Loader.class.getResource(path));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
}
