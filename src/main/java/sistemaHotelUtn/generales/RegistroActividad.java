package sistemaHotelUtn.generales;

import java.time.LocalDateTime;
import java.util.HashMap;

public class RegistroActividad
{
    private HashMap<Usuario, LocalDateTime> usuariosLogeados = new HashMap<Usuario, LocalDateTime>();

    public RegistroActividad(){}

    public void agregarUsuarioRegistro(Usuario usuario, LocalDateTime fechaHora)
    {
        this.usuariosLogeados.put(usuario, fechaHora);
    }

    public String listarActividadUsuarios()
    {
        String lista = "";



        return lista;
    }
}
