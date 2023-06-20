package sistemaHotelUtn.gestionClientes;

import sistemaHotelUtn.generales.Gestion;
import sistemaHotelUtn.generales.Json.JsonRepo;
import sistemaHotelUtn.generales.TipoUsuario;
import sistemaHotelUtn.gestionReservas.Reserva;

import java.util.ArrayList;
import java.util.Scanner;

public class GestionClientes extends Gestion<Cliente>
{
    public GestionClientes(){
    cargarClientesJson();

    }

    public Cliente crearNuevoCliente()
    {
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



    public Cliente buscarClientePorUsername(String username)
    {
        for(Cliente cliente: this.getLista() )
        {
            if(cliente.getUsuario().getUsername().equals(username))
            {
                //si lo encuentra lo retorna
                return cliente;
            }
        }

        // si no lo encuentra retorna null
        return null;
    }

    public void guardarClientesJson()
    {
        ArrayList<Cliente> clientesList = this.getLista();
        JsonRepo<Cliente> clientesJson = new JsonRepo<>("clientes",clientesList, Cliente.class);
        clientesJson.guardar();
    }

    public void cargarClientesJson()
    {
        ArrayList<Cliente> clientesList = new ArrayList<>();
        JsonRepo<Cliente> clientesJson = new JsonRepo<>("clientes",clientesList, Cliente.class);
        clientesList = clientesJson.cargar();
        this.setLista(clientesList);
    }

    /*Daniiiii*/
    public void mostrarClientes (){
        int i=1;
        for (Cliente cliente:this.getLista()) {
            System.out.println(cliente);
        }
    }
    public Cliente buscarCliente (String dni){
        for (Cliente cliente : this.getLista()){
            if(cliente.getDni().equals(dni)){
                return cliente;
            }
        }
        return null;
    }
}
