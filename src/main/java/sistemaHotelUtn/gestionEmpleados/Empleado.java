package sistemaHotelUtn.gestionEmpleados;

import sistemaHotelUtn.generales.Persona;
import sistemaHotelUtn.generales.Usuario;

public class Empleado extends Persona {

    //region Atributos
    private Double salario;
    private int antiguedad;
    private String puesto;
    //endregion

    public Empleado() {

    }

    public Empleado(String username, String password)
    {
        this.generarUsuario(username, password);
    }
    public Empleado(Usuario usuario, String contraseña, String nombre,
                    String apellido, String dni, String domicilio,
                    String telefono, Double salario, int antiguedad)
    {
        super(usuario, nombre, apellido, dni, domicilio, telefono);
        this.salario = salario;
        this.antiguedad = antiguedad;
    }


    //region Getters y setters
    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public int getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(int antiguedad) {
        this.antiguedad = antiguedad;
    }

    //endregion

    @Override
    public String toString() {
        return super.toString().replace(
                super.toString().charAt(super.toString().length() - 1),
                ' '
        ) + "\n\tPuesto: " + puesto + "\tSalario: " + salario + "\n\tAntiguedad: " + antiguedad +
                "\n" + "}";
    }
}
