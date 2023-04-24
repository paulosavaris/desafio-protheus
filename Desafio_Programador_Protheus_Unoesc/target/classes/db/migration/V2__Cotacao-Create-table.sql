CREATE TABLE IF NOT EXISTS cotacao (
                         idCotacao SERIAL PRIMARY KEY,
                         idAcao INTEGER REFERENCES acao(idAcao) ON DELETE CASCADE ON UPDATE CASCADE NOT NULL,
                         cotacao FLOAT,
                         valorMercado FLOAT,
                         volumeTransacoes FLOAT,
                         moeda TEXT,
                         data timestamp
);