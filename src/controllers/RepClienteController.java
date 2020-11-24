package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import models.Cliente;
import models.ClienteDAO;
import views.VistaRepClientes;

public class RepClienteController implements ActionListener{
    DefaultTableModel modeloTabla = new DefaultTableModel();
    VistaRepClientes vista = new VistaRepClientes();
    ClienteDAO dao = new ClienteDAO();
    
    public RepClienteController(VistaRepClientes vistarepclientes) {
        this.vista = vistarepclientes;
        this.vista.btnListarReporteClientes.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vista.btnListarReporteClientes){
            limpiarTabla();
            listar(vista.tbReporteClientes);
        }
    }
    
    private void listar(JTable tabla){
        this.modeloTabla = (DefaultTableModel)tabla.getModel();
        List<Cliente> listarTrabajadores = this.dao.repListar();
        Object[] objeto = new Object[7];
        if(listarTrabajadores.size()>0){
            for(int i=0;i<listarTrabajadores.size();i++){
                objeto[0] = listarTrabajadores.get(i).getNombres();
                objeto[1] = listarTrabajadores.get(i).getApellido_paterno();
                objeto[2] = listarTrabajadores.get(i).getApellido_materno();
                objeto[3] = listarTrabajadores.get(i).getTipo_documento();
                objeto[4] = listarTrabajadores.get(i).getNum_documento();
                objeto[5] = listarTrabajadores.get(i).getCelular();
                objeto[6] = listarTrabajadores.get(i).getEmail();
                modeloTabla.addRow(objeto);
            }
            vista.tbReporteClientes.setModel(modeloTabla);
        }else{
            JOptionPane.showMessageDialog(vista, "No hay clientes para listar");
        }
    }
    
    private void limpiarTabla (){
        for(int i=0;i<vista.tbReporteClientes.getRowCount();i++){
            modeloTabla.removeRow(i);
            i = i-1;
        }
    }
}
