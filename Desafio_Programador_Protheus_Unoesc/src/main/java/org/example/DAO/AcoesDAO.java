package org.example.DAO;

import org.example.dadosApi.DadosAcoes;
import org.example.dadosApi.DadosAcoes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AcoesDAO {

    private Connection conexao;
    // Construtor da classe que recebe uma conexão já estabelecida
    public AcoesDAO(Connection connection){
        this.conexao = connection;
    }
    
    public void insertAcoes(DadosAcoes.Resultado resultado){
        // String SQL para inserir dados na tabela "acao" com as colunas "simbolo" e "nome"
        String sql =  "Insert into acao(simbolo, nome) values(?, ?)";
        try {
            // Preparação da declaração SQL com os dados a serem inseridos

            PreparedStatement pstmt = conexao.prepareStatement(sql);

            pstmt.setString(1, resultado.getSimbolo());
            pstmt.setString(2, resultado.getNome());
            pstmt.execute();// Execução da declaração SQL

        }catch (SQLException e) {
            if (e.getSQLState().equals("23505")) { // verificar se a exceção é de chave duplicada
                System.out.println("Simbolo "+ resultado.getSimbolo() +" já existente na tabela acao, a nova linha foi descartada \n \n");
                try {
                    // preparar a declaração SQL para inserir o registro de log
                    String log = "INSERT INTO log (informacao, data_hora) VALUES (?, CURRENT_TIMESTAMP)";
                    PreparedStatement stmt = conexao.prepareStatement(log);
                    stmt.setString(1, "Tentativa de inserção de simbolo já existente na tabela acao: " + resultado.getSimbolo());
                    stmt.executeUpdate();
                } catch (SQLException ex) {
                    System.out.println("Erro ao inserir registro de log: " + ex.getMessage());
                }
            }
        }
    }
}
