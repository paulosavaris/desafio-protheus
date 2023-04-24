package org.example.postgres;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {

    public Connection recuperaConexao() {
        try {
            // Recupera uma conexão com o banco de dados Postgres
            return DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/apiunoesc?user=postgres&password=System01"); //dados do banco
        }catch (SQLException e){
            // Lança uma exceção em caso de falha na conexão
            throw new RuntimeException(e);
        }
    }
}
