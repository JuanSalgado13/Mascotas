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
import modeloDao.UsuarioDao;
import vo.UsuarioVo;

public class VentanaUsuarios extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private GestorCentral controlador;
    private final UsuarioDao usuarioDao = new UsuarioDao();
    private JButton btnVolver;


    JLabel lblId, lblNombre, lblDireccion, lblTelefono;
    JTextField txtId, txtNombre, txtDireccion, txtTelefono;
    JButton btnAgregar, btnBuscar, btnModificar, btnEliminar, btnLista, btnLimpiar;
    JTextArea areaResultado;
    JScrollPane scroll;

    public VentanaUsuarios() {
        setTitle("GESTIÓN DE USUARIOS");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(540, 642);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);

        lblId = new JLabel("Documento:");
        lblNombre = new JLabel("Nombre:");
        lblDireccion = new JLabel("Dirección:");
        lblTelefono = new JLabel("Teléfono:");

        txtId = new JTextField();
        txtNombre = new JTextField();
        txtDireccion = new JTextField();
        txtTelefono = new JTextField();
        
        btnVolver = new JButton("Volver al Menú");
        btnVolver.setBounds(329, 570, 170, 25);
        btnVolver.addActionListener(this);
        getContentPane().add(btnVolver);


        btnAgregar = new JButton("Agregar");
        btnBuscar = new JButton("Buscar");
        btnModificar = new JButton("Modificar");
        btnEliminar = new JButton("Eliminar");
        btnLista = new JButton("Ver Todos");
        btnLimpiar = new JButton("Limpiar");

        areaResultado = new JTextArea();
        scroll = new JScrollPane(areaResultado);

        // Posiciones
        lblId.setBounds(119, 11, 100, 20);        txtId.setBounds(200, 11, 180, 20);
        lblNombre.setBounds(119, 39, 100, 20);    txtNombre.setBounds(200, 42, 180, 20);
        lblDireccion.setBounds(119, 74, 100, 20);txtDireccion.setBounds(200, 74, 180, 20);
        lblTelefono.setBounds(119, 105, 100, 20); txtTelefono.setBounds(200, 105, 180, 20);

        btnAgregar.setBounds(136, 159, 122, 25);
        btnBuscar.setBounds(10, 159, 116, 25);
        btnModificar.setBounds(265, 159, 122, 25);
        btnEliminar.setBounds(397, 159, 110, 25);
        btnLista.setBounds(180, 194, 207, 25);
        scroll.setBounds(20, 230, 480, 320);
        btnLimpiar.setBounds(30, 570, 180, 25);

        getContentPane().add(lblId); getContentPane().add(txtId);
        getContentPane().add(lblNombre); getContentPane().add(txtNombre);
        getContentPane().add(lblDireccion); getContentPane().add(txtDireccion);
        getContentPane().add(lblTelefono); getContentPane().add(txtTelefono);
        getContentPane().add(btnAgregar); getContentPane().add(btnBuscar);
        getContentPane().add(btnModificar); getContentPane().add(btnEliminar);
        getContentPane().add(btnLista); getContentPane().add(scroll); getContentPane().add(btnLimpiar);

        btnAgregar.addActionListener(this);
        btnBuscar.addActionListener(this);
        btnModificar.addActionListener(this);
        btnEliminar.addActionListener(this);
        btnLista.addActionListener(this);
        btnLimpiar.addActionListener(this);
    }

    public void setControlador(GestorCentral controlador) {
        this.controlador = controlador;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAgregar) {
            if (controlador.usuarioExiste(txtId.getText().trim())) {
                areaResultado.setText("El usuario ya está registrado.");
            } else {
                UsuarioVo u = obtenerDatos();
                controlador.agregarUsuario(u);
                areaResultado.setText("Usuario agregado correctamente.");
                limpiar();
            }

        } else if (e.getSource() == btnBuscar) {
            UsuarioVo u = usuarioDao.consultarPorDocumento(txtId.getText().trim());
            areaResultado.setText((u != null) ? u.toString() : "Usuario no encontrado.");

        } else if (e.getSource() == btnModificar) {
            UsuarioVo u = obtenerDatos();
            controlador.modificarUsuario(u);
            areaResultado.setText("Usuario modificado correctamente.");

        } else if (e.getSource() == btnEliminar) {
            controlador.eliminarUsuario(txtId.getText().trim());
            areaResultado.setText("Usuario eliminado.");

        } else if (e.getSource() == btnLista) {
            areaResultado.setText(controlador.listarUsuarios());

        } else if (e.getSource() == btnLimpiar) {
            areaResultado.setText("");
        }else if (e.getSource() == btnVolver) {
            this.dispose();
            MenuPrincipal menu = new MenuPrincipal();
            menu.setGestorCentral(controlador);
            menu.setVisible(true);
        }

    }

    private UsuarioVo obtenerDatos() {
        return new UsuarioVo(
                txtId.getText().trim(),
                txtNombre.getText().trim(),
                txtDireccion.getText().trim(),
                txtTelefono.getText().trim()
        );
    }

    private void limpiar() {
        txtId.setText("");
        txtNombre.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
    }
}