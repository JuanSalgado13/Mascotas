package vistas;

import controlador.GestorCentral;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPrincipal extends JFrame implements ActionListener {
	 private static final long serialVersionUID = 1L;

    private JButton btnUsuarios;
    private JButton btnAnimales;
    private GestorCentral gestorCentral;

    public MenuPrincipal() {
        setTitle("CENTRO DE REGISTRO VETERINARIO");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);

        
        JPanel panelFondo = new JPanel();
        panelFondo.setBackground(new Color(220, 240, 255)); 
        panelFondo.setBounds(0, 0, 484, 361);
        panelFondo.setLayout(null);

        JLabel titulo = new JLabel("Menú Principal");
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setBounds(170, 30, 200, 30);
        panelFondo.add(titulo);

        btnUsuarios = new JButton("Uuario");
        btnUsuarios.setBounds(162, 117, 150, 40);
        btnUsuarios.addActionListener(this);

        btnAnimales = new JButton("Animales");
        btnAnimales.setBounds(162, 211, 150, 40);
        btnAnimales.addActionListener(this);

        panelFondo.add(btnUsuarios);
        panelFondo.add(btnAnimales);

        getContentPane().add(panelFondo);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnUsuarios) {
            gestorCentral.abrirModuloUsuarios();
        } else if (e.getSource() == btnAnimales) {
            gestorCentral.abrirModuloAnimales();
        }
    }

    public void setGestorCentral(GestorCentral gestorCentral) {
        this.gestorCentral = gestorCentral;
    }
}
