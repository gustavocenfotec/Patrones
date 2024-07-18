package Personas;



import DAO.Personal.ProfesorDAO;
import controller.Personas.ProfesorController;

import model.Personas.EstudianteModel;
import model.Personas.ProfesorModel;
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

public class testProfesoresController {

    @Mock
    private ProfesorDAO mockProfesorDAO;

    @Mock
    private ConsoleView mockView;

    @InjectMocks
    private ProfesorController profesorController;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testbuscarProfesores() throws SQLException {
        List<ProfesorModel> profesores = new ArrayList<>();

        when(mockProfesorDAO.listaProfesores()).thenReturn(profesores);

        profesorController.buscarProfesores();

        //Esto seria como simular que tenemos un 200
        // como respuesta buena desde un api

        verify(mockView).showMessage("200");

    }


    @Test

    public void testagregarProfesor() throws SQLException {
        String nombre="PruebaNombre";
        int identificacion=3333333;
        String email="prueba@gmail.com";
        boolean estado=true;
        String departamento="Ciencias";


        ProfesorModel profesorModel = new ProfesorModel(nombre,identificacion,email,estado,departamento);

        doNothing().when(mockProfesorDAO).agregarProfesor(any(ProfesorModel.class));

        profesorController.agregarProfesor(profesorModel);

        //Esto seria como simular que tenemos un 200
        // como respuesta buena desde un api
        verify(mockView).showMessage("200");
    }

    @Test

    public void testbuscarProfesorIdentificacion() throws SQLException {
        int identificacion = 8888888;
        ProfesorModel profesorModel=new ProfesorModel();
        doReturn(profesorModel).when(mockProfesorDAO).buscarProfesorIdentificacion(identificacion);

        profesorController.buscarProfesorIdentificacion(identificacion);

        //Esto seria como simular que tenemos un 200
        // como respuesta buena desde un api

        verify(mockView).showMessage("200");
    }
    /*

    Profesor: id: 1, nombre: Gustavo Carlotos, identificacion: 3333333,email: alvarez@hotmail.com, estado: true,departamento: Ciencias
    Profesor: id: 3, nombre: Gustavo Restrepo, identificacion: 8888888,email: restrepo@gmail.com, estado: true,departamento: Ciencias
    Profesor: id: 6, nombre: PruebaNombre, identificacion: 3333333,email: prueba@gmail.com, estado: true,departamento: Ciencias

     */
    @Test
    public void testactualizarProfesor () throws SQLException {
        int id=6;
        String nombre="CambioNombre";
        int identificacion=6666666;
        String email="prueba@gmail.com";
        boolean estado=true;
        String departamento="Estudios Sociales";


        ProfesorModel profesorModel = new ProfesorModel(id,nombre,identificacion,email,estado,departamento);


        doNothing().when(mockProfesorDAO).actualizarProfesor(any(ProfesorModel.class));

        profesorController.actualizarProfesor(profesorModel);

        //Esto seria como simular que tenemos un 200
        // como respuesta buena desde un api

        verify(mockView).showMessage("200");
    }
    /*

    Profesor: id: 1, nombre: Gustavo Carlotos, identificacion: 3333333,email: alvarez@hotmail.com, estado: true,departamento: Ciencias
    Profesor: id: 3, nombre: Gustavo Restrepo, identificacion: 8888888,email: restrepo@gmail.com, estado: true,departamento: Ciencias
    Profesor: id: 6, nombre: PruebaNombre, identificacion: 6666666,email: prueba@gmail.com, estado: true,departamento: Ciencias
    */
    @Test
    public void testborrarProfesor() throws SQLException {
        int id=7;
        doNothing().when(mockProfesorDAO).borrarProfesor(id);

        profesorController.borrarProfesor(id);

        //Esto seria como simular que tenemos un 200
        // como respuesta buena desde un api

        verify(mockView).showMessage("200");
    }
/*

    Profesor: id: 1, nombre: Gustavo Carlotos, identificacion: 3333333,email: alvarez@hotmail.com, estado: true,departamento: Ciencias
    Profesor: id: 3, nombre: Gustavo Restrepo, identificacion: 8888888,email: restrepo@gmail.com, estado: true,departamento: Ciencias
    Profesor: id: 6, nombre: PruebaNombre, identificacion: 6666666,email: prueba@gmail.com, estado: true,departamento: Ciencias


 */
}
