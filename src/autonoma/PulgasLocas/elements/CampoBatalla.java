
package autonoma.PulgasLocas.elements;

import autonoma.PulgasLocas.main.PulgaGame;
import autonoma.PulgasLocasBase.elements.EscritorArchivoTextoPlano;
import autonoma.PulgasLocasBase.elements.LectorArchivoTextoPlano;
import autonoma.PulgasLocasBase.elements.Sprite;
import autonoma.PulgasLocasBase.elements.SpriteContainer;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Collections;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * @author Gilary
 * @version 1.0
 * @since 06/05/2025
 */

/**
 * Clase que representa el campo de batalla donde se desarrolla el juego
 * de las Pulgas Locas. Controla la aparición, movimiento, interacción y
 * eliminación de las pulgas, así como el puntaje del jugador.
 */
public class CampoBatalla extends SpriteContainer {

    private Random random;
    private int puntaje = 0;
    private int maxPuntaje = 0;
    private PulgaGame game;
    private LectorArchivoTextoPlano lector;
    private EscritorArchivoTextoPlano escritor;

    /**
     * Constructor que inicializa el campo de batalla y carga el puntaje máximo guardado.
     * 
     * @param frame JFrame donde se muestra el campo.
     * @param game Referencia al juego principal.
     */
    public CampoBatalla(JFrame frame, PulgaGame game) {
        super(0, 0, frame.getHeight(), frame.getWidth());
        random = new Random();
        lector = new LectorArchivoTextoPlano();
        this.game = game;
        escritor = new EscritorArchivoTextoPlano("src/autonoma/pulgasLocas/util/maxpuntaje.txt");
        this.cargarMaximoPuntaje();
    }

    /**
     * Carga el puntaje máximo desde un archivo de texto.
     */
    private void cargarMaximoPuntaje() {
        try {
            maxPuntaje = lector.leerPuntajeAlto("src/autonoma/pulgasLocas/util/maxpuntaje.txt");
        } catch (Exception e) {
            System.out.println("Error al cargar el máximo puntaje: " + e.getMessage());
            maxPuntaje = 0;
        }
    }

    /**
     * Agrega una nueva pulga normal en una posición aleatoria sin colisión.
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
     * Agrega una nueva pulga mutante en una posición aleatoria sin colisión.
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
     * Hace que todas las pulgas salten dentro de los límites del campo.
     */
    public void pulgasSaltan() {
        for (Object obj : sprites) {
            Pulga pulga = (Pulga) obj;
            pulga.saltar(this.getBoundaries());
        }
    }

    /**
     * Detecta si una pulga ha sido impactada por un disparo y actualiza el puntaje.
     * 
     * @param x Coordenada X del disparo.
     * @param y Coordenada Y del disparo.
     */
    public void disparoPistola(int x, int y) {
        for (int i = 0; i < sprites.size(); i++) {
            Pulga pulga = (Pulga) sprites.get(i);
            if (x >= pulga.getX() && x <= pulga.getX() + pulga.getWidth()
                    && y >= pulga.getY() && y <= pulga.getY() + pulga.getHeight()) {

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
        if (this.sprites.size() == 0) {
            this.game.getSpawner().stop();
            String[] options = new String[2];
            options[0] = "REINICIAR";
            options[1] = "SALIR";
            int choice = JOptionPane.showOptionDialog(
                    null,
                    "¿Quieres continuar?",
                    "Confirmación",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]
            );
            boolean continuar = (choice == JOptionPane.YES_OPTION);
            if (continuar) {
                this.reiniciarJuego();
            } else {
                handleExitGame();
            }
        }
    }

    /**
     * Maneja el cierre del juego solicitando el nombre del jugador y guardando su puntaje.
     */
    private void handleExitGame() {
        try {
            JTextField campoTexto = new JTextField();
            Object[] mensaje = {
                "Ingresa tu nombre:", campoTexto
            };
            int opcion = JOptionPane.showConfirmDialog(
                    null,
                    mensaje,
                    "Nombre del jugador",
                    JOptionPane.OK_CANCEL_OPTION
            );
            if (opcion == JOptionPane.OK_OPTION) {
                String nombre = campoTexto.getText();
                this.escritor.escribir(nombre + "," + Integer.toString(puntaje));
            } else {
                JOptionPane.showMessageDialog(null, "PUNTAJE NO GUARDADO");
            }
            System.exit(0);
        } catch (IOException ex) {
            System.out.println("Error al leer el archivo");
        }
    }

    /**
     * Dispara un misil que elimina la mitad de las pulgas activas aleatoriamente.
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
     * Verifica si todas las pulgas han sido eliminadas del campo.
     * 
     * @return true si no hay pulgas, false en caso contrario.
     */
    public boolean todasPulgasEliminadas() {
        return sprites.isEmpty();
    }

    /**
     * Reinicia el juego, reseteando el puntaje y generando nuevas pulgas.
     */
    public void reiniciarJuego() {
        if (maxPuntaje < puntaje) {
            maxPuntaje = puntaje;
        }
        puntaje = 0;
        sprites.clear();
        this.game.getSpawner().start();
    }

    /**
     * Obtiene el puntaje actual del jugador.
     * 
     * @return Puntaje actual.
     */
    public int getPuntaje() {
        return puntaje;
    }

    /**
     * Obtiene el puntaje más alto registrado.
     * 
     * @return Máximo puntaje.
     */
    public int getMaxPuntaje() {
        return maxPuntaje;
    }

    /**
     * Devuelve la cantidad de pulgas activas en el campo.
     * 
     * @return Número de pulgas.
     */
    public int getCantidadPulgas() {
        return sprites.size();
    }

    /**
     * Maneja los eventos de teclado para controlar el juego.
     * 
     * @param code Código de la tecla presionada.
     */
    public void keyPressed(int code) {
        if (code == KeyEvent.VK_S) {
            pulgasSaltan();
        }
        if (code == KeyEvent.VK_R) {
            reiniciarJuego();
        }
        if (code == KeyEvent.VK_SPACE) {
            disparoMisil();
        }
        if (code == KeyEvent.VK_P) {
            agregarPulgaNormal();
        }
        if (code == KeyEvent.VK_M) {
            agregarPulgaMutante();
        }
    }

    /**
     * Método llamado para actualizar la lógica del juego (no implementado).
     */
    public void update() {
    }

    /**
     * Dibuja el campo de batalla, incluyendo el puntaje y las pulgas.
     * 
     * @param g Objeto Graphics usado para el dibujo.
     */
    @Override
    public void paint(Graphics g) {
        g.setColor(java.awt.Color.LIGHT_GRAY);
        g.fillRect(x, y, width, height);

        for (Object sprite : sprites) {
            ((Sprite) sprite).paint(g);
        }
        g.setColor(java.awt.Color.BLACK);
        g.drawString("Puntaje: " + puntaje, 20, 40);
        g.drawString("Pulgas restantes: " + getCantidadPulgas(), 20, 60);
        g.drawString("Máximo puntaje: " + maxPuntaje, 20, 80);
    }

    /**
     * Refresca el campo de batalla (no implementado).
     */
    @Override
    public void refresh() {
    }

    /**
     * Retorna los límites del campo de batalla.
     * 
     * @return Un objeto Rectangle con los límites.
     */
    @Override
    public Rectangle getBoundaries() {
        return new Rectangle(x, y, width, height);
    }
}
