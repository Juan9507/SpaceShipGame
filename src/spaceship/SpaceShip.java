/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceship;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.SystemColor;
import java.awt.Window;
import java.awt.image.BufferStrategy;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author Juan David Rivera
 */
public class SpaceShip extends JFrame implements Runnable{ // extends JFrame para utilizar java swing
    
    //Variables estaticas para la dimension de la ventana
    public static final int width_window = 800, height_window = 600;
    private Canvas canvas; // Objeto para permiteir dibujar
    private Thread thread;// Creacion de un hilo aparte del principal
    private boolean running = false; // controlar el estado del juego
    private BufferStrategy bs;
    private Graphics g;
    
    public SpaceShip(){ //Constructor 
        setTitle("Space Ship Game"); // Titulo de la ventana
        setSize(width_window, height_window); // Tamaño de la pantalla
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Permite cerrar la ventana
        setResizable(false); // para que la ventana no se pueda redimendionar
        setLocationRelativeTo(null); // Hace que la pantalla se despliegue en el centro
        setVisible(true); // Mostrar la ventana
        canvas = new Canvas(); // La inicializamos
        canvas.setPreferredSize(new Dimension(width_window, height_window)); //redimensionar
        canvas.setMaximumSize(new Dimension(width_window, height_window)); //redimensionar
        canvas.setMinimumSize(new Dimension(width_window, height_window)); //redimensionar
        canvas.setFocusable(true); // Permite recibir entradas por parte del teclado
        
        add(canvas); //Agregar el canvas a la ventana
    }
    public static void main(String[] args) {
        new SpaceShip().start();
    }
    
    int x = 0;
    private void update(){ //Actualizar
        x++;
    }
    
    private void draw(){ // Dibujar
        bs = canvas.getBufferStrategy();
        
        if(bs == null){
            canvas.createBufferStrategy(3); //Numero de buffers
            return;
        }
        
        g = bs.getDrawGraphics();
        
        //---------------------------------
        
        g.clearRect(0, 0, getWidth(), getHeight());
        
        g.drawRect(x, 0, 100, 100); // es para dibujar un rectangulo
        
        //---------------------------------
        
        g.dispose();
        bs.show();
    }

    @Override
    public void run() {
        // Ciclo principal que se encargara de actualizar la posicion de todos
        // los objetos del juego y dibujar una y potra vez
        while(running){
            update();
            draw();
        }
        stop();
    }
    
    private void start(){ //Metodo para iniciar el hilo
        thread = new Thread(this);
        thread.start();
        running = true;
    }
    
    private void stop(){  //Metodo para detener el hilo
        try {
            thread.join();
            running = false;
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
