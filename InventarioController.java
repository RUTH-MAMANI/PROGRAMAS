public class InventarioController {
    private InventarioModel modelo;
    private InventarioView vista;

    public InventarioController(InventarioModel modelo, InventarioView vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public void agregarItem() {
        String nombre = vista.solicitarTexto("Nombre: ");
        int cantidad = vista.solicitarEntero("Cantidad: ");
        String tipoTxt = vista.solicitarTexto("Tipo (1 = Arma, 2 = Poción): ");
        String descripcion = vista.solicitarTexto("Descripción: ");

        if (nombre.isEmpty() || cantidad <= 0 || descripcion.isEmpty()) {
            vista.mostrarMensaje("Datos inválidos: nombre y descripción no vacíos, cantidad > 0.");
            return;
        }
        TipoItem tipo;
        if (tipoTxt.equals("1")) {
            tipo = TipoItem.ARMA;
        } else if (tipoTxt.equals("2")) {
            tipo = TipoItem.POCION;
        } else {
            vista.mostrarMensaje("Tipo inválido. Usa 1 o 2.");
            return;
        }
        modelo.agregarItem(new Item(nombre, cantidad, tipo, descripcion));
        vista.mostrarMensaje("Item agregado: " + nombre);
    }

    public void eliminarItem() {
        verInventario();
        if (modelo.obtenerItems().isEmpty()) return;
        String nombre = vista.solicitarTexto("Nombre del item a eliminar: ");
        Item item = modelo.buscarItem(nombre);
        if (item == null) {
            vista.mostrarMensaje("No existe ese item.");
        } else {
            modelo.eliminarItem(item);
            vista.mostrarMensaje("Item eliminado: " + item.getNombre());
        }
    }

    public void verInventario() {
        vista.mostrarInventario(modelo.obtenerItems());
    }

    public void mostrarDetalles() {
        String nombre = vista.solicitarTexto("Nombre del item: ");
        Item item = modelo.buscarItem(nombre);
        if (item == null) {
            vista.mostrarMensaje("No existe ese item.");
        } else {
            vista.mostrarDetallesItem(item);
        }
    }

    public void buscarItem() {
        String nombre = vista.solicitarTexto("Nombre a buscar: ");
        Item item = modelo.buscarItem(nombre);
        if (item == null) {
            vista.mostrarMensaje("No se encontró \"" + nombre + "\".");
        } else {
            vista.mostrarMensaje("Encontrado: " + item.getNombre() + " x" + item.getCantidad());
        }
    }

    public void usarItem() {
        String nombre = vista.solicitarTexto("Nombre del item a usar: ");
        Item item = modelo.buscarItem(nombre);
        if (item == null) {
            vista.mostrarMensaje("No existe ese item.");
            return;
        }
        vista.mostrarMensaje(item.usarItem());
        
        if (item.getCantidad() == 0) {
            modelo.eliminarItem(item);
            vista.mostrarMensaje(item.getNombre() + " se agotó y salió del inventario.");
        }
    }

    public void iniciar() {
        String opcion;
        do {
            vista.mostrarMenu();
            opcion = vista.solicitarOpcion();
            switch (opcion) {
                case "1": agregarItem(); break;
                case "2": eliminarItem(); break;
                case "3": verInventario(); break;
                case "4": mostrarDetalles(); break;
                case "5": buscarItem(); break;
                case "6": usarItem(); break;
                case "0": vista.mostrarMensaje("Saliendo..."); break;
                default: vista.mostrarMensaje("Opción no válida. Inténtalo de nuevo.");
            }
        } while (!opcion.equals("0"));
        vista.cerrarScanner();
    }
}
