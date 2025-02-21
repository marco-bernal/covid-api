--TODO: Research for a dataset in kaggle
CREATE TABLE public.covid
(
    id SERIAL NOT NULL,
    country character varying(70) NOT NULL UNIQUE,
    continent character varying(50) NOT NULL UNIQUE,
    active integer NOT NULL,
    dead integer NOT NULL,
    recovered integer NOT NULL
    created_at timestamp default CURRENT_TIMESTAMP,
    updated_at timestamp default CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);