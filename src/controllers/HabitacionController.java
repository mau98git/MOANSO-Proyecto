package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import models.Habitacion;
import models.HabitacionDAO;
import models.Validaciones;
import views.VistaHabitaciones;

public class HabitacionController implements ActionListener{
    boolean listar;
    boolean actualizar;
    
    DefaultTableModel modeloTabla = new DefaultTableModel();
    VistaHabitaciones vista = new VistaHabitaciones();
    HabitacionDAO dao = new HabitacionDAO();
    Validaciones val =new Validaciones();
    boolean var;
    
    public HabitacionController(VistaHabitaciones vistaHabitaciones){
        this.vista=vistaHabitaciones;
        this.vista.btn_listar_hab.addActionListener(this);
        this.vista.btn_nuevo_hab.addActionListener(this);
        this.vista.btn_registrar_hab.addActionListener(this);
        this.vista.btn_actualizar_hab.addActionListener(this);
        this.vista.btn_buscar_hab.addActionListener(this);
        this.vista.btn_eliminar_hab.addActionListener(this);
        this.vista.btn_limpiar_campos_hab.addActionListener(this);
        this.vista.btn_editar_hab.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vista.btn_listar_hab){
            limpiarTabla();
            listar(vista.tb_datos_hab);
            vista.btn_actualizar_hab.setEnabled(false);
        }
        if(e.getSource() == vista.btn_registrar_hab){
            registrar();
            if(listar){
                vista.btn_editar_hab.setEnabled(true);
                vista.btn_eliminar_hab.setEnabled(true);
                limpiarCajas();
                limpiarTabla();
                listar(vista.tb_datos_hab); 
            }
        }
        if(e.getSource() == vista.btn_limpiar_campos_hab){
            limpiarCajas();
            vista.btn_actualizar_hab.setEnabled(false);
            vista.btn_editar_hab.setEnabled(false);
            vista.btn_eliminar_hab.setEnabled(false);
            limpiarTabla();
        }
        if(e.getSource() == vista.btn_editar_hab){
            editar();
        }
        if(e.getSource() == vista.btn_actualizar_hab){
            actualizar();
            if(actualizar){
                limpiarTabla();
                limpiarCajas();
                listar(vista.tb_datos_hab);
            }
        }
        if(e.getSource() == vista.btn_eliminar_hab){
            eliminar();
            limpiarTabla();
            limpiarCajas();
            listar(vista.tb_datos_hab);
        }        
        
        if(e.getSource() == vista.btn_nuevo_hab){
            limpiarCajas();
            vista.btn_nuevo_hab.setEnabled(false);
            vista.btn_registrar_hab.setEnabled(true);
            vista.btn_actualizar_hab.setEnabled(false);
        }
        
