package I_PARCIAL.LaboratorioI;
/*Autor: EDUARDO AJUCHAN
Proyecto: LABORATORIO 1
PROGRAMACION II*/
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Calendar;
//Declaracion de variables globales
class Producto {
    String codigoProducto;
    String nombreProducto;
    int cantidadExistente;
    double precioUnitario;
    Date fechaRegistro;
    Date fechaExtraccion;
//constructor para inicializar las variables de la clase Producto
    public Producto(String codigoProducto, String nombreProducto, int cantidadExistente, double precioUnitario) {
        this.codigoProducto = codigoProducto;
        this.nombreProducto = nombreProducto;
        this.cantidadExistente = cantidadExistente;
        this.precioUnitario = precioUnitario;
        this.fechaRegistro = new Date();
    }
//funcion para calcular el total de un producto
    public double calcularTotal() {
        return cantidadExistente * precioUnitario;
    }
}
//clase Farmacia que contiene los metodos para registrar, mostrar, extraer, eliminar y agregar productos
class Farmacia {
    List<Producto> inventario = new ArrayList<>();
//funcion para registrar un producto en el inventario
    public void registrarProducto(Producto producto) {
        Producto productoExistente = buscarProducto(producto.codigoProducto);
        if (productoExistente != null) {
            System.out.println("Ya existe un producto con el mismo código.");
            return;
        } else {
            inventario.add(producto);
            guardarDatosEnArchivo(); // Corrección aquí
        }
    }
//funcion para guardar los datos de los productos en el archivo de texto
    private void guardarDatosEnArchivo() {
        String filePath = "c:/ficheros/farmacia.txt";
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            dateFormat.setTimeZone(TimeZone.getTimeZone("America/Guatemala"));
            for (Producto producto : inventario) {
                String fechaRegistroStr = dateFormat.format(producto.fechaRegistro);
                String fechaExtraccionStr = producto.fechaExtraccion != null ? dateFormat.format(producto.fechaExtraccion) : "";
                writer.println(producto.codigoProducto + "," + producto.nombreProducto + "," +
                        producto.cantidadExistente + "," + producto.precioUnitario + "," +
                        fechaRegistroStr + "," + fechaExtraccionStr);
            }
        } catch (IOException e) {
            System.out.println("Error al guardar los datos en el archivo.");
        }
    }
//funcion para buscar un producto en el inventario
    private Producto buscarProducto(String codigoProducto) {
        for (Producto producto : inventario) {
            if (producto.codigoProducto.equals(codigoProducto)) {
                return producto;
            }
        }
        return null;
    }
//funcion para mostrar los productos del inventario
    public void mostrarProductos() {
        if (inventario.isEmpty()) {
            System.out.println("Lo siento! No hay productos registrados");
            return;
        }
        double granTotal = 0;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); // Formato de fecha
        dateFormat.setTimeZone(TimeZone.getTimeZone("America/Guatemala")); // Zona horaria
        for (Producto producto : inventario) {
            double total = producto.calcularTotal();
            granTotal += total;
            System.out.println("Codigo: " + producto.codigoProducto);
            System.out.println("Nombre: " + producto.nombreProducto);
            System.out.println("Cantidad: " + producto.cantidadExistente);
            System.out.println("Precio Unitario: " + producto.precioUnitario);

            if (producto.fechaRegistro != null) {
                System.out.println("Fecha de Registro: " + dateFormat.format(producto.fechaRegistro)); // Mostrar fecha de registro
            }

            if (producto.fechaExtraccion != null) {
                System.out.println("Fecha de Venta: " + dateFormat.format(producto.fechaExtraccion)); // Mostrar fecha de la ultima venta si existe alguna
            }

            System.out.println("Sub total: " + total); //total de un producto
            System.out.println();
        }
        System.out.println("Total: " + granTotal); //gran total de todos los productos
    }
    //funcion para extraer productos del inventario
    public void extraerProductos(Scanner scanner) {
        String confirmacion;
        do {
            System.out.print("Ingrese el código del producto para la venta: ");
            String codigo = scanner.next();
            Producto producto = buscarProducto(codigo);
        //condicion para verificar si el producto existe
            if (producto != null) {
                System.out.print("Ingrese la cantidad del producto a vender: ");
                int cantidad = scanner.nextInt();
                if (producto.cantidadExistente >= cantidad) {
                    System.out.println("Vendiendo " + cantidad + " unidades del producto " + producto.nombreProducto);
                    producto.cantidadExistente -= cantidad;
                    producto.fechaExtraccion = new Date();
                    mostrarProducto(producto);
                    guardarDatosEnArchivo();
                } else {
                    System.out.println("No hay suficientes productos !");
                }
            } else {
                System.out.println("Lo siento, el producto que buscas no existe!");
            }
            System.out.print("¿Desea añadir otro producto? (S/N): ");
            confirmacion = scanner.next();
        } while (confirmacion.equalsIgnoreCase("S"));
    }
//funcion para eliminar un producto del inventario
    public void eliminarProducto(String codigo) {
        Producto producto = buscarProducto(codigo);
        if (producto != null) {
            inventario.remove(producto);
            System.out.println("Producto eliminado exitosamente.");
            guardarDatosEnArchivo();
        } else {
            System.out.println("El producto que intentas eliminar no existe.");
        }
    }
    //muestra la informacion del objeto de tipo Producto
    private void mostrarProducto(Producto producto) {
        System.out.println("Codigo: " + producto.codigoProducto);
        System.out.println("Nombre: " + producto.nombreProducto);
        System.out.println("Cantidad: " + producto.cantidadExistente);
        System.out.println("Precio Unitario: " + producto.precioUnitario);
        System.out.println("Total: " + producto.calcularTotal());
    }
