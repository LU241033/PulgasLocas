/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package autonoma.PulgasLocas.main;

import autonoma.PulgasLocas.elements.CampoBatalla;
import autonoma.PulgasLocas.ui.PulgasLocasWindow;
import java.awt.Graphics;

/**
 *
 * @author Alejandra Ortega
 */
/**
 /**
 *
 * @author Gilary Rugeles
 */
/**
 /**
 *
 * @author Luisa Henao
 */
/**
 * Clase principal que inicializa y ejecuta el juego Pulgas Locas.
 * Controla la ventana, el campo de batalla y el ciclo principal del juego.
 */
/**
 * Clase que representa el núcleo del juego Pulgas Locas.
 * Se encarga de iniciar el campo de batalla, controlar el ciclo de juego (game loop),
 * y manejar la lógica de actualización y renderizado.
 * 
 * Implementa {@code Runnable} para ejecutar el bucle del juego en un hilo separado.
 */
public class PulgaGame implements Runnable {

    private Thread gameLoopThread;
    PulgasLocasWindow ventana;
    CampoBatalla campo;
    
    // Constantes para control de velocidad del juego
    private final int FPS_SET = 30; // Cuadros por segundo (frames)
    private final int UPS_SET = 40; // Actualizaciones por segundo (updates)

    /**
     * Constructor que inicializa la ventana y el campo de batalla,
     * agrega pulgas al escenario y comienza el ciclo principal del juego.
     */
    public PulgaGame() {
        this.ventana = new PulgasLocasWindow();
        this.campo = new CampoBatalla(ventana);
        ventana.setCampoBatalla(campo);
        initClasses(); // Agrega pulgas normales y mutantes
        this.ventana.requestFocus(); // Asegura captura del teclado
        this.ventana.setVisible(true); // Muestra la ventana del juego
        this.startGameLoop(); // Inicia el ciclo del juego en un hilo separado
    }

    /**
     * Método que se encarga de actualizar la lógica del juego.
     * Actualmente vacío, pero puede usarse para mover objetos, detectar colisiones, etc.
     */
    public void update() {
        // Lógica de actualización del juego (por implementar)
    }

    /**
     * Método encargado del renderizado personalizado del juego.
     * Actualmente vacío porque el pintado se delega a la ventana y al campo de batalla.
     *
     * @param g Objeto Graphics para dibujar
     */
    public void render(Graphics g) {
        // Renderizado personalizado (por implementar si se desea)
    }

    /**
     * Inicia el hilo del juego y llama al método run().
     */
    private void startGameLoop() {
        gameLoopThread = new Thread(this);
        gameLoopThread.start();
    }

    /**
     * Inicializa las clases necesarias para el juego.
     * En este caso, agrega 10 pulgas alternando entre normales y mutantes.
     */
    private void initClasses() {
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                campo.agregarPulgaNormal();
            } else {
                campo.agregarPulgaMutante();
            }
        }
    }

    /**
     * Método principal del ciclo de juego.
     * Controla la frecuencia de actualizaciones (UPS) y de renderizados (FPS).
     * Mide el rendimiento y mantiene constante la velocidad del juego.
     */
    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;
        long previousTime = System.nanoTime();
        int frames = 0;
        int updates = 0;
        double deltaU = 0;
        double deltaF = 0;
        long lastCheck = System.currentTimeMillis();

        while (true) {
            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (deltaU >= 1) {
                update();
                updates++;
                deltaU--;
            }

            if (deltaF >= 1) {
                this.ventana.repaint();
                deltaF--;
                frames++;
            }

            // Muestra FPS y UPS en consola cada segundo
            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }
        public SpawnPulga getSpawner() {
        return spawner;
    }
}

