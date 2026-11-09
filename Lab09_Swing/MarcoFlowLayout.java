// Archivo "MarcoFlowLayout.java"
// FlowLayout permite que los componentes fluyan a traves de varias lineas.
import java.awt.FlowLayout;
import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JButton;

public class MarcoFlowLayout extends JFrame
{
    private final JButton botonJButtonIzquierda; // boton para establecer la alineacion a la izquierda
    private final JButton botonJButtonCentro;    // boton para establecer la alineacion al centro
    private final JButton botonJButtonDerecha;   // boton para establecer la alineacion a la derecha
    private final FlowLayout esquema;            // objeto esquema
    private final Container contenedor;          // contenedor para establecer el esquema

    // establece la GUI y registra los componentes de escucha de botones
    public MarcoFlowLayout()
    {
        super("FlowLayout - " + Grupo.INTEGRANTES);

        esquema = new FlowLayout();
        contenedor = getContentPane(); // obtiene contenedor para esquema
        setLayout(esquema);

        // establece botonJButtonIzquierda y registra componente de escucha
        botonJButtonIzquierda = new JButton("Izquierda");
        add(botonJButtonIzquierda);
        botonJButtonIzquierda.addActionListener(
            new ActionListener() // clase interna anonima
            {
                @Override
                public void actionPerformed(ActionEvent evento)
                {
                    esquema.setAlignment(FlowLayout.LEFT);
                    // realinea los componentes adjuntos
                    esquema.layoutContainer(contenedor);
                }
            }
        );

        // establece botonJButtonCentro y registra componente de escucha
        botonJButtonCentro = new JButton("Centro");
        add(botonJButtonCentro);
        botonJButtonCentro.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent evento)
                {
                    esquema.setAlignment(FlowLayout.CENTER);
                    esquema.layoutContainer(contenedor);
                }
            }
        );

        // establece botonJButtonDerecha y registra componente de escucha
        botonJButtonDerecha = new JButton("Derecha");
        add(botonJButtonDerecha);
        botonJButtonDerecha.addActionListener(
            new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent evento)
                {
                    esquema.setAlignment(FlowLayout.RIGHT);
                    esquema.layoutContainer(contenedor);
                }
            }
        );
    } // fin del constructor de MarcoFlowLayout
} // fin de la clase MarcoFlowLayout
