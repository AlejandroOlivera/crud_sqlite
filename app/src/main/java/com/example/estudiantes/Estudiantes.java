package com.example.estudiantes;

public class Estudiantes {

    int id;
    int identificacion;
    String nombre;
    String apellido;
    String colegio;
    String tipo;
    String departamento;
    String ciudad;
    int puntaje;

    public Estudiantes() {

    }

    public Estudiantes(int identificacion, String nombre, String apellido, String colegio, String tipo, String departamento, String ciudad, int puntaje) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.apellido = apellido;
        this.colegio = colegio;
        this.tipo = tipo;
        this.departamento = departamento;
        this.ciudad = ciudad;
        this.puntaje = puntaje;
    }

    public Estudiantes(int id, int identificacion, String nombre, String apellido, String colegio, String tipo, String departamento, String ciudad, int puntaje) {
        this.id = id;
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.apellido = apellido;
        this.colegio = colegio;
        this.tipo = tipo;
        this.departamento = departamento;
        this.ciudad = ciudad;
        this.puntaje = puntaje;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(int identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getColegio() {
        return colegio;
    }

    public void setColegio(String colegio) {
        this.colegio = colegio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }
}
