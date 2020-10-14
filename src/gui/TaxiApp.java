package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TaxiApp {
    private JPanel panel1;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JButton button8;
    private JPanel panelFuncion;
    private JPanel panelBotones;

    static JFrame frame = new JFrame("Taxi App");

    public static void main(String[] args) {
        frame.setContentPane(new TaxiApp().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public TaxiApp() {
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelFuncion = new RegistrarTaxi().getRootPanel();
                frame.getContentPane().removeAll();
                frame.getContentPane().add(panelBotones);
                frame.getContentPane().add(panelFuncion);
                frame.pack();
                frame.setVisible(true);
            }
        });
        button3.addActionListener(new ActionListener() {
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
    }
}
