DROP TABLE IF EXISTS Message;
CREATE TABLE Message (
    "id" SERIAL,
    "message" varchar(255) COLLATE "default",
    "created_at" timestamp(6) NOT NULL,
    CONSTRAINT "Message_pkey" PRIMARY KEY ("id") NOT DEFERRABLE INITIALLY IMMEDIATE
)
WITH (OIDS=FALSE);
ALTER TABLE Message OWNER TO "postgres";