package controller.Administrativos;


import DAO.Administrativos.GrupoDAO;
import model.DataSource.conexion;
import model.Administrativos.GrupoModel;
import view.ConsoleView;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class GrupoController {

    private ConsoleView consoleView;

    private GrupoDAO grupoDAO;

    public GrupoController(ConsoleView consoleView ){
        this.consoleView = consoleView;
        Connection connection = conexion.getConnection();
        this.grupoDAO = new GrupoDAO(connection);
    }

    public void agregarGrupo ( GrupoModel datos){
        try {
            grupoDAO.agregarGrupo(datos);
            consoleView.showMessage("200");
            consoleView.showMessage("Datos insertados");
        }catch (SQLException e){
            consoleView.showMessage("500");
            consoleView.errorMessage("Datos no insertados");
        }
    }

    public void actualizarGrupo (GrupoModel datos){

        try {
            grupoDAO.actualizarGrupo(datos);
            consoleView.showMessage("200");
            consoleView.showMessage("Datos insertados");
        }catch (SQLException e){
            consoleView.showMessage("500");
            consoleView.errorMessage("Datos no insertados");
            consoleView.errorMessage(e.toString());
        }
    }

    public void borrarGrupo (int id){
        try {
            grupoDAO.borrarGrupo(id);
            consoleView.showMessage("200");
            consoleView.showMessage("Datos Borrados");
        }catch (SQLException e){
            consoleView.showMessage("500");
            consoleView.errorMessage("Datos no Borrados");
            consoleView.errorMessage(e.toString());
        }
    }

    public GrupoModel buscarGrupoID (int id) {
        GrupoModel datos= null;
        try {
            datos = grupoDAO.buscarGrupoId(id);
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
        }
        return datos;
    }

    public List<GrupoModel> buscarGrupos() {
        List<GrupoModel> lista =null;
        try {
            lista = grupoDAO.listaGrupos();
            consoleView.showMessage("200");
            return lista;

        } catch (SQLException e) {
            consoleView.errorMessage(e.toString());
            consoleView.showMessage("500");
            return lista;
        }
    }
}
