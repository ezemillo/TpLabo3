package sistemaHotelUtn.generales;

import sistemaHotelUtn.gestionClientes.GestionClientes;
import sistemaHotelUtn.gestionClientes.Cliente;
import sistemaHotelUtn.gestionEmpleados.Empleado;
import sistemaHotelUtn.gestionEmpleados.GestionEmpleados;
import sistemaHotelUtn.gestionEventos.Evento;
import sistemaHotelUtn.gestionEventos.GestionEventos;
import sistemaHotelUtn.gestionHabitaciones.GestionHabitaciones;
import sistemaHotelUtn.gestionHabitaciones.Habitacion;
import sistemaHotelUtn.gestionReservas.GestionReservas;

import java.util.Scanner;

public class GestionHotel
{
    private GestionEmpleados gestionEmpleados;
    private GestionClientes gestionClientes;
    private GestionReservas gestionReservas;
    private GestionEventos gestionEventos;
    private GestionHabitaciones gestionHabitaciones;
    private Usuario usuarioActual = null;

    Scanner scanner = new Scanner(System.in);

    public GestionHotel()
    {
        this.gestionClientes = new GestionClientes();
        this.gestionHabitaciones = new GestionHabitaciones();
        this.gestionEventos = new GestionEventos();
        this.gestionReservas = new GestionReservas();
        this.gestionEmpleados = new GestionEmpleados();
    }


    public void mostrarMenuPrincipal()
    {
        /*Muestra el menu principal, y llama a todos los demás menús*/

        String entrada = "";
        int opcion = 0;
        boolean salir = false;

        while( salir != true )
        {
            System.out.println("---------------------------- Sistema Gestion Hotel -------------------------------------");
            System.out.println("\n\t[1] Login\n\t[2] Regitrarse\n\t[3] Salir");
            System.out.println("\nUsuario actual: " +
                    ((usuarioActual == null) ? "No ha hecho login" : usuarioActual.getUsername()));
            System.out.print("\nIngrese su opción (1, 2, 3) --> ");

            entrada = scanner.nextLine();

            opcion = Integer.parseInt(entrada);

            switch(opcion)
            {
                case 1: //login
                    //verificar si es cliente o empleado
                    //mostrar menu que corresponde
                    mostrarMenuLoginGeneral();
                    break;

                case 2: //registrarse
                    //puede registrarse como cliente o empleado
                    mostrarMenuRegistroGeneral();
                    break;

                case 3: //salir
                    salir = true;
                    break;

                default:
                    System.out.println("Opción incorrecta.");
                    break;
            }
        }

    }

    /*---------------------MENUS REGISTRO-----------------*/

    private void mostrarMenuRegistroGeneral()
    {
        String entradaRegistro  = "";
        int opcionRegistro = 0;

        System.out.println("\n-------------------------------- Registro -------------------------------------");
        System.out.println("\t[1] Registrar Cliente");
        System.out.println("\t[2] Registrar Empleado (solo administrador).");
        System.out.println("\t[3] Volver");

        System.out.print("\nIngrese su opción (1, 2, 3) --> ");

        entradaRegistro = scanner.nextLine();
        opcionRegistro = Integer.parseInt(entradaRegistro);

        switch(opcionRegistro)
        {
            case 1: //instanciar nuevo cliente
                mostrarMenuRegistroCliente();
                break;

            case 2: //instanciar nuevo empleado
                mostrarMenuRegistroEmpleado();
                break;

            case 3: //salir
                break;

            default:
                System.out.println("Opción incorrecta");
                break;
        }

    }

    private void mostrarMenuRegistroCliente()
    {
        System.out.println("nuevo cliente");
        Cliente nuevoCliente = gestionClientes.crearNuevoCliente();
        System.out.println("\nSe agregara el nuevo cliente:");
        System.out.println(nuevoCliente);
        gestionClientes.agregar(nuevoCliente);
        gestionClientes.guardarClientesJson();
    }

