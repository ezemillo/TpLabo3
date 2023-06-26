package sistemaHotelUtn.gestionClientes;

import sistemaHotelUtn.generales.Gestion;
import sistemaHotelUtn.generales.Json.JsonRepo;
import sistemaHotelUtn.generales.Persona;
import sistemaHotelUtn.generales.TipoUsuario;
import sistemaHotelUtn.gestionHabitaciones.GestionHabitaciones;
import sistemaHotelUtn.gestionHabitaciones.Habitacion;
import sistemaHotelUtn.gestionReservas.GestionReservas;
import sistemaHotelUtn.gestionReservas.Reserva;
import sistemaHotelUtn.gestionServiciosGastronomicos.GestionServiciosGastronomicos;
import sistemaHotelUtn.gestionServiciosGastronomicos.ServicioGastronomia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cliente extends Persona  {

    private double saldo = 0.0;
    private boolean estaActivo= true;

    public Cliente()
    {
        //super();
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
        this.estaActivo = true;
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
        ) + "\t|| Saldo: " + saldo + "||\tActivo: " + estaActivo+ "}\n";
    }



    public void modificarReserva(GestionClientes gestionClientes, Cliente cliente) {
        System.out.println("Que desea hacer?");
        System.out.println("\n1.- Agregar Servicio\n2.- Pagar Reserva");
        int choice = new Scanner(System.in).nextInt();

        if (choice==1) {//metodo agregar servicio

            GestionReservas gestionReservas = new GestionReservas();
            Reserva reserva=gestionReservas.buscarReserva(cliente.getDni());

            System.out.println("Ingrese 1.- Para Plato del Dia\n2.- Para Cafeteria\n3.- Para Bar");

            int eleccion = new Scanner(System.in).nextInt();

            if(eleccion==1){
                reserva=cargaServicio("Plato del Dia",reserva,eleccion);

                buscarYmodificar(gestionClientes,cliente.getDni(),reserva.getCliente().getSaldo());

                gestionReservas.buscarReservaModificar(reserva,cliente.getDni());
                gestionReservas.guardarJson();
            }
            else if (eleccion==2){
                reserva=cargaServicio("Cafeteria",reserva,eleccion);

                buscarYmodificar(gestionClientes,cliente.getDni(),reserva.getCliente().getSaldo());

                gestionReservas.buscarReservaModificar(reserva,cliente.getDni());
                gestionReservas.guardarJson();
            }
            else if (eleccion==3){
                reserva=cargaServicio("Bar",reserva,eleccion);

                buscarYmodificar(gestionClientes,cliente.getDni(),reserva.getCliente().getSaldo());

                gestionReservas.buscarReservaModificar(reserva,cliente.getDni());
                gestionReservas.guardarJson();
            }
        } else if (choice == 2) {
            pagarReserva(cliente);
        }
    }

    private Reserva cargaServicio(String tipoServicio, Reserva reserva, int eleccion){
        List<ServicioGastronomia> listadoServiciosGastronomicos;
        GestionServiciosGastronomicos gestionServiciosGastronomicos = new GestionServiciosGastronomicos();


        listadoServiciosGastronomicos=gestionServiciosGastronomicos.CargarServicios(tipoServicio);//cargo una lista auxiliar para mostrar los servicios del tipo que pidio
        gestionServiciosGastronomicos.mostrarServicio(listadoServiciosGastronomicos);

        System.out.println("Ingrese opcion deseada");
        eleccion= new Scanner(System.in).nextInt();

        if(eleccion<=listadoServiciosGastronomicos.size()){
            reserva.setMontoPagar(reserva.getMontoPagar()+listadoServiciosGastronomicos.get(eleccion-1).getPrecio());
            reserva.getCliente().setSaldo(reserva.getCliente().getSaldo()+listadoServiciosGastronomicos.get(eleccion-1).getPrecio());
            System.out.println("Reserva modificada");
            System.out.println("Reserva:"+ reserva);
        }

        return reserva;
    }

    private void pagarReserva (Cliente cliente){
        GestionReservas gestionReservas = new GestionReservas();

        gestionReservas.buscarReservaPagar(cliente.getDni());
        gestionReservas.guardarJson();

        System.out.println("Muchas gracias.");

    }
    private void buscarYmodificar (GestionClientes gestionClientes,String dni,double saldo){

        for (Cliente cliente:gestionClientes.getLista()){
            if(cliente.getDni().equals(dni)){
                cliente.setSaldo(saldo);
            }
        }
        gestionClientes.guardarJson();
    }
}
