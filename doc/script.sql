/* Database generated with pgModeler (PostgreSQL Database Modeler).
  Project Site: pgmodeler.com.br
  Model Author: --- */


/* Database creation must be done outside an multicommand file.
   These commands were put in this file only for convenience.

-- object: fulo | type: DATABASE -- 
CREATE DATABASE fulo
	ENCODING = 'UTF8'
;

*/

-- object: public.pessoa | type: TABLE -- 
CREATE TABLE public.pessoa(
	sq_pessoa integer NOT NULL,
	de_nome varchar(50) NOT NULL,
	ds_email varchar(50) NOT NULL,
	CONSTRAINT pk_pessoa PRIMARY KEY (sq_pessoa)
)
WITH (OIDS=FALSE);

-- object: public.usuario | type: TABLE -- 
CREATE TABLE public.usuario(
	sq_pessoa integer NOT NULL,
	sq_perfil integer NOT NULL,
	CONSTRAINT pk_usuario PRIMARY KEY (sq_pessoa)
)
WITH (OIDS=FALSE);

-- object: public.perfil | type: TABLE -- 
CREATE TABLE public.perfil(
	sq_perfil integer NOT NULL,
	de_perfil smallint NOT NULL,
	CONSTRAINT pk_perfil PRIMARY KEY (sq_perfil)
)
WITH (OIDS=FALSE);

-- object: fk_usuario_perfil | type: CONSTRAINT -- 
ALTER TABLE public.usuario ADD CONSTRAINT fk_usuario_perfil FOREIGN KEY (sq_perfil)
REFERENCES public.perfil (sq_perfil) MATCH FULL
ON DELETE CASCADE ON UPDATE CASCADE NOT DEFERRABLE;

-- object: fk_usuario_pessoa | type: CONSTRAINT -- 
ALTER TABLE public.usuario ADD CONSTRAINT fk_usuario_pessoa FOREIGN KEY (sq_pessoa)
REFERENCES public.pessoa (sq_pessoa) MATCH FULL
ON DELETE CASCADE ON UPDATE CASCADE NOT DEFERRABLE;


