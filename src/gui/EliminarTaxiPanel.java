package gui;

import managers.TaxiManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EliminarTaxiPanel {
    private JPanel panel;
    private JTextField placaTextField;
    private JButton cancelarButton;
    private JButton eliminarButton;

    public EliminarTaxiPanel() {
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cerrarPanel();
            }
        });
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    eliminarTaxi(placaTextField.getText());
                    JOptionPane.showMessageDialog(null, "El vehículo fue eliminado correctamente");
                    cerrarPanel();
                } catch (Exception exception) {
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

    private void eliminarTaxi(String placa) throws Exception {
        TaxiManager.eliminarTaxi(placa);
    }
}
