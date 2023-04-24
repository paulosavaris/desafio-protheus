package org.example.DAO;

import org.example.dadosApi.DadosAcoes;
import org.example.dadosApi.DadosAcoes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CotacaoDAO {

    private Connection conexao;

    public CotacaoDAO(Connection connection) {
        this.conexao = connection;
    }

    public void insertCotacoes(DadosAcoes.Resultado resultado) {
       // System.out.println("Inserindo cotação para a ação " + resultado.getSimbolo());
       // System.out.println(resultado.getCotacao());
        String sql = "INSERT INTO cotacao(idacao, cotacao, valormercado, volumetransacoes, moeda, data) VALUES(?, ?, ?, ?, ?, ?)";
        try {
            // Prepara o statement SQL para a inserção na tabela cotacao

            PreparedStatement pstmt = conexao.prepareStatement(sql);
            // Obter o idAcao correspondente ao símbolo informado
            String sqlConsulta = "SELECT idacao FROM acao WHERE simbolo = ?";
            PreparedStatement pstmtConsulta = conexao.prepareStatement(sqlConsulta);
            pstmtConsulta.setString(1, resultado.getSimbolo());
            // Executa a consulta e guarda o resultado em um ResultSet
            ResultSet rs = pstmtConsulta.executeQuery();
            if (rs.next()) {
                int idacao = rs.getInt("idAcao");
                // Inserir a nova cotação na tabela cotacao, associada à ação correspondente
                pstmt.setInt(1, idacao);
                pstmt.setFloat(2, resultado.getCotacao());
                pstmt.setFloat(3, resultado.getValorDeMercado());
                pstmt.setFloat(4,resultado.getVolumeDeTransacoes());
                pstmt.setString(5, resultado.getMoeda());
                pstmt.setTimestamp(6, resultado.getData());
                // Executa a inserção na tabela cotacao
                pstmt.execute();
            } else {
                // Caso não exista um registro no ResultSet, lança uma exceção informando que a ação não foi encontrada
                throw new IllegalArgumentException("Ação não encontrada: " + resultado.getSimbolo());
            }
        } catch (SQLException e) {
            // Caso ocorra uma exceção SQL, lança uma exceção RuntimeException com a mensagem de erro
            throw new RuntimeException(e);
        }
    }
}
//PreparedStatement para evitar injeção de SQL e ResultSet para acessar o resultado da consulta ao banco de dados