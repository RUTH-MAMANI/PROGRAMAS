import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class PedidoModelo {
    private List<Pedido> pedidos;
    private List<Pedido> historial;

    public PedidoModelo() {
        pedidos = new ArrayList<>();
        historial = new ArrayList<>();
    }

    private boolean indiceValido(int i) {
        return i >= 0 && i < pedidos.size();
    }

    public void agregarPedido(Pedido pedido) { pedidos.add(pedido); }

    public List<Pedido> getPedidos() { return pedidos; }

    public boolean actualizarPedido(int indice, String nuevoNombre) {
        if (!indiceValido(indice)) return false;
        pedidos.get(indice).setNombrePlato(nuevoNombre);
        return true;
    }

    public List<Pedido> buscarPedidos(String texto) {
        List<Pedido> encontrados = new ArrayList<>();
        String t = texto.toLowerCase();
        for (Pedido p : pedidos) {
            if (p.getNombrePlato().toLowerCase().contains(t)
                    || p.getTipo().toLowerCase().contains(t)) {
                encontrados.add(p);
            }
        }
        return encontrados;
    }

    public int contarTotal() { return pedidos.size(); }

    public Map<String, Integer> contarPorTipo() {
        Map<String, Integer> conteo = new TreeMap<>();
        for (Pedido p : pedidos) {
            conteo.put(p.getTipo(), conteo.getOrDefault(p.getTipo(), 0) + 1);
        }
        return conteo;
    }

    public boolean eliminarPedido(int indice) {
        if (!indiceValido(indice)) return false;
        Pedido p = pedidos.remove(indice);
        p.setEstado(Estado.ELIMINADO);
        
        if (!historial.contains(p)) {
            historial.add(p);
        }
        return true;
    }

    public boolean completarPedido(int indice) {
        if (!indiceValido(indice)) return false;
        Pedido p = pedidos.get(indice);
        if (p.getEstado() == Estado.COMPLETADO) return false; 
        p.setEstado(Estado.COMPLETADO);
        historial.add(p);
        return true;
    }

    public List<Pedido> pedidosPorEstado(Estado estado) {
        List<Pedido> filtrados = new ArrayList<>();
        for (Pedido p : pedidos) {
            if (p.getEstado() == estado) filtrados.add(p);
        }
        return filtrados;
    }

    public int contarPendientes() {
        return pedidosPorEstado(Estado.PENDIENTE).size();
    }

    public List<Pedido> getHistorial() { return historial; }
}
