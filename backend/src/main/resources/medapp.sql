CREATE TABLE "patients"
(
    "id"               int4 NOT NULL,
    "first_name"       varchar(255),
    "second_name"      varchar(255),
    "last_name"        varchar(255),
    "insurance_number" varchar,
    "created_at"       timestamptz(0),
    "updated_at"       timestamptz(0),
    "deleted"          varchar,
    PRIMARY KEY ("id")
)
    WITHOUT OIDS;
CREATE TABLE "staff"
(
    "id"                int4 NOT NULL,
    "first_name"        varchar(255),
    "second_name"       varchar(255),
    "last_name"         varchar(255),
    "specialization_id" int4 NOT NULL,
    "created_at"        timestamptz(0),
    "updated_at"        timestamptz(0),
    "deleted"           varchar,
    PRIMARY KEY ("id")
)
    WITHOUT OIDS;
CREATE TABLE "specializations"
(
    "id"          int4    NOT NULL,
    "description" varchar NOT NULL,
    "created_at"  timestamptz(0),
    "updated_at"  timestamptz(0),
    "deleted"     varchar(255),
    PRIMARY KEY ("id")
)
WITHOUT OIDS;
CREATE TABLE "prescriptions"
(
    "id"              int4 NOT NULL,
    "patient_id"      int4,
    "procedure_id"    int4,
    "drug_id"         int4,
    "time_pattern_id" int4,
    "start_date"      timestamptz(0),
    "end_date"        timestamptz(0),
    "dosage"          varchar(255),
    "description"     varchar,
    "updated_at"      timestamptz(0),
    "created_at"      timestamptz(0),
    "deleted"         varchar,
    PRIMARY KEY ("id")
)
    WITHOUT OIDS;
CREATE TABLE "procedures"
(
    "id"          int4 NOT NULL,
    "description" varchar,
    "created_at"  timestamptz(0),
    "updated_at"  timestamptz(0),
    "deleted"     varchar,
    PRIMARY KEY ("id")
)
    WITHOUT OIDS;
CREATE TABLE "events"
(
    "id"           int4 NOT NULL,
    "patient_id"   int4,
    "procedure_id" int4,
    "room_id"      int4,
    "staff_id"     int4,
    "status"       varchar(255),
    "start_date"   timestamptz(0),
    "end_date"     timestamptz(0),
    "created_at"   timestamptz(0),
    "updated_at"   timestamptz(0),
    "deleted"      varchar,
    PRIMARY KEY ("id")
)
    WITHOUT OIDS;
CREATE TABLE "rooms"
(
    "id"          int4 NOT NULL,
    "description" varchar,
    "created_at"  timestamptz(0) DEFAULT NOW(),
    "updated_at"  timestamptz(0),
    "deleted"     varchar,
    PRIMARY KEY ("id")
)
    WITHOUT OIDS;
CREATE TABLE "drugs"
(
    "id"          int4 NOT NULL,
    "description" varchar,
    "type"        varchar(255),
    "created_at"  timestamptz(0),
    "updated_at"  timestamptz(0),
    "deleted"     varchar,
    PRIMARY KEY ("id")
)
    WITHOUT OIDS;
CREATE TABLE "therapy_log"
(
    "id"               int4 NOT NULL,
    "patient_id"       int4,
    "prescriptionn_id" int4,
    "diagnose_id"      int4,
    "description"      varchar,
    "created_at"       timestamptz(0),
    "updated_at"       timestamptz(0),
    "deleted"          varchar,
    PRIMARY KEY ("id")
)
    WITHOUT OIDS;
CREATE TABLE "schedule"
(
    "id"         int4 NOT NULL,
    "staff_id"   int4,
    "date"       timestamptz(255),
    "start_time" timestamptz(0),
    "end_time"   timestamptz(0),
    "created_at" timestamptz(0),
    "updated_at" timestamptz(0),
    "deleted"    varchar,
    PRIMARY KEY ("id")
)
    WITHOUT OIDS;
CREATE TABLE "diagnoses"
(
    "id"          int4 NOT NULL,
    "description" varchar,
    "created_at"  timestamptz(0),
    "updated_at"  timestamptz(0),
    "deleted"     varchar,
    PRIMARY KEY ("id")
)
    WITHOUT OIDS;

ALTER TABLE "staff"
    ADD CONSTRAINT "fk_doctors_specializations_1" FOREIGN KEY ("specialization_id") REFERENCES "specializations" ("id");
ALTER TABLE "prescriptions"
    ADD CONSTRAINT "fk_prescriptions_procedures_1" FOREIGN KEY ("procedure_id") REFERENCES "procedures" ("id");
ALTER TABLE "prescriptions"
    ADD CONSTRAINT "fk_prescriptions_drugs_1" FOREIGN KEY ("drug_id") REFERENCES "drugs" ("id");
ALTER TABLE "therapy_log"
    ADD CONSTRAINT "fk_card_records_patients_1" FOREIGN KEY ("patient_id") REFERENCES "patients" ("id");
ALTER TABLE "therapy_log"
    ADD CONSTRAINT "fk_therapy_log_prescriptions_1" FOREIGN KEY ("prescriptionn_id") REFERENCES "prescriptions" ("id");
ALTER TABLE "schedule"
    ADD CONSTRAINT "fk_schedule_staff_1" FOREIGN KEY ("staff_id") REFERENCES "staff" ("id");
ALTER TABLE "events"
    ADD CONSTRAINT "fk_events_patients_1" FOREIGN KEY ("patient_id") REFERENCES "patients" ("id");
ALTER TABLE "events"
    ADD CONSTRAINT "fk_events_procedures_1" FOREIGN KEY ("procedure_id") REFERENCES "procedures" ("id");
ALTER TABLE "events"
    ADD CONSTRAINT "fk_events_rooms_1" FOREIGN KEY ("room_id") REFERENCES "rooms" ("id");
ALTER TABLE "events"
    ADD CONSTRAINT "fk_events_staff_1" FOREIGN KEY ("staff_id") REFERENCES "staff" ("id");
ALTER TABLE "therapy_log"
    ADD CONSTRAINT "fk_therapy_log_diagnoses_1" FOREIGN KEY ("diagnose_id") REFERENCES "diagnoses" ("id");

CREATE SEQUENCE public.specializations_item_id
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE public.specializations_item_id
    OWNER TO postgres;

