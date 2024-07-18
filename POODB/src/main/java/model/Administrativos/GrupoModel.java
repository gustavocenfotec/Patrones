package model.Administrativos;
/*
Hijo CursoGrupoGeneralModel, este guarda el nombre del grupo asi como la informacion principal del mismo

 */
public class GrupoModel extends CursoGrupoGeneralModel {
    public GrupoModel(String nombre, String descripcion, boolean estado) {
        super(nombre, descripcion, estado);
    }

    public GrupoModel(int id, String nombre, String descripcion, boolean estado) {
        super(id, nombre, descripcion, estado);
    }

    public GrupoModel() {

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
}
