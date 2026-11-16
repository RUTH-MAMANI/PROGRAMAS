package principal;

import javax.swing.*;
import java.awt.*;

/** Ejercicio 1: Binding manual de un Producto (modelo) con la interfaz. */
public class Ejercicio1_Producto extends JFrame {

    private final Producto producto = new Producto("Laptop", 2500.0, 10, "Electrónica");

    private final JTextField txtNombre = new JTextField(producto.getNombre(), 15);
    private final JTextField txtPrecio = new JTextField(String.valueOf(producto.getPrecio()), 15);
    private final JTextField txtStock = new JTextField(String.valueOf(producto.getCantidadStock()), 15);
    private final JTextField txtCategoria = new JTextField(producto.getCategoria(), 15);
    private final JLabel lblInfo = new JLabel();

    public Ejercicio1_Producto() {
        super("Gestión de Producto");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(380, 330);
        setLocationRelativeTo(null);

        JPanel form = new JPanel(new GridLayout(4, 2, 5, 8));
        form.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
        form.add(new JLabel("Nombre:"));          form.add(txtNombre);
        form.add(new JLabel("Precio (S/):"));     form.add(txtPrecio);
        form.add(new JLabel("Cantidad en stock:")); form.add(txtStock);
        form.add(new JLabel("Categoría:"));       form.add(txtCategoria);

        JButton btn = new JButton("Actualizar Producto");
        btn.addActionListener(e -> actualizarModelo());

        JPanel sur = new JPanel(new BorderLayout(5, 5));
        sur.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
        sur.add(btn, BorderLayout.NORTH);
        sur.add(lblInfo, BorderLayout.CENTER);

        add(form, BorderLayout.NORTH);
        add(sur, BorderLayout.CENTER);

        mostrarInfo();
    }

    /** Vista -> Modelo (con validación) y luego Modelo -> Vista. */
    private void actualizarModelo() {
        try {
            String nombre = txtNombre.getText().trim();
            String categoria = txtCategoria.getText().trim();
            if (nombre.isEmpty() || categoria.isEmpty()) {
                throw new IllegalArgumentException("El nombre y la categoría no pueden estar vacíos.");
            }
            double precio = Double.parseDouble(txtPrecio.getText().trim());
            int stock = Integer.parseInt(txtStock.getText().trim());
            if (precio < 0 || stock < 0) {
                throw new IllegalArgumentException("El precio y el stock no pueden ser negativos.");
            }
            producto.setNombre(nombre);
            producto.setPrecio(precio);
            producto.setCantidadStock(stock);
            producto.setCategoria(categoria);
            mostrarInfo();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese un precio (número) y un stock (entero) válidos.",
                    "Dato inválido", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Dato inválido", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void mostrarInfo() {
        lblInfo.setText("<html><b>Producto actualizado:</b><br>"
                + "Nombre: " + producto.getNombre() + "<br>"
                + "Precio: S/ " + String.format("%.2f", producto.getPrecio()) + "<br>"
                + "Stock: " + producto.getCantidadStock() + " unidades<br>"
                + "Categoría: " + producto.getCategoria() + "</html>");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Ejercicio1_Producto().setVisible(true));
    }
}

/** Modelo de datos. */
class Producto {
    private String nombre;
    private double precio;
    private int cantidadStock;
    private String categoria;

    public Producto(String nombre, double precio, int cantidadStock, String categoria) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidadStock = cantidadStock;
        this.categoria = categoria;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }
    public int getCantidadStock() { return cantidadStock; }
    public void setCantidadStock(int cantidadStock) { this.cantidadStock = cantidadStock; }
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
}
