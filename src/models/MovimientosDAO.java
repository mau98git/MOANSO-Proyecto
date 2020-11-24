package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MovimientosDAO {
    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public List<Movimientos> listar(){
        List<Movimientos> listarMovimientos = new ArrayList<>();
        String sql = "SELECT * FROM movimiento WHERE estado_movimiento=1";
        try {
            con = conectar.conectarServidor();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Movimientos mov = new Movimientos();
                mov.setIdMovimiento(rs.getInt(1));
                mov.setNombreCliente(rs.getString(2));
                mov.setApellido_p(rs.getString(3));
                mov.setApellido_m(rs.getString(4));
                mov.setHabitacion(rs.getString(5));
                mov.setFecha_hora_ingreso(rs.getTimestamp(6));
                mov.setFecha_hora_salida(rs.getTimestamp(7));
                listarMovimientos.add(mov);
            }
        } catch (Exception e) {
            System.err.println("Error al listar: "+e.getMessage());
        }
        return listarMovimientos;            
    }
    
    public int actualizar(Movimientos mov){
        int r = 0;
        String sql = "UPDATE movimiento SET habitacionCliente = ? WHERE idMovimiento = ?";
        try {
            con = conectar.conectarServidor();
            ps = con.prepareStatement(sql);
            ps.setString(1, mov.getHabitacion());
            ps.setInt(2, mov.getIdMovimiento());
            r = ps.executeUpdate();
            if(r == 1){
                return 1;
            }else{
                return 0;
            }
        } catch (Exception e) {
            System.err.println("Error al actualizar movimiento: "+e.getMessage()); 
        }
        return r;
    }
    
    public int actualizar_fecha_hora_salida(int idMovimiento){
        String sql = "UPDATE movimiento SET fecha_hora_salida = sysdate() WHERE idMovimiento = "+idMovimiento;
        try {
            con = conectar.conectarServidor();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
            return 1;
        } catch (Exception e) {
            System.err.println("Error al actualizar salida del cliente: "+e.getMessage());
            return 0;
        }
    }
    
    public int actualizar_fecha_hora_entrada(int idMovimiento){
        String sql = "UPDATE movimiento SET fecha_hora_ingreso = sysdate() WHERE idMovimiento = "+idMovimiento;
        try {
            con = conectar.conectarServidor();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
            return 1;
        } catch (Exception e) {
            System.err.println("Error al actualizar entrada del cliente: "+e.getMessage());
            return 0;
        }
    }
    
    public int eliminar(int idMovimiento){
        String sql = "UPDATE movimiento SET habitacionCliente = null, fecha_hora_ingreso = null, fecha_hora_salida = null  WHERE idMovimiento = "+idMovimiento;
        try {
            con = conectar.conectarServidor();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
            return 1;
        } catch (Exception e) {
            System.err.println("Error al eliminar movimiento: "+e.getMessage());
            return 0;
        }
    }
}
