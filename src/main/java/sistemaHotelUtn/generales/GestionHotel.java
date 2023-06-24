package sistemaHotelUtn.generales;

import sistemaHotelUtn.gestionClientes.GestionClientes;
import sistemaHotelUtn.gestionClientes.Cliente;
import sistemaHotelUtn.gestionEmpleados.Empleado;
import sistemaHotelUtn.gestionEmpleados.GestionEmpleados;
import sistemaHotelUtn.gestionEventos.Evento;
import sistemaHotelUtn.gestionEventos.GestionEventos;
import sistemaHotelUtn.gestionHabitaciones.GestionHabitaciones;
import sistemaHotelUtn.gestionHabitaciones.Habitacion;
import sistemaHotelUtn.gestionHabitaciones.ServiciosHabitacion;
import sistemaHotelUtn.gestionReservas.GestionReservas;

import java.time.LocalDate;
import java.util.ArrayList;
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


        this.gestionEmpleados = new GestionEmpleados();


        this.gestionReservas = new GestionReservas();


    }


    public void mostrarMenuPrincipal() {
        /*Muestra el menu principal, y llama a todos los demás menús*/
        String entrada = "";
        int opcion = 0;
        boolean salir = false;

        while (!salir)
        {
            System.out.println("---------------------------- Sistema Gestion Hotel -------------------------------------");
            System.out.println("\n\t[1] Login\n\t[2] Regitrarse\n\t[3] Salir");
            System.out.println("\nUsuario actual: " +
                    ((usuarioActual == null) ? "No ha hecho login" : usuarioActual.getUsername()));

            while( opcion == 0)
            {
                try
                {
                    System.out.print("\nIngrese su opción (1, 2, 3) --> ");
                    entrada = scanner.nextLine();

                    opcion = Integer.parseInt(entrada);

                    if( opcion < 1 || opcion > 3)
                    {
                        opcion = 0;
                        throw new IllegalArgumentException();
                    }

                }catch (NumberFormatException e)
                {
                    opcion = 0;
                    System.out.println("Error: ingrese una de las opciones indicadas.");
                }
                catch(IllegalArgumentException e)
                {
                    opcion = 0;
                    System.out.println("Error: ingrese un numero en el rango indicado.");
                }
            }

            switch (opcion) {
                case 1: //login
                    //verificar si es cliente o empleado
                    //mostrar menu que corresponde
                    mostrarMenuLoginGeneral();
                    opcion = 0; //se resetea la opcion para que luego vuelva al menu principal
                    break;

                case 2: //registrarse
                    //puede registrarse como cliente o empleado
                    mostrarMenuRegistroGeneral();
                    opcion = 0; //se resetea la opcion para que luego vuelva al menu principal
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

    private void mostrarMenuRegistroGeneral() {
        String entradaRegistro = "";
        int opcionRegistro = 0;

        System.out.println("\n-------------------------------- Registro -------------------------------------");
        System.out.println("\t[1] Registrar Cliente");
        System.out.println("\t[2] Registrar Empleado.");
        System.out.println("\t[3] Volver");

        while( opcionRegistro == 0 )
        {
            try
            {
                System.out.print("\nIngrese su opción (1, 2, 3) --> ");
                entradaRegistro = scanner.nextLine();

                opcionRegistro = Integer.parseInt(entradaRegistro);

                if( opcionRegistro < 1 || opcionRegistro > 3)
                {
                    opcionRegistro = 0;
                    throw new IllegalArgumentException();
                }

            }catch (NumberFormatException e)
            {
                opcionRegistro = 0;
                System.out.println("Error: ingrese una de las opciones indicadas.");
            }
            catch(IllegalArgumentException e)
            {
                opcionRegistro = 0;
                System.out.println("Error: ingrese un numero en el rango indicado.");
            }
        }

        switch(opcionRegistro)
        {
            case 1: //instanciar un nuevo cliente con entrada por usuario
                registrarNuevoCliente();
                break;

            case 2: //instanciar un nuevo empleado con entrada por usuario
                registrarNuevoEmpleado();
                break;

            case 3: //salir
                break;

            default:
                System.out.println("Opción incorrecta");
                break;
        }

    }

    private void registrarNuevoCliente()
    {
        System.out.println("**** Registrar Nuevo Cliente **** ");
        Cliente nuevoCliente = gestionClientes.crearNuevoCliente();
        nuevoCliente.asignarIdAutoincremental();
        System.out.println("\nSe agrego el nuevo cliente:");
        System.out.println(nuevoCliente);
        gestionClientes.agregar(nuevoCliente);
        gestionClientes.guardarClientesJson();
    }

    private void registrarNuevoEmpleado()
    {
        System.out.println("**** Registrar Nuevo Empleado **** ");
        Empleado nuevoEmpleado = gestionEmpleados.crearNuevoEmpleado();
        nuevoEmpleado.asignarIdAutoincremental();
        System.out.println("\nSe agrego el nuevo empleado:");
        System.out.println(nuevoEmpleado);
        gestionEmpleados.agregar(nuevoEmpleado);
        gestionEmpleados.guardarEmpleadosJson();
    }

    /*--------------------- MENUS LOGIN -----------------*/

    private void mostrarMenuLoginGeneral() {
        String entradaLogin = "";
        int opcionLogin = 0;
        String username = "";
        String password = "";

        System.out.println("\n-------------------------------- Login -------------------------------------");
        System.out.println("\t[1] Login Cliente");
        System.out.println("\t[2] Login Empleado");
        System.out.println("\t[3] Volver");

        while( opcionLogin == 0 )
        {
            try
            {
                System.out.print("\nIngrese su opción (1, 2, 3) --> ");
                entradaLogin = scanner.nextLine();

                opcionLogin = Integer.parseInt(entradaLogin);

                if( opcionLogin < 1 || opcionLogin > 3)
                {
                    opcionLogin = 0;
                    throw new IllegalArgumentException();
                }

            }catch (NumberFormatException e)
            {
                opcionLogin = 0;
                System.out.println("Error: ingrese una de las opciones indicadas.");
            }
            catch(IllegalArgumentException e)
            {
                opcionLogin = 0;
                System.out.println("Error: ingrese un numero en el rango indicado.");
            }
        }


        switch (opcionLogin) {
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

    private void mostrarMenuLoginCliente() {
        String username = "";
        String password = "";

        System.out.println("\n-------------------------------- Login Cliente -------------------------------------");
        //solicitar username y password
        System.out.print("Ingrese su username --> ");
        username = scanner.nextLine();

        System.out.print("Ingrese su password --> ");
        password = scanner.nextLine();

        //busca que exista ese cliente por username
        this.gestionClientes.cargarClientesJson();
        Cliente cliente = gestionClientes.buscarClientePorUsername(username);
        if(!cliente.getUsuario().getPassword().equals(password)) cliente=null;
        if (cliente != null) {
            this.usuarioActual = cliente.getUsuario();
            System.out.println("Cliente hizo login: " + usuarioActual.getUsername() );
            mostrarMenuCliente( cliente );
        }
        else {
            System.out.println("Cliente hizo login: " + usuarioActual.getUsername());
            mostrarMenuCliente(cliente); //SE LLAMA AL MENU CLIENTE CON CASTEO
        }

    }

    private void mostrarMenuLoginEmpleado() {
        //solicitar username y password
        String username = "";
        String password = "";

        System.out.println("\n-------------------------------- Login Empleado -------------------------------------");
        System.out.print("Ingrese su username --> ");
        username = scanner.nextLine();

        System.out.print("Ingrese su password --> ");
        password = scanner.nextLine();

        Empleado empleado = gestionEmpleados.buscarEmpleadoPorUsername(username); //BUSCA ESE EMPLEADO

        if(!empleado.getUsuario().getPassword().equals(password)) empleado=null;
        if (empleado != null) {
            this.usuarioActual = empleado.getUsuario();
            System.out.println("Empleado hizo login: " + usuarioActual.getUsername());
            mostrarMenuEmpleado(empleado); //SE LLAMA AL MENU CLIENTE CON CASTEO
        } else //deberia lanzar excepcion
            System.out.println("No se ha encontrado un empleado con username: " + username);

    }

    public void mostrarMenuCliente(Cliente cliente) {
        boolean retener = true;
        int opcion = 0;
        String entrada = "";

        while (retener) {
            System.out.println("\n ----------------------------- Menu Cliente -------------------------------------- ");
            System.out.println("[1] Detalles de Cuenta Cliente");
            System.out.println("[2] Menu Reservas");
            System.out.println("[3] Eventos");
            System.out.println("[4] Salir");


            while (opcion == 0) {
                try {
                    System.out.print("\nIngrese su opción (1, 2, 3, 4 ) --> ");
                    entrada = scanner.nextLine();
                    scanner.reset();
                    opcion = Integer.parseInt(entrada);

                    if (opcion < 1 || opcion > 4) {
                        opcion = 0;
                        throw new IllegalArgumentException();
                    }

                } catch (NumberFormatException e) {
                    opcion = 0;
                    System.out.println("Error: ingrese una de las opciones indicadas.");
                } catch (IllegalArgumentException e) {
                    opcion = 0;
                    System.out.println("Error: ingrese un numero en el rango indicado.");
                }
            }

            switch (opcion) {
                case 1:
                    System.out.println("Detalles de su cuenta: ");
                    System.out.println(cliente.toString());
                    opcion =0;
                    break;

                case 2:
                    mostrarMenuReserva(cliente);
                    opcion=0;
                    break;

                case 3: //eventos
                    //el cliente solo puede ver los eventos
                    System.out.println("Eventos Proximos: ");
                    System.out.println(gestionEventos.listar()); // LOS MUESTRA
                    System.out.println(gestionEventos.listar());
                    opcion=0;
                    break;

                case 4:
                    retener = false;
                    break;

                case 5:
                    opcion=0;
                    break;

                case 0:
                    retener = false;
                    break;
            }
        }
    }

    public void mostrarMenuEmpleado(Empleado empleado) {
        boolean retener = true;
        int opcion = 0;
        String entrada = "";

        while (retener) {
            System.out.println("\n ----------------------------- Menu Empleado -------------------------------------- ");
            System.out.println("[1] Detalles de Cuenta Empleado");
            System.out.println("[2] Habitaciones");
            System.out.println("[3] Eventos");
            System.out.println("[4] Mostrar Balance");
            System.out.println("[5] Ver todos los clientes");
            System.out.println("[6] Modificar clientes");
            System.out.println("[7] Ver todos los empleados");
            System.out.println("[8] Ver todas las reservas");
            System.out.println("[9] Salir");

            while( opcion == 0 )
            {
                try
                {
                    System.out.print("\nIngrese su opción (1, 2, 3, 4, 5, 6, 7, 8, 9 ) --> ");
                    entrada = scanner.nextLine();

                    opcion = Integer.parseInt(entrada);

                    if( opcion < 1 || opcion > 9)
                    {
                        opcion = 0;
                        throw new IllegalArgumentException();
                    }

                }catch (NumberFormatException e)
                {
                    opcion = 0;
                    System.out.println("Error: ingrese una de las opciones indicadas.");
                }
                catch(IllegalArgumentException e)
                {
                    opcion = 0;
                    System.out.println("Error: ingrese un numero en el rango indicado.");
                }
            }

            switch (opcion) {
                case 1 -> {
                    System.out.println("Detalles de la cuenta empleado:");
                    System.out.println(empleado.toString());
                }
                case 2 -> mostrarMenuHabEmpleado();
                case 3 -> //eventos
                    //el empleado puede ver y ademas modificar eventos
                        mostrarMenuEventosEmpleado();

                case 4 -> gestionEmpleados.gananciaTotal();

                case 5 -> System.out.println(gestionClientes.getLista());

                case 6 -> {
                    System.out.println("Ingrese el Id del cliente a modificar");


                    // continuar funcion
                }
                case 7 -> System.out.println(gestionEmpleados.getLista());

                case 8 -> System.out.println(gestionReservas.getLista());

                case 9 -> retener = false;


            }
            opcion=0;
        }
    }

    private void mostrarMenuEventosEmpleado() {
        int opcionEventos = 0;
        String entradaEventos = "";
        boolean retener=true;

        while (retener) {
            System.out.println("------------------------------ Eventos ------------------------------------");
            System.out.println("[1] Ver eventos proximos");
            System.out.println("[2] Agregar un evento");
            System.out.println("[3] Modificar un evento");
            System.out.println("[4] Eliminar un evento");
            System.out.println("[5] Salir");


            while (opcionEventos == 0) {


                try {
                    System.out.print("\nIngrese su opción (1, 2, 3, 4, 5) --> ");
                    entradaEventos = scanner.nextLine();

                    opcionEventos = Integer.parseInt(entradaEventos);

                    if (opcionEventos < 1 || opcionEventos > 5) {
                        opcionEventos = 0;
                        throw new IllegalArgumentException();
                    }

                } catch (NumberFormatException e) {
                    opcionEventos = 0;
                    System.out.println("Error: ingrese una de las opciones indicadas.");
                } catch (IllegalArgumentException e) {
                    opcionEventos = 0;
                    System.out.println("Error: ingrese un numero en el rango indicado.");
                }
            }

            switch (opcionEventos) {
                case 1 -> {
                    System.out.println("Eventos Proximos: ");
                    System.out.println(gestionEventos.listar()); // LOS MUESTRA
                }
                case 2 -> { //crear un nuevo evento
                    Evento nuevo = gestionEventos.crearNuevoEvento();
                    nuevo.asignarIdAutoincremental();
                    System.out.println("** Se agregara el nuevo evento ** ");
                    System.out.println(nuevo);
                    gestionEventos.agregar(nuevo);
                    gestionEventos.guardarEventosJson(); //GUARDA EN EL JSON EL NUEVO EVENTO
                }
                case 3 -> { //Modificar un evento por nombre de evento (SEGUIR)
                    System.out.print("Ingrese el nombre del evento a modificar --> ");
                    entradaEventos = scanner.nextLine();
                    gestionEventos.modificarEventoPorNombre(entradaEventos);
                    gestionEventos.guardarEventosJson(); //GUARDA LAS MODIFICACIONES
                }
                case 4 -> { //eliminar un evento por nombre de evento
                    System.out.print("Ingrese el nombre del evento a eliminar --> ");
                    entradaEventos = scanner.nextLine();
                    gestionEventos.eliminarEventoPorNombre(entradaEventos);
                    gestionEventos.guardarEventosJson(); //GUARDA LAS MODIFICACIONES
                }
                case 5 -> //salir
                        retener = false;
                default -> System.out.println("Opción inválida");
            }
            opcionEventos = 0;
        }
    }



    private void mostrarMenuHabEmpleado() {
        int opcion = 0, confirma = 0;
        String entrada = "";
        boolean retener = true;
        Habitacion aux;
        while (retener)
        {
            System.out.println("VOLVI A RETENER con "+retener);
            System.out.println("------------------------------ Habitaciones (empleado) ------------------------------------");
            System.out.println("[1] Agregar Habitacion");
            System.out.println("[2] Eliminar Habitacion");
            System.out.println("[3] Modificar Habitacion");
            System.out.println("[4] Reservar habitacion a un cliente");
            System.out.println("[5] Salir");

            while( opcion == 0 )
            {
                try
                {
                    System.out.print("\nIngrese su opción (1, 2, 3, 4, 5) --> ");

                    entrada = scanner.nextLine();

                    opcion = Integer.parseInt(entrada);

                    if( opcion < 1 || opcion > 5)
                    {
                        opcion = 0;
                        throw new IllegalArgumentException();
                    }

                }catch (NumberFormatException e)
                {
                    opcion = 0;
                    System.out.println("Error: ingrese una de las opciones indicadas.");
                }
                catch(IllegalArgumentException e)
                {
                    opcion = 0;
                    System.out.println("Error: ingrese un numero en el rango indicado.");
                }
            }

            switch (opcion) {
                case 1 -> {
                    System.out.println("Ingrese el valor diario de la habitacion: ");
                    Double precioDiario = scanner.nextDouble();
                    System.out.println("Ingrese la capacidad maxima de la habitacion: ");
                    int capacidadMaxima = scanner.nextInt();
                    ArrayList<ServiciosHabitacion> serviciosHabitaciones = new ArrayList<>();
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
                    System.out.println("Desea habilitar la Habitacion? 1.Si 2.No");
                    int habilitar = scanner.nextInt();
                    boolean activa = false;
                    if (habilitar == 1) activa = true;
                    //pedir info para la nueva habitacion

                    gestionHabitaciones.agregar(new Habitacion(activa, precioDiario, capacidadMaxima, serviciosHabitaciones));
                    System.out.println("Habitacion agregada con exito");
                }
                case 2 -> {
                    System.out.println("Ingrese el ID de la habitacion que desea eliminar");
                    System.out.println(gestionHabitaciones.listar());
                    opcion = scanner.nextInt();
                    aux = gestionHabitaciones.buscarHabitacion(opcion);
                    if (aux != null) {
                        System.out.println("¿Esta seguro que quiere eliminar esta habitacion? Ingrese 0 para confirmar");
                        confirma = scanner.nextInt();
                        if (confirma == 0) {
                            gestionHabitaciones.eliminar(aux);
                        }
                    }
                }
                case 3 -> {
                    System.out.println("Ingrese el ID de la habitacion que desea eliminar");
                    System.out.println(gestionHabitaciones.listar());
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
                }
                case 4 -> {
                    System.out.println(gestionClientes.getLista());
                    System.out.println("Ingrese el dni del cliente que quiere realizar una reserva:");
                    String dni = scanner.nextLine();
                    Cliente cliente = gestionClientes.buscarCliente(dni);
                    mostrarMenuReserva(cliente);
                }

                case 5 -> retener = false;
                default -> System.out.println("Opcion no valida");
            }
            gestionHabitaciones.guardarHabitacionJson();
            System.out.println("JUSTO DESPUES "+retener);
            opcion=0;
        }
    }

    private void mostrarMenuReserva(Cliente cliente) {
        int opcionReserva = 0;
        String entradaReserva = "";

        System.out.println("------------------Reserva Habitaciones ----------------------");
        System.out.println("[1] Ver todas las habitaciones ");
        System.out.println("[2] Ver Habitaciones disponibles por fecha");
        System.out.println("[3] Realizar una nueva reserva");
        System.out.println("[4] Ver mis reservas activas");
        System.out.println("[5] Cancelar mis reservas");
        System.out.println("[6] Ordenar servicio a la habitacion");
        System.out.println("[7] Salir");

        System.out.print("\nIngrese su opción (1, 2, 3, 4 ,5) --> ");
        entradaReserva = scanner.nextLine();

        opcionReserva = Integer.parseInt(entradaReserva);

        switch (opcionReserva) {
            case 1:
                System.out.println("Habitaciones con las que cuenta el hotel: ");
                System.out.println(gestionHabitaciones.listar());
                break;

            case 2:
                System.out.println("Fecha de checkin:");
                LocalDate checkin = gestionReservas.solicitarLocalDate();
                System.out.println("Fecha de checkout:");
                LocalDate checkOut =gestionReservas.solicitarLocalDate();
                System.out.println("");
                System.out.println(gestionReservas.verHabitacionesDisponiblesPorFechas(checkin,checkOut));
                break;

            case 3:
                gestionReservas.generarReserva(cliente);
                gestionReservas.cargarReservasJson();
                break;

            case 4:
                gestionReservas.verMisReservasActivas(cliente.getDni());

                break;

            case 5:

                gestionClientes.anularReserva(gestionReservas,cliente);
                break;
            case 6:

                cliente.modificarReserva(gestionClientes,cliente);
                break;

            case 7: //salir
                break;

            default:
                System.out.println("Opción inválida");
                break;

        }
        this.gestionReservas.cargarReservasJson();
    }

}
