-----------------------------------------------------
--DB schema creation

CREATE SCHEMA "app" AUTHORIZATION "postgres";

-----------------------------------------------------
--Persistence Table Creation
CREATE TABLE "app"."archetype_data" (
  "id" BIGINT NOT NULL, 
  "context_id" VARCHAR(1000), 
  "archetype_name" VARCHAR(1000), 
  "archetype_path" VARCHAR(1000), 
  "name" VARCHAR(1000), 
  "value_string" VARCHAR(1000), 
  "value_int" BIGINT, 
  "value_double" DOUBLE PRECISION, 
  "session_id" VARCHAR(40), 
  "instance_index" INTEGER, 
  "field_created_at" TIMESTAMP(0) WITHOUT TIME ZONE, 
  "archetype_created_at" TIMESTAMP(0) WITHOUT TIME ZONE, 
  "tolven_context" VARCHAR(1000), 
  "value_at_path" VARCHAR(255), 
  "deleted" BOOLEAN DEFAULT false, 
  CONSTRAINT "archetype_data_pkey" PRIMARY KEY("id")
) WITHOUT OIDS;
-----------------------------------------------------
--Archetype Node Container Composite Type creation

CREATE TYPE "public"."nodescontainer" AS (
  "id" BIGINT[],
  "context_id" VARCHAR[],
  "archetype_name" VARCHAR[],
  "archetype_path" VARCHAR[],
  "name" VARCHAR[],
  "value_string" VARCHAR[],
  "value_int" BIGINT[],
  "value_double" DOUBLE PRECISION[],
  "session_id" VARCHAR[],
  "instance_index" INTEGER[],
  "archetype_created_at" TIMESTAMP WITHOUT TIME ZONE[],
  "field_created_at" TIMESTAMP WITHOUT TIME ZONE[]
);

-----------------------------------------------------
--postgresql language creation for functions
create language plpgsql;
-----------------------------------------------------
--Postgresql Function creation
CREATE OR REPLACE FUNCTION "public"."getnodecontainers2" (context_identifier varchar) RETURNS SETOF "public"."nodescontainer" AS
$body$
DECLARE
archetype_data_row app.archetype_data%ROWTYPE;
archetype_data_row_main app.archetype_data%ROWTYPE;
nodescontainervar NodesContainer%ROWTYPE;
nodescontainervarEmpty NodesContainer%ROWTYPE;
session_Identifier varchar;
indexVar integer := 0;
recordCountVar integer := 0;
lastSessionId varchar := '';
BEGIN
     FOR archetype_data_row IN 
	select 
		app.archetype_data.*		
	from app.archetype_data 
    WHERE app.archetype_data.context_id = context_Identifier and app.archetype_data.deleted = FALSE order by app.archetype_data.archetype_created_at desc,  app.archetype_data.session_id    
    LOOP		
		--if we have just switched to a new set of rows with a new sesss id, send the previous container to arr
		IF archetype_data_row.session_id != lastSessionId  and lastSessionId != '' THEN -- the emtpy string check allows us to skip the first ever row
			return NEXT nodescontainervar;
			indexVar := 0; --reset index to 0
			recordCountVar := recordCountVar + 1;
			nodescontainervar := nodescontainervarEmpty;
		END IF;
	
		nodescontainervar.id[indexVar] := archetype_data_row.id;
		nodescontainervar.context_Id[indexVar] := archetype_data_row.context_Id;
		nodescontainervar.archetype_name[indexVar] := archetype_data_row.archetype_name;   
		nodescontainervar.archetype_path[indexVar] := archetype_data_row.archetype_path;
		nodescontainervar.name[indexVar] := archetype_data_row.name;
		nodescontainervar.value_string[indexVar] := archetype_data_row.value_string;
		nodescontainervar.value_int[indexVar] := archetype_data_row.value_int;
		nodescontainervar.value_double[indexVar] := archetype_data_row.value_double;
		nodescontainervar.session_id[indexVar] := archetype_data_row.session_id;		
		nodescontainervar.instance_index[indexVar] := archetype_data_row.instance_index;				
		nodescontainervar.archetype_created_at[indexVar] := archetype_data_row.archetype_created_at;

		indexVar := indexVar + 1;	
		IF recordCountVar > 17 THEN
		EXIT;
		END IF;
		lastSessionId := archetype_data_row.session_id;

    END LOOP;
	IF nodescontainervar.id is not NULL THEN
		return NEXT nodescontainervar;
	END IF;
	return;


    
