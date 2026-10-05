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
        System.out.println(" 1. Agregar Pedido");
        System.out.println(" 2. Mostrar Pedidos");
        System.out.println(" 3. Eliminar Pedido");
        System.out.println(" 4. Actualizar Pedido");
        System.out.println(" 5. Buscar Pedido (nombre o tipo)");
        System.out.println(" 6. Contar Pedidos");
        System.out.println(" 7. Marcar Pedido como Completo");
        System.out.println(" 8. Mostrar Pedidos por Estado");
        System.out.println(" 9. Contador de Pedidos Pendientes");
        System.out.println("10. Historial de Pedidos");
        System.out.println(" 0. Salir");
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

    public void mostrarPedidos(List<Pedido> pedidos, String titulo) {
        if (pedidos.isEmpty()) {
            System.out.println("No hay pedidos para mostrar.");
            return;
        }
        System.out.println(titulo);
        for (int i = 0; i < pedidos.size(); i++) {
            Pedido p = pedidos.get(i);
            System.out.println((i + 1) + ". " + p.getNombrePlato()
                    + " [" + p.getTipo() + "] - " + p.getEstado());
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
