package teoria.patronDAO.daoUsuarios;

import java.util.List;

public interface usuarioDAO {
    boolean insertarUsuario (Usuario usuario);
    List<Usuario> getUsuarios();
    Usuario getUsuarioPorDNI(String dni);
    Usuario updateUsuario(Usuario usuario);
    boolean borrarUsuarioPorDNI(String dni);
    void grabarUsuarios(String sPath);
}
