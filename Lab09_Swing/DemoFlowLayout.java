// Archivo "DemoFlowLayout.java"
// Prueba de MarcoFlowLayout.
import javax.swing.JFrame;

public class DemoFlowLayout
{
    public static void main(String[] args)
    {
        MarcoFlowLayout marcoFlowLayout = new MarcoFlowLayout();
        marcoFlowLayout.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        marcoFlowLayout.setSize(420, 90);
        marcoFlowLayout.setLocationRelativeTo(null);
        marcoFlowLayout.setVisible(true);
    }
} // fin de la clase DemoFlowLayout
