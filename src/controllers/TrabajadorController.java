package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import models.Trabajador;
import models.TrabajadorDAO;
import models.Validaciones;
import views.VistaTrabajadores;

/**
 *
 * @author Diego
 */
public class TrabajadorController implements ActionListener{
    boolean listar;
    boolean actualizar;
    
    DefaultTableModel modeloTabla = new DefaultTableModel();
    VistaTrabajadores vista = new VistaTrabajadores();
    TrabajadorDAO dao = new TrabajadorDAO();
    Validaciones val =new Validaciones();
    boolean var;

    public TrabajadorController(VistaTrabajadores vistaTrabajadores) {
        this.vista = vistaTrabajadores;
        this.vista.btn_listar_trabajador.addActionListener(this);
        this.vista.btn_registrar_trabajador.addActionListener(this);
        this.vista.btn_limpiar_campos_trabajador.addActionListener(this);
        this.vista.btn_editar_trabajador.addActionListener(this);
        this.vista.btn_actualizar_trabajador.addActionListener(this);
        this.vista.btn_eliminar_trabajador.addActionListener(this);
        this.vista.btn_nuevo_trabajador.addActionListener(this);
        this.vista.btn_buscar_trabajador.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vista.btn_listar_trabajador){
            limpiarTabla();
            listar(vista.tb_datos_trabajador);
            vista.btn_actualizar_trabajador.setEnabled(false);
        }
        if(e.getSource() == vista.btn_registrar_trabajador){
            registrar();
            if(listar){
                vista.btn_editar_trabajador.setEnabled(true);
                vista.btn_eliminar_trabajador.setEnabled(true);
                limpiarCajas();
                limpiarTabla();
                listar(vista.tb_datos_trabajador); 
            }
        }
        if(e.getSource() == vista.btn_limpiar_campos_trabajador){
            vista.btn_actualizar_trabajador.setEnabled(false);
            vista.btn_editar_trabajador.setEnabled(false);
            vista.btn_eliminar_trabajador.setEnabled(false);
            limpiarCajas();
        }
        if(e.getSource() == vista.btn_editar_trabajador){
            editar();
        }
        if(e.getSource() == vista.btn_actualizar_trabajador){
            actualizar();
            if(actualizar){
                limpiarTabla();
                limpiarCajas();
                listar(vista.tb_datos_trabajador);
            }
        }
        if(e.getSource() == vista.btn_eliminar_trabajador){
            eliminar();
            limpiarTabla();
            limpiarCajas();
            listar(vista.tb_datos_trabajador);
        }        
        
        if(e.getSource() == vista.btn_nuevo_trabajador){
            limpiarCajas();
            vista.btn_nuevo_trabajador.setEnabled(false);
            vista.btn_registrar_trabajador.setEnabled(true);
            vista.btn_actualizar_trabajador.setEnabled(false);
        }
        
