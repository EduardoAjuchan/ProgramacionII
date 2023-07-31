package I_PARCIAL;
public class ForReloj {
        public static void main(String[] args) {
            for (int hora = 0; hora < 24; hora++) {
                for (int minuto = 0; minuto < 60; minuto++) {
                    for (int segundo = 0; segundo < 60; segundo++) {
                        System.out.printf("%02d:%02d:%02d%n", hora, minuto, segundo);
                        try {
                            Thread.sleep(1000); // Pausa durante 1 segundo (1000 milisegundos)
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }


