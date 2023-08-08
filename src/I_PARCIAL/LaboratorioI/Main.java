package I_PARCIAL.LaboratorioI;
/*Autor: EDUARDO AJUCHAN
Proyecto: LABORATORIO 1
PROGRAMACION II*/
import java.io.*;
import java.util.*;
class Producto {
    String codigoProducto;
    String nombreProducto;
    int cantidadExistente;
    double precioUnitario;
    public Producto(String codigoProducto, String nombreProducto, int cantidadExistente, double precioUnitario) {
        this.codigoProducto = codigoProducto;
        this.nombreProducto = nombreProducto;
        this.cantidadExistente = cantidadExistente;
        this.precioUnitario = precioUnitario;
    }
    public double calcularTotal() {
        return cantidadExistente * precioUnitario;
    }
}
class Farmacia {
    List<Producto> inventario = new ArrayList<>();

    public void registrarProducto(Producto producto) {
        inventario.add(producto);
    }
    public void mostrarProductos() {
        if (inventario.isEmpty()) {
            System.out.println("Lo siento!!! No hay productos");
            return;
        }
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
            System.out.println("Existencia actualizada:");
            mostrarProducto(producto);
        } else {
            System.out.println("Lo siento, producto no existe");
        }
    }
    public void extraerProducto(String codigo, int cantidad) {
        Producto producto = buscarProducto(codigo);
        if (producto != null) {
            if (producto.cantidadExistente >= cantidad) {
                producto.cantidadExistente -= cantidad;
                System.out.println("Existencia actualizada:");
                mostrarProducto(producto);
            } else {
                System.out.println("Existencia insuficiente !!!");
            }
        } else {
            System.out.println("Lo siento, producto no existe !!!");
        }
    }
    private void mostrarProducto(Producto producto) {
        System.out.println("Codigo: " + producto.codigoProducto);
        System.out.println("Nombre: " + producto.nombreProducto);
        System.out.println("Cantidad: " + producto.cantidadExistente);
        System.out.println("Precio Unitario: " + producto.precioUnitario);
        System.out.println("Total: " + producto.calcularTotal());
    }
}
public class Main {
    public static void main(String[] args) {
        Farmacia farmacia = new Farmacia();
        cargarDatosDesdeArchivo(farmacia); // Cargar datos existentes desde el archivo
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("Menú Principal");
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
                    System.out.println("Producto guardado exitosamente");
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
                    System.out.println("¡Hasta luego!");
                    break;

                default:
                    System.out.println("Opción inválida");
                    break;
            }
        } while (opcion != 5);

        scanner.close();
    }
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
    }
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
