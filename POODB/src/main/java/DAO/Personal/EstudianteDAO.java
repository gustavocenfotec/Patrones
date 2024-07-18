package DAO.Personal;

import model.Personas.EstudianteModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstudianteDAO{
/*
Este clase es el Data Access Objet, que es encargado de realizar el CRUD de la tabla Estudiate_gjac,
dependendiento de que se realiza se puede observar que al momento de cambio o agregacion
se corre con el ejecutable y cuando se hacen consultas se utiliza un resultSet para poder adquirir la informacion
 */
    private Connection connection;

    public EstudianteDAO(Connection connection) {
        this.connection = connection;
    }

    public void agregarEstudiante (EstudianteModel objeto) throws SQLException {
        String query = "INSERT INTO `Estudiate_gjac`( `nombre`,`identificacion`, `email`,`fecha_nacimiento`,`estado`) VALUES ( ?,?,?,?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setString(1, objeto.getNombre());
            stmt.setInt(2, objeto.getIdentificacion());
            stmt.setString(3, objeto.getEmail());
            stmt.setDate(4, objeto.getFecha_nacimiento());
            stmt.setBoolean(5, objeto.isEstado());
            stmt.executeUpdate();
        }
    }

    public void actualizarEstudiante (EstudianteModel objeto) throws SQLException {
        String query = "UPDATE `Estudiate_gjac` SET `id`=?, `nombre`=?,`identificacion`=?,`email`=?,`fecha_nacimiento`=?,`estado`=? WHERE `id`=?";

        try (PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setInt(1, objeto.getId());
            stmt.setString(2, objeto.getNombre());
            stmt.setInt(3, objeto.getIdentificacion());
            stmt.setString(4, objeto.getEmail());
            stmt.setDate(5, objeto.getFecha_nacimiento());
            stmt.setBoolean(6, objeto.isEstado());
            stmt.setInt(7, objeto.getId());

            stmt.executeUpdate();
        }
    }

    public void borrarEstudiante(int id) throws SQLException {
        String query = "DELETE FROM `Estudiate_gjac` WHERE id=(?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public EstudianteModel buscarEstudianteIdentificacion(int identificacion) throws SQLException {
        String query = "SELECT `id`, `nombre`, `identificacion`, `email`, `fecha_nacimiento`, `estado` FROM `Estudiate_gjac` WHERE identificacion=(?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setInt(1, identificacion);
            ResultSet rs=stmt.executeQuery();
            EstudianteModel estudianteEncontrado= new EstudianteModel();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                int identificacions = rs.getInt("identificacion");
                String email = rs.getString("email");
                Date fecha_nacimiento=rs.getDate("fecha_nacimiento");
                boolean estado=rs.getBoolean("estado");
                estudianteEncontrado.setId(id);
                estudianteEncontrado.setNombre(nombre);
                estudianteEncontrado.setIdentificacion(identificacions);
                estudianteEncontrado.setEmail(email);
                estudianteEncontrado.setFecha_nacimiento(fecha_nacimiento);
                estudianteEncontrado.setEstado(estado);

            }
            return estudianteEncontrado;
        }
    }

    public  List<EstudianteModel> listaEstudiantes() throws SQLException {
        List<EstudianteModel> lista = new ArrayList<>();

        String query = "SELECT * FROM `Estudiate_gjac`";
        try (PreparedStatement stmt = connection.prepareStatement(query)){
            ResultSet rs=stmt.executeQuery();
            while (rs.next()) {
                EstudianteModel estudianteEncontrado= new EstudianteModel();
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                int identificacions = rs.getInt("identificacion");
                String email = rs.getString("email");
                Date fecha_nacimiento=rs.getDate("fecha_nacimiento");
                boolean estado=rs.getBoolean("estado");
                estudianteEncontrado.setId(id);
                estudianteEncontrado.setNombre(nombre);
                estudianteEncontrado.setIdentificacion(identificacions);
                estudianteEncontrado.setEmail(email);
                estudianteEncontrado.setFecha_nacimiento(fecha_nacimiento);
                estudianteEncontrado.setEstado(estado);

                lista.add(estudianteEncontrado);


            }
            return lista;
        }
    }

}
