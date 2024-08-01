package View.Presentacion;

import View.ViewPersonas.Estudiante.estudianteOpciones;
import View.ViewPersonas.Profesores.profesorOpciones;
import View.ViewsAdministrativo.Curso.cursoOpciones;
import View.ViewsAdministrativo.Grupo.grupoOpciones;
import View.ViewsAdministrativo.GrupoCurso.grupoCursoOpciones;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuGeneralUI extends JFrame {
    private JButton menuDeEstudiantesButton;
    private JButton menuDeProfesoresButton;
    private JButton menuDeGruposButton;
    private JButton menuDeCursosButton;
    private JButton menuDeGrupoCursoButton;
    private JButton salirDelSistemaButton;
    private JPanel MenuGeneralUI;

    public MenuGeneralUI() {
        setContentPane(MenuGeneralUI);
        setTitle("Menu General");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 400);
        setLocationRelativeTo(null);
        setVisible(true);
        menuDeEstudiantesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new estudianteOpciones();

            }
        });
        menuDeProfesoresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new profesorOpciones();
            }
        });
        menuDeGruposButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new grupoOpciones();

            }
        });
        menuDeCursosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new cursoOpciones();

            }
        });

        menuDeGrupoCursoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new grupoCursoOpciones();
            }
        });
        salirDelSistemaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(MenuGeneralUI.this,"Gracias por utilizar el Sistema Vuelva Cuando Lo Necesita");
                dispose();
            }
        });
    }
}
