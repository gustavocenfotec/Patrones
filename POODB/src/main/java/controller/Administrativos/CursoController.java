package controller.Administrativos;

import DAO.Administrativos.CursoDAO;
import model.Administrativos.CursoModel;
import model.DataSource.conexion;
import view.ConsoleView;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CursoController{

    private ConsoleView consoleView;

    private CursoDAO cursoDAO;



     public CursoController(ConsoleView consoleView ){
         this.consoleView = consoleView;
         Connection connection = conexion.getConnection();
         this.cursoDAO = new CursoDAO(connection);
     }

     /*
     *
     * @param datos, recibe el objeto para ser agregado al sistema
     * */

    public void agregarCurso (CursoModel datos){

         try {
             cursoDAO.agregarCurso(datos);
             consoleView.showMessage("200");
             consoleView.showMessage("Datos insertados");
         }catch (SQLException e){
             consoleView.showMessage("500");
             consoleView.errorMessage("Datos no insertados");
         }
    }

    public void actualizarCurso (CursoModel datos){


        try {
            cursoDAO.actualizarCurso(datos);
            consoleView.showMessage("200");
            consoleView.showMessage("Datos insertados");
        }catch (SQLException e){
            consoleView.showMessage("500");
            consoleView.errorMessage("Datos no insertados");
            consoleView.errorMessage(e.toString());
        }
    }

    public void borrarCurso (int id){
        try {
            cursoDAO.borrarCurso(id);
            consoleView.showMessage("200");
            consoleView.showMessage("Datos Borrados");
        }catch (SQLException e){
            consoleView.showMessage("500");
            consoleView.errorMessage("Datos no Borrados");
            consoleView.errorMessage(e.toString());
        }
    }

    public CursoModel buscarCursoID (int id) {
        CursoModel datos= null;
         try {
            datos = cursoDAO.buscarCursoId(id);

            if (datos.getNombre() == null) {
                consoleView.showMessage("404");

                return datos;
            } else {
                consoleView.showMessage("200");
                return datos;
            }

        } catch (SQLException e) {
             consoleView.showMessage("500");
            consoleView.errorMessage(e.toString());
             return datos;
        }
//        return datos;
    }

    public List<CursoModel> listarCursos() {
        List<CursoModel> lista =null;
        try {
            lista = cursoDAO.listaCursos();
            consoleView.showMessage("200");
            return lista;

        } catch (SQLException e) {
            consoleView.showMessage("500");
            consoleView.errorMessage(e.toString());
        }
        consoleView.showMessage("404");
        return lista;
    }




}
