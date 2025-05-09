package teoria.introduccion.inserccion.libros;

import teoria.introduccion.borradoYactualizacion.libros.BorradoYActualizacionLibros;
import teoria.introduccion.clases.Libro;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        InsertarLibros insertarLibros = new InsertarLibros();
        Libro libro = new Libro("libro nuevo 1", "editorial", "autor");
        boolean exito = insertarLibros.insertarLibro(libro, 55);
        System.out.printf("Insertado libro? %B%n", exito);
        exito = insertarLibros.insertarLibro(libro, 1);
        System.out.printf("Insertado libro? %B%n", exito);
        Libro libro1 = new Libro("libro nuevo 2", "editorial", "autor");
        Libro libro2 = new Libro("libro nuevo 3", "editorial", "autor");
        Libro libro3 = new Libro("libro nuevo 4", "editorial", "autor");

        Map<Libro, Integer> libros = Map.of(libro, 2, libro1, 4, libro2, 55, libro3, 5);
        int librosInsertados = insertarLibros.insertarMuchosLibros(libros);
        System.out.printf("Insertados: %d%n", librosInsertados);
        System.out.println("=========borrado de libros=================");
        BorradoYActualizacionLibros borradoYActualizacionLibros = new BorradoYActualizacionLibros();
        int id = 28;
        exito = borradoYActualizacionLibros.eliminarLibroPorId(id);
        System.out.printf("Borrado libro con id: %d, %B%n", id, exito);
        System.out.println("=========actualización de libros=================");
        Libro libro4 = new Libro(38, "XXXXXX", "YYYYY", "ZZZZZZ");
        Libro libroActualizado = borradoYActualizacionLibros.actualizarLibroPorId(libro4);
        if (libroActualizado != null)
            System.out.printf("Actualizado libro: %s%n", libroActualizado);
        else
            System.out.println("No existe ese libro");
        Libro libro5 = new Libro(388, "XXXXXX", "YYYYY", "ZZZZZZ");
        libroActualizado = borradoYActualizacionLibros.actualizarLibroPorId(libro5);
        if (libroActualizado != null)
            System.out.printf("Actualizado libro: %s%n", libroActualizado);
        else
            System.out.println("No existe ese libro");

    }
}
