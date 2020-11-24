package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import models.Cliente;
import models.ClienteDAO;
import models.Validaciones;
import views.VistaClientes;

public class ClienteController implements ActionListener{
    boolean listar;
    boolean actualizar;
    
    DefaultTableModel modeloTabla = new DefaultTableModel();
    VistaClientes vista = new VistaClientes();
    ClienteDAO dao = new ClienteDAO();
    Validaciones val =new Validaciones();
    boolean var;

    public ClienteController(VistaClientes vistaClientes) {
        this.vista = vistaClientes;
        this.vista.btn_listar_cliente.addActionListener(this);
        this.vista.btn_registrar_cliente.addActionListener(this);
        this.vista.btn_limpiarCampos_cliente.addActionListener(this);
        this.vista.btn_editar_cliente.addActionListener(this);
        this.vista.btn_actualizar_cliente.addActionListener(this);
        this.vista.btn_eliminar_cliente.addActionListener(this);
        this.vista.btn_nuevo_cliente.addActionListener(this);
        this.vista.btn_buscar_cliente.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vista.btn_listar_cliente){
            limpiarTabla();
            listar(vista.tb_datos_cliente);
            vista.btn_actualizar_cliente.setEnabled(false);
        }
        if(e.getSource() == vista.btn_registrar_cliente){
            registrar();
            if(listar){
                vista.btn_editar_cliente.setEnabled(true);
                vista.btn_eliminar_cliente.setEnabled(true);
                limpiarCajas();
                limpiarTabla();
                listar(vista.tb_datos_cliente); 
            }
        }
        if(e.getSource() == vista.btn_limpiarCampos_cliente){
            limpiarCajas();
            vista.btn_actualizar_cliente.setEnabled(false);
            vista.btn_editar_cliente.setEnabled(false);
            vista.btn_eliminar_cliente.setEnabled(false);
            limpiarTabla();
        }
        if(e.getSource() == vista.btn_editar_cliente){
            editar();
        }
        if(e.getSource() == vista.btn_actualizar_cliente){
            actualizar();
            if(actualizar){
                limpiarTabla();
                limpiarCajas();
                listar(vista.tb_datos_cliente);
            }
        }
        if(e.getSource() == vista.btn_eliminar_cliente){
            eliminar();
            limpiarTabla();
            limpiarCajas();
            listar(vista.tb_datos_cliente);
        }        
        
        if(e.getSource() == vista.btn_nuevo_cliente){
            limpiarCajas();
            vista.btn_nuevo_cliente.setEnabled(false);
            vista.btn_registrar_cliente.setEnabled(true);
            vista.btn_actualizar_cliente.setEnabled(false);
        }
        
