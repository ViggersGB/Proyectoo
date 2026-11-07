import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class Automata {
    private Set<String> estados;
    private String estadoInicial;
    private Set<String> estadosFinales;
    private List<Transicion> transiciones;

    public Automata(String estadoInicial, Set<String> estadosFinales) {
        this.estados = new LinkedHashSet<>(Arrays.asList(
                "1","2","3","5","6","7","10","11","12","13","14"
        ));
        this.estadoInicial = estadoInicial;
        this.estadosFinales = estadosFinales;
        this.transiciones = new ArrayList<>();
    }
    public void agregarTransicion(String a, String b, int peso) {
        transiciones.add(new Transicion(a, b, peso));
        transiciones.add(new Transicion(b, a, peso));
    }

    public Set<String> getEstados() {
        return estados;
    }

    public void buscarPalabraMinima() {

        Map<String, Integer> dist = new HashMap<>();
        Map<String, String> pred = new HashMap<>();
        Map<String, Integer> pesoPred = new HashMap<>();

        for (String q : estados) {
            dist.put(q, Integer.MAX_VALUE);
        }
        dist.put(estadoInicial, 0);

        PriorityQueue<Transicion> cola =
                new PriorityQueue<>(Comparator.comparingInt(Transicion::getPeso));
        cola.add(new Transicion(null, estadoInicial, 0));
        Set<String> visitados = new HashSet<>();
        while (!cola.isEmpty()) {
            Transicion t = cola.poll();
            String actual = t.getEstadoDestino();
            if (visitados.contains(actual)) continue;
            visitados.add(actual);
            if (estadosFinales.contains(actual)) {
                imprimirResultado(actual, dist, pred, pesoPred);
                return;
            }

            for (Transicion tr : transiciones) {
                if (!tr.getEstadoOrigen().equals(actual)) continue;

                String vecino = tr.getEstadoDestino();
                int nuevoCosto = dist.get(actual) + tr.getPeso();

                if (nuevoCosto < dist.getOrDefault(vecino, Integer.MAX_VALUE)) {
                    dist.put(vecino, nuevoCosto);
                    pred.put(vecino, actual);
                    pesoPred.put(vecino, tr.getPeso());
                    cola.add(new Transicion(actual, vecino, nuevoCosto));
                }
            }
        }
        System.out.println("no existe camino");
    }
    private void imprimirResultado(String finalState,
                                   Map<String, Integer> dist,
                                   Map<String, String> pred,
                                   Map<String, Integer> pesoPred) {

        LinkedList<String> palabra = new LinkedList<>();
        LinkedList<Integer> pesos = new LinkedList<>();
        String paso = finalState;

        while (paso != null) {
            palabra.addFirst(paso);
            if (pesoPred.containsKey(paso)) pesos.addFirst(pesoPred.get(paso));
            paso = pred.get(paso);
        }

        StringBuilder camino = new StringBuilder();
        for (int i = 0; i < palabra.size(); i++) {
            camino.append(palabra.get(i));
            if (i < pesos.size()) {
                camino.append(" --(").append(pesos.get(i)).append(")--> ");
            }
        }

        System.out.println("El autómata ACEPTA la palabra:");
        System.out.println("   " + camino);
        System.out.println();
        System.out.println("   Estado inicial q0 : " + estadoInicial);
        System.out.println("   Estado final  qf : " + finalState);
        System.out.println("   Costo total      : " + dist.get(finalState));
        System.out.println("   Nº de transiciones: " + pesos.size());
    }
}