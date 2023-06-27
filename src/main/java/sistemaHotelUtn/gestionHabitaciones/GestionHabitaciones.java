package sistemaHotelUtn.gestionHabitaciones;

import sistemaHotelUtn.generales.Gestion;
import sistemaHotelUtn.generales.Json.JsonRepo;
import sistemaHotelUtn.generales.Persona;
import sistemaHotelUtn.generales.TipoUsuario;
import sistemaHotelUtn.gestionClientes.Cliente;
import sistemaHotelUtn.gestionEmpleados.Empleado;
import sistemaHotelUtn.gestionEventos.Evento;
import sistemaHotelUtn.gestionReservas.GestionReservas;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;


public class GestionHabitaciones extends Gestion<Habitacion> {
    public GestionHabitaciones() {
        cargarJson();
    }

    public Habitacion crearElemento() {
        Scanner scanner = new Scanner(System.in);
        String entrada = "";
        /*Pide informacion por la consola y crea un nuevo cliente listo para agregar a la lista*/

        Habitacion habitacion = new Habitacion();
        habitacion.setId(this.getUltimoIdDisponible());

        System.out.println("Ingrese el valor diario de la habitacion: ");
        Double precioDiario = scanner.nextDouble();
        habitacion.setPrecioDiario(precioDiario);

        System.out.println("Ingrese la capacidad maxima de la habitacion: ");
        int capacidadMaxima = scanner.nextInt();
        habitacion.setCapacidadMax(capacidadMaxima);

        HashSet<ServiciosHabitacion> serviciosHabitaciones = new HashSet<ServiciosHabitacion>();
        System.out.println("La habiacion dispone de wifi? 1.Si 2.No");
        int wifi = scanner.nextInt();
        if (wifi == 1) {
            serviciosHabitaciones.add(ServiciosHabitacion.WIFI);
        }
        System.out.println("La habiacion dispone de Cable? 1.Si 2.No");
        int cable = scanner.nextInt();
        if (cable == 1) {
            serviciosHabitaciones.add(ServiciosHabitacion.CABLE);
        }
        System.out.println("La habiacion dispone de Bañera? 1.Si 2.No");
        int baniera = scanner.nextInt();
        if (baniera == 1) {
            serviciosHabitaciones.add(ServiciosHabitacion.BANIERA);
        }
        System.out.println("La habiacion dispone de Jacuzzi? 1.Si 2.No");
        int jacuzzi = scanner.nextInt();
        if (jacuzzi == 1) {
            serviciosHabitaciones.add(ServiciosHabitacion.JACUZZI);
        }
        habitacion.setServiciosHabitacion(serviciosHabitaciones);

        System.out.println("Desea habilitar la Habitacion? 1.Si 2.No");
        int habilitar = scanner.nextInt();
        boolean activa = false;
        if (habilitar == 1) activa = true;

        habitacion.setEsReservable(activa);


        return habitacion;
    }

    public Habitacion buscarHabitacion(int ID) {
        for (Habitacion habitacion:this.getLista()) {
            if(habitacion.getId()==ID){
                return habitacion;
            }
        }
        return null;
    }

    public void cargarJson() {
        ArrayList<Habitacion> habitacionesList = new ArrayList<>();
        JsonRepo<Habitacion> habitacionesJson = new JsonRepo<>("habitaciones", habitacionesList, Habitacion.class);
        habitacionesList = habitacionesJson.cargar();
        this.setLista(habitacionesList);

    }


    public void mostrarHabitaciones() {

        for (Habitacion habitacion : this.getLista()) {
            if (habitacion.getEsReservable()) {
                System.out.println(habitacion);

            }
        }
    }

    public Habitacion obtenerHabitacion(int choice) {
        try{
            Habitacion habitacion = this.getLista().get(choice);
            return habitacion;
        }catch (IndexOutOfBoundsException e){
            System.out.println("Entrada erronea");
            return null;
        }
    }

    public void guardarJson() {
        ArrayList<Habitacion> habitacionList = this.getLista();
        JsonRepo<Habitacion> habitacionJson = new JsonRepo<>("habitaciones", habitacionList, Habitacion.class);
        habitacionJson.guardar();
    }

