package teoria.patronDAO.daoUsuarios;

import java.util.List;

public class usuarioDAOImpl implements usuarioDAO{
    //crear conexión
    @Override
    public boolean insertarUsuario(Usuario usuario) {
        return false;
    }

    @Override
    public List<Usuario> getUsuarios() {
        return List.of();  //////////
    }

    @Override
    public Usuario getUsuarioPorDNI(String dni) {
        return null;
    }

    @Override
    public Usuario updateUsuario(Usuario usuario) {
        return null;
    }

    @Override
    public boolean borrarUsuarioPorDNI(String dni) {
        return false;
    }

    @Override
    public void grabarUsuarios(String sPath) {
            /// ////
    }
}
