package autonoma.PulgasLocasBase.elements;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

/**
 * Clase abstracta que representa un objeto gráfico que puede moverse, pintarse y detectar colisiones dentro
 * de un contenedor gráfico.
 * <p>
 * Esta clase proporciona la estructura básica para un "sprite", con propiedades como posición, tamaño y color,
 * así como métodos para manejar colisiones y la interacción con el contenedor gráfico.
 * </p>
 * 
 * @author educacion
 * @since 05/05/2025
 * @version 1.0
 */
public abstract class Sprite {

    protected int x;
    protected int y;
    protected int height;
    protected int width;
    protected Color color;
    protected ImageIcon image;
    protected GraphicContainer gameContainer;

    /**
     * Constructor para crear un nuevo sprite en una posición específica con un tamaño determinado.
     * 
     * @param x Posición en el eje X.
     * @param y Posición en el eje Y.
     * @param height Altura del sprite.
     * @param width Anchura del sprite.
     */
    public Sprite(int x, int y, int height, int width) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
    }

    /**
     * Verifica si el sprite está fuera de los límites del contenedor gráfico.
     * 
     * @return true si el sprite está fuera del contenedor, false en caso contrario.
     */
    public boolean isOutOfGraphicContainer() {
        return isOutOfGraphicContainer(x, y, width, height);
    }

    /**
     * Verifica si un área dada (por coordenadas y tamaño) está fuera de los límites del contenedor gráfico.
     * 
     * @param x Posición en el eje X.
     * @param y Posición en el eje Y.
     * @param width Anchura del área.
     * @param height Altura del área.
     * @return true si el área está fuera del contenedor, false en caso contrario.
     */
    public boolean isOutOfGraphicContainer(int x, int y, int width, int height) {
        if (gameContainer == null)
            return false;
        
        Rectangle bounds = gameContainer.getBoundaries();
        
        return !(x >= bounds.getX() &&
                 y >= bounds.getY() &&
                 x + width <= bounds.getX() + bounds.getWidth() &&
                 y + height <= bounds.getY() + bounds.getHeight());
    }

    /**
     * Verifica si este sprite colisiona con otro sprite.
     * 
     * @param other Otro sprite con el que se verifica la colisión.
     * @return true si hay colisión, false si no la hay.
     */
    public boolean checkCollision(Sprite other) {
        // Colisión en el eje X
        boolean collisionX = this.getX() + this.getWidth() >= other.getX() &&
                             this.getX() < other.getX() + other.getWidth();

        // Colisión en el eje Y
        boolean collisionY = this.getY() + this.getHeight() >= other.getY() &&
                             this.getY() < other.getY() + other.getHeight();

        // Colisión solo si ocurre en ambos ejes
        return collisionX && collisionY;        
    }

    /**
     * Método abstracto para pintar el sprite en el contenedor gráfico.
     * Este método debe ser implementado en las subclases para definir cómo se dibuja el sprite.
     * 
     * @param g El objeto Graphics usado para pintar el sprite.
     */
    public abstract void paint(Graphics g);

    // Métodos de acceso y modificación (getters y setters)
    
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public ImageIcon getImage() {
        return image;
    }

    public void setImage(ImageIcon image) {
        this.image = image;
    }

    public void setGraphicContainer(GraphicContainer gContainer) {
        this.gameContainer = gContainer;
    }
}
