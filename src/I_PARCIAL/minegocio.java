package I_PARCIAL;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
//leer y escribir datos en un txt
public class minegocio {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String cadena;
        int opcion;
        do {
            System.out.println("1. Escribir en fichero");
            System.out.println("2. Leer fichero");
            System.out.println("3. Salir");
            System.out.println("Introduce opción:");
            opcion = sc.nextInt();
            switch (opcion) {
                case 1 -> {
                    try (FileWriter fw = new FileWriter("c:/ficheros/datos.txt", true);
                         PrintWriter salida = new PrintWriter(fw)) {
                        System.out.println("Introduce texto. Para acabar introduce la cadena FIN:");
                        cadena = sc.nextLine();                             //se introduce por teclado una cadena de texto
                        while (!cadena.equalsIgnoreCase("FIN")) {
                            salida.println(cadena);                         //se escribe la cadena en el fichero
                            cadena = sc.nextLine();                         //se introduce por teclado una cadena de texto
                        }
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
                case 2 -> {
                    try (FileReader fr = new FileReader("c:/ficheros/datos.txt");
                         Scanner entrada = new Scanner(fr)) {
                        while (entrada.hasNext()) {
                            String cadena2 = entrada.nextLine();
                            System.out.println(cadena2);
                        }
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
                case 3 -> System.out.println("Fin del programa");
                default -> System.out.println("Opción incorrecta");
            }
        } while (opcion != 3);
    }
}
