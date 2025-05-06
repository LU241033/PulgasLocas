
package autonoma.PulgasLocasBase.elements;

/**
 *
 * @author Luisa Fernanda Henao Posada
 * @since 
 * @version
 * 
 */
import java.io.IOException;
import java.util.ArrayList;

/**
 * Interfaz que define el contrato para leer datos desde una fuente específica.
 */
public interface Lector {
    /**
     * Lee datos desde la ruta especificada.
     * 
     * @param ruta Ruta de la fuente de datos.
     * @return Lista de líneas leídas.
     * @throws IOException Si ocurre un error durante la lectura.
     */
    public abstract ArrayList<String> leer(String ruta) throws IOException;
}