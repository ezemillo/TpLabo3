package sistemaHotelUtn.generales;

public abstract class Persona {

    // region Atributos
    private int id;
    private static int ultimoId;
    private Usuario usuario;
    private String nombre;
    private String apellido;
    private String dni;
    private String domicilio;
    private String telefono;

    // endregion


    // region Constructores


    public Persona() {
    }

    public Persona(Usuario usuario, String contraseña, String nombre, String apellido, String dni, String domicilio, String telefono) {
        this.usuario = usuario;
        this.id = Persona.ultimoId;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.domicilio = domicilio;
        this.telefono = telefono;
        Persona.ultimoId++;
    }
    // endregion


    // region Getters y Setters

    public Usuario getUsuario() {
        return usuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static int getUltimoId() {
        return ultimoId;
    }

    public static void setUltimoId(int ultimoId) {
        Persona.ultimoId = ultimoId;
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

