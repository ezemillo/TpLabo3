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

    private Double saldo;
    private boolean estaActivo;

    public Cliente() {
    }

    public Cliente(String username, String password)
    {
        this.generarUsuario(username, password, TipoUsuario.CLIENTE);
    }

    public Cliente(String usuario, String contraseña, String nombre, String apellido, String dni, String domicilio, String telefono, Double saldo, boolean estaActivo) {
        super(usuario, contraseña, nombre, apellido, dni, domicilio, telefono);
        this.saldo = saldo;
        this.estaActivo = estaActivo;
    }
    //region Getters y Setters
    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public boolean isEstaActivo() {
        return estaActivo;
    }

    public void setEstaActivo(boolean estaActivo) {
        this.estaActivo = estaActivo;
    }
    //endregion


    public Reserva generarReserva (){
        GestionHabitaciones gestionHabitaciones = new GestionHabitaciones();
        gestionHabitaciones.mostrarHabitaciones();

        System.out.println("Ingrese el numero de habitacion que desea reservar");
        int choice = new Scanner(System.in).nextInt();
        Habitacion habitacionReserva = gestionHabitaciones.obtenerHabitacion(choice);

        System.out.println("Ingrese su DNI");
        String dni = new Scanner(System.in).next();

        GestionClientes gestionClientes = new GestionClientes();
        Cliente cliente = gestionClientes.buscarCliente(dni);

        Reserva nuevaReserva = new Reserva();

        if(cliente!=null){
            if(cliente.estaActivo){
                System.out.println("Ingrese los dias a reservar");
                //metodo buscar que la reserva pueda hacerse ese dia
                //nuevaReserva.setDiaCheckIn();
                //nuevaReserva.setDiaCheckOut();

                System.out.println("Ingrese 'S' para confirmar");
                char eleccion = new Scanner(System.in).next().charAt(0);
                if(eleccion=='s'){
                    GestionReservas gestionReservas=new GestionReservas();
                    nuevaReserva.setCliente(cliente);
                    nuevaReserva.setHabitacion(habitacionReserva);
                    nuevaReserva.setMontoPagar(habitacionReserva.getPrecioDiario() * nuevaReserva.cantidadDias());
                    gestionReservas.agregar(nuevaReserva);

                }
                System.out.println("Gracias por elegirnos");
            }else {
                System.out.println("Usted esta imposibilitado para realizar una reserva");
                return null;
            }
        }
        else{
            System.out.println("No está registrado, cree su usuario");
            Cliente nuevoCliente = new Cliente();
            nuevoCliente=crearCliente (nuevoCliente);

            System.out.println("Ingrese 'S' para confirmar");
            char eleccion = new Scanner(System.in).next().charAt(0);
            if(eleccion=='s') {
                nuevaReserva.setCliente(nuevoCliente);

                nuevaReserva.setHabitacion(habitacionReserva);
                nuevaReserva.setMontoPagar(habitacionReserva.getPrecioDiario() * nuevaReserva.cantidadDias());

                nuevoCliente.setSaldo(nuevaReserva.getMontoPagar());
                nuevoCliente.setEstaActivo(true);

                GestionReservas gestionReservas = new GestionReservas();
                gestionReservas.agregar(nuevaReserva);

                gestionClientes.agregar(nuevoCliente);//preguntar si aca se guarda en el JSON

                //MANDAR LA LISTA CON EL METODO GUARDAR!
                gestionClientes.guardarClientesJson();
                gestionReservas.guardarReservasJson();
            }
        }
        return null;
    }
    public Cliente crearCliente (Cliente cliente){
        String entry;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese su usuario");
        entry=scanner.next();
        super.getUsuario().setUsername(entry);
        System.out.println("Ingrese su contraseña");
        entry=scanner.next();
        super.getUsuario().setPassword(entry);
        System.out.println("Ingrese su nombre");
        entry=scanner.next();
        cliente.setNombre(entry);
        System.out.println("Ingrese su apellido");
        entry=scanner.next();
        cliente.setApellido(entry);
        System.out.println("Ingrese su dni");
        entry=scanner.next();
        cliente.setDni(entry);
        System.out.println("Ingrese su domicilio");
        entry=scanner.next();
        cliente.setDomicilio(entry);
        System.out.println("Ingrese su telefono");
        entry=scanner.next();
        cliente.setTelefono(entry);

        return cliente;
    }

    public void modificarReserva() {//anular reserva y agregar servicio, la idea es ponerlo en dos metodos diferentes
        System.out.println("Que desea hacer?");
        System.out.println("1.- Anular reserva\n2.- Agregar Servicio\n3.- Pagar Reserva");
        int choice = new Scanner(System.in).nextInt();
        //mandar una excepcion
        if(choice==1){//metodo anular reserva
            anularReserva();
        } else if (choice==2) {//metodo agregar servicio

            System.out.println("Ingrese su dni");
            String dni = new Scanner(System.in).next();

            GestionReservas gestionReservas = new GestionReservas();//lo mismo que arriba
            Reserva reserva=gestionReservas.buscarReserva(dni);

            GestionServiciosGastronomicos gestionServiciosGastronomicos = new GestionServiciosGastronomicos();//cargo los servicios gastronomicos //borrar una vez modularizado
            System.out.println("Ingrese 1.- Para Plato del Dia\n2.- Para Cafeteria\n3.- Para Bar");//esto se puede mejorar por ENUM

            int eleccion = new Scanner(System.in).nextInt();

            if(eleccion==1){
                reserva=cargaServicio("Plato del Dia",reserva,eleccion);
                gestionReservas.buscarReservaModificar(reserva,dni);
                gestionReservas.guardarReservasJson();
            }
            else if (eleccion==2){
                reserva=cargaServicio("Cafeteria",reserva,eleccion);
                gestionReservas.buscarReservaModificar(reserva,dni);
                gestionReservas.guardarReservasJson();
            }
            else if (eleccion==3){
                reserva=cargaServicio("Bar",reserva,eleccion);
                gestionReservas.buscarReservaModificar(reserva,dni);
                gestionReservas.guardarReservasJson();
            }
        } else if (choice == 3) {
            pagarReserva();
        }
    }

    public Reserva cargaServicio(String tipoServicio, Reserva reserva, int eleccion){
        List<ServicioGastronomia> listadoServiciosGastronomicos;
        GestionServiciosGastronomicos gestionServiciosGastronomicos = new GestionServiciosGastronomicos();
        listadoServiciosGastronomicos=gestionServiciosGastronomicos.CargarServicios(tipoServicio);//cargo una lista auxiliar para mostrar los servicios del tipo que pidio
        gestionServiciosGastronomicos.mostrarServicio(listadoServiciosGastronomicos);

        System.out.println("Ingrese opcion deseada");
        eleccion= new Scanner(System.in).nextInt();

        if(eleccion<=listadoServiciosGastronomicos.size()){
            reserva.setMontoPagar(reserva.getMontoPagar()+listadoServiciosGastronomicos.get(eleccion-1).getPrecio());
            reserva.getCliente().setSaldo(reserva.getCliente().getSaldo()+listadoServiciosGastronomicos.get(eleccion-1).getPrecio());
        }

        return reserva;
    }

    public void pagarReserva (){
        GestionReservas gestionReservas = new GestionReservas();
        System.out.println("Para confirmar pago, ingrese su DNI");
        String dni = new Scanner(System.in).next();
        gestionReservas.buscarReservaPagar(dni);
        gestionReservas.guardarReservasJson();

        System.out.println("Muchas gracias.");

    }
    public void anularReserva(){
        System.out.println("Ingrese su dni");
        String dni = new Scanner(System.in).next();

        GestionReservas gestionReservas = new GestionReservas();//recibo el listado de las reservas
        Reserva reserva=gestionReservas.buscarReserva(dni);//recibo la reserva de la persona
        System.out.println(reserva);

        System.out.println("Confirmar anulacion de reserva");
        char eleccion = new Scanner(System.in).next().charAt(0);

        if(eleccion=='s'){
            gestionReservas.anularReserva(dni);//pongo el estaActuvo en false
            gestionReservas.guardarReservasJson();
            System.out.println("Su reserva fue anulada con exito");
        }
    }

    @Override
    public String toString() {
        return super.toString().replace(
                super.toString().charAt(super.toString().length() - 1),
                ' '
        ) + "\tSaldo: " + saldo + "\n\tActivo: " + estaActivo+ "\n" + "}";
    }
}
