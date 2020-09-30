package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class HabitacionDAO {
    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public List<Habitacion> listar(){
        List<Habitacion> listarHabitacion = new ArrayList<>();
        String sql = "SELECT * FROM habitacion";
        try {
            con = conectar.conectarServidor();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Habitacion hab = new Habitacion();
                hab.setIdHabitacion(rs.getInt(1));
                hab.setNumero(rs.getString(2));
                hab.setPiso(rs.getString(3));
                hab.setPrecio(rs.getDouble(4));
                hab.setEstado(rs.getString(5));
                hab.setTipo_Habitacion(rs.getString(6));
                listarHabitacion.add(hab);
            }
        } catch (Exception e) {
            System.err.println("Error al listar: "+e.getMessage());
        }
        return listarHabitacion;            
    }
    
    public int agregar(Habitacion hab){
        String sql = "INSERT INTO habitacion VALUES(null,?,?,?,?,?)";
        try{
            con = conectar.conectarServidor();
            ps = con.prepareStatement(sql);
            ps.setString(1, hab.getNumero());
            ps.setString(2, hab.getPiso());
            ps.setDouble(3, hab.getPrecio());
            ps.setString(4, hab.getEstado());
            ps.setString(5, hab.getTipo_Habitacion());
            ps.executeUpdate();
        } catch (Exception e) {
            System.err.println("Error al agregar: "+e.getMessage());
            return 0;
        }
        return 1;
    }
    
    public int actualizar(Habitacion hab){
        int r = 0;
        String sql = "UPDATE habitacion SET numero = ?, piso = ?, precio = ?, estado = ?, tipo_habitacion = ? WHERE idHabitacion = ?";
        try {
            con = conectar.conectarServidor();
            ps = con.prepareStatement(sql);
            ps.setString(1, hab.getNumero());
            ps.setString(2, hab.getPiso());
            ps.setDouble(3, hab.getPrecio());
            ps.setString(4, hab.getEstado());
            ps.setString(5, hab.getTipo_Habitacion());
            ps.setInt(6, hab.getIdHabitacion());
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
    
    public int eliminar(int idHabitacion){
        String sql = "DELETE FROM habitacion WHERE idHabitacion = "+idHabitacion;
        try {
            con = conectar.conectarServidor();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
            return 1;
        } catch (Exception e) {
            System.err.println("Error al eliminar: "+e.getMessage());
            return 0;
        }
    }
}
