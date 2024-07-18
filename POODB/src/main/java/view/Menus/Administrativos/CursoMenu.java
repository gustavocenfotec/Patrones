package view.Menus.Administrativos;

import Validaciones.ValidacionObjeto;
import controller.Administrativos.CursoController;
import model.Administrativos.CursoModel;
import view.ConsoleView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class CursoMenu {
    private List<CursoModel> cursos;
    private CursoController cursoController;
    private ConsoleView consoleView;

    public CursoMenu() {
        this.cursos = cursos;
        this.cursoController = cursoController;
        this.consoleView = consoleView;
    }

    public void inicializacionDeValores() {
        consoleView = new ConsoleView();
        cursoController = new CursoController(consoleView);
        cursos=cursoController.listarCursos();
    }

    public void mostrarOpciones() throws IOException {
        boolean menu = true;
        while (menu) {
            System.out.println("Bienvenido al Menu de Cursos");
            inicializacionDeValores();
            System.out.println("Lista de Cursos");
            for (int i = 0; i < cursos.size(); i++) {
                CursoModel cursoModel=cursos.get(i);
                repuestaVisual(cursoModel);
                System.out.println("");
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


            int seleccion =revisionNumero();

            switch (seleccion) {
                case 0:
                   mostrarOpciones();
                    break;
                case 1:
                    menuAgregarCurso();
                    break;
                case 2:
                    menuActualizarCurso();
                    break;
                case 3:
                    menuBorrarCurso();
                    break;
                case 4:
                    menuBusquedaCurso();
                    break;
                default:
                    System.out.println("Gracias Por Usar el sistema de Cursos");
                    menu = false;



            }

        }
    }

    public void menuAgregarCurso() throws IOException {
        boolean menu = true;
        while (menu) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Menu de Agregacion de Curso en el Sistema");
            System.out.println("Por favor llene la siguiente informacion");
            System.out.println("Nombre del Curso:");
            String nombre =reader.readLine();
            System.out.println("Descripcion breve del Curso:");
            String descripcion =reader.readLine();
            boolean estado=true;

            ValidacionObjeto val= new ValidacionObjeto();
            boolean validar=val.validacionCurso(nombre,descripcion);

            if(validar) {

                CursoModel cursoAgregado=new CursoModel(nombre,descripcion,estado);

                cursoController.agregarCurso(cursoAgregado);
                cursos=cursoController.listarCursos();
                cursoAgregado=cursos.get(cursos.size() - 1);


                if(cursoAgregado.getNombre().equals(nombre)){
                    System.out.println("Curso fue Correctamente inscrito");
                    repuestaVisual(cursoAgregado);
                    break;
                }
                else {
                    System.out.println("Curso no fue Correctamente inscrito");
                    break;
                }
            }
            menu=false;


        }
    }

    public void menuActualizarCurso() throws IOException {
        boolean menu = true;
        while (menu) {
            System.out.println("\nMenu de Actualizacion de Informacion de Curso");
            System.out.println("Por favor indicar el numero de ID del Curso");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int ID = revisionNumero();

            CursoModel cursoActualizado;
            cursoActualizado=busquedaCurso(ID);
            if(cursoActualizado.getId()==0){
                break;
            }

            System.out.println("");
            System.out.println("Por favor llene la siguiente informacion");
            System.out.println("Nombre del Curso:");
            String nombre =reader.readLine();
            System.out.println("Descripcion: ");
            String descripcion =reader.readLine();
            System.out.println("Estado A/D:");
            String respuesta =reader.readLine();
            boolean estado=true;
            if (respuesta.toLowerCase().equals("d")){
                estado=false;
            }

            ValidacionObjeto val = new ValidacionObjeto();
            boolean validar = val.validacionGrupo(nombre, descripcion);


            if (validar) {

                int ide=cursoActualizado.getId();
                cursoActualizado=new CursoModel(ide,nombre,descripcion,estado);

                cursoController.actualizarCurso(cursoActualizado);

                cursoActualizado=cursoController.buscarCursoID(ide);

                System.out.println("El Curso fue actualizado\n");
                repuestaVisual(cursoActualizado);
                break;
            }
            System.out.println("El curso no fue actualizado");
           break;

        }
    }
    //
    public void menuBorrarCurso() throws IOException {
        boolean menu = true;
        while (menu) {
            System.out.println("Menu de Borrado de Curso");
            System.out.println("Por favor indicar el numero de ID del Curso dentro del Sistema");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


            int ide = revisionNumero();

            if (ide==0){
                System.out.println("Volver a ingresar al menu");
                break;
            }

            CursoModel cursoActualizado;
            cursoActualizado=busquedaCurso(ide);
            if(cursoActualizado.getId()==0){
                break;
            }

            System.out.println("");
            System.out.println("ES ESTE EL CURSO QUE DESEA ELIMINAR? REPONDER CON S/N");
            String respuesta=reader.readLine();
            if(respuesta.toLowerCase().equals("s")) {
                int id = cursoActualizado.getId();
                cursoController.borrarCurso(id);
                repuestaVisual(cursoActualizado);
                System.out.println("\n"+"FUE ELIMINADO DEL SISTEMA");
                menu=false;
            }
            else {
                System.out.println("No se elimino al curso y se sale del menu de Elminiacion");
                menu=false;
            }


        }
    }

    public void menuBusquedaCurso() throws IOException {
        boolean menu = true;
        while (menu) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Menu de Busqueda de Cursos");
            System.out.println("Lista de Cursos");
            for (int i = 0; i < cursos.size(); i++) {
                CursoModel cursoModel=cursos.get(i);
                repuestaVisual(cursoModel);
                System.out.println("");
            }
            System.out.println("");
            System.out.println("");
            System.out.println("Si desea se puede realizar la busqueda por medio de ID del Curso, RESPONDER S/N");
            String respuesta=reader.readLine().toLowerCase();
            if(respuesta.equals("s")) {
                System.out.println("Por favor ingresar el numero de ID del Curso:");
                int ide = revisionNumero();

                CursoModel cursoEncontrado;
                cursoEncontrado=busquedaCurso(ide);
                if(cursoEncontrado.getId()==0){
                    break;
                }
                menu=false;
            }
            else {
                System.out.println("Gracias por Utilizar el Sistema de Busqueda\n");
                menu=false;
            }


        }
    }

    public void repuestaVisual(CursoModel cursoModel){
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


    public CursoModel busquedaCurso(int id) {
        CursoModel cursoModel;

        try {
            cursoModel = cursoController.buscarCursoID(id);
            repuestaVisual(cursoModel);
            return cursoModel;

        } catch (Exception e) {
            System.out.println("\nDicho Curso no se encuentra en el sistema, revisar la identificacion\n");

            cursoModel = new CursoModel();
            return cursoModel;
        }
    }
}
