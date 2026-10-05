public class Item {
    private String nombre;
    private int cantidad;
    private TipoItem tipo;
    private String descripcion;
    private int valor;

    public Item(String nombre, int cantidad, TipoItem tipo, String descripcion, int valor) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.valor = valor;
    }

    public String getNombre() { return nombre; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    public TipoItem getTipo() { return tipo; }
    public String getDescripcion() { return descripcion; }
    public int getValor() { return valor; }

    public void consumir() {
        if (tipo == TipoItem.POCION && cantidad > 0) {
            cantidad--;
        }
    }
}
