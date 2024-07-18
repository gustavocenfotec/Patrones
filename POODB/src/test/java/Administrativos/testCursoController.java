package Administrativos;

import DAO.Administrativos.CursoDAO;
import controller.Administrativos.CursoController;
import model.Administrativos.CursoModel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import view.ConsoleView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
public class testCursoController {
    @Mock
    private CursoDAO mockCursoDAO;

    @Mock
    private ConsoleView mockView;

    @InjectMocks
    private CursoController cursoController;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testlistarCursos() throws SQLException {
        List<CursoModel> cursos = new ArrayList<>();

        when(mockCursoDAO.listaCursos()).thenReturn(cursos);

        cursoController.listarCursos();

        //Esto seria como simular que tenemos un 200
        // como respuesta buena desde un api

        verify(mockView).showMessage("200");

    }


    @Test

    public void testagregarCurso() throws SQLException {
        String nombre = "Prueba";
        String descripcion="Prueba";
        boolean estado = true;
        CursoModel curso = new CursoModel(nombre,descripcion,estado);

        doNothing().when(mockCursoDAO).agregarCurso(any(CursoModel.class));

        cursoController.agregarCurso(curso);

        //Esto seria como simular que tenemos un 200
        // como respuesta buena desde un api
        verify(mockView).showMessage("200");
    }

    @Test

    public void testbuscarCursoID() throws SQLException {
        int id = 20;
        CursoModel grupo=new CursoModel();
        doReturn(grupo).when(mockCursoDAO).buscarCursoId(id);

        cursoController.buscarCursoID(id);

        //Esto seria como simular que tenemos un 200
        // como respuesta buena desde un api

        verify(mockView).showMessage("404");
    }

//    Curso: id: 1, nombre: Ciencias, descripcion: Clase sobre ciencias,estado: true
//    Curso: id: 3, nombre: Matematicas, descripcion: Matematicas Locas,estado: true
//    Curso: id: 5, nombre: Estudios Sociales, descripcion: Historia,estado: true
//    Curso: id: 6, nombre: Civica, descripcion: Civil de Costa Rica,estado: true
    @Test
    public void testactualizarCurso () throws SQLException {
        int id=7;
        String nombre = "Bioligia";
        String descripcion="Ciencias Naturales";
        boolean estado = true;
        CursoModel curso = new CursoModel(id,nombre,descripcion,estado);

        doNothing().when(mockCursoDAO).actualizarCurso(any(CursoModel.class));

        cursoController.actualizarCurso(curso);

        //Esto seria como simular que tenemos un 200
        // como respuesta buena desde un api

        verify(mockView).showMessage("200");
    }
//    Curso: id: 1, nombre: Ciencias, descripcion: Clase sobre ciencias,estado: true
//    Curso: id: 3, nombre: Matematicas, descripcion: Matematicas Locas,estado: true
//    Curso: id: 5, nombre: Estudios Sociales, descripcion: Historia,estado: true
//    Curso: id: 6, nombre: Civica, descripcion: Civil de Costa Rica,estado: true
//    Curso: id: 7, nombre: Prueba, descripcion: Prueba,estado: true
    @Test
    public void testborrarCurso() throws SQLException {
        int id=1;
        doNothing().when(mockCursoDAO).borrarCurso(id);

        cursoController.borrarCurso(id);

        //Esto seria como simular que tenemos un 200
        // como respuesta buena desde un api

        verify(mockView).showMessage("200");
    }
    //    Curso: id: 1, nombre: Ciencias, descripcion: Clase sobre ciencias,estado: true
//    Curso: id: 3, nombre: Matematicas, descripcion: Matematicas Locas,estado: true
//    Curso: id: 5, nombre: Estudios Sociales, descripcion: Historia,estado: true
//    Curso: id: 6, nombre: Civica, descripcion: Civil de Costa Rica,estado: true


}
