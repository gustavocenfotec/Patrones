package DAO.Administrativos;


import model.Administrativos.GrupoModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
Este clase es el Data Access Objet, que es encargado de realizar el CRUD de la tabla grupo_gjac,
dependendiento de que se realiza se puede observar que al momento de cambio o agregacion
se corre con el ejecutable y cuando se hacen consultas se utiliza un resultSet para poder adquirir la informacion
 */
public class GrupoDAO {
    private Connection connection;

    public GrupoDAO(Connection connection) {
        this.connection = connection;
    }

    public void agregarGrupo (GrupoModel objeto) throws SQLException {
        String query = "INSERT INTO `grupo_gjac`( `nombre`,`descripcion`, `estado`) VALUES ( ?,?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setString(1, objeto.getNombre());
            stmt.setString(2, objeto.getDescripcion());
            stmt.setBoolean(3, objeto.isEstado());
            stmt.executeUpdate();
        }
    }

    public void actualizarGrupo (GrupoModel objeto) throws SQLException {
        String query = "UPDATE `grupo_gjac` SET `id`=?, `nombre`=?,`descripcion`=?,`estado`=? WHERE `id`=?";

        try (PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setInt(1, objeto.getId());
            stmt.setString(2, objeto.getNombre());
            stmt.setString(3, objeto.getDescripcion());
            stmt.setBoolean(4, objeto.isEstado());
            stmt.setInt(5, objeto.getId());

            stmt.executeUpdate();
        }
    }

    public void borrarGrupo(int id) throws SQLException {
        String query = "DELETE FROM `grupo_gjac` WHERE id=(?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public GrupoModel buscarGrupoId(int id) throws SQLException {
        String query = "SELECT `id`, `nombre`, `descripcion`, `estado` FROM `grupo_gjac` WHERE id =(?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setInt(1, id);
            ResultSet rs=stmt.executeQuery();
            GrupoModel grupoEncontrado= new GrupoModel();
            while (rs.next()) {
                int ide = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                boolean estado=rs.getBoolean("estado");
                grupoEncontrado.setId(id);
                grupoEncontrado.setNombre(nombre);
                grupoEncontrado.setDescripcion(descripcion);
                grupoEncontrado.setEstado(estado);

            }
            return grupoEncontrado;
        }
    }

    public List<GrupoModel> listaGrupos() throws SQLException {
        List<GrupoModel> lista = new ArrayList<>();

        String query = "SELECT * FROM `grupo_gjac`";
        try (PreparedStatement stmt = connection.prepareStatement(query)){
            ResultSet rs=stmt.executeQuery();
            while (rs.next()) {
                GrupoModel grupoEncontrado= new GrupoModel();
                int ide = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                boolean estado=rs.getBoolean("estado");
                grupoEncontrado.setId(ide);
                grupoEncontrado.setNombre(nombre);
                grupoEncontrado.setDescripcion(descripcion);
                grupoEncontrado.setEstado(estado);
                lista.add(grupoEncontrado);
            }

            return lista;
        }
    }
}
