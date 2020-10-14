package gui;

import managers.UsuarioManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EliminarUsuario {
    private JPanel panel;
    private JTextField cedulaTextField;
    private JButton cancelarButton;
    private JButton eliminarButton;

    public EliminarUsuario() {
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    UsuarioManager.eliminarUsuario(cedulaTextField.getText());
                    JOptionPane.showMessageDialog(null, "El vehículo fue eliminado correctamente");
                    cerrarPanel();
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, exception.getMessage());
                }
            }
        });
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cerrarPanel();
            }
        });
    }
    public JPanel getPanel() {
        return panel;
    }

    private void cerrarPanel() { this.getPanel().setVisible(false); }

}
