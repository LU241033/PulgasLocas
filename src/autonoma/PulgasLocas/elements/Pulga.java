
package autonoma.PulgasLocas.elements;

import autonoma.PulgasLocasBase.elements.Sprite;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;
import javax.swing.ImageIcon;

/**
 * @author Gilary
 * @version 1.0
 * @since 06/05/2025
 */

/**
 * Clase base para representar las pulgas en el juego
 */
public abstract class Pulga extends Sprite {
    protected ImageIcon imagenPulga;
    protected boolean isAlive = true;
    protected static Random random = new Random();
    
    public Pulga(int x, int y, int height, int width) {
        super(x, y, height, width);
    }
    
    /**
     * Método para recibir el impacto de un disparo
     * @return true si la pulga muere, false en caso contrario
     */
    public abstract boolean recibirImpacto();
    
    /**
     * Método para hacer saltar la pulga a una posición aleatoria
     * @param boundaries límites del campo de batalla
     */
    public void saltar(Rectangle boundaries) {
        int newX = random.nextInt((int)boundaries.getWidth() - width);
        int newY = random.nextInt((int)boundaries.getHeight() - height);
        this.x = newX;
        this.y = newY;
    }
    
    public boolean isAlive() {
        return isAlive;
    }
    
    @Override
    public abstract void paint(Graphics g);
}
