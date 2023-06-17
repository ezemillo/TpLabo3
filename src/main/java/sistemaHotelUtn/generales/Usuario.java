package sistemaHotelUtn.generales;

public class Usuario
{
    private String username;
    private String password;
    private String tipoUsuario; // cliente o empleado

    public Usuario(String username, String password, String tipoUsuario)
    {
        this.username = username;
        this.password = password;
        this.tipoUsuario = tipoUsuario;
    }

    public Usuario(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

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

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String toString()
    {
        return "{\n" +
                "\tUsername: " + username +
                "\tTipo Usuario: " + tipoUsuario +
                "\n}";
    }
}
