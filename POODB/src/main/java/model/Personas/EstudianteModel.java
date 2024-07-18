package model.Personas;

import java.sql.Date;

/*
El Objeto estudiante es hijo de PersonaModel, este tiene como diferencia fundamental la fecha nacimiento
como un date

 */

public class EstudianteModel extends PersonaModel {

    private Date fecha_nacimiento;

    public EstudianteModel() {
    }
    public EstudianteModel(int id, String nombre, int identificacion, String email, boolean estado, Date fecha_nacimiento) {
        super(id, nombre, identificacion, email, estado);
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public EstudianteModel(String nombre, int identificacion, String email, boolean estado, Date fecha_nacimiento) {
        super(nombre, identificacion, email, estado);
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }


    @Override
    public String toString() {
        return super.toString()+ "fecha_nacimiento=" + fecha_nacimiento;
    }
}
