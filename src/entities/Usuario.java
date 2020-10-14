package entities;

import db.DBManager;

import java.io.IOException;
import java.util.*;

public class Usuario{
    private String idUsuario;
    private String cedula;
    private String nombres;
    private String apellidos;
    private String direccion;
    private String telefono;
    private String celular;

    public Usuario(String[] usuario) {
        this.idUsuario = usuario[0] ;
        this.cedula = usuario[1];
        this.nombres =  usuario[2];
        this.apellidos =  usuario[3];
        this.direccion = usuario[4];
        this.telefono = usuario[5];
        this.celular = usuario[6];
    }

    public Usuario(){}

    public String[] usuarioArreglo(){
        String[] usuario = {this.idUsuario,this.cedula, this.nombres, this.apellidos, this.direccion, this.telefono, this.celular};
        return usuario;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario){ this.idUsuario = idUsuario;}

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
}
