

DROP TABLE IF EXISTS message;

CREATE TABLE message
(
  id serial NOT NULL,
  content text,
  "timestamp" timestamp with time zone DEFAULT now(),
  CONSTRAINT id PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE message
  OWNER TO "postgres";
