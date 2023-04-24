package org.example.dadosApi;

import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class DadosAcoes {
    //responsável por armazenar os dados obtidos da API de cotações de ações

    @SerializedName("results") //usada para definir que essa variável "resultados" está mapeada para o campo "results" na resposta JSON da API.
    Resultado[] resultados;

    public Resultado[] getResultados() { //retorna a array de resultados.
        return resultados;
    }
    public void setResultados(Resultado[] resultados) { //define a array de resultados.
        this.resultados = resultados;
    }


    public class Resultado { // contém os dados da ação específicada
        @SerializedName("symbol")
        private String simbolo;
        @SerializedName("shortName")
        private String nome;
        @SerializedName("currency")
        private String moeda;
        @SerializedName("marketCap")
        private float valorDeMercado;
        @SerializedName("regularMarketPrice")
        private float cotacao;
        @SerializedName("regularMarketVolume")
        private float volumeDeTransacoes;
        @SerializedName("regularMarketTime")
        private Timestamp data;

        public String getSimbolo() { // retorna o símbolo da ação.
            return simbolo;
        }

        public  void setSimbolo(String simbolo) { //define o símbolo da ação.
            this.simbolo = simbolo;
        }

        public String getNome() {
            return nome;
        }

        public  void setNome(String nome) {
            this.nome = nome;
        }

        public String getMoeda() {
            return moeda;
        }

        public  void setMoeda(String moeda) {
            this.moeda = moeda;
        }

        public float getValorDeMercado() {
            return valorDeMercado;
        }

        public void setValorDeMercado(float valorDeMercado) {
            this.valorDeMercado = valorDeMercado;
        }

        public float getCotacao() {
            return cotacao;
        }

        public void setCotacao(float cotacao) {
            this.cotacao = cotacao;
        }

        public float getVolumeDeTransacoes() {
            return volumeDeTransacoes;
        }

        public  void setVolumeDeTransacoes(float volumeDeTransacoes) {
            this.volumeDeTransacoes = volumeDeTransacoes;
        }

        public Timestamp getData() {
            return data;
        }

        public void setData(Timestamp data) {
            this.data = data;
        }

        @Override
        public String toString() { //retorna uma string formatada contendo todos os dados da ação.
            DecimalFormat formato = new DecimalFormat("#,##0.00");
            SimpleDateFormat formatadorSaida = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
            return  "Símbolo: '"+simbolo+"' \n"  +
                    "Nome: '"+nome+"' \n"  +
                    "Cotação: '"+formato.format(cotacao)+"' \n"  +
                    "Valor de Mercado: "+ formato.format(valorDeMercado)+" \n" +
                    "Volume de Transações: "+formato.format(volumeDeTransacoes)+"\n"  +
                    "Moeda: "+moeda+"\n"  +
                    "Data: '"+formatadorSaida.format(data)+"'" +
                    "\n";
        }
    }

}
