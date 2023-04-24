package org.example.consultaApi;

import com.google.gson.Gson;
import org.example.dadosApi.AcoesStock;
import org.example.dadosApi.DadosAcoes;
import org.example.postgres.InsercaoBanco;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class TodasAcoes {

    private static Scanner teclado = new Scanner(System.in);

    public static void allStocks() throws IOException, InterruptedException {
        TodasAcoes newAcoes = new TodasAcoes();
        AcoesStock meuEstoque = newAcoes.consultaAcoesStock(); // Obtem a lista de ações disponíveis

        // Objeto para inserção de dados no banco de dados
        InsercaoBanco insercaoBanco = new InsercaoBanco();

        DadoAcoesStock dadoAcoesStock = new DadoAcoesStock(); // Cria uma instância da classe que contém o método getDadosAcoes()

        int quantidade = 0;
        // Percorre todas as ações disponíveis
        for (String acao : meuEstoque.getStocks()) {
            System.out.println("Informações da ação " + acao + ":");
            for (DadosAcoes.Resultado resultado : dadoAcoesStock.getDadosAcoes(acao).getResultados()) {
                //System.out.println(resultado.toString());
                System.out.println("Baixando informacoes no banco de dados");
                // Insere as informações da ação no banco de dados
                insercaoBanco.inserirAcao(resultado);
                insercaoBanco.inserirCotacao(resultado);
                System.out.println("Informacoes baixadas no banco de dados");
            }
            quantidade++;
        }

        System.out.println(quantidade);
        System.out.println("Fim do loop For");
    }
    public AcoesStock consultaAcoesStock() throws IOException, InterruptedException {
        String endereco = "https://brapi.dev/api/available";

        // Cria um cliente HTTP e faz uma solicitação GET para a API com a URL criada acima
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(endereco)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        //extrai o json
        String json = response.body();
        System.out.println(json);
        Gson gson = new Gson();
        // desserialização do objeto JSON que é retornado da API para um objeto do tipo DadosAcoes, utilizando a biblioteca Gson.
        AcoesStock estoque = gson.fromJson(json, AcoesStock.class);
        // fromJson recebe Json como 1 parametro, DadosStock = classe para qual deve ser convertido o Json,
        // retorna objeto do tipo DadosAcoes contendo as informacoes requisitadas
        System.out.println(estoque);
        return estoque;
    }

  /*  DadosAcoes getDadosAcoes(String acao) throws IOException, InterruptedException {
        String endereco = "https://brapi.dev/api/quote/" + acao; // URL da API que retorna os dados da ação informada

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(endereco)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        String json = response.body();
        Gson gson = new Gson();
        DadosAcoes dadosAcoes = gson.fromJson(json, DadosAcoes.class);
        return dadosAcoes;
    }*/

    public  static void dados(String acao) throws IOException, InterruptedException {
        DadoAcoesStock dadosAcoes = new DadoAcoesStock();
        dadosAcoes.getDadosAcoes(acao);
    }

}
