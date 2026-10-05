import java.util.List;
import java.util.Scanner;

public class InventarioView {
    private Scanner scanner = new Scanner(System.in);

    public void mostrarMenu() {
        System.out.println("\n=== INVENTARIO ===");
        System.out.println("1. Agregar item");
        System.out.println("2. Eliminar item");
        System.out.println("3. Ver inventario");
        System.out.println("4. Ver detalles de un item");
        System.out.println("5. Buscar item");
        System.out.println("6. Usar item");
        System.out.println("0. Salir");
    }

    public String solicitarOpcion() {
        System.out.print("Selecciona una opción: ");
        return scanner.nextLine().trim();
    }

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

    public void mostrarInventario(List<Item> items) {
        if (items.isEmpty()) {
            System.out.println("El inventario está vacío.");
            return;
        }
        System.out.println("--- Inventario ---");
        for (Item it : items) {
            System.out.println("- " + it.getNombre() + " x" + it.getCantidad()
                    + " (" + it.getTipo() + ")");
        }
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void mostrarDetallesItem(Item item) {
        System.out.println("Nombre: " + item.getNombre());
        System.out.println("Cantidad: " + item.getCantidad());
        System.out.println("Tipo: " + item.getTipo());
        System.out.println("Descripción: " + item.getDescripcion());
    }

    public void cerrarScanner() {
        scanner.close();
    }
}
