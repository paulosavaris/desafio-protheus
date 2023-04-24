package org.example.consultaApi;

import com.google.gson.Gson;
import org.example.dadosApi.DadosAcoes;
import org.example.postgres.InsercaoBanco;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.SQLException;
import java.util.Scanner;

public class InformacoesDasAcoes {
    private static Scanner teclado = new Scanner(System.in);

    public String consultaDadosAcoes() throws IOException, InterruptedException, SQLException {
        System.out.println("Informe uma ou mais acoes separadas por vírgula (ex: PETR4,MGLU3,B3SA3): ");
        var acao = teclado.next();
        System.out.println("\n");
        // Cria a URL da API com as ações inseridas pelo usuário
        String endereco = "https://brapi.dev/api/quote/" + String.join("%2C", acao.split(","));

        // Cria um cliente HTTP e faz uma solicitação GET para a API com a URL criada acima
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(endereco)).build();
        HttpResponse<String> reponse = client.send(request, HttpResponse.BodyHandlers.ofString());

        //extrai o json
        String json = reponse.body();
        //System.out.println(json);
        Gson gson = new Gson();
       //desserialização do objeto JSON que é retornado da API para um objeto do tipo DadosAcoes, utilizando a biblioteca Gson.
        DadosAcoes minhaAcao = gson.fromJson(json, DadosAcoes.class);//

        // Cria uma instância de InsercaoBanco para inserir as informações no banco de dados PostgreSQL
        InsercaoBanco inserteBD = new InsercaoBanco();

        // Percorre o Array para poder trazer mais de uma acao e exibe as informações da ação para o usuário
        for (DadosAcoes.Resultado resultado : minhaAcao.getResultados()) {
            System.out.println("TEste 1  " + resultado.toString());
            inserteBD.inserirAcao(resultado);
            inserteBD.inserirCotacao(resultado);
        }
        System.out.println("Ações salvas no banco de dados");

        // Retorna as ações inseridas pelo usuário
        return acao;
    }

}
