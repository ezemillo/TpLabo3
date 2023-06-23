/*
 * Representa un evento que se realiza en un hotel, con un nombre ej :
 * "Charla del medioambiente en MDP", y con un organizador, ej: "Municipalidad de GP".
 * por Blanco, Santiago.
 * */

package sistemaHotelUtn.gestionEventos;

import sistemaHotelUtn.generales.Persona;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Evento {
    //region Atributos
    private static int contadorEventos = 0;
    private int id = 0;
    private boolean idEstaAsignado = false;
    private String nombreEvento = "";
    private String organizador = "";
    private int participantes = 0;
    private LocalDateTime fechaHoraInicio = LocalDateTime.now();
    private LocalDateTime fechaHoraFin = LocalDateTime.now().plusHours(2);
    //endregion

    //region Constructores
    public Evento() {
    }

    public Evento(String nombreEvento, String organizador, int participantes) {
        this.nombreEvento = nombreEvento;
        this.organizador = organizador;
        this.participantes = participantes;
    }

    public Evento(String nombreEvento, String organizador, int participantes,
                  LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin) {
        this.nombreEvento = nombreEvento;
        this.organizador = organizador;

        if (argParticipantesEsCorrecto(participantes))
            this.participantes = participantes;

        this.fechaHoraInicio = fechaHoraInicio;

        if (argFechaEsCorrecta(fechaHoraFin))
            this.fechaHoraFin = fechaHoraFin;
    }
    //endregion

    //region Getters y Setters
    public int getId() {
        return id;
    }

    public void asignarIdAutoincremental()
    {
        if( ! idEstaAsignado )
        {
            this.id = ++Evento.contadorEventos;
            this.idEstaAsignado = true;
        }
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

    public void setParticipantes(int participantes) {
        if (argParticipantesEsCorrecto(participantes))
            this.participantes = participantes;
    }

    public LocalDateTime getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(LocalDateTime fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public LocalDateTime getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(LocalDateTime fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }
    //endregion

    private boolean argParticipantesEsCorrecto(int part) throws IllegalArgumentException {
        if (part > 0)
            return true;
        else
            throw new IllegalArgumentException("Los participantes deben ser mayor que cero.");

    }

    private boolean argFechaEsCorrecta(LocalDateTime f) throws IllegalArgumentException {
        if (!this.fechaHoraInicio.equals(f))
            return true;
        else
            throw new IllegalArgumentException("La fecha de fin no puede ser igual a la fecha de inicio.");
    }

    @Override
    public String toString() {
        return "{\t" +
                "id Evento: " + id +
                ",|| \tEvento: " + nombreEvento +
                ",||\tOrganizador: " + organizador +
                ",||\tParticipantes: " + participantes +
                ",||\tInicia: " + getFechaHoraInicio() +
                ",||\tFinaliza: " + getFechaHoraFin() +
                "" + "}";
    }
}
