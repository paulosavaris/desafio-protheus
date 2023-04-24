package org.example.consultaApi;


import org.example.dadosApi.AcoesStock;

import com.google.gson.Gson;
import org.example.dadosApi.DadosAcoes;
import org.example.postgres.InsercaoBanco;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class AcoesStockParcial {

    private static Scanner teclado = new Scanner(System.in);

    // Método principal que consulta informações parciais sobre ações e as insere no banco de dados
    public static void acoesAvailableParcial() throws IOException, InterruptedException {
        // Cria um objeto AcoesStockParcial para chamar o método acoesParciais()
        AcoesStockParcial newAcoesParc = new AcoesStockParcial();

        // Chama o método acoesParciais() para obter informações parciais sobre as ações inseridas pelo usuário
        AcoesStock meuEstoque = newAcoesParc.acoesParciais();

        DadoAcoesStock dadoAcoesStock = new DadoAcoesStock(); // Cria uma instância da classe que contém o método getDadosAcoes()


        // Cria um objeto InsercaoBanco para inserir as informações no banco de dados PostgreSQL
        InsercaoBanco insercaoBanco = new InsercaoBanco();

        // Itera pelas ações do estoque e obtém as informações detalhadas de cada ação para inserir no banco de dados
        for (String acao : meuEstoque.getStocks()) {
            System.out.println("Informações da ação " + acao + ":");
            //itera sobre os resultados retornados
            for (DadosAcoes.Resultado resultado : dadoAcoesStock.getDadosAcoes(acao).getResultados()) {
                // Insere as informações da ação e da cotação no banco de dados
                System.out.println(resultado.toString());
                insercaoBanco.inserirAcao(resultado);
                insercaoBanco.inserirCotacao(resultado);
            }
        }
    }

    // Método que obtém informações parciais sobre uma ação a partir da entrada do usuário
    public AcoesStock acoesParciais() throws IOException, InterruptedException {
        System.out.println("Informe uma acao, nao precisa ser o nome inteiro");
        var acao = teclado.next();
        // Constrói a URL de consulta concatenando o código da ação com a URL base
        String endereco = "https://brapi.dev/api/available?search=" + acao;

        // Faz uma requisição GET para a API BrAPI e obtém uma resposta com informações parciais sobre as ações correspondentes
        // Cria um objeto HttpClient para fazer a requisição HTTP
        HttpClient client = HttpClient.newHttpClient();
        // Constrói um objeto HttpRequest com a URL da consulta
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(endereco)).build();
        // Envia a requisição HTTP e recebe a resposta em um objeto HttpResponse
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Converte a resposta em uma string JSON e, em seguida, em um objeto AcoesStock usando a biblioteca Gson
        String json = response.body();
        Gson gson = new Gson();
        AcoesStock estoque = gson.fromJson(json, AcoesStock.class);
        System.out.println(estoque);
        return estoque;
    }


}
