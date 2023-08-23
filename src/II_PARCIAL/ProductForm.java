package II_PARCIAL;
//se importan las librerias necesarias
import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class ProductForm extends JFrame {
    private JTextField nombreField, precioField, cantidadField;
    private JDateChooser fechaChooser;

    //se define el tamaño y el titulo de la ventana
    public ProductForm() {
        setTitle("FARMACIAS EL DIFUNTO");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 2)); // 6 filas, 2 columnas para el GridLayout

        //se crean los objetos que se van a mostrar en la ventana
        JLabel nameLabel = new JLabel("Nombre del Medicamento:");
        nombreField = new JTextField();

        JLabel precioLabel = new JLabel("Precio Unitario:");
        precioField = new JTextField();

        JLabel cantidadLabel = new JLabel("Cantidad Existente:");
        cantidadField = new JTextField();

        JLabel fechaLabel = new JLabel("Fecha de Vencimiento:");
        fechaChooser = new JDateChooser();

        //se crea el boton de guardar y se le asigna la funcion de guardar los datos en la base de datos
        JButton guardarButton = new JButton("Guardar");
        guardarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guardarEnBaseDeDatos();
            }
        });
        //se crea el boton de salir
        JButton salirButton = new JButton("Salir");
        salirButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Cerrar la aplicación al presionar el botón "Salir"
                dispose();
                System.exit(0);
            }
        });
        //se crea el boton de limpiar
        JButton limpiarButton = new JButton("Limpiar");
        limpiarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });
        //se agregan los objetos a la ventana
        add(nameLabel);
        add(nombreField);

        add(precioLabel);
        add(precioField);

        add(cantidadLabel);
        add(cantidadField);

        add(fechaLabel);
        add(fechaChooser);

        add(guardarButton);
        add(salirButton);
        add(limpiarButton);
        setVisible(true);
    }
    //se crea el metodo para limpiar los campos
    private void limpiarCampos() {
        nombreField.setText("");
        precioField.setText("");
        cantidadField.setText("");
        fechaChooser.setDate(null); // Limpia la fecha seleccionada
    }
    //se crea el metodo para guardar los datos en la base de datos
    private void guardarEnBaseDeDatos() {
        String url = "jdbc:mysql://localhost/BDNegocio"; //se define la url de la base de datos
        String user = "root";
        String password = "123456";
        //se crea el try catch para guardar los datos en la base de datos
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            System.out.println("Conexión establecida");
            String query = "INSERT INTO producto (nombreProducto, precioUnitario, cantidadProducto, fechaVencimiento) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, nombreField.getText());
            statement.setDouble(2, Double.parseDouble(precioField.getText()));
            statement.setInt(3, Integer.parseInt(cantidadField.getText()));

            // se obtiene la fecha del JDateChooser y la formateamos al formato de la base de datos
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = dateFormat.format(fechaChooser.getDate());
            statement.setString(4, formattedDate);
            //se ejecuta el query
            statement.executeUpdate();
            JOptionPane.showMessageDialog(this, "Medicamento guardado correctamente");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al guardar el medicamento");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ProductForm());
    }
}
