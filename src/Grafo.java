import java.util.*;
public class Grafo {

    private Map<String, List<Aresta>> adj;
    private int numVertices;
    private int numArestas;

    public Grafo() {
        this.adj = new HashMap<>();
        this.numVertices = 0;
        this.numArestas = 0;
    }

    public void inserirVertice(String v) {
        if (!adj.containsKey(v)) {
            adj.put(v, new ArrayList<>());
            numVertices++;
        }
    }

    public void inserirAresta(String u, String v, double peso) {
        inserirVertice(u);
        inserirVertice(v);

        adj.get(u).add(new Aresta(v, peso));

        adj.get(v).add(new Aresta(u, peso));

        numArestas++;
    }

    public void removerVertice(String v) {
        if (!adj.containsKey(v)) return;

        for (String u : adj.keySet()) {
            removerAresta(u, v);
        }

        adj.remove(v);
        numVertices--;
    }

    public void removerAresta(String u, String v) {
        if (adj.containsKey(u)) {
            adj.get(u).removeIf(aresta -> aresta.getDestino().equals(v));
        }
        if (adj.containsKey(v)) {
            adj.get(v).removeIf(aresta -> aresta.getDestino().equals(u));
        }
        numArestas--;
    }

    public List<Aresta> obterAdjacentes(String v) {
        return adj.getOrDefault(v, new ArrayList<>());
    }

    public Set<String> obterVertices() {
        return adj.keySet();
    }

    public boolean existeAresta(String u, String v) {
        if (!adj.containsKey(u)) return false;

        for (Aresta a : adj.get(u)) {
            if (a.getDestino().equals(v)) {
                return true;
            }
        }
        return false;
    }

    public boolean existeVertice(String v) {
        return adj.containsKey(v);
    }

    public int numeroVertices() {
        return numVertices;
    }

    public int numeroArestas() {
        return numArestas;
    }

    public void imprimir() {
        System.out.println("\nGrafo (Lista de Adjacências)");
        System.out.println("Vértices: " + numVertices);
        System.out.println("Arestas: " + numArestas);
        System.out.println();

        for (String v : adj.keySet()) {
            System.out.print(v + " -> ");
            List<Aresta> arestas = adj.get(v);
            for (int i = 0; i < arestas.size(); i++) {
                Aresta a = arestas.get(i);
                System.out.print(a.toString());
                if (i < arestas.size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        }
    }
}