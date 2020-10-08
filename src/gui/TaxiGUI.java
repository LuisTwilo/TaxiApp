package gui;

import entities.Taxi;
import managers.TaxiManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class TaxiGUI {
    static JFrame frame = new JFrame("Registrar taxi");
    private JPanel rootPanel;
    private JTextField placaTextField;
    private JTextField marcaTextField;
    private JSpinner modeloSpinner;
    private JTextField conductorTextField;
    private JTextField polizaTextField;
    private JTextField soatTextField;
    private JComboBox vencTMDia;
    private JComboBox vencTMMes;
    private JComboBox vencTMAnio;
    private JButton guardarButton;

    private String placa;
    private String marca;
    private String modelo;
    private String conductor;
    private String poliza;
    private String soat;
    private String fechaVenceTM;

    public TaxiGUI() {
        this.modeloSpinner.setValue(2020);
        this.inicializarComboBoxFecha();
        /**
         * ejecuta la accion asociada al boton
         */
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    obtenerInfoTaxi();
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null, exception.getMessage());
                }
            }
        });
    }

    public static void main(String[] args) {

        frame.setContentPane(new TaxiGUI().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Valida la información del taxi y lo almacena en BD
     * @throws Exception
     */
    private void obtenerInfoTaxi() throws Exception {
        this.placa = placaTextField.getText();
        this.marca = marcaTextField.getText();
        this.modelo = modeloSpinner.getValue().toString();
        this.conductor = conductorTextField.getText();
        this.poliza = polizaTextField.getText();
        this.soat = soatTextField.getText();

        if(this.placa.equals("")){
            throw new Exception("Por favor ingrese la placa del vehículo");
        }

        if(this.marca.equals("")){
            throw new Exception("Por favor ingrese la marca del vehículo");
        }

        if(this.modelo.equals("")){
            throw new Exception("Por favor ingrese el modelo del vehículo");
        }

        if(this.conductor.equals("")){
            throw new Exception("Por favor ingrese la cédula del conductor del vehículo");
        }

        if(this.poliza.equals("")){
            throw new Exception("Por favor ingrese la información de la póliza del vehículo");
        }

        if(this.soat.equals("")){
            throw new Exception("Por favor ingrese la información del SOAT del vehículo");
        }

        validarFecha(vencTMDia.getSelectedItem().toString(), vencTMMes.getSelectedItem().toString(), vencTMAnio.getSelectedItem().toString());

        System.out.println(this.placa);
        System.out.println(this.marca);
        System.out.println(this.modelo);
        System.out.println(this.conductor);
        System.out.println(this.poliza);
        System.out.println(this.soat);
        System.out.println(this.fechaVenceTM);

        try{
            String[] taxStr = {this.placa, this.marca, this.modelo, this.conductor, this.poliza, this.poliza, this.fechaVenceTM};
            Taxi tax = new Taxi(taxStr);
            TaxiManager.guardarTaxi(tax);
            JOptionPane.showMessageDialog(null, "El vehículo "+this.placa+" fue registrado correctamente");
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        }catch(Exception e){
            throw new Exception("Ocurrió un error guardando la información del vehículo");
        }

    }

    /**
     * inicializa los combos para la fecha de vencimiento de la tecnomecanica
     */
    private void inicializarComboBoxFecha() {
        this.vencTMDia.addItem("");
        for(int i = 1; i <= 31; i++){
            this.vencTMDia.addItem(i);
        }

        String[] meses = {"", "01", "02", "03",
                "04", "05", "06",
                "07", "08", "09",
                "10", "11", "12"};
        for(int i = 0; i < meses.length; i++){
            this.vencTMMes.addItem(meses[i]);
        }

        this.vencTMAnio.addItem("");
        int anioActual = Calendar.getInstance().get(Calendar.YEAR);
        for(int i = anioActual; i <= anioActual+5; i++){
            this.vencTMAnio.addItem(i);
        }

    }

    /**
     * Valida que la información ingresada corresponda a una fecha válida
     * @param dia día seleccionado
     * @param mes mes seleccionado
     * @param anio anio seleccionado
     * @throws Exception si la fecha no corresponde a una válida
     */
    private void validarFecha(String dia, String mes, String anio) throws Exception {
        if(dia.equals("")||mes.equals("")||anio.equals("")){
            throw new Exception("Por favor ingrese la fecha de vencimiento de la tecnomecánica");
        }
        if (mes.equals("02")){
            GregorianCalendar calendar = new GregorianCalendar();
            if (calendar.isLeapYear(Integer.parseInt(anio)) && Integer.parseInt(dia)>29){
                throw new Exception("Por favor verifique la fecha ingresada");
            }else if(Integer.parseInt(dia)>28){
                throw new Exception("Por favor verifique la fecha ingresada");
            }
        } else if ((mes.equals("04")||mes.equals("06")||mes.equals("09")||mes.equals("11")) && Integer.parseInt(dia)>30 ){
            throw new Exception("Por favor verifique la fecha ingresada");
        }

        this.fechaVenceTM = dia+"/"+mes+"/"+anio;
    }
}
