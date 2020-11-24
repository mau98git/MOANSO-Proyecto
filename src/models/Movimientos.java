package models;

import java.sql.Timestamp;

public class Movimientos {
    private int idMovimiento;
    private String nombreCliente;
    private String apellido_p;
    private String apellido_m;
    private String habitacion;
    private Timestamp fecha_hora_ingreso;
    private Timestamp fecha_hora_salida;

    public Movimientos() {
    }

    public Movimientos(int idMovimiento, String nombreCliente, String apellido_p, String apellido_m, String habitacion, Timestamp fecha_hora_ingreso, Timestamp fecha_hora_salida) {
        this.idMovimiento = idMovimiento;
        this.nombreCliente = nombreCliente;
        this.apellido_p = apellido_p;
        this.apellido_m = apellido_m;
        this.habitacion = habitacion;
        this.fecha_hora_ingreso = fecha_hora_ingreso;
        this.fecha_hora_salida = fecha_hora_salida;
    }

    public Movimientos(String nombreCliente, String apellido_p, String apellido_m, String habitacion, Timestamp fecha_hora_ingreso, Timestamp fecha_hora_salida) {
        this.nombreCliente = nombreCliente;
        this.apellido_p = apellido_p;
        this.apellido_m = apellido_m;
        this.habitacion = habitacion;
        this.fecha_hora_ingreso = fecha_hora_ingreso;
        this.fecha_hora_salida = fecha_hora_salida;
    }

    public int getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(int idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getApellido_p() {
        return apellido_p;
    }

    public void setApellido_p(String apellido_p) {
        this.apellido_p = apellido_p;
    }

    public String getApellido_m() {
        return apellido_m;
    }

    public void setApellido_m(String apellido_m) {
        this.apellido_m = apellido_m;
    }

    public String getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(String habitacion) {
        this.habitacion = habitacion;
    }

    public Timestamp getFecha_hora_ingreso() {
        return fecha_hora_ingreso;
    }

    public void setFecha_hora_ingreso(Timestamp fecha_hora_ingreso) {
        this.fecha_hora_ingreso = fecha_hora_ingreso;
    }

    public Timestamp getFecha_hora_salida() {
        return fecha_hora_salida;
    }

    public void setFecha_hora_salida(Timestamp fecha_hora_salida) {
        this.fecha_hora_salida = fecha_hora_salida;
    }

    
    
    
}
