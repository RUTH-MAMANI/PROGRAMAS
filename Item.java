public class Item {
    private String nombre;
    private int cantidad;
    private TipoItem tipo;
    private String descripcion;

    public Item(String nombre, int cantidad, TipoItem tipo, String descripcion) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.tipo = tipo;
        this.descripcion = descripcion;
    }

    public String getNombre() { return nombre; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    public TipoItem getTipo() { return tipo; }
    public String getDescripcion() { return descripcion; }

    public String usarItem() {
        if (cantidad <= 0) {
            return "No te quedan unidades de " + nombre + ".";
        }
        if (tipo == TipoItem.POCION) {
            cantidad--;
            return "Usaste " + nombre + ". Te quedan " + cantidad + ".";
        }
        return "Atacaste con " + nombre + ".";
    }
}
