package sistemaHotelUtn.generales;

public abstract class Persona {

    // region Atributos
    private int id;
    private static int ultimoId = 0;
    private Usuario usuario = null;
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
        this.id = ++Persona.ultimoId;
        this.usuario = usuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.domicilio = domicilio;
        this.telefono = telefono;
    }

    public Persona(String nombre, String apellido, String dni, String domicilio, String telefono)
    {
        this.id = ++Persona.ultimoId;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.domicilio = domicilio;
        this.telefono = telefono;
    }
    // endregion

    public Persona(String username, String password, String nombre, String apellido,
                   String dni, String domicilio, String telefono)
    {
        this.usuario = new Usuario(username, password);
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.domicilio = domicilio;
        this.telefono = telefono;
    }

    // region Getters y Setters

    public Usuario getUsuario() {
        return usuario;
    }

    public void generarUsuario(String username, String password)
    {
        if( this.usuario != null )
            this.usuario = new Usuario(username, password);
    }

    public int getId() {
        return id;
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
        return "Persona{" +
                "usuario='" + usuario + '\'' +
                ", id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", dni='" + dni + '\'' +
                ", domicilio='" + domicilio + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}

