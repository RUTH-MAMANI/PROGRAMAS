import java.util.ArrayList;
import java.util.List;

public class Compra {
    private int numero;
    private List<String> detalle;
    private double subtotal;
    private double descuento;
    private double envio;
    private double total;

    public Compra(int numero, List<ItemCarrito> items, double subtotal,
                  double descuento, double envio, double total) {
        this.numero = numero;
        this.detalle = new ArrayList<>();
        for (ItemCarrito it : items) {
            detalle.add(it.getCantidad() + " x " + it.getProducto().getNombre());
        }
        this.subtotal = subtotal;
        this.descuento = descuento;
        this.envio = envio;
        this.total = total;
    }

    public int getNumero() { return numero; }
    public List<String> getDetalle() { return detalle; }
    public double getSubtotal() { return subtotal; }
    public double getDescuento() { return descuento; }
    public double getEnvio() { return envio; }
    public double getTotal() { return total; }
}
