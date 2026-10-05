import java.util.List;
import java.util.Scanner;

public class CombateVista {
    private Scanner scanner = new Scanner(System.in);

    public String solicitarTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine().trim();
    }

    public int solicitarEntero(String mensaje) {
        System.out.print(mensaje);
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public void mostrarEstado(Jugador j, List<Enemigo> enemigos) {
        System.out.println("\n========== ESTADO DEL COMBATE ==========");
        String arma = (j.getEquipado() == null) ? "puños" : j.getEquipado().getNombre();
        System.out.println(j.getNombre() + " (Nv " + j.getNivel() + ") Salud: "
                + j.getSalud() + "/" + j.getSaludMaxima() + " | Equipado: " + arma);
        System.out.println("Enemigos:");
        for (int i = 0; i < enemigos.size(); i++) {
            Enemigo e = enemigos.get(i);
            String estado = e.estaVivo() ? "Salud " + e.getSalud() : "DERROTADO";
            System.out.println("  " + (i + 1) + ". " + e.getNombre() + " [" + e.getTipo()
                    + ", Nv " + e.getNivel() + "] - " + estado);
        }
    }

    public void mostrarMenuAcciones() {
        System.out.println("\n¿Qué haces?");
        System.out.println("1. Atacar");
        System.out.println("2. Usar objeto");
        System.out.println("3. Ver inventario");
        System.out.println("4. Huir");
    }

    public void mostrarInventario(List<Item> items) {
        if (items.isEmpty()) {
            System.out.println("Tu inventario está vacío.");
            return;
        }
        System.out.println("--- Inventario ---");
        for (int i = 0; i < items.size(); i++) {
            Item it = items.get(i);
            System.out.println((i + 1) + ". " + it.getNombre() + " x" + it.getCantidad()
                    + " (" + it.getTipo() + ", valor " + it.getValor() + ") - " + it.getDescripcion());
        }
    }

    public void mostrarMensajes(List<String> mensajes) {
        for (String m : mensajes) {
            System.out.println(">> " + m);
        }
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void cerrarScanner() {
        scanner.close();
    }
}
