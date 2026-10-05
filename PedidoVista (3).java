import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class PedidoVista {
    private Scanner scanner;

    public PedidoVista() {
        scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        System.out.println("\n=== RESTAURANTE - PEDIDOS ===");
        System.out.println("1. Agregar Pedido");
        System.out.println("2. Mostrar Pedidos");
        System.out.println("3. Eliminar Pedido");
        System.out.println("4. Actualizar Pedido");
        System.out.println("5. Buscar Pedido (por nombre o tipo)");
        System.out.println("6. Contar Pedidos");
        System.out.println("7. Salir");
    }

    public String solicitarOpcion() {
        System.out.print("Selecciona una opción: ");
        return scanner.nextLine().trim();
    }

    public String solicitarTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine().trim();
    }

    public int solicitarNumeroPedido(String mensaje) {
        System.out.print(mensaje);
        try {
            return Integer.parseInt(scanner.nextLine().trim()) - 1;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public void mostrarPedidos(List<Pedido> pedidos) {
        if (pedidos.isEmpty()) {
            System.out.println("No hay pedidos en la lista.");
            return;
        }
        System.out.println("Lista de Pedidos:");
        for (int i = 0; i < pedidos.size(); i++) {
            Pedido p = pedidos.get(i);
            System.out.println((i + 1) + ". " + p.getNombrePlato() + " [" + p.getTipo() + "]");
        }
    }

    public void mostrarResultadosBusqueda(List<Pedido> encontrados) {
        if (encontrados.isEmpty()) {
            System.out.println("No se encontraron pedidos con ese criterio.");
            return;
        }
        System.out.println("Resultados de la búsqueda:");
        for (Pedido p : encontrados) {
            System.out.println("- " + p.getNombrePlato() + " [" + p.getTipo() + "]");
        }
    }

    public void mostrarConteo(int total, Map<String, Integer> porTipo) {
        System.out.println("Total de pedidos: " + total);
        for (Map.Entry<String, Integer> e : porTipo.entrySet()) {
            System.out.println("  - " + e.getKey() + ": " + e.getValue());
        }
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void cerrarScanner() {
        scanner.close();
    }
}
