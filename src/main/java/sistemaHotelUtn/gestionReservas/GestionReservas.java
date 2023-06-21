package sistemaHotelUtn.gestionReservas;

import sistemaHotelUtn.generales.Gestion;
import sistemaHotelUtn.generales.Json.JsonRepo;
import sistemaHotelUtn.gestionClientes.Cliente;
import sistemaHotelUtn.gestionHabitaciones.GestionHabitaciones;
import sistemaHotelUtn.gestionHabitaciones.Habitacion;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;


public class GestionReservas extends Gestion<Reserva> {
    public GestionReservas() {
        cargarReservasJson();
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

    public void cargarReservasJson() {
        ArrayList<Reserva> reservaList = new ArrayList<>();
        JsonRepo<Reserva> reservaJson = new JsonRepo<>("reservas", reservaList, Reserva.class);
        reservaList = reservaJson.cargar();
        this.setLista(reservaList);
    }

    public ArrayList<Habitacion> verHabitacionesDisponiblesPorFechas(LocalDate checkIn, LocalDate checkOut) {

       ArrayList<Habitacion> habitacionesDisponibles = new ArrayList<>();
        GestionHabitaciones gestionHabitaciones = new GestionHabitaciones();
        gestionHabitaciones.cargarHabitacionesJson();

        for (Habitacion habitacion : gestionHabitaciones.getLista()
        ) {
            System.out.println("habitacion en ver habitaciones disponibles por fechas = " + habitacion);
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


    public void guardarReservasJson() {
        ArrayList<Reserva> reservasList = this.getLista();
        JsonRepo<Reserva> reservasJson = new JsonRepo<>("reservas", reservasList, Reserva.class);
        reservasJson.guardar();
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

    public void generarReserva(Cliente cliente) {
        GestionHabitaciones gestionHabitaciones = new GestionHabitaciones();
        gestionHabitaciones.cargarHabitacionesJson();
        gestionHabitaciones.mostrarHabitaciones();
        Reserva nuevaReserva = new Reserva();
        System.out.println("Cliente activo:" + cliente.toString());

        System.out.println("Ingrese el ID de habitacion que desea reservar");
        int choice = new Scanner(System.in).nextInt();

        nuevaReserva.setHabitacion(gestionHabitaciones.obtenerHabitacion(choice));
        nuevaReserva.setCliente(cliente);

        if (cliente.isEstaActivo()) {
            System.out.println("Ingrese la fecha de checkIn dd-mm-aaaa");
            nuevaReserva.setDiaCheckIn(solicitarLocalDate());
            System.out.println("Ingrese la fecha de checkOut dd-mm-aaaa");
            nuevaReserva.setDiaCheckOut(solicitarLocalDate());
            nuevaReserva.setEstaActiva(true);

            if (isDisponiblePorFecha(nuevaReserva.getHabitacion().getId(), nuevaReserva.getDiaCheckIn(), nuevaReserva.getDiaCheckOut())) {

                nuevaReserva.setMontoPagar(((double) cantidadDias(nuevaReserva.getDiaCheckIn(), nuevaReserva.getDiaCheckOut())) * nuevaReserva.getHabitacion().getPrecioDiario());
                this.getLista().add(nuevaReserva);
                guardarReservasJson();
                System.out.println("Reserva realizada con exito. Muchas gracias!");
            } else {
                System.out.println("Lo sentimos, la habitacion no se encuentra disponible en las fechas solicitadas =(");
                System.out.println("Las habitaciones disponibles en sus fechas son las siguientes: ");
                System.out.println(verHabitacionesDisponiblesPorFechas(nuevaReserva.getDiaCheckIn(), nuevaReserva.getDiaCheckOut()));
            }

        } else {
            System.out.println("Usted esta imposibilitado para realizar una reserva, contacte al administrador");
        }
    }

    public long cantidadDias(LocalDate diaCheckIn, LocalDate diaCheckOut) {
        // Calcular la diferencia en días

        return ChronoUnit.DAYS.between(diaCheckIn, diaCheckOut);
    }
}

