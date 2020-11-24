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
import views.VistaListaTrabajador;
import views.VistaReserva;

public class TrabajadorListaController implements ActionListener{
    
    DefaultTableModel modeloTabla = new DefaultTableModel();
    VistaListaTrabajador vista = new VistaListaTrabajador();
    TrabajadorDAO dao = new TrabajadorDAO();
    VistaReserva v = new VistaReserva();
    Validaciones val = new Validaciones();
    boolean var;
    
    public TrabajadorListaController(VistaListaTrabajador vistalistatrabajdor) {
        this.vista = vistalistatrabajdor;
        this.vista.btn_buscar_trabajador.addActionListener(this);
        this.vista.btn_salir_trabajador.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vista.btn_buscar_trabajador){
            limpiarTabla();
            if(validarBuscar()==true){
                listar(vista.tb_vista_trabajador,vista.txt_buscar_trabajador.getText());
            } 
        }
        if(e.getSource() == vista.btn_salir_trabajador){
            this.vista.dispose();
        }
    }
    
    private void listar(JTable tabla,String buscar){
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
            vista.tb_vista_trabajador.setModel(modeloTabla);
        }else{
            JOptionPane.showMessageDialog(vista, "No hay trabajdores para listar");
        }
        
    }
    
    private void limpiarTabla (){
        for(int i=0;i<vista.tb_vista_trabajador.getRowCount();i++){
            modeloTabla.removeRow(i);
            i = i-1;
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
