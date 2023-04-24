
create TABLE acao (
  idAcao SERIAL PRIMARY KEY NOT NULL,
  simbolo text unique,
  nome TEXT
);

CREATE TABLE cotacao (
  idCotacao SERIAL PRIMARY KEY,
  idAcao INTEGER REFERENCES acao(idAcao) ON DELETE CASCADE ON UPDATE CASCADE NOT NULL,
  cotacao FLOAT,
  valorMercado FLOAT,
  volumeTransacoes FLOAT,
  moeda TEXT,
  data timestamp
);

CREATE TABLE log (
  id SERIAL PRIMARY KEY,
  data_hora TIMESTAMP DEFAULT NOW(),
  idCotacao INTEGER REFERENCES cotacao(idCotacao),
  idAcao INTEGER REFERENCES acao(idAcao),
  informacao TEXT
);


CREATE OR REPLACE FUNCTION log_cotacao()
RETURNS TRIGGER AS $$
DECLARE
    log_message text;
BEGIN
  IF (TG_OP = 'INSERT') THEN
    SELECT INTO log_message 
      CONCAT('Nova linha inserida na tabela cotacao ',
        CASE WHEN OLD.cotacao IS NOT NULL and OLD.cotacao IS DISTINCT FROM NEW.cotacao 
          THEN CONCAT('alterado cotacao para ', NEW.cotacao::text, ', ')
          ELSE ''
        END,
        CASE WHEN OLD.valormercado IS NOT NULL AND OLD.valormercado IS DISTINCT FROM NEW.valormercado 
          THEN CONCAT('alterado valormercado para ', NEW.valormercado::text, ', ')
          ELSE ''
        END,
        CASE WHEN OLD.volumeTransacoes IS NOT NULL AND OLD.volumeTransacoes IS DISTINCT FROM NEW.volumeTransacoes 
          THEN CONCAT('alterado volumeTransacoes para ', NEW.volumeTransacoes::text, ', ')
          ELSE ''
        END,
        CASE WHEN OLD.moeda IS NOT NULL AND OLD.moeda IS DISTINCT FROM NEW.moeda 
          THEN CONCAT('alterado moeda de para ', NEW.moeda, ', ')
          ELSE ''
        END,
        CASE WHEN OLD.data IS NOT NULL and OLD.data IS DISTINCT FROM NEW.data 
          THEN CONCAT('alterado data de para ', NEW.data::text, ', ')
          ELSE ''
        END
      )
    FROM cotacao WHERE idCotacao = NEW.idCotacao;
    IF log_message IS NOT NULL THEN
      INSERT INTO log (data_hora, informacao, idCotacao, idAcao)
      VALUES (now(), log_message, NEW.idCotacao, NEW.idAcao);
    END IF;
  END IF;
  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trig_log_cotacao
AFTER INSERT OR UPDATE ON cotacao
FOR EACH ROW
EXECUTE FUNCTION log_cotacao();


COPY 
(SELECT a.simbolo as Símbolo, a.nome as Nome, 
to_char(c.cotacao, 'FM999G999D9999' ) AS Cotação, 
to_char(c.valormercado::numeric, 'FM999G999G999G999G900D09')  AS "Valor de Mercado", 
to_char(c.volumetransacoes , 'FM999G999G999G999G900D09') AS "Volume de Transações", c.moeda as Moeda, 
TO_CHAR(c."data" ,'HH24:MI:SS DD/MM/YYYY') as Data 
FROM acao a 
JOIN (SELECT idacao, MAX(idcotacao) as idcotacao FROM cotacao GROUP BY idacao) cmax ON cmax.idacao = a.idacao 
JOIN cotacao c ON c.idcotacao = cmax.idcotacao 
WHERE WHERE UPPER(a.simbolo) in ('PETR4', 'MGLU3'); 
TO 'D:/Curriculo/UNOESC/Desafio_UNOESC/ApiUnoesc/Teste.csv' WITH (FORMAT CSV, HEADER, DELIMITER ';', ENCODING 'UTF-8');








