package teoria.patronDAO.daoUsuarios;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=======todos los usuarios======");
        UsuarioDAOImpl usuarioDAO = new UsuarioDAOImpl();
        List<Usuario> usuarios = usuarioDAO.getUsuarios();
        usuarios.forEach(System.out::println);
        System.out.println("usuarios por dni======");
        String dni =  "11111111";
        System.out.println(usuarioDAO.getUsuarioPorDNI(dni));
        dni =  "111";
        System.out.println(usuarioDAO.getUsuarioPorDNI(dni));
        System.out.println("======insertar usuarios=======");
        Usuario usuario = new Usuario("12345678", "jacinto", "pérez pérez");
        boolean exito =  usuarioDAO.insertarUsuario(usuario);
        System.out.printf("¿Insertado usuario? %B%n", exito);
        System.out.println("======actualizar usuarios=======");
        usuario = new Usuario("11111111", "john", "steven steven");
        Usuario usuarioNew = usuarioDAO.updateUsuario(usuario);
        System.out.println(usuarioNew);
        usuario = new Usuario("11111", "luisa", "moreno moreno");
        usuarioNew = usuarioDAO.updateUsuario(usuario);
        System.out.println(usuarioNew);
        System.out.println("======borrado usuarios=======");
        boolean exito1 = usuarioDAO.borrarUsuarioPorDNI("22222222");
        System.out.printf("¿Borrado usuario? %B%n", exito1);


    }
}
