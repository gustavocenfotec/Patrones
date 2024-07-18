package view.Menus.Personas;

import Validaciones.ValidacionObjeto;
import controller.Personas.ProfesorController;
import model.Personas.ProfesorModel;
import view.ConsoleView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.sql.Date;
import java.util.List;

public class ProfesorMenu {

    private List<ProfesorModel> profesores;
    private ProfesorController profesorController;
    private ConsoleView consoleView;

    public ProfesorMenu() {
        this.profesores = profesores;
        this.profesorController = profesorController;
        this.consoleView = consoleView;
    }

    public void inicializacionDeValores() {
        consoleView = new ConsoleView();
        profesorController = new ProfesorController(consoleView);
        profesores=profesorController.buscarProfesores();
    }

    public void mostrarOpciones() throws IOException {
        boolean menu = true;
        while (menu) {
            System.out.println("\nBienvenido al Menu de Profesores");
            inicializacionDeValores();
            System.out.println("Lista de Profesores");
            for (int i = 0; i < profesores.size(); i++) {
                repuestaVisual(profesores.get(i));
            }
            System.out.println("");
            System.out.println("");
            System.out.println("Opciones de Menu de Profesores");
            System.out.println("");
            System.out.println("1.Ingresar nuevo Profesor al Sistema");
            System.out.println("2.Actualizar Informacion de Profesor en el Sistema");
            System.out.println("3.Borrar Informacion de Profesor en el Sistema");
            System.out.println("4.Buscar a Profesor por medio de Identificacion");
            System.out.println("5.Salir del Menu Profesor");
            System.out.println("");
            System.out.println("Ingrese su seleccion:");


            int seleccion = revisionNumero();

            switch (seleccion) {
                case 0:
                    mostrarOpciones();
                    break;
                case 1:
                   menuAgregarProfesor();
                    break;
                case 2:
                    menuActualizarProfesor();
                    break;
                case 3:
                    menuBorrarProfesor();
                    break;
                case 4:
                    menuBusquedaProfesor();
                    break;
                default:
                    System.out.println("Gracias Por Usar el sistema de Profesores");
                    menu = false;

            }

        }
    }

