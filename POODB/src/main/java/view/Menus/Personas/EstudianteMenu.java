package view.Menus.Personas;

import Validaciones.ValidacionObjeto;
import controller.Personas.EstudiantesController;

import model.Personas.EstudianteModel;

import view.ConsoleView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.util.List;

public class EstudianteMenu {

    private List<EstudianteModel> estudiantes;
    private EstudiantesController estudiantesController;
    private ConsoleView consoleView;


    public EstudianteMenu() {
        this.estudiantes = estudiantes;
        this.estudiantesController = estudiantesController;
        this.consoleView = consoleView;
    }

    public void inicializacionDeValores() {
        consoleView = new ConsoleView();
        estudiantesController = new EstudiantesController(consoleView);
        estudiantes=estudiantesController.buscarEstudiantes();
    }

    public void mostrarOpciones() throws IOException {
        boolean menu = true;
        while (menu) {
            System.out.println("Bienvenido al Menu de Estudiantes");
            inicializacionDeValores();
            System.out.println("Lista de Estudiantes");
            for (int i = 0; i < estudiantes.size(); i++) {
                EstudianteModel estudianteModel=estudiantes.get(i);
                repuestaVisual(estudianteModel);
                System.out.println("");
            }
            System.out.println("");
            System.out.println("");
            System.out.println("Opciones de Menu de Estudiantes");
            System.out.println("");
            System.out.println("1.Ingresar nuevo Estudiante al Sistema");
            System.out.println("2.Actualizar Informacion de Estudiante en el Sistema");
            System.out.println("3.Borrar Informacion de Estudiante en el Sistema");
            System.out.println("4.Buscar a Estudiante por medio de Identificacion");
            System.out.println("5.Salir del Menu Estudiante");
            System.out.println("");
            System.out.println("Ingrese su seleccion:");

            int seleccion=revisionNumero();
            switch (seleccion) {
                case 0:
                    mostrarOpciones();
                    break;
                case 1:
                    menuAgregarEstudiante();
                    break;
                case 2:
                    menuActualizarEstudiante();
                    break;
                case 3:
                    menuBorrarEstudiante();
                    break;
                case 4:
                    menuBusquedaEstudiante();
                    break;
                default:
                    System.out.println("Gracias Por Usar el sistema de Estudiantes");
                    menu = false;



            }

        }
    }

