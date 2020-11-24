package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TrabajadorDAO {
    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public List<Trabajador> listar(){
        List<Trabajador> listarTrabajador = new ArrayList<>();
        String sql = "SELECT * FROM trabajador WHERE estado_trabajador = 1";
        try {
            con = conectar.conectarServidor();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Trabajador trab = new Trabajador();
                trab.setId_trabajador(rs.getInt(1));
                trab.setNombre(rs.getString(2));
                trab.setApe_paterno(rs.getString(3));
                trab.setApe_materno(rs.getString(4));
                trab.setTipo_documento(rs.getString(5));
                trab.setNum_documento(rs.getString(6));
                trab.setCelular(rs.getString(7));
                trab.setEmail(rs.getString(8));
                trab.setSueldo(rs.getFloat(9));
                trab.setAcceso(rs.getString(10));
                trab.setLogin(rs.getString(11));
                trab.setPassword(rs.getString(12));
                trab.setEstado(rs.getInt(13));
                listarTrabajador.add(trab);
            }
        } catch (Exception e) {
            System.err.println("Error al listar trabajador: "+e.getMessage());
        }
        return listarTrabajador;            
    }
    
    public List<Trabajador> repListar(){
        List<Trabajador> listarTrabajador = new ArrayList<>();
        String sql = "SELECT nombre,apellido_paterno_trabajador,apellido_materno_trabajador,tipo_documento_trabajador,num_documento_trabajador,celular_trabajador,email_trabajador,sueldo,acceso,login FROM trabajador WHERE estado_trabajador = 1";
        try {
            con = conectar.conectarServidor();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Trabajador trab = new Trabajador();
                trab.setNombre(rs.getString(1));
                trab.setApe_paterno(rs.getString(2));
                trab.setApe_materno(rs.getString(3));
                trab.setTipo_documento(rs.getString(4));
                trab.setNum_documento(rs.getString(5));
                trab.setCelular(rs.getString(6));
                trab.setEmail(rs.getString(7));
                trab.setSueldo(rs.getFloat(8));
                trab.setAcceso(rs.getString(9));
                trab.setLogin(rs.getString(10));
                listarTrabajador.add(trab);
            }
        } catch (Exception e) {
            System.err.println("Error al listar trabajador: "+e.getMessage());
        }
        return listarTrabajador;            
    }
    
    public int agregar(Trabajador trab){
        String sql = "INSERT INTO trabajador VALUES(null,?,?,?,?,?,?,?,?,?,?,?,?)";
        try{
            con = conectar.conectarServidor();
            ps = con.prepareStatement(sql);
            ps.setString(1, trab.getNombre());
            ps.setString(2, trab.getApe_paterno());
            ps.setString(3, trab.getApe_materno());
            ps.setString(4, trab.getTipo_documento());
            ps.setString(5, trab.getNum_documento());
            ps.setString(6, trab.getCelular());
            ps.setString(7, trab.getEmail());
            ps.setDouble(8, trab.getSueldo());
            ps.setString(9, trab.getAcceso());
            ps.setString(10,trab.getLogin());
            ps.setString(11,trab.getPassword());
            ps.setInt(12,trab.getEstado());
            ps.executeUpdate();
        } catch (Exception e) {
            System.err.println("Error al agregar trabajador: "+e.getMessage());
            return 0;
        }
        return 1;
    }
    
    public int actualizar(Trabajador trab){
        int r = 0;
        String sql = "UPDATE trabajador SET nombre = ?, apellido_paterno_trabajador = ?, apellido_materno_trabajador = ?, tipo_documento_trabajador = ?, num_documento_trabajador = ?, celular_trabajador = ?, email_trabajador = ?, sueldo = ?, acceso = ?, login = ?, password = ?, estado_trabajador = ? WHERE idTrabajador = ?";
        try {
            con = conectar.conectarServidor();
            ps = con.prepareStatement(sql);
            ps.setString(1, trab.getNombre());
            ps.setString(2, trab.getApe_paterno());
            ps.setString(3, trab.getApe_materno());
            ps.setString(4, trab.getTipo_documento());
            ps.setString(5, trab.getNum_documento());
            ps.setString(6, trab.getCelular());
            ps.setString(7, trab.getEmail());
            ps.setDouble(8,trab.getSueldo());
            ps.setString(9,trab.getAcceso());
            ps.setString(10,trab.getLogin());
            ps.setString(11,trab.getPassword());
            ps.setInt(12,trab.getEstado());
            ps.setInt(13, trab.getId_trabajador());
            r = ps.executeUpdate();
            if(r == 1){
                return 1;
            }else{
                return 0;
            }
        } catch (Exception e) {
            System.err.println("Error al actualizar trabajador: "+e.getMessage()); 
        }
        return r;
    }
    
    public int eliminar(int idTrabajador){
        String sql = "UPDATE trabajador SET estado_trabajador = 0 WHERE idTrabajador = "+idTrabajador;
        try {
            con = conectar.conectarServidor();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
            return 1;
        } catch (Exception e) {
            System.err.println("Error al eliminar trabajador: "+e.getMessage());
            return 0;
        }
    }
    
    public List<Trabajador> login(String login,String password){
        List<Trabajador> listarTrabajador = new ArrayList<>();
        String sql = "SELECT idTrabajador,nombre,apellido_paterno_trabajador,apellido_materno_trabajador,acceso,login,password,estado_trabajador FROM trabajador "
                + "where login= '"+login +"'and password='"+password+"'and estado_trabajador = 1";
        try {
            con = conectar.conectarServidor();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Trabajador trab = new Trabajador();
                trab.setId_trabajador(rs.getInt(1));
                trab.setNombre(rs.getString(2));
                trab.setApe_paterno(rs.getString(3));
                trab.setApe_materno(rs.getString(4));
                trab.setAcceso(rs.getString(5));
                trab.setLogin(rs.getString(6));
                trab.setPassword(rs.getString(7));
                trab.setEstado(rs.getInt(8));
                listarTrabajador.add(trab);
            }
        } catch (Exception e) {
            System.err.println("Error al recibir trabajador: "+e.getMessage());
        }
        return listarTrabajador;            
    }
    
    public List<Trabajador> listarBuscar(String buscar){
        List<Trabajador> listarTrabajador = new ArrayList<>();
        String sql = "SELECT * FROM trabajador WHERE estado_trabajador = 1 and nombre like '%"+ buscar + "%'";
        try {
            con = conectar.conectarServidor();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Trabajador trab = new Trabajador();
                trab.setId_trabajador(rs.getInt(1));
                trab.setNombre(rs.getString(2));
                trab.setApe_paterno(rs.getString(3));
                trab.setApe_materno(rs.getString(4));
                trab.setTipo_documento(rs.getString(5));
                trab.setNum_documento(rs.getString(6));
                trab.setCelular(rs.getString(7));
                trab.setEmail(rs.getString(8));
                trab.setSueldo(rs.getFloat(9));
                trab.setAcceso(rs.getString(10));
                trab.setLogin(rs.getString(11));
                trab.setPassword(rs.getString(12));
                trab.setEstado(rs.getInt(13));
                listarTrabajador.add(trab);
            }
        } catch (Exception e) {
            System.err.println("Error al listar trabajador: "+e.getMessage());
        }
        return listarTrabajador;            
    }
}
