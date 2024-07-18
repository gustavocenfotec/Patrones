package DAO.Personal;

import model.Personas.EstudianteModel;
import model.Personas.ProfesorModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/*
Este clase es el Data Access Objet, que es encargado de realizar el CRUD de la tabla profesores_gjac,
dependendiento de que se realiza se puede observar que al momento de cambio o agregacion
se corre con el ejecutable y cuando se hacen consultas se utiliza un resultSet para poder adquirir la informacion
 */
public class ProfesorDAO {
    private Connection connection;

    public ProfesorDAO(Connection connection) {
        this.connection = connection;
    }

    public void agregarProfesor (ProfesorModel objeto) throws SQLException {
        String query = "INSERT INTO `profesores_gjac`( `nombre`,`identificacion`, `email`,`departamento`,`estado`) VALUES ( ?,?,?,?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setString(1, objeto.getNombre());
            stmt.setInt(2, objeto.getIdentificacion());
            stmt.setString(3, objeto.getEmail());
            stmt.setString(4, objeto.getDepartamento());
            stmt.setBoolean(5, objeto.isEstado());
            stmt.executeUpdate();
        }
    }

    public void actualizarProfesor (ProfesorModel objeto) throws SQLException {
        String query = "UPDATE `profesores_gjac` SET `id`=?, `nombre`=?,`identificacion`=?,`email`=?,`departamento`=?,`estado`=? WHERE `id`=?";

        try (PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setInt(1, objeto.getId());
            stmt.setString(2, objeto.getNombre());
            stmt.setInt(3, objeto.getIdentificacion());
            stmt.setString(4, objeto.getEmail());
            stmt.setString(5, objeto.getDepartamento());
            stmt.setBoolean(6, objeto.isEstado());
            stmt.setInt(7, objeto.getId());

            stmt.executeUpdate();
        }
    }

    public void borrarProfesor(int id) throws SQLException {
        String query = "DELETE FROM `profesores_gjac` WHERE id=(?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public ProfesorModel buscarProfesorIdentificacion(int identificacion) throws SQLException {
        String query = "SELECT `id`, `nombre`, `identificacion`, `email`, `departamento`, `estado` FROM `profesores_gjac` WHERE identificacion=(?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setInt(1, identificacion);
            ResultSet rs=stmt.executeQuery();
            ProfesorModel profesorEncontrado= new ProfesorModel();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                int identificacions = rs.getInt("identificacion");
                String email = rs.getString("email");
                String departamento=rs.getString("departamento");
                boolean estado=rs.getBoolean("estado");
                profesorEncontrado.setId(id);
                profesorEncontrado.setNombre(nombre);
                profesorEncontrado.setIdentificacion(identificacions);
                profesorEncontrado.setEmail(email);
                profesorEncontrado.setDepartamento(departamento);
                profesorEncontrado.setEstado(estado);

            }
            return profesorEncontrado;
        }
    }

    public List<ProfesorModel> listaProfesores() throws SQLException {
        List<ProfesorModel> lista = new ArrayList<>();

        String query = "SELECT * FROM `profesores_gjac`";
        try (PreparedStatement stmt = connection.prepareStatement(query)){
            ResultSet rs=stmt.executeQuery();
            while (rs.next()) {
                ProfesorModel profesorEncontrado= new ProfesorModel();
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                int identificacions = rs.getInt("identificacion");
                String email = rs.getString("email");
                String departamento=rs.getString("departamento");
                boolean estado=rs.getBoolean("estado");
                profesorEncontrado.setId(id);
                profesorEncontrado.setNombre(nombre);
                profesorEncontrado.setIdentificacion(identificacions);
                profesorEncontrado.setEmail(email);
                profesorEncontrado.setDepartamento(departamento);
                profesorEncontrado.setEstado(estado);

                lista.add(profesorEncontrado);


            }
            return lista;
        }
    }
}
