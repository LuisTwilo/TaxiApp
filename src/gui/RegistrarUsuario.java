package gui;

import entities.Usuario;
import managers.UsuarioManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;

public class RegistrarUsuario {
    static JFrame frame = new JFrame("Registrar Usuario");
    private JPanel rootPanel;
    private JButton cancelarButton;
    private JButton guardarButton;
    private JTextField nombresTextField;
    private JTextField apellidosTextField;
    private JTextField direccionTextField;
    private JTextField telefonoTextField;
    private JTextField celularTextField;

    private String nombres = "";
    private String apellidos = "";
    private String direccion = "";
    private String telefono = "";
    private String celular = "";

    public JPanel getRootPanel() {
        return rootPanel;
    }

    public RegistrarUsuario() {
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
                    obtenerInfoUsuario();
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, exception.getMessage());
                }
            }
        });
    }
    public static void main(String[] args) {
        frame.setContentPane(new RegistrarUsuario().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }



    private void cerrarPanel() {
        this.getRootPanel().setVisible(false);
    }

    private void obtenerInfoUsuario() throws Exception {
        this.nombres= nombresTextField.getText().trim();
        this.apellidos = apellidosTextField.getText().trim();
        this.direccion = direccionTextField.getText().trim();
        this.telefono = telefonoTextField.getText().trim();
        this.celular = celularTextField.getText().trim();

        if(this.nombres.equals("")){
            throw new Exception("Por favor ingrese el nombre del usuario");
        }
        if(this.apellidos.equals("")){
            throw new Exception("Por favor ingrese el apellido del usuario");
        }
        if(this.direccion.equals("")){
            throw new Exception("Por favor ingrese la dirección del usuario");
        }


        try{
            String[] usr = {UUID.randomUUID().toString(), this.nombres, this.apellidos, this.direccion, this.telefono, this.celular };
            Usuario usuario = new Usuario(usr);
            UsuarioManager.guardarUsuario(usuario);
            JOptionPane.showMessageDialog(null, "El usuario "+this.nombres+" fue registrado correctamente");
            cerrarPanel();
        }catch (Exception e){
            throw new Exception("Ocurrió un error guardando la información del usuario");
        }
    }

}
