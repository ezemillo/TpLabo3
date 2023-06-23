package sistemaHotelUtn.generales;

import java.io.Serializable;

public abstract class Persona implements Serializable {

    // region Atributos
    private int id = 0;
    private static int ultimoId = 0;
    private Usuario usuario = new Usuario();
    private boolean idEstaAsignado = false;
    private String nombre;
    private String apellido;
    private String dni;
    private String domicilio;
    private String telefono;

    // endregion


    // region Constructores


    public Persona() {
    }

    public Persona(Usuario usuario, String nombre, String apellido, String dni,
                   String domicilio, String telefono) {
        this.usuario = usuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.domicilio = domicilio;
        this.telefono = telefono;
    }

    public Persona(String nombre, String apellido, String dni, String domicilio, String telefono) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.domicilio = domicilio;
        this.telefono = telefono;
    }

    public Persona(String username, String password, String nombre, String apellido,
                   String dni, String domicilio, String telefono) {
        this.usuario = new Usuario(username, password);
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.domicilio = domicilio;
        this.telefono = telefono;
    }

    // endregion


    // region Getters y Setters

    public Usuario getUsuario() {
        return usuario;
    }

    public void generarUsuario(String username, String password, TipoUsuario tipoUsuario) {
        this.usuario = new Usuario(username, password, tipoUsuario);
    }

    public int getId() {
        return id;
    }

    public void asignarIdAutoincremental()
    {
        if( ! idEstaAsignado )
        {
            this.id = ++Persona.ultimoId;
            this.idEstaAsignado = true;
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    // endregion

    @Override
    public String toString() {
        return "{" +
                "\tId: " + id +
                "||\tNombre: " + nombre +
                "||\tApellido: " + apellido +
                "||\tDni: " + dni +
                "||\tDomicilio: " + domicilio +
                "||\tTelefono: " + telefono +
                "||\tUsuario: " + usuario.getUsername() + "\n" + "}";
    }
}