    private void mostrarMenuRegistroEmpleado()
    {
        System.out.println("nuevo empleado");
        Empleado nuevoEmpleado = gestionEmpleados.crearNuevoEmpleado();
        System.out.println("\nSe agregara el nuevo empleado:");
        System.out.println(nuevoEmpleado);
        gestionEmpleados.agregar(nuevoEmpleado);
        gestionEmpleados.guardarEmpleadosJson();
    }

    /*--------------------- MENUS LOGIN -----------------*/

    private void mostrarMenuLoginGeneral()
    {
        String entradaLogin  = "";
        int opcionLogin = 0;
        String username = "";
        String password = "";

        System.out.println("\n-------------------------------- Login -------------------------------------");
        System.out.println("\t[1] Login Cliente");
        System.out.println("\t[2] Login Empleado");
        System.out.println("\t[3] Volver");

        System.out.print("\nIngrese su opción (1, 2, 3) --> ");

        entradaLogin = scanner.nextLine();
        opcionLogin = Integer.parseInt(entradaLogin);

        switch(opcionLogin)
        {
            case 1: //login cliente
                mostrarMenuLoginCliente();
                break;

            case 2: //login empleado
                mostrarMenuLoginEmpleado();
                break;

            case 3:
                break;

            default:
                System.out.println("Opción incorrecta");
                break;
        }
    }

    private void mostrarMenuLoginCliente()
    {
        String username = "";
        String password = "";

        System.out.println("login cliente");
        //solicitar username y password
        System.out.print("Ingrese su username --> ");
        username = scanner.nextLine();

        System.out.print("Ingrese su password --> ");
        password = scanner.nextLine();

        //busca que exista ese cliente por username
        Cliente cliente = gestionClientes.buscarClientePorUsername(username);

        if( cliente != null )
        {
            this.usuarioActual = cliente.getUsuario();
            System.out.println("Cliente hizo login: " + usuarioActual.getUsername() );
            mostrarMenuCliente( cliente ); //SE LLAMA AL MENU CLIENTE CON CASTEO
        }
        else //deberia lanzar excepcion
            System.out.println("No se ha encontrado un cliente con username: " + username);

    }

    private void mostrarMenuLoginEmpleado()
    {
        //solicitar username y password
        String username = "";
        String password = "";

        System.out.println("login empleado");
        System.out.print("Ingrese su username --> ");
        username = scanner.nextLine();

        System.out.print("Ingrese su password --> ");
        password = scanner.nextLine();

        gestionEmpleados.cargarEmpleadosJson(); //CARGA LOS EMPLEADOS DESDE EL JSON
        Empleado empleado = gestionEmpleados.buscarEmpleadoPorUsername(username); //BUSCA ESE EMPLEADO

        if( empleado != null )
        {
            this.usuarioActual = empleado.getUsuario();
            System.out.println("Empleado hizo login: " + usuarioActual.getUsername() );
            mostrarMenuEmpleado( empleado ); //SE LLAMA AL MENU CLIENTE CON CASTEO
        }
        else //deberia lanzar excepcion
            System.out.println("No se ha encontrado un empleado con username: " + username);

    }

    public void mostrarMenuCliente(Cliente cliente)
    {
        boolean retener = true;
        int opcion=0;
        Scanner scanner= new Scanner(System.in);
        while (retener)
        {
            System.out.println("Elija una opcion:");
            System.out.println("1. Cuenta");
            System.out.println("2. Habitaciones");
            System.out.println("3. Eventos");
            System.out.println("0. Salir");
            opcion=scanner.nextInt();
            switch (opcion)
            {
                case 1:

                    break;
                case 2:
                    break;

                case 3: //eventos
                    //el cliente solo puede ver los eventos
                    System.out.println("Eventos Proximos: ");
                    System.out.println( gestionEventos.listar() );
                    break;

                case 0:
                    retener=false;
                    break;
            }
        }
    }