        if(e.getSource() == vista.btn_buscar_trabajador){
            limpiarTabla();
            if(validarBuscar()==true){
                listarBuscar(vista.tb_datos_trabajador,vista.txt_buscar_trabajador.getText());
                vista.btn_actualizar_trabajador.setEnabled(false);
            } 
        }

    }
    private void registrar(){
        if (vista.txt_nombre_trabajador.getText().equals("") || vista.txt_ape_paterno_trabajador.getText().equals("") || vista.txt_ape_materno_trabajador.getText().equals("")
                || vista.txt_tipo_documento_trabajador.getText().equals("") || vista.txt_num_documento_trabajador.getText().equals("") || vista.txt_celular_trabajador.getText().equals("") ||
                vista.txt_sueldo_trabajador.getText().equals("") || vista.txt_acceso_trabajador.getText().equals("") || vista.txt_login_trabajador.getText().equals("") || 
                vista.txt_password_trabajador.getText().equals("") || vista.txt_estado_trabajador.getText().equals("")){
            JOptionPane.showMessageDialog(vista, "Debe completar todos los campos");
            listar = false;
        }
        else{
            if(validarNombre()==true && validarApePaterno()==true && validarApeMaterno()==true && validarTipo_documento()==true
            && validarNumero_documento()==true && validarCelular()==true && validarEmail()==true && validarSueldo()==true && validarAcceso()==true &&
            validarEstado()==true){
                listar = true;
                String nombres = vista.txt_nombre_trabajador.getText();
                String apellido_paterno = vista.txt_ape_paterno_trabajador.getText();
                String apellido_materno = vista.txt_ape_materno_trabajador.getText();
                String tipo_documento = vista.txt_tipo_documento_trabajador.getText();
                String num_documento = vista.txt_num_documento_trabajador.getText();
                String celular = vista.txt_celular_trabajador.getText();
                String email = vista.txt_email_trabajador.getText();
                double sueldo = Double.parseDouble(vista.txt_sueldo_trabajador.getText());
                String acceso = vista.txt_acceso_trabajador.getText();
                String login = vista.txt_login_trabajador.getText();
                String password = vista.txt_password_trabajador.getText();
                int estado = Integer.parseInt(vista.txt_estado_trabajador.getText());

                Trabajador trabajador = new Trabajador(nombres, apellido_paterno, apellido_materno, tipo_documento, num_documento, celular, email, sueldo, acceso, login, password, estado);
                int listo = this.dao.agregar(trabajador);
                if(listo == 1){
                    JOptionPane.showMessageDialog(vista, "Trabajador registrado con éxito");
                }else{
                    JOptionPane.showMessageDialog(vista, "Debe completar todos los campos");
                }
            }
        }
    }
    
    private void listar(JTable tabla){
        this.vista.btn_editar_trabajador.setEnabled(true);
        this.vista.btn_eliminar_trabajador.setEnabled(true);
        this.modeloTabla = (DefaultTableModel)tabla.getModel();
        List<Trabajador> listarTrabajadores = this.dao.listar();
        Object[] objeto = new Object[13];
        if(listarTrabajadores.size()>0){
            for(int i=0;i<listarTrabajadores.size();i++){
                objeto[0] = listarTrabajadores.get(i).getId_trabajador();
                objeto[1] = listarTrabajadores.get(i).getNombre();
                objeto[2] = listarTrabajadores.get(i).getApe_paterno();
                objeto[3] = listarTrabajadores.get(i).getApe_materno();
                objeto[4] = listarTrabajadores.get(i).getTipo_documento();
                objeto[5] = listarTrabajadores.get(i).getNum_documento();
                objeto[6] = listarTrabajadores.get(i).getCelular();
                objeto[7] = listarTrabajadores.get(i).getEmail();
                objeto[8] = listarTrabajadores.get(i).getSueldo();
                objeto[9] = listarTrabajadores.get(i).getAcceso();
                objeto[10] = listarTrabajadores.get(i).getLogin();
                objeto[11] = listarTrabajadores.get(i).getPassword();
                objeto[12] = listarTrabajadores.get(i).getEstado();
                modeloTabla.addRow(objeto);
            }
            vista.tb_datos_trabajador.setModel(modeloTabla);
        }else{
            JOptionPane.showMessageDialog(vista, "No hay trabajdores para listar");
        }
        
    }
    
    private void limpiarTabla (){
        for(int i=0;i<vista.tb_datos_trabajador.getRowCount();i++){
            modeloTabla.removeRow(i);
            i = i-1;
        }
    }
    
    private void limpiarCajas(){
        vista.txt_codigo_trabajador.setText("");
        vista.txt_nombre_trabajador.setText("");
        vista.txt_ape_paterno_trabajador.setText("");
        vista.txt_ape_materno_trabajador.setText("");
        vista.txt_tipo_documento_trabajador.setText("");
        vista.txt_num_documento_trabajador.setText("");
        vista.txt_celular_trabajador.setText("");
        vista.txt_email_trabajador.setText("");
        vista.txt_sueldo_trabajador.setText("");
        vista.txt_acceso_trabajador.setText("");
        vista.txt_login_trabajador.setText("");
        vista.txt_password_trabajador.setText("");
        vista.txt_estado_trabajador.setText("");
    }
    
    private void editar(){
        int fila = vista.tb_datos_trabajador.getSelectedRow();
            if(fila == -1){
                JOptionPane.showMessageDialog(vista, "Debe seleccionar una fila");
            }else{
                this.vista.btn_registrar_trabajador.setEnabled(false);
                this.vista.btn_actualizar_trabajador.setEnabled(true);
                this.vista.btn_nuevo_trabajador.setEnabled(true);
                int codigo = Integer.parseInt((String)vista.tb_datos_trabajador.getValueAt(fila, 0).toString());
                String nombres = (String)vista.tb_datos_trabajador.getValueAt(fila, 1);
                String apellido_paterno = (String)vista.tb_datos_trabajador.getValueAt(fila, 2);
                String apellido_materno = (String)vista.tb_datos_trabajador.getValueAt(fila, 3);
                String tipo_Documento = (String)vista.tb_datos_trabajador.getValueAt(fila, 4);
                String num_documento = (String)vista.tb_datos_trabajador.getValueAt(fila, 5);
                String celular = (String)vista.tb_datos_trabajador.getValueAt(fila, 6);
                String email = (String)vista.tb_datos_trabajador.getValueAt(fila, 7);
                double sueldo = Double.parseDouble((String)vista.tb_datos_trabajador.getValueAt(fila, 8).toString());
                String acceso = (String)vista.tb_datos_trabajador.getValueAt(fila, 9);
                String login = (String)vista.tb_datos_trabajador.getValueAt(fila, 10);
                String password = (String)vista.tb_datos_trabajador.getValueAt(fila, 11);
                int estado = Integer.parseInt((String)vista.tb_datos_trabajador.getValueAt(fila, 12).toString());
                
                vista.txt_codigo_trabajador.setText(codigo+"");
                vista.txt_nombre_trabajador.setText(nombres);
                vista.txt_ape_paterno_trabajador.setText(apellido_paterno);
                vista.txt_ape_materno_trabajador.setText(apellido_materno);
                vista.txt_tipo_documento_trabajador.setText(tipo_Documento);
                vista.txt_num_documento_trabajador.setText(num_documento);
                vista.txt_celular_trabajador.setText(celular);
                vista.txt_email_trabajador.setText(email);
                vista.txt_sueldo_trabajador.setText(sueldo+"");
                vista.txt_acceso_trabajador.setText(acceso);
                vista.txt_login_trabajador.setText(login);
                vista.txt_password_trabajador.setText(password);
                vista.txt_estado_trabajador.setText(estado+"");
            }
    }
    
    private void actualizar(){
        if(vista.txt_nombre_trabajador.getText().equals("") || vista.txt_ape_paterno_trabajador.getText().equals("") || vista.txt_ape_materno_trabajador.getText().equals("")
        || vista.txt_tipo_documento_trabajador.getText().equals("") || vista.txt_num_documento_trabajador.getText().equals("") || vista.txt_celular_trabajador.getText().equals("") ||
        vista.txt_sueldo_trabajador.getText().equals("") || vista.txt_acceso_trabajador.getText().equals("") || vista.txt_login_trabajador.getText().equals("") || 
        vista.txt_password_trabajador.getText().equals("") || vista.txt_estado_trabajador.getText().equals("")){
            JOptionPane.showMessageDialog(vista, "Debe completar todos los campos");
            listar = false;
        }else{
            if(validarNombre()==true && validarApePaterno()==true && validarApeMaterno()==true && validarTipo_documento()==true
            && validarNumero_documento()==true && validarCelular()==true && validarEmail()==true && validarSueldo()==true && validarAcceso()==true &&
            validarEstado()==true){
                actualizar = true;
                vista.btn_registrar_trabajador.setEnabled(true);
                vista.btn_actualizar_trabajador.setEnabled(false);
                vista.btn_nuevo_trabajador.setEnabled(false);
                int idTrabajador = Integer.parseInt(vista.txt_codigo_trabajador.getText());
                String nombres = vista.txt_nombre_trabajador.getText();
                String apellido_paterno = vista.txt_ape_paterno_trabajador.getText();
                String apellido_materno = vista.txt_ape_materno_trabajador.getText();
                String tipo_documento = vista.txt_tipo_documento_trabajador.getText();
                String num_documento = vista.txt_num_documento_trabajador.getText();
                String celular = vista.txt_celular_trabajador.getText();
                String email = vista.txt_email_trabajador.getText();
                double sueldo = Double.parseDouble(vista.txt_sueldo_trabajador.getText());
                String acceso = vista.txt_acceso_trabajador.getText();
                String login = vista.txt_login_trabajador.getText();
                String password = vista.txt_password_trabajador.getText();
                int estado = Integer.parseInt(vista.txt_estado_trabajador.getText());

                Trabajador trabajador = new Trabajador(idTrabajador, nombres, apellido_paterno, apellido_materno, tipo_documento, num_documento, celular, email, sueldo, acceso, login, password, estado);
                int r = this.dao.actualizar(trabajador);
                if(r == 1){
                    JOptionPane.showMessageDialog(vista, "Trabajador actualizado con éxito");
                }else{
                    JOptionPane.showMessageDialog(vista, "Error al actualizar trabajador");
                }
            }
        }
    }
    
    private void eliminar(){
        int fila = vista.tb_datos_trabajador.getSelectedRow();
        if(fila == -1){
            JOptionPane.showMessageDialog(vista, "Debe seleccionar una fila");
        }else{
            int id = Integer.parseInt((String)vista.tb_datos_trabajador.getValueAt(fila, 0).toString());
            int iden = this.dao.eliminar(id);
            if (iden == 1){
                JOptionPane.showMessageDialog(vista, "Trabajador eliminado con éxito");
                vista.btn_registrar_trabajador.setEnabled(true);
                vista.btn_nuevo_trabajador.setEnabled(false);
                vista.btn_actualizar_trabajador.setEnabled(false);
            }else{
                JOptionPane.showMessageDialog(vista, "Error al eliminar trabajador");
            }
        }
    }
    
    private void listarBuscar(JTable tabla, String buscar){
        this.vista.btn_editar_trabajador.setEnabled(true);
        this.vista.btn_eliminar_trabajador.setEnabled(true);
        this.modeloTabla = (DefaultTableModel)tabla.getModel();
        List<Trabajador> listarTrabajadores = this.dao.listarBuscar(buscar);
        Object[] objeto = new Object[13];
        if(listarTrabajadores.size()>0){
            for(int i=0;i<listarTrabajadores.size();i++){
                objeto[0] = listarTrabajadores.get(i).getId_trabajador();
                objeto[1] = listarTrabajadores.get(i).getNombre();
                objeto[2] = listarTrabajadores.get(i).getApe_paterno();
                objeto[3] = listarTrabajadores.get(i).getApe_materno();
                objeto[4] = listarTrabajadores.get(i).getTipo_documento();
                objeto[5] = listarTrabajadores.get(i).getNum_documento();
                objeto[6] = listarTrabajadores.get(i).getCelular();
                objeto[7] = listarTrabajadores.get(i).getEmail();
                objeto[8] = listarTrabajadores.get(i).getSueldo();
                objeto[9] = listarTrabajadores.get(i).getAcceso();
                objeto[10] = listarTrabajadores.get(i).getLogin();
                objeto[11] = listarTrabajadores.get(i).getPassword();
                objeto[12] = listarTrabajadores.get(i).getEstado();
                modeloTabla.addRow(objeto);
            }
            vista.tb_datos_trabajador.setModel(modeloTabla);
        }else{
            JOptionPane.showMessageDialog(vista, "No hay trabajdores para listar");
        }
        
    }
    
    private boolean validarNombre(){
        boolean nombres = val.validacionNomApe(vista.txt_nombre_trabajador.getText());
        if(nombres == false){
            JOptionPane.showMessageDialog(vista, "Ingrese un nombre valido");
            vista.txt_nombre_trabajador.setText("");
            return false;
        }else{
            return true;
        }
    }
    
    private boolean validarApePaterno(){
        var = val.validacionNomApe(vista.txt_ape_paterno_trabajador.getText());
        if(var == false){
            JOptionPane.showMessageDialog(vista, "Ingrese un apellido valido");
            vista.txt_ape_paterno_trabajador.setText("");
            return false;
        }else{
            return true;
        }
    }
    
    private boolean validarApeMaterno(){
        var = val.validacionNomApe(vista.txt_ape_materno_trabajador.getText());
        if(var == false){
            JOptionPane.showMessageDialog(vista, "Ingrese un apellido valido");
            vista.txt_ape_materno_trabajador.setText("");
            return false;
        }else{
            return true;
        }
    }
    
    private boolean validarTipo_documento(){
        var = val.validacionTipoDocumento(vista.txt_tipo_documento_trabajador.getText());
        if(var == false){
            JOptionPane.showMessageDialog(vista, "Solo puede ingresar DNI o Pasaporte");
            vista.txt_tipo_documento_trabajador.setText("");
            return false;
        }else{
            return true;
        }
    }
    
    private boolean validarNumero_documento(){
        var = val.validacionNumeros(vista.txt_num_documento_trabajador.getText());
        if(var == false){
            JOptionPane.showMessageDialog(vista, "Ingrese un numero de documento valido");
            vista.txt_num_documento_trabajador.setText("");
            return false;
        }else{
            return true;
        }
    }
    
    private boolean validarCelular(){
        var = val.validacionNumeros(vista.txt_celular_trabajador.getText());
        if(var == false){
            JOptionPane.showMessageDialog(vista, "Ingrese un numero de celular valido");
            vista.txt_celular_trabajador.setText("");
            return false;
        }else{
            return true;
        }
    }
    
    private boolean validarEmail(){
        var = val.validacionEmail(vista.txt_email_trabajador.getText());
        if(var == false){
            JOptionPane.showMessageDialog(vista, "Ingrese un email valido");
            vista.txt_email_trabajador.setText("");
            return false;
        }else{
            return true;
        }
    }
    
    private boolean validarSueldo(){
        var = val.validacionNumeros(vista.txt_sueldo_trabajador.getText());
        if(var == false){
            JOptionPane.showMessageDialog(vista, "Ingrese un sueldo valido");
            vista.txt_sueldo_trabajador.setText("");
            return false;
        }else{
            return true;
        }
    }
    
    private boolean validarAcceso(){
        var = val.validacionAcceso(vista.txt_acceso_trabajador.getText());
        if(var == false){
            JOptionPane.showMessageDialog(vista, "Solo puede ingresar Administrador o Recepcion");
            vista.txt_acceso_trabajador.setText("");
            return false;
        }else{
            return true;
        }
    }
    
    private boolean validarEstado(){
        var = val.validacionEstado(vista.txt_estado_trabajador.getText());
        if(var == false){
            JOptionPane.showMessageDialog(vista, "Solo puede ingresar 0->(inactivo) o 1->(activo)");
            vista.txt_estado_trabajador.setText("");
            return false;
        }else{
            return true;
        }
    }
    
    private boolean validarBuscar(){
        var = val.validacionLetras(vista.txt_buscar_trabajador.getText());
        if(var == false){
            JOptionPane.showMessageDialog(vista, "Ingrese un nombre de trabajador valido");
            vista.txt_buscar_trabajador.setText("");
            return false;
        }else{
            return true;
        }
    }
}
