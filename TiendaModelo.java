import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TiendaModelo {
    
    public static final double ENVIO_NORMAL = 15.0;
    public static final double MONTO_ENVIO_GRATIS = 200.0;

    private List<Producto> catalogo = new ArrayList<>();
    private List<ItemCarrito> carrito = new ArrayList<>();
    private List<Compra> historial = new ArrayList<>();
    private Map<String, Double> cupones = new HashMap<>();
    private double porcentajeDescuento = 0;   
    private String cuponAplicado = null;
    private int siguienteId = 1;

    public TiendaModelo() {
        
        cupones.put("DESC10", 10.0);
        cupones.put("DESC20", 20.0);
    }

    public Producto agregarProducto(String nombre, double precio, int stock) {
        Producto p = new Producto(siguienteId++, nombre, precio, stock);
        catalogo.add(p);
        return p;
    }

    public List<Producto> getCatalogo() { return catalogo; }

    public Producto buscarProducto(int id) {
        for (Producto p : catalogo) {
            if (p.getId() == id) return p;
        }
        return null;
    }

    private ItemCarrito buscarEnCarrito(int idProducto) {
        for (ItemCarrito it : carrito) {
            if (it.getProducto().getId() == idProducto) return it;
        }
        return null;
    }

    public String agregarAlCarrito(int idProducto, int cantidad) {
        Producto p = buscarProducto(idProducto);
        if (p == null) return "No existe un producto con ese ID.";
        if (cantidad <= 0) return "La cantidad debe ser mayor que 0.";
        ItemCarrito existente = buscarEnCarrito(idProducto);
        int yaEnCarrito = (existente == null) ? 0 : existente.getCantidad();
        if (yaEnCarrito + cantidad > p.getStock()) {
            return "Stock insuficiente. Disponible: " + p.getStock()
                    + " (ya tienes " + yaEnCarrito + " en el carrito).";
        }
        if (existente == null) {
            carrito.add(new ItemCarrito(p, cantidad));
        } else {
            existente.sumarCantidad(cantidad);
        }
        return null;
    }

    public boolean eliminarDelCarrito(int idProducto) {
        ItemCarrito it = buscarEnCarrito(idProducto);
        if (it == null) return false;
        carrito.remove(it);
        return true;
    }

    public List<ItemCarrito> getCarrito() { return carrito; }

    public double calcularSubtotal() {
        double suma = 0;
        for (ItemCarrito it : carrito) suma += it.getSubtotal();
        return suma;
    }

    public boolean aplicarDescuento(String codigo) {
        Double pct = cupones.get(codigo.toUpperCase());
        if (pct == null) return false;
        porcentajeDescuento = pct;
        cuponAplicado = codigo.toUpperCase();
        return true;
    }

    public String getCuponAplicado() { return cuponAplicado; }

    public double calcularDescuento() {
        return calcularSubtotal() * porcentajeDescuento / 100.0;
    }

    public double calcularEnvio() {
        if (carrito.isEmpty()) return 0;
        double baseEnvio = calcularSubtotal() - calcularDescuento();
        return (baseEnvio >= MONTO_ENVIO_GRATIS) ? 0 : ENVIO_NORMAL;
    }

    public double calcularTotal() {
        return calcularSubtotal() - calcularDescuento() + calcularEnvio();
    }

    public Compra realizarCompra() {
        if (carrito.isEmpty()) return null;
        Compra c = new Compra(historial.size() + 1, carrito, calcularSubtotal(),
                calcularDescuento(), calcularEnvio(), calcularTotal());
        for (ItemCarrito it : carrito) {
            it.getProducto().reducirStock(it.getCantidad());
        }
        historial.add(c);
        carrito = new ArrayList<>();
        porcentajeDescuento = 0;
        cuponAplicado = null;
        return c;
    }

    public List<Compra> getHistorial() { return historial; }
}
