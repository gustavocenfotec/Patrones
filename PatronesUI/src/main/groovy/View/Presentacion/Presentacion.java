package View.Presentacion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Presentacion extends JFrame {
    private JPanel Presentacion;
    private JButton ingresarButton;

    public Presentacion() {
        setContentPane(Presentacion);
        setTitle("Sistema Administrativo");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700,400);
        setLocationRelativeTo(null);
        setVisible(true);
        ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new MenuGeneralUI();

            }
        });
    }

}
