package sistemaHotelUtn.gestionHabitaciones;

import java.io.Serializable;
import java.util.ArrayList;

public class Habitacion implements Serializable {

    //region Atributos
    private int id;
    private static int ultimoId;
    private boolean esReservable;
    private Double precioDiario;
    private int capacidadMax;
    private ArrayList serviciosHabitacion;

    //endregion

    //region Constructores
    public Habitacion() {
    }

    public Habitacion(boolean esReservable, Double precioDiario, int capacidadMax,
                      ArrayList<ServiciosHabitacion> serviciosHabitacion) {
        this.id = ultimoId;
        Habitacion.ultimoId++;
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

    public ArrayList getServiciosHabitacion() {
        return serviciosHabitacion;
    }

    public void setServiciosHabitacion(ArrayList<ServiciosHabitacion> serviciosHabitacion) {
        this.serviciosHabitacion = serviciosHabitacion;
    }
    //endregion

    @Override
    public String toString() {
        return "{\n" +
                "\tId: " + id +
                "\n\tPrecio diario: " + precioDiario +
                "\n\tCapacidad: " + capacidadMax +
                "\n\tReservable: " + esReservable +
                "\n\tServicios: " + serviciosHabitacion +
                "\n" + "}";
    }
}