    public void mostrarMenuEmpleado(Empleado empleado){
        boolean retener = true;
        int opcion=0;
        Scanner scanner= new Scanner(System.in);
        while (retener)
        {
            System.out.println("Elija una opcion:");
            System.out.println("1. Cuenta");
            System.out.println("2. Habitaciones");
            System.out.println("3. Eventos");
            System.out.println("0. Salir");
            opcion=scanner.nextInt();
            switch (opcion)
            {
                case 1:

                    break;
                case 2:
                    break;

                case 3: //eventos
                    //el empleado puede ver y ademas modificar eventos
                    mostrarMenuEventosEmpleado();
                    break;

                case 0:
                    retener=false;
                    break;
            }
        }
    }

    private void mostrarMenuEventosEmpleado()
    {
        int opcionEventos = 0;
        String entradaEventos = "";

        System.out.println("------------------Eventos ----------------------");
        System.out.println("[1] Ver eventos proximos");
        System.out.println("[2] Agregar un evento");
        System.out.println("[3] Modificar un evento");
        System.out.println("[4] Eliminar un evento");
        System.out.println("[5] Salir");

        System.out.print("\nIngrese su opción (1, 2, 3) --> ");
        entradaEventos = scanner.nextLine();

        opcionEventos = Integer.parseInt(entradaEventos);

        switch (opcionEventos)
        {
            case 1:
                System.out.println("Eventos proximos: ");
                System.out.println( gestionEventos.listar() );
                break;

            case 2: //crear un nuevo evento
                Evento nuevo = gestionEventos.crearNuevoEvento();
                System.out.println("**Se agregara el nuevo evento**");
                System.out.println(nuevo);
                gestionEventos.agregar(nuevo);
                break;

            case 3: //Modificar un evento por nombre de evento (SEGUIR)
                System.out.print("Ingrese el nombre del evento a modificar --> ");
                entradaEventos = scanner.nextLine();
                gestionEventos.modificarEventoPorNombre(entradaEventos);
                break;

            case 4: //eliminar un evento por nombre de evento
                System.out.print("Ingrese el nombre del evento a eliminar --> ");
                entradaEventos = scanner.nextLine();
                gestionEventos.eliminarEventoPorNombre(entradaEventos);
                break;

            case 5: //salir
                break;

            default:
                System.out.println("Opción inválida");
                break;
        }

    }

    public void menuHabitaciones(Persona usuario, GestionReservas gestionReservas){

        if(usuario instanceof Empleado)
        {
            mostrarMenuHabEmpleado();
        }else
        {
            mostrarMenuHabCliente();
        }
    }

    private void mostrarMenuHabEmpleado()
    {
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
            switch (opcion)
            {
                case 1 -> {
                    //pedir info para la nueva habitacion
                    aux = new Habitacion();
                    gestionHabitaciones.agregar(aux);
                    break;
                }

                case 2 -> {
                    System.out.println("Ingrese el ID de la habitacion que desea eliminar");
                    System.out.println(gestionHabitaciones.listar());
                    opcion = scanner.nextInt();
                    aux = gestionHabitaciones.buscarHabitacion(opcion);

                    if (aux != null)
                    {
                        System.out.println("¿Esta seguro que quiere eliminar esta habtacion? Ingrese 0 para confirmar");
                        confirma = scanner.nextInt();
                        if (confirma == 0) {
                            gestionHabitaciones.eliminar(aux);
                        }
                    }

                    break;
                }

                case 3 -> {
                    System.out.println("Ingrese el ID de la habitacion que desea eliminar");
                    System.out.println( gestionHabitaciones.listar() );
                    opcion = scanner.nextInt();
                    scanner.nextLine();
                    aux = gestionHabitaciones.buscarHabitacion(opcion);
                    if (aux != null) {
                        System.out.println("Ingrese el campo que quiere modificar");
                        System.out.println("1. Condicion de reserva");
                        System.out.println("2. Precio por dia");
                        System.out.println("3. Capacidad maxima");
                        System.out.println("4. Servicio de habitacion");
                        gestionHabitaciones.modificar(aux, scanner.nextLine());
                    }

                    break;
                }
                case 4 -> mostrarMenuReserva();
                case 0 -> retener = false;
                default -> System.out.println("Opcion no valida");
            }
        }
    }

    private void mostrarMenuReserva()
    {

    }
    private void mostrarMenuHabCliente()
    {

    }
}
