public class PedidoControlador {
    private PedidoModelo modelo;
    private PedidoVista vista;

    public PedidoControlador(PedidoModelo modelo, PedidoVista vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    private void listar() {
        vista.mostrarPedidos(modelo.getPedidos(), "Lista de Pedidos:");
    }

    public void agregarPedido() {
        String nombre = vista.solicitarTexto("Nombre del plato: ");
        String tipo = vista.solicitarTexto("Tipo (Entrada/Fondo/Postre/Bebida): ");
        if (nombre.isEmpty() || tipo.isEmpty()) {
            vista.mostrarMensaje("El nombre y el tipo no pueden estar vacíos.");
            return;
        }
        modelo.agregarPedido(new Pedido(nombre, tipo));
        vista.mostrarMensaje("Pedido agregado (PENDIENTE): " + nombre);
    }

    public void eliminarPedido() {
        listar();
        if (modelo.contarTotal() == 0) return;
        int i = vista.solicitarNumeroPedido("Número de pedido a eliminar: ");
        vista.mostrarMensaje(modelo.eliminarPedido(i)
                ? "Pedido eliminado (guardado en el historial)."
                : "Número de pedido inválido.");
    }

    public void actualizarPedido() {
        listar();
        if (modelo.contarTotal() == 0) return;
        int i = vista.solicitarNumeroPedido("Número de pedido a actualizar: ");
        String nuevo = vista.solicitarTexto("Nuevo nombre del plato: ");
        if (nuevo.isEmpty()) {
            vista.mostrarMensaje("El nombre no puede estar vacío.");
        } else {
            vista.mostrarMensaje(modelo.actualizarPedido(i, nuevo)
                    ? "Pedido actualizado." : "Número de pedido inválido.");
        }
    }

    public void buscarPedido() {
        String texto = vista.solicitarTexto("Buscar por nombre o tipo: ");
        if (texto.isEmpty()) {
            vista.mostrarMensaje("Debes escribir algo para buscar.");
            return;
        }
        vista.mostrarPedidos(modelo.buscarPedidos(texto), "Resultados de la búsqueda:");
    }

    public void contarPedidos() {
        vista.mostrarConteo(modelo.contarTotal(), modelo.contarPorTipo());
    }

    public void completarPedido() {
        listar();
        if (modelo.contarTotal() == 0) return;
        int i = vista.solicitarNumeroPedido("Número de pedido a marcar como completo: ");
        vista.mostrarMensaje(modelo.completarPedido(i)
                ? "Pedido marcado como COMPLETADO."
                : "No se pudo: número inválido o el pedido ya estaba completo.");
    }

    public void mostrarPorEstado() {
        String op = vista.solicitarTexto("Ver (1) Pendientes o (2) Completados: ");
        if (op.equals("1")) {
            vista.mostrarPedidos(modelo.pedidosPorEstado(Estado.PENDIENTE), "Pedidos PENDIENTES:");
        } else if (op.equals("2")) {
            vista.mostrarPedidos(modelo.pedidosPorEstado(Estado.COMPLETADO), "Pedidos COMPLETADOS:");
        } else {
            vista.mostrarMensaje("Opción no válida.");
        }
    }

    public void mostrarPendientes() {
        vista.mostrarMensaje("Pedidos pendientes: " + modelo.contarPendientes());
    }

    public void mostrarHistorial() {
        vista.mostrarPedidos(modelo.getHistorial(), "Historial (completados y eliminados):");
    }

    public void iniciar() {
        String opcion;
        do {
            vista.mostrarMenu();
            opcion = vista.solicitarOpcion();
            switch (opcion) {
                case "1": agregarPedido(); break;
                case "2": listar(); break;
                case "3": eliminarPedido(); break;
                case "4": actualizarPedido(); break;
                case "5": buscarPedido(); break;
                case "6": contarPedidos(); break;
                case "7": completarPedido(); break;
                case "8": mostrarPorEstado(); break;
                case "9": mostrarPendientes(); break;
                case "10": mostrarHistorial(); break;
                case "0": vista.mostrarMensaje("Saliendo..."); break;
                default: vista.mostrarMensaje("Opción no válida. Inténtalo de nuevo.");
            }
        } while (!opcion.equals("0"));
        vista.cerrarScanner();
    }
}
