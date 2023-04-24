package org.example.dadosApi;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AcoesStock {
    @SerializedName("stocks") //usada para definir que essa variável "stocks" está mapeada para o campo "stocks" na resposta JSON da API.
    private ArrayList<String> stocks;

    public ArrayList<String> getStocks() {
        return stocks;
    }

    public void setStocks(ArrayList<String> stocks) {
        this.stocks = stocks;
    }

    @Override
    public String toString() { //retorna uma string formatada contendo todos os dados da ação.
        StringBuilder builder = new StringBuilder();
        for ( String stock : stocks){
            builder.append(stock).append("\n");
        }
        return builder.toString();
    }
}
