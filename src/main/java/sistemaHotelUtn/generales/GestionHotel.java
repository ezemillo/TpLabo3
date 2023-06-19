package sistemaHotelUtn.generales;

import sistemaHotelUtn.gestionClientes.GestionClientes;
import sistemaHotelUtn.gestionClientes.Cliente;
import sistemaHotelUtn.gestionEmpleados.Empleado;
import sistemaHotelUtn.gestionEmpleados.GestionEmpleados;
import sistemaHotelUtn.gestionEventos.GestionEventos;
import sistemaHotelUtn.gestionHabitaciones.GestionHabitaciones;
import sistemaHotelUtn.gestionReservas.GestionReservas;

public class GestionHotel
{

    private GestionEmpleados gestionEmpleados;
    private GestionClientes gestionClientes;
    private GestionReservas gestionReservas;
    private GestionEventos gestionEventos;
    private GestionHabitaciones gestionHabitaciones;
    private Persona usuarioActual;

    public GestionHotel()
    {


    }

    public void menuPrincipal(){
        if(usuarioActual instanceof Cliente)
        {
            gestionClientes.menuCliente((Cliente) usuarioActual);
        }else
        {
            gestionEmpleados.menuEmpleado((Empleado) usuarioActual);
        }
    }
}
