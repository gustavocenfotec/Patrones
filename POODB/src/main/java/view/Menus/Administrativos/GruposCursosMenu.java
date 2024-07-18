package view.Menus.Administrativos;

import controller.Administrativos.CursoController;
import controller.Administrativos.GrupoController;
import controller.Administrativos.GrupoCursoController;
import model.Administrativos.CursoModel;
import model.Administrativos.GrupoCurso;
import model.Administrativos.GrupoModel;
import view.ConsoleView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class GruposCursosMenu {

    private List<CursoModel> cursos;
    private CursoController cursoController;
    private ConsoleView consoleView;
    private List<GrupoModel> grupos;
    private GrupoController grupoController;

    private List<GrupoCurso> gruposCursos;
    private GrupoCursoController grupoCursoController;


    public GruposCursosMenu() {
        this.cursos = cursos;
        this.cursoController = cursoController;
        this.consoleView = consoleView;
        this.grupos = grupos;
        this.grupoController = grupoController;
        this.gruposCursos = gruposCursos;
        this.grupoCursoController = grupoCursoController;
    }

    public void inicializacionDeValores() {
        consoleView = new ConsoleView();
        grupoController = new GrupoController(consoleView);
        grupos=grupoController.buscarGrupos();
        cursoController = new CursoController(consoleView);
        cursos=cursoController.listarCursos();
        grupoCursoController = new GrupoCursoController(consoleView);
        gruposCursos=grupoCursoController.buscarGruposCursos();
    }

    public void mostrarOpciones() throws IOException {
        boolean menu = true;
        while (menu) {
            System.out.println("");
            System.out.println("");
            System.out.println("\nBienvenido al Menu de Cursos y Grupos");
            inicializacionDeValores();
            System.out.println("Lista de Cursos y Grupos");
            for (int i = 0; i < gruposCursos.size(); i++) {
                GrupoCurso grupoCurso=gruposCursos.get(i);
                repuestaVisual(grupoCurso);
            }
            System.out.println("");
            System.out.println("");
            System.out.println("Opciones de Menu de Cursos");
            System.out.println("");
            System.out.println("1.Ingresar nuevo Curso al Sistema");
            System.out.println("2.Actualizar Informacion de Curso en el Sistema");
            System.out.println("3.Borrar Informacion de Curso en el Sistema");
            System.out.println("4.Buscar a Cursos por medio de ID");
            System.out.println("5.Salir del Menu Cursos");
            System.out.println("");
            System.out.println("Ingrese su seleccion:");

            int seleccion = revisionNumero();

            switch (seleccion) {
                case 0:
                   mostrarOpciones();
                    break;
                case 1:
                    menuAgregarCursoGrupo();
                    break;
                case 2:
                    menuActualizarCursoGrupo();
                    break;
                case 3:
                    menuBorrarGrupoCurso();
                    break;
                case 4:
                    menuBusquedaGruppoCurso();
                    break;
                default:
                    System.out.println("Gracias Por Usar el sistema de Grupos");
                    menu = false;



            }

        }
    }

    public void menuAgregarCursoGrupo() throws IOException {
        boolean menu = true;
        while (menu) {
            System.out.println("\nMenu de Agregacion de Curso dentro de Grupo en el Sistema");
            System.out.println("\nLista de Grupos");

            for (int i = 0; i < grupos.size(); i++) {
                GrupoModel grupoModel=grupos.get(i);
                repuestaVisualGrupos(grupoModel);
                System.out.println("");
            }


            System.out.println("\nLista de Cursos");
            for (int i = 0; i < cursos.size(); i++) {
                CursoModel cursoModel=cursos.get(i);
                repuestaVisualCurso(cursoModel);
                System.out.println("");
            }

            System.out.println("Por favor llene la siguiente informacion");
            System.out.println("Numero de ID de Curso:");
            int curso_id =revisionNumero();
            System.out.println("Numero de ID de Grupo:");
            int grupo_id =revisionNumero();

            if (curso_id==0||grupo_id==0)
            {
                System.out.println("revisar informacion puesta solo se aceptan numeros y tambien no se aceptan espacios en blanco");
                break;
            }

            GrupoCurso grupoCursoAgregado=new GrupoCurso(grupo_id,curso_id);
            grupoCursoController.agregarGrupoCurso(grupoCursoAgregado);
            gruposCursos=grupoCursoController.buscarGruposCursos();
            grupoCursoAgregado=gruposCursos.get(gruposCursos.size() - 1);


            if(grupoCursoAgregado.getCurso_id()==curso_id){
                System.out.println("Grupo Curso fue Correctamente inscrito");
                repuestaVisual(grupoCursoAgregado);
                menu=false;
            }
            else {
                System.out.println("Grupo no fue Correctamente inscrito");
                menu = false;
            }


        }
    }

    public void menuActualizarCursoGrupo() throws IOException {
        boolean menu = true;
        while (menu) {
            System.out.println("\nMenu de Actualizacion de Informacion de Grupo Curso");
            System.out.println("Por favor indicar el numero de ID unico de Combinacion Curso Grupo");
            int ID = revisionNumero();

            if(ID==0){
                System.out.println("revisar informacion puesta solo se aceptan numeros y tambien no se aceptan espacios en blanco");
                break;
            }

            GrupoCurso grupoCursoActualizado;
            grupoCursoActualizado=busquedaGrupoCurso(ID);

            if(grupoCursoActualizado.getId()==0){
                System.out.println("Se sale de menu");
                break;
            }

            repuestaVisual(grupoCursoActualizado);
            System.out.println("");
            System.out.println("\nLista de Grupos");

            for (int i = 0; i < grupos.size(); i++) {
                GrupoModel grupoModel=grupos.get(i);
                repuestaVisualGrupos(grupoModel);
                System.out.println("");
            }


            System.out.println("\nLista de Cursos");
            for (int i = 0; i < cursos.size(); i++) {
                CursoModel cursoModel=cursos.get(i);
                repuestaVisualCurso(cursoModel);
                System.out.println("");
            }
            System.out.println("Por favor llene la siguiente informacion");
            System.out.println("ID de Curso:");
            int curso_id =revisionNumero();
            System.out.println("ID del grupo ");
            int grupo_id =revisionNumero();

            int ide=grupoCursoActualizado.getId();

            if (curso_id==0||grupo_id==0)
            {
                System.out.println("revisar informacion puesta solo se aceptan numeros y tambien no se aceptan espacios en blanco");
                break;
            }

            grupoCursoActualizado=new GrupoCurso(ide,grupo_id,curso_id);

            grupoCursoController.actualizarGrupoCurso(grupoCursoActualizado);

            grupoCursoActualizado=grupoCursoController.buscarGrupoCursoID(ide);

            repuestaVisual(grupoCursoActualizado);
            System.out.println("El Curso con el Grupo fue actualizado");
            menu=false;


        }
    }

    public void menuBorrarGrupoCurso() throws IOException {
        boolean menu = true;
        while (menu) {
            System.out.println("Menu de Borrado de Grupo Curso");
            System.out.println("Por favor indicar el numero de ID unico del Grupo Curso dentro del Sistema");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


            int ide = revisionNumero();

            GrupoCurso grupocursoActualizado;
            grupocursoActualizado=busquedaGrupoCurso(ide);
            if(grupocursoActualizado.getId()==0){
                break;
            }

            repuestaVisual(grupocursoActualizado);

            System.out.println("");
            System.out.println("ES ESTE EL GRUPO CURSO QUE DESEA ELIMINAR? REPONDER CON S/N");
            String respuesta=reader.readLine();
            if(respuesta.toLowerCase().equals("s")) {
                int id = grupocursoActualizado.getId();
                grupoCursoController.borrarGrupoCurso(id);
                repuestaVisual(grupocursoActualizado);
                System.out.println("\n"+"FUE ELIMINADO DEL SISTEMA");
                menu=false;
            }
            else {
                System.out.println("No se elimino al grupoCursp y se sale del menu de Elminiacion");
                menu=false;
            }


        }
    }
//
    public void menuBusquedaGruppoCurso() throws IOException {
        boolean menu = true;
        while (menu) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("\nMenu de Cursos y Grupos");
            System.out.println("\nLista de Cursos y Grupos");
            for (int i = 0; i < gruposCursos.size(); i++) {
                GrupoCurso grupoCurso=gruposCursos.get(i);
                repuestaVisual(grupoCurso);
            }
            System.out.println("");
            System.out.println("");
            System.out.println("Si desea se puede realizar la busqueda por medio de ID unico de combinacion grupo, RESPONDER S/N");
            String respuesta=reader.readLine().toLowerCase();
            if(respuesta.equals("s")) {
                System.out.println("Por favor ingresar el numero de ID del Grupo:");
                int ide = revisionNumero();
                GrupoCurso grupocursoActualizado;
                grupocursoActualizado=busquedaGrupoCurso(ide);
                if(grupocursoActualizado.getId()==0){
                    break;
                }
                System.out.println("\nEste es la informacion solicitada\n");
                repuestaVisual(grupocursoActualizado);
                System.out.println("\nGracias por Utilizar el Sistema de Busqueda");
            }
            else {
                System.out.println("\nGracias por Utilizar el Sistema de Busqueda");
                menu=false;
            }


        }
    }

    public void repuestaVisual(GrupoCurso grupoCurso){
        CursoModel cursoModel;
        cursoModel=busquedaCurso(grupoCurso.getCurso_id());

        GrupoModel grupoModel;
        grupoModel=busquedaGrupo(grupoCurso.getGrupo_id());
        if(cursoModel.getId()==0){
            System.out.println("");
        } else if (grupoModel==null) {
            System.out.println("\n ID unico de Combinacion de CursoGrupo: "+ grupoCurso.getId() );
            System.out.println("\n      El curso con id: "+cursoModel.getId()+ ", de nombre: "+cursoModel.getNombre()+", no tiene ningun grupo en este curso" );
        }
        else if (cursoModel.getId()!=0&&grupoModel.getId()!=0) {
            System.out.println("\n ID unico de Combinacion de CursoGrupo: "+ grupoCurso.getId() );
            System.out.println("\n       El curso con id: "+cursoModel.getId()+ ", de nombre: "+cursoModel.getNombre()+", tiene el siguiente grupo:" );
            System.out.println("\n"+"        El grupo con id: "+grupoModel.getId()+ ", de nombre: "+grupoModel.getNombre()+", con la descripcion:"+grupoModel.getDescripcion());
        }

    }

    public void repuestaVisualGrupos(GrupoModel grupoModel){
        System.out.printf("Grupo: id: " + grupoModel.getId() + ", nombre: " + grupoModel.getNombre() + ", descripcion: " + grupoModel.getDescripcion() + ",estado: " + grupoModel.isEstado());
    }

    public void repuestaVisualCurso(CursoModel cursoModel){
        System.out.printf("Curso: id: " + cursoModel.getId() + ", nombre: " + cursoModel.getNombre() + ", descripcion: " + cursoModel.getDescripcion() + ",estado: " + cursoModel.isEstado());
    }

    public int revisionNumero() {
        int numero=0;

        try {
            BufferedReader reader =new BufferedReader(new InputStreamReader(System.in));
            numero = Integer.parseInt(reader.readLine());
            return numero;


        }catch (NumberFormatException e){
            System.out.println("\nPor favor ingresar un numero\n");
            return numero;


        }
        catch (Exception e){
            System.out.println("\nPor favor ingresar un numero\n");
            return numero;

        }



    }


    public GrupoCurso busquedaGrupoCurso(int id) {
        GrupoCurso grupoCurso;

        try {
            grupoCurso = grupoCursoController.buscarGrupoCursoID(id);
//            repuestaVisual(grupoCurso);
            return grupoCurso;

        } catch (Exception e) {
//            System.out.println("\nDicho Curso no se encuentra en el sistema, revisar la identificacion\n");

            grupoCurso = new GrupoCurso();
            return grupoCurso;
        }
    }

    public CursoModel busquedaCurso(int id) {
        CursoModel cursoModel;

        try {
            cursoModel = cursoController.buscarCursoID(id);
//            repuestaVisualCurso(cursoModel);
            return cursoModel;

        } catch (Exception e) {
//            System.out.println("\nDicho Curso no se encuentra en el sistema, revisar la identificacion\n");

            cursoModel = new CursoModel();
            return cursoModel;
        }
    }

    public GrupoModel busquedaGrupo(int id) {
        GrupoModel grupoModel;

        try {
            grupoModel = grupoController.buscarGrupoID(id);
//            repuestaVisualGrupos(grupoModel);
            return grupoModel;

        } catch (Exception e) {
//            System.out.println("\nDicho grupo no se encuentra en el sistema, revisar la identificacion\n");

            grupoModel = new GrupoModel();
            return grupoModel;
        }
    }
}


