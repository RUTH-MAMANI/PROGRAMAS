public class Jugador {
    private String nombre;
    private int salud;
    private int saludMaxima;
    private int nivel;
    private InventarioModel inventario;
    private Item equipado;   

    public Jugador(String nombre, int salud, int nivel) {
        this.nombre = nombre;
        this.salud = salud;
        this.saludMaxima = salud;
        this.nivel = nivel;
        this.inventario = new InventarioModel();
    }

    public String getNombre() { return nombre; }
    public int getSalud() { return salud; }
    public int getSaludMaxima() { return saludMaxima; }
    public int getNivel() { return nivel; }
    public InventarioModel getInventario() { return inventario; }
    public Item getEquipado() { return equipado; }
    public boolean estaVivo() { return salud > 0; }

    public int atacar(Enemigo enemigo) {
        int base = (equipado != null) ? equipado.getValor() : 2;
        int danio = base + nivel;
        enemigo.recibirDanio(danio);
        return danio;
    }

    public String usarObjeto(Item item) {
        if (item.getTipo() == TipoItem.ARMA) {
            equipado = item;
            return nombre + " equipó " + item.getNombre() + ".";
        }
        int antes = salud;
        salud = Math.min(saludMaxima, salud + item.getValor());
        item.consumir();
        return nombre + " usó " + item.getNombre() + " y recuperó " + (salud - antes) + " de salud.";
    }

    public void recibirDanio(int danio) {
        salud = Math.max(0, salud - danio);
    }
}
