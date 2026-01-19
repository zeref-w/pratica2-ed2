import java.io.*;
import java.util.*;

public class Main {

    private static PrintWriter saida;

    public static void main(String[] args) {
        try {
            Grafo g = new Grafo();
            saida = new PrintWriter(new FileWriter("src/saida.txt"));

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

            imprimir("Grafo carregado!");
            imprimir("Vertices: " + g.numeroVertices());
            imprimir("Arestas: " + g.numeroArestas());
            imprimir("");

            imprimir("Rota curta:");
            calcular(g, "cohama", "cohafuma");
            imprimir("");

            imprimir("Rota media:");
            calcular(g, "cohama", "centro");
            imprimir("");

            imprimir("Rota longa:");
            calcular(g, "ipase", "aeroporto");
            imprimir("");

            imprimir("Rota teste:");
            calcular(g, "sao-cristovao", "divineia");

            saida.close();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            if (saida != null) saida.close();
        }
    }

    static void imprimir(String texto) {
        System.out.println(texto);
        saida.println(texto);
    }

    static void calcular(Grafo grafo, String origem, String destino) {
        Map<String, String> pai = new HashMap<>();
        Map<String, Double> distancias = Dijkstra.executar(grafo, origem, pai);

        double distancia = distancias.get(destino);
        List<String> caminho = Dijkstra.obterCaminho(origem, destino, pai);

        imprimir("De: " + origem);
        imprimir("Para: " + destino);
        imprimir("Distancia: " + distancia + " km");

        StringBuilder caminhoStr = new StringBuilder("Caminho: ");
        for (int i = 0; i < caminho.size(); i++) {
            caminhoStr.append(caminho.get(i));
            if (i < caminho.size() - 1) caminhoStr.append(" -> ");
        }
        imprimir(caminhoStr.toString());
    }
}