package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TaxiApp {
    private JPanel panel;
    private JButton botonAdicionarTaxi;
    private JButton botonEliminarTaxi;
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
        frame.setContentPane(new TaxiApp().panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public TaxiApp() {
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
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
