package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import models.Reserva;
import models.ReservaDAO;
import views.VistaReserva;
import java.util.Calendar;
import javax.swing.JFrame;
import models.Validaciones;
import views.VistaListaCliente;
import views.VistaListaHabitacion;
import views.VistaListaTrabajador;

public class ReservasController implements ActionListener{
    boolean listar;
    boolean actualizar;
    
    DefaultTableModel modeloTabla = new DefaultTableModel();
    VistaReserva vista = new VistaReserva();
    ReservaDAO dao = new ReservaDAO();
    Validaciones val = new Validaciones();
    Calendar cal = Calendar.getInstance();
    boolean var;
    int d,m,a;

    public ReservasController(VistaReserva vistaReserva) {
        this.vista = vistaReserva;
        this.vista.btn_listar_reserva.addActionListener(this);
        this.vista.btn_registrar_reserva.addActionListener(this);
        this.vista.btn_limpiar_campos_reserva.addActionListener(this);
        this.vista.btn_eliminar_reserva.addActionListener(this);
        this.vista.btn_nuevo_reserva.addActionListener(this);
        this.vista.btn_lista_cliente_reserva.addActionListener(this);
        this.vista.btn_lista_habitacion_reserva.addActionListener(this);
        this.vista.btn_lista_trabajador_reserva.addActionListener(this);
        this.vista.btn_buscar_reserva.addActionListener(this);
        this.vista.btn_editar_reserva.addActionListener(this);
        this.vista.btn_actualizar_reserva.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vista.btn_listar_reserva){
            limpiarTabla();
            listar(vista.tb_datos_reserva);
        }
        if(e.getSource() == vista.btn_registrar_reserva){
            registrar();
            if(listar){
                vista.btn_eliminar_reserva.setEnabled(true);
                limpiarCajas();
                limpiarTabla();
                listar(vista.tb_datos_reserva); 
            }
        }
        if(e.getSource() == vista.btn_limpiar_campos_reserva){
            vista.btn_eliminar_reserva.setEnabled(false);
            limpiarCajas();
        }
        if(e.getSource() == vista.btn_lista_cliente_reserva){
            mostrarListaCliente();
        }
        if(e.getSource() == vista.btn_lista_trabajador_reserva){
            mostrarListaTrabajdor();
        }
        if(e.getSource() == vista.btn_lista_habitacion_reserva){
            mostrarListaHabitacion();
        }
        
        if(e.getSource() == vista.btn_eliminar_reserva){
            eliminar();
            limpiarTabla();
            limpiarCajas();
            listar(vista.tb_datos_reserva);
        }        
        
        if(e.getSource() == vista.btn_nuevo_reserva){
            limpiarCajas();
            vista.btn_nuevo_reserva.setEnabled(false);
            vista.btn_registrar_reserva.setEnabled(true);
        }
        
        if(e.getSource() == vista.btn_buscar_reserva){
            limpiarTabla();
            if(validarBuscar()==true){
                listarBuscar(vista.tb_datos_reserva,vista.txt_buscar_reserva.getText());
                vista.btn_actualizar_reserva.setEnabled(false);
            } 
        }
        
        if(e.getSource() == vista.btn_editar_reserva){
            editar();
        }
        
