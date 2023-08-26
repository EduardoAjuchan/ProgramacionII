package II_PARCIAL;

import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class ProductForm extends JFrame {
    private JTextField nombreField, precioField, cantidadField;
    private JDateChooser fechaChooser;
    private DefaultTableModel tableModel;
    private JTable productTable;

    public ProductForm() {
        setTitle("FARMACIAS EL DIFUNTO");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(6, 2));

        JLabel nameLabel = new JLabel("Nombre del Medicamento:");
        nombreField = new JTextField();

        JLabel precioLabel = new JLabel("Precio Unitario:");
        precioField = new JTextField();

        JLabel cantidadLabel = new JLabel("Cantidad Existente:");
        cantidadField = new JTextField();

        JLabel fechaLabel = new JLabel("Fecha de Vencimiento:");
        fechaChooser = new JDateChooser();

        JButton guardarButton = new JButton("Guardar");
        guardarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guardarEnBaseDeDatos();
                cargarProductos();
            }
        });

        JButton modificarButton = new JButton("Modificar");
        modificarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                modificarProducto();
            }
        });

        JButton eliminarButton = new JButton("Eliminar");
        eliminarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eliminarProducto();
            }
        });

        JButton limpiarButton = new JButton("Limpiar");
        limpiarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });

        inputPanel.add(nameLabel);
        inputPanel.add(nombreField);
        inputPanel.add(precioLabel);
        inputPanel.add(precioField);
        inputPanel.add(cantidadLabel);
        inputPanel.add(cantidadField);
        inputPanel.add(fechaLabel);
        inputPanel.add(fechaChooser);
        inputPanel.add(guardarButton);
        inputPanel.add(modificarButton);
        inputPanel.add(eliminarButton);
        inputPanel.add(limpiarButton);

        add(inputPanel, BorderLayout.NORTH);

        String[] columnNames = {"Código", "Nombre", "Precio", "Cantidad", "Fecha de Vencimiento"};
        tableModel = new DefaultTableModel(columnNames, 0);
        productTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(productTable);
        add(scrollPane, BorderLayout.CENTER);

        JButton salirButton = new JButton("Salir");
        salirButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                System.exit(0);
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(new JLabel()); // Espacio en blanco para el diseño
        buttonPanel.add(limpiarButton);
        buttonPanel.add(salirButton);

        add(buttonPanel, BorderLayout.SOUTH);

        cargarProductos();

        setVisible(true);
    }


    private void limpiarCampos() {
        nombreField.setText("");
        precioField.setText("");
        cantidadField.setText("");
        fechaChooser.setDate(null);
    }

    private void guardarEnBaseDeDatos() {
        String url = "jdbc:mysql://localhost/BDNegocio";
        String user = "root";
        String password = "123456";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            System.out.println("Conexión establecida");
            String query = "INSERT INTO producto (nombreProducto, precioUnitario, cantidadProducto, fechaVencimiento) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, nombreField.getText());
            statement.setDouble(2, Double.parseDouble(precioField.getText()));
            statement.setInt(3, Integer.parseInt(cantidadField.getText()));
            String formattedDate = new SimpleDateFormat("yyyy-MM-dd").format(fechaChooser.getDate());
            statement.setString(4, formattedDate);
            statement.executeUpdate();
            JOptionPane.showMessageDialog(this, "Medicamento guardado correctamente");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al guardar el medicamento");
        }
    }

    private void cargarProductos() {
        String url = "jdbc:mysql://localhost/BDNegocio";
        String user = "root";
        String password = "123456";

        tableModel.setRowCount(0);

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT codigoProducto, nombreProducto, precioUnitario, cantidadProducto, fechaVencimiento FROM producto";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String[] rowData = {
                        resultSet.getString("codigoProducto"),
                        resultSet.getString("nombreProducto"),
                        resultSet.getString("precioUnitario"),
                        resultSet.getString("cantidadProducto"),
                        resultSet.getString("fechaVencimiento")
                };
                tableModel.addRow(rowData);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar los productos");
        }
    }

    private void modificarProducto() {
        // Lógica para modificar un producto
        // Obtener el índice de la fila seleccionada en la tabla
        int selectedRow = productTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un producto para modificar");
            return;
        }

        // Obtener los datos actuales del producto
        String codigo = productTable.getValueAt(selectedRow, 0).toString();
        String nombre = productTable.getValueAt(selectedRow, 1).toString();
        String precio = productTable.getValueAt(selectedRow, 2).toString();
        String cantidad = productTable.getValueAt(selectedRow, 3).toString();
        String fecha = productTable.getValueAt(selectedRow, 4).toString();

        // Mostrar un diálogo para editar los datos
        String nuevoNombre = JOptionPane.showInputDialog(this, "Nuevo nombre:", nombre);
        if (nuevoNombre == null) {
            return; // El usuario canceló la operación
        }
        String nuevoPrecio = JOptionPane.showInputDialog(this, "Nuevo precio:", precio);
        if (nuevoPrecio == null) {
            return;
        }
        String nuevaCantidad = JOptionPane.showInputDialog(this, "Nueva cantidad:", cantidad);
        if (nuevaCantidad == null) {
            return;
        }
        // Obtener la nueva fecha de vencimiento
        JDateChooser dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("yyyy-MM-dd");
        String[] opciones = {"OK", "Cancelar"};
        int seleccion = JOptionPane.showOptionDialog(null, dateChooser, "Nueva fecha de vencimiento", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);
        if (seleccion == 0) {
            String nuevaFecha = new SimpleDateFormat("yyyy-MM-dd").format(dateChooser.getDate());

            // Actualizar los datos en la base de datos
            String url = "jdbc:mysql://localhost/BDNegocio";
            String user = "root";
            String password = "123456";

            try (Connection connection = DriverManager.getConnection(url, user, password)) {
                String query = "UPDATE producto SET nombreProducto=?, precioUnitario=?, cantidadProducto=?, fechaVencimiento=? WHERE codigoProducto=?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, nuevoNombre);
                statement.setDouble(2, Double.parseDouble(nuevoPrecio));
                statement.setInt(3, Integer.parseInt(nuevaCantidad));
                statement.setString(4, nuevaFecha);
                statement.setString(5, codigo);
                statement.executeUpdate();
                JOptionPane.showMessageDialog(this, "Producto modificado correctamente");
                cargarProductos(); // Actualizar la tabla
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error al modificar el producto");
            }
        }
    }

    private void eliminarProducto() {
        // Lógica para eliminar un producto
        int selectedRow = productTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un producto para eliminar");
            return;
        }

        String codigo = productTable.getValueAt(selectedRow, 0).toString();
        String nombre = productTable.getValueAt(selectedRow, 1).toString();

        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Estás seguro de eliminar el producto '" + nombre + "'?");
        if (confirmacion == JOptionPane.YES_OPTION) {
            String url = "jdbc:mysql://localhost/BDNegocio";
            String user = "root";
            String password = "123456";

            try (Connection connection = DriverManager.getConnection(url, user, password)) {
                String query = "DELETE FROM producto WHERE codigoProducto=?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, codigo);
                statement.executeUpdate();
                JOptionPane.showMessageDialog(this, "Producto eliminado correctamente");
                cargarProductos(); // Actualizar la tabla
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error al eliminar el producto");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ProductForm());
    }
}
