package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public List<Cliente> listar(){
        List<Cliente> listarCliente = new ArrayList<>();
        String sql = "SELECT * FROM cliente";
        try {
            con = conectar.conectarServidor();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Cliente cli = new Cliente();
                cli.setIdCliente(rs.getInt(1));
                cli.setNombres(rs.getString(2));
                cli.setApellido_paterno(rs.getString(3));
                cli.setApellido_materno(rs.getString(4));
                cli.setTipo_documento(rs.getString(5));
                cli.setNum_documento(rs.getString(6));
                cli.setCelular(rs.getString(7));
                cli.setEmail(rs.getString(8));
                listarCliente.add(cli);
            }
        } catch (Exception e) {
            System.err.println("Error al listar: "+e.getMessage());
        }
        return listarCliente;            
    }
    
    public List<Cliente> repListar(){
        List<Cliente> listarCliente = new ArrayList<>();
        String sql = "SELECT nombres,apellido_paterno,apellido_materno,tipo_documento,num_documento,celular,email FROM cliente;";
        try {
            con = conectar.conectarServidor();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Cliente cli = new Cliente();
                cli.setNombres(rs.getString(1));
                cli.setApellido_paterno(rs.getString(2));
                cli.setApellido_materno(rs.getString(3));
                cli.setTipo_documento(rs.getString(4));
                cli.setNum_documento(rs.getString(5));
                cli.setCelular(rs.getString(6));
                cli.setEmail(rs.getString(7));
                listarCliente.add(cli);
            }
        } catch (Exception e) {
            System.err.println("Error al listar: "+e.getMessage());
        }
        return listarCliente;            
    }
    
    public int agregar(Cliente cli){
        String sql = "INSERT INTO cliente VALUES(null,?,?,?,?,?,?,?)";
        try{
            con = conectar.conectarServidor();
            ps = con.prepareStatement(sql);
            ps.setString(1, cli.getNombres());
            ps.setString(2, cli.getApellido_paterno());
            ps.setString(3, cli.getApellido_materno());
            ps.setString(4, cli.getTipo_documento());
            ps.setString(5, cli.getNum_documento());
            ps.setString(6, cli.getCelular());
            ps.setString(7, cli.getEmail());
            ps.executeUpdate();
        } catch (Exception e) {
            System.err.println("Error al agregar: "+e.getMessage());
            return 0;
        }
        return 1;
    }
    
    public int actualizar(Cliente cli){
        int r = 0;
        String sql = "UPDATE cliente SET nombres = ?, apellido_paterno = ?, apellido_materno = ?, tipo_documento = ?, num_documento = ?, celular = ?, email = ? WHERE idCliente = ?";
        try {
            con = conectar.conectarServidor();
            ps = con.prepareStatement(sql);
            ps.setString(1, cli.getNombres());
            ps.setString(2, cli.getApellido_paterno());
            ps.setString(3, cli.getApellido_materno());
            ps.setString(4, cli.getTipo_documento());
            ps.setString(5, cli.getNum_documento());
            ps.setString(6, cli.getCelular());
            ps.setString(7, cli.getEmail());
            ps.setInt(8, cli.getIdCliente());
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
    
    public int eliminar(int idCliente){
        String sql = "DELETE FROM cliente WHERE idCliente = "+idCliente;
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
    
    public List<Cliente> listarBuscar(String buscar){
        List<Cliente> listarCliente = new ArrayList<>();
        String sql = "select * from cliente where nombres like '%"+ buscar + "%'";
        try {
            con = conectar.conectarServidor();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Cliente cli = new Cliente();
                cli.setIdCliente(rs.getInt(1));
                cli.setNombres(rs.getString(2));
                cli.setApellido_paterno(rs.getString(3));
                cli.setApellido_materno(rs.getString(4));
                cli.setTipo_documento(rs.getString(5));
                cli.setNum_documento(rs.getString(6));
                cli.setCelular(rs.getString(7));
                cli.setEmail(rs.getString(8));
                listarCliente.add(cli);
            }
        } catch (Exception e) {
            System.err.println("Error al listar: "+e.getMessage());
        }
        return listarCliente;            
    }
}
