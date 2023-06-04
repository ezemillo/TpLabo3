package org.example;

import java.util.ArrayList;
import java.util.List;

public class Habitacion {

    private final int id;
    private static int ultimoId;

    private boolean esReservable;

    private Double precioDiario;

    private int capacidadMax;

    private ArrayList serviciosHabitacion;

    public Habitacion(boolean esReservable, Double precioDiario, int capacidadMax, ArrayList serviciosHabitacion) {
        this.id = ultimoId;
        Habitacion.ultimoId++;
        this.esReservable = esReservable;
        this.precioDiario = precioDiario;
        this.capacidadMax = capacidadMax;
        this.serviciosHabitacion = serviciosHabitacion;
    }

    public int getId() {
        return id;
    }

    public boolean isEsReservable() {
        return esReservable;
    }

    public void setEsReservable(boolean esReservable) {
        this.esReservable = esReservable;
    }

    public Double getPrecioDiario() {
        return precioDiario;
    }

    public void setPrecioDiario(Double precioDiario) {
        this.precioDiario = precioDiario;
    }

    public int getCapacidadMax() {
        return capacidadMax;
    }

    public void setCapacidadMax(int capacidadMax) {
        this.capacidadMax = capacidadMax;
    }

    public ArrayList getServiciosHabitacion() {
        return serviciosHabitacion;
    }

    public void setServiciosHabitacion(ArrayList serviciosHabitacion) {
        this.serviciosHabitacion = serviciosHabitacion;
    }

    @Override
    public String toString() {
        return "Habitacion{" +
                "id=" + id +
                ", esReservable=" + esReservable +
                ", precioDiario=" + precioDiario +
                ", capacidadMax=" + capacidadMax +
                ", serviciosHabitacion=" + serviciosHabitacion +
                '}';
    }
}
