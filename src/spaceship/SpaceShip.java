/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceship;

import graphics.Assets;
import java.awt.Canvas;
import java.awt.Color;
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
    private Canvas canvas; // Objeto para permitir dibujar
    private Thread thread;// Creacion de un hilo aparte del principal
    private boolean running = false; // controlar el estado del juego
    private BufferStrategy bs;
    private Graphics g;
    private final int fps = 60; //Constante para fotograma por segundo
    private double target_time = 1000000000/fps;  // Tiempo de el fotograma por nanosegundos
    private double delta = 0; // Acumulador temporar para el tiempo que vaya pasando
    private int average_fps = fps; // variable para saber a cuanto esta correndo el juego
    
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
    
    //int x = 0;
    private void update(){ //Actualizar
        //x++;
    }
    
    private void draw(){ // Dibujar
        bs = canvas.getBufferStrategy();
        
        if(bs == null){
            canvas.createBufferStrategy(3); //Numero de buffers
            return;
        }
        
        g = bs.getDrawGraphics();
        
        //---------------------------------
        
        //g.clearRect(0, 0, getWidth(), getHeight());
        
        //g.drawRect(x, 0, 100, 100); // es para dibujar un rectangulo
        
        g.setColor(Color.black);
        
        g.fillRect(0, 0, getWidth(), getHeight());
        
        g.drawImage(Assets.player, 100, 100, null);
        
        g.setColor(Color.blue);
        
        g.drawString(""+average_fps, 10, 10);
        //---------------------------------
        
        g.dispose();
        bs.show();
    }
    
    private void init(){ //iniciar metodo estativo init
        Assets.init();
    }

    @Override
    public void run() {
        
        long now = 0;
        long lastTime = System.nanoTime(); // Nos da el tiempo en nanosegundos
        int frames = 0; // variables para mostrar fotogramas por segundo
        long time = 0;
        init();
        // Ciclo principal que se encargara de actualizar la posicion de todos
        // los objetos del juego y dibujar una y potra vez
        while(running){
            now = System.nanoTime();
            delta +=(now - lastTime)/ target_time; //obtener el tiempo que a pasado
            time += (now - lastTime);
            lastTime = now;
            
            if(delta >= 1){
                update();
                draw();
                delta--;
                frames ++;
            }
            if(time >= 1000000000){ 
                average_fps = frames;
                frames = 0;
                time = 0;
            }
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
