--TODO: refactor to single insert
-- Initial test data
INSERT INTO covid(country, active, death, recovered,continent) VALUES('country1' ,20, 1,2 ,'asia');
INSERT INTO covid(country, active, death, recovered,continent) VALUES('country2' ,99, 6,1 ,'asia');
INSERT INTO covid(country, active, death, recovered,continent) VALUES('country3' ,80, 3,0 ,'asia');
INSERT INTO covid(country, active, death, recovered,continent) VALUES('country4',60, 7,3  ,'xx'  );
INSERT INTO covid(country, active, death, recovered,continent) VALUES('country5',60, 5,6  ,'eu'  );
INSERT INTO covid(country, active, death, recovered,continent) VALUES('country6',550, 2,77,'eu'  );
INSERT INTO covid(country, active, death, recovered,continent) VALUES('country7',670, 4,88,'eu'  );

-- AGENTS
--INSERT INTO public.agent
--(email, first_name, second_name, first_surname, second_surname, organization_id, manager_id, available, status, role)
--VALUES
--    ('1-prueba@test.com', 'prueba', null, 'pruebita', null, 1, null, true, 'ENABLED', 'MANAGER'),
--    ('1-manager2@test.com', 'manager', null, 'test', null, 1, null, true, 'ENABLED', 'MANAGER'),
--    ('1-agent@test.com', 'agent', null, 'test', null, 1, 1, true, 'ENABLED', 'AGENT'),
--    ('1-manager_segment2@test.com', 'manager_s2', null, 'test_s2', null, 1, null, true, 'ENABLED', 'MANAGER'),
--    ('1-agent_segment2@test.com', 'agent_s2', null, 'test:s2', null, 1, 4, true, 'ENABLED', 'AGENT'),
--    ('1-manager_segment3@test.com', 'manager_s3', null, 'test_s3', null, 1, null, true, 'ENABLED', 'MANAGER'),
--    ('1-agent_segment3@test.com', 'agent_s3', null, 'test:s3', null, 1, 6, true, 'ENABLED', 'AGENT'),
--    ('admin@test.com', 'admin', null, 'admin', null, 1, null, false, 'ENABLED', 'ADMINISTRATOR'),
--    ('supervisor@test.com', 'supervisor', null, 'supervisor', null, 1, null, false, 'ENABLED', 'SUPERVISOR');

