package controller.Personas;

import DAO.Personal.ProfesorDAO;
import model.DataSource.conexion;

import model.Personas.EstudianteModel;
import model.Personas.ProfesorModel;
import view.ConsoleView;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ProfesorController {
    private ConsoleView consoleView;

    private ProfesorDAO profesorDAO;

    public ProfesorController(ConsoleView consoleView ){
        this.consoleView = consoleView;
        Connection connection = conexion.getConnection();
        this.profesorDAO = new ProfesorDAO(connection);
    }

    public void agregarProfesor( ProfesorModel datos){
        try {
            profesorDAO.agregarProfesor(datos);
            consoleView.showMessage("200");
            consoleView.showMessage("Datos insertados");
        }catch (SQLException e){
            consoleView.showMessage("500");
            consoleView.errorMessage("Datos no insertados" + e);
        }
    }

    public void actualizarProfesor (ProfesorModel datos){

        try {
            profesorDAO.actualizarProfesor(datos);
            consoleView.showMessage("200");
            consoleView.showMessage("Datos insertados");
        }catch (SQLException e){
            consoleView.showMessage("500");
            consoleView.errorMessage("Datos no insertados");
            consoleView.errorMessage(e.toString());
        }
    }

    public void borrarProfesor (int id){
        try {
            profesorDAO.borrarProfesor(id);
            consoleView.showMessage("200");
            consoleView.showMessage("Datos Borrados");
        }catch (SQLException e){
            consoleView.showMessage("500");
            consoleView.errorMessage("Datos no Borrados");
            consoleView.errorMessage(e.toString());
        }
    }
    public ProfesorModel buscarProfesorIdentificacion (int identificacion) {

        try {
            ProfesorModel datos = profesorDAO.buscarProfesorIdentificacion(identificacion);
            if (datos.getIdentificacion() == 0) {
                consoleView.showMessage("404");
                consoleView.showMessage("Datos no Encontrados");
                return datos=null;
            } else {
                consoleView.showMessage("200");
                consoleView.showMessage("Datos Encontrados");
                return datos;
            }

        } catch (SQLException e) {
            consoleView.showMessage("500");
            consoleView.errorMessage("Datos no Encontrados");
            consoleView.errorMessage(e.toString());
        }
        ProfesorModel datos=new ProfesorModel();
        return datos;
    }

    public List<ProfesorModel> buscarProfesores() {
        List<ProfesorModel> lista = null;
        try {
            lista = profesorDAO.listaProfesores();
            consoleView.showMessage("200");
            consoleView.showMessage("Datos Encontrados");
            return lista;

        } catch (SQLException e) {
            consoleView.showMessage("500");
            consoleView.errorMessage("Datos no Encontrados");
            consoleView.errorMessage(e.toString());
        }
        return lista;
    }
}
