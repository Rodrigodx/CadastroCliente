ALTER TABLE telefone DROP CONSTRAINT telefone_tipo_check;

ALTER TABLE telefone
ADD CONSTRAINT telefone_tipo_check CHECK (tipo IN ('1', '2', '3'));
