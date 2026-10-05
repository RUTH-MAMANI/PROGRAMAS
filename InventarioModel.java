import java.util.ArrayList;
import java.util.List;

public class InventarioModel {
    private List<Item> items;

    public InventarioModel() {
        items = new ArrayList<>();
    }

    public void agregarItem(Item item) {
        Item existente = buscarItem(item.getNombre());
        if (existente != null) {
            existente.setCantidad(existente.getCantidad() + item.getCantidad());
        } else {
            items.add(item);
        }
    }

    public boolean eliminarItem(Item item) {
        return items.remove(item);
    }

    public List<Item> obtenerItems() {
        return items;
    }

    public Item buscarItem(String nombre) {
        for (Item it : items) {
            if (it.getNombre().equalsIgnoreCase(nombre)) {
                return it;
            }
        }
        return null;
    }
}
