package View.ViewPersonas.Estudiante;

import View.Presentacion.MenuGeneralUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class estudianteOpciones extends JFrame {
    private JPanel estudiantesOpciones;
    private JButton menuDeAgregacionDeButton;
    private JButton menuDeModificacionDeButton;
    private JButton menuDeEliminacionDeButton;
    private JButton menuDeBusquedaDeButton;
    private JButton salirDeMenuEstudiantesButton;

    public estudianteOpciones() {
        setContentPane(estudiantesOpciones);
        setTitle("Menu Estudiantes");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 400);
        setLocationRelativeTo(null);
        setVisible(true);
        menuDeAgregacionDeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new menuEstudiantesUI(1);

            }
        });
        menuDeModificacionDeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new menuEstudiantesUI(2);

            }
        });
        menuDeEliminacionDeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new menuEstudiantesUI(3);
            }
        });
        menuDeBusquedaDeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new menuEstudiantesUI(4);

            }
        });
        salirDeMenuEstudiantesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new MenuGeneralUI();
            }
        });
    }
}