END;
$body$
LANGUAGE 'plpgsql'
VOLATILE
CALLED ON NULL INPUT
SECURITY INVOKER;
-----------------------------------------------------
--Postgresql Function Creation

CREATE OR REPLACE FUNCTION "public"."getnodecontainers4" (context_identifier varchar, startdate char, enddate char, adlname char) RETURNS SETOF "public"."nodescontainer" AS
$body$
DECLARE
archetype_data_row app.archetype_data%ROWTYPE;
archetype_data_row_main app.archetype_data%ROWTYPE;
nodescontainervar NodesContainer%ROWTYPE;
nodescontainervarEmpty NodesContainer%ROWTYPE;
session_Identifier varchar;
indexVar integer := 0;
lastSessionId varchar := '';
BEGIN
     FOR archetype_data_row IN 
	select 
		app.archetype_data.*		
	from app.archetype_data 
    WHERE app.archetype_data.context_id = context_Identifier 
	AND app.archetype_data.archetype_name = adlName
	AND app.archetype_data.deleted = FALSE
	AND app.archetype_data.archetype_created_at BETWEEN  CAST(startDate as TIMESTAMP )  AND CAST (endDate AS TIMESTAMP)	
	order by app.archetype_data.archetype_created_at desc,  app.archetype_data.session_id
    LOOP
--    	RAISE NOTICE 'containervar: %',archetype_data_row.id;   		
		--if we have just switched to a new set of rows with a new sesss id, send the previous container to arr
--        RAISE NOTICE 'containervar: %',nodescontainervar.session_id;
		IF archetype_data_row.session_id != lastSessionId  and lastSessionId != '' THEN -- the emtpy string check allows us to skip the first ever row
--	        RAISE NOTICE 'NOW SWITCHING TO NEW GROUP OF ROWS, OLD: %, NEW: %, INDEXVAR: %',lastSessionId,archetype_data_row.session_id,indexVar;
			return NEXT nodescontainervar;
--            RAISE NOTICE 'indexVarBeforeReset: %',indexVar;			
			indexVar := 0; --reset index to 0
            nodescontainervar := nodescontainervarEmpty;
  --      ELSE 
    --    	RAISE NOTICE 'NO SESSION ID UPDATE: lastsesId: %, currentSesId %',lastSessionId,nodescontainervar.session_id;			
		END IF;
	
		nodescontainervar.id[indexVar] := archetype_data_row.id;
		nodescontainervar.context_Id[indexVar] := archetype_data_row.context_Id;
		nodescontainervar.archetype_name[indexVar] := archetype_data_row.archetype_name;   
		nodescontainervar.archetype_path[indexVar] := archetype_data_row.archetype_path;
		nodescontainervar.name[indexVar] := archetype_data_row.name;
		nodescontainervar.value_string[indexVar] := archetype_data_row.value_string;
		nodescontainervar.value_int[indexVar] := archetype_data_row.value_int;
		nodescontainervar.value_double[indexVar] := archetype_data_row.value_double;
		nodescontainervar.session_id[indexVar] := archetype_data_row.session_id;		
		nodescontainervar.instance_index[indexVar] := archetype_data_row.instance_index;				
		nodescontainervar.archetype_created_at[indexVar] := archetype_data_row.archetype_created_at;

		indexVar := indexVar + 1;	
		lastSessionId := archetype_data_row.session_id;

    END LOOP;
	IF nodescontainervar.id is not NULL THEN
		return NEXT nodescontainervar;
	END IF;
	return;


    
END;
$body$
LANGUAGE 'plpgsql'
VOLATILE
CALLED ON NULL INPUT
SECURITY INVOKER;

-----------------------------------------------------
--sequence creator
CREATE SEQUENCE "public"."hibernate_sequence"
    INCREMENT 1  MINVALUE 1
    MAXVALUE 9223372036854775807  START 378
    CACHE 1;



