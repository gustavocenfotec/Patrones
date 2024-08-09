package View.ViewsAdministrativo.GrupoCurso;

import View.Presentacion.MenuGeneralUI;
import View.ViewsAdministrativo.Grupo.grupoOpciones;
import View.ViewsAdministrativo.Grupo.menuGruposUI;
import controller.Administrativos.CursoController;
import controller.Administrativos.GrupoController;
import controller.Administrativos.GrupoCursoController;
import model.Administrativos.CursoModel;
import model.Administrativos.GrupoCurso;
import model.Administrativos.GrupoModel;
import view.ConsoleView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class menuGruposCursoUI extends JFrame{

    private GrupoCurso grupoCurso;

    private GrupoCursoController grupoCursoController;

    private GrupoModel grupoModel;

    private GrupoController grupoController;

    private CursoModel cursoModel;

    private CursoController cursoController;

    private JPanel menuGruposCursoUI;
    private JTextField id;
    private JButton crearButton;
    private JButton modificarButton;
    private JButton eliminarButton;
    private JButton buscarButton;
    private JButton salirButton;
    private JTable table3;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JLabel GrupoLabel;

    private int dialogButton;

    public menuGruposCursoUI() {
        this.grupoCursoController=new GrupoCursoController(new ConsoleView());
        this.grupoController=new GrupoController(new ConsoleView());
        this.cursoController=new CursoController(new ConsoleView());

        List<GrupoModel> lista2 = grupoController.buscarGrupos();
        List<CursoModel> lista3 = cursoController.listarCursos();


        for (int i = 0; i < lista2.size(); i++) {
            GrupoModel grupoModel1=lista2.get(i);
            comboBox1.addItem(grupoModel1);
        }

        for (int i = 0; i < lista3.size(); i++) {
            CursoModel cursoModel1=lista3.get(i);
            comboBox2.addItem(cursoModel1);
        }


        setContentPane(menuGruposCursoUI);
        setTitle("Menu General Grupos");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1200, 400);
        setLocationRelativeTo(null);
        setVisible(true);
        crearTabla();


        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new MenuGeneralUI();
            }
        });

        crearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GrupoModel grupoModel1= (GrupoModel) comboBox1.getSelectedItem();
                CursoModel cursoModel1= (CursoModel) comboBox2.getSelectedItem();


                GrupoCurso grupoCursoAgregado = new GrupoCurso(grupoModel1.getId(), cursoModel1.getId());
                grupoCursoController.agregarGrupoCurso(grupoCursoAgregado);
                List<GrupoCurso> lista2 = grupoCursoController.buscarGruposCursos();
                grupoCursoAgregado = lista2.get(lista2.size() - 1);
                if (grupoCursoAgregado.getCurso_id() == cursoModel1.getId()) {
                    JOptionPane.showMessageDialog(menuGruposCursoUI.this,"El grupoCurso con id:"+grupoCursoAgregado.getId()+" fue Registrado de Forma exitosa");
                    crearTabla();
                } else {
                    JOptionPane.showMessageDialog(menuGruposCursoUI.this,"El grupoCurso no fue Registrado ");
                    crearTabla();
                }

            }
        });
        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int idEscrita=Integer.parseInt(id.getText());
                GrupoModel grupoModel1= (GrupoModel) comboBox1.getSelectedItem();
                CursoModel cursoModel1= (CursoModel) comboBox2.getSelectedItem();

                GrupoCurso grupoCursoActualizado = new GrupoCurso(idEscrita, grupoModel1.getId(), cursoModel1.getId());
                grupoCursoController.actualizarGrupoCurso(grupoCursoActualizado);
                grupoCursoActualizado = grupoCursoController.buscarGrupoCursoID(idEscrita);
                JOptionPane.showMessageDialog(menuGruposCursoUI.this,"El grupoCurso "+grupoCursoActualizado.getId()+" fue Modificado de Forma Exitosa ");
                crearTabla();
            }
        });
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idEscrita=Integer.parseInt(id.getText());

                GrupoCurso grupoCursoBorrado;
                grupoCursoBorrado=grupoCursoController.buscarGrupoCursoID(idEscrita);
                if(grupoCursoBorrado.getId()==0){
                    JOptionPane.showMessageDialog(menuGruposCursoUI.this,"El grupoCurso con id:"+idEscrita+" no existe en el sistema");
                }
                else{

                    int dialogResult = JOptionPane.showConfirmDialog (menuGruposCursoUI.this, "Esta seguro que desea borra al Grupo Curso:"+ grupoCursoBorrado.getId(),"Ventana Borrado",dialogButton);
                    if(dialogResult == JOptionPane.YES_OPTION){
                        grupoCursoController.borrarGrupoCurso(idEscrita);
                        JOptionPane.showMessageDialog(menuGruposCursoUI.this,"El grupo Curso con ID UNica: "+grupoCursoBorrado.getId()+" fue borrado en el Sistema");
                        crearTabla();

                    } else{
                        JOptionPane.showMessageDialog(menuGruposCursoUI.this,"El grupo Curso con ID UNica: "+grupoCursoBorrado.getId()+"  NO fue borrado en el Sistema");
                        new grupoOpciones();

                    }


                }

            }
        });
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int idEscrita=Integer.parseInt(id.getText());

                GrupoCurso grupoCursoBuscado;
                grupoCursoBuscado=grupoCursoController.buscarGrupoCursoID(idEscrita);
                if(grupoCursoBuscado.getId()==0){
                    JOptionPane.showMessageDialog(menuGruposCursoUI.this,"El grupo Curso con id:"+idEscrita+" no existe en el sistema");
                }
                else{
                    JOptionPane.showMessageDialog(menuGruposCursoUI.this,"El grupo Curso con id:"+idEscrita+" fue encontrado en el sistema");
                    crearTablaBusqueda(grupoCursoBuscado);

                }

            }
        });
    }

    public void crearTabla(){
        List<GrupoCurso> lista1 = grupoCursoController.buscarGruposCursos();

        int c = lista1.size();

        Object[][] row=crearFilasTabla(c,lista1);

        String[] column = {"ID Unico", "ID Grupo", "Nombre", "ID Curso","Nombre"};

        table3.setModel(new DefaultTableModel(row, column));
    }

    public void crearTablaBusqueda(GrupoCurso grupoCurso){
        List<GrupoCurso> lista3=new ArrayList<>();
        lista3.add(grupoCurso);


        int c = lista3.size();


        Object[][] row=crearFilasTabla(c,lista3);
        String[] column = {"ID Unico", "ID Grupo", "Nombre", "ID Curso","Nombre"};

        table3.setModel(new DefaultTableModel(row, column));
    }


    public Object[][] crearFilasTabla(int c, List<GrupoCurso> lista1){
        Object[][] data = new Object[c][5];

        int value = 1;
        for (int i = 0; i < c; i++) {
            for (int j = 0; j < 5; j++) {
                if(j==0){
                    data[i][j] = lista1.get(i).getId();
                    value++;}
                else if (j==1) {
                    GrupoModel grupoModel1=grupoController.buscarGrupoID(lista1.get(i).getGrupo_id());
                    data[i][j] = grupoModel1.getId();
                    value++;}
                else if (j==2) {
                    GrupoModel grupoModel1=grupoController.buscarGrupoID(lista1.get(i).getGrupo_id());
                    data[i][j] = grupoModel1.getNombre();
                    value++;}
                else if (j==3) {
                    CursoModel cursoModel1=cursoController.buscarCursoID(lista1.get(i).getCurso_id());
                    data[i][j] = cursoModel1.getId();
                    value++;}
                else if (j==4) {
                    CursoModel cursoModel1=cursoController.buscarCursoID(lista1.get(i).getCurso_id());
                    data[i][j] = cursoModel1.getNombre();
                    value++;}
            }
        }
        return data;
    }


}
