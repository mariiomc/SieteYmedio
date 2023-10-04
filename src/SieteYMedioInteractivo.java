import java.util.*;

public class SieteYMedioInteractivo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        Stack<Double> Baraja = new Stack<Double>();

        while (true) {
            System.out.println("¡Bienvenido a Siete y Medio!");
            setBaraja(Baraja);
            Stack<Double> manoJugador = new Stack<Double>();
            Stack<Double> manoComputadora = new Stack<Double>();

            // Barajar el mazo
            mezclarBaraja(Baraja, random);

            // Repartir las cartas
            repartirBaraja(Baraja, manoJugador, manoComputadora);

            // Mostrar la mano inicial del jugador
            System.out.println("Tu mano inicial: ");
            mostrarMano(manoJugador);

            // Turno del jugador
            turnoJugador(manoJugador, Baraja, scanner);

            // Turno de la computadora
            turnoComputadora(manoComputadora, Baraja, random);

            // Determinar al ganador
            determinarGanador(manoJugador, manoComputadora, Baraja);

            System.out.println("¿Quieres jugar de nuevo? (s/n)");
            String respuesta = scanner.next();
            if (!respuesta.equalsIgnoreCase("s")) {
                break;
            }
        }

        System.out.println("Gracias por jugar Siete y Medio. ¡Hasta la próxima!");
        scanner.close();
    }


    public static Stack<Double> setBaraja(Stack<Double> Baraja) {

        //Añade las cartas del 1 al 7 de los 4 palos
        for (int r = 1; r <= 4; r++) {
            for (int i = 1; i <= 7; i++) {
                Baraja.push(Double.valueOf(i));
            }
        }
            //Añada sota, caballo y rey de cada palo con un valor de 0.5
        for(int v =1; v <= 4; v++) {
            for (int a = 1; a <= 4; a++) {
                Baraja.push(0.5);
            }
        }
        int num_cartas_3 = Baraja.size();
        System.out.println("La baraja tiene " + num_cartas_3 + " cartas.");
        return Baraja;

    }
    static void mezclarBaraja(Stack<Double> Baraja, Random random){
        for(int i = Baraja.size() -1 ; i>0;i--){
            int j = random.nextInt(i+1);
            Double temp = Baraja.get(i);
            Baraja.set(i, Baraja.get(j));
            Baraja.set(j, temp);
        }
    }

    static void repartirBaraja(Stack<Double> Baraja, Stack<Double> manoJugador, Stack<Double> manoComputadora){
        for (int i = 0; i < 2; i++) {
            manoJugador.add(Baraja.remove(0));
            manoComputadora.add(Baraja.remove(0));
        }
    }
    static void mostrarMano(Stack<Double> mano) {
        for (Double mano1 : mano) {
            System.out.println(mano1);
        }
    }

    static void turnoJugador(Stack<Double> mano, Stack<Double> Baraja, Scanner scanner) {
        while (true) {
            double total = calcularTotal(mano);
            System.out.println("Total de tu mano: " + total);
            if (total > 7.5) {
                System.out.println("Te has pasado de 7.5. ¡Has perdido!");
                break;
            }
            System.out.println("¿Quieres otra carta? (s/n)");
            String respuesta = scanner.next();
            if (!respuesta.equalsIgnoreCase("s")) {
                break;
            }
            double carta = Baraja.pop();
            mano.push(carta);
            System.out.println("Has sacado un: " +  carta);
            int num_cartas = Baraja.size();
            System.out.println("Quedan " + num_cartas + " cartas en la baraja.");
        }
    }

    static void turnoComputadora(Stack<Double> mano, Stack<Double> Baraja, Random random) {
        while (calcularTotal(mano) < 5.5) {
            mano.push(Baraja.pop());
        }
    }

    static double calcularTotal(Stack<Double> mano) {
        double total = 0;
        for (Double carta  : mano) {
            total += carta.doubleValue();
        }
        return total;
    }

    static void determinarGanador(Stack<Double> manoJugador, Stack<Double> manoComputadora, Stack<Double> Baraja) {
        double totalJugador = calcularTotal(manoJugador);
        double totalComputadora = calcularTotal(manoComputadora);

        System.out.println("Total de tu mano: " + totalJugador);
        System.out.println("Total de la mano de la computadora: " + totalComputadora);

        if (totalJugador > 7.5 || (totalComputadora <= 7.5 && totalComputadora > totalJugador)) {
            System.out.println("¡Gana la computadora!");
        } else if (totalComputadora > 7.5 || totalJugador > totalComputadora) {
            System.out.println("¡Ganas tú! ");
        } else {
            System.out.println("Es un empate.");
        }

        while(Baraja.isEmpty() == false) {
            Baraja.pop();
        }
        int num_cartas_2 = Baraja.size();
        System.out.println("Número de cartas en la baraja: " + num_cartas_2);
    }
}