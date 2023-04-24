package org.example.consultaApi;

import com.google.gson.Gson;
import org.example.dadosApi.DadosAcoes;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class DadoAcoesStock {

    DadosAcoes getDadosAcoes(String acao) throws IOException, InterruptedException {
        String endereco = "https://brapi.dev/api/quote/" + acao; // URL da API que retorna os dados da ação informada

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(endereco)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        String json = response.body();
        Gson gson = new Gson();
        DadosAcoes dadosAcoes = gson.fromJson(json, DadosAcoes.class);
        return dadosAcoes;
    }
}

/*o método "getDadosAcoes" recebe um código de ação, busca as informações dessa ação através de uma API,
 converte as informações em um objeto do tipo DadosAcoes e retorna esse objeto.*/
