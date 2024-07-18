package Personas;


import DAO.Personal.EstudianteDAO;
import controller.Personas.EstudiantesController;
import model.Personas.EstudianteModel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import view.ConsoleView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;

public class testEstudiantesController {
    @Mock
    private EstudianteDAO mockEstudianteDAO;

    @Mock
    private ConsoleView mockView;

    @InjectMocks
    private EstudiantesController estudiantesController;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testbuscarEstudiantes() throws SQLException {
        List<EstudianteModel> estudiantes = new ArrayList<>();

        when(mockEstudianteDAO.listaEstudiantes()).thenReturn(estudiantes);

        estudiantesController.buscarEstudiantes();

        //Esto seria como simular que tenemos un 200
        // como respuesta buena desde un api

        verify(mockView).showMessage("200");

    }


    @Test

    public void testagregarEstudiante() throws SQLException {
        String nombre="PruebaNombre";
        int identificacion=3333333;
        String email="prueba@gmail.com";
        boolean estado=true;
        String nacimiento="1988-09-29";
        java.sql.Date fecha_nacimiento= java.sql.Date.valueOf(nacimiento);


        EstudianteModel estudiante = new EstudianteModel(nombre,identificacion,email,estado,fecha_nacimiento);

        doNothing().when(mockEstudianteDAO).agregarEstudiante(any(EstudianteModel.class));

        estudiantesController.agregarEstudiante(estudiante);

        //Esto seria como simular que tenemos un 200
        // como respuesta buena desde un api
        verify(mockView).showMessage("200");
    }

    @Test

    public void testbuscarEstudianteIdentificacion() throws SQLException {
        int identificacion = 20;
        EstudianteModel estudianteModel=new EstudianteModel();
        doReturn(estudianteModel).when(mockEstudianteDAO).buscarEstudianteIdentificacion(identificacion);

        estudiantesController.buscarEstudianteIdentificacion(identificacion);

        //Esto seria como simular que tenemos un 200
        // como respuesta buena desde un api

        verify(mockView).showMessage("404");
    }
/*
    Estudiante: id: 1, nombre: Gustavo Alvarez, identificacion: 114141888,email: alvarez@hotmail.com, estado: true,fecha de nacimiento: 2000-10-10

    Estudiante: id: 5, nombre: Roberto Brenes, identificacion: 999999999,email: roberto@myspace.com, estado: true,fecha de nacimiento: 1980-02-25

    Estudiante: id: 6, nombre: Carlos Mora, identificacion: 2138999,email: carlos94@gmail.com, estado: true,fecha de nacimiento: 1994-03-03

    Estudiante: id: 7, nombre: Olga Leiton, identificacion: 115810321,email: olgaleiton@gmail.com, estado: true,fecha de nacimiento: 1994-08-15

    Estudiante: id: 8, nombre: Jose Francisco, identificacion: 113880888,email: jose@gmail.com, estado: true,fecha de nacimiento: 1980-12-02

 */
    @Test
    public void testactualizarEstudiante () throws SQLException {
        int id=9;
        String nombre="CambioNombre";
        int identificacion=3333333;
        String email="prueba@gmail.com";
        boolean estado=true;
        String nacimiento="1988-09-29";
        java.sql.Date fecha_nacimiento= java.sql.Date.valueOf(nacimiento);

        EstudianteModel estudiante = new EstudianteModel(id,nombre,identificacion,email,estado,fecha_nacimiento);


        doNothing().when(mockEstudianteDAO).actualizarEstudiante(any(EstudianteModel.class));

        estudiantesController.actualizarEstudiante(estudiante);

        //Esto seria como simular que tenemos un 200
        // como respuesta buena desde un api

        verify(mockView).showMessage("200");
    }
    /*
Estudiante: id: 1, nombre: Gustavo Alvarez, identificacion: 114141888,email: alvarez@hotmail.com, estado: true,fecha de nacimiento: 2000-10-10

Estudiante: id: 5, nombre: Roberto Brenes, identificacion: 999999999,email: roberto@myspace.com, estado: true,fecha de nacimiento: 1980-02-25

Estudiante: id: 6, nombre: Carlos Mora, identificacion: 2138999,email: carlos94@gmail.com, estado: true,fecha de nacimiento: 1994-03-03

Estudiante: id: 7, nombre: Olga Leiton, identificacion: 115810321,email: olgaleiton@gmail.com, estado: true,fecha de nacimiento: 1994-08-15

Estudiante: id: 8, nombre: Jose Francisco, identificacion: 113880888,email: jose@gmail.com, estado: true,fecha de nacimiento: 1980-12-02

Estudiante: id: 9, nombre: PruebaNombre, identificacion: 3333333,email: prueba@gmail.com, estado: true,fecha de nacimiento: 1988-09-29
     */
    @Test
    public void testborrarEstudiante() throws SQLException {
        int id=9;
        doNothing().when(mockEstudianteDAO).borrarEstudiante(id);

        estudiantesController.borrarEstudiante(id);

        //Esto seria como simular que tenemos un 200
        // como respuesta buena desde un api

        verify(mockView).showMessage("200");
    }
/*
    Estudiante: id: 1, nombre: Gustavo Alvarez, identificacion: 114141888,email: alvarez@hotmail.com, estado: true,fecha de nacimiento: 2000-10-10

    Estudiante: id: 5, nombre: Roberto Brenes, identificacion: 999999999,email: roberto@myspace.com, estado: true,fecha de nacimiento: 1980-02-25

    Estudiante: id: 6, nombre: Carlos Mora, identificacion: 2138999,email: carlos94@gmail.com, estado: true,fecha de nacimiento: 1994-03-03

    Estudiante: id: 7, nombre: Olga Leiton, identificacion: 115810321,email: olgaleiton@gmail.com, estado: true,fecha de nacimiento: 1994-08-15

    Estudiante: id: 8, nombre: Jose Francisco, identificacion: 113880888,email: jose@gmail.com, estado: true,fecha de nacimiento: 1980-12-02

 */

}
