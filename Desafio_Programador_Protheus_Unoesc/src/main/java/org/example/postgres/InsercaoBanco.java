package org.example.postgres;

import org.example.DAO.AcoesDAO;
import org.example.DAO.CotacaoDAO;
import org.example.dadosApi.DadosAcoes;
import org.example.dadosApi.DadosAcoes;

import java.sql.Connection;

public class InsercaoBanco {

    private ConexaoFactory connection;
    //Construtor que inicializa uma instância da classe ConexaoFactory
    public InsercaoBanco(){
        this.connection = new ConexaoFactory();
    }

    //Insere uma ação no banco de dados
    public void inserirAcao(DadosAcoes.Resultado resultado) {
        Connection conn = connection.recuperaConexao();
        new AcoesDAO(conn).insertAcoes(resultado);
    }

    //Insere uma cotação no banco de dados
    public void inserirCotacao(DadosAcoes.Resultado resultado) {
        Connection conn = connection.recuperaConexao();
        new CotacaoDAO(conn).insertCotacoes(resultado);
    }

}