        if(e.getSource() == vista.btn_buscar_hab){
            limpiarTabla();
            if(validarBuscar()==true){
                listarBuscar(vista.tb_datos_hab,vista.txt_buscar_hab.getText());
                vista.btn_actualizar_hab.setEnabled(false);
            } 
        }
    }
    
    private void registrar(){
        if (vista.txt_numero_hab.getText().equals("") || vista.txt_piso_hab.getText().equals("") || vista.txt_precio_hab.getText().equals("")
            || vista.txt_tipo_hab.getText().equals("") || vista.txt_estado_hab.getText().equals("")){
            JOptionPane.showMessageDialog(vista, "Debe completar todos los campos");
            listar = false;
        }
        else{
            if(validarNumero()==true && validarPiso()==true && validarPrecio()==true && validarTipo()==true
                && validarEstado()==true){
                listar = true;
                String numero = vista.txt_numero_hab.getText();
                String piso = vista.txt_piso_hab.getText();
                double precio = Double.parseDouble(vista.txt_precio_hab.getText());
                String tipo_habitacion = vista.txt_tipo_hab.getText();
                String estado = vista.txt_estado_hab.getText();

                Habitacion hab =new Habitacion(numero, piso, precio, tipo_habitacion, estado);
                int listo = this.dao.agregar(hab);
                if(listo == 1){
                    JOptionPane.showMessageDialog(vista, "Habitacion registrado con éxito");
                }else{
                    JOptionPane.showMessageDialog(vista, "Error al registrar habitacion");
                } 
            }
        }
    }
    
    private void listarBuscar(JTable tabla,String buscar){
        this.vista.btn_editar_hab.setEnabled(true);
        this.vista.btn_eliminar_hab.setEnabled(true);
        this.modeloTabla = (DefaultTableModel)tabla.getModel();
        List<Habitacion> listarHabitacion = this.dao.listarBuscar(buscar);
        Object[] objeto = new Object[6];
        if(listarHabitacion.size()>0){
            for(int i=0;i<listarHabitacion.size();i++){
                objeto[0] = listarHabitacion.get(i).getIdHabitacion();
                objeto[1] = listarHabitacion.get(i).getNumero();
                objeto[2] = listarHabitacion.get(i).getPiso();
                objeto[3] = listarHabitacion.get(i).getPrecio();
                objeto[4] = listarHabitacion.get(i).getTipo_Habitacion();
                objeto[5] = listarHabitacion.get(i).getEstado();
                modeloTabla.addRow(objeto);
            }
            vista.tb_datos_hab.setModel(modeloTabla);
        }else{
            JOptionPane.showMessageDialog(vista, "No hay habitaciones para listar");
        }
    }
    
    private void listar(JTable tabla){
        this.vista.btn_editar_hab.setEnabled(true);
        this.vista.btn_eliminar_hab.setEnabled(true);
        this.modeloTabla = (DefaultTableModel)tabla.getModel();
        List<Habitacion> listarHabitacion = this.dao.listar();
        Object[] objeto = new Object[6];
        if(listarHabitacion.size()>0){
            for(int i=0;i<listarHabitacion.size();i++){
                objeto[0] = listarHabitacion.get(i).getIdHabitacion();
                objeto[1] = listarHabitacion.get(i).getNumero();
                objeto[2] = listarHabitacion.get(i).getPiso();
                objeto[3] = listarHabitacion.get(i).getPrecio();
                objeto[4] = listarHabitacion.get(i).getTipo_Habitacion();
                objeto[5] = listarHabitacion.get(i).getEstado();
                modeloTabla.addRow(objeto);
            }
            vista.tb_datos_hab.setModel(modeloTabla);
        }else{
            JOptionPane.showMessageDialog(vista, "No hay habitaciones para listar");
        }
    }
    
    private void eliminar(){
        int fila = vista.tb_datos_hab.getSelectedRow();
        if(fila == -1){
            JOptionPane.showMessageDialog(vista, "Debe seleccionar una fila");
        }else{
            int id = Integer.parseInt((String)vista.tb_datos_hab.getValueAt(fila, 0).toString());
            int iden = this.dao.eliminar(id);
            if (iden == 1){
                JOptionPane.showMessageDialog(vista, "Habitacion eliminada con éxito");
                vista.btn_registrar_hab.setEnabled(true);
                vista.btn_nuevo_hab.setEnabled(false);
                vista.btn_actualizar_hab.setEnabled(false);
            }else{
                JOptionPane.showMessageDialog(vista, "Error al eliminar habitacion");
            }
        }
    }
    
    private void editar(){
        int fila = vista.tb_datos_hab.getSelectedRow();
            if(fila == -1){
                JOptionPane.showMessageDialog(vista, "Debe seleccionar una fila");
            }else{
                this.vista.btn_registrar_hab.setEnabled(false);
                this.vista.btn_actualizar_hab.setEnabled(true);
                this.vista.btn_nuevo_hab.setEnabled(true);
                int codigo = Integer.parseInt((String)vista.tb_datos_hab.getValueAt(fila, 0).toString());
                String numero = (String)vista.tb_datos_hab.getValueAt(fila, 1);
                String piso = (String)vista.tb_datos_hab.getValueAt(fila, 2);
                Double precio = Double.parseDouble((String)vista.tb_datos_hab.getValueAt(fila, 3).toString());
                String tipo_habitacion = (String)vista.tb_datos_hab.getValueAt(fila, 4);
                String estado = (String)vista.tb_datos_hab.getValueAt(fila, 5);
                
                vista.txt_id_habitacion.setText(codigo+"");
                vista.txt_numero_hab.setText(numero);
                vista.txt_piso_hab.setText(piso);
                vista.txt_precio_hab.setText(precio+"");
                vista.txt_tipo_hab.setText(tipo_habitacion);
                vista.txt_estado_hab.setText(estado);
            }
    }
    
    private void actualizar(){
         if (vista.txt_numero_hab.getText().equals("") || vista.txt_piso_hab.getText().equals("") || vista.txt_precio_hab.getText().equals("")
            || vista.txt_tipo_hab.getText().equals("") || vista.txt_estado_hab.getText().equals("")){
            JOptionPane.showMessageDialog(vista, "Debe completar todos los campos");
            listar = false;
        }else{
            if(validarNumero()==true && validarPiso()==true && validarPrecio()==true && validarTipo()==true
                && validarEstado()==true){
                actualizar = true;
                vista.btn_registrar_hab.setEnabled(true);
                vista.btn_actualizar_hab.setEnabled(false);
                vista.btn_nuevo_hab.setEnabled(false);
                
                int codigo = Integer.parseInt(vista.txt_id_habitacion.getText());
                String numero = vista.txt_numero_hab.getText();
                String piso = vista.txt_piso_hab.getText();
                double precio = Double.parseDouble(vista.txt_precio_hab.getText());
                String tipo_habitacion = vista.txt_tipo_hab.getText();
                String estado = vista.txt_estado_hab.getText();

                Habitacion hab =new Habitacion(codigo,numero, piso, precio, estado, tipo_habitacion);
                int r = this.dao.actualizar(hab);
                if(r == 1){
                    JOptionPane.showMessageDialog(vista, "Habitacion actualizada con éxito");
                }else{
                    JOptionPane.showMessageDialog(vista, "Error al actualizar habitacion");
                }
            }
        }
    }

    private void limpiarTabla (){
        for(int i=0;i<vista.tb_datos_hab.getRowCount();i++){
            modeloTabla.removeRow(i);
            i = i-1;
        }
    }
    
    private void limpiarCajas(){
        vista.txt_id_habitacion.setText("");
        vista.txt_numero_hab.setText("");
        vista.txt_piso_hab.setText("");
        vista.txt_precio_hab.setText("");
        vista.txt_tipo_hab.setText("");
        vista.txt_estado_hab.setText("");
    }    
    
    private boolean validarNumero(){
        var = val.validacionHabitaciones(vista.txt_numero_hab.getText());
        if(var == false){
            JOptionPane.showMessageDialog(vista, "El numero de habitaciones es de 3 digitos");
            vista.txt_numero_hab.setText("");
            return false;
        }else{
            return true;
        }
    }
    
    private boolean validarPiso(){
       var = val.validacionPiso(vista.txt_piso_hab.getText());
        if(var == false){
            JOptionPane.showMessageDialog(vista, "El numero de piso es de un solo digito");
            vista.txt_piso_hab.setText("");
            return false;
        }else{
            return true;
        }
    }
    
    private boolean validarPrecio(){
        var = val.validacionNumerosDecimales(vista.txt_precio_hab.getText());
        if(var == false){
            JOptionPane.showMessageDialog(vista, "Ingrese un precio valido");
            vista.txt_precio_hab.setText("");
            return false;
        }else{
            return true;
        }
    }
    
    private boolean validarTipo(){
       var = val.validacionLetras(vista.txt_tipo_hab.getText());
        if(var == false){
            JOptionPane.showMessageDialog(vista, "Solo puede ingresar letras");
            vista.txt_tipo_hab.setText("");
            return false;
        }else{
            return true;
        }
    }
    
    private boolean validarEstado(){
       var = val.validacionLetras(vista.txt_estado_hab.getText());
        if(var == false){
            JOptionPane.showMessageDialog(vista, "Solo puede ingresar letras");
            vista.txt_estado_hab.setText("");
            return false;
        }else{
            return true;
        }
    }
    
    private boolean validarBuscar(){
        var = val.validacionHabitaciones(vista.txt_buscar_hab.getText());
        if(var == false){
            JOptionPane.showMessageDialog(vista, "Ingrese un numero de habitacion valido");
            vista.txt_buscar_hab.setText("");
            return false;
        }else{
            return true;
        }
    }
}
