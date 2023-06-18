/*
* Representa un evento que se realiza en un hotel, con un nombre ej :
* "Charla del medioambiente en MDP", y con un organizador, ej: "Municipalidad de GP".
* por Blanco, Santiago.
* */

package sistemaHotelUtn.gestionEventos;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Evento implements Serializable
{
    private static int contadorEventos = 0;
    private int id;
    private String nombreEvento = "";
    private String organizador = "";
    private int participantes = 0;
    private LocalDateTime fechaHoraInicio = LocalDateTime.now();
    private LocalDateTime fechaHoraFin = LocalDateTime.now();

    public Evento(String nombreEvento, String organizador, int participantes,
                  LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin)
    {
        this.id = ++contadorEventos;
        this.nombreEvento = nombreEvento;
        this.organizador = organizador;

        if( argParticipantesEsCorrecto(participantes) )
            this.participantes = participantes;

        this.fechaHoraInicio = fechaHoraInicio;

        if( argFechaEsCorrecta(fechaHoraFin) )
            this.fechaHoraFin = fechaHoraFin;
    }

    public int getId() {
        return id;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public String getOrganizador() {
        return organizador;
    }

    public void setOrganizador(String organizador) {
        this.organizador = organizador;
    }

    public int getParticipantes() {
        return participantes;
    }

    public void setParticipantes(int participantes)
    {
        if( argParticipantesEsCorrecto(participantes) )
            this.participantes = participantes;
    }

    public String getFechaHoraInicio()
    {
        /*
        Devuelve la hora en formato dia-mes-año hora:min
        ej: 04-06-2022 18:40
         */
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:MM");
        String fechaFormateada = this.fechaHoraInicio.format(formato);

        return fechaFormateada;
    }

    public void setFechaHoraInicio(LocalDateTime fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public String getFechaHoraFin()
    {
        /*
        Devuelve la hora en formato dia-mes-año hora:min
        ej: 04-06-2022 18:40
         */

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:MM");
        String fechaFormateada = this.fechaHoraFin.format(formato);

        return fechaFormateada;
    }
    public void setFechaHoraFin(LocalDateTime fechaHoraFin)
    {
        if( argFechaEsCorrecta(fechaHoraFin) )
            this.fechaHoraFin = fechaHoraFin;
    }

    private boolean argParticipantesEsCorrecto(int part) throws IllegalArgumentException
    {
        if( part > 0 )
            return true;
        else
            throw new IllegalArgumentException("Los participantes deben ser mayor que cero.");

    }

    private boolean argFechaEsCorrecta(LocalDateTime f) throws IllegalArgumentException
    {
        if( ! this.fechaHoraInicio.equals(f) )
            return true;
        else
            throw new IllegalArgumentException("La fecha de fin no puede ser igual a la fecha de inicio.");
    }

    @Override
    public String toString() {
        return "{\n\tid: " + id +
                ",\n\tEvento: " + nombreEvento +
                ",\n\tOrganizador: " + organizador +
                ",\n\tParticipantes: " + participantes +
                ",\n\tInicia: " + getFechaHoraInicio() +
                ",\n\tFinaliza: " + getFechaHoraFin() +
                "\n" + "}";
    }
}
