package model.Administrativos;

/*
Hijo CursoGrupoGeneralModel, este guarda el nombre del curso asi como la informacion principal del mismo

 */
public class CursoModel extends CursoGrupoGeneralModel {

    public CursoModel(int id, String nombre, String descripcion, boolean estado) {
        super(id, nombre, descripcion, estado);
    }

    public CursoModel(String nombre, String descripcion, boolean estado) {
        super(nombre, descripcion, estado);
    }

    public CursoModel( ){
    }

    @Override
    public String getNombre() {
        return super.getNombre();
    }

    @Override
    public void setNombre(String nombre) {
        super.setNombre(nombre);
    }

    @Override
    public String getDescripcion() {
        return super.getDescripcion();
    }

    @Override
    public void setDescripcion(String descripcion) {
        super.setDescripcion(descripcion);
    }

    @Override
    public boolean isEstado() {
        return super.isEstado();
    }

    @Override
    public void setEstado(boolean estado) {
        super.setEstado(estado);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}




