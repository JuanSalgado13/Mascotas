package procesos;

import javax.swing.JOptionPane;

import modeloDao.AnimalDao;
import vo.AnimalVo;

public class GestionAnimal {

    private final AnimalDao animalDao;

    public GestionAnimal() {
        this.animalDao = new AnimalDao();
    }

    public void menu() {
        String opciones = """
                === GESTIÓN DE ANIMALES ===

                1. Registrar animal
                2. Consultar animal
                3. Listar animales
                4. Modificar animal
                5. Eliminar animal
                6. Salir
                """;

        int opcion = 0;

        while (opcion != 6) {
            try {
                opcion = Integer.parseInt(JOptionPane.showInputDialog(opciones));
                switch (opcion) {
                    case 1 -> registrar();
                    case 2 -> consultar();
                    case 3 -> listar();
                    case 4 -> actualizar();
                    case 5 -> eliminar();
                    case 6 -> JOptionPane.showMessageDialog(null, "Saliendo");
                    default -> JOptionPane.showMessageDialog(null, "Opción inválida.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Entrada inválida. Debes ingresar un número.");
            }
        }
    }

    private void registrar() {
        AnimalVo a = new AnimalVo();
        a.setNombre(JOptionPane.showInputDialog("Nombre del animal:"));
        a.setEspecie(JOptionPane.showInputDialog("Especie:"));
        a.setGenero(JOptionPane.showInputDialog("Género:"));
        a.setPropietarioId(JOptionPane.showInputDialog("ID del propietario:"));

        try {
            animalDao.insertarAnimal(a);
            JOptionPane.showMessageDialog(null, "Animal registrado correctamente.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al registrar: " + e.getMessage());
        }
    }

    private void consultar() {
        String nombre = JOptionPane.showInputDialog("Nombre del animal:");
        AnimalVo animal = animalDao.buscarPorNombre(nombre);
        if (animal != null) {
            JOptionPane.showMessageDialog(null, animal.toString());
        } else {
            JOptionPane.showMessageDialog(null, "Animal no existente.");
        }
    }

    private void listar() {
        String listado = animalDao.obtenerListado();
        JOptionPane.showMessageDialog(null, listado.isEmpty() ? "No hay animales registrados." : listado);
    }

    public void actualizar() {
        String nombre = JOptionPane.showInputDialog("Nombre del animal a Editar:");
        AnimalVo animal = animalDao.buscarPorNombre(nombre);

        if (animal != null) {
            animal.setEspecie(JOptionPane.showInputDialog("Nueva especie:", animal.getEspecie()));
            animal.setGenero(JOptionPane.showInputDialog("Nuevo género:", animal.getGenero()));
            animal.setPropietarioId(JOptionPane.showInputDialog("Nuevo ID del propietario:", animal.getPropietarioId()));

            animalDao.modificarAnimal(animal);
            JOptionPane.showMessageDialog(null, "Animal actualizado correctamente.");
        } else {
            JOptionPane.showMessageDialog(null, "Animal no encontrado.");
        }
    }

    public void eliminar() {
        String nombre = JOptionPane.showInputDialog("Nombre del animal a eliminar:");
        animalDao.eliminarAnimal(nombre);
        JOptionPane.showMessageDialog(null, "Animal eliminado si existía.");
    }
}
