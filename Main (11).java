public class Main {
    public static void main(String[] args) {
        TiendaModelo modelo = new TiendaModelo();
        TiendaVista vista = new TiendaVista();
        TiendaControlador controlador = new TiendaControlador(modelo, vista);
        controlador.iniciar();
    }
}