    @Override
    public void modificar(Habitacion target, Scanner scanner) {
        boolean retener=true;
        int opcionI;
        String entrada, opcionS;
        while (retener) {
            System.out.println("Ingrese el campo que quiere modificar");
            System.out.println("1. Condicion de reserva");
            System.out.println("2. Precio por dia");
            System.out.println("3. Capacidad maxima");
            System.out.println("4. Servicio de habitacion");
            System.out.println("5. Cancelar");

            try
            {
                System.out.print("\nIngrese su opción (1, 2, 3, 4, 5) --> ");

                entrada = scanner.nextLine();

                opcionI = Integer.parseInt(entrada);

                if( opcionI < 1 || opcionI > 5)
                {

                    throw new IllegalArgumentException();
                }

            }catch (NumberFormatException e)
            {
                opcionI = 0;
                System.out.println("Error: ingrese una de las opciones indicadas.");
            }
            catch(IllegalArgumentException e)
            {
                opcionI = 0;
                System.out.println("Error: ingrese un numero en el rango indicado.");
            }

            switch (opcionI){
                case 1:
                    if(target.getEsReservable())
                    {
                        System.out.println("Actualmente la habitacion es reservable ¿Desea hacerla hacerla no reservable?");
                    }else
                    {

                        System.out.println("Actualmente la habitacion es no reservable ¿Desea hacerla hacerla reservable?");
                    }
                    System.out.println("Ingrese s para confirmar el cambio. Ingrese otro valor para mantener el estado");
                   opcionS=scanner.nextLine();
                   if(Objects.equals(opcionS, "s"))
                   {
                       target.setEsReservable(!target.getEsReservable());
                   }
                    break;
                case 2:
                    System.out.println("Actualmente el precio es "+target.getPrecioDiario());
                    System.out.println("Ingrese el precio nuevo: ");
                    double nuevoPrecio=scanner.nextDouble();
                    scanner.nextLine();
                    System.out.println("Precio actual: "+target.getPrecioDiario());
                    System.out.println("Nuevo precio: "+nuevoPrecio);

                    System.out.println("Ingrese s para confirmar el cambio. Ingrese otro valor para mantener el estado");
                    opcionS=scanner.nextLine();
                    if(Objects.equals(opcionS, "s"))
                    {
                        target.setPrecioDiario(nuevoPrecio);
                    }
                    break;
                case 3:
                    System.out.println("Actualmente la capacidad maxima es "+target.getCapacidadMax());
                    System.out.println("Ingrese la nueva capacidad maxima: ");
                    int nuevaCapacidad=scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Capacidad actual: "+target.getCapacidadMax());
                    System.out.println("Nueva capacidad: "+nuevaCapacidad);

                    System.out.println("Ingrese s para confirmar el cambio. Ingrese otro valor para mantener el estado");
                    opcionS=scanner.nextLine();
                    if(Objects.equals(opcionS, "s"))
                    {
                        target.setCapacidadMax(nuevaCapacidad);
                    }
                    break;
                case 4:
                    modifServicios(target,scanner);
                    break;
                case 5:
                    retener=false;
                    break;
            }

        }

    }

    private void modifServicios(Habitacion target, Scanner scanner)
    {
        int opcionI;
        String opcionS;
        ServiciosHabitacion servi;

        target.listarServicios();
        System.out.println("¿Que servicio desea agregar o remover?");
        System.out.println("1. Wifi");
        System.out.println("2. Cable");
        System.out.println("3. Baniera");
        System.out.println("4. Jacuzzi");

        opcionI=scanner.nextInt();
        switch (opcionI)
        {
            case 1:
                servi=ServiciosHabitacion.WIFI;
                break;
            case 2:
                servi=ServiciosHabitacion.CABLE;
                break;
            case 3:
                servi=ServiciosHabitacion.BANIERA;
                break;
            case 4:
                servi=ServiciosHabitacion.JACUZZI;
                break;
            default:
                servi=null;
                System.out.println("Opcion no valida");
        }
        if(servi!=null)
        {
            if (target.getServiciosHabitacion().contains(servi)){
                System.out.println("¿Dese retirar el servicio de "+servi+"?");
            }else {
                System.out.println("¿Desea agregar el servicio de "+servi+"?");
            }

            System.out.println("Ingrese s para confirmar el cambio. Ingrese otro valor para mantener el estado");
            opcionS=scanner.nextLine();
            if(Objects.equals(opcionS, "s"))
            {
                if (target.getServiciosHabitacion().contains(servi)){
                    target.getServiciosHabitacion().remove(servi);
                }else {
                    target.getServiciosHabitacion().add(servi);
                }
            }


        }
    }
}
