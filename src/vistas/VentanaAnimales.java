package vistas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controlador.GestorCentral;
import modeloDao.AnimalDao;
import vo.AnimalVo;

public class VentanaAnimales extends JFrame implements ActionListener {

	private JButton btnVolver;
	// ya están: btnGuardar, btnBuscar, etc.

	private static final long serialVersionUID = 1L;
	private GestorCentral controlador;
    private final AnimalDao animalDao = new AnimalDao();

    JLabel lblPropietario, lblNombre, lblEspecie, lblGenero;
    JTextField txtPropietario, txtNombre, txtEspecie, txtGenero;
    JButton btnGuardar, btnBuscar, btnEditar, btnEliminar, btnVerLista, btnLimpiar;
    JTextArea areaResultado;
    JScrollPane scroll;

    public VentanaAnimales() {
        setTitle(" ANIMALES");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(550, 658);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);

        lblPropietario = new JLabel("ID Usuario:");
        lblNombre = new JLabel("Nombre:");
        lblEspecie = new JLabel("Especie:");
        lblGenero = new JLabel("Género:");

        txtPropietario = new JTextField();
        txtNombre = new JTextField();
        txtEspecie = new JTextField();
        txtGenero = new JTextField();
        
        btnVolver = new JButton("Volver al Menú");
        btnVolver.setBounds(304, 584, 180, 25);
        btnVolver.addActionListener(this);
        getContentPane().add(btnVolver);


        btnGuardar = new JButton("Guardar");
        btnBuscar = new JButton("Buscar");
        btnEditar = new JButton("Editar");
        btnEliminar = new JButton("Eliminar");
        btnVerLista = new JButton("Ver Lista");
        btnLimpiar = new JButton("Limpiar");

        areaResultado = new JTextArea();
        scroll = new JScrollPane(areaResultado);

        
        lblPropietario.setBounds(120, 19, 100, 20); txtPropietario.setBounds(180, 19, 180, 20);
        lblNombre.setBounds(120, 50, 100, 20);      txtNombre.setBounds(180, 50, 180, 20);
        lblEspecie.setBounds(120, 81, 80, 20);     txtEspecie.setBounds(180, 81, 180, 20);
        lblGenero.setBounds(120, 111, 80, 20);      txtGenero.setBounds(180, 111, 180, 20);

        btnGuardar.setBounds(133, 170, 111, 25);
        btnBuscar.setBounds(392, 170, 111, 25);
        btnEditar.setBounds(20, 170, 92, 25);
        btnEliminar.setBounds(269, 170, 113, 25);
        btnVerLista.setBounds(180, 214, 180, 25);
        scroll.setBounds(20, 250, 480, 320);
        btnLimpiar.setBounds(20, 584, 180, 25);

        getContentPane().add(lblPropietario); getContentPane().add(txtPropietario);
        getContentPane().add(lblNombre); getContentPane().add(txtNombre);
        getContentPane().add(lblEspecie); getContentPane().add(txtEspecie);
        getContentPane().add(lblGenero); getContentPane().add(txtGenero);
        getContentPane().add(btnGuardar); getContentPane().add(btnBuscar);
        getContentPane().add(btnEditar); getContentPane().add(btnEliminar);
        getContentPane().add(btnVerLista); getContentPane().add(scroll); getContentPane().add(btnLimpiar);

        btnGuardar.addActionListener(this);
        btnBuscar.addActionListener(this);
        btnEditar.addActionListener(this);
        btnEliminar.addActionListener(this);
        btnVerLista.addActionListener(this);
        btnLimpiar.addActionListener(this);
    }

    public void setControlador(GestorCentral controlador) {
        this.controlador = controlador;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnGuardar) {
            if (!validarGenero()) return;
            AnimalVo a = capturarDatos();
            controlador.registrarAnimal(a);
            areaResultado.setText("Animal registrado correctamente.");
            limpiarCampos();

        } else if (e.getSource() == btnBuscar) {
            AnimalVo a = animalDao.buscarPorNombre(txtNombre.getText().trim());
            areaResultado.setText((a != null) ? a.toString() : "Animal no encontrado.");
            limpiarCampos();

        } else if (e.getSource() == btnEditar) {
            AnimalVo actualizado = capturarDatos();
            controlador.actualizarAnimal(actualizado);
            areaResultado.setText("Animal actualizado.");
            limpiarCampos();

        } else if (e.getSource() == btnEliminar) {
            controlador.eliminarAnimal(txtNombre.getText().trim());
            areaResultado.setText("Animal eliminado.");
            limpiarCampos();

        } else if (e.getSource() == btnVerLista) {
            areaResultado.setText(controlador.obtenerListadoAnimales());

        } else if (e.getSource() == btnLimpiar) {
            areaResultado.setText("");
        }else if (e.getSource() == btnVolver) {
            this.dispose(); // cerrar ventana actual
            MenuPrincipal menu = new MenuPrincipal();
            menu.setGestorCentral(controlador); // usa el mismo controlador
            menu.setVisible(true);
        }

    }

    private AnimalVo capturarDatos() {
        return new AnimalVo(
                txtNombre.getText().trim(),
                txtEspecie.getText().trim(),
                txtGenero.getText().trim(),
                txtPropietario.getText().trim()
        );
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtEspecie.setText("");
        txtGenero.setText("");
        txtPropietario.setText("");
    }

    private boolean validarGenero() {
        String g = txtGenero.getText().trim().toLowerCase();
        if (!g.equals("Masculino") && !g.equals("Femenino")) {
            areaResultado.setText("El género debe ser 'Masculino' o 'Femenino'.");
            return false;
        }
        return true;
    }
}