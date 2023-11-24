--mysql -u root -p

CREATE DATABASE events_ces3; --Crear BD

USE events_ces3;


DROP TABLE IF EXISTS event_attendees;
DROP TABLE IF EXISTS attendees;
DROP TABLE IF EXISTS events;


CREATE TABLE events(
    id INT(5) UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL,
    description TEXT,
    date DATETIME NOT NULL,
    location VARCHAR(255) NOT NULL
); 


CREATE TABLE attendees(
    document VARCHAR(20) PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    first_last_name VARCHAR(100) NOT NULL,
    second_last_name VARCHAR(100)
); 

CREATE TABLE event_attendees(
	event_id INT(5) UNSIGNED,
	doc_attendees VARCHAR(20),
	register_date DATETIME NOT NULL DEFAULT CURRENT_DATE,
	PRIMARY KEY(doc_attendees, event_id),
	FOREIGN KEY (doc_attendees) REFERENCES attendees(document),
	FOREIGN KEY (event_id) REFERENCES events(id)
);


---------------------------------------DML
INSERT INTO events(title, description, date, location) 
VALUES('Exposición Ingenierías',
        'En esta exposición se presentarán todos los proyectos actuales de la Facultad de Minas',
        '2023-11-23 14:30:00','Universidad Nacional sede Medellin');
INSERT INTO events(title, description, date, location) 
VALUES('Biblioteca Abierta 24 horas',
        'Tendremos: Asesorías, tableros, consomé, café y rutas campus',
        '2023-11-23 06:00:00','Biblioteca Efe Gomez UNAL');
INSERT INTO events(title, description, date, location) 
VALUES('Charla Retos de la Universidad para la construcción del conocimiento',
        'Presentado por Ligia Estela Urrego y William Vasquez',
        '2023-11-22 17:00:00','Canal de YouTube');
INSERT INTO events(title, description, date, location) 
VALUES('Implementación del Sistema de Distrital de Cuidados de la ciudad de Medellín',
        'A cargo de: Laura Carla Moisá Elicabide. Profesora adscrita al Departamento de Economía',
        '2023-11-23 10:00:00','Bloque 46, primer piso');
INSERT INTO events(title, description, date, location) 
VALUES('CONVOCATORIA 7 - CRÉDITOS EDUCATIVOS CONDONABLES PARA MAESTRÍAS DE INVESTIGACIÓN',
        'Exposición',
        '2023-12-06 10:00:00','Bloque 46, primer piso');
INSERT INTO events(title, description, date, location) 
VALUES('Cuento: El laberinto de los ídolos',
        'Por: Esteban Páez Quintero Historiador Facultad de Ciencias Humanas y Económicas',
        '2023-12-06 10:00:00','Bloque 21');
INSERT INTO events(title, description, date, location) 
VALUES('Conferencia de Finanzas con Oscar León García',
        'Capacidad 280 personas, hasta el momento tenemos 180 registrados.',
        '2023-12-06 10:00:00','Auditorio Gerardo Molina');

INSERT INTO attendees (document, full_name, first_last_name, second_last_name) VALUES ('123456789', 'Juan Pérez', 'Pérez', NULL);
INSERT INTO attendees (document, full_name, first_last_name, second_last_name) VALUES ('987654321', 'Ana Gómez Martínez', 'Gómez', 'Martínez');
INSERT INTO attendees (document, full_name, first_last_name, second_last_name) VALUES ('567890123', 'Luisa Fernanda González Rodríguez', 'González', 'Rodríguez');
INSERT INTO attendees (document, full_name, first_last_name, second_last_name) VALUES ('A123BC', 'Carlos Ramírez', 'Ramírez', NULL);
INSERT INTO attendees (document, full_name, first_last_name, second_last_name) VALUES ('111222333', 'María', "Hernandez", NULL);
INSERT INTO attendees (document, full_name, first_last_name, second_last_name) VALUES ('444555666', 'Javier Sánchez García', 'Sánchez', 'García');
INSERT INTO attendees (document, full_name, first_last_name, second_last_name) VALUES ('AB12', 'Roberto López', 'López', NULL);

INSERT INTO event_attendees(event_id, doc_attendees) VALUES(1,'123456789'); 
INSERT INTO event_attendees(event_id, doc_attendees) VALUES(1,'AB12'); 
INSERT INTO event_attendees(event_id, doc_attendees) VALUES(1,'A123BC'); 
INSERT INTO event_attendees(event_id, doc_attendees) VALUES(2,'111222333'); 
INSERT INTO event_attendees(event_id, doc_attendees) VALUES(2,'567890123'); 
INSERT INTO event_attendees(event_id, doc_attendees) VALUES(2,'123456789'); 
INSERT INTO event_attendees(event_id, doc_attendees) VALUES(2,'AB12'); 
INSERT INTO event_attendees(event_id, doc_attendees) VALUES(2,'987654321'); 
INSERT INTO event_attendees(event_id, doc_attendees) VALUES(3,'444555666'); 
INSERT INTO event_attendees(event_id, doc_attendees) VALUES(4,'444555666'); 
INSERT INTO event_attendees(event_id, doc_attendees) VALUES(5,'444555666'); 
INSERT INTO event_attendees(event_id, doc_attendees) VALUES(6,'444555666'); 









