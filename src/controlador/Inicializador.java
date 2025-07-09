package controlador;

import modeloDao.AnimalDao;
import modeloDao.UsuarioDao;
import procesos.GestionAnimal;
import procesos.GestionUsuario;
import vistas.MenuPrincipal;
import vistas.VentanaUsuarios;
import vistas.VentanaAnimales;

public class Inicializador {

    private GestorCentral gestor;
    private AnimalDao animalDao;
    private UsuarioDao usuarioDao;
    private GestionAnimal gestionAnimal;
    private GestionUsuario gestionUsuario;
    private VentanaAnimales ventanaAnimales;
    private VentanaUsuarios ventanaUsuarios;
    private MenuPrincipal menuPrincipal;

    public void iniciar() {
        gestor = new GestorCentral();

        setAnimalDao(new AnimalDao());
        setUsuarioDao(new UsuarioDao());
        gestionAnimal = new GestionAnimal();
        gestionUsuario = new GestionUsuario();
        ventanaAnimales = new VentanaAnimales();
        ventanaUsuarios = new VentanaUsuarios();
        menuPrincipal = new MenuPrincipal();

        
        gestor.setGestionAnimal(gestionAnimal);
        gestor.setGestionUsuario(gestionUsuario);
        gestor.setMenuPrincipal(menuPrincipal);
        
        
        ventanaAnimales.setControlador(gestor);
        ventanaUsuarios.setControlador(gestor);
        menuPrincipal.setGestorCentral(gestor);

       
        menuPrincipal.setVisible(true);
    }

    public GestorCentral getGestor() {
        return gestor;
    }

	public AnimalDao getAnimalDao() {
		return animalDao;
	}

	public void setAnimalDao(AnimalDao animalDao) {
		this.animalDao = animalDao;
	}

	public UsuarioDao getUsuarioDao() {
		return usuarioDao;
	}

	public void setUsuarioDao(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}
}
