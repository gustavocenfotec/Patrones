package controller.Personas;

import DAO.Personal.EstudianteDAO;
import model.DataSource.conexion;
import model.Personas.EstudianteModel;
import view.ConsoleView;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class EstudiantesController {
    private ConsoleView consoleView;

    private EstudianteDAO estudianteDAO;

    public EstudiantesController(ConsoleView consoleView ){
        this.consoleView = consoleView;
        Connection connection = conexion.getConnection();
        this.estudianteDAO = new EstudianteDAO(connection);
    }

    public void agregarEstudiante(EstudianteModel datos){

        try {
            estudianteDAO.agregarEstudiante(datos);
            consoleView.showMessage("200");
            consoleView.showMessage("Datos insertados");
        }catch (SQLException e){
            consoleView.showMessage("404");
            consoleView.errorMessage("Datos no insertados" + e);
        }
    }

    public void actualizarEstudiante (EstudianteModel datos){

        try {
           estudianteDAO.actualizarEstudiante(datos);
            consoleView.showMessage("200");
            consoleView.showMessage("Datos insertados");
        }catch (SQLException e){
            consoleView.showMessage("404");
            consoleView.errorMessage("Datos no insertados");
            consoleView.errorMessage(e.toString());
        }
    }

    public void borrarEstudiante (int id){
        try {
            estudianteDAO.borrarEstudiante(id);
            consoleView.showMessage("200");
            consoleView.showMessage("Datos Borrados");
        }catch (SQLException e){
            consoleView.showMessage("404");
            consoleView.errorMessage("Datos no Borrados");
            consoleView.errorMessage(e.toString());
        }
    }

    public EstudianteModel buscarEstudianteIdentificacion (int identificacion) {
        EstudianteModel datos=new EstudianteModel();
        try {
            datos = estudianteDAO.buscarEstudianteIdentificacion(identificacion);
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
        return datos=null;
    }

        public List<EstudianteModel> buscarEstudiantes() {
        List<EstudianteModel> lista =null;
        try {
                lista = estudianteDAO.listaEstudiantes();
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

