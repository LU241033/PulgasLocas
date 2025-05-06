
package autonoma.PulgasLocasBase.elements;

/**
 *
 * @author Luisa Fernanda Henao
 */
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que implementa la interfaz {@link Lector} para leer datos desde archivos de texto plano.
 * Esta clase permite leer archivos línea por línea.
 * 
 * @author Usuario
 * @version 1.0
 */
public class LectorArchivoTextoPlano implements Lector {
    
    /**
     * Lee todas las líneas de un archivo de texto plano.
     * 
     * @param ruta Ruta del archivo que se desea leer.
     * @return Lista de líneas de texto contenidas en el archivo.
     * @throws IOException Si ocurre un error al acceder o leer el archivo.
     */
    @Override
    public ArrayList<String> leer(String ruta) throws IOException {
        List<String> lineas = Files.readAllLines(Paths.get(ruta));
        return new ArrayList<>(lineas);
    }
    
    /**
     * Lee el puntaje más alto almacenado en un archivo.
     * 
     * @param ruta Ruta del archivo que contiene el puntaje.
     * @return El puntaje más alto como un entero.
     * @throws IOException Si ocurre un error al acceder o leer el archivo.
     */
    public int leerPuntajeAlto(String ruta) throws IOException {
        try {
            List<String> lineas = Files.readAllLines(Paths.get(ruta));
            if (lineas.isEmpty()) {
                return 0;
            }
            return Integer.parseInt(lineas.get(0));
        } catch (Exception e) {
            return 0;
        }
    }
}