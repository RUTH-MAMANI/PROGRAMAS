import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random azar = (args.length > 0) ? new Random(Long.parseLong(args[0])) : new Random();

        CombateVista vista = new CombateVista();
        String nombre = vista.solicitarTexto("Nombre de tu personaje: ");
        if (nombre.isEmpty()) nombre = "Héroe";

        Jugador jugador = new Jugador(nombre, 120, 3);
        jugador.getInventario().agregarItem(new Item("Espada", 1, TipoItem.ARMA, "Espada de hierro", 8));
        jugador.getInventario().agregarItem(new Item("Arco", 1, TipoItem.ARMA, "Arco de madera", 6));
        jugador.getInventario().agregarItem(new Item("Poción", 2, TipoItem.POCION, "Cura 25 de salud", 25));

        CombateModelo modelo = new CombateModelo(jugador);
        modelo.agregarEnemigo(new Enemigo("Goblin", 30, 1, "Humanoide"));
        modelo.agregarEnemigo(new Enemigo("Lobo", 35, 2, "Bestia"));
        modelo.agregarEnemigo(new Enemigo("Orco", 50, 3, "Bruto"));

        CombateControlador controlador = new CombateControlador(modelo, vista, azar);
        controlador.iniciar();
    }
}