    public void menuAgregarProfesor() throws IOException {
        boolean menu = true;
        while (menu) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Menu de Agregacion de Profesor en el Sistema");
            System.out.println("Por favor llene la siguiente informacion");
            System.out.println("Nombre del Docente:");
            String nombre =reader.readLine();
            System.out.println("Numero de Identificacion:");
            int identificacion = revisionNumero();
            System.out.println("Correo Electronico:");
            String email =reader.readLine();
            System.out.println("Departamento en que Labora:");
            String departamento =reader.readLine();
            boolean estado=true;

            ValidacionObjeto val= new ValidacionObjeto();
            boolean validar=val.validacionProfesor(nombre,identificacion,email,departamento);

            if(validar){
                ProfesorModel profesorAgregado=new ProfesorModel(nombre,identificacion,email,estado,departamento);
                profesorController.agregarProfesor(profesorAgregado);
                profesorAgregado=profesorController.buscarProfesorIdentificacion(identificacion);
                if(profesorAgregado==null){
                    repuestaVisual(profesorAgregado);
                    break;
                }
                else {
                    repuestaVisual(profesorAgregado);
                    System.out.println("El docente fue inscrito");
                    break;
                }

            }
            System.out.println("El docente no fue inscrito");
            menu=false;

        }
    }

    public void menuActualizarProfesor() throws IOException {
        boolean menu = true;
        while (menu) {
            System.out.println("Menu de Actualizacion de Informacion de Profesor");
            System.out.println("Por favor indicar el numero de identificacion del Docente");

            int identificacion = revisionNumero();
            if (identificacion == 0) {
                System.out.println("Solo se aceptan numeros");
                System.out.println("Se sale del sistema");
                break;

            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            ProfesorModel profesorActualizado;
            profesorActualizado = busquedaProfesor(identificacion);
            if (profesorActualizado.getId() == 0) {
                System.out.println("Profesor no encontrado");
                break;
            }

            System.out.printf("Profesor: id: " + profesorActualizado.getId() + ", nombre: " + profesorActualizado.getNombre() + ", identificacion: " + profesorActualizado.getIdentificacion() + ",email: " + profesorActualizado.getEmail() + ", estado: " + profesorActualizado.isEstado() + ",departamento: " + profesorActualizado.getDepartamento());
            System.out.println("");
            System.out.println("Por favor llene la siguiente informacion");
            System.out.println("Nombre del Docente:");
            String nombre = reader.readLine();
            System.out.println("Numero de Identificacion Poner de Nuevo la Identificacion si es la misma:");
            int identificacionNueva = revisionNumero();
            System.out.println("Correo Electronico:");
            String email = reader.readLine();
            System.out.println("Departamento en que Labora:");
            String departamento = reader.readLine();


            boolean estado = true;
            ValidacionObjeto val = new ValidacionObjeto();
            boolean validar = val.validacionProfesor(nombre, identificacionNueva, email, departamento);

            if (validar) {

                int id = profesorActualizado.getId();

                profesorActualizado=new ProfesorModel(id, nombre, identificacionNueva, email, estado, departamento);

                profesorController.actualizarProfesor(profesorActualizado);

                profesorActualizado = profesorController.buscarProfesorIdentificacion(identificacionNueva);

                repuestaVisual(profesorActualizado);

                System.out.println("El docente fue actualizado");
               break;


            }
            System.out.println("El docente no fue actualizado");
            menu = false;
        }
    }

    public void menuBorrarProfesor() throws IOException {
        boolean menu = true;
        while (menu) {
            System.out.println("Menu de Borrado de Profesor");
            System.out.println("Por favor indicar el numero de identificacion del Docente dentro del Sistema");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            int identificacion = revisionNumero();

            ProfesorModel profesorActualizado;
            profesorActualizado=busquedaProfesor(identificacion);
            if(profesorActualizado.getId()==0){
                break;
            }

            System.out.println("");
            System.out.println("ES ESTE EL DOCENTE QUE DESEA ELIMINAR? REPONDER CON S/N");
            String respuesta=reader.readLine();
            if(respuesta.toLowerCase().equals("s")) {
                int id = profesorActualizado.getId();
                profesorController.borrarProfesor(id);
                repuestaVisual(profesorActualizado);
                System.out.println("FUE ELIMINADO DEL SISTEMA");
                menu=false;
            }
            else {
                System.out.println("No se elimino al docente y se sale del menu de Elminiacion");
                menu=false;
            }


        }
    }

    public void menuBusquedaProfesor() throws IOException {
        boolean menu = true;
        while (menu) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Menu de Busqueda de Profesores");
            System.out.println("Lista de Profesores");
            for (int i = 0; i < profesores.size(); i++) {
                repuestaVisual(profesores.get(i));            }
            System.out.println("");
            System.out.println("");
            System.out.println("Si desea se puede realizar la busqueda por medio de Identificacion, RESPONDER S/N");
            String respuesta=reader.readLine().toLowerCase();
            if(respuesta.equals("s")) {
                System.out.println("Por favor ingresar el numero de identificacion del Profesor:");
                int identificacion = revisionNumero();
                if(identificacion==0){
                    System.out.println("Solo se aceptan numeros");
                    System.out.println("Se sale del sistema");
                    break;

                }

                ProfesorModel profesorActualizado;
                profesorActualizado=busquedaProfesor(identificacion);
                if(profesorActualizado.getId()==0){
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

    public void repuestaVisual(ProfesorModel profesorModel){
        System.out.printf("\nProfesor: id: " + profesorModel.getId() + ", nombre: " + profesorModel.getNombre() + ", identificacion: " + profesorModel.getIdentificacion() + ",email: " + profesorModel.getEmail() + ", estado: " + profesorModel.isEstado() + ",departamento: " + profesorModel.getDepartamento());
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


    public ProfesorModel busquedaProfesor(int identificacion) {
        ProfesorModel profesorModel;

        try {
            profesorModel=profesorController.buscarProfesorIdentificacion(identificacion);
            repuestaVisual(profesorModel);
            return profesorModel;

        }
        catch (Exception e){
            System.out.println("\nDicho profesor no se encuentra en el sistema, revisar la identificacion\n");

            profesorModel=new ProfesorModel();
            return profesorModel;
        }




    }



}
