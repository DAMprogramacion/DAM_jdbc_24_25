package teoria.introduccion.accesoBD;

public class PatronUnico {
    private static PatronUnico patronUnico = null;
    private PatronUnico() {}
    public static PatronUnico getInstance() {
        if (patronUnico == null)
            patronUnico = new PatronUnico();
        return patronUnico;
    }
}
