import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Scanner lectura = new Scanner(System.in);
        Set<String> estadosValidos = new HashSet<>(Arrays.asList(
                "1","2","3","5","6","7","10","11","12","13","14"
        ));

        String q0;
        do {
            System.out.print("Ingrese el inicial del nodo, rango (1-14): ");
            q0 = lectura.nextLine().trim();
            if (!estadosValidos.contains(q0)) {
                System.out.println("No es un estado válido del autómata.");
            }
        } while (!estadosValidos.contains(q0));

        String qf;
        do {
            System.out.print("Ingrese el inicial del nodo, rango (2-14): ");
            qf = lectura.nextLine().trim();
            if (!estadosValidos.contains(qf)) {
                System.out.println(" No es un estado válido.");
            }
        } while (!estadosValidos.contains(qf));

        Set<String> F = new HashSet<>(Collections.singletonList(qf));
        Automata automata = new Automata(q0, F);

        automata.agregarTransicion("1", "7", 89);
        automata.agregarTransicion("1", "12", 80);
        automata.agregarTransicion("1", "2", 47);
        automata.agregarTransicion("1", "3", 75);
        automata.agregarTransicion("2", "12", 82);
        automata.agregarTransicion("2", "5", 35);
        automata.agregarTransicion("2", "15", 99);
        automata.agregarTransicion("3", "5", 73);
        automata.agregarTransicion("5", "6", 99);
        automata.agregarTransicion("6", "17", 79);
        automata.agregarTransicion("6", "15", 88);
        automata.agregarTransicion("7", "16", 61);
        automata.agregarTransicion("10", "14", 65);
        automata.agregarTransicion("10", "11", 34);
        automata.agregarTransicion("10", "16", 89);
        automata.agregarTransicion("11", "12", 93);
        automata.agregarTransicion("11", "15", 80);
        automata.agregarTransicion("11", "7", 85);
        automata.agregarTransicion("12", "15", 80);
        automata.agregarTransicion("13", "15", 64);
        automata.agregarTransicion("13", "17", 88);
        automata.agregarTransicion("14", "15", 88);
        automata.buscarPalabraMinima();
    }
}
