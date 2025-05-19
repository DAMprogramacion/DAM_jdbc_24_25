package ejercicio.DAOs;

import ejercicio.modelos.Capitulo;
import ejercicio.modelos.Serie;
import ejercicio.modelos.Temporada;

import java.util.List;

public interface SerieDAO {
    void crearSerie (Serie serie);
    boolean borrarSerie(String id);
    List<Serie> obtenerTodasSeries();
    Serie obtenerSeriePorId(String id);
    void crearTemporada(Temporada temporada);
    void crearCapitulo(Capitulo capitulo);
}
