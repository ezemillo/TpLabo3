package sistemaHotelUtn.gestionEventos;

import sistemaHotelUtn.generales.Gestion;
import sistemaHotelUtn.generales.Json.JsonRepo;
import sistemaHotelUtn.gestionEmpleados.Empleado;

import java.util.ArrayList;

public class GestionEventos extends Gestion<Evento>
{
    public GestionEventos(){
        ArrayList<Evento> eventosList = new ArrayList<>();
        JsonRepo<Evento> eventosJson = new JsonRepo<>("eventos",eventosList, Evento.class);
        eventosList=eventosJson.cargar();
        this.setLista(eventosList);



    }
}
