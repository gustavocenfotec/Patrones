package model.Administrativos;

import model.IDModel;

/*
Modelo abstracto el cual tiene todos los atributos del sector administrativo de la institucion
mismo es el padre de los otros puntos administrativos
 */

public abstract class CursoGrupoGeneralModel extends IDModel {
    private String nombre;

    private String descripcion;

    private boolean estado;

    public CursoGrupoGeneralModel(int id, String nombre, String descripcion, boolean estado) {
        super(id);
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public CursoGrupoGeneralModel() {

    }

    public CursoGrupoGeneralModel(String nombre, String descripcion, boolean estado) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "CursoGrupoGeneralModel{ id="+getId() +
                "nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", estado=" + estado +
                '}';
    }
}
