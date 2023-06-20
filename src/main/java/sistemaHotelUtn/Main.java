package sistemaHotelUtn;


import sistemaHotelUtn.generales.GestionHotel;
import sistemaHotelUtn.generales.Json.JsonRepo;
import sistemaHotelUtn.gestionClientes.Cliente;
import sistemaHotelUtn.gestionEmpleados.Empleado;
import sistemaHotelUtn.gestionEventos.Evento;
import sistemaHotelUtn.gestionEventos.GestionEventos;
import sistemaHotelUtn.gestionHabitaciones.Habitacion;
import sistemaHotelUtn.gestionHabitaciones.ServiciosHabitacion;
import sistemaHotelUtn.gestionReservas.GestionReservas;
import sistemaHotelUtn.gestionReservas.Reserva;

import javax.swing.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime; //test clase evento
import java.time.format.DateTimeFormatter; //test clase evento
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void testearClaseEvento() {
        LocalDateTime t = LocalDateTime.of(2023, 6, 4, 20, 00);
        LocalDateTime t2 = LocalDateTime.of(2023, 6, 4, 22, 00);
        System.out.println(t);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String formattedDateTime = t.format(dateTimeFormatter);
        System.out.println(formattedDateTime);

        Evento e = new Evento("Mardel Valley", "UTN MDP", 200, t, t2);
        Evento e2 = new Evento("Hackathon", "Accenture", 70, t, t2);

        System.out.println(e.getFechaHoraInicio());

        System.out.println(e);
        System.out.println(e2);

    }

    public static void testearClaseGestionEvento() {
        LocalDateTime t = LocalDateTime.of(2023, 6, 4, 20, 00);
        LocalDateTime t2 = LocalDateTime.of(2023, 6, 4, 22, 00);
        System.out.println(t);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String formattedDateTime = t.format(dateTimeFormatter);
        System.out.println(formattedDateTime);

        Evento e = new Evento("Mardel Valley", "UTN MDP", 200, t, t2);
        Evento e2 = new Evento("Hackathon", "Accenture", 70, t, t2);
        Evento e3 = new Evento("Encuentro Innovación", "Facultad Ingeniería", 30);

        GestionEventos ge = new GestionEventos();

        ge.agregar(e);
        ge.agregar(e2);
        ge.agregar(e3);

        //System.out.println( ge.listar() );
        JsonRepo<Evento> jsonEventos = new JsonRepo<>("eventos", ge.getLista(), Evento.class);

        jsonEventos.guardar();

        ArrayList<Evento> listaTest = new ArrayList<>();

        listaTest = jsonEventos.cargar();

        System.out.println("Contenido json:");
        System.out.println(listaTest);

    }

    public static void testearClaseGestionHotel() {
        ArrayList<ServiciosHabitacion> serviciosHabitacionList = new ArrayList<ServiciosHabitacion>();
        serviciosHabitacionList.add(ServiciosHabitacion.JACUZZI);

        Cliente cliente = new Cliente("usuario1", "1234", "eze", "fran", "3562", "gascon 1000", "155", 0.0, true);
        Habitacion habitacion = new Habitacion(true, 250.20, 3, serviciosHabitacionList);
        Reserva reserva = new Reserva(LocalDate.of(2023, 7, 25),
                LocalDate.of(2023, 7, 28), cliente, habitacion);

        System.out.println("habitacion = " + habitacion);
        System.out.println("cliente = " + cliente);
        System.out.println("reserva = " + reserva);

    }

    /*public static void testearClaseGestionReserva()
    {


        GestionReservas gestionReservas = new GestionReservas();
        gestionReservas.
        System.out.println("lista" + gestionReservas.getLista());

        if (gestionReservas.isDisponiblePorFecha(0,t,t2 )){
        System.out.println("habitacion disponible");
        }else{
            System.out.println("no disponible");
        }

    }*/

    public static void testearJson() {

        ArrayList<ServiciosHabitacion> serviciosHabitacionList = new ArrayList<ServiciosHabitacion>();
        serviciosHabitacionList.add(ServiciosHabitacion.JACUZZI);
        Habitacion habitacion = new Habitacion(true, 250.20, 3, serviciosHabitacionList);

        Cliente cliente = new Cliente("usuario", "1234", "eze", "fran", "3562", "gascon 1000", "155", 0.0, true);
        Reserva reserva = new Reserva(LocalDate.of(2023, 7, 25), LocalDate.of(2023, 7, 28), cliente, habitacion);
        Reserva reserva2 = new Reserva(LocalDate.of(2023, 8, 2), LocalDate.of(2023, 7, 28), cliente, habitacion);


        ArrayList<Reserva> reservaList = new ArrayList<>();
        reservaList.add(reserva);
        reservaList.add(reserva2);


        JsonRepo<Reserva> jsonReserva = new JsonRepo<>("reservas", reservaList, Reserva.class);
        System.out.println("reservaList = " + reservaList);


        jsonReserva.guardar();

        reservaList = jsonReserva.cargar();
        System.out.println("reservaList = " + reservaList);

    }

    public static void main(String[] args) {

        GestionHotel gestionHotel = new GestionHotel();

        gestionHotel.mostrarMenuPrincipal();


    }
}