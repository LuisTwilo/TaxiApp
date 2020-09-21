package entities;

import utils.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Taxi {
    private String placa;
    private String marca;
    private String modelo;
    private String cedulaConductor;
    private String poliza;
    private String soat;
    private Date fechaVencimientoTecnoMecanica;

    public Taxi(String[] taxi) throws ParseException {
        this.placa = taxi[0];
        this.marca = taxi[1];
        this.modelo = taxi[2];
        this.cedulaConductor = taxi[3];
        this.poliza = taxi[4];
        this.soat = taxi[5];
        this.fechaVencimientoTecnoMecanica = DateUtils.convertirStringADate(taxi[6], "dd/MM/yyyy");
    }

    public String[] taxiArreglo(){
        String[] taxi = {this.placa, this.marca, this.modelo, this.cedulaConductor, this.poliza, this.soat, this.fechaVencimientoTecnoMecanica.toString()};
        return taxi;
    }


    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCedulaConductor() {
        return cedulaConductor;
    }

    public void setCedulaConductor(String cedulaConductor) {
        this.cedulaConductor = cedulaConductor;
    }

    public String getPoliza() {
        return poliza;
    }

    public void setPoliza(String poliza) {
        this.poliza = poliza;
    }

    public String getSoat() {
        return soat;
    }

    public void setSoat(String soat) {
        this.soat = soat;
    }

    public Date getFechaVencimientoTecnoMecanica() {
        return fechaVencimientoTecnoMecanica;
    }

    public void setFechaVencimientoTecnoMecanica(Date fechaVencimientoTecnoMecanica) {
        this.fechaVencimientoTecnoMecanica = fechaVencimientoTecnoMecanica;
    }
}
