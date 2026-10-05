import java.util.List;
import java.util.Scanner;

public class TiendaVista {
    private Scanner scanner = new Scanner(System.in);

    public void mostrarMenu() {
        System.out.println("\n=== TIENDA - CARRITO DE COMPRAS ===");
        System.out.println("1. Agregar producto al catálogo");
        System.out.println("2. Listar productos");
        System.out.println("3. Agregar producto al carrito");
        System.out.println("4. Ver carrito");
        System.out.println("5. Eliminar producto del carrito");
        System.out.println("6. Aplicar descuento");
        System.out.println("7. Calcular envío");
        System.out.println("8. Ver historial de compras");
        System.out.println("9. Realizar compra");
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

    public double solicitarDecimal(String mensaje) {
        System.out.print(mensaje);
        try {
            return Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public void mostrarProductos(List<Producto> productos) {
        if (productos.isEmpty()) {
            System.out.println("El catálogo está vacío.");
            return;
        }
        System.out.println("ID | Producto | Precio | Stock");
        for (Producto p : productos) {
            System.out.printf("%d | %s | S/ %.2f | %d%n",
                    p.getId(), p.getNombre(), p.getPrecio(), p.getStock());
        }
    }

    public void mostrarCarrito(List<ItemCarrito> items, double subtotal, double descuento,
                               String cupon, double envio, double total) {
        if (items.isEmpty()) {
            System.out.println("El carrito está vacío.");
            return;
        }
        System.out.println("--- Tu carrito ---");
        for (ItemCarrito it : items) {
            System.out.printf("[%d] %s x%d = S/ %.2f%n", it.getProducto().getId(),
                    it.getProducto().getNombre(), it.getCantidad(), it.getSubtotal());
        }
        System.out.printf("Subtotal: S/ %.2f%n", subtotal);
        if (cupon != null) {
            System.out.printf("Descuento (%s): -S/ %.2f%n", cupon, descuento);
        }
        System.out.printf("Envío: S/ %.2f%n", envio);
        System.out.printf("TOTAL: S/ %.2f%n", total);
    }

    public void mostrarHistorial(List<Compra> compras) {
        if (compras.isEmpty()) {
            System.out.println("Aún no hay compras realizadas.");
            return;
        }
        for (Compra c : compras) {
            System.out.println("Compra #" + c.getNumero() + ": " + String.join(", ", c.getDetalle()));
            System.out.printf("   Subtotal S/ %.2f | Descuento S/ %.2f | Envío S/ %.2f | Total S/ %.2f%n",
                    c.getSubtotal(), c.getDescuento(), c.getEnvio(), c.getTotal());
        }
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void cerrarScanner() {
        scanner.close();
    }
}
