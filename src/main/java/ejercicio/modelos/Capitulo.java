package ejercicio.modelos;

import java.lang.ref.PhantomReference;

/*numeroCapitulo INTEGER PRIMARY KEY AUTOINCREMENT,
    temporada_id INTEGER NOT NULL,
    nombre TEXT NOT NULL,
    duracion REAL,*/
public class Capitulo {
    private int numeroCapitulo;
    private final int temporadaId;
    private String nombreCapitulo;
    private float duracionCapitulo;

    public Capitulo(int temporadaId, String nombreCapitulo, float duracionCapitulo, int numeroCapitulo) {
        this.temporadaId = temporadaId;
        this.nombreCapitulo = nombreCapitulo;
        this.duracionCapitulo = duracionCapitulo;
        this.numeroCapitulo = numeroCapitulo;
    }
    public Capitulo(int temporadaId, String nombreCapitulo, float duracionCapitulo) {
        this.temporadaId = temporadaId;
        this.nombreCapitulo = nombreCapitulo;
        this.duracionCapitulo = duracionCapitulo;
    }

    public int getNumeroCapitulo() {
        return numeroCapitulo;
    }

    public void setNumeroCapitulo(int numeroCapitulo) {
        this.numeroCapitulo = numeroCapitulo;
    }

    public int getTemporadaId() {
        return temporadaId;
    }

    public String getNombreCapitulo() {
        return nombreCapitulo;
    }

    public void setNombreCapitulo(String nombreCapitulo) {
        this.nombreCapitulo = nombreCapitulo;
    }

    public float getDuracionCapitulo() {
        return duracionCapitulo;
    }

    public void setDuracionCapitulo(float duracionCapitulo) {
        this.duracionCapitulo = duracionCapitulo;
    }
}
