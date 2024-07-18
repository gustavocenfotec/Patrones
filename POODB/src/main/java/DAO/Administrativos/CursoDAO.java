package DAO.Administrativos;

import model.Administrativos.CursoModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
Este clase es el Data Access Objet, que es encargado de realizar el CRUD de la tabla curso_gjac,
dependendiento de que se realiza se puede observar que al momento de cambio o agregacion
se corre con el ejecutable y cuando se hacen consultas se utiliza un resultSet para poder adquirir la informacion
 */
public class CursoDAO {

    private Connection connection;

    public CursoDAO(Connection connection) {
        this.connection = connection;
    }

    public void agregarCurso (CursoModel objeto) throws SQLException {
        String query = "INSERT INTO `curso_gjac`( `nombre`,`descripcion`, `estado`) VALUES ( ?,?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setString(1, objeto.getNombre());
            stmt.setString(2, objeto.getDescripcion());
            stmt.setBoolean(3, objeto.isEstado());
            stmt.executeUpdate();
        }
    }

    public void actualizarCurso (CursoModel objeto) throws SQLException {
        String query = "UPDATE `curso_gjac` SET `id`=?, `nombre`=?,`descripcion`=?,`estado`=? WHERE `id`=?";

        try (PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setInt(1, objeto.getId());
            stmt.setString(2, objeto.getNombre());
            stmt.setString(3, objeto.getDescripcion());
            stmt.setBoolean(4, objeto.isEstado());
            stmt.setInt(5, objeto.getId());

            stmt.executeUpdate();
        }
    }

    public void borrarCurso (int id) throws SQLException {
        String query = "DELETE FROM `curso_gjac` WHERE id=(?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public CursoModel buscarCursoId(int id) throws SQLException {
        CursoModel cursoEncontrado= new CursoModel();
        String query = "SELECT `id`, `nombre`, `descripcion`, `estado` FROM `curso_gjac` WHERE id =(?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setInt(1, id);
            ResultSet rs=stmt.executeQuery();

            while (rs.next()) {
                int ide = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                boolean estado=rs.getBoolean("estado");
                cursoEncontrado.setId(ide);
                cursoEncontrado.setNombre(nombre);
                cursoEncontrado.setDescripcion(descripcion);
                cursoEncontrado.setEstado(estado);

            }
            return cursoEncontrado;
        }
    }

    public List<CursoModel> listaCursos() throws SQLException {
        List<CursoModel> lista = new ArrayList<>();

        String query = "SELECT * FROM `curso_gjac`";
        try (PreparedStatement stmt = connection.prepareStatement(query)){
            ResultSet rs=stmt.executeQuery();
            while (rs.next()) {
                CursoModel cursoModelEncontrado= new CursoModel();
                int ide = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                boolean estado=rs.getBoolean("estado");
                cursoModelEncontrado.setId(ide);
                cursoModelEncontrado.setNombre(nombre);
                cursoModelEncontrado.setDescripcion(descripcion);
                cursoModelEncontrado.setEstado(estado);
                lista.add(cursoModelEncontrado);
            }
            return lista;
        }
    }



}


