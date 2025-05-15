package ejercicio.DAOs;

import ejercicio.modelos.Pelicula;

public interface PeliuculaDAO {
    void insertarPelicula (Pelicula pelicula);
    Pelicula borrarPeliculaPorId(String id);
}
