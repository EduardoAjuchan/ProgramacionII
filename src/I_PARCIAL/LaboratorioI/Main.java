package I_PARCIAL.LaboratorioI;
/*Autor: EDUARDO AJUCHAN
Proyecto: LABORATORIO 1
PROGRAMACION II*/
import java.io.*;
import java.util.*;
//Definiendo tipo de datos para el producto
class Producto {
    String codigoProducto;
    String nombreProducto;
    int cantidadExistente;
    double precioUnitario;
    //Constructor de la clase Producto con sus atributos y parametros de entrada (Creando un objeto)
    public Producto(String codigoProducto, String nombreProducto, int cantidadExistente, double precioUnitario) {
        this.codigoProducto = codigoProducto;
        this.nombreProducto = nombreProducto;
        this.cantidadExistente = cantidadExistente;
        this.precioUnitario = precioUnitario;
    }
    //metodo para calcular el valor si se vendieran todas las unidades de un producto a su precio unitario
    public double calcularTotal() {
        return cantidadExistente * precioUnitario;
    }
}
//Lista para almacenar los objetos de tipo Producto
class Farmacia {
    List<Producto> inventario = new ArrayList<>();

    //metodo para agregar un producto al inventario de la farmacia pasando un objeto de tipo Producto
    public void registrarProducto(Producto producto) {
        // Verificar si ya existe un producto con el mismo código
        Producto productoExistente = buscarProducto(producto.codigoProducto);
        if (productoExistente != null) {
            System.out.println("Ya existe un producto con el mismo código.");
            return;
        } else {
            inventario.add(producto);

        }
    }

    //metodo para mostrar los productos del inventario verificando primero si hay productos ingresados
    public void mostrarProductos() {
        if (inventario.isEmpty()) {
            System.out.println("Lo siento!!! No hay productos");
            return;
        }
        //muestra la informacion de los productos existentes y calcula el gran total
        double granTotal = 0;
        for (Producto producto : inventario) {
            double total = producto.calcularTotal();
            granTotal += total;
            System.out.println("Codigo: " + producto.codigoProducto);
            System.out.println("Nombre: " + producto.nombreProducto);
            System.out.println("Cantidad: " + producto.cantidadExistente);
            System.out.println("Precio Unitario: " + producto.precioUnitario);
            System.out.println("Total: " + total);
            System.out.println();
        }
        System.out.println("Gran Total: " + granTotal);
    }
    //metodo para buscar un producto por su codigo y retornar el objeto de tipo Producto
    public Producto buscarProducto(String codigo) {
        for (Producto producto : inventario) {
            if (producto.codigoProducto.equals(codigo)) {
                return producto;
            }
        }
        return null;
    }
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
    //busca un producto por su codigo y verifica si hay existencias suficientes para extraer
    public void extraerProducto(String codigo, int cantidad) {
        Producto producto = buscarProducto(codigo);
        if (producto != null) {
            if (producto.cantidadExistente >= cantidad) {
                producto.cantidadExistente -= cantidad;
                System.out.println("Existencias actualizada:");
                mostrarProducto(producto);
            } else {
                System.out.println("No hay suficientes productos !!!");
            }
        } else {
            System.out.println("Lo siento, el producto que buscas no existe !!!");
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
}
//clase principal que contiene el metodo main
public class Main {
    public static void main(String[] args) {
        Farmacia farmacia = new Farmacia();
        cargarDatosDesdeArchivo(farmacia); // Cargar datos existentes desde el archivo
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("*********************************************");
            System.out.println("FARMACIAS EL DIFUNTO");
            System.out.println("*********************************************");
            System.out.println("1. Registrar producto en el inventario");
            System.out.println("2. Mostrar productos del inventario");
            System.out.println("3. Ingresar producto al inventario");
            System.out.println("4. Extraer producto del inventario");
            System.out.println("5. Salir");
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
                    System.out.print("Ingrese el código del producto a extraer: ");
                    codigo = scanner.next();
                    System.out.print("Ingrese la cantidad del producto a extraer: ");
                    cantidad = scanner.nextInt();
                    farmacia.extraerProducto(codigo, cantidad);
                    guardarDatosEnArchivo(farmacia); // Guardar datos actualizados en el archivo
                    break;

                case 5:
                    System.out.println("Gracias por preferir Farmcias El Difunto");
                    break;

                default:
                    System.out.println("Seleccionaste una opción inválida");
                    break;
            }
        } while (opcion != 5);

        scanner.close();
    }
    //funcion para cargar los datos de los productos desde el archivo de texto y los registra en el objeto de tipo Farmacia
    private static void cargarDatosDesdeArchivo(Farmacia farmacia) {
        String filePath = "c:/ficheros/farmacia.txt";
        try (Scanner fileScanner = new Scanner(new File(filePath))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String codigo = parts[0];
                    String nombre = parts[1];
                    int cantidad = Integer.parseInt(parts[2]);
                    double precio = Double.parseDouble(parts[3]);
                    farmacia.registrarProducto(new Producto(codigo, nombre, cantidad, precio));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("El archivo no existe. Se creará uno nuevo al guardar datos.");
        }
    } //Try Catch para manejar la excepcion en caso de que el archivo no exista

    /*funcion para guardar los datos de los productos en el archivo de texto
    Se utiliza un bucle for para recorrer los productos y escribir los datos de cada producto en una linea individual*/
    private static void guardarDatosEnArchivo(Farmacia farmacia) {
        String filePath = "c:/ficheros/farmacia.txt";
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (Producto producto : farmacia.inventario) {
                writer.println(producto.codigoProducto + "," + producto.nombreProducto + "," +
                        producto.cantidadExistente + "," + producto.precioUnitario);
            }
        } catch (IOException e) {
            System.out.println("Error al guardar los datos en el archivo.");
        }
    }
}