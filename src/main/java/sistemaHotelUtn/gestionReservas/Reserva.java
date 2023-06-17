package sistemaHotelUtn.gestionReservas;

import sistemaHotelUtn.gestionClientes.Cliente;
import sistemaHotelUtn.gestionHabitaciones.Habitacion;
import sistemaHotelUtn.gestionHabitaciones.ServiciosHabitacion;

import java.time.LocalDateTime;

public class Reserva {

    private final int id;
    private static int ultimoId;
    private LocalDateTime diaCheckIn;
    private LocalDateTime diaCheckOut;
    private Cliente cliente;
    private Habitacion habitacion;
    private Double montoPagar;
    private Boolean estaPago;

    //private ServiciosGastronomia List<ServiciosGastronomia>; agregar servicioGastronomia

    public Reserva(LocalDateTime diaCheckIn, LocalDateTime diaCheckOut, Cliente cliente,
                   Habitacion habitacion)
    {
        this.id = ++Reserva.ultimoId;
        this.diaCheckIn = diaCheckIn;
        this.diaCheckOut = diaCheckOut;
        this.cliente = cliente;
        this.habitacion = habitacion;
    }
    public int getId() {
        return id;
    }
    public static int getUltimoId() {
        return ultimoId;
    }
    public LocalDateTime getDiaCheckIn() {
        return diaCheckIn;
    }
    public void setDiaCheckIn(LocalDateTime diaCheckIn) {
        this.diaCheckIn = diaCheckIn;
    }
    public LocalDateTime getDiaCheckOut() {
        return diaCheckOut;
    }
    public void setDiaCheckOut(LocalDateTime diaCheckOut) {
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
