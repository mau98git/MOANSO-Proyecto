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
import views.VistaListaCliente;
import views.VistaReserva;

public class ClienteListaController implements ActionListener{
    
    DefaultTableModel modeloTabla = new DefaultTableModel();
    VistaListaCliente vista = new VistaListaCliente();
    ClienteDAO dao = new ClienteDAO();
    VistaReserva v = new VistaReserva();
    Validaciones val = new Validaciones();
    boolean var;
    
    public ClienteListaController(VistaListaCliente vistalistacliente) {
        this.vista = vistalistacliente;
        this.vista.btn_buscar.addActionListener(this);
        this.vista.btn_salir.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vista.btn_buscar){
            limpiarTabla();
            if(validarBuscar()==true){
                listar(vista.tb_vista_cliente,vista.txt_buscar_cliente.getText());
            } 
        }
        if(e.getSource() == vista.btn_salir){
            this.vista.dispose();
        }
    }
    
    private void listar(JTable tabla,String buscar){
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
            vista.tb_vista_cliente.setModel(modeloTabla);
        }else{
            JOptionPane.showMessageDialog(vista, "No hay clientes para listar");
        }
    }
    
    private void limpiarTabla (){
        for(int i=0;i<vista.tb_vista_cliente.getRowCount();i++){
            modeloTabla.removeRow(i);
            i = i-1;
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
