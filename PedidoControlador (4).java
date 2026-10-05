public class PedidoControlador {
    private PedidoModelo modelo;
    private PedidoVista vista;

    public PedidoControlador(PedidoModelo modelo, PedidoVista vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public void agregarPedido() {
        String nombre = vista.solicitarTexto("Nombre del plato: ");
        String tipo = vista.solicitarTexto("Tipo (Entrada/Fondo/Postre/Bebida): ");
        if (nombre.isEmpty() || tipo.isEmpty()) {
            vista.mostrarMensaje("El nombre y el tipo no pueden estar vacíos.");
            return;
        }
        modelo.agregarPedido(new Pedido(nombre, tipo));
        vista.mostrarMensaje("Pedido agregado: " + nombre + " [" + tipo + "]");
    }

    public void mostrarPedidos() {
        vista.mostrarPedidos(modelo.getPedidos());
    }

    public void eliminarPedido() {
        mostrarPedidos();
        if (modelo.contarTotal() == 0) return;
        int indice = vista.solicitarNumeroPedido("Número de pedido a eliminar: ");
        if (modelo.eliminarPedido(indice)) {
            vista.mostrarMensaje("Pedido eliminado.");
        } else {
            vista.mostrarMensaje("Número de pedido inválido.");
        }
    }

    public void actualizarPedido() {
        mostrarPedidos();
        if (modelo.contarTotal() == 0) return;
        int indice = vista.solicitarNumeroPedido("Número de pedido a actualizar: ");
        String nuevoNombre = vista.solicitarTexto("Nuevo nombre del plato: ");
        if (nuevoNombre.isEmpty()) {
            vista.mostrarMensaje("El nombre no puede estar vacío.");
        } else if (modelo.actualizarPedido(indice, nuevoNombre)) {
            vista.mostrarMensaje("Pedido actualizado.");
        } else {
            vista.mostrarMensaje("Número de pedido inválido.");
        }
    }

    public void buscarPedido() {
        String texto = vista.solicitarTexto("Buscar por nombre o tipo: ");
        if (texto.isEmpty()) {
            vista.mostrarMensaje("Debes escribir algo para buscar.");
            return;
        }
        vista.mostrarResultadosBusqueda(modelo.buscarPedidos(texto));
    }

    public void contarPedidos() {
        vista.mostrarConteo(modelo.contarTotal(), modelo.contarPorTipo());
    }

    public void iniciar() {
        String opcion;
        do {
            vista.mostrarMenu();
            opcion = vista.solicitarOpcion();
            switch (opcion) {
                case "1": agregarPedido(); break;
                case "2": mostrarPedidos(); break;
                case "3": eliminarPedido(); break;
                case "4": actualizarPedido(); break;
                case "5": buscarPedido(); break;
                case "6": contarPedidos(); break;
                case "7": vista.mostrarMensaje("Saliendo..."); break;
                default: vista.mostrarMensaje("Opción no válida. Inténtalo de nuevo.");
            }
        } while (!opcion.equals("7"));
        vista.cerrarScanner();
    }
}
