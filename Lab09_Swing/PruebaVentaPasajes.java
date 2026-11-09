// Archivo "PruebaVentaPasajes.java"
// Prueba de MarcoVentaPasajes (Ejercicio 2).
import javax.swing.JFrame;

public class PruebaVentaPasajes
{
    public static void main(String[] args)
    {
        MarcoVentaPasajes marcoVentaPasajes = new MarcoVentaPasajes();
        marcoVentaPasajes.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        marcoVentaPasajes.setSize(520, 580);
        marcoVentaPasajes.setLocationRelativeTo(null);
        marcoVentaPasajes.setVisible(true);
    }
} // fin de la clase PruebaVentaPasajes
