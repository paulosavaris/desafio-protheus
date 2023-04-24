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