    public void menuAgregarEstudiante() throws IOException {
        boolean menu = true;
        while (menu) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Menu de Agregacion de Estudiantes en el Sistema");
            System.out.println("Por favor llene la siguiente informacion");
            System.out.println("Nombre del Estudiante:");
            String nombre =reader.readLine();
            System.out.println("Numero de Identificacion:");
            int identificacion=revisionNumero();
            System.out.println("Correo Electronico:");
            String email =reader.readLine();
            System.out.println("Anno de nacimiento:");
            String anno =reader.readLine();
            System.out.println("Mes de nacimiento:");
            String mes =reader.readLine();
            System.out.println("Dia de nacimiento:");
            String dia =reader.readLine();

            ValidacionObjeto val= new ValidacionObjeto();
            boolean validar=val.validacionEstudiante(nombre,identificacion,email,anno,mes,dia);

            String nacimiento=(anno+"-"+mes+"-"+dia);
            boolean estado=true;

            if(validar){
                Date fecha_nacimiento=Date.valueOf(nacimiento);
                EstudianteModel estudianteAgregado=new EstudianteModel(nombre,identificacion,email,estado,fecha_nacimiento);
                estudiantesController.agregarEstudiante(estudianteAgregado);
                estudianteAgregado=estudiantesController.buscarEstudianteIdentificacion(identificacion);
                if(estudianteAgregado==null){
                    repuestaVisual(estudianteAgregado);
                }
                else {
                    repuestaVisual(estudianteAgregado);
                }
            }
            menu = false;
        }
    }

    public void menuActualizarEstudiante() throws IOException {
        boolean menu = true;
        while (menu) {
            System.out.println("Menu de Actualizacion de Informacion de Estudiantes");
            System.out.println("Por favor indicar el numero de identificacion del Estudiante");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int identificacion = revisionNumero();
            if(identificacion==0){
                System.out.println("Solo se aceptan numeros");
                System.out.println("Se sale del sistema");
                break;

            }

            EstudianteModel estudianteActualizado;
            estudianteActualizado=revisionEstudiante(identificacion);
            if(estudianteActualizado.getId()==0){
                break;
            }

            System.out.println("");
            System.out.println("Por favor llene la siguiente informacion");
            System.out.println("Nombre del Estudiante:");
            String nombre =reader.readLine();
            System.out.println("Numero de Identificacion Poner de Nuevo la Identificacion si es la misma:");
            int identificacionNueva = revisionNumero();
            System.out.println("Correo Electronico:");
            String email =reader.readLine();
            System.out.println("Anno de nacimiento:");
            String anno =reader.readLine();
            System.out.println("Mes de nacimiento:");
            String mes =reader.readLine();
            System.out.println("Dia de nacimiento:");
            String dia =reader.readLine();
            String nacimiento=(anno+"-"+mes+"-"+dia);

            ValidacionObjeto val= new ValidacionObjeto();
            boolean validar=val.validacionEstudiante(nombre,identificacionNueva,email,anno,mes,dia);
            boolean estado=true;

            if(validar){
                Date fecha_nacimiento=Date.valueOf(nacimiento);
                int id=estudianteActualizado.getId();
                estudianteActualizado=new EstudianteModel(id,nombre,identificacion,email,estado,fecha_nacimiento);
                estudiantesController.actualizarEstudiante(estudianteActualizado);
                estudianteActualizado=estudiantesController.buscarEstudianteIdentificacion(identificacionNueva);

                repuestaVisual(estudianteActualizado);
                System.out.println("\nEl estudiante fue actualizado");
                menu=false;
        }
            else {
                System.out.println("\nEl estudiante no fue actualizado");
                menu=false;
            }



        }
    }

    public void menuBorrarEstudiante() throws IOException {
        boolean menu = true;
        while (menu) {
            System.out.println("Menu de Borrado de Estudiantes");
            System.out.println("Por favor indicar el numero de identificacion del Estudiante dentro del Sistema");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int identificacion = revisionNumero();
            if(identificacion==0){
                System.out.println("Solo se aceptan numeros");
                System.out.println("Se sale del sistema");
                break;

            }

            EstudianteModel estudianteActualizado;
            estudianteActualizado=revisionEstudiante(identificacion);
            if(estudianteActualizado.getId()==0){
            break;
            }
            System.out.println("");
            System.out.println("ES ESTE EL ESTUDIANTE QUE DESEA ELIMINAR? REPONDER CON S/N");
            String respuesta=reader.readLine();
            if(respuesta.toLowerCase().equals("s")) {
                int id = estudianteActualizado.getId();
                estudiantesController.borrarEstudiante(id);
                repuestaVisual(estudianteActualizado);
                System.out.println("FUE ELIMINADO DEL SISTEMA");
                menu=false;
            }
            else {
                System.out.println("No se elimino al estudiante y se sale del menu de Elminiacion");
                menu=false;
            }


        }
    }

    public void menuBusquedaEstudiante() throws IOException {
        boolean menu = true;
        while (menu) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("\nMenu de Busqueda de Estudiantes");
            System.out.println("\nLista de Estudiantes");
            for (int i = 0; i < estudiantes.size(); i++) {
                EstudianteModel estudianteModel=estudiantes.get(i);
                repuestaVisual(estudianteModel);
                System.out.println("");
            }
            System.out.println("");
            System.out.println("");
            System.out.println("Si desea se puede realizar la busqueda por medio de Identificacion, RESPONDER S/N");
            String respuesta=reader.readLine().toLowerCase();
            if(respuesta.equals("s")) {
                System.out.println("Por favor ingresar el numero de identificacion del Estudiante:");
                int identificacion =revisionNumero();
                if(identificacion==0){
                    System.out.println("Solo se aceptan numeros");
                    System.out.println("Se sale del menu");
                    break;
                }

                EstudianteModel estudianteBuscado;
                estudianteBuscado=revisionEstudiante(identificacion);
                if(estudianteBuscado.getId()==0){
                    break;
                }
            }
            else {
                System.out.println("\nGracias por Utilizar el Sistema de Busqueda");
                menu=false;
            }


        }
    }

    public void repuestaVisual(EstudianteModel estudianteModel){
        System.out.printf("\nEstudiante: id: " + estudianteModel.getId() + ", nombre: " + estudianteModel.getNombre() + ", identificacion: " + estudianteModel.getIdentificacion() + ",email: " + estudianteModel.getEmail() + ", estado: " + estudianteModel.isEstado() + ",fecha de nacimiento: " + estudianteModel.getFecha_nacimiento());
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


    public EstudianteModel revisionEstudiante(int identificacion) {
      EstudianteModel estudianteModel;

        try {
            estudianteModel=estudiantesController.buscarEstudianteIdentificacion(identificacion);
            repuestaVisual(estudianteModel);
            return estudianteModel;

        }
        catch (Exception e){
            System.out.println("\nDicho estudiante no se encuentra en el sistema revisar la identificacion\n");

        estudianteModel=new EstudianteModel();
            return estudianteModel;
        }




    }


}
