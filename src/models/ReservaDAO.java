package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ReservaDAO {
    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public List<Reserva> listar(){
        List<Reserva> listarReserva = new ArrayList<>();
        String sql = "select idReserva, idCliente, nombres, apellido_paterno, apellido_materno, \n" +
        "idTrabajador, nombre, apellido_paterno_trabajador, apellido_materno_trabajador, \n" +
        "idHabitacion, numero,\n" +
        "tipo_reserva, fecha_reserva, fecha_ingreso, fecha_salida, costo_alojamiento, estado_reserva from reserva \n" +
        "inner join cliente using (idCliente)\n" +
        "inner join trabajador using (idTrabajador)\n" +
        "inner join habitacion using (idHabitacion) where estado_reserva = 1";
        try {
            con = conectar.conectarServidor();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Reserva res = new Reserva();
                res.setIdReserva(rs.getInt(1));
                res.setIdCliente(rs.getInt(2));
                res.setNombres(rs.getString(3));
                res.setApellido_paterno(rs.getString(4));
                res.setApellido_materno(rs.getString(5));
                res.setIdTrabajador(rs.getInt(6));
                res.setNombre(rs.getString(7));
                res.setApellido_paterno_trabajador(rs.getString(8));
                res.setApellido_materno_trabajdor(rs.getString(9));
                res.setIdHabitacion(rs.getInt(10));
                res.setNumero(rs.getString(11));
                res.setTipo_reserva(rs.getString(12));
                res.setFecha_reserva(rs.getDate(13));
                res.setFecha_ingreso(rs.getDate(14));
                res.setFecha_salida(rs.getDate(15));
                res.setCosto_alojamiento(rs.getDouble(16));
                res.setEstado_reserva(rs.getInt(17));
                listarReserva.add(res);
            }
        } catch (Exception e) {
            System.err.println("Error al listar reserva: "+e.getMessage());
        }
        return listarReserva;            
    }
    
    public List<Reserva> repListar(){
        List<Reserva> listarReserva = new ArrayList<>();
        String sql = "select nombres, apellido_paterno, apellido_materno, \n" +
        "nombre, apellido_paterno_trabajador, apellido_materno_trabajador, \n" +
        "numero, tipo_reserva, fecha_reserva, fecha_ingreso, fecha_salida, costo_alojamiento from reserva \n" +
        "inner join cliente using (idCliente)\n" +
        "inner join trabajador using (idTrabajador)\n" +
        "inner join habitacion using (idHabitacion) where estado_reserva = 1;";
        try {
            con = conectar.conectarServidor();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Reserva res = new Reserva();
                res.setNombres(rs.getString(1));
                res.setApellido_paterno(rs.getString(2));
                res.setApellido_materno(rs.getString(3));
                res.setNombre(rs.getString(4));
                res.setApellido_paterno_trabajador(rs.getString(5));
                res.setApellido_materno_trabajdor(rs.getString(6));
                res.setNumero(rs.getString(7));
                res.setTipo_reserva(rs.getString(8));
                res.setFecha_reserva(rs.getDate(9));
                res.setFecha_ingreso(rs.getDate(10));
                res.setFecha_salida(rs.getDate(11));
                res.setCosto_alojamiento(rs.getDouble(12));
                listarReserva.add(res);
            }
        } catch (Exception e) {
            System.err.println("Error al listar reserva: "+e.getMessage());
        }
        return listarReserva;            
    }
    
    public List<Reserva> listarBuscar(String buscar){
        List<Reserva> listarReserva = new ArrayList<>();
        String sql = "select idReserva, idCliente, nombres, apellido_paterno, apellido_materno, \n" +
        "idTrabajador, nombre, apellido_paterno_trabajador, apellido_materno_trabajador, \n" +
        "idHabitacion, numero,\n" +
        "tipo_reserva, fecha_reserva, fecha_ingreso, fecha_salida, costo_alojamiento, estado_reserva from reserva \n" +
        "inner join cliente using (idCliente)\n" +
        "inner join trabajador using (idTrabajador)\n" +
        "inner join habitacion using (idHabitacion) where estado_reserva = 1 and nombres like '%"+ buscar + "%'";
        try {
            con = conectar.conectarServidor();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Reserva res = new Reserva();
                res.setIdReserva(rs.getInt(1));
                res.setIdCliente(rs.getInt(2));
                res.setNombres(rs.getString(3));
                res.setApellido_paterno(rs.getString(4));
                res.setApellido_materno(rs.getString(5));
                res.setIdTrabajador(rs.getInt(6));
                res.setNombre(rs.getString(7));
                res.setApellido_paterno_trabajador(rs.getString(8));
                res.setApellido_materno_trabajdor(rs.getString(9));
                res.setIdHabitacion(rs.getInt(10));
                res.setNumero(rs.getString(11));
                res.setTipo_reserva(rs.getString(12));
                res.setFecha_reserva(rs.getDate(13));
                res.setFecha_ingreso(rs.getDate(14));
                res.setFecha_salida(rs.getDate(15));
                res.setCosto_alojamiento(rs.getDouble(16));
                res.setEstado_reserva(rs.getInt(17));
                listarReserva.add(res);
            }
        } catch (Exception e) {
            System.err.println("Error al listar reserva: "+e.getMessage());
        }
        return listarReserva;            
    }
    
    public int agregar(Reserva res){
        String sql = "INSERT INTO reserva VALUES(null,?,?,?,?,?,?,?,?,?)";
        try{
            con = conectar.conectarServidor();
            ps = con.prepareStatement(sql);
            ps.setInt(1, res.getIdCliente());
            ps.setInt(2, res.getIdTrabajador());
            ps.setInt(3, res.getIdHabitacion());
            ps.setString(4, res.getTipo_reserva());
            ps.setDate(5, res.getFecha_reserva());
            ps.setDate(6, res.getFecha_ingreso());
            ps.setDate(7, res.getFecha_salida());
            ps.setDouble(8, res.getCosto_alojamiento());
            ps.setInt(9, res.getEstado_reserva());
            ps.executeUpdate();
        } catch (Exception e) {
            System.err.println("Error al agregar reserva: "+e.getMessage());
            return 0;
        }
        return 1;
    }
    
    public int actualizar(Reserva res){
        int r = 0;
        String sql = "UPDATE reserva SET idCliente = ?, idTrabajador = ?, idHabitacion = ?, tipo_reserva = ?, costo_alojamiento = ?, estado_reserva = ? WHERE idReserva = ?";
        try {
            con = conectar.conectarServidor();
            ps = con.prepareStatement(sql);
            ps.setInt(1, res.getIdCliente());
            ps.setInt(2, res.getIdTrabajador());
            ps.setInt(3, res.getIdHabitacion());
            ps.setString(4, res.getTipo_reserva());
            ps.setDouble(5, res.getCosto_alojamiento());
            ps.setInt(6, res.getEstado_reserva());
            ps.setInt(7, res.getIdReserva());
            r = ps.executeUpdate();
            if(r == 1){
                return 1;
            }else{
                return 0;
            }
        } catch (Exception e) {
            System.err.println("Error al actualizar: "+e.getMessage()); 
        }
        return r;
    }
    
    
    public int eliminar(int idReserva){
        String sql = "UPDATE reserva SET estado_reserva = 0 WHERE idReserva = "+idReserva;
        try {
            con = conectar.conectarServidor();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
            return 1;
        } catch (Exception e) {
            System.err.println("Error al eliminar reserva: "+e.getMessage());
            return 0;
        }
    }
}
