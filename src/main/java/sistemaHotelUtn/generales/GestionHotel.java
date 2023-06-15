package sistemaHotelUtn.generales;

import sistemaHotelUtn.gestionClientes.GestionClientes;
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


}
