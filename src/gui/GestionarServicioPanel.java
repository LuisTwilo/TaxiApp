package gui;

import entities.Servicio;
import entities.Usuario;
import managers.ServicioManager;
import managers.UsuarioManager;

import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class GestionarServicioPanel {
    private JPanel panel;
    private JTextField cedulaTextField;
    private JTextField nombreTextField;
    private JTextField origenTextField3;
    private JTextField destinoTextField;
    private JButton finalizarServicioButton;
    private JButton cancelarButton;
    private JComboBox taxiComboBox;
    private JButton cancelarServicioButton;
    List<Servicio> serviciosPendientes;
    String taxi;
    Servicio servicioSeleccionado;

    public GestionarServicioPanel() {
        cedulaTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                Usuario usuario = UsuarioManager.obtenerUsuarioPorcedula(cedulaTextField.getText());
                nombreTextField.setText(usuario.getNombres()+" "+usuario.getApellidos());
                serviciosPendientes = ServicioManager.obtenerServiciosVigenteUsuario(usuario.getIdUsuario());
                for (Servicio servicio: serviciosPendientes){
                    taxiComboBox.addItem(servicio.getTaxi().getPlaca());
                }
            }
        });
        taxiComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                taxi = taxiComboBox.getSelectedItem().toString();
                servicioSeleccionado = serviciosPendientes.stream()
                        .filter(servicio -> servicio.getTaxi().getPlaca().equals(taxi))
                        .collect(Collectors.toList()).get(0);

                origenTextField3.setText(servicioSeleccionado.getDireccionOrigen());
                destinoTextField.setText(servicioSeleccionado.getDireccionDestino());
            }
        });
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cerrarPanel();
            }
        });
        cancelarServicioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ServicioManager.actualizarServicio(servicioSeleccionado.getIdServicio(), Servicio.estadoServicio.Cancelado.toString());
                    JOptionPane.showMessageDialog(null, "El servicio fue cancelado correctamente");
                    cerrarPanel();
                } catch (IOException exception) {
                    JOptionPane.showMessageDialog(null, exception.getMessage());
                }
            }
        });
        finalizarServicioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Servicio servicioActualizado = ServicioManager.actualizarServicio(servicioSeleccionado.getIdServicio(), Servicio.estadoServicio.Cumplido.toString());
                    JOptionPane.showMessageDialog(null, "El servicio fue finalizado correctamente \n Valor servicio: "+servicioActualizado.getServicioValor()+" COP \n Duración servicio: "+servicioActualizado.getServicioDuracion()+" minutos");
                    cerrarPanel();
                } catch (IOException exception) {
                    JOptionPane.showMessageDialog(null, exception.getMessage());
                }
            }
        });
    }
    private void cerrarPanel() { this.getPanel().setVisible(false); }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }
}
