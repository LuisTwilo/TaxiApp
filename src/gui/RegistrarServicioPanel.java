package gui;

import entities.Servicio;
import entities.Taxi;
import entities.Usuario;
import managers.ServicioManager;
import managers.UsuarioManager;
import utils.DateUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class RegistrarServicioPanel {
    private JTextField usuarioTextField;
    private JTextField origenTextField;
    private JTextField destinoTextField;
    private JComboBox taxiComboBox;
    private JTextField nombreUsuarioTextField;
    private JButton guardarButton;
    private JButton cancelarButton;
    private JPanel panel;

    private String idUsuario;
    private String ccUsuario;
    private String origen;
    private String destino;
    private String taxi;

    public RegistrarServicioPanel() {
        List<Taxi> taxisDisponibles = ServicioManager.obtenerTaxisDisponibles();

        for (Taxi taxi : taxisDisponibles){
            taxiComboBox.addItem(taxi.getPlaca());
        }

        usuarioTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                Usuario usuario = UsuarioManager.obtenerUsuarioPorcedula(usuarioTextField.getText());
                nombreUsuarioTextField.setText(usuario.getNombres() + " " + usuario.getApellidos());
                origenTextField.setText(usuario.getDireccion());
                idUsuario = usuario.getIdUsuario();
            }
        });
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cerrarPanel();
            }
        });
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    obtenerInfoServicio();
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, exception.getMessage());
                }
            }
        });
    }

    private void obtenerInfoServicio() throws Exception {
        this.ccUsuario = this.usuarioTextField.getText();
        this.origen = this.origenTextField.getText();
        this.destino = this.destinoTextField.getText();
        this.taxi = this.taxiComboBox.getSelectedItem().toString();

        if(this.ccUsuario.equals("")){
            throw new Exception("Por favor ingrese la información del usuario");
        }

        if(this.origen.equals("")){
            throw new Exception("Por favor ingrese el origen del servicio");
        }

        if(this.destino.equals("")){
            throw new Exception("Por favor ingrese el destino del servicio");
        }

        try{
            String[] serString = {UUID.randomUUID().toString(),idUsuario, origen, destino, DateUtils.DateAStringConFormato(new Date(), "dd/MM/yyyy HH:mm:ss"),"","", taxi, ""};
            Servicio ser = new Servicio(serString);
            ServicioManager.guardarServicio(ser);
            JOptionPane.showMessageDialog(null, "El servicio fue registrado correctamente");
            cerrarPanel();
        }catch(Exception e){
            throw new Exception("Ocurrió un error guardando la información del servicio");
        }
    }

    private void cerrarPanel() {
        this.getPanel().setVisible(false);
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }
}
