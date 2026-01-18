import java.util.*;

public class Dijkstra {

    private static class Vertice implements Comparable<Vertice> {
        String nome;
        double distancia;

        public Vertice(String nome, double distancia) {
            this.nome = nome;
            this.distancia = distancia;
        }

        @Override
        public int compareTo(Vertice outro) {
            return Double.compare(this.distancia, outro.distancia);
        }
    }

    public static Map<String, Double> executar(Grafo grafo, String origem, Map<String, String> pai) {

        Map<String, Double> distancias = new HashMap<>();
        Set<String> visitados = new HashSet<>();

        PriorityQueue<Vertice> fila = new PriorityQueue<>();

        for (String vertice : grafo.obterVertices()) {
            distancias.put(vertice, Double.MAX_VALUE);
            pai.put(vertice, null);
        }
        distancias.put(origem, 0.0);

        for (String vertice : grafo.obterVertices()) {
            fila.add(new Vertice(vertice, distancias.get(vertice)));
        }

        while (!fila.isEmpty()) {
            Vertice verticeAtual = fila.poll();
            String atual = verticeAtual.nome;

            if (visitados.contains(atual)) {
                continue;
            }

            visitados.add(atual);

            for (Aresta aresta : grafo.obterAdjacentes(atual)) {
                String vizinho = aresta.getDestino();
                double peso = aresta.getPeso();

                if (distancias.get(vizinho) > distancias.get(atual) + peso) {
                    distancias.put(vizinho, distancias.get(atual) + peso);

                    pai.put(vizinho, atual);

                    fila.add(new Vertice(vizinho, distancias.get(vizinho)));
                }
            }
        }

        return distancias;
    }

    public static List<String> obterCaminho(String origem, String destino,
                                            Map<String, String> pai) {
        List<String> caminho = new ArrayList<>();

        if (pai.get(destino) == null && !destino.equals(origem)) {
            return caminho;
        }

        String atual = destino;
        while (atual != null) {
            caminho.add(0, atual);
            atual = pai.get(atual);
        }

        return caminho;
    }

    public static Object[] calcularRota(Grafo grafo, String origem, String destino) {
        Map<String, String> pai = new HashMap<>();
        Map<String, Double> distancias = executar(grafo, origem, pai);

        double distancia = distancias.get(destino);
        List<String> caminho = obterCaminho(origem, destino, pai);

        return new Object[]{distancia, caminho};
    }
}