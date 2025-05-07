/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package autonoma.PulgasLocas.ui;

import autonoma.PulgasLocas.elements.CampoBatalla;
import autonoma.PulgasLocasBase.elements.GraphicContainer;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author 
 */
import javax.swing.JFrame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Clase que representa la ventana principal del juego Pulgas Locas.
 * Extiende JFrame y actúa como contenedor gráfico principal del campo de batalla.
 * Implementa la interfaz {@code GraphicContainer}, que permite la interacción
 * con los elementos gráficos del juego.
 * 
 * Esta ventana configura las propiedades básicas del JFrame como título,
 * tamaño, cierre, centrado, y escucha eventos del teclado para controlar el juego.
 * 
 * @author Alejandra
 */
public class PulgasLocasWindow extends javax.swing.JFrame implements GraphicContainer {

    /**
     * Campo de batalla donde se desarrolla el juego.
     * Se inicializa posteriormente y es el área donde se dibujan los sprites.
     */
    protected CampoBatalla campoBatalla;

    /**
     * Constructor de la clase PulgasLocasWindow.
     * Inicializa y configura la ventana principal del juego, 
     * definiendo su tamaño, comportamiento al cerrar, ubicación y eventos clave.
     */
    public PulgasLocasWindow() {
        super("Pulgas Locas"); // Título de la ventana

        // Configura las propiedades de la ventana
        setSize(800, 600); // Tamaño fijo de la ventana
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Cierra la aplicación al cerrar la ventana
        setLocationRelativeTo(null); // Centra la ventana en la pantalla
        setResizable(false); // No permite redimensionar la ventana

        // Agrega un KeyListener para capturar eventos de teclado
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                formKeyPressed(evt); // Método personalizado para manejar teclas presionadas
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
    /**
     * Método que maneja los eventos de teclado cuando una tecla es presionada.
     * <ul>
     *   <li>Si se presiona la tecla <b>Q</b>, se cierra inmediatamente la aplicación.</li>
     *   <li>Si el campo de batalla ha sido inicializado y se presiona la barra espaciadora (SPACE),
     *       la tecla <b>M</b> o la tecla <b>R</b>, el evento se pasa al método {@code keyPressed} 
     *       de la clase {@code CampoBatalla} para que sea procesado dentro del juego.</li>
     * </ul>
     *
     * @param evt Evento de teclado capturado
     */
        if (evt.getKeyCode() == KeyEvent.VK_Q) {
            System.exit(0);
        }

        // Envía los eventos de teclas relevantes al campo de batalla
        if (campoBatalla != null &&
            (evt.getKeyCode() == KeyEvent.VK_SPACE ||
             evt.getKeyCode() == KeyEvent.VK_M ||
             evt.getKeyCode() == KeyEvent.VK_R)) {

            campoBatalla.keyPressed(evt.getKeyCode());
        
    }
     // TODO add your handling code here:
    }//GEN-LAST:event_formKeyPressed
    /**
     * Establece el campo de batalla que se mostrará en esta ventana.
     * Este método permite inyectar la instancia de {@code CampoBatalla}
     * para que pueda ser utilizada en los métodos de dibujo y control.
     *
     * @param campoBatalla Objeto que representa el área del juego
     */
    public void setCampoBatalla(CampoBatalla campoBatalla) {
        this.campoBatalla = campoBatalla;
    }

    /**
     * Método sobrescrito que se encarga de pintar la ventana.
     * Llama al método {@code paint} del campo de batalla si este ha sido definido.
     * Esto asegura que todos los elementos gráficos del juego se dibujen correctamente.
     *
     * @param g Objeto {@code Graphics} que proporciona el contexto gráfico
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g); // Pinta los componentes estándar de la ventana
        if (campoBatalla != null) {
            campoBatalla.paint(g); // Dibuja el contenido del campo de batalla
        }
    }

    /**
     * Método definido por la interfaz {@code GraphicContainer}.
     * Solicita una nueva repintada de la ventana, lo cual actualiza la interfaz gráfica.
     * Se utiliza para refrescar la vista del juego cuando cambian los elementos gráficos.
     */
    @Override
    public void refresh() {
        repaint();
    }

    /**
     * Método definido por la interfaz {@code GraphicContainer}.
     * Devuelve los límites actuales de la ventana como un objeto {@code Rectangle}.
     * Es útil para calcular colisiones o limitar el movimiento de los sprites dentro del área visible.
     *
     * @return Un objeto {@code Rectangle} con los límites de la ventana
     */
    @Override
    public Rectangle getBoundaries() {
        return getBounds();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
