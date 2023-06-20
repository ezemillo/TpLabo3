package sistemaHotelUtn.gestionEmpleados;

import sistemaHotelUtn.generales.Persona;
import sistemaHotelUtn.generales.TipoUsuario;
import sistemaHotelUtn.generales.Usuario;

public class Empleado extends Persona {

    //region Atributos
    private double salario = 200000.00;
    private int antiguedad;
    private String puesto = "Empleado";
    //endregion

    public Empleado() {

    }

    public Empleado(String username, String password)
    {
        this.generarUsuario(username, password, TipoUsuario.EMPLEADO);
    }

    public Empleado(String username, String password, String nombre,
                    String apellido, String dni, String domicilio,
                    String telefono, double salario, int antiguedad, String puesto)
    {
        super(username, password, nombre, apellido, dni, domicilio, telefono);
        this.salario = salario;
        this.antiguedad = antiguedad;
        this.puesto = puesto;
    }

    public Empleado(String nombre, String apellido, String dni, String domicilio,
                    String telefono, double salario, int antiguedad, String puesto)
    {
        super(nombre, apellido, dni, domicilio, telefono);
        this.salario = salario;
        this.antiguedad = antiguedad;
        this.puesto = puesto;
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
        ) + "\tPuesto: " + puesto + "\n\tSalario: $" + salario + "\n\tAntiguedad: " + antiguedad +
                "\n" + "}";
    }
}
