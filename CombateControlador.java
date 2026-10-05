import java.util.List;
import java.util.Random;

public class CombateControlador {
    private CombateModelo modelo;
    private CombateVista vista;
    private Random azar;

    public CombateControlador(CombateModelo modelo, CombateVista vista, Random azar) {
        this.modelo = modelo;
        this.vista = vista;
        this.azar = azar;
    }

    private boolean turnoJugador(String opcion) {
        Jugador jugador = modelo.getJugador();
        switch (opcion) {
            case "1": {
                List<Enemigo> vivos = modelo.enemigosVivos();
                for (int i = 0; i < vivos.size(); i++) {
                    vista.mostrarMensaje((i + 1) + ". " + vivos.get(i).getNombre());
                }
                int n = vista.solicitarEntero("¿A quién atacas? ") - 1;
                if (n < 0 || n >= vivos.size()) {
                    vista.mostrarMensaje("Objetivo inválido.");
                    return false;
                }
                Enemigo objetivo = vivos.get(n);
                int danio = jugador.atacar(objetivo);
                modelo.agregarMensaje(jugador.getNombre() + " ataca a " + objetivo.getNombre()
                        + " y le hace " + danio + " de daño.");
                if (!objetivo.estaVivo()) {
                    modelo.agregarMensaje(objetivo.getNombre() + " fue derrotado.");
                }
                return true;
            }
            case "2": {
                List<Item> items = jugador.getInventario().obtenerItems();
                vista.mostrarInventario(items);
                if (items.isEmpty()) return false;
                int n = vista.solicitarEntero("¿Qué objeto usas? ") - 1;
                if (n < 0 || n >= items.size()) {
                    vista.mostrarMensaje("Objeto inválido.");
                    return false;
                }
                Item item = items.get(n);
                modelo.agregarMensaje(jugador.usarObjeto(item));
                if (item.getCantidad() == 0) {   
                    jugador.getInventario().eliminarItem(item);
                    modelo.agregarMensaje(item.getNombre() + " se agotó.");
                }
                return true;
            }
            case "3":
                vista.mostrarInventario(jugador.getInventario().obtenerItems());
                return false;   
            default:
                vista.mostrarMensaje("Opción no válida.");
                return false;
        }
    }

    private void turnoEnemigos() {
        Jugador jugador = modelo.getJugador();
        for (Enemigo e : modelo.enemigosVivos()) {
            if (!jugador.estaVivo()) break;
            int accion = azar.nextInt(3);
            if (accion == 0) {
                int d = e.atacar(jugador, 2);
                modelo.agregarMensaje(e.getNombre() + " te ataca y te hace " + d + " de daño.");
            } else if (accion == 1) {
                int d = e.atacar(jugador, 4);
                modelo.agregarMensaje(e.getNombre() + " lanza un ATAQUE FUERTE: " + d + " de daño.");
            } else {
                int r = e.descansar();
                if (r > 0) {
                    modelo.agregarMensaje(e.getNombre() + " descansa y recupera " + r + " de salud.");
                } else {
                    modelo.agregarMensaje(e.getNombre() + " se queda en guardia.");
                }
            }
        }
    }

    public void iniciar() {
        Jugador jugador = modelo.getJugador();
        vista.mostrarMensaje("¡Comienza el combate!");
        boolean huyo = false;

        while (jugador.estaVivo() && modelo.hayEnemigosVivos() && !huyo) {
            modelo.limpiarMensajes();
            vista.mostrarEstado(jugador, modelo.getEnemigos());
            vista.mostrarMenuAcciones();
            String opcion = vista.solicitarTexto("Elige una acción: ");

            if (opcion.equals("4")) {
                huyo = true;
                break;
            }
            boolean gastoTurno = turnoJugador(opcion);
            if (gastoTurno) {
                if (modelo.hayEnemigosVivos()) {
                    turnoEnemigos();
                }
                vista.mostrarMensajes(modelo.getMensajes());
            }
        }

        if (huyo) {
            vista.mostrarMensaje("\nHuiste del combate.");
        } else if (!jugador.estaVivo()) {
            vista.mostrarEstado(jugador, modelo.getEnemigos());
            vista.mostrarMensaje("\nHas sido derrotado... GAME OVER.");
        } else {
            vista.mostrarEstado(jugador, modelo.getEnemigos());
            vista.mostrarMensaje("\n¡VICTORIA! Derrotaste a todos los enemigos.");
        }
        vista.cerrarScanner();
    }
}
