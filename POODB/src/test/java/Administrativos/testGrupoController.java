package Administrativos;

import DAO.Administrativos.GrupoDAO;
import controller.Administrativos.GrupoController;
import model.Administrativos.GrupoModel;
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


public class testGrupoController {
    @Mock
    private GrupoDAO mockGrupoDAO;

    @Mock
    private ConsoleView mockView;

    @InjectMocks
    private GrupoController grupoController;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testbuscarGrupos() throws SQLException {
        List<GrupoModel> grupos = new ArrayList<>();

        when(mockGrupoDAO.listaGrupos()).thenReturn(grupos);

        grupoController.buscarGrupos();

        verify(mockView).showMessage("200");

    }


    @Test

    public void testaagregarGrupo() throws SQLException {
        String nombre = "NombrePrueba";
        String descripcion="DescripcionPrueba";
        boolean estado = true;
        GrupoModel grupo = new GrupoModel(nombre,descripcion,estado);

        doNothing().when(mockGrupoDAO).agregarGrupo(any(GrupoModel.class));

        grupoController.agregarGrupo(grupo);

        //Esto seria como simular que tenemos un 200
        // como respuesta buena desde un api
        verify(mockView).showMessage("200");
    }

    @Test

    public void testabuscarGrupoID() throws SQLException {
        int id = 3;
        GrupoModel grupo=new GrupoModel();
        doReturn(grupo).when(mockGrupoDAO).buscarGrupoId(id);

        grupoController.buscarGrupoID(id);

        //Esto seria como simular que tenemos un 200
        // como respuesta buena desde un api

        verify(mockView).showMessage("200");
    }
    @Test
    public void testactualizarGrupo () throws SQLException {
        int id=7;
        String nombre = "Seccion 6-1";
        String descripcion="Sexto";
        boolean estado = true;
        GrupoModel grupo = new GrupoModel(id,nombre,descripcion,estado);

        doNothing().when(mockGrupoDAO).actualizarGrupo(any(GrupoModel.class));

        grupoController.actualizarGrupo(grupo);

        //Esto seria como simular que tenemos un 200
        // como respuesta buena desde un api

        verify(mockView).showMessage("200");
    }
    @Test
    public void testborrarGrupo () throws SQLException {
        int id=9;
        doNothing().when(mockGrupoDAO).borrarGrupo(id);

        grupoController.borrarGrupo(id);

        //Esto seria como simular que tenemos un 200
        // como respuesta buena desde un api

        verify(mockView).showMessage("200");
    }

//    LISTA DE IDS POSIBLES
//    Grupo: id: 3, nombre: Seccion 3-0, descripcion: Chicos de tercer grado,estado: true
//
//    Grupo: id: 4, nombre: Seccion 4-1, descripcion: Chicos de 10 annos,estado: true
//
//    Grupo: id: 5, nombre: Seccion 5-1, descripcion: Chicos de 11 anos,estado: false

//    Grupo: id: 7, nombre: NombrePrueba, descripcion: DescripcionPrueba,estado: true
//
//    Grupo: id: 8, nombre: NombrePrueba, descripcion: DescripcionPrueba,estado: true
//
//    Grupo: id: 9, nombre: NombrePrueba, descripcion: DescripcionPrueba,estado: true




}
