CREATE TABLE IF NOT EXISTS log (
                     id SERIAL PRIMARY KEY,
                     data_hora TIMESTAMP DEFAULT NOW(),
                     idCotacao INTEGER REFERENCES cotacao(idCotacao),
                     idAcao INTEGER REFERENCES acao(idAcao),
                     informacao TEXT
);