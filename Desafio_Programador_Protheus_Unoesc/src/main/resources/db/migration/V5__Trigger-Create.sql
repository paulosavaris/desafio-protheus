CREATE TRIGGER trig_log_cotacao
    AFTER INSERT OR UPDATE ON cotacao
                        FOR EACH ROW
                        EXECUTE FUNCTION log_cotacao();