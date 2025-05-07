package autonoma.PulgasLocas.ui;

import autonoma.PulgasLocas.elements.CampoBatalla;
import autonoma.PulgasLocasBase.elements.GraphicContainer;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Clase que representa la ventana principal del juego "Pulgas Locas".
 * Esta clase extiende de {@link javax.swing.JFrame} e implementa la interfaz {@link GraphicContainer}
 * para manejar la visualización gráfica y los eventos de usuario como teclas y clics.
 * 
 * @author jgiugtiñut
 * @since 05/05/2025
 * @version 1.0.0
 */
public class PulgasLocasWindow extends javax.swing.JFrame implements GraphicContainer {

    // Campo de batalla donde se desarrolla la lógica del juego
    protected CampoBatalla campoBatalla;

    /**
     * Constructor de la clase que inicializa la ventana del juego.
     * Configura el tamaño, comportamiento y eventos de la ventana.
     */
    public PulgasLocasWindow() {
        super("Pulgas Locas");

        setSize(800, 600);  // Establece el tamaño de la ventana
        setDefaultCloseOperation(EXIT_ON_CLOSE);  // Cierra la aplicación al cerrar la ventana
        setLocationRelativeTo(null);  // Centra la ventana en la pantalla
        setResizable(false);  // Deshabilita el redimensionado de la ventana

        // Configura el manejador de clics del ratón
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mousePresionado(e);  // Llama a la función que maneja los clics
            }
        });

        // Configura el manejador de teclas
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                formKeyPressed(evt);  // Llama a la función que maneja las teclas presionadas
            }
        });
    }

    /**
     * Este método se llama al presionar una tecla.
     * Se verifica si la tecla presionada corresponde a un comando específico.
     * 
     * @param evt Evento de la tecla presionada.
     */
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
     // Sale del juego si se presiona la tecla 'Q'
        if (evt.getKeyCode() == KeyEvent.VK_Q) {
            System.exit(0);  // Termina el juego

        }

        // Si el campo de batalla no es nulo, verifica qué tecla fue presionada y la maneja
        if (campoBatalla != null &&
            (evt.getKeyCode() == KeyEvent.VK_SPACE ||
             evt.getKeyCode() == KeyEvent.VK_M ||
             evt.getKeyCode() == KeyEvent.VK_S || 
             evt.getKeyCode() == KeyEvent.VK_P ||
             evt.getKeyCode() == KeyEvent.VK_R)) {

            // Pasa la tecla presionada al campo de batalla para su manejo
            campoBatalla.keyPressed(evt.getKeyCode());
        }
    
    /**
     * Este método es llamado al hacer clic en la ventana.
     * 
     * @param e Evento del clic del ratón.
     */
    
    }//GEN-LAST:event_formKeyPressed
private void mousePresionado(MouseEvent e) {
        // Llama al método disparoPistola con las coordenadas del clic
        this.campoBatalla.disparoPistola(e.getX(), e.getY());
    }

    /**
     * Asigna el campo de batalla en el que se juega.
     * 
     * @param campoBatalla El campo de batalla donde se desarrollará el juego.
     */
    public void setCampoBatalla(CampoBatalla campoBatalla) {
        this.campoBatalla = campoBatalla;
    }

    /**
     * Dibuja en la ventana utilizando el objeto {@link Graphics}.
     * Si el campo de batalla no es nulo, llama al método {@code paint} del campo de batalla.
     * 
     * @param g Objeto {@link Graphics} para pintar sobre la ventana.
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (campoBatalla != null) {
            campoBatalla.paint(g);  // Dibuja el campo de batalla
        }
    }

    /**
     * Refresca la ventana llamando a {@code repaint} para redibujar el contenido.
     */
    @Override
    public void refresh() {
        repaint();  // Solicita que la ventana sea redibujada
    }

    /**
     * Obtiene los límites de la ventana de la interfaz gráfica.
     * 
     * @return Un objeto {@link Rectangle} que define los límites de la ventana.
     */
    @Override
    public Rectangle getBoundaries() {
        return getBounds();  // Devuelve los límites de la ventana
    
}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
