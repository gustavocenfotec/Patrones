package model.Personas;
/*
El objeto Profesor,es heredero con diferencia del atributo departamento, el cual delimita de cual departamento
pertenece
 */
public class ProfesorModel extends PersonaModel {

    private String departamento;

    public ProfesorModel() {

    }


    public ProfesorModel(int id, String nombre, int identificacion, String email, boolean estado, String departamento) {
        super(id, nombre, identificacion, email, estado);
        this.departamento = departamento;
    }

    public ProfesorModel(String nombre, int identificacion, String email, Boolean estado, String departamento) {
        super(nombre, identificacion, email, estado);
        this.departamento = departamento;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    @Override
    public String toString() {
        return super.toString()+ "ProfesorModel{" +
                "departamento='" + departamento + '\'' +
                '}';
    }
}
