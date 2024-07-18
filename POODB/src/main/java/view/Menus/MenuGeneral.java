package view.Menus;
import view.Menus.Administrativos.CursoMenu;
import view.Menus.Administrativos.GruposCursosMenu;
import view.Menus.Administrativos.GruposMenu;
import view.Menus.Personas.EstudianteMenu;
import view.Menus.Personas.ProfesorMenu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MenuGeneral {


    public MenuGeneral() {


    }


    public void visualMenu() throws IOException {
        boolean menu=true;
        while(menu){

            System.out.println("Menu General y Eleccion de Usuario");
            System.out.println("Por favor elegir en las siguientes Opciones: ");
            System.out.println("1.Sistema de CRUD Estudiantes ");
            System.out.println("2.Sistema de CRUD Profesores ");
            System.out.println("3.Sistema de CRUD Grupos ");
            System.out.println("4.Sistema de CRUD Cursos ");
            System.out.println("5.Sistema de CRUD Grupos Cursos ");
            System.out.println("6.Salir del Sistema ");
            System.out.println("Ingrese su opcion elegida: ");

            int seleccion=seleccionMenu();


            switch(seleccion) {
                case 0:
                    visualMenu();
                    break;
                case 1:
                    new EstudianteMenu().mostrarOpciones();
                    break;
                case 2:
                  new ProfesorMenu().mostrarOpciones();
                    break;
                case 3:
                    new GruposMenu().mostrarOpciones();
                    break;
                 case 4:
                    new CursoMenu().mostrarOpciones();
                    break;
                case 5:
                    new GruposCursosMenu().mostrarOpciones();
                    break;
                default:
                    System.out.println("Gracias Por Usar el sistema");
                    menu=false;
        }
    }
    }

    public int seleccionMenu() {
        int seleccion=0;

        try {
            BufferedReader reader =new BufferedReader(new InputStreamReader(System.in));
            seleccion = Integer.parseInt(reader.readLine());
            return seleccion;


        }catch (NumberFormatException e){
            System.out.println("\nPor favor elegir un numero\n");
            System.out.println(seleccion);
            return seleccion;


        }
        catch (Exception e){
            System.out.println("\nPor favor elegir un numero\n");
            System.out.println(seleccion);
            return seleccion;


        }



    }
}
