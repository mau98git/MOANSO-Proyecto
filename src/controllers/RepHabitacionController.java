package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import models.Habitacion;
import models.HabitacionDAO;
import views.VistaRepHabitacion;

public class RepHabitacionController implements ActionListener{
    DefaultTableModel modeloTabla = new DefaultTableModel();
    VistaRepHabitacion vista = new VistaRepHabitacion();
    HabitacionDAO dao = new HabitacionDAO();
    
    public RepHabitacionController(VistaRepHabitacion vistarephabitacion) {
        this.vista = vistarephabitacion;
        this.vista.btnListarReporteHabitaciones.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vista.btnListarReporteHabitaciones){
            limpiarTabla();
            listar(vista.tbReporteHabitaciones);
        }
    }
    
    private void listar(JTable tabla){
        this.modeloTabla = (DefaultTableModel)tabla.getModel();
        List<Habitacion> listarHabitacion = this.dao.repListar();
        Object[] objeto = new Object[5];
        if(listarHabitacion.size()>0){
            for(int i=0;i<listarHabitacion.size();i++){
                objeto[0] = listarHabitacion.get(i).getNumero();
                objeto[1] = listarHabitacion.get(i).getPiso();
                objeto[2] = listarHabitacion.get(i).getPrecio();
                objeto[3] = listarHabitacion.get(i).getTipo_Habitacion();
                objeto[4] = listarHabitacion.get(i).getEstado();
                modeloTabla.addRow(objeto);
            }
            vista.tbReporteHabitaciones.setModel(modeloTabla);
        }else{
            JOptionPane.showMessageDialog(vista, "No hay habitaciones para listar");
        }
    }
    
    private void limpiarTabla (){
        for(int i=0;i<vista.tbReporteHabitaciones.getRowCount();i++){
            modeloTabla.removeRow(i);
            i = i-1;
        }
    }
}
