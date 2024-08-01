package View.ViewsAdministrativo.Curso;

import View.Presentacion.MenuGeneralUI;


import controller.Administrativos.CursoController;
import model.Administrativos.CursoModel;
import view.ConsoleView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class menuCursosUI extends JFrame{

    private CursoModel cursoModel;

    private CursoController cursoController;
    private JPanel menuCursosUI;
    private JTextField nombre;
    private JTextField descripcion;
    private JTextField id;
    private JButton crearButton;
    private JButton modificarButton;
    private JButton eliminarButton;
    private JButton buscarButton;
    private JButton salirButton;
    private JTable table1;
    private JRadioButton activoRadioButton;
    private JRadioButton inactivoRadioButton;

    private int dialogButton;


    public menuCursosUI(int selecccion) {

        this.cursoController=new CursoController(new ConsoleView());
        setContentPane(menuCursosUI);
        setTitle("Menu General Cursos");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 400);
        setLocationRelativeTo(null);
        setVisible(true);
        esconderElementos(selecccion);
        crearTabla();



        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new cursoOpciones();
            }
        });


        crearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String nombreEscrito=nombre.getText() ;
                String descripcionEscrito=descripcion.getText();
                boolean estado=true;


                CursoModel cursoAgregado=new CursoModel(nombreEscrito,descripcionEscrito,estado);
                cursoController.agregarCurso(cursoAgregado);
                crearTabla();

            }
        });


        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String nombreEscrito=nombre.getText() ;
                String descripcionEscrito=descripcion.getText();
                boolean estado=true;
                int idEscrita=Integer.parseInt(id.getText());

                if(activoRadioButton.isSelected()){
                    estado=true;
                } else if (inactivoRadioButton.isSelected()) {
                    estado=false;
                }

                CursoModel cursoActualizado;
                cursoActualizado=cursoController.buscarCursoID(idEscrita);
                if(cursoActualizado.getId()==0){
                    JOptionPane.showMessageDialog(menuCursosUI.this,"El curso con id:"+idEscrita+" no existe en el sistema");
                }
                else{

                    cursoActualizado=new CursoModel(idEscrita,nombreEscrito,descripcionEscrito,estado);
                    cursoController.actualizarCurso(cursoActualizado);
                    cursoActualizado=cursoController.buscarCursoID(idEscrita);
                    JOptionPane.showMessageDialog(menuCursosUI.this,"El curso con id:"+cursoActualizado.getId()+" fue actualizado en el Sistema");
                    crearTablaBusqueda(cursoActualizado);

                }

            }
        });




        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int idEscrita=Integer.parseInt(id.getText());

                CursoModel cursoBorrado;
                cursoBorrado=cursoController.buscarCursoID(idEscrita);
                if(cursoBorrado.getId()==0){
                    JOptionPane.showMessageDialog(menuCursosUI.this,"El curso con id:"+idEscrita+" no existe en el sistema");
                }
                else{

                    int dialogResult = JOptionPane.showConfirmDialog (menuCursosUI.this, "Esta seguro que desea borra al curso:"+ cursoBorrado.getNombre()+" ID: "+cursoBorrado.getId(),"Ventana Borrado",dialogButton);
                    if(dialogResult == JOptionPane.YES_OPTION){
                        cursoController.borrarCurso(idEscrita);
                        JOptionPane.showMessageDialog(menuCursosUI.this,"El curso con nombre: "+cursoBorrado.getNombre()+" con ID: "+ cursoBorrado.getId()+" fue borrado en el Sistema");
                        crearTabla();

                    } else{
                        JOptionPane.showMessageDialog(menuCursosUI.this,"El curso con nombre: "+cursoBorrado.getNombre()+" con ID: "+ cursoBorrado.getId()+" NO fue borrado en el Sistema");
                        new cursoOpciones();

                    }


                }


            }
        });




        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int idEscrita=Integer.parseInt(id.getText());

                CursoModel cursoBuscado;
                cursoBuscado=cursoController.buscarCursoID(idEscrita);
                if(cursoBuscado.getId()==0){
                    JOptionPane.showMessageDialog(menuCursosUI.this,"El curso con id:"+idEscrita+" no existe en el sistema");
                }
                else{
                    JOptionPane.showMessageDialog(menuCursosUI.this,"El curso con id:"+idEscrita+" fue encontrado en el sistema");
                    crearTablaBusqueda(cursoBuscado);

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
                id.setVisible(false);
                activoRadioButton.setVisible(false);
                inactivoRadioButton.setVisible(false);
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
                descripcion.setVisible(false);
                activoRadioButton.setVisible(false);
                inactivoRadioButton.setVisible(false);
                break;
            default:
                crearButton.setVisible(false);
                modificarButton.setVisible(false);
                eliminarButton.setVisible(false);
                nombre.setVisible(false);
                descripcion.setVisible(false);
                activoRadioButton.setVisible(false);
                inactivoRadioButton.setVisible(false);

        }
    }




    public void crearTabla(){
        List<CursoModel> lista3 = cursoController.listarCursos();


        int c = lista3.size();
        System.out.println(c);

        Object[][] row=crearFilasTabla(c,lista3);
//        int id, String nombre, int identificacion, String email, boolean estado, Date fecha_nacimiento

        String[] column = {"ID", "Nombre", "Descripcion","Estado"};

        table1.setModel(new DefaultTableModel(row, column));
    }

    public void crearTablaBusqueda(CursoModel cursoModel){
        List<CursoModel> lista3=new ArrayList<>();
        lista3.add(cursoModel);


        int c = lista3.size();
        System.out.println(c);

        Object[][] row=crearFilasTabla(c,lista3);
        String[] column = {"ID", "Nombre", "Descripcion","Estado"};

        table1.setModel(new DefaultTableModel(row, column));
    }

    public Object[][] crearFilasTabla(int c,List<CursoModel> lista3){
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
                    data[i][j] = lista3.get(i).getDescripcion();
                    value++;}
                else if (j==3) {
                    data[i][j] = lista3.get(i).isEstado()? "activo":"inactivado";
                    value++;}
            }
        }
        return data;
    }
}
