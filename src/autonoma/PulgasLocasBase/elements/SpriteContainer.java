package autonoma.PulgasLocasBase.elements;
import java.util.ArrayList;

/**
 * Clase abstracta que representa un contenedor de sprites, permitiendo agregar, remover
 * y gestionar una colección de objetos `Sprite` dentro de un área específica.
 * <p>
 * Esta clase extiende de {@link Sprite} e implementa {@link GraphicContainer}, permitiendo
 * la gestión de múltiples sprites dentro de un contenedor gráfico.
 * </p>
 * 
 * @author Luisa Fernanada Henao Posada
 * @since 05/05/2025
 * @version 1.0.0
 */
public abstract class SpriteContainer extends Sprite implements GraphicContainer {

    // Lista de sprites que pertenecen a este contenedor.
    protected ArrayList<Sprite> sprites;

    /**
     * Constructor para crear un contenedor de sprites con una posición y tamaño específicos.
     * 
     * @param x Posición en el eje X del contenedor.
     * @param y Posición en el eje Y del contenedor.
     * @param height Altura del contenedor.
     * @param width Anchura del contenedor.
     */
    public SpriteContainer(int x, int y, int height, int width) {
        super(x, y, height, width);
        sprites = new ArrayList<>();
    }

    /**
     * Agrega un sprite al contenedor.
     * 
     * @param sprite El sprite que se agregará al contenedor.
     * @return true si el sprite fue agregado exitosamente, false si no lo fue.
     */
    public boolean add(Sprite sprite) {
        return sprites.add(sprite);
    }

    /**
     * Elimina un sprite del contenedor utilizando su índice.
     * 
     * @param index El índice del sprite que se eliminará.
     */
    public void remove(int index) {
        if (index >= 0 && index < sprites.size()) {
            sprites.remove(index);
        }
    }

    /**
     * Elimina un sprite del contenedor utilizando el propio objeto sprite.
     * 
     * @param sprite El sprite que se eliminará.
     */
    public void remove(Sprite sprite) {
        sprites.remove(sprite);
    }

    /**
     * Obtiene la cantidad de sprites en el contenedor.
     * 
     * @return El número de sprites almacenados en el contenedor.
     */
    public int size() {
        return sprites.size();
    }
}
