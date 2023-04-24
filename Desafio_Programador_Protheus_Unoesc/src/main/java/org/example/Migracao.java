package org.example;

import org.flywaydb.core.Flyway;

public class Migracao {

    public static void migration() {
        // Configuração do Flyway com os dados da conexão do banco de dados
        // alterar conforme banco de dados
        Flyway flyway = Flyway.configure().dataSource("jdbc:postgresql://localhost:5432/apiunoesc", "postgres", "System01").load();
        // O método "repair" pode ser utilizado em casos de corrupção no histórico de migração, para reparar o schema version
        //flyway.repair();

        // Chamada do método "migrate" para executar a migração do banco de dados
        flyway.migrate();
    }
}