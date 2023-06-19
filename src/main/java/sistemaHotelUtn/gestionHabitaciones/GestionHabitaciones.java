package sistemaHotelUtn.gestionHabitaciones;

import sistemaHotelUtn.generales.Gestion;
import sistemaHotelUtn.generales.Json.JsonRepo;
import sistemaHotelUtn.generales.Persona;
import sistemaHotelUtn.gestionEmpleados.Empleado;
import sistemaHotelUtn.gestionEventos.Evento;
import sistemaHotelUtn.gestionReservas.GestionReservas;

import java.util.ArrayList;
import java.util.Scanner;

public class GestionHabitaciones extends Gestion<Habitacion>
{
    public GestionHabitaciones(){
        ArrayList<Habitacion> habitacionesList = new ArrayList<>();
        JsonRepo<Habitacion> habitacionesJson = new JsonRepo<>("habitaciones",habitacionesList, Habitacion.class);
        habitacionesList=habitacionesJson.cargar();
        this.setLista(habitacionesList);

    }

    public void menuHabitaciones(Persona usuario, GestionReservas gestionReservas){

        if(usuario instanceof Empleado)
        {
            menuHabEmpleado(gestionReservas);
        }else
        {
            menuHabCliente(gestionReservas);
        }
    }

    private void menuHabCliente(GestionReservas gestionReservas){
        gestionReservas.menuReserva();
    }

    private void menuHabEmpleado(GestionReservas gestionReservas){
        int opcion,confirma;
        Scanner scanner =new Scanner(System.in);
        boolean retener=true;
        Habitacion aux;
        while (retener) {
            System.out.println("1. Agregar Habitacion");
            System.out.println("2. Eliminar Habitacion");
            System.out.println("3. Modificar Habitacion");
            System.out.println("4. Menu reserva");
            System.out.println("0. Salir");
            opcion = scanner.nextInt();
            scanner.nextLine();
            switch (opcion) {
                case 1 -> agregar(nuevaHabitacion());
                case 2 -> {
                    System.out.println("Ingrese el ID de la habitacion que desea eliminar");
                    System.out.println(listar());
                    opcion = scanner.nextInt();
                    aux = buscarHabitacion(opcion);
                    if (aux != null) {
                        System.out.println("¿Esta seguro que quiere eliminar esta habtacion? Ingrese 0 para confirmar");
                        confirma = scanner.nextInt();
                        if (confirma == 0) {
                            eliminar(aux);
                        }
                    }
                }
                case 3 -> {
                    System.out.println("Ingrese el ID de la habitacion que desea eliminar");
                    System.out.println(listar());
                    opcion = scanner.nextInt();
                    scanner.nextLine();
                    aux = buscarHabitacion(opcion);
                    if (aux != null) {
                        System.out.println("Ingrese el campo que quiere modificar");
                        System.out.println("1. Condicion de reserva");
                        System.out.println("2. Precio por dia");
                        System.out.println("3. Capacidad maxima");
                        System.out.println("4. Servicio de habitacion");
                        modificar(aux, scanner.nextLine());
                    }
                }
                case 4 -> gestionReservas.menuReserva();
                case 0 -> retener = false;
                default -> System.out.println("Opcion no valida");
            }
        }
    }

    private Habitacion nuevaHabitacion()
    {
        int aceptar=0;
        Scanner scanner = new Scanner(System.in);
        Habitacion nueva = new Habitacion(true, 0.0, 0, new ArrayList<ServiciosHabitacion>());
        while (aceptar==0) {
            System.out.println("Ingrese precio diario de alquiler: ");
            nueva.setPrecioDiario(scanner.nextDouble());
            System.out.println("Ingrese la capacidad maxima: ");
            nueva.setCapacidadMax(scanner.nextInt());
            //PONER LISTA DE SERVICIOS

            System.out.println("¿Esta de acuerdo con esta informacion? Ingrese 0 para modificar de nuevo los valores");
            System.out.println(nueva.toString());
            aceptar=scanner.nextInt();
        }
        return nueva;
    }

    public Habitacion buscarHabitacion(int ID)
    {
        Habitacion aux=null;
        boolean encontrado=false;
        for (int i = 0; i < getLista().size() || encontrado; i++) {
            aux=getLista().get(i);
            if(aux.getId()==ID) {
                encontrado=true;
            }
        }

        if (!encontrado) {
            System.out.println("No se encontro una habitacion con ese ID");
            aux=null;
        }
        return aux;
    }


}
