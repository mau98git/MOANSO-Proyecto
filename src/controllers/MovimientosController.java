/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import models.Movimientos;
import models.MovimientosDAO;
import views.VistaMovimientos;

/**
 *
 * @author Diego
 */
public class MovimientosController implements ActionListener{
    boolean listar;
    boolean actualizar;
    
    DefaultTableModel modeloTabla = new DefaultTableModel();
    VistaMovimientos vista = new VistaMovimientos();
    MovimientosDAO dao = new MovimientosDAO();

    public MovimientosController(VistaMovimientos vistaMovimientos) {
        this.vista = vistaMovimientos;
        this.vista.btn_listar_movimientos.addActionListener(this);
        this.vista.btn_limpiar_campos_movimiento.addActionListener(this);
        this.vista.btn_editar_movimientos.addActionListener(this);
        this.vista.btn_actualizar_movimientos.addActionListener(this);
        this.vista.btn_eliminar_movimientos.addActionListener(this);
        this.vista.btn_marcar_salida_movimientos.addActionListener(this);
        this.vista.btn_marcar_entrada_mov.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vista.btn_listar_movimientos){
            limpiarTabla();
            listar(vista.tb_datos_movimientos);
        }
        if(e.getSource() == vista.btn_limpiar_campos_movimiento){
            limpiarCajas();
        }
        if(e.getSource() == vista.btn_editar_movimientos){
            editar();
        }
        if(e.getSource() == vista.btn_actualizar_movimientos){
            actualizar();
            if(actualizar){
                limpiarTabla();
                limpiarCajas();
                listar(vista.tb_datos_movimientos);
            }
        }
        if(e.getSource() == vista.btn_eliminar_movimientos){
            eliminar();
            limpiarTabla();
            limpiarCajas();
            listar(vista.tb_datos_movimientos);
        }

        if(e.getSource() == vista.btn_marcar_salida_movimientos){
            marcarSalida();
            limpiarTabla();
            limpiarCajas();
            listar(vista.tb_datos_movimientos);
        }
        
