
package autonoma.PulgasLocasBase.elements;

/**
 * @author Luisa Fernanda Henao Posada
 * @since 06-05-2025
 * @version 1.0.0
 */
import java.io.IOException;
import java.util.ArrayList;

/**
 * Interfaz que define el contrato para escribir datos en un destino específico.
 */
public interface Escritor {
    /**
     * Escribe una lista de líneas en el destino especificado.
     * 
     * @param lineas Lista de líneas a escribir.
     * @throws IOException Si ocurre un error durante la escritura.
     */
    public abstract void escribir(ArrayList<String> lineas) throws IOException;
}