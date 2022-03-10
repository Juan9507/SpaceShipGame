/*
 * Recursos internos
 * 
 */
package graphics;

import java.awt.image.BufferedImage;

/**
 *
 * @author Juan David Rivera
 */
public class Assets {
    
    public static BufferedImage player;
    
    public static void init(){
        player = Loader.ImageLoader("/ships/player1.png");
    }
}
