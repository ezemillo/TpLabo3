package sistemaHotelUtn.gestionHabitaciones;

import sistemaHotelUtn.generales.Gestion;
import sistemaHotelUtn.generales.Json.JsonRepo;
import sistemaHotelUtn.generales.Persona;
import sistemaHotelUtn.gestionEmpleados.Empleado;
import sistemaHotelUtn.gestionEventos.Evento;
import sistemaHotelUtn.gestionReservas.GestionReservas;

import java.util.ArrayList;
import java.util.Scanner;

public class GestionHabitaciones extends Gestion<Habitacion> {
    public GestionHabitaciones() {
        cargarHabitacionesJson();
    }

    private Habitacion nuevaHabitacion() {
        int aceptar = 0;
        Scanner scanner = new Scanner(System.in);
        Habitacion nueva = new Habitacion(true, 0.0, 0, new ArrayList<ServiciosHabitacion>());
        while (aceptar == 0) {
            System.out.println("Ingrese precio diario de alquiler: ");
            nueva.setPrecioDiario(scanner.nextDouble());
            System.out.println("Ingrese la capacidad maxima: ");
            nueva.setCapacidadMax(scanner.nextInt());
            //PONER LISTA DE SERVICIOS

            System.out.println("¿Esta de acuerdo con esta informacion? Ingrese 0 para modificar de nuevo los valores");
            System.out.println(nueva.toString());
            aceptar = scanner.nextInt();
        }
        return nueva;
    }

    public Habitacion buscarHabitacion(int ID) {
        Habitacion aux = null;
        boolean encontrado = false;
        for (int i = 0; i < getLista().size() || encontrado; i++) {
            aux = getLista().get(i);
            if (aux.getId() == ID) {
                encontrado = true;
            }
        }

        if (!encontrado) {
            System.out.println("No se encontro una habitacion con ese ID");
            aux = null;
        }
        return aux;
    }

    public void cargarHabitacionesJson() {
        ArrayList<Habitacion> habitacionesList = new ArrayList<>();
        JsonRepo<Habitacion> habitacionesJson = new JsonRepo<>("habitaciones", habitacionesList, Habitacion.class);
        habitacionesList = habitacionesJson.cargar();
        this.setLista(habitacionesList);
    }


    public void mostrarHabitaciones() {
        int i = 1;
        for (Habitacion habitacion : this.getLista()) {
            if (habitacion.getEsReservable()) {
                System.out.println(i + ")" + habitacion);
                i++;
            }
        }
    }

    public Habitacion obtenerHabitacion(int choice) {
        return this.getLista().get(choice);
    }

    public void guardarHabitacionJson()
    {
        ArrayList<Habitacion> habitacionList = this.getLista();
        JsonRepo<Habitacion> habitacionJson = new JsonRepo<>("habitaciones", habitacionList, Habitacion.class);
        habitacionJson.guardar();
    }

}
