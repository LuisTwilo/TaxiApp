package entities;


import managers.TaxiManager;
import managers.UsuarioManager;

import java.util.Date;
import java.util.UUID;

public class Servicio {
    private String idServicio;
    private Usuario usuario;
    private String direccionOrigen;
    private String direccionDestino;
    private Date servicioFechaHora;
    private Integer servicioDuracion;
    private Double servicioValor;
    private Taxi taxiPlaca;
    private enum estadoServicio {
        Pendiente, Cumplido, Cancelado
    }
    private String estado;


    public Servicio(String[] servicio){
        this.idServicio = servicio[0];
        this.usuario = UsuarioManager.obtenerUsuario(servicio[1]);
        this.direccionOrigen = servicio[2];
        this.direccionDestino = servicio[3];
        this.servicioFechaHora = new Date();
        //this.servicioDuracion;
        //this.servicioValor = Double.valueOf(servicio[5]);
        this.taxiPlaca = TaxiManager.obtenerTaxi(servicio[4]);
        this.estado = estadoServicio.Pendiente.name();
    }

    public String getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(String idServicio) {
        this.idServicio = idServicio;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getDireccionOrigen() {
        return direccionOrigen;
    }

    public void setDireccionOrigen(String direccionOrigen) {
        this.direccionOrigen = direccionOrigen;
    }

    public String getDireccionDestino() {
        return direccionDestino;
    }

    public void setDireccionDestino(String direccionDestino) {
        this.direccionDestino = direccionDestino;
    }

    public Date getServicioFechaHora() {
        return servicioFechaHora;
    }

    public void setServicioFechaHora(Date servicioFechaHora) {
        this.servicioFechaHora = servicioFechaHora;
    }

    public Integer getServicioDuracion() {
        return servicioDuracion;
    }

    public void setServicioDuracion(Integer servicioDuracion) {
        this.servicioDuracion = servicioDuracion;
    }

    public Double getServicioValor() {
        return servicioValor;
    }

    public void setServicioValor(Double servicioValor) {
        this.servicioValor = servicioValor;
    }

    public Taxi getTaxi() {
        return taxiPlaca;
    }

    public void setTaxi(Taxi taxi) {
        this.taxiPlaca = taxi;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
