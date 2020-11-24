package models;

import java.sql.Date;

public class Reserva {
    private int idReserva;
    private int idCliente;
    private String nombres;
    private String apellido_paterno;
    private String apellido_materno;
    private int idTrabajador;
    private String nombre;
    private String apellido_paterno_trabajador;
    private String apellido_materno_trabajdor;
    private int idHabitacion;
    private String numero;
    private String tipo_reserva;
    private Date fecha_reserva;
    private Date fecha_ingreso;
    private Date fecha_salida;
    private Double costo_alojamiento;
    private int estado_reserva;

    public Reserva() {
    }

    public Reserva(int idReserva, int idCliente, String nombres, String apellido_paterno, String apellido_materno, int idTrabajador, String nombre, String apellido_paterno_trabajador, String apellido_materno_trabajdor, int idHabitacion, String numero, String tipo_reserva, Date fecha_reserva, Date fecha_ingreso, Date fecha_salida, Double costo_alojamiento, int estado_reserva) {
        this.idReserva = idReserva;
        this.idCliente = idCliente;
        this.nombres = nombres;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        this.idTrabajador = idTrabajador;
        this.nombre = nombre;
        this.apellido_paterno_trabajador = apellido_paterno_trabajador;
        this.apellido_materno_trabajdor = apellido_materno_trabajdor;
        this.idHabitacion = idHabitacion;
        this.numero = numero;
        this.tipo_reserva = tipo_reserva;
        this.fecha_reserva = fecha_reserva;
        this.fecha_ingreso = fecha_ingreso;
        this.fecha_salida = fecha_salida;
        this.costo_alojamiento = costo_alojamiento;
        this.estado_reserva = estado_reserva;
    }

    public Reserva(int idReserva, int idCliente, int idTrabajador, int idHabitacion, String tipo_reserva, Date fecha_reserva, Date fecha_ingreso, Date fecha_salida, Double costo_alojamiento, int estado_reserva) {
        this.idReserva = idReserva;
        this.idCliente = idCliente;
        this.idTrabajador = idTrabajador;
        this.idHabitacion = idHabitacion;
        this.tipo_reserva = tipo_reserva;
        this.fecha_reserva = fecha_reserva;
        this.fecha_ingreso = fecha_ingreso;
        this.fecha_salida = fecha_salida;
        this.costo_alojamiento = costo_alojamiento;
        this.estado_reserva = estado_reserva;
    }

    public Reserva(int idCliente, int idTrabajador, int idHabitacion, String tipo_reserva, Date fecha_reserva, Date fecha_ingreso, Date fecha_salida, Double costo_alojamiento, int estado_reserva) {
        this.idCliente = idCliente;
        this.idTrabajador = idTrabajador;
        this.idHabitacion = idHabitacion;
        this.tipo_reserva = tipo_reserva;
        this.fecha_reserva = fecha_reserva;
        this.fecha_ingreso = fecha_ingreso;
        this.fecha_salida = fecha_salida;
        this.costo_alojamiento = costo_alojamiento;
        this.estado_reserva = estado_reserva;
    }

    public Reserva(int idReserva, int idCliente, int idTrabajador, int idHabitacion, String tipo_reserva, Double costo_alojamiento, int estado_reserva) {
        this.idReserva = idReserva;
        this.idCliente = idCliente;
        this.idTrabajador = idTrabajador;
        this.idHabitacion = idHabitacion;
        this.tipo_reserva = tipo_reserva;
        this.costo_alojamiento = costo_alojamiento;
        this.estado_reserva = estado_reserva;
    }
    
    

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellido_paterno() {
        return apellido_paterno;
    }

    public void setApellido_paterno(String apellido_paterno) {
        this.apellido_paterno = apellido_paterno;
    }

    public String getApellido_materno() {
        return apellido_materno;
    }

    public void setApellido_materno(String apellido_materno) {
        this.apellido_materno = apellido_materno;
    }

    public int getIdTrabajador() {
        return idTrabajador;
    }

    public void setIdTrabajador(int idTrabajador) {
        this.idTrabajador = idTrabajador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido_paterno_trabajador() {
        return apellido_paterno_trabajador;
    }

    public void setApellido_paterno_trabajador(String apellido_paterno_trabajador) {
        this.apellido_paterno_trabajador = apellido_paterno_trabajador;
    }

    public String getApellido_materno_trabajdor() {
        return apellido_materno_trabajdor;
    }

    public void setApellido_materno_trabajdor(String apellido_materno_trabajdor) {
        this.apellido_materno_trabajdor = apellido_materno_trabajdor;
    }

    public int getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(int idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTipo_reserva() {
        return tipo_reserva;
    }

    public void setTipo_reserva(String tipo_reserva) {
        this.tipo_reserva = tipo_reserva;
    }

    public Date getFecha_reserva() {
        return fecha_reserva;
    }

    public void setFecha_reserva(Date fecha_reserva) {
        this.fecha_reserva = fecha_reserva;
    }

    public Date getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(Date fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public Date getFecha_salida() {
        return fecha_salida;
    }

    public void setFecha_salida(Date fecha_salida) {
        this.fecha_salida = fecha_salida;
    }

    public Double getCosto_alojamiento() {
        return costo_alojamiento;
    }

    public void setCosto_alojamiento(Double costo_alojamiento) {
        this.costo_alojamiento = costo_alojamiento;
    }

    public int getEstado_reserva() {
        return estado_reserva;
    }

    public void setEstado_reserva(int estado_reserva) {
        this.estado_reserva = estado_reserva;
    }

    
    
}
