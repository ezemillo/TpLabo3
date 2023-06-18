package sistemaHotelUtn.generales;

import java.io.Serializable;

public class Usuario implements Serializable
{
    private String username;
    private String password;

    public Usuario(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    public Usuario() {
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

    public String toString()
    {
        return "{\n" +
                "\tUsername: " + username +
                "\n}";
    }
}
