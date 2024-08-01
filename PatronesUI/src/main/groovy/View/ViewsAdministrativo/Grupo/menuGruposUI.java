package View.ViewsAdministrativo.Grupo;

import View.Presentacion.MenuGeneralUI;
import controller.Administrativos.GrupoController;

import model.Administrativos.GrupoModel;
import view.ConsoleView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class menuGruposUI extends JFrame{

    private GrupoModel grupoModel;

    private GrupoController grupoController;
    private JPanel menuGruposUI;
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

    public menuGruposUI(int seleccion) {
        this.grupoController=new GrupoController(new ConsoleView());
        setContentPane(menuGruposUI);
        setTitle("Menu General Grupos");
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
                new grupoOpciones();
            }
        });



        crearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreEscrito=nombre.getText() ;
                String descripcionEscrito=descripcion.getText();
                boolean estado=true;


                GrupoModel grupoAgregado=new GrupoModel(nombreEscrito,descripcionEscrito,estado);
                grupoController.agregarGrupo(grupoAgregado);
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

                GrupoModel grupoActualizado;
                grupoActualizado=grupoController.buscarGrupoID(idEscrita);
                if(grupoActualizado.getId()==0){
                    JOptionPane.showMessageDialog(menuGruposUI.this,"El grupo con id:"+idEscrita+" no existe en el sistema");
                }
                else{

                    grupoActualizado=new GrupoModel(idEscrita,nombreEscrito,descripcionEscrito,estado);
                    grupoController.actualizarGrupo(grupoActualizado);
                    grupoActualizado=grupoController.buscarGrupoID(idEscrita);
                    JOptionPane.showMessageDialog(menuGruposUI.this,"El grupo con id:"+grupoActualizado.getId()+" fue actualizado en el Sistema");
                    crearTablaBusqueda(grupoActualizado);

                }

            }
        });
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idEscrita=Integer.parseInt(id.getText());

                GrupoModel grupoBorrado;
                grupoBorrado=grupoController.buscarGrupoID(idEscrita);
                if(grupoBorrado.getId()==0){
                    JOptionPane.showMessageDialog(menuGruposUI.this,"El grupo con id:"+idEscrita+" no existe en el sistema");
                }
                else{

                    int dialogResult = JOptionPane.showConfirmDialog (menuGruposUI.this, "Esta seguro que desea borra al grupo:"+ grupoBorrado.getNombre()+" ID: "+grupoBorrado.getId(),"Ventana Borrado",dialogButton);
                    if(dialogResult == JOptionPane.YES_OPTION){
                        grupoController.borrarGrupo(idEscrita);
                        JOptionPane.showMessageDialog(menuGruposUI.this,"El grupo con nombre: "+grupoBorrado.getNombre()+" con ID: "+ grupoBorrado.getId()+" fue borrado en el Sistema");
                        crearTabla();

                    } else{
                        JOptionPane.showMessageDialog(menuGruposUI.this,"El grupo con nombre: "+grupoBorrado.getNombre()+" con ID: "+ grupoBorrado.getId()+" NO fue borrado en el Sistema");
                        new grupoOpciones();

                    }


                }
            }
        });
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idEscrita=Integer.parseInt(id.getText());

                GrupoModel grupoBuscado;
                grupoBuscado=grupoController.buscarGrupoID(idEscrita);
                if(grupoBuscado.getId()==0){
                    JOptionPane.showMessageDialog(menuGruposUI.this,"El grupo con id:"+idEscrita+" no existe en el sistema");
                }
                else{
                    JOptionPane.showMessageDialog(menuGruposUI.this,"El grupo con id:"+idEscrita+" fue encontrado en el sistema");
                    crearTablaBusqueda(grupoBuscado);

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
        List<GrupoModel> lista3 = grupoController.buscarGrupos();


        int c = lista3.size();

        Object[][] row=crearFilasTabla(c,lista3);
//        int id, String nombre, int identificacion, String email, boolean estado, Date fecha_nacimiento

        String[] column = {"ID", "Nombre", "Descripcion","Estado"};

        table1.setModel(new DefaultTableModel(row, column));
    }

    public void crearTablaBusqueda(GrupoModel grupoModel){
        List<GrupoModel> lista3=new ArrayList<>();
        lista3.add(grupoModel);


        int c = lista3.size();
        System.out.println(c);

        Object[][] row=crearFilasTabla(c,lista3);
        String[] column = {"ID", "Nombre", "Descripcion","Estado"};

        table1.setModel(new DefaultTableModel(row, column));
    }

    public Object[][] crearFilasTabla(int c,List<GrupoModel> lista3){
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
