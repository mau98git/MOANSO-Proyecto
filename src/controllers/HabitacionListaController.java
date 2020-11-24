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
import views.VistaListaHabitacion;
import views.VistaReserva;

public class HabitacionListaController implements ActionListener{
    
    DefaultTableModel modeloTabla = new DefaultTableModel();
    VistaListaHabitacion vista = new VistaListaHabitacion();
    HabitacionDAO dao = new HabitacionDAO();
    VistaReserva v = new VistaReserva();
    Validaciones val = new Validaciones();
    boolean var;
    
    public HabitacionListaController(VistaListaHabitacion vistalistahabitacion) {
        this.vista = vistalistahabitacion;
        this.vista.btn_buscar_habitacion.addActionListener(this);
        this.vista.btn_salir_habitacion.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vista.btn_buscar_habitacion){
            limpiarTabla();
            if(validarBuscar()==true){
                listar(vista.tb_vista_habitacion,vista.txt_buscar_habitacion.getText());
            } 
        }
        if(e.getSource() == vista.btn_salir_habitacion){
            this.vista.dispose();
        }
    }
    
    private void listar(JTable tabla,String buscar){
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
            vista.tb_vista_habitacion.setModel(modeloTabla);
        }else{
            JOptionPane.showMessageDialog(vista, "No hay habitaciones para listar");
        }
    }
    
    private void limpiarTabla (){
        for(int i=0;i<vista.tb_vista_habitacion.getRowCount();i++){
            modeloTabla.removeRow(i);
            i = i-1;
        }
    }
    
    private boolean validarBuscar(){
        var = val.validacionHabitaciones(vista.txt_buscar_habitacion.getText());
        if(var == false){
            JOptionPane.showMessageDialog(vista, "Ingrese un numero de habitacion valido");
            vista.txt_buscar_habitacion.setText("");
            return false;
        }else{
            return true;
        }
    }
}
