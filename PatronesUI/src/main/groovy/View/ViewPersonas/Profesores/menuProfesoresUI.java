package View.ViewPersonas.Profesores;

import View.Presentacion.MenuGeneralUI;


import controller.Personas.ProfesorController;
import model.Personas.ProfesorModel;
import view.ConsoleView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class menuProfesoresUI extends JFrame{
    private ProfesorModel profesorModel;

    private ProfesorController profesorController;
    private JPanel menuProfesoresUI;
    private JTextField nombre;
    private JTextField identificacion;
    private JTextField email;
    private JTextField departamento;
    private JButton crearButton;
    private JButton modificarButton;
    private JButton eliminarButton;
    private JButton buscarButton;
    private JButton salirButton;
    private JTable table1;
    private int dialogButton;

    public menuProfesoresUI(int seleccion) {

        /*
        TO DO
        Realizar Validaciones para la captacion de Informacion;
         */
        this.profesorController=new ProfesorController(new ConsoleView());
        setContentPane(menuProfesoresUI);
        setTitle("Menu General Profesores");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 400);
        setLocationRelativeTo(null);
        setVisible(true);
        esconderElementos(seleccion);
        crearTabla();



        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new profesorOpciones();
            }
        });



        crearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreEscrito=nombre.getText() ;
                int identificacionEscrita=Integer.parseInt(identificacion.getText());
                String correoEscrito=email.getText();
                String departamentoEscrito=departamento.getText();
                boolean estado=true;

                ProfesorModel profesorAgregado=new ProfesorModel(nombreEscrito,identificacionEscrita,correoEscrito,estado,departamentoEscrito);
                profesorController.agregarProfesor(profesorAgregado);
                crearTabla();
            }
        });



        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                String nombreEscrito=nombre.getText() ;
                int identificacionEscrita=Integer.parseInt(identificacion.getText());
                String correoEscrito=email.getText();
                String departamentoEscrito=departamento.getText();
                boolean estado=true;


                ProfesorModel profesorActualizado;
                profesorActualizado=profesorController.buscarProfesorIdentificacion(identificacionEscrita);
                if(profesorActualizado.getId()==0){
                    JOptionPane.showMessageDialog(menuProfesoresUI.this,"El profesor con id:"+identificacionEscrita+" no existe en el sistema");
                }
                else{
                    int id=profesorActualizado.getId();
                    profesorActualizado=new ProfesorModel(id,nombreEscrito,identificacionEscrita,correoEscrito,estado,departamentoEscrito);
                    profesorController.actualizarProfesor(profesorActualizado);
                    profesorActualizado=profesorController.buscarProfesorIdentificacion(identificacionEscrita);
                    JOptionPane.showMessageDialog(menuProfesoresUI.this,"El profesor con identificacion:"+profesorActualizado.getIdentificacion()+" fue actualizado en el Sistema");
                    crearTablaBusqueda(profesorActualizado);
                }

            }
        });
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int identificacionEscrita=Integer.parseInt(identificacion.getText());

                ProfesorModel profesorBorrado;
                profesorBorrado=profesorController.buscarProfesorIdentificacion(identificacionEscrita);
                if(profesorBorrado.getId()==0){
                    JOptionPane.showMessageDialog(menuProfesoresUI.this,"El profesor con id:"+identificacionEscrita+" no existe en el sistema");
                }
                else{

                    int dialogResult = JOptionPane.showConfirmDialog (menuProfesoresUI.this, "Esta seguro que desea borra al profesor:"+ profesorBorrado.getNombre()+" identificacion: "+profesorBorrado.getIdentificacion(),"Ventana Borrado",dialogButton);
                    if(dialogResult == JOptionPane.YES_OPTION){
                        int id = profesorBorrado.getId();
                        profesorController.borrarProfesor(id);
                        JOptionPane.showMessageDialog(menuProfesoresUI.this,"El profesor con identificacion:"+profesorBorrado.getIdentificacion()+" fue borrado en el Sistema");
                        crearTabla();

                    } else{
                        JOptionPane.showMessageDialog(menuProfesoresUI.this,"El profesor con identificacion:"+profesorBorrado.getIdentificacion()+" no fue borrado en el Sistema");
                        new profesorOpciones();

                    }


                }
            }
        });


        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int identificacionEscrita=Integer.parseInt(identificacion.getText());

                ProfesorModel profesorBuscado;
                profesorBuscado=profesorController.buscarProfesorIdentificacion(identificacionEscrita);
                if(profesorBuscado.getId()==0){
                    JOptionPane.showMessageDialog(menuProfesoresUI.this,"El profesor con id:"+identificacionEscrita+" no existe en el sistema");
                }
                else{
                    JOptionPane.showMessageDialog(menuProfesoresUI.this,"El profesor con id:"+identificacionEscrita+" fue encontrado en el sistema");
                    crearTablaBusqueda(profesorBuscado);

                }
            }
        });
    }




    public void esconderElementos(int seleccion) {
        switch (seleccion) {
            case 1:
                modificarButton.setVisible(false);
                eliminarButton.setVisible(false);
                buscarButton.setVisible(false);
                break;
            case 2:
                crearButton.setVisible(false);
                eliminarButton.setVisible(false);
                buscarButton.setVisible(false);
                break;
            case 3:
                crearButton.setVisible(false);
                modificarButton.setVisible(false);
                buscarButton.setVisible(false);
                nombre.setVisible(false);
                email.setVisible(false);
                departamento.setVisible(false);
                break;
            default:
                crearButton.setVisible(false);
                modificarButton.setVisible(false);
                eliminarButton.setVisible(false);
                nombre.setVisible(false);
                email.setVisible(false);
                departamento.setVisible(false);


        }
    }







    public void crearTabla(){
        List<ProfesorModel> lista3 = profesorController.buscarProfesores();


        int c = lista3.size();
        System.out.println(c);

        Object[][] row=crearFilasTabla(c,lista3);
//        int id, String nombre, int identificacion, String email, boolean estado, departamento

        String[] column = {"ID", "Nombre", "Identificacion", "Email", "Estado","Departamento"};

        table1.setModel(new DefaultTableModel(row, column));
    }

    public void crearTablaBusqueda(ProfesorModel profesorModel){
        List<ProfesorModel> lista3=new ArrayList<>();
        lista3.add(profesorModel);


        int c = lista3.size();
        System.out.println(c);

        Object[][] row=crearFilasTabla(c,lista3);

//        int id, String nombre, int identificacion, String email, boolean estado, String departamento
        String[] column = {"ID", "Nombre", "Identificacion", "Email", "Estado","Departamento"};

        table1.setModel(new DefaultTableModel(row, column));
    }

    public Object[][] crearFilasTabla(int c,List<ProfesorModel> lista3){
        Object[][] data = new Object[c][9];

        int value = 1;
        for (int i = 0; i < c; i++) {
            for (int j = 0; j < 9; j++) {
                if(j==0){
                    data[i][j] = lista3.get(i).getId();
                    value++;}
                else if (j==1) {
                    data[i][j] = lista3.get(i).getNombre();
                    value++;}
                else if (j==2) {
                    data[i][j] = lista3.get(i).getIdentificacion();
                    value++;}
                else if (j==3) {
                    data[i][j] = lista3.get(i).getEmail();
                    value++;}
                else if (j==4) {
                    data[i][j] = lista3.get(i).isEstado()? "activo":"desactivado";
                    value++;}
                else if (j==5) {
                    data[i][j] = lista3.get(i).getDepartamento();
                    value++;}
            }
        }
        return data;
    }
}
