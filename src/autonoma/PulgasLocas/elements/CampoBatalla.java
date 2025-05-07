
package autonoma.PulgasLocas.elements;

import autonoma.PulgasLocasBase.elements.EscritorArchivoTextoPlano;
import autonoma.PulgasLocasBase.elements.LectorArchivoTextoPlano;
import autonoma.PulgasLocasBase.elements.Sprite;
import autonoma.PulgasLocasBase.elements.SpriteContainer;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.Collections;
import java.util.Random;
import javax.swing.JFrame;

/**
 * @author Gilary
 * @version 1.0
 * @since 06/05/2025
 */

/**
 * Clase que representa el campo de batalla donde se desarrolla el juego.
 * Administra la lógica del juego como agregar pulgas, disparar, contar puntaje,
 * y dibujar el estado del juego en pantalla.
 */
public class CampoBatalla extends SpriteContainer {
    private Random random;
    private int puntaje = 0;
    private int maxPuntaje = 0;
    private LectorArchivoTextoPlano lector;
    private EscritorArchivoTextoPlano escritor;

    /**
     * Constructor que inicializa el campo de batalla con las dimensiones del JFrame.
     * @param frame Ventana principal del juego.
     */
    public CampoBatalla(JFrame frame) {
        super(0, 0, frame.getHeight(), frame.getWidth());
        random = new Random();
        lector = new LectorArchivoTextoPlano();
        escritor = new EscritorArchivoTextoPlano("maxpuntaje.txt");
        cargarMaximoPuntaje();
    }

    /**
     * Carga el puntaje más alto registrado previamente desde un archivo.
     */
    private void cargarMaximoPuntaje() {
        try {
            maxPuntaje = lector.leerPuntajeAlto("maxpuntaje.txt");
        } catch (Exception e) {
            System.out.println("Error al cargar el máximo puntaje: " + e.getMessage());
        }
    }

    /**
     * Guarda el nuevo puntaje máximo si el puntaje actual lo supera.
     */
    public void guardarMaximoPuntaje() {
        if (puntaje > maxPuntaje) {
            maxPuntaje = puntaje;
            try {
                escritor.guardarPuntaje(maxPuntaje);
            } catch (Exception e) {
                System.out.println("Error al guardar el máximo puntaje: " + e.getMessage());
            }
        }
    }

    /**
     * Agrega una nueva pulga normal al campo sin colisionar con otras.
     */
    public void agregarPulgaNormal() {
        int tamanio = 30;
        int x, y;
        boolean colision;

        do {
            x = random.nextInt(width - tamanio);
            y = random.nextInt(height - tamanio);
            colision = false;
            for (Object obj : sprites) {
                Pulga p = (Pulga) obj;
                if (Math.abs(p.getX() - x) < tamanio && Math.abs(p.getY() - y) < tamanio) {
                    colision = true;
                    break;
                }
            }
        } while (colision);

        PulgaNormal nuevaPulga = new PulgaNormal(x, y, tamanio, tamanio);
        this.add(nuevaPulga);
    }

    /**
     * Agrega una nueva pulga mutante al campo sin colisionar con otras.
     */
    public void agregarPulgaMutante() {
        int tamanio = 30;
        int x, y;
        boolean colision;

        do {
            x = random.nextInt(width - tamanio);
            y = random.nextInt(height - tamanio);
            colision = false;
            for (Object obj : sprites) {
                Pulga p = (Pulga) obj;
                if (Math.abs(p.getX() - x) < tamanio && Math.abs(p.getY() - y) < tamanio) {
                    colision = true;
                    break;
                }
            }
        } while (colision);

        PulgaMutante nuevaPulga = new PulgaMutante(x, y, tamanio, tamanio);
        this.add(nuevaPulga);
    }

    /**
     * Hace que todas las pulgas en el campo salten.
     */
    public void pulgasSaltan() {
        for (Object obj : sprites) {
            Pulga pulga = (Pulga) obj;
            pulga.saltar(this.getBoundaries());
        }
    }

