package principal;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;

/** Ejercicio 3: Reproductor de efectos de sonido (Clip, formato WAV). */
public class Ejercicio3_EfectosSonido extends JFrame {

    public Ejercicio3_EfectosSonido() {
        super("Reproductor de Efectos de Sonido");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(420, 150);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(1, 3, 10, 10));

        agregarBoton("Aplausos", "audio/aplausos.wav");
        agregarBoton("Campana", "audio/campana.wav");
        agregarBoton("Explosión", "audio/explosion.wav");
    }

    private void agregarBoton(String texto, String ruta) {
        JButton b = new JButton(texto);
        b.addActionListener(e -> reproducir(ruta));
        add(b);
    }

    private void reproducir(String ruta) {
        try (AudioInputStream ais = AudioSystem.getAudioInputStream(new File(ruta))) {
            Clip clip = AudioSystem.getClip();
            clip.open(ais);
            clip.addLineListener(ev -> {                 // libera el recurso al terminar
                if (ev.getType() == LineEvent.Type.STOP) clip.close();
            });
            clip.start();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "No se pudo reproducir: " + ruta + "\n" + ex.getMessage(),
                    "Error de audio", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Ejercicio3_EfectosSonido().setVisible(true));
    }
}