//funcion para ingresar productos (existencias) al inventario
    public void ingresarProducto(String codigo, int cantidad) {
        Producto producto = buscarProducto(codigo);
        if (producto != null) {
            producto.cantidadExistente += cantidad;
            System.out.println("Existencias actualizadas:");
            mostrarProducto(producto);
        } else {
            System.out.println("Lo siento, el producto que buscas no existe");
        }
    }
}
//clase principal que contiene el metodo main
public class Main {
    public static void main(String[] args) {
        Farmacia farmacia = new Farmacia();
        cargarDatosDesdeArchivo(farmacia);// Cargar datos existentes desde el archivo
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("*********************************************");
            System.out.println("FARMACIAS EL DIFUNTO");
            System.out.println("*********************************************");
            System.out.println("1. Registrar medicamento en el inventario");
            System.out.println("2. Mostrar medicamentos del inventario");
            System.out.println("3. Ingresar existencias al inventario");
            System.out.println("4. Realizar Venta");
            System.out.println("5. Eliminar un producto del inventario");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el código del producto: ");
                    String codigo = scanner.next();
                    System.out.print("Ingrese el nombre del producto: ");
                    String nombre = scanner.next();
                    System.out.print("Ingrese la cantidad del producto: ");
                    int cantidad = scanner.nextInt();
                    System.out.print("Ingrese el precio unitario del producto: ");
                    double precio = scanner.nextDouble();
                    farmacia.registrarProducto(new Producto(codigo, nombre, cantidad, precio));
                    guardarDatosEnArchivo(farmacia); // Guardar datos actualizados en el archivo
                    System.out.println("Producto registrado exitosamente.");
                    break;

                case 2:
                    farmacia.mostrarProductos();
                    break;

                case 3:
                    System.out.print("Ingrese el código del producto a ingresar: ");
                    codigo = scanner.next();
                    System.out.print("Ingrese la cantidad del producto a ingresar: ");
                    cantidad = scanner.nextInt();
                    farmacia.ingresarProducto(codigo, cantidad);
                    guardarDatosEnArchivo(farmacia); // Guardar datos actualizados en el archivo
                    break;

                case 4:
                    farmacia.extraerProductos(scanner);
                    guardarDatosEnArchivo(farmacia); // Guardar datos actualizados en el archivo
                    break;

                case 5:
                    System.out.print("Ingrese el código del producto a eliminar: ");
                    codigo = scanner.next();
                    farmacia.eliminarProducto(codigo);
                    guardarDatosEnArchivo(farmacia); // Guardar datos actualizados en el archivo
                    break;
                case 6:
                    System.out.println("Gracias por preferir Farmcias EL DIFUNTO");
                    break;
                default:
                    System.out.println("Seleccionaste una opción inválida");
                    break;
            }
        } while (opcion != 6);

        scanner.close();
    }
    //funcion para cargar los datos de los productos desde el archivo de texto y los registra en el objeto de tipo Farmacia
    private static void cargarDatosDesdeArchivo(Farmacia farmacia) {
        String filePath = "c:/ficheros/farmacia.txt";
        try (Scanner fileScanner = new Scanner(new File(filePath))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    String codigo = parts[0];
                    String nombre = parts[1];
                    int cantidad = Integer.parseInt(parts[2]);
                    double precio = Double.parseDouble(parts[3]);
                    Producto producto = new Producto(codigo, nombre, cantidad, precio);
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    dateFormat.setTimeZone(TimeZone.getTimeZone("America/Guatemala"));
                    if (parts.length >= 5 && parts[4].length() > 0) {
                        Date fechaRegistro = dateFormat.parse(parts[4]);
                        producto.fechaRegistro = fechaRegistro;
                    }

                    if (parts.length >= 6 && parts[5].length() > 0) {
                        Date fechaExtraccion = dateFormat.parse(parts[5]);
                        producto.fechaExtraccion = fechaExtraccion;
                    }

                    farmacia.registrarProducto(producto);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("El archivo no existe. Se creará uno nuevo al guardar datos.");
        } catch (ParseException e) {
            System.out.println("Error al analizar las fechas desde el archivo.");
        }
    }//Try Catch para manejar la excepcion en caso de que el archivo no exista

    /*funcion para guardar los datos de los productos en el archivo de texto
    Se utiliza un bucle for para recorrer los productos y escribir los datos de cada producto en una linea individual*/
    private static void guardarDatosEnArchivo(Farmacia farmacia) {
        String filePath = "c:/ficheros/farmacia.txt";
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); // Formato de fecha
            dateFormat.setTimeZone(TimeZone.getTimeZone("America/Guatemala")); // Zona horaria
            for (Producto producto : farmacia.inventario) {
                String fechaRegistroStr = dateFormat.format(producto.fechaRegistro); // Convertir fecha a string
                String fechaExtraccionStr = producto.fechaExtraccion != null ? dateFormat.format(producto.fechaExtraccion) : ""; // Convertir fecha a string si existe
                writer.println(producto.codigoProducto + "," + producto.nombreProducto + "," +
                        producto.cantidadExistente + "," + producto.precioUnitario + "," +
                        fechaRegistroStr + "," + fechaExtraccionStr);
            }
        } catch (IOException e) {
            System.out.println("Error al guardar los datos en el archivo.");
        }
    }
}