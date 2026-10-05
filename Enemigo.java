public class Enemigo {
    private String nombre;
    private int salud;
    private int saludMaxima;
    private int nivel;
    private String tipo;

    public Enemigo(String nombre, int salud, int nivel, String tipo) {
        this.nombre = nombre;
        this.salud = salud;
        this.saludMaxima = salud;
        this.nivel = nivel;
        this.tipo = tipo;
    }

    public String getNombre() { return nombre; }
    public int getSalud() { return salud; }
    public int getNivel() { return nivel; }
    public String getTipo() { return tipo; }
    public boolean estaVivo() { return salud > 0; }

    public int atacar(Jugador jugador, int multiplicador) {
        int danio = nivel * multiplicador;
        jugador.recibirDanio(danio);
        return danio;
    }

    public void recibirDanio(int danio) {
        salud = Math.max(0, salud - danio);
    }

    public int descansar() {
        int antes = salud;
        salud = Math.min(saludMaxima, salud + 5);
        return salud - antes;
    }
}
