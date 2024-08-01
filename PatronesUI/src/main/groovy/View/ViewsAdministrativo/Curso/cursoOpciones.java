package View.ViewsAdministrativo.Curso;

import View.Presentacion.MenuGeneralUI;
import View.ViewsAdministrativo.Curso.menuCursosUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class cursoOpciones extends JFrame {
    private JPanel cursoOpciones;
    private JButton menuDeAgregacionDeButton;
    private JButton menuDeModificacionDeButton;
    private JButton menuDeEliminacionDeButton;
    private JButton menuDeBusquedaDeButton;
    private JButton salirDeMenuCursoButton;

    public cursoOpciones() {
        setContentPane(cursoOpciones);
        setTitle("Menu Cursos");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 400);
        setLocationRelativeTo(null);
        setVisible(true);
        menuDeAgregacionDeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new menuCursosUI(1);

            }
        });
        menuDeModificacionDeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new menuCursosUI(2);

            }
        });
        menuDeEliminacionDeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new menuCursosUI(3);
            }
        });
        menuDeBusquedaDeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new menuCursosUI(4);

            }
        });

        salirDeMenuCursoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new MenuGeneralUI();

            }
        });
    }
}
