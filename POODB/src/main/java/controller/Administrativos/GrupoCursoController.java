package controller.Administrativos;


import DAO.Administrativos.GrupoCursoDAO;
import model.DataSource.conexion;
import model.Administrativos.GrupoCurso;
import view.ConsoleView;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GrupoCursoController {

    private ConsoleView consoleView;

    private GrupoCursoDAO grupoCursoDAO;

    public GrupoCursoController(ConsoleView consoleView ){
        this.consoleView = consoleView;
        Connection connection = conexion.getConnection();
        this.grupoCursoDAO = new GrupoCursoDAO(connection);
    }

    public void agregarGrupoCurso ( GrupoCurso datos){

        try {
            grupoCursoDAO.agregarGrupoCurso(datos);
            consoleView.showMessage("Datos insertados");
        }catch (SQLException e){
            consoleView.errorMessage("Datos no insertados");
        }
    }

    public void actualizarGrupoCurso (GrupoCurso datos){

        try {
            grupoCursoDAO.actualizarGrupoCurso(datos);
            consoleView.showMessage("Datos insertados");
        }catch (SQLException e){
            consoleView.errorMessage("Datos no insertados");
            consoleView.errorMessage(e.toString());
        }
    }

    public void borrarGrupoCurso (int id){
        try {
            grupoCursoDAO.borrarGrupoCurso(id);
            consoleView.showMessage("Datos Borrados");
        }catch (SQLException e){
            consoleView.errorMessage("Datos no Borrados");
            consoleView.errorMessage(e.toString());
        }
    }

    public GrupoCurso buscarGrupoCursoID (int id) {
        GrupoCurso datos=new GrupoCurso();
        try {
            datos = grupoCursoDAO.buscarGrupoId(id);
            if (datos.getId() == 0) {
//                consoleView.showMessage("Datos no Encontrados");

            } else {
//                consoleView.showMessage("Datos Encontrados");
                return datos;
            }

        } catch (SQLException e) {
//            consoleView.errorMessage("Datos no Encontrados");
            consoleView.errorMessage(e.toString());
            return datos;
        }
        return datos;
    }

    public List<GrupoCurso> buscarGruposCursos() {
        List<GrupoCurso> lista =new ArrayList<>();
        try {
            lista = grupoCursoDAO.listaGruposCursos();
//            consoleView.showMessage("Datos Encontrados");
           return lista;

        } catch (SQLException e) {
//            consoleView.errorMessage("Datos no Encontrados");
            consoleView.errorMessage(e.toString());
        }
        return lista;
    }
}
