package I_PARCIAL;

public class CuadroFor {
    public static void main(String[] args) {
        for (int i = 0; i < 80; i++) {
            System.out.print("*");
        }
        System.out.println();
        for (int i = 0; i < 22; i++) {
            System.out.print("*");
            for (int j = 0; j < 78; j++) {
                System.out.print(" ");
            }
            System.out.println("*");
        }
        for (int i = 0; i < 80; i++) {
            System.out.print("*");
        }
    }
}
