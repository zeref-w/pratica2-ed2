import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        try {
            Grafo g = new Grafo();

            BufferedReader br = new BufferedReader(new FileReader("src/entrada.txt"));
            String linha;

            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(" ");
                String v1 = dados[0];
                String v2 = dados[1];
                double dist = Double.parseDouble(dados[2]);
                g.inserirAresta(v1, v2, dist);
            }
            br.close();

            System.out.println("Grafo carregado!");
            System.out.println("Vertices: " + g.numeroVertices());
            System.out.println("Arestas: " + g.numeroArestas());
            System.out.println();

            System.out.println("ROTA CURTA:");
            calcular(g, "cohama", "cohafuma");
            System.out.println();

            System.out.println("ROTA MEDIA:");
            calcular(g, "cohama", "centro");
            System.out.println();

            System.out.println("ROTA LONGA:");
            calcular(g, "ipase", "aeroporto");

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    static void calcular(Grafo grafo, String origem, String destino) {
        Map<String, String> pai = new HashMap<>();
        Map<String, Double> distancias = Dijkstra.executar(grafo, origem, pai);

        double distancia = distancias.get(destino);
        List<String> caminho = Dijkstra.obterCaminho(origem, destino, pai);

        System.out.println("De: " + origem);
        System.out.println("Para: " + destino);
        System.out.println("Distancia: " + distancia + " km");
        System.out.print("Caminho: ");
        for (int i = 0; i < caminho.size(); i++) {
            System.out.print(caminho.get(i));
            if (i < caminho.size() - 1) System.out.print(" -> ");
        }
        System.out.println();
    }
}