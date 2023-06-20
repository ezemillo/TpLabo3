package sistemaHotelUtn.generales;

import java.io.Serializable;

public class Usuario implements Serializable {
    // region Atributos
    private String username;
    private String password;
    private TipoUsuario tipoUsuario; // cliente o empleado

    //endregion

    //region Constructores
    public Usuario() {
    }

    //endregion
    public Usuario(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Usuario(String username, String password, TipoUsuario tipoUsuario) {
        this.username = username;
        this.password = password;
        this.tipoUsuario = tipoUsuario;
    }

    //region Getters y Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    //endregion
    public String toString() {
        return "{\n" +
                "\tUsername: " + username +
                "\n\tPassword: " + password +
                "\n\tTipo Usuario: " + tipoUsuario +
                "\n" + "}";
    }
}
