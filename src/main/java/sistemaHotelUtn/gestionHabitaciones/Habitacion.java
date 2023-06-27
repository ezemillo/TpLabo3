package sistemaHotelUtn.gestionHabitaciones;

import sistemaHotelUtn.generales.Persona;
import sistemaHotelUtn.gestionReservas.Reserva;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Habitacion implements Serializable {

    //region Atributos
    private int id;
    private boolean esReservable;
    private Double precioDiario;
    private int capacidadMax;
    private HashSet<ServiciosHabitacion> serviciosHabitacion;

    //endregion

    //region Constructores
    public Habitacion() {
    }

    public Habitacion(boolean esReservable, Double precioDiario, int capacidadMax,
                      HashSet<ServiciosHabitacion> serviciosHabitacion) {

        this.esReservable = esReservable;
        this.precioDiario = precioDiario;
        this.capacidadMax = capacidadMax;
        this.serviciosHabitacion = serviciosHabitacion;
    }
    //endregion

    //region Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public boolean getEsReservable() {
        return esReservable;
    }

    public void setEsReservable(boolean esReservable) {
        this.esReservable = esReservable;
    }

    public Double getPrecioDiario() {
        return precioDiario;
    }

    public void setPrecioDiario(Double precioDiario) {
        this.precioDiario = precioDiario;
    }

    public int getCapacidadMax() {
        return capacidadMax;
    }


    public void setCapacidadMax(int capacidadMax) {
        this.capacidadMax = capacidadMax;
    }

    public HashSet<ServiciosHabitacion> getServiciosHabitacion() {
        return serviciosHabitacion;
    }

    public void setServiciosHabitacion(HashSet<ServiciosHabitacion> serviciosHabitacion) {
        this.serviciosHabitacion = serviciosHabitacion;
    }
    //endregion

    @Override
    public String toString() {
        return "\n{" +
                "\tIdHabitacion: " + id +
                "||\tPrecio diario: " + precioDiario +
                "||\tCapacidad: " + capacidadMax +
                "||\tHabilitada: " + esReservable +
                "||\tServicios: " + serviciosHabitacion +
                "||" + "}";
    }


    public void listarServicios(){
        System.out.println("Los servicios de la habitacion "+id+" son: ");
        for (ServiciosHabitacion habitacion : serviciosHabitacion) {
            System.out.println(habitacion);
        }
    }
}
