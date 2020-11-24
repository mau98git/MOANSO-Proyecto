package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import models.Reserva;
import models.ReservaDAO;
import views.VistaRepReservas;

public class RepReservasController implements ActionListener{
    
    DefaultTableModel modeloTabla = new DefaultTableModel();
    VistaRepReservas vista = new VistaRepReservas();
    ReservaDAO dao = new ReservaDAO();
    
    public RepReservasController(VistaRepReservas vistarepreservas) {
        this.vista = vistarepreservas;
        this.vista.btnListarReporteReservas.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vista.btnListarReporteReservas){
            limpiarTabla();
            listar(vista.tbReporteReservas);
        }
    }
    
    private void listar(JTable tabla){
        this.modeloTabla = (DefaultTableModel)tabla.getModel();
        List<Reserva> listarReserva = this.dao.listar();
        Object[] objeto = new Object[9];
        if(listarReserva.size()>0){
            for(int i=0;i<listarReserva.size();i++){
                objeto[0] = listarReserva.get(i).getNombres()+" "+listarReserva.get(i).getApellido_paterno()+" "+listarReserva.get(i).getApellido_materno();
                objeto[1] = listarReserva.get(i).getNombre()+" "+listarReserva.get(i).getApellido_paterno_trabajador()+" "+listarReserva.get(i).getApellido_materno_trabajdor();
                objeto[2] = listarReserva.get(i).getNumero();
                objeto[3] = listarReserva.get(i).getTipo_reserva();
                objeto[4] = listarReserva.get(i).getFecha_reserva();
                objeto[5] = listarReserva.get(i).getFecha_ingreso();
                objeto[6] = listarReserva.get(i).getFecha_salida();
                objeto[7] = listarReserva.get(i).getCosto_alojamiento();
                modeloTabla.addRow(objeto);
            }
            vista.tbReporteReservas.setModel(modeloTabla);
        }else{
            JOptionPane.showMessageDialog(vista, "No hay reservas para listar");
        }
    }
    
    private void limpiarTabla (){
        for(int i=0;i<vista.tbReporteReservas.getRowCount();i++){
            modeloTabla.removeRow(i);
            i = i-1;
        }
    }
}