        if(e.getSource() == vista.btn_buscar_cliente){
            limpiarTabla();
            if(validarBuscar()==true){
                listarBuscar(vista.tb_datos_cliente,vista.txt_buscar_cliente.getText());
                vista.btn_actualizar_cliente.setEnabled(false);
            }    
        }
    }
    private void registrar(){
        if (vista.txt_nombre_cliente.getText().equals("") || vista.txt_apellido_p_cliente.getText().equals("") || vista.txt_apellido_m_cliente.getText().equals("")
                || vista.txt_tipo_doc_cliente.getText().equals("") || vista.txt_num_doc_cliente.getText().equals("") || vista.txt_cel_cliente.getText().equals("")){
            JOptionPane.showMessageDialog(vista, "Debe completar todos los campos");
            listar = false;
        }
        else{
            if(validarNombre()==true && validarApePaterno()==true && validarApeMaterno()==true && validarTipo_documento()==true
                && validarNumero_documento()==true && validarCelular()==true && validarEmail()==true){
                listar = true;
                String nombres = vista.txt_nombre_cliente.getText();
                String apellido_paterno = vista.txt_apellido_p_cliente.getText();
                String apellido_materno = vista.txt_apellido_m_cliente.getText();
                String tipo_documento = vista.txt_tipo_doc_cliente.getText();
                String num_documento = vista.txt_num_doc_cliente.getText();
                String celular = vista.txt_cel_cliente.getText();
                String email = vista.txt_email_cliente.getText();

                Cliente cliente = new Cliente(nombres, apellido_paterno, apellido_materno, tipo_documento, num_documento, celular, email);
                int listo = this.dao.agregar(cliente);
                if(listo == 1){
                    JOptionPane.showMessageDialog(vista, "Cliente registrado con éxito");
                }else{
                    JOptionPane.showMessageDialog(vista, "Error al registrar cliente");
                } 
            }
        }
    }
    
    private void listarBuscar(JTable tabla,String buscar){
        this.vista.btn_editar_cliente.setEnabled(true);
        this.vista.btn_eliminar_cliente.setEnabled(true);
        this.modeloTabla = (DefaultTableModel)tabla.getModel();
        List<Cliente> listarClientes = this.dao.listarBuscar(buscar);
        Object[] objeto = new Object[9];
        if(listarClientes.size()>0){
            for(int i=0;i<listarClientes.size();i++){
                objeto[0] = listarClientes.get(i).getIdCliente();
                objeto[1] = listarClientes.get(i).getNombres();
                objeto[2] = listarClientes.get(i).getApellido_paterno();
                objeto[3] = listarClientes.get(i).getApellido_materno();
                objeto[4] = listarClientes.get(i).getTipo_documento();
                objeto[5] = listarClientes.get(i).getNum_documento();
                objeto[6] = listarClientes.get(i).getCelular();
                objeto[7] = listarClientes.get(i).getEmail();
                modeloTabla.addRow(objeto);
            }
            vista.tb_datos_cliente.setModel(modeloTabla);
        }else{
            JOptionPane.showMessageDialog(vista, "No hay clientes para listar");
        }
    }
    
    private void listar(JTable tabla){
        this.vista.btn_editar_cliente.setEnabled(true);
        this.vista.btn_eliminar_cliente.setEnabled(true);
        this.modeloTabla = (DefaultTableModel)tabla.getModel();
        List<Cliente> listarClientes = this.dao.listar();
        Object[] objeto = new Object[9];
        if(listarClientes.size()>0){
            for(int i=0;i<listarClientes.size();i++){
                objeto[0] = listarClientes.get(i).getIdCliente();
                objeto[1] = listarClientes.get(i).getNombres();
                objeto[2] = listarClientes.get(i).getApellido_paterno();
                objeto[3] = listarClientes.get(i).getApellido_materno();
                objeto[4] = listarClientes.get(i).getTipo_documento();
                objeto[5] = listarClientes.get(i).getNum_documento();
                objeto[6] = listarClientes.get(i).getCelular();
                objeto[7] = listarClientes.get(i).getEmail();
                modeloTabla.addRow(objeto);
            }
            vista.tb_datos_cliente.setModel(modeloTabla);
        }else{
            JOptionPane.showMessageDialog(vista, "No hay clientes para listar");
        }
    }
    
    private void limpiarTabla (){
        for(int i=0;i<vista.tb_datos_cliente.getRowCount();i++){
            modeloTabla.removeRow(i);
            i = i-1;
        }
    }
    
    private void limpiarCajas(){
        vista.txt_idCliente.setText("");
        vista.txt_nombre_cliente.setText("");
        vista.txt_apellido_p_cliente.setText("");
        vista.txt_apellido_m_cliente.setText("");
        vista.txt_tipo_doc_cliente.setText("");
        vista.txt_num_doc_cliente.setText("");
        vista.txt_cel_cliente.setText("");
        vista.txt_email_cliente.setText("");
    }
   
    
    private void editar(){
        int fila = vista.tb_datos_cliente.getSelectedRow();
            if(fila == -1){
                JOptionPane.showMessageDialog(vista, "Debe seleccionar una fila");
            }else{
                this.vista.btn_registrar_cliente.setEnabled(false);
                this.vista.btn_actualizar_cliente.setEnabled(true);
                this.vista.btn_nuevo_cliente.setEnabled(true);
                int codigo = Integer.parseInt((String)vista.tb_datos_cliente.getValueAt(fila, 0).toString());
                String nombres = (String)vista.tb_datos_cliente.getValueAt(fila, 1);
                String apellido_paterno = (String)vista.tb_datos_cliente.getValueAt(fila, 2);
                String apellido_materno = (String)vista.tb_datos_cliente.getValueAt(fila, 3);
                String tipo_Documento = (String)vista.tb_datos_cliente.getValueAt(fila, 4);
                String num_documento = (String)vista.tb_datos_cliente.getValueAt(fila, 5);
                String celular = (String)vista.tb_datos_cliente.getValueAt(fila, 6);
                String email = (String)vista.tb_datos_cliente.getValueAt(fila, 7);
                
                vista.txt_idCliente.setText(codigo+"");
                vista.txt_nombre_cliente.setText(nombres);
                vista.txt_apellido_p_cliente.setText(apellido_paterno);
                vista.txt_apellido_m_cliente.setText(apellido_materno);
                vista.txt_tipo_doc_cliente.setText(tipo_Documento);
                vista.txt_num_doc_cliente.setText(num_documento);
                vista.txt_cel_cliente.setText(celular);
                vista.txt_email_cliente.setText(email);
            }
    }
    
    private void actualizar(){
         if (vista.txt_nombre_cliente.getText().equals("") || vista.txt_apellido_p_cliente.getText().equals("") || vista.txt_apellido_m_cliente.getText().equals("")
                || vista.txt_tipo_doc_cliente.getText().equals("") || vista.txt_num_doc_cliente.getText().equals("") || vista.txt_cel_cliente.getText().equals("")){
            JOptionPane.showMessageDialog(vista, "Debe completar todos los campos");
            listar = false;
        }else{
            if(validarNombre()==true && validarApePaterno()==true && validarApeMaterno()==true && validarTipo_documento()==true
            && validarNumero_documento()==true && validarCelular()==true && validarEmail()==true){
                actualizar = true;
                vista.btn_registrar_cliente.setEnabled(true);
                vista.btn_actualizar_cliente.setEnabled(false);
                vista.btn_nuevo_cliente.setEnabled(false);
                int idCliente = Integer.parseInt(vista.txt_idCliente.getText());
                String nombres = vista.txt_nombre_cliente.getText();
                String apellido_paterno = vista.txt_apellido_p_cliente.getText();
                String apellido_materno = vista.txt_apellido_m_cliente.getText();
                String tipo_documento = vista.txt_tipo_doc_cliente.getText();
                String num_documento = vista.txt_num_doc_cliente.getText();
                String celular = vista.txt_cel_cliente.getText();
                String email = vista.txt_email_cliente.getText();

                Cliente cliente = new Cliente(idCliente, nombres, apellido_paterno, apellido_materno, tipo_documento, num_documento, celular, email);
                int r = this.dao.actualizar(cliente);
                if(r == 1){
                    JOptionPane.showMessageDialog(vista, "Cliente actualizado con éxito");
                }else{
                    JOptionPane.showMessageDialog(vista, "Error al actualizar cliente");
                }
            }
        }
    }
    
    private void eliminar(){
        int fila = vista.tb_datos_cliente.getSelectedRow();
        if(fila == -1){
            JOptionPane.showMessageDialog(vista, "Debe seleccionar una fila");
        }else{
            int id = Integer.parseInt((String)vista.tb_datos_cliente.getValueAt(fila, 0).toString());
            int iden = this.dao.eliminar(id);
            if (iden == 1){
                JOptionPane.showMessageDialog(vista, "Cliente eliminado con éxito");
                vista.btn_registrar_cliente.setEnabled(true);
                vista.btn_nuevo_cliente.setEnabled(false);
                vista.btn_actualizar_cliente.setEnabled(false);
            }else{
                JOptionPane.showMessageDialog(vista, "Error al eliminar cliente");
            }
        }
    }
    
    private boolean validarNombre(){
        var = val.validacionNomApe(vista.txt_nombre_cliente.getText());
        if(var == false){
            JOptionPane.showMessageDialog(vista, "Ingrese un nombre valido");
            vista.txt_nombre_cliente.setText("");
            return false;
        }else{
            return true;
        }
    }
    
    private boolean validarApePaterno(){
       var = val.validacionNomApe(vista.txt_apellido_p_cliente.getText());
        if(var == false){
            JOptionPane.showMessageDialog(vista, "Ingrese un apellido valido");
            vista.txt_apellido_p_cliente.setText("");
            return false;
        }else{
            return true;
        }
    }
    
    private boolean validarApeMaterno(){
        var = val.validacionNomApe(vista.txt_apellido_m_cliente.getText());
        if(var == false){
            JOptionPane.showMessageDialog(vista, "Ingrese un apellido valido");
            vista.txt_apellido_m_cliente.setText("");
            return false;
        }else{
            return true;
        }
    }
    
    private boolean validarTipo_documento(){
       var = val.validacionTipoDocumento(vista.txt_tipo_doc_cliente.getText());
        if(var == false){
            JOptionPane.showMessageDialog(vista, "Solo puede ingresar DNI o Pasaporte");
            vista.txt_tipo_doc_cliente.setText("");
            return false;
        }else{
            return true;
        }
    }
    
    private boolean validarNumero_documento(){
       var = val.validacionNumeros(vista.txt_num_doc_cliente.getText());
        if(var == false){
            JOptionPane.showMessageDialog(vista, "Ingrese un numero de documento valido");
            vista.txt_num_doc_cliente.setText("");
            return false;
        }else{
            return true;
        }
    }
    
    private boolean validarCelular(){
        var = val.validacionNumeros(vista.txt_cel_cliente.getText());
        if(var == false){
            JOptionPane.showMessageDialog(vista, "Ingrese un numero de celular valido");
            vista.txt_cel_cliente.setText("");
            return false;
        }else{
            return true;
        }
    }
    
    private boolean validarEmail(){
        var = val.validacionEmail(vista.txt_email_cliente.getText());
        if(var == false){
            JOptionPane.showMessageDialog(vista, "Ingrese un email valido");
            vista.txt_email_cliente.setText("");
            return false;
        }else{
            return true;
        }
    }
    
    private boolean validarBuscar(){
        var = val.validacionLetras(vista.txt_buscar_cliente.getText());
        if(var == false){
            JOptionPane.showMessageDialog(vista, "Ingrese un nombre valido");
            vista.txt_buscar_cliente.setText("");
            return false;
        }else{
            return true;
        }
    }
}
