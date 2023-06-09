package sistemaHotelUtn.gestionReservas;

import sistemaHotelUtn.generales.Gestion;
import sistemaHotelUtn.generales.Json.JsonRepo;
import sistemaHotelUtn.gestionClientes.Cliente;
import sistemaHotelUtn.gestionClientes.GestionClientes;
import sistemaHotelUtn.gestionHabitaciones.GestionHabitaciones;
import sistemaHotelUtn.gestionHabitaciones.Habitacion;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;


public class GestionReservas extends Gestion<Reserva> {
    public GestionReservas() {
        cargarJson();
        Reserva.setUltimoId(this.getLista().size());
    }

    public boolean isDisponiblePorFecha(int idHabitacion, LocalDate checkIn, LocalDate checkOut) {

        for (Reserva reserva : this.getLista()
        ) {

            if (idHabitacion == reserva.getHabitacion().getId() && reserva.getEstaActiva()
                    && (checkOut.isAfter(reserva.getDiaCheckIn()) || checkOut.isEqual(reserva.getDiaCheckOut()))
                    && (checkIn.isBefore(reserva.getDiaCheckOut()) || checkIn.isEqual(reserva.getDiaCheckIn()))) {
                return false;

            }
        }
        return true;
    }


    public void guardarJson(){
        ArrayList<Reserva> reservasList = this.getLista();
        JsonRepo<Reserva> reservasJson = new JsonRepo<>("reservas",reservasList, Reserva.class);
        reservasJson.guardar();
    }

    public void cargarJson() {
        ArrayList<Reserva> reservaList = new ArrayList<>();
        JsonRepo<Reserva> reservaJson = new JsonRepo<>("reservas", reservaList, Reserva.class);
        reservaList = reservaJson.cargar();
        this.setLista(reservaList);
    }

    public ArrayList<Habitacion> verHabitacionesDisponiblesPorFechas(LocalDate checkIn, LocalDate checkOut) {

        ArrayList<Habitacion> habitacionesDisponibles = new ArrayList<>();
        GestionHabitaciones gestionHabitaciones = new GestionHabitaciones();
        gestionHabitaciones.cargarJson();

        for (Habitacion habitacion : gestionHabitaciones.getLista()
        ) {

            if (isDisponiblePorFecha(habitacion.getId(), checkIn, checkOut) && habitacion.getEsReservable()) {

                habitacionesDisponibles.add(habitacion);
            }
        }
        return habitacionesDisponibles;
    }

    public void anularReserva(Reserva reserva) {
        reserva.setEstaActiva(false);
    }


    public LocalDate solicitarLocalDate() {

        Scanner scan = new Scanner(System.in);
        System.out.println("Ingrese el dia:");
        int day = Integer.parseInt(scan.nextLine());
        System.out.println("Ingrese el mes:");
        int month = Integer.parseInt(scan.nextLine());
        System.out.println("Ingrese el año:");
        int year = Integer.parseInt(scan.nextLine());


        return LocalDate.of(year, month, day);

    }




    public Reserva buscarReserva(String dni) {

        for (Reserva reserva : this.getLista()) {
            if (reserva.getCliente().getDni().equals(dni) && reserva.getEstaActiva()) {
                return reserva;
            }
        }
        return null;
    }

    public void anularReserva(String dni) {
        for (Reserva reserva : this.getLista()) {
            if (reserva.getCliente().getDni().equals(dni)) {
                reserva = null;
            }
        }
    }

    public void buscarReservaPagar(String dni) {

        for (Reserva reserva : this.getLista()) {
            if (reserva.getCliente().getDni().equals(dni) && reserva.getEstaActiva()) {
                reserva.setDiaCheckIn(null);
                reserva.setDiaCheckOut(null);
                reserva.getCliente().setSaldo(0.00);
                reserva.setEstaActiva(false);
                //reserva.setMontoPagar(0.00); no lo seteo porque quiero que haya un metodo para que podamos consultar la ganancia de todas las reservas
                //ahora, si esto lo dejo así, cuando el cliente quiera reservar de nuevo, el MontoPagar se va a sumar con lo viejo, lo que va a posibilitar de que el administrador
                //pueda consultar cuanto fue la ganancia con ese cliente
            }
        }
    }

