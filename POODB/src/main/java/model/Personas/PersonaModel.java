package model.Personas;

import model.IDModel;

/*
Objeto Padre de todas las personas
este goza de los atributos compartidos de todas las personas en el sistema
 */

public class PersonaModel extends IDModel {
    private String nombre;
    private int identificacion;
    private String email;
    private boolean estado;

    public PersonaModel() {

    }

    public PersonaModel(int id, String nombre, int identificacion, String email, boolean estado) {
        super(id);
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.email = email;
        this.estado = estado;
    }

    public PersonaModel(String nombre, int identificacion, String email, boolean estado) {
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.email = email;
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(int identificacion) {
        this.identificacion = identificacion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return super.toString()+"PersonaModel{" +
                "nombre='" + nombre + '\'' +
                ", identificacion=" + identificacion +
                ", email='" + email + '\'' +
                ", estado=" + estado +
                '}';
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
