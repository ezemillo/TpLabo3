package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GestionHostel {

    private int idUsuarioActivo;

    public static void init(){
        ArrayList <ServiciosHabitacion> serviciosHabitacionList = new ArrayList<ServiciosHabitacion>();
        serviciosHabitacionList.add(ServiciosHabitacion.JACUZZI);

        org.example.Cliente cliente = new org.example.Cliente("usuario1","1234","eze","fran","3562","gascon 1000","155",0.0,true);
        Habitacion habitacion = new Habitacion(true,250.20,3,serviciosHabitacionList);
        Reserva reserva = new Reserva(LocalDate.of(2023,7,25),LocalDate.of(2023,7,28),cliente,habitacion);

        System.out.println("habitacion = " +habitacion );
        System.out.println("cliente = " + cliente );
        System.out.println("reserva = " +reserva);



    }

}
