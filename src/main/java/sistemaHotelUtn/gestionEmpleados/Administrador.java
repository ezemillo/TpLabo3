package sistemaHotelUtn.gestionEmpleados;


import sistemaHotelUtn.generales.Usuario;
import sistemaHotelUtn.gestionEmpleados.Empleado;

public class Administrador extends Empleado {

    public Administrador(Usuario usuario, String contraseña, String nombre, String apellido, String dni, String domicilio, String telefono, Double salario, int antiguedad) {
        super(usuario, contraseña, nombre, apellido, dni, domicilio, telefono, salario, antiguedad);
    }



    public void editarEmpleado(Empleado empleado){}
    public void altaEmpleado(){}
    public void bajaEmpleado(Empleado empleado){}

    // public void editarCliente(Cliente cliente){}

    //public void crearHabitacion(){}

    // public void modificarHabitacion(){}


}
