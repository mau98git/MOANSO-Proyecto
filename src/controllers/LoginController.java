package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import models.Trabajador;
import models.TrabajadorDAO;
import views.Menu;
import views.VistaLogin;

public class LoginController implements ActionListener{
    DefaultTableModel modeloTabla = new DefaultTableModel();
    VistaLogin vista = new VistaLogin();
    TrabajadorDAO dao = new TrabajadorDAO();
    int totalRegistros;
    
    public LoginController(VistaLogin vistaLogin) {
        this.vista = vistaLogin;
        this.vista.btn_ingresar_login.addActionListener(this);
        this.vista.btn_salir_login.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vista.btn_ingresar_login){
            String login=vista.txt_usuario_login.getText();
            String password=vista.txt_password_login.getText();
            int num = login(vista.tb_listado_login,login,password);
            if(num>0){
                vista.dispose();
                Menu mnu = new Menu();
                mnu.toFront();
                mnu.setVisible(true);
                mnu.lbl_idTrabajador.setText((String)vista.tb_listado_login.getValueAt(0, 0).toString());
                mnu.lbl_nombre.setText((String)vista.tb_listado_login.getValueAt(0, 1).toString());
                mnu.lbl_ape_paterno.setText((String)vista.tb_listado_login.getValueAt(0, 2).toString());
                mnu.lbl_ape_materno.setText((String)vista.tb_listado_login.getValueAt(0, 3).toString());
                mnu.lbl_acceso.setText((String)vista.tb_listado_login.getValueAt(0, 4).toString());
                if(!mnu.lbl_acceso.getText().equals("Administrador")){
                    mnu.mnuHabitaciones.setEnabled(false);
                    mnu.mnuTrabajador.setEnabled(false);
                    mnu.mnuReportes.setEnabled(false);
                }
                
            }else{
            JOptionPane.showMessageDialog(vista, "Usuario o contraseña incorrectos");
            }
        }
        if(e.getSource() == vista.btn_salir_login){
            salir();
        }
    }
    
    private int login(JTable tabla,String login,String password){
        this.modeloTabla = (DefaultTableModel)tabla.getModel();
        List<Trabajador> listarTrabajadores = this.dao.login(login,password);
        Object[] objeto = new Object[8];
        totalRegistros=0;
        if(listarTrabajadores.size()>0){
            for(int i=0;i<listarTrabajadores.size();i++){
                objeto[0] = listarTrabajadores.get(i).getId_trabajador();
                objeto[1] = listarTrabajadores.get(i).getNombre();
                objeto[2] = listarTrabajadores.get(i).getApe_paterno();
                objeto[3] = listarTrabajadores.get(i).getApe_materno();
                objeto[4] = listarTrabajadores.get(i).getAcceso();
                objeto[5] = listarTrabajadores.get(i).getLogin();
                objeto[6] = listarTrabajadores.get(i).getPassword();
                objeto[7] = listarTrabajadores.get(i).getEstado();
                totalRegistros=totalRegistros+1;
                modeloTabla.addRow(objeto);
            }
            vista.tb_listado_login.setModel(modeloTabla);
        }
        return totalRegistros;
    }
    
    
    private void salir(){
        System.exit(0);
    }
}