    /**
     * Detecta si un disparo de pistola ha impactado una pulga y actualiza el puntaje.
     * @param x Coordenada X del disparo.
     * @param y Coordenada Y del disparo.
     */
    public void disparoPistola(int x, int y) {
        for (int i = 0; i < sprites.size(); i++) {
            Pulga pulga = (Pulga) sprites.get(i);
            if (x >= pulga.getX() && x <= pulga.getX() + pulga.getWidth() &&
                y >= pulga.getY() && y <= pulga.getY() + pulga.getHeight()) {

                if (pulga instanceof PulgaMutante && !pulga.recibirImpacto()) {
                    PulgaNormal pulgaNormal = ((PulgaMutante) pulga).convertirANormal();
                    sprites.set(i, pulgaNormal);
                } else {
                    sprites.remove(i);
                    puntaje++;
                }
                break;
            }
        }
    }

    /**
     * Dispara un misil que elimina la mitad de las pulgas en el campo aleatoriamente.
     */
    public void disparoMisil() {
        int cantidadEliminar = sprites.size() / 2;
        Collections.shuffle(sprites);

        for (int i = 0; i < cantidadEliminar; i++) {
            sprites.remove(0);
            puntaje++;
        }
    }

    /**
     * Verifica si ya no quedan pulgas en el campo.
     * @return true si no hay pulgas; false en caso contrario.
     */
    public boolean todasPulgasEliminadas() {
        return sprites.isEmpty();
    }

    /**
     * Reinicia el estado del juego: puntaje y lista de pulgas.
     */
    public void reiniciarJuego() {
        guardarMaximoPuntaje();
        puntaje = 0;
        sprites.clear();
    }

    /**
     * Obtiene el puntaje actual.
     * @return Puntaje actual del jugador.
     */
    public int getPuntaje() {
        return puntaje;
    }

    /**
     * Obtiene el puntaje máximo registrado.
     * @return Máximo puntaje alcanzado en el juego.
     */
    public int getMaxPuntaje() {
        return maxPuntaje;
    }

    /**
     * Obtiene la cantidad actual de pulgas en el campo.
     * @return Número de pulgas vivas.
     */
    public int getCantidadPulgas() {
        return sprites.size();
    }

    /**
     * Procesa eventos de teclado para controlar el juego.
     * @param code Código de la tecla presionada.
     */
    public void keyPressed(int code) {
        if (code == KeyEvent.VK_SPACE) {
            pulgasSaltan();
        } else if (code == KeyEvent.VK_R) {
            reiniciarJuego();
        } else if (code == KeyEvent.VK_M) {
            disparoMisil();
        } else if (code == KeyEvent.VK_N) {
            agregarPulgaNormal();
        } else if (code == KeyEvent.VK_U) {
            agregarPulgaMutante();
        }
    }

    /**
     * Dibuja el estado actual del campo de batalla y la interfaz del juego.
     * @param g Contexto gráfico para dibujar.
     */
    @Override
    public void paint(Graphics g) {
        g.setColor(java.awt.Color.LIGHT_GRAY);
        g.fillRect(x, y, width, height);

        for (Object sprite : sprites) {
            ((Sprite) sprite).paint(g);
        }

        g.setColor(java.awt.Color.BLACK);
        g.drawString("Puntaje: " + puntaje, 20, 20);
        g.drawString("Pulgas restantes: " + getCantidadPulgas(), 20, 40);
        g.drawString("Máximo puntaje: " + maxPuntaje, 20, 60);
    }

    /**
     * Método vacío requerido por la superclase. No se necesita implementación.
     */
    @Override
    public void refresh() {}

    /**
     * Obtiene los límites del campo de batalla.
     * @return Un objeto Rectangle con los límites.
     */
    @Override
    public Rectangle getBoundaries() {
        return new Rectangle(x, y, width, height);
    }
}