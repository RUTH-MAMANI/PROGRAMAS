import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class PedidoModelo {
    private List<Pedido> pedidos;

    public PedidoModelo() {
        pedidos = new ArrayList<>();
    }

    public void agregarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public boolean eliminarPedido(int indice) {
        if (indice >= 0 && indice < pedidos.size()) {
            pedidos.remove(indice);
            return true;
        }
        return false;
    }

    public boolean actualizarPedido(int indice, String nuevoNombre) {
        if (indice >= 0 && indice < pedidos.size()) {
            pedidos.get(indice).setNombrePlato(nuevoNombre);
            return true;
        }
        return false;
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

    public int contarTotal() {
        return pedidos.size();
    }

    public Map<String, Integer> contarPorTipo() {
        Map<String, Integer> conteo = new TreeMap<>();
        for (Pedido p : pedidos) {
            conteo.put(p.getTipo(), conteo.getOrDefault(p.getTipo(), 0) + 1);
        }
        return conteo;
    }
}
