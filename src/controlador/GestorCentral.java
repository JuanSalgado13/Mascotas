package controlador;

import modeloDao.AnimalDao;
import modeloDao.UsuarioDao;
import procesos.GestionAnimal;
import procesos.GestionUsuario;
import vistas.MenuPrincipal;
import vistas.VentanaAnimales;
import vistas.VentanaUsuarios;
import vo.AnimalVo;
import vo.UsuarioVo;

public class GestorCentral {

    private AnimalDao animalDao;
    private UsuarioDao usuarioDao;
    private GestionAnimal gestionAnimal;
    private GestionUsuario gestionUsuario;
    private VentanaAnimales formularioAnimales;
    private VentanaUsuarios ventanaUsuarios;
    private MenuPrincipal menuPrincipal;

    public GestorCentral() {
        animalDao = new AnimalDao();
        usuarioDao = new UsuarioDao();
    }

    public void registrarAnimal(AnimalVo animal) {
        animalDao.insertarAnimal(animal);
    }

    public void actualizarAnimal(AnimalVo animal) {
        animalDao.modificarAnimal(animal);
        gestionAnimal.actualizar();
    }

    public void eliminarAnimal(String nombre) {
        animalDao.eliminarAnimal(nombre);
        gestionAnimal.eliminar();
    }

    public String obtenerListadoAnimales() {
        return animalDao.obtenerListado();
    }

    public void agregarUsuario(UsuarioVo usuario) {
        usuarioDao.guardarUsuario(usuario);
    }

    public void modificarUsuario(UsuarioVo usuario) {
        usuarioDao.actualizarUsuario(usuario);
        gestionUsuario.actualizar();
    }

    public void eliminarUsuario(String documento) {
        usuarioDao.borrarUsuario(documento);
        gestionUsuario.eliminar();
    }

    public String listarUsuarios() {
        return usuarioDao.listarUsuarios();
    }

    public boolean usuarioExiste(String documento) {
        return usuarioDao.consultarPorDocumento(documento) != null;
    }

    public void abrirModuloAnimales() {
        if (formularioAnimales == null || !formularioAnimales.isDisplayable()) {
            formularioAnimales = new VentanaAnimales();
            formularioAnimales.setControlador(this);
            formularioAnimales.setVisible(true);
        } else {
            formularioAnimales.toFront();
        }
    }

    public void abrirModuloUsuarios() {
        if (ventanaUsuarios == null || !ventanaUsuarios.isDisplayable()) {
            ventanaUsuarios = new VentanaUsuarios();
            ventanaUsuarios.setControlador(this);
            ventanaUsuarios.setVisible(true);
        } else {
            ventanaUsuarios.toFront();
        }
    }

    public void setGestionAnimal(GestionAnimal gestionAnimal) {
        this.gestionAnimal = gestionAnimal;
    }

    public void setGestionUsuario(GestionUsuario gestionUsuario) {
        this.gestionUsuario = gestionUsuario;
    }

    public void setMenuPrincipal(MenuPrincipal menuPrincipal) {
        this.menuPrincipal = menuPrincipal;
    }

    public MenuPrincipal getMenuPrincipal() {
        return menuPrincipal;
    }
}
