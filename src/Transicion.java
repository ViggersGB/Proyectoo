public class Transicion {

    private String estadoOrigen;
    private String estadoDestino;
    private int peso;

    public Transicion(String estadoOrigen, String estadoDestino, int peso) {
        this.estadoOrigen = estadoOrigen;
        this.estadoDestino = estadoDestino;
        this.peso = peso;
    }

    public String getEstadoOrigen() {
        return estadoOrigen;
    }

    public String getEstadoDestino() {
        return estadoDestino;
    }

    public int getPeso() {
        return peso;
    }

    @Override
    public String toString() {
        return estadoOrigen + " --(" + peso + ")--> " + estadoDestino;
    }
}