        if(e.getSource() == vista.btn_marcar_entrada_mov){
            marcarEntrada();
            limpiarTabla();
            limpiarCajas();
            listar(vista.tb_datos_movimientos);
        }  
    }
    
    private void listar(JTable tabla){
        this.vista.btn_editar_movimientos.setEnabled(true);
        this.vista.btn_eliminar_movimientos.setEnabled(true);
        vista.btn_marcar_salida_movimientos.setEnabled(true);
        vista.btn_marcar_entrada_mov.setEnabled(true);
        this.modeloTabla = (DefaultTableModel)tabla.getModel();
        List<Movimientos> listarMovimientos = this.dao.listar();
        Object[] objeto = new Object[7];
        if(listarMovimientos.size()>0){
            for(int i=0;i<listarMovimientos.size();i++){
                objeto[0] = listarMovimientos.get(i).getIdMovimiento();
                objeto[1] = listarMovimientos.get(i).getNombreCliente();
                objeto[2] = listarMovimientos.get(i).getApellido_p();
                objeto[3] = listarMovimientos.get(i).getApellido_m();
                objeto[4] = listarMovimientos.get(i).getHabitacion();
                objeto[5] = listarMovimientos.get(i).getFecha_hora_ingreso();
                objeto[6] = listarMovimientos.get(i).getFecha_hora_salida();
                modeloTabla.addRow(objeto);
            }
            vista.tb_datos_movimientos.setModel(modeloTabla);
        }else{
            JOptionPane.showMessageDialog(vista, "No hay movimientos para listar");
        }
        
    }
    
    private void limpiarTabla (){
        for(int i=0;i<vista.tb_datos_movimientos.getRowCount();i++){
            modeloTabla.removeRow(i);
            i = i-1;
        }
    }
    
    private void limpiarCajas(){
        vista.txt_codigo_movimientos.setText("");
        vista.txt_nombre_movimientos.setText("");
        vista.txt_ape_p_movimientos.setText("");
        vista.txt_ape_m_movimientos.setText("");
        vista.txt_habitacion_movimientos.setText("");
        vista.txt_fecha_hora_i_movimientos.setText("");
        vista.txt_fecha_hora_s_movimientos.setText("");
        this.vista.btn_actualizar_movimientos.setEnabled(false);
    }
    
    private void editar(){
        int fila = vista.tb_datos_movimientos.getSelectedRow();
            if(fila == -1){
                JOptionPane.showMessageDialog(vista, "Debe seleccionar una fila");
            }else{
                this.vista.btn_marcar_salida_movimientos.setEnabled(true);
                this.vista.btn_marcar_entrada_mov.setEnabled(true);
                this.vista.btn_actualizar_movimientos.setEnabled(true);
                int codigo = Integer.parseInt((String)vista.tb_datos_movimientos.getValueAt(fila, 0).toString());
                String nombre = (String)vista.tb_datos_movimientos.getValueAt(fila, 1);
                String apellido_paterno = (String)vista.tb_datos_movimientos.getValueAt(fila, 2);
                String apellido_materno = (String)vista.tb_datos_movimientos.getValueAt(fila, 3);
                String habitacion = (String)vista.tb_datos_movimientos.getValueAt(fila, 4);
                Timestamp fecha_hora_ingreso = Timestamp.valueOf((String)vista.tb_datos_movimientos.getValueAt(fila, 5).toString());
                Timestamp fecha_hora_salida = Timestamp.valueOf((String)vista.tb_datos_movimientos.getValueAt(fila, 6).toString());
                
                vista.txt_codigo_movimientos.setText(codigo+"");
                vista.txt_nombre_movimientos.setText(nombre);
                vista.txt_ape_p_movimientos.setText(apellido_paterno);
                vista.txt_ape_m_movimientos.setText(apellido_materno);
                vista.txt_habitacion_movimientos.setText(habitacion);
                vista.txt_fecha_hora_i_movimientos.setText(fecha_hora_ingreso.toString());
                vista.txt_fecha_hora_s_movimientos.setText(fecha_hora_salida.toString());
            }
    }
    
    private void actualizar(){
         if (vista.txt_codigo_movimientos.getText().equals("") || vista.txt_nombre_movimientos.getText().equals("") || vista.txt_ape_p_movimientos.getText().equals("") || vista.txt_ape_m_movimientos.getText().equals("")
                || vista.txt_habitacion_movimientos.getText().equals("") || vista.txt_fecha_hora_i_movimientos.getText().equals("")){
            JOptionPane.showMessageDialog(vista, "Debe completar todos los campos");
            listar = false;
        }else{
            actualizar = true;
            vista.btn_actualizar_movimientos.setEnabled(false);
            vista.btn_marcar_salida_movimientos.setEnabled(true);
            int idMovimiento = Integer.parseInt(vista.txt_codigo_movimientos.getText());
            String nombre = vista.txt_nombre_movimientos.getText();
            String apellido_paterno = vista.txt_ape_p_movimientos.getText();
            String apellido_materno = vista.txt_ape_m_movimientos.getText();
            String habitacion = vista.txt_habitacion_movimientos.getText();
            Timestamp fecha_hora_i=Timestamp.valueOf(vista.txt_fecha_hora_i_movimientos.getText());
            Timestamp fecha_hora_s=Timestamp.valueOf(vista.txt_fecha_hora_s_movimientos.getText());
            
            Movimientos movimientos = new Movimientos(idMovimiento, nombre, apellido_paterno, apellido_materno, habitacion, fecha_hora_i, fecha_hora_s);
            int r = this.dao.actualizar(movimientos);
            if(r == 1){
                JOptionPane.showMessageDialog(vista, "Movimiento actualizado con éxito");
            }else{
                JOptionPane.showMessageDialog(vista, "Error al actualizar Movimiento");
            }
        }
    }
    
    private void marcarSalida(){
        int fila = vista.tb_datos_movimientos.getSelectedRow();
        if(fila == -1){
            JOptionPane.showMessageDialog(vista, "Debe seleccionar una fila");
        }else{
            int id = Integer.parseInt((String)vista.tb_datos_movimientos.getValueAt(fila, 0).toString());
            int iden = this.dao.actualizar_fecha_hora_salida(id);
            if (iden == 1){
                JOptionPane.showMessageDialog(vista, "Salida actualizada con éxito");
                vista.btn_marcar_salida_movimientos.setEnabled(false);
                vista.btn_actualizar_movimientos.setEnabled(false);
            }else{
                JOptionPane.showMessageDialog(vista, "Error al actualizar salida");
            }
        }
    }
    
    private void marcarEntrada(){
        int fila = vista.tb_datos_movimientos.getSelectedRow();
        if(fila == -1){
            JOptionPane.showMessageDialog(vista, "Debe seleccionar una fila");
        }else{
            int id = Integer.parseInt((String)vista.tb_datos_movimientos.getValueAt(fila, 0).toString());
            int iden = this.dao.actualizar_fecha_hora_entrada(id);
            if (iden == 1){
                JOptionPane.showMessageDialog(vista, "Entrada actualizada con éxito");
                vista.btn_marcar_salida_movimientos.setEnabled(false);
                vista.btn_actualizar_movimientos.setEnabled(false);
            }else{
                JOptionPane.showMessageDialog(vista, "Error al actualizar entrada");
            }
        }
    }
    
    private void eliminar(){
        int fila = vista.tb_datos_movimientos.getSelectedRow();
        if(fila == -1){
            JOptionPane.showMessageDialog(vista, "Debe seleccionar una fila");
        }else{
            int id = Integer.parseInt((String)vista.tb_datos_movimientos.getValueAt(fila, 0).toString());
            int iden = this.dao.eliminar(id);
            if (iden == 1){
                JOptionPane.showMessageDialog(vista, "Movimiento eliminado con éxito");
                vista.btn_marcar_salida_movimientos.setEnabled(false);
                vista.btn_actualizar_movimientos.setEnabled(false);
            }else{
                JOptionPane.showMessageDialog(vista, "Error al eliminar trabajador");
            }
        }
    }
}
