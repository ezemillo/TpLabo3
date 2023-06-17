package sistemaHotelUtn;


import sistemaHotelUtn.gestionClientes.Cliente;
import sistemaHotelUtn.gestionEventos.Evento;
import sistemaHotelUtn.gestionEventos.GestionEventos;
import sistemaHotelUtn.gestionHabitaciones.Habitacion;
import sistemaHotelUtn.gestionHabitaciones.ServiciosHabitacion;
import sistemaHotelUtn.gestionReservas.Reserva;

import java.time.LocalDate;
import java.time.LocalDateTime; //test clase evento
import java.time.format.DateTimeFormatter; //test clase evento
import java.util.ArrayList;

public class Main
{
    public static void testearClaseEvento()
    {
        LocalDateTime t = LocalDateTime.of(2023, 6, 4, 20, 00);
        LocalDateTime t2 = LocalDateTime.of(2023, 6, 4, 22, 00);
        System.out.println(t);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String formattedDateTime= t.format(dateTimeFormatter);
        System.out.println(formattedDateTime);

        Evento e = new Evento("Mardel Valley", "UTN MDP", 200,t,t2);
        Evento e2 = new Evento("Hackathon", "Accenture", 70,t,t2);

        System.out.println(e.getFechaHoraInicio());

        System.out.println(e);
        System.out.println(e2);

    }

    public static void testearClaseGestionEvento()
    {
        LocalDateTime t = LocalDateTime.of(2023, 6, 4, 20, 00);
        LocalDateTime t2 = LocalDateTime.of(2023, 6, 4, 22, 00);
        System.out.println(t);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String formattedDateTime= t.format(dateTimeFormatter);
        System.out.println(formattedDateTime);

        Evento e = new Evento("Mardel Valley", "UTN MDP", 200,t,t2);
        Evento e2 = new Evento("Hackathon", "Accenture", 70,t,t2);
        Evento e3 = new Evento("Encuentro Innovación", "Facultad Ingeniería", 30);

        GestionEventos ge = new GestionEventos();

        ge.agregar(e);
        ge.agregar(e2);
        ge.agregar(e3);

        System.out.println( ge.listar() );

    }
    public static void testearClaseGestionHotel()
    {
        ArrayList<ServiciosHabitacion> serviciosHabitacionList = new ArrayList<ServiciosHabitacion>();
        serviciosHabitacionList.add(ServiciosHabitacion.JACUZZI);

        Cliente cliente = new Cliente("usuario1","1234","eze","fran","3562","gascon 1000","155",0.0,true);
        Habitacion habitacion = new Habitacion(true,250.20,3,serviciosHabitacionList);
        Reserva reserva = new Reserva(LocalDateTime.of(2023,7,25, 15,30),
                LocalDateTime.of(2023,7,28, 15, 30),cliente,habitacion);

        System.out.println("habitacion = " +habitacion );
        System.out.println("cliente = " + cliente );
        System.out.println("reserva = " +reserva);

    }
    public static void main(String[] args)
    {
        //TESTS

        //testearClaseGestionHotel();
        //testearClaseEvento();
        testearClaseGestionEvento();

        //abrir 5 json

        //****comienza el loop

        //logearse o registrarse

        //mostrar menu disponible (si es empleado o cliente)

        //realizar operacion y guardar en listas

        //****salida del loop

        //actualizar 5 json si hay cambios
    }
}