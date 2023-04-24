create TABLE IF NOT EXISTS acao (
                      idAcao SERIAL PRIMARY KEY NOT NULL,
                      simbolo text unique,
                      nome TEXT
);