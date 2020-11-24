package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import models.Trabajador;
import models.TrabajadorDAO;
import views.VistaRepTrabajadores;

public class RepTrabajadorController implements ActionListener{
    DefaultTableModel modeloTabla = new DefaultTableModel();
    VistaRepTrabajadores vista = new VistaRepTrabajadores();
    TrabajadorDAO dao = new TrabajadorDAO();
    
    public RepTrabajadorController(VistaRepTrabajadores vistareptrabajadores) {
        this.vista = vistareptrabajadores;
        this.vista.btnListarReporteTrabajador.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vista.btnListarReporteTrabajador){
            limpiarTabla();
            listar(vista.tbReporteTrabajadores);
        }
    }
    
    private void listar(JTable tabla){
        this.modeloTabla = (DefaultTableModel)tabla.getModel();
        List<Trabajador> listarTrabajadores = this.dao.repListar();
        Object[] objeto = new Object[10];
        if(listarTrabajadores.size()>0){
            for(int i=0;i<listarTrabajadores.size();i++){
                objeto[0] = listarTrabajadores.get(i).getNombre();
                objeto[1] = listarTrabajadores.get(i).getApe_paterno();
                objeto[2] = listarTrabajadores.get(i).getApe_materno();
                objeto[3] = listarTrabajadores.get(i).getTipo_documento();
                objeto[4] = listarTrabajadores.get(i).getNum_documento();
                objeto[5] = listarTrabajadores.get(i).getCelular();
                objeto[6] = listarTrabajadores.get(i).getEmail();
                objeto[7] = listarTrabajadores.get(i).getSueldo();
                objeto[8] = listarTrabajadores.get(i).getAcceso();
                objeto[9] = listarTrabajadores.get(i).getLogin();
                modeloTabla.addRow(objeto);
            }
            vista.tbReporteTrabajadores.setModel(modeloTabla);
        }else{
            JOptionPane.showMessageDialog(vista, "No hay trabajadores para listar");
        }
    }
    
    private void limpiarTabla (){
        for(int i=0;i<vista.tbReporteTrabajadores.getRowCount();i++){
            modeloTabla.removeRow(i);
            i = i-1;
        }
    }
}
