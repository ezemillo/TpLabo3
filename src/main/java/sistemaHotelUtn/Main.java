package sistemaHotelUtn;


import java.time.LocalDateTime; //test clase evento
import java.time.format.DateTimeFormatter; //test clase evento

public class Main
{
    public static void testearClaseEvento()
    {
        LocalDateTime t = LocalDateTime.of(2023, 6, 4, 20, 00);
        LocalDateTime t2 = LocalDateTime.of(2023, 6, 4, 22, 00);
        System.out.println(t);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String formattedDateTime= t.format(dateTimeFormatter);
        System.out.println(formattedDateTime);

        Evento e = new Evento("Mardel Valley", "UTN MDP", 200,t,t2);
        Evento e2 = new Evento("Hackathon", "Accenture", 70,t,t2);

        System.out.println(e.getFechaHoraInicio());

        System.out.println(e);
        System.out.println(e2);

    }

    public static void testearClaseGestionEvento()
    {
        LocalDateTime t = LocalDateTime.of(2023, 6, 4, 20, 00);
        LocalDateTime t2 = LocalDateTime.of(2023, 6, 4, 22, 00);
        System.out.println(t);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String formattedDateTime= t.format(dateTimeFormatter);
        System.out.println(formattedDateTime);

        Evento e = new Evento("Mardel Valley", "UTN MDP", 200,t,t2);
        Evento e2 = new Evento("Hackathon", "Accenture", 70,t,t2);

        GestionEventos ge = new GestionEventos();

        ge.agregar(e);
        ge.agregar(e2);

        System.out.println( ge.listar() );

    }
    public static void main(String[] args)
    {
        GestionHostel.init();

        //testearClaseEvento();
        //testearClaseGestionEvento();
    }
}