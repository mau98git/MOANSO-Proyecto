package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import models.Habitacion;
import models.HabitacionDAO;
import views.VistaHabitaciones;

public class HabitacionController implements ActionListener{
    boolean listar;
    boolean actualizar;
    
    DefaultTableModel modeloTabla = new DefaultTableModel();
    VistaHabitaciones vista = new VistaHabitaciones();
    HabitacionDAO dao = new HabitacionDAO();
    
    public HabitacionController(VistaHabitaciones vistaHabitaciones){
        this.vista=vistaHabitaciones;
        this.vista.btnListar_hab.addActionListener(this);
        this.vista.btnnuevo_hab.addActionListener(this);
        this.vista.btnguardar_hab.addActionListener(this);
        this.vista.btncancelar_hab.addActionListener(this);
        this.vista.btnbuscar_hab.addActionListener(this);
        this.vista.btneliminar_hab.addActionListener(this);
        this.vista.btnsalir_hab.addActionListener(this);
        this.vista.btneditar_hab.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
}
