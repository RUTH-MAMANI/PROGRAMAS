public class TiendaControlador {
    private TiendaModelo modelo;
    private TiendaVista vista;

    public TiendaControlador(TiendaModelo modelo, TiendaVista vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public void agregarProducto() {
        String nombre = vista.solicitarTexto("Nombre del producto: ");
        double precio = vista.solicitarDecimal("Precio (S/): ");
        int stock = vista.solicitarEntero("Stock: ");
        if (nombre.isEmpty() || precio <= 0 || stock < 0) {
            vista.mostrarMensaje("Datos inválidos: revisa nombre, precio (>0) y stock (>=0).");
            return;
        }
        Producto p = modelo.agregarProducto(nombre, precio, stock);
        vista.mostrarMensaje("Producto agregado con ID " + p.getId());
    }

    public void listarProductos() {
        vista.mostrarProductos(modelo.getCatalogo());
    }

    public void agregarAlCarrito() {
        listarProductos();
        if (modelo.getCatalogo().isEmpty()) return;
        int id = vista.solicitarEntero("ID del producto: ");
        int cantidad = vista.solicitarEntero("Cantidad: ");
        String error = modelo.agregarAlCarrito(id, cantidad);
        vista.mostrarMensaje(error == null ? "Producto agregado al carrito." : error);
    }

    public void verCarrito() {
        vista.mostrarCarrito(modelo.getCarrito(), modelo.calcularSubtotal(),
                modelo.calcularDescuento(), modelo.getCuponAplicado(),
                modelo.calcularEnvio(), modelo.calcularTotal());
    }

    public void eliminarDelCarrito() {
        verCarrito();
        if (modelo.getCarrito().isEmpty()) return;
        int id = vista.solicitarEntero("ID del producto a quitar: ");
        vista.mostrarMensaje(modelo.eliminarDelCarrito(id)
                ? "Producto eliminado del carrito." : "Ese producto no está en el carrito.");
    }

    public void aplicarDescuento() {
        if (modelo.getCarrito().isEmpty()) {
            vista.mostrarMensaje("Primero agrega productos al carrito.");
            return;
        }
        String codigo = vista.solicitarTexto("Código de descuento (DESC10 / DESC20): ");
        vista.mostrarMensaje(modelo.aplicarDescuento(codigo)
                ? "Descuento aplicado." : "Código de descuento inválido.");
    }

    public void calcularEnvio() {
        double envio = modelo.calcularEnvio();
        if (modelo.getCarrito().isEmpty()) {
            vista.mostrarMensaje("El carrito está vacío, no hay envío que calcular.");
        } else if (envio == 0) {
            vista.mostrarMensaje("¡Envío GRATIS! (compras desde S/ "
                    + (int) TiendaModelo.MONTO_ENVIO_GRATIS + ")");
        } else {
            vista.mostrarMensaje("Costo de envío: S/ " + String.format("%.2f", envio));
        }
    }

    public void verHistorial() {
        vista.mostrarHistorial(modelo.getHistorial());
    }

    public void realizarCompra() {
        verCarrito();
        Compra c = modelo.realizarCompra();
        if (c == null) {
            vista.mostrarMensaje("No hay nada que comprar.");
        } else {
            vista.mostrarMensaje(String.format("¡Compra #%d realizada! Total pagado: S/ %.2f",
                    c.getNumero(), c.getTotal()));
        }
    }

    public void iniciar() {
        
        modelo.agregarProducto("Laptop", 2500.00, 5);
        modelo.agregarProducto("Mouse", 45.50, 20);
        modelo.agregarProducto("Teclado", 120.00, 10);
        modelo.agregarProducto("Audífonos", 89.90, 8);

        String opcion;
        do {
            vista.mostrarMenu();
            opcion = vista.solicitarOpcion();
            switch (opcion) {
                case "1": agregarProducto(); break;
                case "2": listarProductos(); break;
                case "3": agregarAlCarrito(); break;
                case "4": verCarrito(); break;
                case "5": eliminarDelCarrito(); break;
                case "6": aplicarDescuento(); break;
                case "7": calcularEnvio(); break;
                case "8": verHistorial(); break;
                case "9": realizarCompra(); break;
                case "0": vista.mostrarMensaje("Gracias por visitar la tienda."); break;
                default: vista.mostrarMensaje("Opción no válida. Inténtalo de nuevo.");
            }
        } while (!opcion.equals("0"));
        vista.cerrarScanner();
    }
}
