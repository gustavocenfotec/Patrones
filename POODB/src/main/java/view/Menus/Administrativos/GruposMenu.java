package view.Menus.Administrativos;

import Validaciones.ValidacionObjeto;
import controller.Administrativos.GrupoController;
import model.Administrativos.GrupoModel;
import view.ConsoleView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class GruposMenu {
    private List<GrupoModel> grupos;
    private GrupoController grupoController;
    private ConsoleView consoleView;

    public GruposMenu() {
        this.grupos = grupos;
        this.grupoController = grupoController;
        this.consoleView = consoleView;
    }

    public void inicializacionDeValores() {
        consoleView = new ConsoleView();
        grupoController = new GrupoController(consoleView);
        grupos=grupoController.buscarGrupos();
    }

    public void mostrarOpciones() throws IOException {
        boolean menu = true;
        while (menu) {
            System.out.println("Bienvenido al Menu de Grupos");
            inicializacionDeValores();
            System.out.println("Lista de Grupos");
            for (int i = 0; i < grupos.size(); i++) {
                GrupoModel grupoModel=grupos.get(i);
                repuestaVisual(grupoModel);
                System.out.println("");
            }
            System.out.println("");
            System.out.println("");
            System.out.println("Opciones de Menu de Grupos");
            System.out.println("");
            System.out.println("1.Ingresar nuevo Grupo al Sistema");
            System.out.println("2.Actualizar Informacion de Grupos en el Sistema");
            System.out.println("3.Borrar Informacion de Grupo en el Sistema");
            System.out.println("4.Buscar a Grupos por medio de ID");
            System.out.println("5.Salir del Menu Grupos");
            System.out.println("");
            System.out.println("Ingrese su seleccion:");


            int seleccion = revisionNumero();

            switch (seleccion) {
                case 0:
                    mostrarOpciones();
                    break;
                case 1:
                    menuAgregarGrupo();
                    break;
                case 2:
                    menuActualizarGrupo();
                    break;
                case 3:
                    menuBorrarGrupo();
                    break;
                case 4:
                    menuBusquedaGrupo();
                    break;
                default:
                    System.out.println("Gracias Por Usar el sistema de Grupos");
                    menu = false;

            }

        }
    }

    public void menuAgregarGrupo() throws IOException {
        boolean menu = true;
        while (menu) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Menu de Agregacion de Grupo en el Sistema");
            System.out.println("Por favor llene la siguiente informacion");
            System.out.println("Nombre del Grupo:");
            String nombre =reader.readLine();
            System.out.println("Descripcion breve del Grupo:");
            String descripcion =reader.readLine();
            boolean estado=true;

            ValidacionObjeto val= new ValidacionObjeto();
            boolean validar=val.validacionGrupo(nombre,descripcion);

            if(validar) {
                GrupoModel grupoAgregado=new GrupoModel(nombre, descripcion, estado);
                grupoController.agregarGrupo(grupoAgregado);
                grupos = grupoController.buscarGrupos();
                grupoAgregado = grupos.get(grupos.size() - 1);
                repuestaVisual(grupoAgregado);
                if(grupoAgregado.getNombre().equals(nombre)){
                    System.out.println("Grupo fue Correctamente inscrito");
                    repuestaVisual(grupoAgregado);
                    break;
                }
                else {
                    System.out.println("Grupo no fue Correctamente inscrito");
                    break;
                }
            }
            menu=false;



        }
    }

    public void menuActualizarGrupo() throws IOException {
        boolean menu = true;
        while (menu) {
            System.out.println("Menu de Actualizacion de Informacion de Grupo");
            System.out.println("Por favor indicar el numero de ID del Grupo");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int ID = revisionNumero();

            GrupoModel grupoActualizado;
            grupoActualizado=busquedaGrupo(ID);
            if(grupoActualizado.getId()==0){
                break;
            }

            repuestaVisual(grupoActualizado);
            System.out.println("");
            System.out.println("Por favor llene la siguiente informacion");
            System.out.println("Nombre del Grupo:");
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

                int id=grupoActualizado.getId();
                grupoActualizado=new GrupoModel(id,nombre,descripcion,estado);
                grupoController.actualizarGrupo(grupoActualizado);

                grupoActualizado=grupoController.buscarGrupoID(id);

                repuestaVisual(grupoActualizado);
                System.out.println("El grupo fue actualizado");
                break;


            }
            System.out.println("El grupo no fue actualizado");
            break;


        }
    }
//
    public void menuBorrarGrupo() throws IOException {
        boolean menu = true;
        while (menu) {
            System.out.println("Menu de Borrado de Grupo");
            System.out.println("Por favor indicar el numero de ID del Grupo dentro del Sistema");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int identificacion =revisionNumero();

            if (identificacion==0){
                System.out.println("Volver a ingresar al menu");
                break;
            }

            GrupoModel grupoActualizado;
            grupoActualizado=busquedaGrupo(identificacion);
            if(grupoActualizado.getId()==0){
                break;
            }

            System.out.println("");
            System.out.println("ES ESTE EL GRUPO QUE DESEA ELIMINAR? REPONDER CON S/N");
            String respuesta=reader.readLine();
            if(respuesta.toLowerCase().equals("s")) {
                int id = grupoActualizado.getId();
                grupoController.borrarGrupo(id);
                repuestaVisual(grupoActualizado);
                System.out.println("\n"+"FUE ELIMINADO DEL SISTEMA");
                menu=false;
            }
            else {
                System.out.println("No se elimino al grupo y se sale del menu de Elminiacion");
                menu=false;
            }


        }
    }

    public void menuBusquedaGrupo() throws IOException {
        boolean menu = true;
        while (menu) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Menu de Busqueda de Grupos");
            System.out.println("Lista de Grupos");
            for (int i = 0; i < grupos.size(); i++) {
                GrupoModel grupoModel=grupos.get(i);
                repuestaVisual(grupoModel);
                System.out.println("");
            }
            System.out.println("");
            System.out.println("");
            System.out.println("Si desea se puede realizar la busqueda por medio de ID del grupo, RESPONDER S/N");
            String respuesta=reader.readLine().toLowerCase();
            if(respuesta.equals("s")) {
                System.out.println("Por favor ingresar el numero de ID del Grupo:");
                int ide = revisionNumero();
                GrupoModel grupoActualizado;
                grupoActualizado=busquedaGrupo(ide);
                if(grupoActualizado.getId()==0){
                    break;
                }
                menu=false;
            }
            else {
                System.out.println("Gracias por Utilizar el Sistema de Busqueda");
                menu=false;
            }


        }
    }

    public void repuestaVisual(GrupoModel grupoModel){
        consoleView.showMessage("Grupo: id: " + grupoModel.getId() + ", nombre: " + grupoModel.getNombre() + ", descripcion: " + grupoModel.getDescripcion() + ",estado: " + grupoModel.isEstado());
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


    public GrupoModel busquedaGrupo(int id) {
        GrupoModel grupoModel;

        try {
            grupoModel = grupoController.buscarGrupoID(id);
            repuestaVisual(grupoModel);
            return grupoModel;

        } catch (Exception e) {
            System.out.println("\nDicho grupo no se encuentra en el sistema, revisar la identificacion\n");

            grupoModel = new GrupoModel();
            return grupoModel;
        }
    }

}