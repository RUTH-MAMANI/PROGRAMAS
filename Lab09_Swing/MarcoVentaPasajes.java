// Archivo "MarcoVentaPasajes.java"
// Ejercicio 2: ingreso de datos para la compra de pasajes de una empresa de transporte terrestre.
// Usa: JLabel, JTextField, JButton, JCheckBox, JRadioButton (ButtonGroup), JComboBox, JList y JPanel.
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class MarcoVentaPasajes extends JFrame
{
    private static final String[] CIUDADES =
        { "Arequipa", "Lima", "Cusco", "Tacna", "Puno", "Ica", "Moquegua", "Trujillo" };
    private static final String[] SERVICIOS = { "Economico", "Standard", "VIP" };

    // Etiquetas: rotulan que informacion se ingresa en cada componente
    private final JLabel etiquetaTitulo;
    private final JLabel etiquetaNombre;
    private final JLabel etiquetaDocumento;
    private final JLabel etiquetaFecha;
    private final JLabel etiquetaOrigen;
    private final JLabel etiquetaDestino;
    private final JLabel etiquetaPiso;
    private final JLabel etiquetaServiciosExtra;
    private final JLabel etiquetaCalidad;

    // Campos de texto: nombres, documento de identidad y fecha de viaje
    private final JTextField campoNombre;
    private final JTextField campoDocumento;
    private final JTextField campoFecha;

    // Cuadros combinados: lugar de origen y de destino
    private final JComboBox<String> comboOrigen;
    private final JComboBox<String> comboDestino;

    // Botones de opcion: 1er o 2do piso (mutuamente excluyentes)
    private final JRadioButton opcionPrimerPiso;
    private final JRadioButton opcionSegundoPiso;
    private final ButtonGroup grupoPiso;

    // Casillas de verificacion: servicios opcionales
    private final JCheckBox casillaAudifonos;
    private final JCheckBox casillaManta;
    private final JCheckBox casillaRevistas;

    // Lista: calidad de servicio
    private final JList<String> listaCalidad;

    // Botones de comando
    private final JButton botonMostrar;
    private final JButton botonReiniciar;

    public MarcoVentaPasajes()
    {
        super("Venta de Pasajes - " + Grupo.INTEGRANTES);
        setLayout(new BorderLayout(5, 5));

        // ---------- Norte: titulo ----------
        etiquetaTitulo = new JLabel("COMPRA DE PASAJES - TRANSPORTE TERRESTRE", JLabel.CENTER);
        etiquetaTitulo.setFont(new Font("SansSerif", Font.BOLD, 16));
        etiquetaTitulo.setOpaque(true);
        etiquetaTitulo.setBackground(new Color(30, 90, 160));
        etiquetaTitulo.setForeground(Color.WHITE);
        etiquetaTitulo.setBorder(BorderFactory.createEmptyBorder(8, 5, 8, 5));
        add(etiquetaTitulo, BorderLayout.NORTH);

        // ---------- Centro: formulario (GridLayout de 2 columnas) ----------
        JPanel panelFormulario = new JPanel(new GridLayout(0, 2, 8, 8));
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(10, 15, 5, 15));

        etiquetaNombre = new JLabel("Nombres y apellidos:");
        campoNombre = new JTextField(20);
        etiquetaDocumento = new JLabel("Documento de identidad (DNI):");
        campoDocumento = new JTextField(10);
        etiquetaFecha = new JLabel("Fecha de viaje (dd/mm/aaaa):");
        campoFecha = new JTextField(10);

        etiquetaOrigen = new JLabel("Origen:");
        comboOrigen = new JComboBox<String>(CIUDADES);
        comboOrigen.setMaximumRowCount(4);
        etiquetaDestino = new JLabel("Destino:");
        comboDestino = new JComboBox<String>(CIUDADES);
        comboDestino.setMaximumRowCount(4);
        comboDestino.setSelectedIndex(1); // Lima por defecto

        etiquetaPiso = new JLabel("Piso del bus:");
        opcionPrimerPiso = new JRadioButton("1er piso", true);
        opcionSegundoPiso = new JRadioButton("2do piso", false);
        grupoPiso = new ButtonGroup(); // relacion logica entre los botones de opcion
        grupoPiso.add(opcionPrimerPiso);
        grupoPiso.add(opcionSegundoPiso);
        JPanel panelPiso = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        panelPiso.add(opcionPrimerPiso);
        panelPiso.add(opcionSegundoPiso);

        etiquetaServiciosExtra = new JLabel("Servicios opcionales:");
        casillaAudifonos = new JCheckBox("Audifonos");
        casillaManta = new JCheckBox("Manta");
        casillaRevistas = new JCheckBox("Revistas");
        JPanel panelExtras = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        panelExtras.add(casillaAudifonos);
        panelExtras.add(casillaManta);
        panelExtras.add(casillaRevistas);

        etiquetaCalidad = new JLabel("Calidad de servicio:");
        listaCalidad = new JList<String>(SERVICIOS);
        listaCalidad.setVisibleRowCount(3);
        listaCalidad.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaCalidad.setSelectedIndex(1); // Standard por defecto

        panelFormulario.add(etiquetaNombre);        panelFormulario.add(campoNombre);
        panelFormulario.add(etiquetaDocumento);     panelFormulario.add(campoDocumento);
        panelFormulario.add(etiquetaFecha);         panelFormulario.add(campoFecha);
        panelFormulario.add(etiquetaOrigen);        panelFormulario.add(comboOrigen);
        panelFormulario.add(etiquetaDestino);       panelFormulario.add(comboDestino);
        panelFormulario.add(etiquetaPiso);          panelFormulario.add(panelPiso);
        panelFormulario.add(etiquetaServiciosExtra);panelFormulario.add(panelExtras);
        panelFormulario.add(etiquetaCalidad);       panelFormulario.add(new JScrollPane(listaCalidad));
        add(panelFormulario, BorderLayout.CENTER);

        // ---------- Sur: botones de comando ----------
        botonMostrar = new JButton("Mostrar resumen");
        botonReiniciar = new JButton("Reiniciar");
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 8));
        panelBotones.add(botonMostrar);
        panelBotones.add(botonReiniciar);
        add(panelBotones, BorderLayout.SOUTH);

        // ---------- Registro de manejadores de eventos ----------
        ManejadorBoton manejador = new ManejadorBoton();
        botonMostrar.addActionListener(manejador);
        botonReiniciar.addActionListener(manejador);
    }

    // clase interna privada para manejar los eventos de los botones de comando
    private class ManejadorBoton implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent evento)
        {
            if (evento.getSource() == botonMostrar)
                mostrarResumen();
            else if (evento.getSource() == botonReiniciar)
                reiniciar();
        }
    }

    // valida los datos y muestra el resumen en un cuadro de dialogo
    private void mostrarResumen()
    {
        String nombre = campoNombre.getText().trim();
        String documento = campoDocumento.getText().trim();
        String fecha = campoFecha.getText().trim();

        if (nombre.isEmpty() || documento.isEmpty() || fecha.isEmpty())
        {
            JOptionPane.showMessageDialog(this, "Complete nombre, documento y fecha de viaje.",
                "Datos incompletos", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!documento.matches("\\d{8}"))
        {
            JOptionPane.showMessageDialog(this, "El DNI debe tener 8 digitos numericos.",
                "Documento invalido", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!fecha.matches("\\d{2}/\\d{2}/\\d{4}"))
        {
            JOptionPane.showMessageDialog(this, "Use el formato dd/mm/aaaa para la fecha.",
                "Fecha invalida", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (comboOrigen.getSelectedItem().equals(comboDestino.getSelectedItem()))
        {
            JOptionPane.showMessageDialog(this, "El origen y el destino deben ser distintos.",
                "Ruta invalida", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String piso = opcionPrimerPiso.isSelected() ? "1er piso" : "2do piso";

        String extras = "";
        if (casillaAudifonos.isSelected()) extras += "Audifonos ";
        if (casillaManta.isSelected())     extras += "Manta ";
        if (casillaRevistas.isSelected())  extras += "Revistas ";
        if (extras.isEmpty()) extras = "Ninguno";

        String calidad = listaCalidad.getSelectedValue();
        if (calidad == null) calidad = "No seleccionada";

        String resumen = String.format(
            "Pasajero: %s%nDocumento: %s%nFecha de viaje: %s%nRuta: %s -> %s%nPiso: %s%n"
            + "Servicios opcionales: %s%nCalidad de servicio: %s",
            nombre, documento, fecha, comboOrigen.getSelectedItem(),
            comboDestino.getSelectedItem(), piso, extras.trim(), calidad);

        JOptionPane.showMessageDialog(this, resumen, "Resumen del pasaje",
            JOptionPane.INFORMATION_MESSAGE);
    }

    // blanquea todos los componentes
    private void reiniciar()
    {
        campoNombre.setText("");
        campoDocumento.setText("");
        campoFecha.setText("");
        comboOrigen.setSelectedIndex(0);
        comboDestino.setSelectedIndex(1);
        opcionPrimerPiso.setSelected(true);
        casillaAudifonos.setSelected(false);
        casillaManta.setSelected(false);
        casillaRevistas.setSelected(false);
        listaCalidad.setSelectedIndex(1);
        campoNombre.requestFocus();
    }
} // fin de la clase MarcoVentaPasajes
