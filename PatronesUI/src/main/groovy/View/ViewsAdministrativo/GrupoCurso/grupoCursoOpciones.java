package View.ViewsAdministrativo.GrupoCurso;

import View.Presentacion.MenuGeneralUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class grupoCursoOpciones extends JFrame {
    private JPanel grupoCursoOpciones;
    private JButton menuDeAgregacionDeButton;
    private JButton menuDeModificacionDeButton;
    private JButton menuDeEliminacionDeButton;
    private JButton menuDeBusquedaDeButton;
    private JButton salirDeMenuGrupoCursoButton;

    public grupoCursoOpciones() {
        setContentPane(grupoCursoOpciones);
        setTitle("Menu Grupos");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 400);
        setLocationRelativeTo(null);
        setVisible(true);
        menuDeAgregacionDeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new menuGruposCursoUI();

            }
        });
        menuDeModificacionDeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new menuGruposCursoUI();

            }
        });
        menuDeEliminacionDeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new menuGruposCursoUI();
            }
        });
        menuDeBusquedaDeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new menuGruposCursoUI();

            }
        });

        salirDeMenuGrupoCursoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new MenuGeneralUI();

            }
        });
    }
}
