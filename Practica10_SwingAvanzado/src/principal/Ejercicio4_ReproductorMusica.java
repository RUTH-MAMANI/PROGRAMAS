package principal;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;

/** Ejercicio 4: Reproducir, pausar y reanudar una pista de audio con Clip. */
public class Ejercicio4_ReproductorMusica extends JFrame {

    private Clip clip;
    private long posicionPausa = 0;   // microsegundos donde se pausó
    private final JLabel lblEstado = new JLabel("Detenido", SwingConstants.CENTER);

    public Ejercicio4_ReproductorMusica() {
        super("Reproductor de Música");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 140);
        setLocationRelativeTo(null);

        JButton btnPlay = new JButton("Reproducir");
        JButton btnPausa = new JButton("Pausar");
        JButton btnReanudar = new JButton("Reanudar");

        btnPlay.addActionListener(e -> reproducir());
        btnPausa.addActionListener(e -> pausar());
        btnReanudar.addActionListener(e -> reanudar());

        JPanel botones = new JPanel(new GridLayout(1, 3, 10, 10));
        botones.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
        botones.add(btnPlay); botones.add(btnPausa); botones.add(btnReanudar);

        add(botones, BorderLayout.CENTER);
        add(lblEstado, BorderLayout.SOUTH);
    }

    private void reproducir() {
        try {
            if (clip != null && clip.isOpen()) clip.close();
            AudioInputStream ais = AudioSystem.getAudioInputStream(new File("audio/musica.wav"));
            clip = AudioSystem.getClip();
            clip.open(ais);
            clip.setMicrosecondPosition(0);   // desde el inicio
            clip.start();
            posicionPausa = 0;
            lblEstado.setText("Reproduciendo...");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "No se pudo cargar el audio: " + ex.getMessage(),
                    "Error de audio", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void pausar() {
        if (clip != null && clip.isRunning()) {
            posicionPausa = clip.getMicrosecondPosition();  // guarda dónde se detuvo
            clip.stop();
            lblEstado.setText("En pausa");
        }
    }

    private void reanudar() {
        if (clip != null && !clip.isRunning()) {
            clip.setMicrosecondPosition(posicionPausa);     // continúa desde ese punto
            clip.start();
            lblEstado.setText("Reproduciendo...");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Ejercicio4_ReproductorMusica().setVisible(true));
    }
}
