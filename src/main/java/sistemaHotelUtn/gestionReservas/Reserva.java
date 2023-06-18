package sistemaHotelUtn.gestionReservas;

import com.fasterxml.jackson.annotation.JsonFormat;
import sistemaHotelUtn.gestionClientes.Cliente;
import sistemaHotelUtn.gestionHabitaciones.Habitacion;
import sistemaHotelUtn.gestionHabitaciones.ServiciosHabitacion;

import java.io.Serializable;
import java.time.LocalDate;


public class Reserva implements Serializable {

    private  int id;
    private static int ultimoId;
    private boolean estaPago;
    private LocalDate diaCheckIn;
    private LocalDate diaCheckOut;
    private Cliente cliente;
    private Habitacion habitacion;
    private double saldo;
    private double montoPagar;

    //private ServiciosGastronomia List<ServiciosGastronomia>; agregar servicioGastronomia

    public Reserva() {}
    public Reserva(LocalDate diaCheckIn, LocalDate diaCheckOut, Cliente cliente, Habitacion habitacion)
    {
        this.id = Reserva.ultimoId;
        Reserva.ultimoId++;
        this.diaCheckIn = diaCheckIn;
        this.diaCheckOut = diaCheckOut;
        this.cliente = cliente;
        this.habitacion = habitacion;
    }
    public int getId()
    {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDiaCheckIn() {
        return diaCheckIn;
    }
    public void setDiaCheckIn(LocalDate diaCheckIn) {
        this.diaCheckIn = diaCheckIn;
    }
    public LocalDate getDiaCheckOut() {
        return diaCheckOut;
    }
    public void setDiaCheckOut(LocalDate diaCheckOut) {
        this.diaCheckOut = diaCheckOut;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public Habitacion getHabitacion() {
        return habitacion;
    }
    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    public Double getMontoPagar() {
        return montoPagar;
    }

    public void setMontoPagar(Double montoPagar) {
        this.montoPagar = (double) this.diaCheckIn.compareTo(this.diaCheckOut)*this.habitacion.getPrecioDiario();
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "id=" + id +
                ", diaCheckIn=" + diaCheckIn +
                ", diaCheckOut=" + diaCheckOut +
                ", cliente=" + cliente +
                ", habitacion=" + habitacion +
                ", saldo=" + montoPagar +
                '}';
    }
}
