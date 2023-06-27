package sistemaHotelUtn.gestionEventos;

import sistemaHotelUtn.generales.CrearElementos;
import sistemaHotelUtn.generales.Gestion;
import sistemaHotelUtn.generales.Json.JsonRepo;
import sistemaHotelUtn.gestionClientes.Cliente;
import sistemaHotelUtn.gestionEmpleados.Empleado;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class GestionEventos extends Gestion<Evento> implements CrearElementos<Evento> {
    public GestionEventos() {
        cargarJson();
    }

    public Evento crearElemento() {
        Evento evento = new Evento();
        Scanner scanner = new Scanner(System.in);
        String entrada = "";

        System.out.println("**Crear Nuevo Evento**");

        System.out.print("Ingrese el nombre del evento --> ");
        entrada = scanner.nextLine();
        evento.setNombreEvento(entrada);

        System.out.print("Ingrese el organizador evento --> ");
        entrada = scanner.nextLine();
        evento.setOrganizador(entrada);

        System.out.print("Ingrese la cantidad de participantes --> ");
        entrada = scanner.nextLine();
        evento.setParticipantes(Integer.parseInt(entrada)); //INT

        //ingreso de fecha
        System.out.print("Ingrese al año --> ");
        int yr = Integer.parseInt(scanner.nextLine());

        System.out.print("Ingrese el mes --> ");
        int mth = Integer.parseInt(scanner.nextLine());

        System.out.print("Ingrese el dia --> ");
        int day = Integer.parseInt(scanner.nextLine());

        System.out.print("Ingrese la hora --> ");
        int hr = Integer.parseInt(scanner.nextLine());

        System.out.print("Ingrese los minutos --> ");
        int min = Integer.parseInt(scanner.nextLine());

        LocalDateTime inicioEvento = LocalDateTime.of(yr, mth, day, hr, min, 00);
        evento.setFechaHoraInicio(inicioEvento);

        System.out.print("Ingrese la duración del evento (en horas) --> ");
        int duracion = Integer.parseInt(scanner.nextLine());
        evento.setFechaHoraFin(inicioEvento.plusHours(duracion));

        evento.setId(this.getUltimoIdDisponible());

        return evento;
    }

    public Evento buscarEventoPorNombre(String nombreEvento) {
        for (Evento e : this.getLista()) {
            if (e.getNombreEvento().equals(nombreEvento)) {
                return e;
            }
        }

        return null;
    }

    public void modificarEventoPorNombre(String nombreEvento) {
        Evento evento = buscarEventoPorNombre(nombreEvento);

        if (evento != null) {
            //realizar modificaciones
            //pedir campo a modificar
            System.out.println("Modificar el evento: ");
            System.out.println(evento);
        } else //PODRIA LANZAR UNA EXCEPCION
            System.out.println("No se encontro un evento con nombre: " + nombreEvento);
    }

    public void eliminarEventoPorNombre(String nombreEvento) {
        Evento evento = buscarEventoPorNombre(nombreEvento);

        if (evento != null) {
            //eliminar evento
            System.out.println("Eliminar el evento:");
            System.out.println(evento);
            this.eliminar(evento);
        }
        else //LANZAR EXCEPCION
            System.out.println("No se encontro un evento con nombre: " + nombreEvento);
    }

    public void guardarJson()
    {
        ArrayList<Evento> eventosList = this.getLista();
        JsonRepo<Evento> eventosJson = new JsonRepo<>("eventos", eventosList, Evento.class);
        eventosJson.guardar();
    }

    public void cargarJson() {
        ArrayList<Evento> eventosList = new ArrayList<>();
        JsonRepo<Evento> eventosJson = new JsonRepo<>("eventos", eventosList, Evento.class);
        eventosList = eventosJson.cargar();
        this.setLista(eventosList);
    }

}
