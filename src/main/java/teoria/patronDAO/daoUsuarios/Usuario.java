package teoria.patronDAO.daoUsuarios;

import java.util.Objects;

public class Usuario {
    private final String dni;
    private String nombreUsuario;
    private String apellidos;

    public Usuario(String dni, String nombreUsuario, String apellidos) {
        this.dni = dni;
        this.nombreUsuario = nombreUsuario;
        this.apellidos = apellidos;
    }

    public String getDni() {
        return dni;
    }

    /*public void setDni(String dni) {
        this.dni = dni;
    }*/

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(dni, usuario.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(dni);
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%S", dni, nombreUsuario, apellidos);
    }
}
