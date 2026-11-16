package principal;

import javax.swing.*;
import java.awt.*;

/** Actividad 4: Visualización de datos con un gráfico de barras. */
public class AppGraficoBarras extends JPanel {
    private String[] productos = {"Producto A", "Producto B", "Producto C", "Producto D"};
    private int[] ventas = {50, 120, 80, 150};

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int anchoBarra = 50;
        int espacioBarra = 20;
        int margenIzquierdo = 50;
        int margenInferior = 50;
        int alturaMaxima = 200;

        int maxVenta = 0;
        for (int venta : ventas) {
            if (venta > maxVenta) maxVenta = venta;
        }

        for (int i = 0; i < productos.length; i++) {
            int alturaBarra = (int) ((double) ventas[i] / maxVenta * alturaMaxima);
            int x = margenIzquierdo + i * (anchoBarra + espacioBarra);
            int y = getHeight() - margenInferior - alturaBarra;

            g2d.setColor(new Color(100, 150, 200));
            g2d.fillRect(x, y, anchoBarra, alturaBarra);

            g2d.setColor(Color.BLACK);
            g2d.drawString(String.valueOf(ventas[i]), x + (anchoBarra / 4), y - 5);
            g2d.drawString(productos[i], x, getHeight() - margenInferior + 20);
        }

        g2d.drawLine(margenIzquierdo, getHeight() - margenInferior,
                     margenIzquierdo, getHeight() - margenInferior - alturaMaxima);
        g2d.drawString("Ventas", margenIzquierdo - 40, getHeight() - margenInferior - alturaMaxima);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Visualización de Datos - Gráfico de Barras");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.add(new AppGraficoBarras());
        frame.setVisible(true);
    }
}
