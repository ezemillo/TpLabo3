package sistemaHotelUtn.gestionReservas;

import sistemaHotelUtn.generales.Gestion;
import sistemaHotelUtn.generales.Json.JsonRepo;
import sistemaHotelUtn.gestionHabitaciones.GestionHabitaciones;
import sistemaHotelUtn.gestionHabitaciones.Habitacion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;


public class GestionReservas extends Gestion<Reserva>
{
    public GestionReservas(){}

    public boolean isDisponiblePorFecha(int idHabitacion, LocalDate checkIn, LocalDate checkOut) {

        for (Reserva reserva: this.getLista()
             ) {
            
            System.out.println("reserva = " + reserva);

            if (idHabitacion == reserva.getHabitacion().getId() && reserva.getEstaActiva()
                    && (checkOut.isAfter(reserva.getDiaCheckIn()) || checkOut.isEqual(reserva.getDiaCheckOut()))
                    && (checkIn.isBefore(reserva.getDiaCheckOut()) || checkIn.isEqual(reserva.getDiaCheckIn()))) {
                return false;

            }
        }
        return true;
    }
    public void cargarReservasJson() {
        ArrayList<Reserva> reservaList = new ArrayList<>();
        JsonRepo<Reserva> reservaJson = new JsonRepo<>("reservas", reservaList, Reserva.class);
        reservaList = reservaJson.cargar();
        this.setLista(reservaList);
    }

    public ArrayList<Habitacion> verHabitacionesDisponiblesPorFechas(LocalDate checkIn, LocalDate checkOut){

        ArrayList<Habitacion> habitacionesDisponibles = new ArrayList<>();
        GestionHabitaciones gestionHabitaciones = new GestionHabitaciones();
        gestionHabitaciones.cargarHabitacionesJson();

        for (Habitacion habitacion: gestionHabitaciones.getLista()
             ) {
            if(isDisponiblePorFecha(habitacion.getId(), checkIn,checkOut)){
                habitacionesDisponibles.add(habitacion);
            }
        }
    return habitacionesDisponibles;
    }

    public void anularReserva(Reserva reserva){
        reserva.setEstaActiva(false);
    }


    public LocalDate solicitarLocalDate(){

        Scanner scan = new Scanner(System.in);
        System.out.println("Ingrese el dia:");
        int day = Integer.parseInt(scan.nextLine());
        System.out.println("Ingrese el mes:");
        int month = Integer.parseInt(scan.nextLine());
        System.out.println("Ingrese el año:");
        int year = Integer.parseInt(scan.nextLine());



        return LocalDate.of(year,month,day);

    }
}

