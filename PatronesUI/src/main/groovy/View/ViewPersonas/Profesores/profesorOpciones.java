package View.ViewPersonas.Profesores;

import View.Presentacion.MenuGeneralUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class profesorOpciones extends JFrame {
    private JPanel profesorOpciones;
    private JButton menuDeAgregacionDeButton;
    private JButton menuDeModificacionDeButton;
    private JButton menuDeEliminacionDeButton;
    private JButton menuDeBusquedaDeButton;
    private JButton salirDeMenuProfesoresButton;

    public profesorOpciones() {
        setContentPane(profesorOpciones);
        setTitle("Menu Profesores");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 400);
        setLocationRelativeTo(null);
        setVisible(true);
        menuDeAgregacionDeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new menuProfesoresUI(1);

            }
        });
        menuDeModificacionDeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new menuProfesoresUI(2);

            }
        });
        menuDeEliminacionDeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new menuProfesoresUI(3);
            }
        });
        menuDeBusquedaDeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new menuProfesoresUI(4);

            }
        });

        salirDeMenuProfesoresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new MenuGeneralUI();

            }
        });
    }
}
