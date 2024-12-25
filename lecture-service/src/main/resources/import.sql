INSERT INTO TB_USER (user_name, created_at, modified_at) VALUES('허재', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO TB_USER (user_name, created_at, modified_at) VALUES('하헌우', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO TB_USER (user_name, created_at, modified_at) VALUES('조유강', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO TB_USER (user_name, created_at, modified_at) VALUES('전유진', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO TB_USER (user_name, created_at, modified_at) VALUES('신범준', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO TB_LECTURE (speaker, subject, lecture_date, capacity, created_at, modified_at) VALUES('허재' , 'TDD 특강', CURRENT_DATE, 30, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO TB_LECTURE (speaker, subject, lecture_date, capacity, created_at, modified_at) VALUES('허재' , '클린코드 특강', CURRENT_DATE + 2, 30, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO TB_LECTURE (speaker, subject, lecture_date, capacity, created_at, modified_at) VALUES('하헌우' , '아키텍처 특강', CURRENT_DATE + 2, 30, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);