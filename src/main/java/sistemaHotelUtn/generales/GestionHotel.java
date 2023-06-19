package sistemaHotelUtn.generales;

import sistemaHotelUtn.gestionClientes.GestionClientes;
import sistemaHotelUtn.gestionClientes.Cliente;
import sistemaHotelUtn.gestionEmpleados.Empleado;
import sistemaHotelUtn.gestionEmpleados.GestionEmpleados;
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
    private Persona usuarioActual = null;

    Scanner scanner = new Scanner(System.in);

    public GestionHotel() {}

    /*public void menuPrincipal(){
        if(usuarioActual instanceof Cliente)
        {
            gestionClientes.menuCliente((Cliente) usuarioActual);
        }else
        {
            gestionEmpleados.menuEmpleado((Empleado) usuarioActual);
        }
    }*/

    public void iniciar()
    {
        /*Muestra el menu principal, y llama a todos los demás menús*/

        String entrada = "";
        int opcion = 0;

        System.out.println("---------------------------- Sistema Gestion Hotel -------------------------------------");
        System.out.println("\n\t[1] Login\n\t[2] Regitrarse\n\t[3] Salir");
        System.out.print("\nIngrese su opción (1, 2, 3) --> ");

        entrada = scanner.nextLine();

        opcion = Integer.parseInt(entrada);

        switch(opcion)
        {
            case 1: //login
                //verificar si es cliente o empleado
                //mostrar menu que corresponde
                mostrarMenuLogin();
                break;

            case 2: //registrarse
                //puede registrarse como cliente o empleado
                mostrarMenuRegistro();
                break;

            case 3: //salir
                break;

            default:
                System.out.println("Opción incorrecta.");
                break;
        }
    }

    private void mostrarMenuRegistro()
    {
        String entradaRegistro  = "";
        int opcionRegistro = 0;

        System.out.println("\n-------------------------------- Registro -------------------------------------");
        System.out.println("\t[1] Registrar Cliente ");
        System.out.println("\t[2] Registrar Empleado (solo administrador)");
        System.out.println("\t[3] Volver");

        entradaRegistro = scanner.nextLine();
        opcionRegistro = Integer.parseInt(entradaRegistro);

        switch(opcionRegistro)
        {
            case 1: //instanciar nuevo cliente
                Cliente nuevoCliente = gestionClientes.crearNuevoCliente();
                gestionClientes.agregar(nuevoCliente);
                break;

            case 2: //instanciar nuevo empleado
                System.out.println("nuevo empleado");
                Empleado nuevoEmpleado = gestionEmpleados.crearNuevoEmpleado();
                gestionEmpleados.agregar(nuevoEmpleado);
                break;

            case 3: //salir
                break;

            default:
                System.out.println("Opción incorrecta");
                break;
        }

    }

    private void mostrarMenuLogin()
    {
        String entradaLogin  = "";
        int opcionLogin = 0;
        String username = "";
        String password = "";

        System.out.println("\n-------------------------------- Login -------------------------------------");
        System.out.println("\t[1] Login Cliente");
        System.out.println("\t[2] Login Empleado");
        System.out.println("\t[3] Volver");

        entradaLogin = scanner.nextLine();
        opcionLogin = Integer.parseInt(entradaLogin);

        switch(opcionLogin)
        {
            case 1: //login cliente
                //solicitar username y password
                System.out.print("Ingrese username --> ");
                username = scanner.nextLine();

                System.out.print("Ingrese password --> ");
                password = scanner.nextLine();

                usuarioActual = new Cliente(username, password);
                mostrarMenuCliente( (Cliente) usuarioActual ); //SE LLAMA AL MENU CLIENTE CON CASTEO
                break;

            case 2: //login empleado
                //solicitar username y password
                System.out.println("login empleado");

                username = scanner.nextLine();
                password = scanner.nextLine();

                usuarioActual = new Empleado(username, password);
                mostrarMenuEmpleado( (Empleado) usuarioActual ); //SE LLAMA AL MENU EMPLEADO CON CAST

                break;

            case 3:
                break;

            default:
                System.out.println("Opción incorrecta");
                break;
        }
    }
    public void mostrarMenuCliente(Cliente cliente)
    {
        boolean retener = true;
        int opcion=0;
        Scanner scanner= new Scanner(System.in);
        while (retener)
        {
            System.out.println("Eliga una opcion:");
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

                case 3:
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
            System.out.println("Eliga una opcion:");
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
                case 0:
                    retener=false;
                    break;
            }
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
