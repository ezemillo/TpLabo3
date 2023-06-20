package sistemaHotelUtn.gestionClientes;

import sistemaHotelUtn.generales.Persona;
import sistemaHotelUtn.generales.TipoUsuario;
import sistemaHotelUtn.gestionHabitaciones.GestionHabitaciones;
import sistemaHotelUtn.gestionHabitaciones.Habitacion;
import sistemaHotelUtn.gestionReservas.GestionReservas;
import sistemaHotelUtn.gestionReservas.Reserva;
import sistemaHotelUtn.gestionServiciosGastronomicos.GestionServiciosGastronomicos;
import sistemaHotelUtn.gestionServiciosGastronomicos.ServicioGastronomia;

import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

public class Cliente extends Persona  {

    private double saldo = 0.0;
    private boolean estaActivo;

    public Cliente()
    {
        super();
    }

    public Cliente(String username, String password)
    {
        super();
        this.generarUsuario(username, password, TipoUsuario.CLIENTE);
    }

    public Cliente(String usuario, String contraseña, String nombre, String apellido,
                   String dni, String domicilio, String telefono, double saldo, boolean estaActivo)
    {
        super(usuario, contraseña, nombre, apellido, dni, domicilio, telefono);
        this.saldo = saldo;
        this.estaActivo = estaActivo;
    }
    //region Getters y Setters
    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public boolean isEstaActivo() {
        return estaActivo;
    }

    public void setEstaActivo(boolean estaActivo) {
        this.estaActivo = estaActivo;
    }
    //endregion


    @Override
    public String toString() {
        return super.toString().replace(
                super.toString().charAt(super.toString().length() - 1),
                ' '
        ) + "\tSaldo: " + saldo + "\n\tActivo: " + estaActivo+ "\n" + "}";
    }
}
