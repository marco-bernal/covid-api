-- Incident_Rate: Incidence Rate = cases per 100,000 persons.
-- Case_Fatality_Ratio (%): Case-Fatality Ratio (%) = Number recorded deaths / Number cases.
CREATE TABLE public.covid
(
    id SERIAL NOT NULL,
    country character varying(70) NOT NULL UNIQUE,
    continent character varying(50) NOT NULL UNIQUE,
    confirmed integer NOT NULL,
    death integer NOT NULL,
    recovered integer NOT NULL,
    active integer NOT NULL,
    created_at timestamp default CURRENT_TIMESTAMP,
    updated_at timestamp default CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

