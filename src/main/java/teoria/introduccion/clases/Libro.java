package teoria.introduccion.clases;

import java.util.Objects;

public class Libro {
    private int idLibro;
    private String nombreLibro;
    private String editorial;
    private String autor;

    public Libro(int idLibro, String nombreLibro, String editorial, String autor) {
        this.idLibro = idLibro;
        this.nombreLibro = nombreLibro;
        this.editorial = editorial;
        this.autor = autor;
    }

    public Libro(String nombreLibro, String editorial, String autor) {
        this.nombreLibro = nombreLibro;
        this.editorial = editorial;
        this.autor = autor;
    }

    public int getIdLibro() {
        return idLibro;
    }

    /*public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }*/

    public String getNombreLibro() {
        return nombreLibro;
    }

    public void setNombreLibro(String nombreLibro) {
        this.nombreLibro = nombreLibro;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return String.format("%d,%s,%s,%s", idLibro, nombreLibro, editorial, autor);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Libro libro = (Libro) o;
        return idLibro == libro.idLibro;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idLibro);
    }
}
