package autonoma.PulgasLocasBase.elements;

/**
 ** @author Luisa Fernanda Henao Posada
 * @since 06-05-2025
 * @version 1.0.0
 */
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Esta clase permite escribir una lista de líneas en un archivo especificado. Cada línea de la lista
 * se escribe en una nueva línea del archivo.
 * El archivo se sobrescribe cada vez que se llama al método {@code escribir}.
 */
public class EscritorArchivoTextoPlano implements Escritor {
    private final String rutaArchivo;
    
    /**
     * Constructor que inicializa el escritor con la ruta del archivo especificada.
     * 
     * @param rutaArchivo Ruta del archivo donde se escribirán los datos.
     */
    public EscritorArchivoTextoPlano(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }
    
    /**
     * Escribe una lista de líneas en el archivo de texto plano especificado.
     * 
     * Cada elemento de la lista se escribe como una nueva línea en el archivo.
     * El contenido anterior del archivo será reemplazado.
     * 
     * @param lineas Lista de líneas a escribir en el archivo.
     * @throws IOException Si ocurre un error durante la escritura del archivo.
     */
    @Override
    public void escribir(ArrayList<String> lineas) throws IOException {
        File archivo = new File(this.rutaArchivo);
        try (FileWriter fw = new FileWriter(archivo, false); PrintWriter pw = new PrintWriter(fw)) {
            for (String linea : lineas) {
                pw.println(linea);
            }
        }
    }
    
    /**
     * Guarda un puntaje en el archivo especificado.
     * 
     * @param puntaje Puntaje a guardar.
     * @throws IOException Si ocurre un error durante la escritura del archivo.
     */
    public void guardarPuntaje(int puntaje) throws IOException {
        ArrayList<String> lineas = new ArrayList<>();
        lineas.add(String.valueOf(puntaje));
        escribir(lineas);
    }
}
