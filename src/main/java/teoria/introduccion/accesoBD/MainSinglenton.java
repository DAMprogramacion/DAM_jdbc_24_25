package teoria.introduccion.accesoBD;

public class MainSinglenton {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            PatronUnico patronUnico = PatronUnico.getInstance();
            System.out.println(patronUnico);
        }
    }
}
