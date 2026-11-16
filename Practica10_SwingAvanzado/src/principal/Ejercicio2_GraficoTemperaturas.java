package principal;

import javax.swing.*;
import java.awt.*;

/** Ejercicio 2: Gráfico de líneas con la temperatura diaria de una semana. */
public class Ejercicio2_GraficoTemperaturas extends JFrame {

    private static final String[] DIAS = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};
    private final JTextField[] campos = new JTextField[7];
    private final PanelGrafico panel = new PanelGrafico();

    public Ejercicio2_GraficoTemperaturas() {
        super("Temperatura semanal - Gráfico de líneas");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 520);
        setLocationRelativeTo(null);

        JPanel entrada = new JPanel(new GridLayout(2, 7, 5, 3));
        entrada.setBorder(BorderFactory.createEmptyBorder(8, 8, 4, 8));
        for (String d : DIAS) entrada.add(new JLabel(d, SwingConstants.CENTER));
        for (int i = 0; i < 7; i++) {
            campos[i] = new JTextField("", 4);
            campos[i].setHorizontalAlignment(SwingConstants.CENTER);
            entrada.add(campos[i]);
        }

        JButton btn = new JButton("Mostrar Gráfico");
        btn.addActionListener(e -> mostrarGrafico());
        JPanel norte = new JPanel(new BorderLayout());
        norte.add(entrada, BorderLayout.CENTER);
        norte.add(btn, BorderLayout.SOUTH);

        add(norte, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
    }

    private void mostrarGrafico() {
        double[] datos = new double[7];
        try {
            for (int i = 0; i < 7; i++) {
                datos[i] = Double.parseDouble(campos[i].getText().trim().replace(',', '.'));
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese una temperatura numérica para los 7 días.",
                    "Dato inválido", JOptionPane.ERROR_MESSAGE);
            return;
        }
        panel.setDatos(datos);
    }

    /** Lienzo donde se dibuja el gráfico (sobrescribe paintComponent). */
    static class PanelGrafico extends JPanel {
        private double[] datos;

        void setDatos(double[] datos) {
            this.datos = datos;
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int mi = 60, md = 30, ms = 40, minf = 50;
            int w = getWidth() - mi - md;
            int h = getHeight() - ms - minf;

            g2.setColor(Color.BLACK);
            g2.drawString("Temperatura diaria (°C)", mi, 22);
            g2.drawLine(mi, ms, mi, ms + h);                 // eje Y
            g2.drawLine(mi, ms + h, mi + w, ms + h);         // eje X

            if (datos == null) {
                g2.drawString("Ingrese las temperaturas y presione \"Mostrar Gráfico\".", mi + 20, ms + h / 2);
                return;
            }

            double min = datos[0], max = datos[0];
            for (double d : datos) { min = Math.min(min, d); max = Math.max(max, d); }
            if (max == min) { max += 1; min -= 1; }
            double pad = (max - min) * 0.1;
            min -= pad; max += pad;

            // Marcas del eje Y
            for (int i = 0; i <= 5; i++) {
                double val = min + (max - min) * i / 5.0;
                int y = ms + h - (int) (h * i / 5.0);
                g2.setColor(new Color(220, 220, 220));
                g2.drawLine(mi, y, mi + w, y);
                g2.setColor(Color.BLACK);
                g2.drawString(String.format("%.1f", val), 12, y + 4);
            }

            int[] xs = new int[7], ys = new int[7];
            for (int i = 0; i < 7; i++) {
                xs[i] = mi + 30 + (int) ((w - 60) * i / 6.0);
                ys[i] = ms + h - (int) ((datos[i] - min) / (max - min) * h);
            }

            g2.setStroke(new BasicStroke(2.5f));
            g2.setColor(new Color(40, 110, 190));
            g2.drawPolyline(xs, ys, 7);                      // línea que conecta los puntos
            g2.setStroke(new BasicStroke(1f));

            for (int i = 0; i < 7; i++) {
                g2.setColor(new Color(220, 60, 60));
                g2.fillOval(xs[i] - 5, ys[i] - 5, 10, 10);   // punto de cada día
                g2.setColor(Color.BLACK);
                g2.drawString(String.format("%.1f", datos[i]), xs[i] - 12, ys[i] - 10);
                g2.drawString(DIAS[i].substring(0, 3), xs[i] - 10, ms + h + 20);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Ejercicio2_GraficoTemperaturas().setVisible(true));
    }
}
