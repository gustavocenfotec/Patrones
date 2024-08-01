package View.ViewsAdministrativo.Grupo;

import View.Presentacion.MenuGeneralUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class grupoOpciones extends JFrame {
    private JPanel grupoOpciones;
    private JButton menuDeAgregacionDeButton;
    private JButton menuDeModificacionDeButton;
    private JButton menuDeEliminacionDeButton;
    private JButton menuDeBusquedaDeButton;
    private JButton salirDeMenuGrupoButton;

    public grupoOpciones() {
        setContentPane(grupoOpciones);
        setTitle("Menu Grupos");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 400);
        setLocationRelativeTo(null);
        setVisible(true);
        menuDeAgregacionDeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new menuGruposUI(1);

            }
        });
        menuDeModificacionDeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new menuGruposUI(2);

            }
        });
        menuDeEliminacionDeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new menuGruposUI(3);
            }
        });
        menuDeBusquedaDeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new menuGruposUI(4);

            }
        });

        salirDeMenuGrupoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new MenuGeneralUI();

            }
        });
    }
}
