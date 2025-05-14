package ejercicio.modelos;

import java.util.ArrayList;
import java.util.List;

public class Serie extends Video{
    private String idSerie;
    private List<Temporada> temporadas = new ArrayList<>();

    public Serie(String id, String titulo, String sinopsis, String imagen, String descarga, float duracion) {
        super(id, titulo, sinopsis, imagen, descarga, duracion);
        this.idSerie = id;
    }

    public String getIdSerie() {
        return idSerie;
    }
    //añadimos temporadas
    public boolean addTemporada (Temporada temporada) {
        return temporadas.add(temporada);
    }

    public List<Temporada> getTemporadas() {
        return temporadas;
    }
}
