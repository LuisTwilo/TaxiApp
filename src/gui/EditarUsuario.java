package gui;

import entities.Usuario;
import managers.UsuarioManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class EditarUsuario {
    private JPanel panel;
    private JTextField cedulaTextField;
    private JTextField nombresTextField;
    private JTextField apellidosTextField;
    private JTextField direccionTextField;
    private JTextField telefonoTextField;
    private JTextField celularTextField;
    private JButton cancelarButton;
    private JButton guardarButton;
    private Usuario usuario;

    public JPanel getPanel() {
        return panel;
    }

    public EditarUsuario() {
        cedulaTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                usuario = UsuarioManager.obtenerUsuarioPorcedula(cedulaTextField.getText());

                if (usuario != null) {
                    nombresTextField.setText(usuario.getNombres());
                    apellidosTextField.setText(usuario.getApellidos());
                    direccionTextField.setText(usuario.getDireccion());
                    telefonoTextField.setText(usuario.getTelefono());
                    celularTextField.setText(usuario.getCelular());
                } else {
                    JOptionPane.showMessageDialog(null, "El usuario no existe");
                    cerrarPanel();
                }


            }
        });
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    UsuarioManager.actualizarUsuario(usuario.getIdUsuario(), usuario.getCedula(),
                            direccionTextField.getText(), telefonoTextField.getText(), celularTextField.getText());
                    JOptionPane.showMessageDialog(null, "El usuario fue editado con éxito");
                    cerrarPanel();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Hubo un problema actualizando el usuario");
                    cerrarPanel();
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

    private void cerrarPanel() {
        this.getPanel().setVisible(false);
    }
}
