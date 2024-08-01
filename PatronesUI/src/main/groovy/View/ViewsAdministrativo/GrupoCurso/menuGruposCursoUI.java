package View.ViewsAdministrativo.GrupoCurso;

import View.Presentacion.MenuGeneralUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class menuGruposCursoUI extends JFrame{
    private JPanel menuGruposCursoUI;
    private JTextField grupoId;
    private JTextField descripcion;
    private JTextField id;
    private JButton crearButton;
    private JButton modificarButton;
    private JButton eliminarButton;
    private JButton buscarButton;
    private JButton salirButton;
    private JTable tableGrupos;
    private JTable tableCursos;
    private JTable table3;

    public menuGruposCursoUI() {
        setContentPane(menuGruposCursoUI);
        setTitle("Menu General Grupos");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 400);
        setLocationRelativeTo(null);
        setVisible(true);
        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new MenuGeneralUI();
            }
        });
    }
}
