package principal;

import javax.swing.*;
import java.awt.*;

/** Actividad 2: Gráficos simples con la clase Graphics. */
public class AppGraficos extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLUE);
        g.drawLine(10, 10, 100, 100);          // línea

        g.setColor(Color.RED);
        g.drawRect(50, 50, 100, 50);           // rectángulo

        g.setColor(Color.GREEN);
        g.drawOval(150, 50, 100, 50);          // óvalo

        g.setColor(Color.ORANGE);
        g.fillOval(200, 150, 50, 50);          // círculo lleno

        g.setColor(Color.BLACK);
        g.drawString("Ejemplo de Gráficos en Swing", 10, 200); // texto
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Ejemplo de Gráficos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.add(new AppGraficos());
        frame.setVisible(true);
    }
}
