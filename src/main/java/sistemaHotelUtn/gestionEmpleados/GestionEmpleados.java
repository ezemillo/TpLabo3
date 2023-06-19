package sistemaHotelUtn.gestionEmpleados;

import sistemaHotelUtn.generales.Gestion;
import sistemaHotelUtn.generales.Json.JsonRepo;
import sistemaHotelUtn.gestionClientes.Cliente;
import sistemaHotelUtn.gestionReservas.Reserva;

import java.util.ArrayList;
import java.util.Scanner;

public class GestionEmpleados extends Gestion<Empleado>
{
    public GestionEmpleados(){
        ArrayList<Empleado> empleadosList = new ArrayList<>();
        JsonRepo<Empleado> empleadosJson = new JsonRepo<>("empleados",empleadosList, Empleado.class);
        empleadosList=empleadosJson.cargar();
        this.setLista(empleadosList);
    }

    public Empleado crearNuevoEmpleado()
    {
        Empleado nuevo = new Empleado();

        return nuevo;
    }

}
