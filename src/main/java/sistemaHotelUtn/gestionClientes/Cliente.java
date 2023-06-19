package sistemaHotelUtn.gestionClientes;

import sistemaHotelUtn.generales.Persona;

import java.io.Serializable;

public class Cliente extends Persona  {

    private Double saldo;
    private boolean estaActivo;

    public Cliente() {
    }

    public Cliente(String username, String password)
    {
        this.generarUsuario(username, password);
    }

    public Cliente(String usuario, String contraseña, String nombre, String apellido, String dni, String domicilio, String telefono, Double saldo, boolean estaActivo) {
        super(usuario, contraseña, nombre, apellido, dni, domicilio, telefono);
        this.saldo = saldo;
        this.estaActivo = estaActivo;
    }
    //region Getters y Setters
    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public boolean isEstaActivo() {
        return estaActivo;
    }

    public void setEstaActivo(boolean estaActivo) {
        this.estaActivo = estaActivo;
    }
    //endregion
    @Override
    public String toString() {
        return "Cliente{" +
                "saldo=" + saldo +
                ", estaActivo=" + estaActivo +
                '}';
    }
}
