package org.example;

import org.example.consultaApi.AcoesStockParcial;
import org.example.consultaApi.InformacoesDasAcoes;
import org.example.consultaApi.TodasAcoes;
import org.example.postgres.ExportaAcoes;
import org.example.Migracao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    // Cria uma instância do objeto Scanner para leitura de dados do teclado
    private static Scanner teclado = new Scanner(System.in);
    public static void main(String[] args) {

        // Realiza a migração das tabelas do banco de dados com o Flyway
        Migracao tabelas_banco = new Migracao();
        tabelas_banco.migration();

        // Exibe o menu e armazena a opção selecionada pelo usuário
        var opcao = exibirMenu();
        // Loop do menu, enquanto a opção selecionada for diferente de 7 (sair) continuará exibindo o menu
        while (opcao != 7){
            try{
                switch (opcao){
                    case 1: consultarInformacaoAcoes();
                        break;
                    case 2: consultarTodasAcoes();
                        break;
                    case 3: consultarAcoesParciais();
                        break;
                    case 4: ExportAcoes();
                        break;
                }
            } catch (Exception e){
                System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu");
                teclado.next();
            }
            opcao = exibirMenu();
        }
        System.out.println("Finalizada a aplicacao.");
    }

    // Método que exibe o menu e retorna a opção selecionada pelo usuário
    private static int exibirMenu() {
        System.out.println("""
                API brapi - ESCOLHA UMA OPÇÃO:
                1 - Consultar dados das acoes, individual ou em grupo.
                2 - Consulta todas as acoes ( recomendavel rodar a cada inicializacao do programa 
                pois baixa todas as informacoes das acoes no banco).
                3 - Consulta grupos de acoes com as mesma letras informadas.
                4 - Exportar Arquivo CSV.
                7 - Sair / finalizar aplicação 
                """);
        return teclado.nextInt();
    }

    // Método que realiza a consulta de informações de ações individuais ou em grupo
    private static void consultarInformacaoAcoes() throws IOException, InterruptedException, SQLException {

        InformacoesDasAcoes DadosAcoes = new InformacoesDasAcoes();
        DadosAcoes.consultaDadosAcoes();

        System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu principal");
        teclado.nextLine();
    }

    // Método que realiza a consulta de todas as ações disponíveis na API
    private static void consultarTodasAcoes() throws IOException, InterruptedException {

        TodasAcoes stockAll = new TodasAcoes();
        stockAll.allStocks();


        System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu principal");
        teclado.nextLine();
    }

    // Método que realiza a consulta de ações disponíveis com as mesmas letras informadas
    private static void consultarAcoesParciais() throws IOException, InterruptedException {

        AcoesStockParcial consultarStock = new AcoesStockParcial();
        consultarStock.acoesAvailableParcial();

        System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu principal");
        teclado.nextLine();
    }

    private static void ExportAcoes(){
        ExportaAcoes exportAcoes = new ExportaAcoes();
        exportAcoes.exportaAcoes();

        System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu principal");
        teclado.nextLine();
    }


}