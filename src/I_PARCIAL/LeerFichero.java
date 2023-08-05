package I_PARCIAL;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
//lee datos escritos en un fichero
public class LeerFichero {
    public static void main(String[] args) {
        try (FileReader fr = new FileReader("c:/ficheros/datos.txt");
             Scanner entrada = new Scanner(fr)) {
            while (entrada.hasNext()) {
                String cadena = entrada.nextLine();
                System.out.println(cadena);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
