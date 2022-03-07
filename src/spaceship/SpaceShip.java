/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceship;

import javax.swing.JFrame;

/**
 *
 * @author Juan David Rivera
 */
public class SpaceShip extends JFrame{ // extends JFrame para utilizar java swing
    
    //Variables estaticas para la dimension de la ventana
    public static final int width_window = 800, height_window = 600;

    public SpaceShip(){ //Constructor 
        setTitle("Space Ship Game"); // Titulo de la ventana
        setSize(width_window, height_window); // Tamaño de la pantalla
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Permite cerrar la ventana
        setResizable(false); // para que la ventana no se pueda redimendionar
        setLocationRelativeTo(null); // Hace que la pantalla se despliegue en el centro
        setVisible(true); // Mostrar la ventana
    }
    public static void main(String[] args) {
        new SpaceShip();
    }
    
}
