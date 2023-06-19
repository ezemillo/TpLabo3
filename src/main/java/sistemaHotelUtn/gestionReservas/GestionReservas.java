package sistemaHotelUtn.gestionReservas;

import sistemaHotelUtn.generales.Gestion;
import sistemaHotelUtn.generales.Json.JsonRepo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GestionReservas extends Gestion<Reserva>
{
    public GestionReservas(){

        ArrayList<Reserva> reservaList = new ArrayList<>();
        JsonRepo<Reserva> reservaJson = new JsonRepo<>("reservas",reservaList, Reserva.class);
        reservaList =reservaJson.cargar();
        this.setLista(reservaList);

    }

    public boolean isDisponiblePorFecha(int idHabitacion, LocalDate checkIn, LocalDate checkOut) {

        for (Reserva reserva: this.getLista()
             ) {

            if (idHabitacion == reserva.getId()
                    && (checkOut.isAfter(reserva.getDiaCheckIn()) || checkOut.isEqual(reserva.getDiaCheckOut()))
                    && (checkIn.isBefore(reserva.getDiaCheckOut()) || checkIn.isEqual(reserva.getDiaCheckIn()))) {
                return false;

            }
        }
        return true;
    }



    public void menuReserva(){}
}
