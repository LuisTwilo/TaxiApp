package gui;

import entities.Usuario;
import managers.UsuarioManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TaxiApp {

    private Map<String, Usuario> usuarios;
    private JPanel panel;
    private JButton botonAdicionarTaxi;
    private JButton botonEliminarTaxi;
    private JButton botonRegistrarUsuario;
    private JButton botonVerUsuarios;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JButton button8;
    private JPanel panelFuncion;
    private JPanel panelBotones;

    static JFrame frame = new JFrame("Taxi App");

    public static void main(String[] args) {
        frame.setContentPane(new TaxiApp().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private Map<String, Usuario> obtenerUsuarios(){
        return UsuarioManager.obtenerUsuarios();

    }

    public TaxiApp() {
        //Se precarga la información de los usuarios
        this.usuarios = UsuarioManager.obtenerUsuarios();
        botonAdicionarTaxi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelFuncion = new RegistrarTaxiPanel().getPanel();
                frame.getContentPane().removeAll();
                frame.getContentPane().add(panelBotones);
                frame.getContentPane().add(panelFuncion);
                frame.pack();
                frame.setVisible(true);
            }
        });

        botonEliminarTaxi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelFuncion = new EliminarTaxiPanel().getPanel();
                frame.getContentPane().removeAll();
                frame.getContentPane().add(panelBotones);
                frame.getContentPane().add(panelFuncion);
                frame.pack();
                frame.setVisible(true);
            }
        });
        botonRegistrarUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelFuncion = new RegistrarUsuario().getRootPanel();
                frame.getContentPane().removeAll();
                frame.getContentPane().add(panelBotones);
                frame.getContentPane().add(panelFuncion);
                frame.pack();
                frame.setVisible(true);
            }
        });
        botonVerUsuarios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                VerUsuariosPanel panel = new VerUsuariosPanel(usuarios);
                frame.getContentPane().removeAll();
                frame.getContentPane().add(panelBotones);
                frame.getContentPane().add(panel.getScrollPane());
                frame.pack();
                frame.setVisible(true);
            }
        });
    }



    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
