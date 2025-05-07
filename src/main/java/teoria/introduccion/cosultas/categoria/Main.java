package teoria.introduccion.cosultas.categoria;

import teoria.introduccion.clases.Categoria;
import teoria.introduccion.clases.LibroCategoria;
import teoria.introduccion.cosultas.vistas.ConsultaLibroCategoria;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ConsultasTablaCategoria tablaCategoria = new ConsultasTablaCategoria();
        List<Categoria> categorias = tablaCategoria.getCategorias();
        categorias.forEach(System.out::println);
        System.out.println("=============categorías por id================");
        int idCategoria = 1;
        String categoria = tablaCategoria.getNombreCategoriaById(idCategoria);
        System.out.printf("Para id: %d, la categoría es: %S%n", idCategoria, categoria);
        idCategoria = 15;
        categoria = tablaCategoria.getNombreCategoriaById(idCategoria);
        System.out.printf("Para id: %d, la categoría es: %S%n", idCategoria, categoria);
        System.out.println("============libros categorias==============");
        ConsultaLibroCategoria libroCategoria = new ConsultaLibroCategoria();
        List<LibroCategoria> libroCategorias = libroCategoria.getLibroCategorias();
        libroCategorias.forEach(System.out::println);
        System.out.println("============libros categorias por categorias==============");
        String categoriaBusqueda = "Ofi OR 1 == 1";
        libroCategorias = libroCategoria.getLibrosCategoriaByCategoria(categoriaBusqueda);
        libroCategorias.forEach(System.out::println);



    }
}
