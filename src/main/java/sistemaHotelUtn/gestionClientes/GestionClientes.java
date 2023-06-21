package sistemaHotelUtn.gestionClientes;

import sistemaHotelUtn.generales.Gestion;
import sistemaHotelUtn.generales.Json.JsonRepo;
import sistemaHotelUtn.generales.TipoUsuario;
import sistemaHotelUtn.gestionHabitaciones.GestionHabitaciones;
import sistemaHotelUtn.gestionHabitaciones.Habitacion;
import sistemaHotelUtn.gestionReservas.GestionReservas;
import sistemaHotelUtn.gestionReservas.Reserva;
import sistemaHotelUtn.gestionServiciosGastronomicos.GestionServiciosGastronomicos;
import sistemaHotelUtn.gestionServiciosGastronomicos.ServicioGastronomia;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestionClientes extends Gestion<Cliente> {
    public GestionClientes() {
    }

    public Cliente crearNuevoCliente() {
        Scanner scanner = new Scanner(System.in);
        String entrada = "";
        /*Pide informacion por la consola y crea un nuevo cliente listo para agregar a la lista*/

        Cliente nuevo = new Cliente();

        System.out.println("**Crear Nuevo Cliente**");
        System.out.print("Ingrese su nombre --> ");
        entrada = scanner.nextLine();
        nuevo.setNombre(entrada);

        System.out.print("Ingrese su apellido --> ");
        entrada = scanner.nextLine();
        nuevo.setApellido(entrada);

        System.out.print("Ingrese su dni --> ");
        entrada = scanner.nextLine();
        nuevo.setDni(entrada);

        System.out.print("Ingrese su domicilio --> ");
        entrada = scanner.nextLine();
        nuevo.setDomicilio(entrada);

        System.out.print("Ingrese su telefono --> ");
        entrada = scanner.nextLine();
        nuevo.setTelefono(entrada);

        System.out.print("Ingrese un username --> ");
        String username = scanner.nextLine();

        System.out.print("Ingrese un password --> ");
        String password = scanner.nextLine();
        nuevo.generarUsuario(username, password, TipoUsuario.CLIENTE);

        return nuevo;
    }


    public Cliente buscarClientePorUsername(String username) {
        for (Cliente cliente : this.getLista()) {
            if (cliente.getUsuario().getUsername().equals(username)) {
                //si lo encuentra lo retorna
                return cliente;
            }
        }

        // si no lo encuentra retorna null
        return null;
    }

    public void guardarClientesJson() {
        ArrayList<Cliente> clientesList = this.getLista();
        JsonRepo<Cliente> clientesJson = new JsonRepo<>("clientes", clientesList, Cliente.class);
        clientesJson.guardar();
    }

    public void cargarClientesJson() {
        ArrayList<Cliente> clientesList = new ArrayList<>();
        JsonRepo<Cliente> clientesJson = new JsonRepo<>("clientes", clientesList, Cliente.class);
        clientesList = clientesJson.cargar();
        this.setLista(clientesList);
    }

    /*Daniiiii*/
    public void mostrarClientes() {
        int i = 1;
        for (Cliente cliente : this.getLista()) {
            System.out.println(cliente);
        }
    }

    public Cliente buscarCliente(String dni) {
        for (Cliente cliente : this.getLista()) {
            if (cliente.getDni().equals(dni)) {
                return cliente;
            }
        }
        return null;
    }

    public void buscarYmodificar (String dni,double saldo){
        for (Cliente cliente:this.getLista()){
            if(cliente.getDni().equals(dni)){
                cliente.setSaldo(saldo);
            }
        }
        guardarClientesJson();
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

            System.out.println("Ingrese 1.- Para Plato del Dia\n2.- Para Cafeteria\n3.- Para Bar");//esto se puede mejorar por ENUM

            int eleccion = new Scanner(System.in).nextInt();

            if(eleccion==1){
                reserva=cargaServicio("Plato del Dia",reserva,eleccion);

                buscarYmodificar(dni,reserva.getCliente().getSaldo());

                gestionReservas.buscarReservaModificar(reserva,dni);
                gestionReservas.guardarReservasJson();
            }
            else if (eleccion==2){
                reserva=cargaServicio("Cafeteria",reserva,eleccion);

                buscarYmodificar(dni,reserva.getCliente().getSaldo());

                gestionReservas.buscarReservaModificar(reserva,dni);
                gestionReservas.guardarReservasJson();
            }
            else if (eleccion==3){
                reserva=cargaServicio("Bar",reserva,eleccion);

                buscarYmodificar(dni,reserva.getCliente().getSaldo());

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
}