    public void buscarReservaModificar(Reserva elemento, String dni) {
        int i = 0;
        for (Reserva reserva : this.getLista()) {
            if (reserva.getCliente().getDni().equals(dni) && reserva.getEstaActiva()) {
                this.getLista().set(i, elemento);
            }
            i++;
        }
    }

    public void verMisReservasActivas(String dni) {
        for (Reserva reserva : this.getLista()) {
            if (reserva.getCliente().getDni().equals(dni) && reserva.getEstaActiva()) {
                System.out.println(reserva);
            }
        }
    }


    public void generarReserva(Cliente cliente) {//puede venir un cliente o un null
        System.out.println("1"+cliente);
        GestionHabitaciones gestionHabitaciones = new GestionHabitaciones();
        gestionHabitaciones.cargarJson();
        gestionHabitaciones.mostrarHabitaciones();
        try{
            System.out.println("Cliente activo:" + cliente.toString());
            System.out.println("2"+cliente);
        }catch (Exception e){
            System.out.println("El cliente no existe, se creara unoS");
        }


        System.out.println("Ingrese el ID de habitacion que desea reservar");
        int choice = new Scanner(System.in).nextInt();
        Habitacion nuevaHabitacion = gestionHabitaciones.obtenerHabitacion(choice);//obtengo habitacion o null

        if (nuevaHabitacion != null) {//si la habitacion no es null...

            if (cliente != null) {

                if (cliente.isEstaActivo()) {

                    comprobarYreservar(cliente, nuevaHabitacion);

                } else {
                    System.out.println("Usted esta imposibilitado para realizar una reserva, contacte al administrador");
                }

            } else {//en este caso el cliente es null
                GestionClientes gestionClientes = new GestionClientes();

                cliente = gestionClientes.crearElemento();
                comprobarYreservar(cliente, nuevaHabitacion);

                gestionClientes.agregar(cliente);
                gestionClientes.guardarJson();


            }
        } else {
            System.out.println("Lo sentimos, no se pudo reservar");
        }

    }

    private void comprobarYreservar(Cliente cliente, Habitacion habitacion) {
        System.out.println("Ingrese la fecha de checkIn dd-mm-aaaa");
        LocalDate checkIn = solicitarLocalDate();
        System.out.println("Ingrese la fecha de checkOut dd-mm-aaaa");
        LocalDate checkOut = solicitarLocalDate();


        if (isDisponiblePorFecha(habitacion.getId(), checkIn, checkOut)) {
            Reserva nuevaReserva = new Reserva();
            nuevaReserva.setId(Reserva.ultimoId++);
            nuevaReserva.setHabitacion(habitacion);
            nuevaReserva.setDiaCheckIn(checkIn);
            nuevaReserva.setDiaCheckOut(checkOut);
            nuevaReserva.setEstaActiva(true);
            nuevaReserva.setCliente(cliente);
            nuevaReserva.setMontoPagar(((double) cantidadDias(nuevaReserva.getDiaCheckIn(), nuevaReserva.getDiaCheckOut())) * nuevaReserva.getHabitacion().getPrecioDiario());
            this.getLista().add(nuevaReserva);
            guardarJson();
            System.out.println("Reserva realizada con exito. Muchas gracias!");

        } else {
            System.out.println("Lo sentimos, la habitacion no se encuentra disponible en las fechas solicitadas =(");
            System.out.println("Las habitaciones disponibles en sus fechas son las siguientes: ");
            System.out.println(verHabitacionesDisponiblesPorFechas(checkIn, checkOut));
        }
    }

    private long cantidadDias(LocalDate diaCheckIn, LocalDate diaCheckOut) {
        // Calcular la diferencia en días
        return ChronoUnit.DAYS.between(diaCheckIn, diaCheckOut);
    }


}

