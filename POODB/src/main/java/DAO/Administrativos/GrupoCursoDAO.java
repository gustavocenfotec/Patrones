package DAO.Administrativos;

import model.Administrativos.GrupoCurso;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GrupoCursoDAO {
    /*
Este clase es el Data Access Objet, que es encargado de realizar el CRUD de la tabla grupo_curso_gjac,
dependendiento de que se realiza se puede observar que al momento de cambio o agregacion
se corre con el ejecutable y cuando se hacen consultas se utiliza un resultSet para poder adquirir la informacion
 */

    private Connection connection;

    public GrupoCursoDAO(Connection connection) {
        this.connection = connection;
    }

    public void agregarGrupoCurso (GrupoCurso objeto) throws SQLException {
        String query = "INSERT INTO `grupo_curso_gjac`( `grupo_id`,`curso_id`) VALUES ( ?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setInt(1, objeto.getGrupo_id());
            stmt.setInt(2, objeto.getCurso_id());
            stmt.executeUpdate();
        }
    }

    public void actualizarGrupoCurso(GrupoCurso objeto) throws SQLException {
        String query = "UPDATE `grupo_curso_gjac` SET `id`=?, `grupo_id`=?,`curso_id`=? WHERE `id`=?";

        try (PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setInt(1, objeto.getId());
            stmt.setInt(2, objeto.getGrupo_id());
            stmt.setInt(3, objeto.getCurso_id());
            stmt.setInt(4, objeto.getId());
            stmt.executeUpdate();
        }
    }

    public void borrarGrupoCurso(int id) throws SQLException {
        String query = "DELETE FROM `grupo_curso_gjac` WHERE id=(?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public GrupoCurso buscarGrupoId(int id) throws SQLException {
        String query = "SELECT `id`, `grupo_id`, `curso_id` FROM `grupo_curso_gjac` WHERE id =(?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setInt(1, id);
            ResultSet rs=stmt.executeQuery();
            GrupoCurso grupoCursoEncontrado= new GrupoCurso();
            while (rs.next()) {
                int ide = rs.getInt("id");
                int grupo_id = rs.getInt("grupo_id");
                int curso_id = rs.getInt("curso_id");
                grupoCursoEncontrado.setId(ide);
                grupoCursoEncontrado.setGrupo_id(grupo_id);
                grupoCursoEncontrado.setCurso_id(curso_id);
            }
            return grupoCursoEncontrado;
        }
    }

    public List<GrupoCurso> listaGruposCursos() throws SQLException {
        List<GrupoCurso> lista = new ArrayList<>();

        String query = "SELECT * FROM `grupo_curso_gjac`";
        try (PreparedStatement stmt = connection.prepareStatement(query)){
            ResultSet rs=stmt.executeQuery();
            while (rs.next()) {
                GrupoCurso grupoCursoEncontrado= new GrupoCurso();
                int ide = rs.getInt("id");
                int grupo_id = rs.getInt("grupo_id");
                int curso_id = rs.getInt("curso_id");
                grupoCursoEncontrado.setId(ide);
                grupoCursoEncontrado.setGrupo_id(grupo_id);
                grupoCursoEncontrado.setCurso_id(curso_id);
                lista.add(grupoCursoEncontrado);
            }
            return lista;
        }
    }
}
