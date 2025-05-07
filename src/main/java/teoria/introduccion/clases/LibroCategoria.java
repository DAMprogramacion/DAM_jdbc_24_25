package teoria.introduccion.clases;

public record LibroCategoria(String libro, String autor, String editorial , String categoria) {
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("===============\n");
        builder.append("LIBRO: ").append(libro).append('\n');
        builder.append("AUTOR: ").append(autor).append('\n');
        builder.append("EDITORIAL: ").append(editorial).append('\n');
        builder.append("CATEGORÍA: ").append(categoria).append('\n');
        return builder.toString();
    }
}
