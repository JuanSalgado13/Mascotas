package procesos;

import javax.swing.JOptionPane;

public class AccionesSistema {

    private final GestionUsuario gestionUsuario;
    private final GestionAnimal gestionAnimal;

    public AccionesSistema() {
        this.gestionUsuario = new GestionUsuario();
        this.gestionAnimal = new GestionAnimal();
    }

    public void iniciarAplicacion() {
        int opcion = 0;

        do {
            opcion = mostrarMenuPrincipal();
            switch (opcion) {
                case 1 -> gestionUsuario.mostrarMenu();
                case 2 -> gestionAnimal.menu();
                case 3 -> JOptionPane.showMessageDialog(null, "Aplicación finalizada.");
                default -> JOptionPane.showMessageDialog(null, "Opción no reconocida.");
            }
        } while (opcion != 3);
    }

    private int mostrarMenuPrincipal() {
        String opciones = """
                ==== SISTEMA DE REGISTRO ====

                1. Módulo de Usuarios
                2. Módulo de Animales
                3. Salir
                """;

        try {
            return Integer.parseInt(JOptionPane.showInputDialog(opciones));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Por favor ingrese una opción válida.");
            return 0;
        }
    }
}
