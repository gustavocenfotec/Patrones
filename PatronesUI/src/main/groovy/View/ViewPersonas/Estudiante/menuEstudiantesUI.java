package View.ViewPersonas.Estudiante;

import controller.Personas.EstudiantesController;
import model.Personas.EstudianteModel;
import view.ConsoleView;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class menuEstudiantesUI extends JFrame{
    private EstudianteModel estudianteModel;

    private EstudiantesController estudiantesController;
    private JPanel menuEstudiantesUI;
    private JTextField nombre;
    private JTextField identificacion;
    private JTextField email;
    private JTextField anno;
    private JButton crearButton;
    private JButton modificarButton;
    private JButton eliminarButton;
    private JButton buscarButton;
    private JButton salirButton;
    private JTable table1;
    private JTextField mes;
    private JTextField dia;
    private int dialogButton;

    public menuEstudiantesUI(int seleccion) {

         /*
        TO DO
        Realizar Validaciones para la captacion de Informacion;
         */
        this.estudiantesController=new EstudiantesController(new ConsoleView());
        setContentPane(menuEstudiantesUI);
        setTitle("Menu General Estudiantes");
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
                new estudianteOpciones();
            }
        });
        crearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String nombreEscrito=nombre.getText() ;
                int identificacionEscrita=Integer.parseInt(identificacion.getText());
                String correoEscrito=email.getText();
                String annoEscrito=anno.getText();
                String mesEscrito=mes.getText();
                String diaEscrito=dia.getText();
                boolean estado=true;

                String nacimiento=(annoEscrito+"-"+mesEscrito+"-"+diaEscrito);

                Date fecha_nacimiento=Date.valueOf(nacimiento);
                EstudianteModel estudianteAgregado=new EstudianteModel(nombreEscrito,identificacionEscrita,correoEscrito,estado,fecha_nacimiento);
                estudiantesController.agregarEstudiante(estudianteAgregado);
                crearTabla();


            }
        });
        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreEscrito=nombre.getText() ;
                int identificacionEscrita=Integer.parseInt(identificacion.getText());
                String correoEscrito=email.getText();
                String annoEscrito=anno.getText();
                String mesEscrito=mes.getText();
                String diaEscrito=dia.getText();
                boolean estado=true;


                EstudianteModel estudianteActualizado;
                estudianteActualizado=estudiantesController.buscarEstudianteIdentificacion(identificacionEscrita);
                if(estudianteActualizado.getId()==0){
                    JOptionPane.showMessageDialog(menuEstudiantesUI.this,"El estudiante con id:"+identificacionEscrita+" no existe en el sistema");
                }
                else{
                    String nacimiento=(annoEscrito+"-"+mesEscrito+"-"+diaEscrito);

                    Date fecha_nacimiento=Date.valueOf(nacimiento);
                    int id=estudianteActualizado.getId();
                    estudianteActualizado=new EstudianteModel(id,nombreEscrito,identificacionEscrita,correoEscrito,estado,fecha_nacimiento);
                    estudiantesController.actualizarEstudiante(estudianteActualizado);
                    estudianteActualizado=estudiantesController.buscarEstudianteIdentificacion(identificacionEscrita);
                    JOptionPane.showMessageDialog(menuEstudiantesUI.this,"El estudiante con identificacion:"+estudianteActualizado.getIdentificacion()+" fue actualizado en el Sistema");
                    crearTabla();

                }
            }
        });
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int identificacionEscrita=Integer.parseInt(identificacion.getText());

                EstudianteModel estudianteBorrado;
                estudianteBorrado=estudiantesController.buscarEstudianteIdentificacion(identificacionEscrita);
                if(estudianteBorrado.getId()==0){
                    JOptionPane.showMessageDialog(menuEstudiantesUI.this,"El estudiante con id:"+identificacionEscrita+" no existe en el sistema");
                }
                else{

                    int dialogResult = JOptionPane.showConfirmDialog (menuEstudiantesUI.this, "Esta seguro que desea borra al estudiante:"+ estudianteBorrado.getNombre()+" identificacion: "+estudianteBorrado.getIdentificacion(),"Ventana Borrado",dialogButton);
                    if(dialogResult == JOptionPane.YES_OPTION){
                        int id = estudianteBorrado.getId();
                        estudiantesController.borrarEstudiante(id);
                        JOptionPane.showMessageDialog(menuEstudiantesUI.this,"El estudiante con identificacion:"+estudianteBorrado.getIdentificacion()+" fue borrado en el Sistema");
                        crearTabla();

                    } else{
                        JOptionPane.showMessageDialog(menuEstudiantesUI.this,"El estudiante con identificacion:"+estudianteBorrado.getIdentificacion()+" no fue borrado en el Sistema");
                        new estudianteOpciones();

                    }


                }

            }
        });
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int identificacionEscrita=Integer.parseInt(identificacion.getText());

                EstudianteModel estudianteBuscado;
                estudianteBuscado=estudiantesController.buscarEstudianteIdentificacion(identificacionEscrita);
                if(estudianteBuscado.getId()==0){
                    JOptionPane.showMessageDialog(menuEstudiantesUI.this,"El estudiante con id:"+identificacionEscrita+" no existe en el sistema");
                }
                else{
                    JOptionPane.showMessageDialog(menuEstudiantesUI.this,"El estudiante con id:"+identificacionEscrita+" fue encontrado en el sistema");
                    crearTablaBusqueda(estudianteBuscado);

                    }
                }

        });
    }

    public void esconderElementos(int seleccion){
        switch(seleccion) {
            case 1:
                modificarButton.setVisible(false);
                eliminarButton.setVisible(false);
                buscarButton.setVisible(false);
                break;
            case 2:
                crearButton.setVisible(false);
                eliminarButton.setVisible(false);
                buscarButton.setVisible(false);;
                break;
            case 3:
                crearButton.setVisible(false);
                modificarButton.setVisible(false);
                buscarButton.setVisible(false);
                nombre.setVisible(false);
                email.setVisible(false);
                anno.setVisible(false);
                mes.setVisible(false);
                dia.setVisible(false);

                break;
            default:
                crearButton.setVisible(false);
                modificarButton.setVisible(false);
                eliminarButton.setVisible(false);
                nombre.setVisible(false);
                email.setVisible(false);
                anno.setVisible(false);
                mes.setVisible(false);
                dia.setVisible(false);


        }

    }
    public void crearTabla(){
        List<EstudianteModel> lista3 = estudiantesController.buscarEstudiantes();


        int c = lista3.size();
        System.out.println(c);

        Object[][] row=crearFilasTabla(c,lista3);
//        int id, String nombre, int identificacion, String email, boolean estado, Date fecha_nacimiento

        String[] column = {"ID", "Nombre", "Identificacion", "Email", "Estado","Fecha"};

        table1.setModel(new DefaultTableModel(row, column));
    }

    public void crearTablaBusqueda(EstudianteModel estudianteModel){
        List<EstudianteModel> lista3=new ArrayList<>();
        lista3.add(estudianteModel);


        int c = lista3.size();
        System.out.println(c);

        Object[][] row=crearFilasTabla(c,lista3);
        String[] column = {"ID", "Nombre", "Identificacion", "Email", "Estado","Fecha"};

        table1.setModel(new DefaultTableModel(row, column));
    }

    public Object[][] crearFilasTabla(int c,List<EstudianteModel> lista3){
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
                    data[i][j] = lista3.get(i).getFecha_nacimiento();
                    value++;}
            }
        }
        return data;
    }
}