        if(e.getSource() == vista.btn_actualizar_reserva){
            actualizar();
            if(actualizar){
                limpiarTabla();
                limpiarCajas();
                listar(vista.tb_datos_reserva);
            }
        }
    }
    private void registrar(){
        if (vista.txt_idCliente_reserva.getText().equals("") || vista.txt_idHabitacion_reserva.getText().equals("") || vista.txt_idTrabajador_reserva.getText().equals("")
                || vista.txt_tipo_reserva.getText().equals("") || vista.txt_costo_reserva.getText().equals("") || vista.txt_estado_reserva.getText().equals("") || 
                vista.jd_fecha_reserva.getCalendar()==null || vista.jd_fecha_ingreso_reserva.getCalendar()==null || vista.jd_fecha_salida_reserva.getCalendar()==null){
            JOptionPane.showMessageDialog(vista, "Debe completar todos los campos");
            listar = false;
        }
        else{
            if(validaridCliente()==true && validarTrabajdor()==true && validaridHabitacion()==true && validartipoReserva()==true
            && validarCosto()==true && validaEstado()==true){
                listar = true;
                int idCliente = Integer.parseInt(vista.txt_idCliente_reserva.getText());
                int idHabitacion = Integer.parseInt(vista.txt_idHabitacion_reserva.getText());
                int idTrabajdor = Integer.parseInt(vista.txt_idTrabajador_reserva.getText());
                String tipo_reserva = vista.txt_tipo_reserva.getText();

                Calendar fecha_reserva_c = vista.jd_fecha_reserva.getCalendar();
                d=fecha_reserva_c.get(Calendar.DAY_OF_MONTH);
                m=fecha_reserva_c.get(Calendar.MONTH);
                a=fecha_reserva_c.get(Calendar.YEAR) - 1900;
                Date fecha_reserva = (new Date(a,m,d)); 

                Calendar fecha_ingreso_c = vista.jd_fecha_ingreso_reserva.getCalendar();
                d=fecha_ingreso_c.get(Calendar.DAY_OF_MONTH);
                m=fecha_ingreso_c.get(Calendar.MONTH);
                a=fecha_ingreso_c.get(Calendar.YEAR) - 1900;
                Date fecha_ingreso = (new Date(a,m,d)); 

                Calendar fecha_salida_c = vista.jd_fecha_salida_reserva.getCalendar();
                d=fecha_salida_c.get(Calendar.DAY_OF_MONTH);
                m=fecha_salida_c.get(Calendar.MONTH);
                a=fecha_salida_c.get(Calendar.YEAR) - 1900;
                Date fecha_salida = (new Date(a,m,d)); 

                double costo = Double.parseDouble(vista.txt_costo_reserva.getText());
                int estado = Integer.parseInt(vista.txt_estado_reserva.getText());

                Reserva reserva = new Reserva(idCliente, idTrabajdor, idHabitacion, tipo_reserva, fecha_reserva, fecha_ingreso, fecha_salida, costo, estado);
                int listo = this.dao.agregar(reserva);
                if(listo == 1){
                    JOptionPane.showMessageDialog(vista, "Reserva registrada con éxito");
                }else{
                    JOptionPane.showMessageDialog(vista, "Debe completar todos los campos");
                }
            }
        }
    }
    
    private void listar(JTable tabla){
        this.vista.btn_eliminar_reserva.setEnabled(true);
        this.modeloTabla = (DefaultTableModel)tabla.getModel();
        List<Reserva> listarReserva = this.dao.listar();
        Object[] objeto = new Object[13];
        if(listarReserva.size()>0){
            for(int i=0;i<listarReserva.size();i++){
                objeto[0] = listarReserva.get(i).getIdReserva();
                objeto[1] = listarReserva.get(i).getIdCliente();
                objeto[2] = listarReserva.get(i).getNombres()+" "+listarReserva.get(i).getApellido_paterno()+" "+listarReserva.get(i).getApellido_materno();
                objeto[3] = listarReserva.get(i).getIdTrabajador();
                objeto[4] = listarReserva.get(i).getNombre()+" "+listarReserva.get(i).getApellido_paterno_trabajador()+" "+listarReserva.get(i).getApellido_materno_trabajdor();
                objeto[5] = listarReserva.get(i).getIdHabitacion();
                objeto[6] = listarReserva.get(i).getNumero();
                objeto[7] = listarReserva.get(i).getTipo_reserva();
                objeto[8] = listarReserva.get(i).getFecha_reserva();
                objeto[9] = listarReserva.get(i).getFecha_ingreso();
                objeto[10] = listarReserva.get(i).getFecha_salida();
                objeto[11] = listarReserva.get(i).getCosto_alojamiento();
                objeto[12] = listarReserva.get(i).getEstado_reserva();
                modeloTabla.addRow(objeto);
            }
            vista.tb_datos_reserva.setModel(modeloTabla);
        }else{
            JOptionPane.showMessageDialog(vista, "No hay reservas para listar");
        }      
    }
    
    private void listarBuscar(JTable tabla, String buscar){
        this.vista.btn_eliminar_reserva.setEnabled(true);
        this.modeloTabla = (DefaultTableModel)tabla.getModel();
        List<Reserva> listarReserva = this.dao.listarBuscar(buscar);
        Object[] objeto = new Object[13];
        if(listarReserva.size()>0){
            for(int i=0;i<listarReserva.size();i++){
                objeto[0] = listarReserva.get(i).getIdReserva();
                objeto[1] = listarReserva.get(i).getIdCliente();
                objeto[2] = listarReserva.get(i).getNombres()+" "+listarReserva.get(i).getApellido_paterno()+" "+listarReserva.get(i).getApellido_materno();
                objeto[3] = listarReserva.get(i).getIdTrabajador();
                objeto[4] = listarReserva.get(i).getNombre()+" "+listarReserva.get(i).getApellido_paterno_trabajador()+" "+listarReserva.get(i).getApellido_materno_trabajdor();
                objeto[5] = listarReserva.get(i).getIdHabitacion();
                objeto[6] = listarReserva.get(i).getNumero();
                objeto[7] = listarReserva.get(i).getTipo_reserva();
                objeto[8] = listarReserva.get(i).getFecha_reserva();
                objeto[9] = listarReserva.get(i).getFecha_ingreso();
                objeto[10] = listarReserva.get(i).getFecha_salida();
                objeto[11] = listarReserva.get(i).getCosto_alojamiento();
                objeto[12] = listarReserva.get(i).getEstado_reserva();
                modeloTabla.addRow(objeto);
            }
            vista.tb_datos_reserva.setModel(modeloTabla);
        }else{
            JOptionPane.showMessageDialog(vista, "No hay reservas para listar");
        }      
    }
    
    private void eliminar(){
        int fila = vista.tb_datos_reserva.getSelectedRow();
        if(fila == -1){
            JOptionPane.showMessageDialog(vista, "Debe seleccionar una fila");
        }else{
            int id = Integer.parseInt((String)vista.tb_datos_reserva.getValueAt(fila, 0).toString());
            int iden = this.dao.eliminar(id);
            if (iden == 1){
                JOptionPane.showMessageDialog(vista, "Reserva eliminado con éxito");
                vista.btn_registrar_reserva.setEnabled(true);
                vista.btn_nuevo_reserva.setEnabled(false);
            }else{
                JOptionPane.showMessageDialog(vista, "Error al eliminar reserva");
            }
        }
    }
    
    private void editar(){
        int fila = vista.tb_datos_reserva.getSelectedRow();
            if(fila == -1){
                JOptionPane.showMessageDialog(vista, "Debe seleccionar una fila");
            }else{
                this.vista.btn_registrar_reserva.setEnabled(false);
                this.vista.btn_actualizar_reserva.setEnabled(true);
                this.vista.btn_nuevo_reserva.setEnabled(true);
                
                int codigo = Integer.parseInt((String)vista.tb_datos_reserva.getValueAt(fila, 0).toString());
                int idCliente = Integer.parseInt((String)vista.tb_datos_reserva.getValueAt(fila, 1).toString());
                int idTrabajador = Integer.parseInt((String)vista.tb_datos_reserva.getValueAt(fila, 3).toString());
                int idHabitacion = Integer.parseInt((String)vista.tb_datos_reserva.getValueAt(fila, 5).toString());
                String tipo_reserva = (String)vista.tb_datos_reserva.getValueAt(fila, 7);                
                Double costo = Double.parseDouble((String)vista.tb_datos_reserva.getValueAt(fila, 11).toString());
                int estado = Integer.parseInt((String)vista.tb_datos_reserva.getValueAt(fila, 12).toString());

                
                vista.txt_codigo_reserva.setText(codigo+"");
                vista.txt_idCliente_reserva.setText(idCliente+"");
                vista.txt_idHabitacion_reserva.setText(idHabitacion+"");
                vista.txt_idTrabajador_reserva.setText(idTrabajador+"");
                vista.jd_fecha_reserva.setEnabled(false);
                vista.jd_fecha_ingreso_reserva.setEnabled(false);
                vista.jd_fecha_salida_reserva.setEnabled(false);
                vista.txt_tipo_reserva.setText(tipo_reserva);
                vista.txt_costo_reserva.setText(costo+"");
                vista.txt_estado_reserva.setText(estado+"");
            }
    }
    
    private void actualizar(){
         if (vista.txt_idCliente_reserva.getText().equals("") || vista.txt_idHabitacion_reserva.getText().equals("") || vista.txt_idTrabajador_reserva.getText().equals("")
                || vista.txt_tipo_reserva.getText().equals("") || vista.txt_costo_reserva.getText().equals("") || vista.txt_estado_reserva.getText().equals("")){
            JOptionPane.showMessageDialog(vista, "Debe completar todos los campos");
            listar = false;
        }else{
            if(validaridCliente()==true && validarTrabajdor()==true && validaridHabitacion()==true && validartipoReserva()==true
            && validarCosto()==true && validaEstado()==true){
                actualizar = true;
                vista.btn_registrar_reserva.setEnabled(true);
                vista.btn_actualizar_reserva.setEnabled(false);
                vista.btn_nuevo_reserva.setEnabled(false);
                
                listar = true;
                int codigo = Integer.parseInt(vista.txt_codigo_reserva.getText());
                int idCliente = Integer.parseInt(vista.txt_idCliente_reserva.getText());
                int idHabitacion = Integer.parseInt(vista.txt_idHabitacion_reserva.getText());
                int idTrabajdor = Integer.parseInt(vista.txt_idTrabajador_reserva.getText());
                String tipo_reserva = vista.txt_tipo_reserva.getText();
                double costo = Double.parseDouble(vista.txt_costo_reserva.getText());
                int estado = Integer.parseInt(vista.txt_estado_reserva.getText());

                Reserva res = new Reserva(codigo,idCliente, idTrabajdor, idHabitacion, tipo_reserva, costo, estado);
                int r = this.dao.actualizar(res);
                if(r == 1){
                    JOptionPane.showMessageDialog(vista, "Reserva actualizada con éxito");
                }else{
                    JOptionPane.showMessageDialog(vista, "Error al actualizar reserva");
                }
                vista.jd_fecha_reserva.setEnabled(true);
                vista.jd_fecha_ingreso_reserva.setEnabled(true);
                vista.jd_fecha_salida_reserva.setEnabled(true);
            }
        }
    }
    
    private void limpiarTabla (){
        for(int i=0;i<vista.tb_datos_reserva.getRowCount();i++){
            modeloTabla.removeRow(i);
            i = i-1;
        }
    }
    
    private void limpiarCajas(){
        vista.txt_codigo_reserva.setText("");
        vista.txt_idCliente_reserva.setText("");
        vista.txt_idHabitacion_reserva.setText("");
        vista.txt_idTrabajador_reserva.setText("");
        vista.txt_tipo_reserva.setText("");
        vista.jd_fecha_reserva.setCalendar(null);
        vista.jd_fecha_ingreso_reserva.setCalendar(null);
        vista.jd_fecha_salida_reserva.setCalendar(null);
        vista.txt_costo_reserva.setText("");
        vista.txt_estado_reserva.setText("");
        vista.jd_fecha_reserva.setEnabled(true);
        vista.jd_fecha_ingreso_reserva.setEnabled(true);
        vista.jd_fecha_salida_reserva.setEnabled(true);
    }
    
    private void mostrarListaCliente(){
        VistaListaCliente vista = new VistaListaCliente();
        vista.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        ClienteListaController controlador = new ClienteListaController(vista);
        vista.setVisible(true);
        vista.setLocationRelativeTo(vista);
    }
    
    private void mostrarListaTrabajdor(){
        VistaListaTrabajador vista = new VistaListaTrabajador();
        vista.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        TrabajadorListaController controlador = new TrabajadorListaController(vista);
        vista.setVisible(true);
        vista.setLocationRelativeTo(vista);
    }
    
    private void mostrarListaHabitacion(){
        VistaListaHabitacion vista = new VistaListaHabitacion();
        vista.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        HabitacionListaController controlador = new HabitacionListaController(vista);
        vista.setVisible(true);
        vista.setLocationRelativeTo(vista);
    }
    
    private boolean validaridCliente(){
        var = val.validacionNumeros(vista.txt_idCliente_reserva.getText());
        if(var == false){
            JOptionPane.showMessageDialog(vista, "Ingrese un codigo de cliente valido");
            vista.txt_idCliente_reserva.setText("");
            return false;
        }else{
            return true;
        }
    }
    
    private boolean validaridHabitacion(){
        var = val.validacionNumeros(vista.txt_idHabitacion_reserva.getText());
        if(var == false){
            JOptionPane.showMessageDialog(vista, "Ingrese un codigo de habitacion valido");
            vista.txt_idHabitacion_reserva.setText("");
            return false;
        }else{
            return true;
        }
    }
    
    private boolean validarTrabajdor(){
        var = val.validacionNumeros(vista.txt_idTrabajador_reserva.getText());
        if(var == false){
            JOptionPane.showMessageDialog(vista, "Ingrese un codigo de trabajador valido");
            vista.txt_idTrabajador_reserva.setText("");
            return false;
        }else{
            return true;
        }
    }
    
    private boolean validartipoReserva(){
        var = val.validacionLetras(vista.txt_tipo_reserva.getText());
        if(var == false){
            JOptionPane.showMessageDialog(vista, "Ingrese un tipo de reserva valido");
            vista.txt_idTrabajador_reserva.setText("");
            return false;
        }else{
            return true;
        }
    }
    
    private boolean validarCosto(){
        var = val.validacionNumerosDecimales(vista.txt_costo_reserva.getText());
        if(var == false){
            JOptionPane.showMessageDialog(vista, "Ingrese un costo valido");
            vista.txt_costo_reserva.setText("");
            return false;
        }else{
            return true;
        }
    }
    
    private boolean validaEstado(){
        var = val.validacionEstado(vista.txt_estado_reserva.getText());
        if(var == false){
            JOptionPane.showMessageDialog(vista, "Solo puede ingresar 0->(inactivo) o 1->(activo)");
            vista.txt_estado_reserva.setText("");
            return false;
        }else{
            return true;
        }
    }
    
    private boolean validarBuscar(){
        var = val.validacionLetras(vista.txt_buscar_reserva.getText());
        if(var == false){
            JOptionPane.showMessageDialog(vista, "Ingrese un nombre de cliente valido");
            vista.txt_buscar_reserva.setText("");
            return false;
        }else{
            return true;
        }
    }
}
