import java.util.ArrayList;
import java.util.List;

public class CombateModelo {
    private Jugador jugador;
    private List<Enemigo> enemigos = new ArrayList<>();
    private List<String> mensajes = new ArrayList<>();

    public CombateModelo(Jugador jugador) {
        this.jugador = jugador;
    }

    public Jugador getJugador() { return jugador; }
    public List<Enemigo> getEnemigos() { return enemigos; }
    public void agregarEnemigo(Enemigo e) { enemigos.add(e); }

    public List<Enemigo> enemigosVivos() {
        List<Enemigo> vivos = new ArrayList<>();
        for (Enemigo e : enemigos) {
            if (e.estaVivo()) vivos.add(e);
        }
        return vivos;
    }

    public boolean hayEnemigosVivos() { return !enemigosVivos().isEmpty(); }

    public void agregarMensaje(String mensaje) { mensajes.add(mensaje); }
    public List<String> getMensajes() { return mensajes; }
    public void limpiarMensajes() { mensajes.clear(); }
}
