package sistemaHotelUtn.gestionClientes;

import sistemaHotelUtn.generales.Gestion;
import sistemaHotelUtn.generales.Json.JsonRepo;
import sistemaHotelUtn.gestionReservas.Reserva;

import java.util.ArrayList;
import java.util.Scanner;

public class GestionClientes extends Gestion<Cliente>
{
    public GestionClientes(){
        ArrayList<Cliente> clientesList = new ArrayList<>();
        JsonRepo<Cliente> clientesJson = new JsonRepo<>("clientes",clientesList, Cliente.class);
        clientesList = clientesJson.cargar();
        this.setLista(clientesList);
    }

    public Cliente crearNuevoCliente()
    {
        //SEGUIR
        /*Pide informacion por la consola y crea un nuevo cliente listo para agregar a la lista*/

        Cliente nuevo = new Cliente();

        return nuevo;
    }
}
