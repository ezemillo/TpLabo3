package sistemaHotelUtn.gestionEmpleados;

import sistemaHotelUtn.generales.Persona;
import sistemaHotelUtn.generales.Usuario;

public class Empleado extends Persona {

    private Double salario;
    private int antiguedad;

    private String puesto;

    public Empleado(Usuario usuario, String contraseña, String nombre,
                    String apellido, String dni, String domicilio,
                    String telefono, Double salario, int antiguedad)
    {
        super(usuario, nombre, apellido, dni, domicilio, telefono);
        this.salario = salario;
        this.antiguedad = antiguedad;
    }

    public Empleado() {

    }

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



    @Override
    public String toString() {
        return "Empleado{" +
                "salario=" + salario +
                ", antiguedad=" + antiguedad +
                '}';
    }
}
